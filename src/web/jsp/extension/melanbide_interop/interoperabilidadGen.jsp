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
    String nombreModulo = request.getParameter("nombreModulo");
    String codOrganizacion = request.getParameter("codOrganizacionModulo");
    String numExpediente = request.getParameter("numero");

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
        } else
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
        } else
        {
            alert(texto);
        }
    }

    function llamarServicioCorrientePagoTgss()
    {
        var listaTercerosExp = recogerListaTerceros();
        var ajax = getXMLHttpRequest();
        var nodos = null;
        var CONTEXT_PATH = '<%=request.getContextPath()%>';
        var url = CONTEXT_PATH + "/PeticionModuloIntegracion.do";
        var parametros = "tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=GetConsultaCorrientePagoTGSS&tipo=0&numero=<%=numExpediente%>&listaTercerosExp=" + listaTercerosExp;
        var xmlDoc = null;
        var text = null;
        var elemento = null;
        var hijos = null;
        var codigoOperacion = null;
        var textoRespuestaWS = null;
        var codigoOperacionNumero = null;
        var j = 0;

        try
        {
            ajax.open("POST", url, false);
            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=ISO-8859-1");
            ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");
            ajax.send(parametros);

            if (ajax.readyState == 4 && ajax.status == 200)
            {
                if (navigator.appName.indexOf("Internet Explorer") != -1)
                {
                    text = ajax.responseText;
                    xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                    xmlDoc.async = "false";
                    xmlDoc.loadXML(text);
                } else
                {
                    xmlDoc = ajax.responseXML;
                }
            }

            nodos = xmlDoc.getElementsByTagName("RESPUESTA");
            if (nodos.length > 0)
            {
                elemento = nodos[0];
                hijos = elemento.childNodes;

                for (j = 0; hijos != null && j < hijos.length; j++)
                {
                    if (hijos[j].nodeName == "CODIGO_OPERACION")
                    {
                        codigoOperacion = hijos[j].childNodes[0].nodeValue;
                    } else if (hijos[j].nodeName == "RESULTADO")
                    {
                        textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                    }
                }

                codigoOperacionNumero = parseInt(codigoOperacion, 10);

                if (codigoOperacionNumero == 0)
                {
                    mostrarRespuestaWS(textoRespuestaWS);
                } else if (codigoOperacionNumero == 1 || codigoOperacionNumero > 4)
                {
                    mostrarRespuestaWS(textoRespuestaWS);
                } else if (codigoOperacionNumero == 2)
                {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                } else if (codigoOperacionNumero == 3)
                {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.pasoParametros")%>');
                } else if (codigoOperacionNumero == 4)
                {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.expSinTercero")%>');
                } else
                {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                }
            } else
            {
                jsp_alerta("A", "Error procesando la solicitud. No se ha podido establecer conexion/obtener respuesta del WebService.");
            }
        } catch (Err)
        {
            jsp_alerta("A", "Error procesando la solicitud : " + Err.message);
        }
    }

    function llamarServicioCorrientePagoHHFF()
    {
        var listaTercerosExp = recogerListaTerceros();
        var ajax = getXMLHttpRequest();
        var nodos = null;
        var CONTEXT_PATH = '<%=request.getContextPath()%>';
        var url = CONTEXT_PATH + "/PeticionModuloIntegracion.do";
        var parametros = "tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=GetConsultaCorrientePagoHHFF&tipo=0&numero=<%=numExpediente%>&listaTercerosExp=" + listaTercerosExp;
        var xmlDoc = null;
        var text = null;
        var elemento = null;
        var hijos = null;
        var codigoOperacion = null;
        var textoRespuestaWS = null;
        var j = 0;

        try
        {
            ajax.open("POST", url, false);
            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=ISO-8859-1");
            ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");
            ajax.send(parametros);

            if (ajax.readyState == 4 && ajax.status == 200)
            {
                if (navigator.appName.indexOf("Internet Explorer") != -1)
                {
                    text = ajax.responseText;
                    xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                    xmlDoc.async = "false";
                    xmlDoc.loadXML(text);
                } else
                {
                    xmlDoc = ajax.responseXML;
                }
            }

            nodos = xmlDoc.getElementsByTagName("RESPUESTA");
            if (nodos.length > 0)
            {
                elemento = nodos[0];
                hijos = elemento.childNodes;

                for (j = 0; hijos != null && j < hijos.length; j++)
                {
                    if (hijos[j].nodeName == "CODIGO_OPERACION")
                    {
                        codigoOperacion = hijos[j].childNodes[0].nodeValue;
                    } else if (hijos[j].nodeName == "RESULTADO")
                    {
                        textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                    }
                }

                if (codigoOperacion == "0")
                {
                    mostrarRespuestaWS(textoRespuestaWS);
                } else if (codigoOperacion == "1" || parseInt(codigoOperacion, 10) > 4)
                {
                    mostrarRespuestaWS(textoRespuestaWS);
                } else if (codigoOperacion == "2")
                {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                } else if (codigoOperacion == "3")
                {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.pasoParametros")%>');
                } else if (codigoOperacion == "4")
                {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.expSinTercero")%>');
                } else
                {
                    jsp_alerta("A", '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                }
            } else
            {
                jsp_alerta("A", "Error procesando la solicitud. No se ha podido establecer conexion/obtener respuesta del WebService.");
            }
        } catch (Err)
        {
            jsp_alerta("A", "Error procesando la solicitud : " + Err.message);
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

    /**
     * PRUEBA E2E — PASO 4: Envía el fichero Excel en base64 al servidor y muestra el resultado.
     *
     * Datos de entrada (llamada desde ejecutarCvlMasivoDesdeExcel):
     *   · excelBase64 = "UEsDBBQA..."  (contenido del fichero prueba_cvl_masivo.xlsx en base64)
     *   · document.getElementById("fechaDesdeCVLMasivo").value = "01/01/2023"
     *   · document.getElementById("fechaHastaCVLMasivo").value = "31/12/2023"
     *
     * Conversión de fechas (convertirFechaCalendarioAFormatoWS):
     *   · "01/01/2023" → "2023-01-01"
     *   · "31/12/2023" → "2023-12-31"
     *
     * POST enviado a /PeticionModuloIntegracion.do con los parámetros:
     *   tarea=preparar
     *   &modulo=MELANBIDE_INTEROP
     *   &operacion=ejecutarCvlMasivoDesdeTexto
     *   &tipo=0
     *   &numero=EXP2024/000123
     *   &fechaDesdeCVL=2023-01-01
     *   &fechaHastaCVL=2023-12-31
     *   &fkWSSolicitado=1
     *   &listaDocsMasivo=
     *   &excelBase64=UEsDBBQA...
     *
     * Respuesta XML esperada (éxito):
     *   <RESPUESTA>
     *     <CODIGO_OPERACION>0</CODIGO_OPERACION>
     *     <RESULTADO><![CDATA[Expediente contexto=CVL_MASIVO/2024/000042,
     *       Leidos=3, Procesados=3, Correctos=2, Errores=1 |
     *       Detalle errores: [Linea 3: 87654321B -> ERR01 Persona no encontrada]]]></RESULTADO>
     *   </RESPUESTA>
     *
     * Resultado esperado en pantalla (codigo="0"):
     *   Ventana modal con el texto del RESULTADO anterior.
     *
     * Respuesta XML esperada (error de validación, p.ej. sin fechas):
     *   <RESPUESTA>
     *     <CODIGO_OPERACION>3</CODIGO_OPERACION>
     *     <RESULTADO><![CDATA[Debe indicar una lista de NIF/NIE para procesar.]]></RESULTADO>
     *   </RESPUESTA>
     *
     * @param {string} excelBase64 - Contenido del fichero Excel codificado en base64.
     */
    function enviarPeticionCvlMasivoExcel(excelBase64)
    {
        var fechaDesde = document.getElementById("fechaDesdeCVLMasivo").value;
        var fechaHasta = document.getElementById("fechaHastaCVLMasivo").value;
        var ajax = getXMLHttpRequest();
        var url = '<%=request.getContextPath()%>/PeticionModuloIntegracion.do';
        var params = "";
        var xmlDoc = null;
        var nodos = null;
        var codigo = null;
        var resultado = null;

        fechaDesde = convertirFechaCalendarioAFormatoWS(fechaDesde);
        fechaHasta = convertirFechaCalendarioAFormatoWS(fechaHasta);

        params = "tarea=preparar"
                + "&modulo=MELANBIDE_INTEROP"
                + "&operacion=ejecutarCvlMasivoDesdeTexto"
                + "&tipo=0"
                + "&numero=<%=numExpediente%>"
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
                if (navigator.appName.indexOf("Internet Explorer") != -1)
                {
                    xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                    xmlDoc.async = "false";
                    xmlDoc.loadXML(ajax.responseText);
                } else
                {
                    xmlDoc = ajax.responseXML;
                }

                nodos = xmlDoc.getElementsByTagName("RESPUESTA");

                if (nodos != null && nodos.length > 0)
                {
                    if (nodos[0].getElementsByTagName("CODIGO_OPERACION").length > 0
                            && nodos[0].getElementsByTagName("CODIGO_OPERACION")[0].childNodes.length > 0)
                    {
                        codigo = nodos[0].getElementsByTagName("CODIGO_OPERACION")[0].childNodes[0].nodeValue;
                    }

                    if (nodos[0].getElementsByTagName("RESULTADO").length > 0
                            && nodos[0].getElementsByTagName("RESULTADO")[0].childNodes.length > 0)
                    {
                        resultado = nodos[0].getElementsByTagName("RESULTADO")[0].childNodes[0].nodeValue;
                    }

                    if (codigo == "0")
                    {
                        jsp_alerta("A", resultado);
                    } else
                    {
                        jsp_alerta("A", "Error: " + resultado);
                    }
                } else
                {
                    jsp_alerta("A", "Error procesando la solicitud. No se ha podido establecer conexion/obtener respuesta del WebService.");
                }
            } else
            {
                jsp_alerta("A", "Error procesando la solicitud. No se ha podido establecer conexion/obtener respuesta del WebService.");
            }
        } catch (Err)
        {
            jsp_alerta("A", "Error ejecutando CVL masivo: " + Err.message);
        }
    }

    /**
     * PRUEBA E2E — PASO 3: El usuario hace clic en el botón "Ejecutar CVL masivo".
     *
     * Flujo completo desde la pantalla hasta el servidor:
     *
     *   PASO 1 — El usuario rellena las fechas en el formulario:
     *     · fechaDesdeCVLMasivo  = "01/01/2023"
     *     · fechaHastaCVLMasivo  = "31/12/2023"
     *
     *   PASO 2 — El usuario hace clic en el input type="file" (id="listaDocsMasivoExcel")
     *     y selecciona el fichero:  prueba_cvl_masivo.xlsx
     *     Contenido del Excel (primera hoja):
     *       Columna A   | Columna B
     *       ------------|----------
     *       TIPO_DOC    | DOCUMENTO      ← fila cabecera (se descarta)
     *       NIF         | 12345678A
     *       NIE         | X1234567L
     *       NIF         | 87654321B
     *
     *   PASO 3 — El usuario hace clic en "Ejecutar CVL masivo".
     *     Esta función:
     *       1. Lee inputExcel.files[0] → { name: "prueba_cvl_masivo.xlsx", size: 4096 }
     *       2. Valida la extensión (.xls / .xlsx) → OK
     *       3. Crea un FileReader y llama a readAsDataURL(ficheroExcel)
     *       4. En lector.onload extrae la parte base64:
     *            contenido  = "data:application/vnd.openxmlformats-...;base64,UEsDBBQA..."
     *            excelBase64 = "UEsDBBQA..."
     *       5. Llama a enviarPeticionCvlMasivoExcel("UEsDBBQA...")
     *
     * Resultado esperado: se ejecuta enviarPeticionCvlMasivoExcel con el base64 del Excel.
     */
    function ejecutarCvlMasivoDesdeExcel()
    {
        var inputExcel = document.getElementById("listaDocsMasivoExcel");
        var ficheroExcel = null;
        var nombreFichero = "";
        var lector = null;

        if (inputExcel == null || inputExcel.files == null || inputExcel.files.length == 0)
        {
            jsp_alerta("A", "Debe seleccionar un fichero Excel.");
            return;
        }

        ficheroExcel = inputExcel.files[0];

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

            if (evento != null && evento.target != null)
            {
                contenido = evento.target.result;
            }

            if (contenido == null || contenido.indexOf("data:") != 0 || contenido.indexOf(",") < 0)
            {
                jsp_alerta("A", "No se pudo leer el fichero Excel seleccionado.");
                return;
            }

            excelBase64 = contenido.split(",")[1];

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
