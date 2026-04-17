package es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Resumen de ejecucion de una carga masiva CVL desde CSV/Excel.
 */
public class InteropCvlMasivoResultadoVO {

    private String numExpedienteContexto;
    private int totalLeidos;
    private int totalProcesados;
    private int totalCorrectos;
    private int totalErrores;
    private final List<String> errores = new ArrayList<String>();

    public int getTotalLeidos() {
        return totalLeidos;
    }

    public String getNumExpedienteContexto() {
        return numExpedienteContexto;
    }

    public void setNumExpedienteContexto(final String numExpedienteContexto) {
        this.numExpedienteContexto = numExpedienteContexto;
    }

    public void setTotalLeidos(final int totalLeidos) {
        this.totalLeidos = totalLeidos;
    }

    public int getTotalProcesados() {
        return totalProcesados;
    }

    public void setTotalProcesados(final int totalProcesados) {
        this.totalProcesados = totalProcesados;
    }

    public int getTotalCorrectos() {
        return totalCorrectos;
    }

    public void setTotalCorrectos(final int totalCorrectos) {
        this.totalCorrectos = totalCorrectos;
    }

    public int getTotalErrores() {
        return totalErrores;
    }

    public void setTotalErrores(final int totalErrores) {
        this.totalErrores = totalErrores;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void addError(final String error) {
        errores.add(error);
    }
}
