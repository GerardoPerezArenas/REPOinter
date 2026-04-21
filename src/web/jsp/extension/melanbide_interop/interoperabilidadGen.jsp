<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%@page import="es.altia.flexia.integracion.moduloexterno.melanbide_interop.i18n.MeLanbideInteropI18n"%>
<%@page import="es.altia.agora.business.escritorio.UsuarioValueObject" %>
<%@page import="es.altia.common.service.config.Config"%>
<%@page import="es.altia.common.service.config.ConfigServiceHelper"%>
<%@page import="es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.tercero.TerceroVO"%>
<%@page import="java.util.List" %>

<%
    int idiomaUsuario = 1;

    if (request.getParameter("idioma") != null)
    {
        try
        {
            idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
        }
        catch (Exception ex)
        {
        }
    }

    UsuarioValueObject usuario = null;
    try
    {
        if (session != null)
        {
            usuario = (UsuarioValueObject) session.getAttribute("usuario");
            if (usuario != null)
            {
                idiomaUsuario = usuario.getIdioma();
            }
        }
    }
    catch (Exception ex)
    {
    }

    MeLanbideInteropI18n meLanbideInteropI18n = MeLanbideInteropI18n.getInstance();

    Config m_Config = ConfigServiceHelper.getConfig("common");
    String nombreModulo     = request.getParameter("nombreModulo");
    String codOrganizacion  = request.getParameter("codOrganizacionModulo");
    String numExpediente    = request.getParameter("numero");

    TerceroVO _tercero = new TerceroVO();
    List<TerceroVO> _tercerosxExpediente = (List<TerceroVO>) request.getAttribute("listaTerceros");
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilo.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/extension/melanbide_interop/melanbide_interop.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/calendario.js"></script>

<script type="text/javascript">
    function configurarPestanas() {}
    function ocultarPestanaEpecialidadesRecursos() {}
    function mostrarPestanaEpecialidadesRecursos() {}

    function recogerListaTerceros()
    {
        var listaTercerosExp = "";
        var nooChlidren = 0;
        var uno = document.forms[0];
        var i = 0;

        if (navigator.appName.indexOf("Internet Explorer") != -1)
        {
            nooChlidren = uno.children.length;
        }
        else
        {
            nooChlidren = uno.childElementCount;
        }

        for (i = 0; i < nooChlidren; i++)
        {
            if (uno.children[i].name == "listaCodTercero")
            {
                listaTercerosExp = uno.children[i].value;
                break;
            }
        }

        return listaTercerosExp;
    }

    function mostrarRespuestaWS(texto)
    {
        if (window.showModalDialog)
        {
            jsp_alerta("A", texto.replace(/\n/g, "<br>"));
        }
        else
        {
            alert(texto);
        }
    }

   function llamarServicioCorrientePagoTgss(){
        var listaTercerosExp = recogerListaTerceros();
        var ajax = getXMLHttpRequest();
        var nodos = null;
        var CONTEXT_PATH = '<%=request.getContextPath()%>';
        var url = CONTEXT_PATH + "/PeticionModuloIntegracion.do";
        var parametros = "tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=GetConsultaCorrientePagoTGSS&tipo=0&numero=<%=numExpediente%>&listaTercerosExp=" + listaTercerosExp;
        try {
            ajax.open("POST", url, false);
            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=ISO-8859-1");
            ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");
            ajax.send(parametros);
            if (ajax.readyState == 4 && ajax.status == 200) {
                var xmlDoc = null;
                if (navigator.appName.indexOf("Internet Explorer") != -1) {
                    var text = ajax.responseText;
                    xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                    xmlDoc.async = "false";
                    xmlDoc.loadXML(text);
                } else {
                    xmlDoc = ajax.responseXML;
                }
            }
            nodos = xmlDoc.getElementsByTagName("RESPUESTA");
            if (nodos.length > 0) {
                var elemento = nodos[0];
                var hijos = elemento.childNodes;
                var codigoOperacion = null;
                var textoRespuestaWS = null;
                for (j = 0; hijos != null && j < hijos.length; j++) {
                    if (hijos[j].nodeName == "CODIGO_OPERACION") {
                        codigoOperacion = hijos[j].childNodes[0].nodeValue;
                    } else if (hijos[j].nodeName == "RESULTADO") {
                        textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                    }
                }
                if (codigoOperacion == "0") {
                    mostrarRespuestaWS(textoRespuestaWS);
                } else if (codigoOperacion == "1" || "4" < codigoOperacion) {
                    mostrarRespuestaWS(textoRespuestaWS);
                } else if (codigoOperacion == "2") {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                } else if (codigoOperacion == "3") {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.pasoParametros")%>');
                } else if (codigoOperacion == "4") {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.expSinTercero")%>');
                } else {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                }
            } else {
                jsp_alerta('A', "Error procesando la solicitud. No se ha podido establecer conexion/obtener respuesta del WebService.");
            }
        } catch (Err) {
            jsp_alerta('A', "Error procesando la solicitud : " + Err.message);
        }
    }

    function llamarServicioCorrientePagoHHFF() {
        var listaTercerosExp = recogerListaTerceros();
        var ajax = getXMLHttpRequest();
        var nodos = null;
        var CONTEXT_PATH = '<%=request.getContextPath()%>';
        var url = CONTEXT_PATH + "/PeticionModuloIntegracion.do";
        var parametros = "tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=GetConsultaCorrientePagoHHFF&tipo=0&numero=<%=numExpediente%>&listaTercerosExp=" + listaTercerosExp;
        try {
            ajax.open("POST", url, false);
            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=ISO-8859-1");
            ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");
            ajax.send(parametros);
            if (ajax.readyState == 4 && ajax.status == 200) {
                var xmlDoc = null;
                if (navigator.appName.indexOf("Internet Explorer") != -1) {
                    var text = ajax.responseText;
                    xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                    xmlDoc.async = "false";
                    xmlDoc.loadXML(text);
                } else {
                    xmlDoc = ajax.responseXML;
                }
            }
            nodos = xmlDoc.getElementsByTagName("RESPUESTA");
            if (nodos.length > 0) {
                var elemento = nodos[0];
                var hijos = elemento.childNodes;
                var codigoOperacion = null;
                var textoRespuestaWS = null;
                for (j = 0; hijos != null && j < hijos.length; j++) {
                    if (hijos[j].nodeName == "CODIGO_OPERACION") {
                        codigoOperacion = hijos[j].childNodes[0].nodeValue;
                    } else if (hijos[j].nodeName == "RESULTADO") {
                        textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                    }
                }
                if (codigoOperacion == "0") {
                    mostrarRespuestaWS(textoRespuestaWS);
                } else if (codigoOperacion == "1" || "4" < codigoOperacion) {
                    mostrarRespuestaWS(textoRespuestaWS);
                } else if (codigoOperacion == "2") {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                } else if (codigoOperacion == "3") {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.pasoParametros")%>');
                } else if (codigoOperacion == "4") {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.expSinTercero")%>');
                } else {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                }
            } else {
                jsp_alerta('A', "Error procesando la solicitud. No se ha podido establecer conexion/obtener respuesta del WebService.");
            }
        } catch (Err) {
            jsp_alerta('A', "Error procesando la solicitud : " + Err.message);
        }
    }

    function convertirFechaCalendarioAFormatoWS(fecha)
    {
        var partes = null;

        if (fecha == null || fecha.replace(/\s/g, "").length == 0)
        {
            return "";
        }

        if (fecha.indexOf("/") != -1)
        {
            partes = fecha.split("/");
            if (partes.length == 3)
            {
                return partes[2] + "-" + partes[1] + "-" + partes[0];
            }
        }

        return fecha;
    }

    function gestionarRespuestaCvlMasivo(respuesta)
    {
        var xmlDoc = null;
        var nodos = null;
        var elemento = null;
        var hijos = null;
        var codigoOperacion = null;
        var textoRespuestaWS = null;
        var codigoOperacionNumero = null;
        var j = 0;

        try
        {
            if (respuesta == null || respuesta.replace(/\s/g, "").length == 0)
            {
                jsp_alerta("A", "La respuesta del proceso CVL masivo ha llegado vacía.");
                return;
            }

            if (navigator.appName.indexOf("Internet Explorer") != -1)
            {
                xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                xmlDoc.async = "false";
                xmlDoc.loadXML(respuesta);
            } else
            {
                if (window.DOMParser)
                {
                    xmlDoc = (new DOMParser()).parseFromString(respuesta, "text/xml");
                }
            }

            if (xmlDoc == null)
            {
                jsp_alerta("A", "No se pudo interpretar la respuesta del proceso CVL masivo.\n\nRespuesta recibida:\n" + respuesta);
                return;
            }

            nodos = xmlDoc.getElementsByTagName("RESPUESTA");

            if (nodos == null || nodos.length == 0)
            {
                jsp_alerta("A", "No se ha recibido una respuesta XML válida del proceso CVL masivo.\n\nRespuesta recibida:\n" + respuesta);
                return;
            }

            elemento = nodos[0];
            hijos = elemento.childNodes;

            for (j = 0; hijos != null && j < hijos.length; j++)
            {
                if (hijos[j].nodeName == "CODIGO_OPERACION" && hijos[j].childNodes != null && hijos[j].childNodes.length > 0)
                {
                    codigoOperacion = hijos[j].childNodes[0].nodeValue;
                } else if (hijos[j].nodeName == "RESULTADO" && hijos[j].childNodes != null && hijos[j].childNodes.length > 0)
                {
                    textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                }
            }

            codigoOperacionNumero = parseInt(codigoOperacion, 10);

            if (codigoOperacionNumero == 0)
            {
                mostrarRespuestaWS(textoRespuestaWS);
            } else if (textoRespuestaWS != null && textoRespuestaWS != "")
            {
                mostrarRespuestaWS(textoRespuestaWS);
            } else
            {
                jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
            }
        } catch (Err)
        {
            jsp_alerta("A", "Error procesando la respuesta CVL masiva: " + Err.message + "\n\nRespuesta recibida:\n" + respuesta);
        }
    }

    /**
     * Envía el fichero Excel en base64 al servidor y muestra el resultado.
     */
    function enviarPeticionCvlMasivoExcel(excelBase64)
    {
        var fechaDesde = convertirFechaCalendarioAFormatoWS(document.getElementById("fechaDesdeCVLMasivo").value);
        var fechaHasta = convertirFechaCalendarioAFormatoWS(document.getElementById("fechaHastaCVLMasivo").value);
        var codOrganizacion = "<%=codOrganizacion != null ? codOrganizacion : ""%>";
        var ajax = null;
        var url = null;
        var params = "";
        var respuesta = null;

        if (codOrganizacion == null || codOrganizacion.replace(/\s/g, "") == "" || codOrganizacion == "null")
        {
            jsp_alerta("A", "No se ha podido determinar la organización. Cierre la pantalla y vuelva a abrir el módulo desde el expediente.");
            return;
        }

        if (excelBase64 == null || excelBase64.replace(/\s/g, "").length == 0)
        {
            jsp_alerta("A", "No se ha podido obtener el contenido del fichero Excel.");
            return;
        }

        ajax = getXMLHttpRequest();
        url = "<%=request.getContextPath()%>/PeticionModuloIntegracion.do";

        params = "tarea=preparar"
                + "&modulo=MELANBIDE_INTEROP"
                + "&operacion=ejecutarCvlMasivoDesdeTexto"
                + "&tipo=0"
                + "&numero=<%=numExpediente%>"
                + "&codOrganizacionModulo=" + encodeURIComponent(codOrganizacion)
                + "&fechaDesdeCVL=" + encodeURIComponent(fechaDesde)
                + "&fechaHastaCVL=" + encodeURIComponent(fechaHasta)
                + "&fkWSSolicitado=1"
                + "&listaDocsMasivo="
                + "&excelBase64=" + encodeURIComponent(excelBase64);

        try
        {
            ajax.open("POST", url, false);
            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=ISO-8859-1");
            ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");
            ajax.send(params);

            if (ajax.readyState == 4 && ajax.status == 200)
            {
                respuesta = ajax.responseText;

                if (respuesta != null)
                {
                    alert("Respuesta servidor CVL masivo:\n\n" + respuesta);
                }

                gestionarRespuestaCvlMasivo(respuesta);
            } else
            {
                jsp_alerta("A", "Error al ejecutar CVL masivo. Estado HTTP: " + ajax.status);
            }
        } catch (Err)
        {
            jsp_alerta("A", "Error al ejecutar CVL masivo: " + Err.message);
        }
    }

    /**
     * Lee el fichero Excel y lanza el proceso CVL masivo.
     */
    function ejecutarCvlMasivoDesdeExcel()
    {
        var inputExcel = document.getElementById("listaDocsMasivoExcel");
        var ficheroExcel = null;
        var nombreFichero = "";
        var lector = null;

        if (typeof FileReader == "undefined")
        {
            jsp_alerta("A", "El navegador no soporta la carga de ficheros desde esta pantalla.");
            return;
        }

        if (inputExcel == null)
        {
            jsp_alerta("A", "No se ha encontrado el campo de selección de fichero.");
            return;
        }

        if (inputExcel.files == null || inputExcel.files.length == 0)
        {
            jsp_alerta("A", "Debe seleccionar un fichero Excel.");
            return;
        }

        ficheroExcel = inputExcel.files[0];

        if (ficheroExcel == null)
        {
            jsp_alerta("A", "No se ha podido obtener el fichero seleccionado.");
            return;
        }

        if (ficheroExcel.name != null)
        {
            nombreFichero = ficheroExcel.name.toLowerCase();
        }

        if (!(/\.(xls|xlsx)$/i).test(nombreFichero))
        {
            jsp_alerta("A", "Formato no válido. Debe seleccionar un fichero .xls o .xlsx.");
            return;
        }

        lector = new FileReader();

        lector.onload = function (evento)
        {
            var contenido = null;
            var excelBase64 = null;
            var separador = -1;

            if (evento != null && evento.target != null)
            {
                contenido = evento.target.result;
            }

            if (contenido == null)
            {
                jsp_alerta("A", "No se pudo leer el fichero Excel seleccionado.");
                return;
            }

            separador = contenido.indexOf(",");

            if (separador < 0)
            {
                jsp_alerta("A", "El formato leído del fichero Excel no es válido.");
                return;
            }

            excelBase64 = contenido.substring(separador + 1);

            if (excelBase64 == null || excelBase64.replace(/\s/g, "").length == 0)
            {
                jsp_alerta("A", "El fichero Excel seleccionado no contiene datos válidos.");
                return;
            }

            enviarPeticionCvlMasivoExcel(excelBase64);
        };

        lector.onerror = function ()
        {
            jsp_alerta("A", "Error leyendo el fichero Excel.");
        };

        lector.readAsDataURL(ficheroExcel);
    }

    function mostrarCalFechaDesdeCVLMasivo(evento)
    {
        if (window.event)
        {
            evento = window.event;
        }

        if (document.getElementById("calfechaDesdeCVLMasivo").src.indexOf("icono.gif") != -1)
        {
            showCalendar(
                    'forms[0]',
                    'fechaDesdeCVLMasivo',
                    null, null, null, '',
                    'calfechaDesdeCVLMasivo',
                    '',
                    null, null, null, null, null, null, null, null,
                    evento
                    );
        }
    }

    function mostrarCalFechaHastaCVLMasivo(evento)
    {
        if (window.event)
        {
            evento = window.event;
        }

        if (document.getElementById("calfechaHastaCVLMasivo").src.indexOf("icono.gif") != -1)
        {
            showCalendar(
                    'forms[0]',
                    'fechaHastaCVLMasivo',
                    null, null, null, '',
                    'calfechaHastaCVLMasivo',
                    '',
                    null, null, null, null, null, null, null, null,
                    evento
                    );
        }
    }
</script>

<script type="text/javascript">
    console.log("numExpediente=<%=numExpediente%>");
    console.log("codOrganizacion=<%=codOrganizacion%>");
</script>

<body>
    <div class="tab-page" id="tabPageinteropGen" style="height:520px; width: 100%;">
        <h2 class="tab" id="pestanainteropGen"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"label.interoperabilidad.tituloPestana")%></h2>
        <script type="text/javascript">
            tp1.addTabPage(document.getElementById("tabPageinteropGen"));
        </script>
        <div style="clear: both;">
            <div class="contenidoPantalla">
                <div style="width: 100%; padding: 10px; text-align: left;">
                    <div class="sub3titulo" style="clear: both; text-align: center; font-size: 14px;">
                        <span>Servicios Disponibles</span>
                    </div>
                    <br><br>

                    <div class="botonera" style="text-align: center">
                        <logic:equal name="hidenbtnCorrientePagoTGSS" value="1" scope="request">
                            <input type="button"
                                   id="btnCorrientePagoTGSS"
                                   name="btnCorrientePagoTGSS"
                                   class="interopBotonMuylargoBoton"
                                   value="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"btn.btnCorrientePagoTGSS")%>"
                                   onclick="llamarServicioCorrientePagoTgss()">
                            <br><br>
                        </logic:equal>

                        <logic:equal name="hidenbtnHHFF" value="1" scope="request">
                            <input type="button"
                                   id="btnCorrientePagoHHFF"
                                   name="btnCorrientePagoHHFF"
                                   class="interopBotonMuylargoBoton"
                                   value="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"btn.btnCorrientePagoHHFF")%>"
                                   onclick="llamarServicioCorrientePagoHHFF()">
                        </logic:equal>

                        <hr>

                        <div style="text-align:left; border:1px solid #cccccc; padding:8px; margin-top:8px;">
                            <label class="legendAzul">CVL masivo por Excel</label>
                            <br>

                            <label>Fecha desde</label>

                            <input type="text"
                                   id="fechaDesdeCVLMasivo"
                                   name="fechaDesdeCVLMasivo"
                                   maxlength="10"
                                   size="10"
                                   style="width:90px;" />

                            <a href="javascript:calClick(event);return false;"
                               onclick="mostrarCalFechaDesdeCVLMasivo(event);
                                       return false;"
                               style="text-decoration:none;">
                                <img style="border:0px solid"
                                     height="17"
                                     id="calfechaDesdeCVLMasivo"
                                     name="calfechaDesdeCVLMasivo"
                                     border="0"
                                     src="<c:url value='/images/calendario/icono.gif'/>">
                            </a>

                            &nbsp;&nbsp;

                            <label>Fecha hasta</label>

                            <input type="text"
                                   id="fechaHastaCVLMasivo"
                                   name="fechaHastaCVLMasivo"
                                   maxlength="10"
                                   size="10"
                                   style="width:90px;" />

                            <a href="javascript:calClick(event);return false;"
                               onclick="mostrarCalFechaHastaCVLMasivo(event);return false;"
                               style="text-decoration:none;">
                                <img style="border:0px solid"
                                     height="17"
                                     id="calfechaHastaCVLMasivo"
                                     name="calfechaHastaCVLMasivo"
                                     border="0"
                                     src="<c:url value='/images/calendario/icono.gif'/>">
                            </a>

                            <br><br>

                            <input type="file"
                                   id="listaDocsMasivoExcel"
                                   name="listaDocsMasivoExcel"
                                   accept=".xls,.xlsx"
                                   style="width:98%;" />

                            <br>

                            <span style="font-size:11px;color:#666;">
                                Primera hoja del Excel. Columnas permitidas: TIPO_DOC + DOCUMENTO o DOCUMENTO + TIPO_DOC.
                                Ejemplo: DNI | 11111111H
                            </span>

                            <br><br>

                            <input type="button"
                                   id="btnCvlMasivoExcel"
                                   name="btnCvlMasivoExcel"
                                   class="interopBotonMuylargoBoton"
                                   value="Ejecutar CVL masivo"
                                   onclick="ejecutarCvlMasivoDesdeExcel()">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
