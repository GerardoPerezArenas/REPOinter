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
 */
public class InteropCvlMasivoCsvService {

    private static final Logger log = Logger.getLogger(InteropCvlMasivoCsvService.class);
    private static final String SEPARADOR_CSV = ";";
    private static final String PREFIJO_EXP_TECNICO = "CVL_MASIVO";

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
                        Integer.valueOf(codOrganizacion),
                        Integer.valueOf(codTramite),
                        Integer.valueOf(ocurrenciaTramite),
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
