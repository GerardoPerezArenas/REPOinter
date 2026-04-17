<%-- ============================================================
     interoperabilidadGen.jsp
     Pantalla principal de interoperabilidad con Lanbide.
     Ofrece:
       1. Consulta de corriente de pago TGSS / HHFF para los
          terceros del expediente actual.
       2. CVL masivo desde Excel: el usuario sube un fichero
          .xlsx con columnas NIF y TIPO_DOC, el sistema extrae
          los DNI/NIE y lanza la consulta CVL para cada uno
          dentro del rango de fechas indicado.
     ============================================================ --%>

<%-- Librerías de etiquetas Struts y JSTL usadas en la vista --%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%-- Importaciones Java necesarias para la lógica del scriptlet --%>
<%@page import="es.altia.flexia.integracion.moduloexterno.melanbide_interop.i18n.MeLanbideInteropI18n"%>
<%@page import="es.altia.agora.business.escritorio.UsuarioValueObject" %>
<%@page import="es.altia.common.service.config.Config"%>
<%@page import="es.altia.common.service.config.ConfigServiceHelper"%>
<%@page import="es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.tercero.TerceroVO"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>

<%
    /* --------------------------------------------------------
     * Bloque de inicialización JSP (se ejecuta en el servidor)
     * --------------------------------------------------------
     * 1. Se obtiene el idioma del usuario: primero desde el
     *    parámetro "idioma" de la petición; si no existe, se
     *    recupera del objeto UsuarioValueObject en sesión.
     *    El valor por defecto es 1 (castellano).
     * 2. Se obtiene la instancia singleton de i18n para
     *    resolver los textos en el idioma del usuario.
     * 3. Se leen los parámetros de contexto de la petición:
     *    - nombreModulo      → nombre del módulo de integración
     *    - codOrganizacion   → código de la organización
     *    - numExpediente     → número del expediente activo
     * 4. Se recupera la lista de terceros asociados al
     *    expediente desde los atributos de la petición.
     * -------------------------------------------------------- */

    // Idioma por defecto: 1 = castellano
    int idiomaUsuario = 1;
    if(request.getParameter("idioma") != null)
    {
        try
        {
            idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
        }
        catch(Exception ex)
        {
            // Si el valor no es numérico se mantiene el idioma por defecto
        }
    }

    // Intentar obtener el idioma real del usuario autenticado en sesión
    UsuarioValueObject usuario = new UsuarioValueObject();
    try
    {
        if (session != null)
        {
            if (usuario != null)
            {
                usuario = (UsuarioValueObject) session.getAttribute("usuario");
                idiomaUsuario = usuario.getIdioma();
            }
        }
    }
    catch(Exception ex)
    {
        // Si la sesión no está disponible se continúa con el idioma por defecto
    }

    // Servicio de internacionalización (i18n) para MeLanbide Interop
    MeLanbideInteropI18n meLanbideInteropI18n = MeLanbideInteropI18n.getInstance();

    // Configuración general de la aplicación
    Config m_Config = ConfigServiceHelper.getConfig("common");

    // Parámetros de contexto recibidos desde la petición HTTP
    String nombreModulo     = request.getParameter("nombreModulo");         // Nombre del módulo externo
    String codOrganizacion  = request.getParameter("codOrganizacionModulo"); // Código de organización
    String numExpediente    = request.getParameter("numero");               // Número de expediente activo

    // Objetos de dominio: tercero individual y lista de terceros del expediente
    TerceroVO _tercero = new TerceroVO();
    List<TerceroVO> _tercerosxExpediente = (List<TerceroVO>)request.getAttribute("listaTerceros");
%>

<%-- Hoja de estilos general de la aplicación --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilo.css"/>
<%-- Hoja de estilos específica del módulo MeLanbide Interop --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/extension/melanbide_interop/melanbide_interop.css"/>
<%-- Librería SheetJS (xlsx) para leer ficheros Excel (.xlsx/.xls) en el navegador sin servidor.
     Se carga desde CDN oficial con comprobación de integridad SRI (sha384). --%>
<script type="text/javascript" src="https://cdn.sheetjs.com/xlsx-0.20.3/package/dist/xlsx.full.min.js" integrity="sha384-OLBgp1GsljhM2TJ+sbHjaiH9txEUvgdDTAzHv2P24donTt6/529l+9Ua0vFImLlb" crossorigin="anonymous"></script>

<script type="text/javascript">
    /* ----------------------------------------------------------
     * Funciones de gestión de pestañas (stubs requeridos por el
     * framework de pestañas de Flexia; sin lógica propia aquí).
     * ---------------------------------------------------------- */
    function configurarPestanas() {}
    function ocultarPestanaEpecialidadesRecursos() {}
    function mostrarPestanaEpecialidadesRecursos() {}

    /**
     * recogerListaTerceros()
     * ----------------------
     * Recorre los campos del formulario principal (forms[0]) y
     * devuelve el valor del campo oculto "listaCodTercero", que
     * contiene los códigos de los terceros asociados al expediente.
     * Se usa como parámetro de entrada en las llamadas a los
     * servicios de TGSS y HHFF.
     *
     * Compatibilidad: IE usa .children.length; el resto usa
     * .childElementCount.
     *
     * @returns {string} Valor del campo listaCodTercero o undefined
     *                   si no se encuentra.
     */
    function recogerListaTerceros(){
        var listaTercerosExp;
        var nooChlidren = 0;
        var uno = document.forms[0];
        // Compatibilidad con Internet Explorer
        if(navigator.appName.indexOf("Internet Explorer")!=-1){
            nooChlidren = uno.children.length;
        }else{
            nooChlidren = uno.childElementCount;
        }
        // Recorrer hijos del formulario hasta encontrar el campo correcto
        for(i=0; i<nooChlidren; i++)
        {
            if(uno.children[i].name=="listaCodTercero"){
                listaTercerosExp = uno.children[i].value;
                break;
            }
        }
        return listaTercerosExp;
    }

    /**
     * mostrarRespuestaWS(texto)
     * -------------------------
     * Muestra el texto de respuesta del servicio web al usuario.
     * - En navegadores que soportan showModalDialog (IE antiguo)
     *   usa el diálogo modal Flexia (jsp_alerta), convirtiendo
     *   los saltos de línea en <br>.
     * - En el resto de navegadores usa el alert nativo.
     *
     * @param {string} texto Texto a mostrar al usuario.
     */
    function mostrarRespuestaWS(texto){
        if(window.showModalDialog){
            jsp_alerta("A", texto.replace(/\n/g,"<br>"));
        } else {
            alert(texto);
        }
    }

    /**
     * llamarServicioCorrientePagoTgss()
     * -----------------------------------
     * Llama al servicio "GetConsultaCorrientePagoTGSS" de Lanbide
     * de forma síncrona (AJAX síncrono) para consultar si los
     * terceros del expediente están al corriente de pago con la
     * Tesorería General de la Seguridad Social (TGSS).
     *
     * Flujo:
     *   1. Obtiene la lista de terceros del expediente.
     *   2. Envía POST al servlet PeticionModuloIntegracion.do.
     *   3. Parsea la respuesta XML buscando el nodo <RESPUESTA>.
     *   4. Interpreta CODIGO_OPERACION:
     *      - 0 → éxito: muestra RESULTADO al usuario.
     *      - 1 o >4 → muestra resultado informativo.
     *      - 2 → error general.
     *      - 3 → error en paso de parámetros.
     *      - 4 → expediente sin tercero.
     */
    function llamarServicioCorrientePagoTgss(){
        var listaTercerosExp = recogerListaTerceros();
        var ajax = getXMLHttpRequest();
        var nodos = null;
        var CONTEXT_PATH = '<%=request.getContextPath()%>';
        var url = CONTEXT_PATH + "/PeticionModuloIntegracion.do";
        var parametros = "tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=GetConsultaCorrientePagoTGSS&tipo=0&numero=<%=numExpediente%>&listaTercerosExp="+listaTercerosExp;
        try{
            ajax.open("POST",url,false);
            ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=ISO-8859-1");
            ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");
            ajax.send(parametros);
            if (ajax.readyState==4 && ajax.status==200){
                var xmlDoc = null;
                // Parseo de XML: IE usa ActiveXObject; el resto usa responseXML
                if(navigator.appName.indexOf("Internet Explorer")!=-1){
                    var text = ajax.responseText;
                    xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
                    xmlDoc.async="false";
                    xmlDoc.loadXML(text);
                }else{
                    xmlDoc = ajax.responseXML;
                }
            }
            // Procesar nodo <RESPUESTA> del XML devuelto por el servidor
            nodos = xmlDoc.getElementsByTagName("RESPUESTA");
            if(nodos.length>0){
                var elemento = nodos[0];
                var hijos = elemento.childNodes;
                var codigoOperacion = null;
                var textoRespuestaWS = null;
                // Recorrer hijos para extraer código y resultado
                for(j=0;hijos!=null && j<hijos.length;j++){
                    if(hijos[j].nodeName=="CODIGO_OPERACION"){
                        codigoOperacion = hijos[j].childNodes[0].nodeValue;
                    }
                    else if(hijos[j].nodeName=="RESULTADO"){
                        textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                    }
                }
                // Interpretar código de operación y mostrar resultado o error
                var codigoOperacionNumero = parseInt(codigoOperacion, 10);
                if(codigoOperacionNumero===0){
                    mostrarRespuestaWS(textoRespuestaWS);
                }else if(codigoOperacionNumero===1 || codigoOperacionNumero>4){
                    mostrarRespuestaWS(textoRespuestaWS);
                }else if(codigoOperacionNumero===2){
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                }else if(codigoOperacionNumero===3){
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.pasoParametros")%>');
                }else if(codigoOperacionNumero===4){
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.expSinTercero")%>');
                }else{
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                }
            }else{
                jsp_alerta('A',"Error procesando la solicitud. No se ha podido establecer conexion/obtener respuesta del WebService.");
            }
        }
        catch(Err){
            jsp_alerta('A',"Error procesando la solicitud : " + Err.message);
        }
    }

    /**
     * llamarServicioCorrientePagoHHFF()
     * -----------------------------------
     * Idéntica en estructura a llamarServicioCorrientePagoTgss()
     * pero invoca el servicio "GetConsultaCorrientePagoHHFF" para
     * consultar la corriente de pago con Haciendas Forales (HHFF).
     *
     * Diferencia clave en la interpretación del código:
     *   - Se compara codigoOperacion como string ("0", "1", "2"…)
     *     en lugar de convertir a entero, para mantener la lógica
     *     original de este servicio.
     */
    function llamarServicioCorrientePagoHHFF(){
        var listaTercerosExp = recogerListaTerceros();
        var ajax = getXMLHttpRequest();
        var nodos = null;
        var CONTEXT_PATH = '<%=request.getContextPath()%>';
        var url = CONTEXT_PATH + "/PeticionModuloIntegracion.do";
        var parametros = "tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=GetConsultaCorrientePagoHHFF&tipo=0&numero=<%=numExpediente%>&listaTercerosExp="+listaTercerosExp;
        try{
            ajax.open("POST",url,false);
            ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=ISO-8859-1");
            ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");
            ajax.send(parametros);
            if (ajax.readyState==4 && ajax.status==200){
                var xmlDoc = null;
                // Parseo de XML: IE usa ActiveXObject; el resto usa responseXML
                if(navigator.appName.indexOf("Internet Explorer")!=-1){
                    var text = ajax.responseText;
                    xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
                    xmlDoc.async="false";
                    xmlDoc.loadXML(text);
                }else{
                    xmlDoc = ajax.responseXML;
                }
            }
            // Procesar nodo <RESPUESTA> del XML
            nodos = xmlDoc.getElementsByTagName("RESPUESTA");
            if(nodos.length>0){
                var elemento = nodos[0];
                var hijos = elemento.childNodes;
                var codigoOperacion = null;
                var textoRespuestaWS = null;
                // Extraer código y resultado de los nodos hijo
                for(j=0;hijos!=null && j<hijos.length;j++){
                    if(hijos[j].nodeName=="CODIGO_OPERACION"){
                        codigoOperacion = hijos[j].childNodes[0].nodeValue;
                    }
                    else if(hijos[j].nodeName=="RESULTADO"){
                        textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                    }
                }
                // Interpretar código de operación (comparación como string)
                if(codigoOperacion=="0"){
                    mostrarRespuestaWS(textoRespuestaWS);
                }else if(codigoOperacion=="1" || parseInt(codigoOperacion,10) > 4){
                    mostrarRespuestaWS(textoRespuestaWS);
                }else if(codigoOperacion=="2"){
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                }else if(codigoOperacion=="3"){
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.pasoParametros")%>');
                }else if(codigoOperacion=="4"){
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.expSinTercero")%>');
                }else{
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                }
            }else{
                jsp_alerta('A',"Error procesando la solicitud. No se ha podido establecer conexion/obtener respuesta del WebService.");
            }
        }
        catch(Err){
            jsp_alerta('A',"Error procesando la solicitud : " + Err.message);
        }
    }

    /**
     * ejecutarCvlMasivoDesdeTexto()
     * ------------------------------
     * Envía al servidor la lista CSV de documentos (NIF/NIE)
     * acumulada en el textarea oculto "listaDocsMasivo" para
     * lanzar el proceso CVL masivo.
     *
     * Formato esperado en el textarea (una línea por documento):
     *   <NIF>;<TIPO_DOC>
     *   Ejemplo:
     *     11111111H;NIF
     *     22222222J;NIF
     *
     * Parámetros enviados al servlet PeticionModuloIntegracion.do:
     *   - tarea              = preparar
     *   - modulo             = MELANBIDE_INTEROP
     *   - operacion          = ejecutarCvlMasivoDesdeTexto
     *   - numero             = número de expediente (del JSP)
     *   - codOrganizacion    = código de organización (del JSP)
     *   - fechaDesdeCVL      = fecha desde (campo fechaDesdeCVLMasivo)
     *   - fechaHastaCVL      = fecha hasta (campo fechaHastaCVLMasivo)
     *   - fkWSSolicitado     = 1 (tipo de servicio CVL)
     *   - listaDocsMasivo    = lista CSV URL-encoded
     *
     * La respuesta XML se parsea en <RESPUESTA>/<CODIGO_OPERACION>
     * y <RESPUESTA>/<RESULTADO> y se muestra al usuario.
     */
    function ejecutarCvlMasivoDesdeTexto(){
        // Leer la lista de documentos del textarea oculto
        var lista = document.getElementById('listaDocsMasivo').value;
        if(!lista || lista.replace(/\s/g,'').length===0){
            jsp_alerta('A','Debe indicar una lista CSV de NIF/NIE.');
            return;
        }
        // Leer rango de fechas
        var fechaDesde = document.getElementById('fechaDesdeCVLMasivo').value;
        var fechaHasta = document.getElementById('fechaHastaCVLMasivo').value;
        var ajax = getXMLHttpRequest();
        var url = '<%=request.getContextPath()%>/PeticionModuloIntegracion.do';
        // Construir parámetros del POST
        var params = 'tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=ejecutarCvlMasivoDesdeTexto&tipo=0&numero=<%=numExpediente%>&codOrganizacionModulo=<%=codOrganizacion%>'
            + '&fechaDesdeCVL=' + encodeURIComponent(fechaDesde)
            + '&fechaHastaCVL=' + encodeURIComponent(fechaHasta)
            + '&fkWSSolicitado=1'
            + '&listaDocsMasivo=' + encodeURIComponent(lista);
        try{
            ajax.open('POST', url, false); // false = llamada síncrona
            ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded; charset=ISO-8859-1');
            ajax.setRequestHeader('Accept','text/xml, application/xml, text/plain');
            ajax.send(params);
            if (ajax.readyState==4 && ajax.status==200){
                // Parseo de XML compatible IE / resto de navegadores
                var xmlDoc = (navigator.appName.indexOf('Internet Explorer')!=-1) ? new ActiveXObject('Microsoft.XMLDOM') : ajax.responseXML;
                if(navigator.appName.indexOf('Internet Explorer')!=-1){
                    xmlDoc.async='false';
                    xmlDoc.loadXML(ajax.responseText);
                }
                // Extraer nodo <RESPUESTA> y mostrar resultado al usuario
                var nodos = xmlDoc.getElementsByTagName('RESPUESTA');
                if(nodos.length>0){
                    var codigo = nodos[0].getElementsByTagName('CODIGO_OPERACION')[0].childNodes[0].nodeValue;
                    var resultado = nodos[0].getElementsByTagName('RESULTADO')[0].childNodes[0].nodeValue;
                    if(codigo=='0'){
                        jsp_alerta('A', resultado); // Éxito
                    }else{
                        jsp_alerta('A', 'Error: ' + resultado); // Error de negocio
                    }
                }
            }
        }catch(err){
            jsp_alerta('A','Error ejecutando CVL masivo: ' + err.message);
        }
    }

    /**
     * abrirSelectorExcel()
     * ---------------------
     * Punto de entrada del flujo CVL masivo desde Excel.
     * Antes de abrir el selector de ficheros comprueba que el
     * usuario haya rellenado ambas fechas (desde y hasta).
     * Si alguna está vacía muestra un aviso y cancela la acción.
     * Si ambas están presentes, activa el input[type=file] oculto
     * (#ficheroExcelMasivo), que al cambiar disparará
     * cargarDesdeFicheroExcel().
     */
    function abrirSelectorExcel() {
        var fechaDesde = document.getElementById('fechaDesdeCVLMasivo').value;
        var fechaHasta = document.getElementById('fechaHastaCVLMasivo').value;
        // Validar que ambas fechas estén informadas antes de continuar
        if (!fechaDesde || !fechaHasta) {
            jsp_alerta('A', 'Debe indicar la fecha desde y la fecha hasta antes de cargar el Excel.');
            return;
        }
        // Disparar el selector de ficheros nativo del navegador
        document.getElementById('ficheroExcelMasivo').click();
    }

    function cargarDesdeFicheroExcel() {
        var fileInput = document.getElementById('ficheroExcelMasivo');
        if (!fileInput.files || fileInput.files.length === 0) {
            return;
        }
        var fechaDesde = document.getElementById('fechaDesdeCVLMasivo').value;
        var fechaHasta = document.getElementById('fechaHastaCVLMasivo').value;
        if (!fechaDesde || !fechaHasta) {
            jsp_alerta('A', 'Debe indicar la fecha desde y la fecha hasta antes de cargar el Excel.');
            fileInput.value = '';
            return;
        }
        var file = fileInput.files[0];
        var reader = new FileReader();
        reader.onload = function(e) {
            try {
                var data = new Uint8Array(e.target.result);
                var workbook = XLSX.read(data, { type: 'array' });
                var sheet = workbook.Sheets[workbook.SheetNames[0]];
                var rows = XLSX.utils.sheet_to_json(sheet, { header: 1, defval: '' });
                if (!rows || rows.length < 2) {
                    jsp_alerta('A', 'El fichero Excel no contiene datos.');
                    return;
                }
                var getCellValue = function(row, idx, defVal) {
                    return idx >= 0 && row[idx] !== undefined ? String(row[idx]).trim() : defVal;
                };
                var header = rows[0];
                var idxNif = -1, idxTipo = -1;
                for (var h = 0; h < header.length; h++) {
                    var col = String(header[h]).trim().toUpperCase();
                    if (col === 'NIF') idxNif = h;
                    if (col === 'TIPO_DOC') idxTipo = h;
                }
                if (idxNif === -1) {
                    jsp_alerta('A', 'No se encontró la columna NIF en el Excel.');
                    return;
                }
                var lineas = [];
                for (var r = 1; r < rows.length; r++) {
                    var nif = getCellValue(rows[r], idxNif, '');
                    var tipo = getCellValue(rows[r], idxTipo, 'NIF') || 'NIF';
                    if (nif !== '') {
                        lineas.push(nif + ';' + tipo);
                    }
                }
                if (lineas.length === 0) {
                    jsp_alerta('A', 'No se encontraron filas con datos en el Excel.');
                    return;
                }
                document.getElementById('listaDocsMasivo').value = lineas.join('\n');
                ejecutarCvlMasivoDesdeTexto();
            } catch (err) {
                jsp_alerta('A', 'Error al procesar el fichero Excel: ' + err.message);
            }
        };
        reader.readAsArrayBuffer(file);
    }

</script>

<body>
    <div class="tab-page" id="tabPageinteropGen" style="height:520px; width: 100%;">
        <h2 class="tab" id="pestanainteropGen"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"label.interoperabilidad.tituloPestana")%></h2>
        <script type="text/javascript">tp1.addTabPage( document.getElementById( "tabPageinteropGen" ) );</script>
        <div style="clear: both;">
            <div class="contenidoPantalla">
                <div style="width: 100%; padding: 10px; text-align: left;">
                    <div class="sub3titulo" style="clear: both; text-align: center; font-size: 14px;">
                        <span>Servicios Disponibles</span>
                    </div>
                    <br><br>
                    <div class="botonera" style="text-align: center">
                        <logic:equal name="hidenbtnCorrientePagoTGSS" value="1" scope="request">
                            <input type="button" id="btnCorrientePagoTGSS" name="btnCorrientePagoTGSS" class="interopBotonMuylargoBoton" value="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"btn.btnCorrientePagoTGSS")%>" onclick="llamarServicioCorrientePagoTgss()">
                            <br><br>
                        </logic:equal>
                        <logic:equal name="hidenbtnHHFF" value="1" scope="request">
                            <input type="button" id="btnCorrientePagoHHFF" name="btnCorrientePagoHHFF" class="interopBotonMuylargoBoton" value="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"btn.btnCorrientePagoHHFF")%>" onclick="llamarServicioCorrientePagoHHFF()">
                        </logic:equal>
                        <hr>
                        <div style="text-align:left; border:1px solid #cccccc; padding:8px; margin-top:8px;">
                            <label class="legendAzul">CVL masivo desde Excel</label><br><br>
                            <label>Fecha desde</label>
                            <input type="date" id="fechaDesdeCVLMasivo" style="width:150px;"/>
                            &nbsp;
                            <label>Fecha hasta</label>
                            <input type="date" id="fechaHastaCVLMasivo" style="width:150px;"/>
                            <br><br>
                            <input type="file" id="ficheroExcelMasivo" accept=".xlsx,.xls" style="display:none;" onchange="cargarDesdeFicheroExcel()"/>
                            <input type="button" id="btnCargarExcel" class="interopBotonMuylargoBoton" value="Cargar Excel y ejecutar" onclick="abrirSelectorExcel()">
                            <br><br>
                            <textarea id="listaDocsMasivo" rows="4" style="width:98%; display:none;"></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
