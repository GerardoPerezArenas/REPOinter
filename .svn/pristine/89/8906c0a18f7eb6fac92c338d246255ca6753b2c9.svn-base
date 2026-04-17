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
public class InteropJobsFSEExptsProce {
    
    private final SimpleDateFormat formatFechaddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
    
    private Long id; // number primary key,
    private String nombreProcesoJobBatch; //varchar2(200) not null,
    private String numeroExpediente; // varchar2(35) not null,
    private String observaciones; // varchar2(4000),
    private int expedienteProcesadoCompl; // number(1) default 0,
    private Date fechaCreacion; // timestamp default systimestamp 

    public InteropJobsFSEExptsProce(Long id, String nombreProcesoJobBatch, String numeroExpediente, String observaciones, int expedienteProcesadoCompl, Date fechaCreacion) {
        this.id = id;
        this.nombreProcesoJobBatch = nombreProcesoJobBatch;
        this.numeroExpediente = numeroExpediente;
        this.observaciones = observaciones;
        this.expedienteProcesadoCompl = expedienteProcesadoCompl;
        this.fechaCreacion = fechaCreacion;
    }

    public InteropJobsFSEExptsProce(String nombreProcesoJobBatch, String numeroExpediente, String observaciones) {
        this.nombreProcesoJobBatch = nombreProcesoJobBatch;
        this.numeroExpediente = numeroExpediente;
        this.observaciones = observaciones;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProcesoJobBatch() {
        return nombreProcesoJobBatch;
    }

    public void setNombreProcesoJobBatch(String nombreProcesoJobBatch) {
        this.nombreProcesoJobBatch = nombreProcesoJobBatch;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getExpedienteProcesadoCompl() {
        return expedienteProcesadoCompl;
    }

    public void setExpedienteProcesadoCompl(int expedienteProcesadoCompl) {
        this.expedienteProcesadoCompl = expedienteProcesadoCompl;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    
    public String getFechaCreacionAsddMMyyyy() {
        return (fechaCreacion!=null ? formatFechaddMMyyyy.format(fechaCreacion): null);
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "InteropJobsFSEExptsProce{id=" + id + ", nombreProcesoJobBatch=" + nombreProcesoJobBatch + ", numeroExpediente=" + numeroExpediente + ", observaciones=" + observaciones + ", expedienteProcesadoCompl=" + expedienteProcesadoCompl + ", fechaCreacion=" + getFechaCreacionAsddMMyyyy() + '}';
    }
    
    
    
    
}
