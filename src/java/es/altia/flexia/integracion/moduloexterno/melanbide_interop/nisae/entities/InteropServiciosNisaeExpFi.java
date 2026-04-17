/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.entities;

import java.util.Date;

/**
 *
 * @author INGDGC
 */
public class InteropServiciosNisaeExpFi {
    
    private Integer codOrganizacion;
    private String numeroExpediente;
    private Integer procesoEjecutado;
    private String observaciones;
    private Date fechaAlta;

    public Integer getCodOrganizacion() {
        return codOrganizacion;
    }

    public void setCodOrganizacion(Integer codOrganizacion) {
        this.codOrganizacion = codOrganizacion;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public Integer getProcesoEjecutado() {
        return procesoEjecutado;
    }

    public void setProcesoEjecutado(Integer procesoEjecutado) {
        this.procesoEjecutado = procesoEjecutado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
    
}
