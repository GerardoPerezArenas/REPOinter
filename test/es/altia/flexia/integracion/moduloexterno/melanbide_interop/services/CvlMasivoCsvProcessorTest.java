package es.altia.flexia.integracion.moduloexterno.melanbide_interop.services;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.InteropCvlMasivoNifVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.InteropCvlMasivoResultadoVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Persona;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.ws.client.vidalaboralws.response.Response;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class CvlMasivoCsvProcessorTest {

    @Test
    public void detectaCabeceraCsvDocumentoTipoDoc() throws Exception {
        final TestWsClient wsClient = new TestWsClient();
        wsClient.addResponse(response("0001", "KO"));

        final TestAuditoriaWriter auditoriaWriter = new TestAuditoriaWriter();
        final TestVidaLaboralWriter vidaLaboralWriter = new TestVidaLaboralWriter();
        final TestSleeper sleeper = new TestSleeper();

        final CvlMasivoCsvProcessor processor = new CvlMasivoCsvProcessor(wsClient, auditoriaWriter, vidaLaboralWriter, sleeper);
        final String csv = "DOCUMENTO;TIPO_DOC\n12345678Z;NIF";

        final InteropCvlMasivoResultadoVO resultado = processor.procesarCsv(
                new StringReader(csv), "2025-01-01", "2025-12-31",
                1, 10, 1, "CVL_MASIVO/2026/000001", "1", "tester");

        Assert.assertEquals(1, resultado.getTotalLeidos());
        Assert.assertEquals(1, wsClient.invocaciones);
    }

    @Test
    public void bloqueaPasaporteSinInvocarWsYContinua() throws Exception {
        final TestWsClient wsClient = new TestWsClient();
        wsClient.addResponse(response("0001", "KO NEGOCIO"));

        final TestAuditoriaWriter auditoriaWriter = new TestAuditoriaWriter();
        final TestVidaLaboralWriter vidaLaboralWriter = new TestVidaLaboralWriter();
        final TestSleeper sleeper = new TestSleeper();

        final CvlMasivoCsvProcessor processor = new CvlMasivoCsvProcessor(wsClient, auditoriaWriter, vidaLaboralWriter, sleeper);
        final String csv = "DOCUMENTO;TIPO_DOC\nAA123456;PASAPORTE\n12345678Z;NIF";

        final InteropCvlMasivoResultadoVO resultado = processor.procesarCsv(
                new StringReader(csv), "2025-01-01", "2025-12-31",
                1, 10, 1, "CVL_MASIVO/2026/000002", "1", "tester");

        Assert.assertEquals(2, resultado.getTotalLeidos());
        Assert.assertEquals(1, wsClient.invocaciones);
        Assert.assertEquals(2, auditoriaWriter.registros.size());
        Assert.assertEquals("INVALID_TIPO_DOC", auditoriaWriter.registros.get(0).getCodRespuesta());
    }

    @Test
    public void reintentaWsAnteExcepcionYNullHastaExito() throws Exception {
        final TestWsClient wsClient = new TestWsClient();
        wsClient.addException(new RuntimeException("fallo tecnico"));
        wsClient.addResponse(null);
        wsClient.addResponse(response("0000", "OK"));

        final TestAuditoriaWriter auditoriaWriter = new TestAuditoriaWriter();
        final TestVidaLaboralWriter vidaLaboralWriter = new TestVidaLaboralWriter();
        final TestSleeper sleeper = new TestSleeper();

        final CvlMasivoCsvProcessor processor = new CvlMasivoCsvProcessor(wsClient, auditoriaWriter, vidaLaboralWriter, sleeper);
        final String csv = "12345678Z;NIF";

        final InteropCvlMasivoResultadoVO resultado = processor.procesarCsv(
                new StringReader(csv), "2025-01-01", "2025-12-31",
                1, 10, 1, "CVL_MASIVO/2026/000003", "1", "tester");

        Assert.assertEquals(3, wsClient.invocaciones);
        Assert.assertEquals(2, sleeper.esperas.size());
        Assert.assertEquals(Long.valueOf(1000L), sleeper.esperas.get(0));
        Assert.assertEquals(Long.valueOf(3000L), sleeper.esperas.get(1));
        Assert.assertEquals(1, resultado.getTotalCorrectos());
        Assert.assertEquals(1, vidaLaboralWriter.persistencias);
    }

    private static Response response(final String codigo, final String descripcion) {
        return new Response(codigo, descripcion, "ESTADO", "TEXTO", null, null);
    }

    private static class TestWsClient implements CvlMasivoCsvProcessor.WsClient {

        private final List<Response> responses = new ArrayList<Response>();
        private final List<Exception> exceptions = new ArrayList<Exception>();
        private int invocaciones = 0;

        public void addResponse(final Response response) {
            responses.add(response);
        }

        public void addException(final Exception ex) {
            exceptions.add(ex);
        }

        public Response getVidaLaboral(final Persona persona, final String fechaDesdeCVL,
                final String fechaHastaCVL, final int codOrganizacion,
                final String numExpediente, final String fkWSSolicitado) throws Exception {
            invocaciones++;
            if (!exceptions.isEmpty()) {
                throw exceptions.remove(0);
            }
            if (responses.isEmpty()) {
                return null;
            }
            return responses.remove(0);
        }
    }

    private static class TestAuditoriaWriter implements CvlMasivoCsvProcessor.AuditoriaWriter {

        private final List<InteropCvlMasivoNifVO> registros = new ArrayList<InteropCvlMasivoNifVO>();

        public void insertarRegistro(final InteropCvlMasivoNifVO registro) {
            registros.add(registro);
        }
    }

    private static class TestVidaLaboralWriter implements CvlMasivoCsvProcessor.VidaLaboralWriter {

        private int persistencias = 0;

        public void persistirRespuesta(final Response response, final Persona persona,
                final String fechaDesdeCVL, final String fechaHastaCVL, final String numExpediente) {
            persistencias++;
        }
    }

    private static class TestSleeper implements CvlMasivoCsvProcessor.Sleeper {

        private final List<Long> esperas = new ArrayList<Long>();

        public void sleep(final long millis) {
            esperas.add(Long.valueOf(millis));
        }
    }
}
