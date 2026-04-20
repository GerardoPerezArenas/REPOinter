package es.altia.flexia.integracion.moduloexterno.melanbide_interop.services;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.dao.InteropCvlMasivoNifDAO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.dao.MeLanbideInteropVidaLaboralDAO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConfigurationParameter;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.MeLanbideInteropMappingUtils;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.InteropCvlMasivoNifVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.InteropCvlMasivoResultadoVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.RegistroVidaLaboralVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Persona;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.ws.client.vidalaboralws.clientws.ClientWSVidaLaboral;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.ws.client.vidalaboralws.response.Response;
import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import org.apache.log4j.Logger;
/**
 * Servicio batch para procesar un CSV exportado de Excel con NIFs.
 *
 * <h2>PRUEBA E2E — PASO 7 (Servidor): Núcleo del proceso CVL masivo.</h2>
 * <p>
 * Este servicio recibe el CSV generado por {@code convertirExcelBase64AListaDocs},
 * itera línea a línea y llama al WebService de Vida Laboral de Lanbide por cada documento.
 * </p>
 *
 * <h3>Ejemplo de datos de entrada completo (caso de prueba UAT):</h3>
 * <pre>
 *   csvReader          = StringReader("12345678A;NIF\nX1234567L;NIE\n87654321B;NIF")
 *   fechaDesdeCVL      = "2023-01-01"
 *   fechaHastaCVL      = "2023-12-31"
 *   codOrganizacion    = 15
 *   codTramite         = 120
 *   ocurrenciaTramite  = 1
 *   numExpediente      = "EXP2024/000123"  (si está vacío se genera uno técnico)
 *   fkWSSolicitado     = "1"
 *   usuario            = "TRAMITADOR01"
 * </pre>
 *
 * <h3>Procesamiento línea a línea con datos de ejemplo:</h3>
 * <table border="1">
 *   <tr><th>Línea</th><th>NIF</th><th>Tipo</th><th>Envío al WS</th>
 *       <th>cod_respuesta WS</th><th>Resultado</th></tr>
 *   <tr><td>1</td><td>12345678A</td><td>NIF</td>
 *       <td>getVidaLaboral("12345678A","NIF","2023-01-01","2023-12-31",15,"EXP2024/000123","1")</td>
 *       <td>0000</td><td>OK → persiste en INTEROP_VIDALABORAL</td></tr>
 *   <tr><td>2</td><td>X1234567L</td><td>NIE</td>
 *       <td>getVidaLaboral("X1234567L","NIE","2023-01-01","2023-12-31",15,"EXP2024/000123","1")</td>
 *       <td>0000</td><td>OK → persiste en INTEROP_VIDALABORAL</td></tr>
 *   <tr><td>3</td><td>87654321B</td><td>NIF</td>
 *       <td>getVidaLaboral("87654321B","NIF","2023-01-01","2023-12-31",15,"EXP2024/000123","1")</td>
 *       <td>ERR01</td><td>ERROR → registra en auditoría → suma totalErrores</td></tr>
 * </table>
 *
 * <h3>Registro de auditoría en BD (tabla INTEROP_CVL_MASIVO_NIF) por cada NIF:</h3>
 * <pre>
 *   Fila 1: timestamp=NOW, nif="12345678A", tipoDoc="NIF", codRespuesta="0000",
 *           descRespuesta="OK", usuario="TRAMITADOR01", codOrg=15,
 *           numExpediente="EXP2024/000123", fechaDesde="2023-01-01", fechaHasta="2023-12-31"
 *   Fila 2: timestamp=NOW, nif="X1234567L",  tipoDoc="NIE", codRespuesta="0000", ...
 *   Fila 3: timestamp=NOW, nif="87654321B",  tipoDoc="NIF", codRespuesta="ERR01",
 *           descRespuesta="Persona no encontrada", ...
 * </pre>
 *
 * <h3>Objeto {@code InteropCvlMasivoResultadoVO} retornado:</h3>
 * <pre>
 *   numExpedienteContexto = "EXP2024/000123"
 *   totalLeidos           = 3
 *   totalProcesados       = 3
 *   totalCorrectos        = 2
 *   totalErrores          = 1
 *   errores               = ["Linea 3: 87654321B -> ERR01 Persona no encontrada"]
 * </pre>
 */
public class InteropCvlMasivoCsvService {

    private static final Logger log = Logger.getLogger(InteropCvlMasivoCsvService.class);
    private static final String SEPARADOR_CSV = ";";
    private static final String PREFIJO_EXP_TECNICO = "CVL_MASIVO";

    /**
     * PRUEBA E2E — Método principal del servicio CVL masivo.
     *
     * <p>Lee el CSV línea a línea (formato "DOCUMENTO;TIPO_DOC"),
     * invoca {@code ClientWSVidaLaboral.getVidaLaboral} por cada NIF/NIE y
     * persiste los resultados en las tablas de auditoría e interoperabilidad.</p>
     *
     * <h3>Caso de prueba con los datos de ejemplo del fichero prueba_cvl_masivo.xlsx:</h3>
     * <pre>
     *   Entrada CSV:
     *     "12345678A;NIF\nX1234567L;NIE\n87654321B;NIF"
     *
     *   Procesamiento:
     *     numLinea=1 → nif="12345678A", tipoDoc="NIF" → esDocumentoValido=true
     *       → WS: getVidaLaboral(p{numDoc="12345678A",tipoDoc="NIF"},
     *                            "2023-01-01","2023-12-31",15,"EXP2024/000123","1")
     *       → response.getCodRespuesta()="0000" → totalCorrectos++
     *       → persistirEnInteropVidaLaboral(response, p, ...)
     *       → InteropCvlMasivoNifDAO.insertarRegistro(registro, con)
     *
     *     numLinea=2 → nif="X1234567L", tipoDoc="NIE" → esDocumentoValido=true
     *       → WS: getVidaLaboral(p{numDoc="X1234567L",tipoDoc="NIE"}, ...)
     *       → response.getCodRespuesta()="0000" → totalCorrectos++
     *
     *     numLinea=3 → nif="87654321B", tipoDoc="NIF" → esDocumentoValido=true
     *       → WS: getVidaLaboral(p{numDoc="87654321B",tipoDoc="NIF"}, ...)
     *       → response.getCodRespuesta()="ERR01" → totalErrores++
     *       → errores.add("Linea 3: 87654321B -> ERR01 Persona no encontrada")
     *
     *   Resultado retornado (InteropCvlMasivoResultadoVO):
     *     numExpedienteContexto = "EXP2024/000123"
     *     totalLeidos           = 3
     *     totalProcesados       = 3
     *     totalCorrectos        = 2
     *     totalErrores          = 1
     *     errores               = ["Linea 3: 87654321B -> ERR01 Persona no encontrada"]
     * </pre>
     *
     * @param csvReader          Reader del CSV "DOCUMENTO;TIPO_DOC" (una entrada por línea).
     * @param fechaDesdeCVL      Fecha inicio del período CVL en formato "YYYY-MM-DD" (p.ej. "2023-01-01").
     * @param fechaHastaCVL      Fecha fin del período CVL en formato "YYYY-MM-DD" (p.ej. "2023-12-31").
     * @param codOrganizacion    Código de organización (p.ej. 15).
     * @param codTramite         Código de trámite (p.ej. 120).
     * @param ocurrenciaTramite  Ocurrencia del trámite (p.ej. 1).
     * @param numExpediente      Número de expediente de contexto; si está vacío se genera uno técnico
     *                           con el formato "CVL_MASIVO/YYYY/NNNNNN".
     * @param fkWSSolicitado     Clave del WS solicitante (normalmente "1").
     * @param usuario            Identificador del usuario tramitador (p.ej. "TRAMITADOR01").
     * @param con                Conexión activa a la base de datos de la organización.
     * @return {@code InteropCvlMasivoResultadoVO} con los contadores y el detalle de errores.
     * @throws Exception Si se produce un error irrecuperable al leer el CSV.
     */
    public InteropCvlMasivoResultadoVO procesarCsv(final Reader csvReader,
        final String fechaDesdeCVL, final String fechaHastaCVL,
        final int codOrganizacion, final int codTramite, final int ocurrenciaTramite,
        final String numExpediente,
        final String fkWSSolicitado, final String usuario,
        final Connection con) throws Exception {

    final InteropCvlMasivoResultadoVO resumen = new InteropCvlMasivoResultadoVO();

    final String numExpedienteTrabajo = (numExpediente != null && numExpediente.trim().length() > 0)
            ? numExpediente.trim() : generarNumExpedienteTecnico(con);

    resumen.setNumExpedienteContexto(numExpedienteTrabajo);

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

            if (numLinea == 1 && linea.toUpperCase().indexOf("NIF") >= 0) {
                // Cabecera CSV
                continue;
            }

            resumen.setTotalLeidos(resumen.getTotalLeidos() + 1);

            final String[] columnas = normalizarSeparador(linea).split(SEPARADOR_CSV);
            final String nif = columnas.length > 0 ? columnas[0].trim().toUpperCase() : "";
            final String tipoDoc = columnas.length > 1 ? columnas[1].trim().toUpperCase() : "NIF";

            if (!esDocumentoValido(nif)) {
                resumen.setTotalErrores(resumen.getTotalErrores() + 1);
                resumen.addError("Linea " + numLinea + ": NIF vacio o invalido -> " + nif);
                registrarAuditoriaError(nif, tipoDoc, usuario,
                        "VALIDACION", "NIF vacio o invalido en CSV", con);
                continue;
            }

            try {
                final Persona p = new Persona();
                p.setNumDocumento(nif);
                p.setTipoDocumento(tipoDoc);

                final Response response = ClientWSVidaLaboral.getVidaLaboral(
                        p, fechaDesdeCVL, fechaHastaCVL,
                        codOrganizacion, numExpedienteTrabajo, fkWSSolicitado);

                final String codRespuesta = response != null ? response.getCodRespuesta() : "WS_NULL";
                final String descRespuesta = response != null ? response.getDescRespuesta() : "Respuesta nula del WS CVL";
                final String payloadResumen = construirPayloadResumen(response);

                final InteropCvlMasivoNifVO registro = new InteropCvlMasivoNifVO(
                        null,
                        new Timestamp(System.currentTimeMillis()),
                        nif,
                        tipoDoc,
                        codRespuesta,
                        descRespuesta,
                        payloadResumen,
                        usuario,
                        codOrganizacion,
                        codTramite,
                        ocurrenciaTramite,
                        numExpedienteTrabajo,
                        fkWSSolicitado,
                        fechaDesdeCVL,
                        fechaHastaCVL);

                InteropCvlMasivoNifDAO.getInstance().insertarRegistro(registro, con);

                resumen.setTotalProcesados(resumen.getTotalProcesados() + 1);

                if ("0000".equals(codRespuesta)) {
                    persistirEnInteropVidaLaboral(response, p,
                            fechaDesdeCVL, fechaHastaCVL,
                            numExpedienteTrabajo, con);

                    resumen.setTotalCorrectos(resumen.getTotalCorrectos() + 1);
                } else {
                    resumen.setTotalErrores(resumen.getTotalErrores() + 1);
                    resumen.addError("Linea " + numLinea + ": " + nif + " -> "
                            + codRespuesta + " " + descRespuesta);
                }

            } catch (Exception ex) {
                log.error("Error procesando linea " + numLinea + " para NIF " + nif, ex);

                resumen.setTotalErrores(resumen.getTotalErrores() + 1);
                resumen.addError("Linea " + numLinea + ": error tecnico para "
                        + nif + " -> " + ex.getMessage());

                registrarAuditoriaError(nif, tipoDoc, usuario,
                        "ERROR", ex.getMessage(), con);
            }
        }

    } finally {
        if (br != null) {
            br.close();
        }
    }

    return resumen;
}

    private void registrarAuditoriaError(final String nif, final String tipoDoc,
            final String usuario, final String codRespuesta,
            final String descRespuesta, final Connection con) {
        try {
            final InteropCvlMasivoNifVO registro = new InteropCvlMasivoNifVO(
                    null,
                    new Timestamp(System.currentTimeMillis()),
                    nif,
                    tipoDoc,
                    codRespuesta,
                    descRespuesta,
                    descRespuesta,
                    usuario);
            InteropCvlMasivoNifDAO.getInstance().insertarRegistro(registro, con);
        } catch (Exception ex) {
            log.error("No se pudo registrar auditoria de error para NIF " + nif, ex);
        }
    }

    private String construirPayloadResumen(final Response response) {
        if (response == null) {
            return "WS_NULL";
        }

        final StringBuilder sb = new StringBuilder();
        sb.append(response.getCodigoEstado() != null ? response.getCodigoEstado() : "");
        sb.append("|");
        sb.append(response.getTextoEstado() != null ? response.getTextoEstado() : "");

        if (sb.length() > 2000) {
            return sb.substring(0, 2000);
        }
        return sb.toString();
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

    private String generarNumExpedienteTecnico(final Connection con) throws Exception {
        Statement st = null;
        ResultSet rs = null;
        try {
            final int year = Calendar.getInstance().get(Calendar.YEAR);
            final String seqName = ConfigurationParameter.getParameter(
                    ConstantesMeLanbideInterop.SEQ_VIDALABORAL,
                    ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            st = con.createStatement();
            rs = st.executeQuery("SELECT " + seqName + ".NEXTVAL FROM DUAL");
            int siguiente = 1;
            if (rs.next()) {
                siguiente = rs.getInt(1);
            }

            final String secuencia6 = String.format("%06d", siguiente);
            return PREFIJO_EXP_TECNICO + "/" + year + "/" + secuencia6;
        } catch (Exception ex) {
            log.error("Error generando expediente tecnico CVL masivo", ex);
            throw new Exception(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * Inserta en INTEROP_VIDALABORAL como el batch actual de expediente.
     */
    private void persistirEnInteropVidaLaboral(final Response response, final Persona persona,
            final String fechaDesdeCVL, final String fechaHastaCVL, final String numExpediente,
            final Connection con) throws Exception {

        final java.util.List<RegistroVidaLaboralVO> registros
                = MeLanbideInteropMappingUtils.mapListaSituacionToListaVidaLaboral(
                        response.getIdentidad(), persona, fechaDesdeCVL, fechaHastaCVL);

        if (registros == null || registros.isEmpty()) {
            return;
        }

        for (int i = 0; i < registros.size(); i++) {
            final RegistroVidaLaboralVO registro = registros.get(i);
            MeLanbideInteropVidaLaboralDAO.getInstance().insertarRegistroVidaLaboral(registro, numExpediente, con);
        }
    }
}
