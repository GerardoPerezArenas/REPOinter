/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author INGDGC
 */
public class InteropJobsFSEPersonaServicio {
    
    private final SimpleDateFormat formatFechaddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
    
    private String numeroExpediente;
    private String tipoDocumentoRegexlan;
    private String tipoDocumetoLanbide;
    private String numeroDocumento;
    private Date fechaInicioServicio;
    private Date fechaFinServicio;
    private String resultado;

    public InteropJobsFSEPersonaServicio() {
    }

    public InteropJobsFSEPersonaServicio(String numeroExpediente,String tipoDocumentoRegexlan, String tipoDocumetoLanbide, String numeroDocumento, Date fechaInicioServicio, Date fechaFinServicio, String resultado) {
        this.numeroExpediente = numeroExpediente;
        this.tipoDocumentoRegexlan = tipoDocumentoRegexlan;
        this.tipoDocumetoLanbide = tipoDocumetoLanbide;
        this.numeroDocumento = numeroDocumento;
        this.fechaInicioServicio = fechaInicioServicio;
        this.fechaFinServicio = fechaFinServicio;
        this.resultado = resultado;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }
    
    public String getTipoDocumentoRegexlan() {
        return tipoDocumentoRegexlan;
    }

    public void setTipoDocumentoRegexlan(String tipoDocumentoRegexlan) {
        this.tipoDocumentoRegexlan = tipoDocumentoRegexlan;
    }

    public String getTipoDocumetoLanbide() {
        return tipoDocumetoLanbide;
    }

    public void setTipoDocumetoLanbide(String tipoDocumetoLanbide) {
        this.tipoDocumetoLanbide = tipoDocumetoLanbide;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFechaInicioServicio() {
        return fechaInicioServicio;
    }

    public void setFechaInicioServicio(Date fechaInicioServicio) {
        this.fechaInicioServicio = fechaInicioServicio;
    }

    public Date getFechaFinServicio() {
        return fechaFinServicio;
    }

    public void setFechaFinServicio(Date fechaFinServicio) {
        this.fechaFinServicio = fechaFinServicio;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getFechaInicioServicioAsString() {
        if(this.fechaInicioServicio!=null){
            return formatFechaddMMyyyy.format(this.fechaInicioServicio);
        }return "";
    }
    
    public String getFechaFinServicioAsString() {
        if(this.fechaFinServicio!=null){
            return formatFechaddMMyyyy.format(this.fechaFinServicio);
        }return "";
    }

    @Override
    public String toString() {
        return "InteropJobsFSEPersonaServicio{numeroExpediente=" + numeroExpediente + ", tipoDocumentoRegexlan=" + tipoDocumentoRegexlan + ", tipoDocumetoLanbide=" + tipoDocumetoLanbide + ", numeroDocumento=" + numeroDocumento + ", fechaInicioServicio=" + this.getFechaInicioServicioAsString() + ", fechaFinServicio=" + this.getFechaFinServicioAsString() + ", resultado=" + resultado + '}';
    }
    
}
