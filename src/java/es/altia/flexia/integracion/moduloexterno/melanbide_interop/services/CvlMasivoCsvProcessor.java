package es.altia.flexia.integracion.moduloexterno.melanbide_interop.services;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.InteropCvlMasivoNifVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.InteropCvlMasivoResultadoVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Persona;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.ws.client.vidalaboralws.response.Response;
import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Timestamp;

/**
 * Procesador testeable de CSV CVL masivo, desacoplado de WS/DAO reales.
 */
public class CvlMasivoCsvProcessor {

    private static final String SEPARADOR_CSV = ";";
    private static final String COD_RESPUESTA_INVALID_TIPO_DOC = "INVALID_TIPO_DOC";
    private static final String COD_RESPUESTA_VALIDACION = "VALIDACION";
    private static final String COD_RESPUESTA_ERROR_TECNICO = "ERROR";
    private static final int MAX_INTENTOS_WS = 3;
    private static final long[] ESPERAS_REINTENTO_MS = new long[]{1000L, 3000L, 7000L};

    public interface WsClient {

        Response getVidaLaboral(Persona persona, String fechaDesdeCVL, String fechaHastaCVL,
                int codOrganizacion, String numExpediente, String fkWSSolicitado) throws Exception;
    }

    public interface AuditoriaWriter {

        void insertarRegistro(InteropCvlMasivoNifVO registro) throws Exception;
    }

    public interface VidaLaboralWriter {

        void persistirRespuesta(Response response, Persona persona, String fechaDesdeCVL,
                String fechaHastaCVL, String numExpediente) throws Exception;
    }

    public interface Sleeper {

        void sleep(long millis) throws InterruptedException;
    }

    private final WsClient wsClient;
    private final AuditoriaWriter auditoriaWriter;
    private final VidaLaboralWriter vidaLaboralWriter;
    private final Sleeper sleeper;

    public CvlMasivoCsvProcessor(final WsClient wsClient, final AuditoriaWriter auditoriaWriter,
            final VidaLaboralWriter vidaLaboralWriter, final Sleeper sleeper) {
        this.wsClient = wsClient;
        this.auditoriaWriter = auditoriaWriter;
        this.vidaLaboralWriter = vidaLaboralWriter;
        this.sleeper = sleeper;
    }

    public InteropCvlMasivoResultadoVO procesarCsv(final Reader csvReader,
            final String fechaDesdeCVL, final String fechaHastaCVL,
            final int codOrganizacion, final int codTramite, final int ocurrenciaTramite,
            final String numExpediente, final String fkWSSolicitado, final String usuario) throws Exception {

        final InteropCvlMasivoResultadoVO resumen = new InteropCvlMasivoResultadoVO();
        resumen.setNumExpedienteContexto(numExpediente);

        BufferedReader br = null;
        try {
            br = new BufferedReader(csvReader);
            String linea = null;
            int numLinea = 0;
            while ((linea = br.readLine()) != null) {
                numLinea++;
                if (linea.trim().length() == 0) {
                    continue;
                }
                if (numLinea == 1 && esCabeceraCsv(linea)) {
                    continue;
                }

                resumen.setTotalLeidos(resumen.getTotalLeidos() + 1);

                final String[] columnas = normalizarSeparador(linea).split(SEPARADOR_CSV);
                final String documento = columnas.length > 0 ? normalizarDocumento(columnas[0]) : "";
                final String tipoDoc = columnas.length > 1 ? normalizarTipoDocumento(columnas[1]) : "NIF";

                if (!esDocumentoValido(documento)) {
                    resumen.setTotalErrores(resumen.getTotalErrores() + 1);
                    resumen.addError("Linea " + numLinea + ": NIF vacio o invalido -> " + documento);
                    registrarAuditoriaError(documento, tipoDoc, usuario, codOrganizacion, codTramite,
                            ocurrenciaTramite, numExpediente, fkWSSolicitado, fechaDesdeCVL, fechaHastaCVL,
                            COD_RESPUESTA_VALIDACION, "NIF vacio o invalido en CSV");
                    continue;
                }

                if (esTipoDocumentoBloqueado(tipoDoc)) {
                    resumen.setTotalErrores(resumen.getTotalErrores() + 1);
                    resumen.addError("Linea " + numLinea + ": tipo documento no permitido -> " + tipoDoc);
                    registrarAuditoriaError(documento, tipoDoc, usuario, codOrganizacion, codTramite,
                            ocurrenciaTramite, numExpediente, fkWSSolicitado, fechaDesdeCVL, fechaHastaCVL,
                            COD_RESPUESTA_INVALID_TIPO_DOC, "Tipo documento no permitido: PASAPORTE");
                    continue;
                }

                final Persona persona = new Persona();
                persona.setNumDocumento(documento);
                persona.setTipoDocumento(tipoDoc);

                final ResultadoReintento resultadoWs = invocarWsConReintentos(persona,
                        fechaDesdeCVL, fechaHastaCVL, codOrganizacion, numExpediente, fkWSSolicitado);

                final String codRespuesta = obtenerCodRespuesta(resultadoWs);
                final String descRespuesta = obtenerDescripcionRespuesta(resultadoWs);
                final String payloadResumen = construirPayloadResumen(resultadoWs);

                final InteropCvlMasivoNifVO registro = new InteropCvlMasivoNifVO(
                        null,
                        new Timestamp(System.currentTimeMillis()),
                        documento,
                        tipoDoc,
                        codRespuesta,
                        descRespuesta,
                        payloadResumen,
                        usuario,
                        Integer.valueOf(codOrganizacion),
                        Integer.valueOf(codTramite),
                        Integer.valueOf(ocurrenciaTramite),
                        numExpediente,
                        fkWSSolicitado,
                        fechaDesdeCVL,
                        fechaHastaCVL);

                auditoriaWriter.insertarRegistro(registro);
                resumen.setTotalProcesados(resumen.getTotalProcesados() + 1);

                if ("0000".equals(codRespuesta)) {
                    vidaLaboralWriter.persistirRespuesta(resultadoWs.getResponse(), persona,
                            fechaDesdeCVL, fechaHastaCVL, numExpediente);
                    resumen.setTotalCorrectos(resumen.getTotalCorrectos() + 1);
                } else {
                    resumen.setTotalErrores(resumen.getTotalErrores() + 1);
                    resumen.addError("Linea " + numLinea + ": " + documento + " -> "
                            + codRespuesta + " " + descRespuesta);
                }
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }

        return resumen;
    }

    private ResultadoReintento invocarWsConReintentos(final Persona persona,
            final String fechaDesdeCVL, final String fechaHastaCVL, final int codOrganizacion,
            final String numExpediente, final String fkWSSolicitado) throws Exception {

        Response response = null;
        Exception ultimaExcepcion = null;

        for (int intento = 1; intento <= MAX_INTENTOS_WS; intento++) {
            try {
                response = wsClient.getVidaLaboral(persona, fechaDesdeCVL, fechaHastaCVL,
                        codOrganizacion, numExpediente, fkWSSolicitado);
                if (!esReintentable(response)) {
                    return new ResultadoReintento(response, null, intento);
                }
            } catch (Exception ex) {
                ultimaExcepcion = ex;
            }

            if (intento < MAX_INTENTOS_WS) {
                try {
                    sleeper.sleep(ESPERAS_REINTENTO_MS[intento - 1]);
                } catch (InterruptedException interruptedEx) {
                    Thread.currentThread().interrupt();
                    throw new Exception("Interrumpido esperando reintento WS", interruptedEx);
                }
            }
        }

        return new ResultadoReintento(response, ultimaExcepcion, MAX_INTENTOS_WS);
    }

    private String construirPayloadResumen(final ResultadoReintento resultadoWs) {
        final StringBuilder sb = new StringBuilder();
        if (resultadoWs.getResponse() == null) {
            if (resultadoWs.getUltimaExcepcion() != null) {
                sb.append("ERROR_TECNICO");
            } else {
                sb.append("WS_NULL");
            }
        } else {
            final Response response = resultadoWs.getResponse();
            sb.append(response.getCodigoEstado() != null ? response.getCodigoEstado() : "");
            sb.append("|");
            sb.append(response.getTextoEstado() != null ? response.getTextoEstado() : "");
        }
        sb.append("|INTENTOS=").append(resultadoWs.getIntentos());
        if (sb.length() > 2000) {
            return sb.substring(0, 2000);
        }
        return sb.toString();
    }

    private String obtenerCodRespuesta(final ResultadoReintento resultadoWs) {
        if (resultadoWs.getResponse() != null) {
            return resultadoWs.getResponse().getCodRespuesta();
        }
        return resultadoWs.getUltimaExcepcion() != null ? COD_RESPUESTA_ERROR_TECNICO : "WS_NULL";
    }

    private String obtenerDescripcionRespuesta(final ResultadoReintento resultadoWs) {
        if (resultadoWs.getResponse() != null) {
            final String desc = resultadoWs.getResponse().getDescRespuesta() != null
                    ? resultadoWs.getResponse().getDescRespuesta() : "";
            return desc + " (intentos=" + resultadoWs.getIntentos() + ")";
        }
        if (resultadoWs.getUltimaExcepcion() != null) {
            return resultadoWs.getUltimaExcepcion().getMessage() + " (intentos="
                    + resultadoWs.getIntentos() + ")";
        }
        return "Respuesta nula del WS CVL (intentos=" + resultadoWs.getIntentos() + ")";
    }

    private boolean esReintentable(final Response response) {
        if (response == null) {
            return true;
        }
        final String cod = response.getCodRespuesta();
        return "WS_NULL".equals(cod) || "ERROR".equals(cod);
    }

    private void registrarAuditoriaError(final String documento, final String tipoDoc, final String usuario,
            final int codOrganizacion, final int codTramite, final int ocurrenciaTramite,
            final String numExpediente, final String fkWSSolicitado,
            final String fechaDesdeCVL, final String fechaHastaCVL,
            final String codRespuesta, final String descRespuesta) throws Exception {
        final InteropCvlMasivoNifVO registro = new InteropCvlMasivoNifVO(
                null,
                new Timestamp(System.currentTimeMillis()),
                documento,
                tipoDoc,
                codRespuesta,
                descRespuesta,
                descRespuesta,
                usuario,
                Integer.valueOf(codOrganizacion),
                Integer.valueOf(codTramite),
                Integer.valueOf(ocurrenciaTramite),
                numExpediente,
                fkWSSolicitado,
                fechaDesdeCVL,
                fechaHastaCVL);
        auditoriaWriter.insertarRegistro(registro);
    }

    private boolean esTipoDocumentoBloqueado(final String tipoDoc) {
        return "PAS".equals(tipoDoc) || "PASAPORTE".equals(tipoDoc);
    }

    private String normalizarDocumento(final String documento) {
        if (documento == null) {
            return "";
        }
        return documento.replaceAll("\\s+", "").toUpperCase();
    }

    private String normalizarTipoDocumento(final String tipoDoc) {
        if (tipoDoc == null) {
            return "NIF";
        }
        final String tipo = tipoDoc.trim().toUpperCase();
        return tipo.length() > 0 ? tipo : "NIF";
    }

    private boolean esDocumentoValido(final String documento) {
        return documento != null && documento.trim().length() >= 8;
    }

    private String normalizarSeparador(final String linea) {
        if (linea.indexOf(';') >= 0) {
            return linea;
        }
        return linea.replace(',', ';');
    }

    private boolean esCabeceraCsv(final String linea) {
        final String[] columnas = normalizarSeparador(linea).split(SEPARADOR_CSV);
        if (columnas.length == 0) {
            return false;
        }
        final String c0 = columnas[0] != null ? columnas[0].trim().toUpperCase() : "";
        final String c1 = columnas.length > 1 && columnas[1] != null ? columnas[1].trim().toUpperCase() : "";
        return (esCabeceraDocumento(c0) && esCabeceraTipoDoc(c1))
                || (esCabeceraDocumento(c1) && esCabeceraTipoDoc(c0));
    }

    private boolean esCabeceraDocumento(final String valor) {
        return "DOCUMENTO".equals(valor)
                || "NIF".equals(valor)
                || "NIE".equals(valor);
    }

    private boolean esCabeceraTipoDoc(final String valor) {
        return "TIPO_DOC".equals(valor)
                || "TIPODOC".equals(valor)
                || "TIPO DOCUMENTO".equals(valor)
                || "TIPO".equals(valor);
    }

    static class ResultadoReintento {

        private final Response response;
        private final Exception ultimaExcepcion;
        private final int intentos;

        ResultadoReintento(final Response response, final Exception ultimaExcepcion, final int intentos) {
            this.response = response;
            this.ultimaExcepcion = ultimaExcepcion;
            this.intentos = intentos;
        }

        public Response getResponse() {
            return response;
        }

        public Exception getUltimaExcepcion() {
            return ultimaExcepcion;
        }

        public int getIntentos() {
            return intentos;
        }
    }
}
