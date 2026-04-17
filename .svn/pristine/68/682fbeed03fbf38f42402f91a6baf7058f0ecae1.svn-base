/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.ws.client.vidalaboralws.clientws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConfigurationParameter;


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.MeLanbideInteropGeneralUtils;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.DatosEspecificos;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Persona;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Tramitador;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.ws.client.vidalaboralws.request.Request;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.ws.client.vidalaboralws.response.Response;

/**
 *
 * @author pablo.bugia
 */
public class ClientWSVidaLaboral {

    private static final Logger log = Logger.getLogger(ClientWSVidaLaboral.class);

    public static Response getVidaLaboral(final Persona persona, final String fechaDesde,
            final String fechaHasta, int codOrganizacion, final String numExpediente,
            final String fkWSSolicitado) {
        try {

            final String nombreModulo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_MODULO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            final String urlRestServiceStr = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.URL_REST_SERVICE_CVL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.info("Url del Rest Service : " + urlRestServiceStr);

            final URL url = new URL(urlRestServiceStr);

            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            // 
            conn.setDoOutput(true);

            final String codigoProcFlexia = MeLanbideInteropGeneralUtils.getCodProcedimientoFromNumExpediente(numExpediente);
            final String cifUsuarioLogueado = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.RESPONSABLE_SERVICIO_NIF + numExpediente.split("/")[1] + ConstantesMeLanbideInterop.BARRA_BAJA + fkWSSolicitado, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            final String nombreUsuarioLogueado = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.RESPONSABLE_SERVICIO_NOMBRE + numExpediente.split("/")[1] + ConstantesMeLanbideInterop.BARRA_BAJA + fkWSSolicitado, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);

            // Crear el cuerpo de la petición
            Request req = new Request(new DatosEspecificos(fechaDesde, fechaHasta),
                    persona,
                    new Tramitador(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + "CVL" + ConstantesMeLanbideInterop.BARRA_BAJA + codigoProcFlexia, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES),
                            ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_PROCEDIMIENTO + codigoProcFlexia, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES),
                            ConfigurationParameter.getParameter("FINALIDAD_PROCEDIMIENTO_CVL_" + codigoProcFlexia, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES),
                            ConfigurationParameter.getParameter("CONSENTIMIENTO_FIRMADO_CVL_" + codigoProcFlexia, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES),
                            cifUsuarioLogueado,
                            nombreUsuarioLogueado));

            final Gson gson = new Gson();
            String jsonString = gson.toJson(req);
            log.debug("ClientWSVidaLaboral getVidaLaboral json a enviar: " + jsonString);

            final OutputStream os = conn.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);

            // Leer la respuesta
            final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            final StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
//            log.info("ClientWSVidaLaboral getVidaLaboral respuesta en json: " + response.toString());
            final Response respuesta = new Gson().fromJson(response.toString(), Response.class);
//            log.info("ClientWSVidaLaboral getVidaLaboral respuesta en objeto: " + respuesta);
            return respuesta;
        } catch (MalformedURLException ex) {
            log.error("ClientWSVidaLaboral getVidaLaboral MalformedURLException: " + ex.getMessage());
            return null;
        } catch (IOException ex) {
            log.error("ClientWSVidaLaboral getVidaLaboral IOException: " + ex.getMessage());
            return null;
        } catch (RuntimeException ex) {
            log.error("ClientWSVidaLaboral getVidaLaboral RuntimeException: " + ex.getMessage());
            return null;
        } catch (Exception ex) {
            log.error("ClientWSVidaLaboral getVidaLaboral Exception: " + ex.getMessage());
            return null;
        }
    }
}
