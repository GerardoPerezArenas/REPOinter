package es.altia.flexia.integracion.moduloexterno.melanbide_interop.services;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

public class CvlMasivoFechaUtilsTest {

    @Test
    public void parseaFechaDdMMyyyyEstricto() throws Exception {
        final Date fecha = CvlMasivoFechaUtils.parsearFechaEstricto("22/04/2026");
        Assert.assertNotNull(fecha);
    }

    @Test(expected = java.text.ParseException.class)
    public void rechazaFechaInvalida() throws Exception {
        CvlMasivoFechaUtils.parsearFechaEstricto("31/02/2026");
    }

    @Test
    public void validaRangoMaximoCincoAnios() throws Exception {
        final Date desde = CvlMasivoFechaUtils.parsearFechaEstricto("01/01/2020");
        final Date hastaValida = CvlMasivoFechaUtils.parsearFechaEstricto("01/01/2025");
        final Date hastaInvalida = CvlMasivoFechaUtils.parsearFechaEstricto("02/01/2025");

        Assert.assertTrue(CvlMasivoFechaUtils.estaDentroMaximoAnios(desde, hastaValida, 5));
        Assert.assertFalse(CvlMasivoFechaUtils.estaDentroMaximoAnios(desde, hastaInvalida, 5));
    }
}
