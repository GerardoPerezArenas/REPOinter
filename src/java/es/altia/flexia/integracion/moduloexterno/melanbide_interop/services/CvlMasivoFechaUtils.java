package es.altia.flexia.integracion.moduloexterno.melanbide_interop.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utilidades de validacion/normalizacion de fechas para CVL masivo.
 */
public final class CvlMasivoFechaUtils {

    private static final String FORMATO_ENTRADA = "dd/MM/yyyy";
    private static final String FORMATO_WS = "yyyy-MM-dd";

    private CvlMasivoFechaUtils() {
    }

    public static Date parsearFechaEstricto(final String fecha) throws ParseException {
        if (fecha == null || fecha.trim().length() == 0) {
            throw new ParseException("Fecha vacia", 0);
        }
        final SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_ENTRADA);
        sdf.setLenient(false);
        return sdf.parse(fecha.trim());
    }

    public static boolean estaDentroMaximoAnios(final Date fechaDesde, final Date fechaHasta, final int maxAnios) {
        if (fechaDesde == null || fechaHasta == null || maxAnios < 0) {
            return false;
        }
        if (fechaHasta.before(fechaDesde)) {
            return false;
        }
        final Calendar limite = Calendar.getInstance();
        limite.setTime(fechaDesde);
        limite.add(Calendar.YEAR, maxAnios);
        return !fechaHasta.after(limite.getTime());
    }

    public static String formatearFechaWs(final Date fecha) {
        if (fecha == null) {
            return "";
        }
        return new SimpleDateFormat(FORMATO_WS).format(fecha);
    }
}
