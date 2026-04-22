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
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import org.apache.log4j.Logger;
/**
 * Servicio batch para procesar un CSV con documentos y consultar CVL en Lanbide.
 */
public class InteropCvlMasivoCsvService {

    private static final Logger log = Logger.getLogger(InteropCvlMasivoCsvService.class);
    private static final String PREFIJO_EXP_TECNICO = "CVL_MASIVO";

    /**
     * Procesa un CSV en formato "DOCUMENTO;TIPO_DOC", consulta CVL y persiste auditoría/resultados.
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
        final CvlMasivoCsvProcessor processor = crearProcesador(con);
        final InteropCvlMasivoResultadoVO resultado = processor.procesarCsv(
                csvReader,
                fechaDesdeCVL,
                fechaHastaCVL,
                codOrganizacion,
                codTramite,
                ocurrenciaTramite,
                numExpedienteTrabajo,
                fkWSSolicitado,
                usuario);
        resumen.setNumExpedienteContexto(resultado.getNumExpedienteContexto());
        resumen.setTotalLeidos(resultado.getTotalLeidos());
        resumen.setTotalProcesados(resultado.getTotalProcesados());
        resumen.setTotalCorrectos(resultado.getTotalCorrectos());
        resumen.setTotalErrores(resultado.getTotalErrores());
        resumen.getErrores().addAll(resultado.getErrores());
        return resumen;
    }

    private CvlMasivoCsvProcessor crearProcesador(final Connection con) {
        return new CvlMasivoCsvProcessor(
                new CvlMasivoCsvProcessor.WsClient() {
                    public Response getVidaLaboral(final Persona persona, final String fechaDesdeCVL,
                            final String fechaHastaCVL, final int codOrganizacion, final String numExpediente,
                            final String fkWSSolicitado) throws Exception {
                        return invocarWsVidaLaboral(persona, fechaDesdeCVL, fechaHastaCVL, codOrganizacion,
                                numExpediente, fkWSSolicitado);
                    }
                },
                new CvlMasivoCsvProcessor.AuditoriaWriter() {
                    public void insertarRegistro(final InteropCvlMasivoNifVO registro) throws Exception {
                        InteropCvlMasivoNifDAO.getInstance().insertarRegistro(registro, con);
                    }
                },
                new CvlMasivoCsvProcessor.VidaLaboralWriter() {
                    public void persistirRespuesta(final Response response, final Persona persona,
                            final String fechaDesdeCVL, final String fechaHastaCVL,
                            final String numExpediente) throws Exception {
                        persistirEnInteropVidaLaboral(response, persona, fechaDesdeCVL, fechaHastaCVL,
                                numExpediente, con);
                    }
                },
                new CvlMasivoCsvProcessor.Sleeper() {
                    public void sleep(final long millis) throws InterruptedException {
                        Thread.sleep(millis);
                    }
                });
    }

    protected Response invocarWsVidaLaboral(final Persona persona, final String fechaDesdeCVL,
            final String fechaHastaCVL, final int codOrganizacion, final String numExpediente,
            final String fkWSSolicitado) {
        return ClientWSVidaLaboral.getVidaLaboral(persona, fechaDesdeCVL, fechaHastaCVL,
                codOrganizacion, numExpediente, fkWSSolicitado);
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
