<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%@page import="es.altia.flexia.integracion.moduloexterno.melanbide_interop.i18n.MeLanbideInteropI18n"%>
<%@page import="es.altia.agora.business.escritorio.UsuarioValueObject" %>
<%@page import="es.altia.common.service.config.Config"%>
<%@page import="es.altia.common.service.config.ConfigServiceHelper"%>
<%@page import="es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.tercero.TerceroVO"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>

<%
    int idiomaUsuario = 1;
    if(request.getParameter("idioma") != null)
    {
        try
        {
            idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
        }
        catch(Exception ex)
        {}
    }
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
    }

    MeLanbideInteropI18n meLanbideInteropI18n = MeLanbideInteropI18n.getInstance();

    Config m_Config = ConfigServiceHelper.getConfig("common");
    String nombreModulo     = request.getParameter("nombreModulo");
    String codOrganizacion  = request.getParameter("codOrganizacionModulo");
    String numExpediente    = request.getParameter("numero");

    TerceroVO _tercero = new TerceroVO();
    List<TerceroVO> _tercerosxExpediente = (List<TerceroVO>)request.getAttribute("listaTerceros");
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilo.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/extension/melanbide_interop/melanbide_interop.css"/>
<script type="text/javascript" src="https://cdn.sheetjs.com/xlsx-0.20.3/package/dist/xlsx.full.min.js" integrity="sha384-OLBgp1GsljhM2TJ+sbHjaiH9txEUvgdDTAzHv2P24donTt6/529l+9Ua0vFImLlb" crossorigin="anonymous"></script>

<script type="text/javascript">
    function configurarPestanas() {}
    function ocultarPestanaEpecialidadesRecursos() {}
    function mostrarPestanaEpecialidadesRecursos() {}

    function recogerListaTerceros(){
        var listaTercerosExp;
        var nooChlidren = 0;
        var uno = document.forms[0];
        if(navigator.appName.indexOf("Internet Explorer")!=-1){
            nooChlidren = uno.children.length;
        }else{
            nooChlidren = uno.childElementCount;
        }
        for(i=0; i<nooChlidren; i++)
        {
            if(uno.children[i].name=="listaCodTercero"){
                listaTercerosExp = uno.children[i].value;
                break;
            }
        }
        return listaTercerosExp;
    }

    function mostrarRespuestaWS(texto){
        if(window.showModalDialog){
            jsp_alerta("A", texto.replace(/\n/g,"<br>"));
        } else {
            alert(texto);
        }
    }

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
                if(navigator.appName.indexOf("Internet Explorer")!=-1){
                    var text = ajax.responseText;
                    xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
                    xmlDoc.async="false";
                    xmlDoc.loadXML(text);
                }else{
                    xmlDoc = ajax.responseXML;
                }
            }
            nodos = xmlDoc.getElementsByTagName("RESPUESTA");
            if(nodos.length>0){
                var elemento = nodos[0];
                var hijos = elemento.childNodes;
                var codigoOperacion = null;
                var textoRespuestaWS = null;
                for(j=0;hijos!=null && j<hijos.length;j++){
                    if(hijos[j].nodeName=="CODIGO_OPERACION"){
                        codigoOperacion = hijos[j].childNodes[0].nodeValue;
                    }
                    else if(hijos[j].nodeName=="RESULTADO"){
                        textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                    }
                }
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
                if(navigator.appName.indexOf("Internet Explorer")!=-1){
                    var text = ajax.responseText;
                    xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
                    xmlDoc.async="false";
                    xmlDoc.loadXML(text);
                }else{
                    xmlDoc = ajax.responseXML;
                }
            }
            nodos = xmlDoc.getElementsByTagName("RESPUESTA");
            if(nodos.length>0){
                var elemento = nodos[0];
                var hijos = elemento.childNodes;
                var codigoOperacion = null;
                var textoRespuestaWS = null;
                for(j=0;hijos!=null && j<hijos.length;j++){
                    if(hijos[j].nodeName=="CODIGO_OPERACION"){
                        codigoOperacion = hijos[j].childNodes[0].nodeValue;
                    }
                    else if(hijos[j].nodeName=="RESULTADO"){
                        textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                    }
                }
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

    function ejecutarCvlMasivoDesdeTexto(){
        var lista = document.getElementById('listaDocsMasivo').value;
        if(!lista || lista.replace(/\s/g,'').length===0){
            jsp_alerta('A','Debe indicar una lista CSV de NIF/NIE.');
            return;
        }
        var fechaDesde = document.getElementById('fechaDesdeCVLMasivo').value;
        var fechaHasta = document.getElementById('fechaHastaCVLMasivo').value;
        var ajax = getXMLHttpRequest();
        var url = '<%=request.getContextPath()%>/PeticionModuloIntegracion.do';
        var params = 'tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=ejecutarCvlMasivoDesdeTexto&tipo=0&numero=<%=numExpediente%>&codOrganizacionModulo=<%=codOrganizacion%>'
            + '&fechaDesdeCVL=' + encodeURIComponent(fechaDesde)
            + '&fechaHastaCVL=' + encodeURIComponent(fechaHasta)
            + '&fkWSSolicitado=1'
            + '&listaDocsMasivo=' + encodeURIComponent(lista);
        try{
            ajax.open('POST', url, false);
            ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded; charset=ISO-8859-1');
            ajax.setRequestHeader('Accept','text/xml, application/xml, text/plain');
            ajax.send(params);
            if (ajax.readyState==4 && ajax.status==200){
                var xmlDoc = (navigator.appName.indexOf('Internet Explorer')!=-1) ? new ActiveXObject('Microsoft.XMLDOM') : ajax.responseXML;
                if(navigator.appName.indexOf('Internet Explorer')!=-1){
                    xmlDoc.async='false';
                    xmlDoc.loadXML(ajax.responseText);
                }
                var nodos = xmlDoc.getElementsByTagName('RESPUESTA');
                if(nodos.length>0){
                    var codigo = nodos[0].getElementsByTagName('CODIGO_OPERACION')[0].childNodes[0].nodeValue;
                    var resultado = nodos[0].getElementsByTagName('RESULTADO')[0].childNodes[0].nodeValue;
                    if(codigo=='0'){
                        jsp_alerta('A', resultado);
                    }else{
                        jsp_alerta('A', 'Error: ' + resultado);
                    }
                }
            }
        }catch(err){
            jsp_alerta('A','Error ejecutando CVL masivo: ' + err.message);
        }
    }

    function cargarDesdeFicheroExcel() {
        var fileInput = document.getElementById('ficheroExcelMasivo');
        if (!fileInput.files || fileInput.files.length === 0) {
            jsp_alerta('A', 'Por favor, seleccione un archivo Excel.');
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
                            <label class="legendAzul">CVL masivo por lista de NIF/NIE (CSV pegado)</label><br>
                            <label>Fecha desde</label>
                            <input type="date" id="fechaDesdeCVLMasivo" style="width:150px;"/>
                            <label>Fecha hasta</label>
                            <input type="date" id="fechaHastaCVLMasivo" style="width:150px;"/>
                            <br><br>
                            <textarea id="listaDocsMasivo" rows="6" style="width:98%;" placeholder="NIF;TIPO_DOC&#10;12345678Z;NIF"></textarea>
                            <br>
                            <label>O cargar desde Excel (.xlsx / .xls):</label>
                            <input type="file" id="ficheroExcelMasivo" accept=".xlsx,.xls" onchange="cargarDesdeFicheroExcel()"/>
                            <br><br>
                            <input type="button" id="btnCvlMasivoTexto" class="interopBotonMuylargoBoton" value="Ejecutar CVL masivo" onclick="ejecutarCvlMasivoDesdeTexto()">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
