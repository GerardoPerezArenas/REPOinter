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

                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
