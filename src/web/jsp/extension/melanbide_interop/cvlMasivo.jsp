<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="es.altia.agora.business.escritorio.UsuarioValueObject" %>
<%@page import="es.altia.flexia.integracion.moduloexterno.melanbide_interop.i18n.MeLanbideInteropI18n" %>
<%
    int idiomaUsuario = 1;
    int codOrganizacion = -1;
    int apl = 5;
    String css = "";
    UsuarioValueObject usuario = null;
    try
    {
        if (session != null)
        {
            usuario = (UsuarioValueObject) session.getAttribute("usuario");
            if (usuario != null)
            {
                idiomaUsuario = usuario.getIdioma();
                codOrganizacion = usuario.getOrgCod();
                apl = usuario.getAppCod();
                css = usuario.getCss();
            }
        }
    }
    catch(Exception ex)
    {
    }

    if (codOrganizacion < 0 && request.getParameter("codOrganizacionModulo") != null && request.getParameter("codOrganizacionModulo").trim().length() > 0)
    {
        try
        {
            codOrganizacion = Integer.parseInt(request.getParameter("codOrganizacionModulo"));
        }
        catch (Exception ex)
        {
            codOrganizacion = -1;
        }
    }

    String numExpediente = request.getParameter("numero");
    if (numExpediente == null)
    {
        numExpediente = "";
    }
    MeLanbideInteropI18n meLanbideInteropI18n = MeLanbideInteropI18n.getInstance();
%>

<link rel="StyleSheet" media="screen" type="text/css" href="<%=request.getContextPath()%><%=css%>">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap413/bootstrap.min.css" media="all"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilo.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap413/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/general.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/popup.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/calendario.js"></script>

<script type="text/javascript">
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

    function enviarPeticionCvlMasivoExcel(excelBase64)
    {
        var fechaDesde = convertirFechaCalendarioAFormatoWS(document.getElementById("fechaDesdeCVLMasivo").value);
        var fechaHasta = convertirFechaCalendarioAFormatoWS(document.getElementById("fechaHastaCVLMasivo").value);
        var codOrganizacion = "<%=codOrganizacion%>";
        var ajax = null;
        var url = null;
        var params = "";
        var respuesta = null;

        if (codOrganizacion == null || codOrganizacion.replace(/\s/g, "") == "" || codOrganizacion == "null" || parseInt(codOrganizacion, 10) < 0)
        {
            jsp_alerta("A", "No se ha podido determinar la organización de ejecución.");
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

<div class="contenidoPantalla">
    <div style="padding-left: 15px; text-align: center;" class="txttitblanco">CVL masivo por Excel</div>
    <div class="container align-self-center" style="width: 60%; margin-top: 30px;">
        <div style="text-align:left; border:1px solid #cccccc; padding:12px; margin-top:8px;">
            <label>Fecha desde</label>
            <input type="text"
                   id="fechaDesdeCVLMasivo"
                   name="fechaDesdeCVLMasivo"
                   maxlength="10"
                   size="10"
                   style="width:90px;" />
            <a href="javascript:calClick(event);return false;"
               onclick="mostrarCalFechaDesdeCVLMasivo(event);return false;"
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
                   class="btn btn-primary"
                   value="Ejecutar CVL masivo"
                   onclick="ejecutarCvlMasivoDesdeExcel()">
        </div>
    </div>
</div>
