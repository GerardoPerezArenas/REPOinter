/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author INGDGC
 */
public class InteropJobsAKSSGAExpedienteRequest {
    
    private static SimpleDateFormat dateFormarddMMyyy = new SimpleDateFormat("dd/MM/yyyy");
    
    private String expedienteNumero;
    private Date expedienteFechaInicio;
    private Date expedienteFechaFin;
    private String expedienteUbicacionDoc;
    private String expedienteTitulo;
    private String expedienteLugares;
    private List<InteropJobsAKSSGADocumento> documentos;

    public InteropJobsAKSSGAExpedienteRequest(String expedienteNumero, Date expedienteFechaInicio, Date expedienteFechaFin, String expedienteUbicacionDoc, List<InteropJobsAKSSGADocumento> documentos) {
        this.expedienteNumero = expedienteNumero;
        this.expedienteFechaInicio = expedienteFechaInicio;
        this.expedienteFechaFin = expedienteFechaFin;
        this.expedienteUbicacionDoc = expedienteUbicacionDoc;
        this.documentos = documentos;
    }
    
    public InteropJobsAKSSGAExpedienteRequest(String expedienteNumero, Date expedienteFechaInicio, Date expedienteFechaFin, String expedienteUbicacionDoc, String expedienteTitulo, String expedienteLugares) {
        this.expedienteNumero = expedienteNumero;
        this.expedienteFechaInicio = expedienteFechaInicio;
        this.expedienteFechaFin = expedienteFechaFin;
        this.expedienteUbicacionDoc = expedienteUbicacionDoc;
        this.expedienteTitulo = expedienteTitulo;
        this.expedienteLugares = expedienteLugares;
    }
           
    public InteropJobsAKSSGAExpedienteRequest(String expedienteNumero) {
        this.expedienteNumero = expedienteNumero;
    }

    public InteropJobsAKSSGAExpedienteRequest() {
    }

    public String getExpedienteNumero() {
        return expedienteNumero;
    }

    public void setExpedienteNumero(String expedienteNumero) {
        this.expedienteNumero = expedienteNumero;
    }

    public List<InteropJobsAKSSGADocumento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<InteropJobsAKSSGADocumento> documentos) {
        this.documentos = documentos;
    }

    public Date getExpedienteFechaInicio() {
        return expedienteFechaInicio;
    }

    public void setExpedienteFechaInicio(Date expedienteFechaInicio) {
        this.expedienteFechaInicio = expedienteFechaInicio;
    }

    public Date getExpedienteFechaFin() {
        return expedienteFechaFin;
    }

    public void setExpedienteFechaFin(Date expedienteFechaFin) {
        this.expedienteFechaFin = expedienteFechaFin;
    }

    public String getExpedienteUbicacionDoc() {
        return expedienteUbicacionDoc;
    }

    public void setExpedienteUbicacionDoc(String expedienteUbicacionDoc) {
        this.expedienteUbicacionDoc = expedienteUbicacionDoc;
    }

    public String getExpedienteTitulo() {
        return expedienteTitulo;
    }

    public void setExpedienteTitulo(String expedienteTitulo) {
        this.expedienteTitulo = expedienteTitulo;
    }

    public String getExpedienteLugares() {
        return expedienteLugares;
    }

    public void setExpedienteLugares(String expedienteLugares) {
        this.expedienteLugares = expedienteLugares;
    }

    @Override
    public String toString() {
        return "InteropJobsAKSSGAExpedienteRequest{" + "expedienteNumero=" + expedienteNumero + ", expedienteFechaInicio=" + (expedienteFechaInicio!=null?dateFormarddMMyyy.format(expedienteFechaInicio):"") + ", expedienteFechaFin=" + (expedienteFechaFin!=null?dateFormarddMMyyy.format(expedienteFechaFin):"") + ", expedienteUbicacionDoc=" + expedienteUbicacionDoc + ",expedienteTitulo=" + expedienteTitulo +", expedienteLugares=" + expedienteLugares +", documentos=" + (documentos!=null?Arrays.toString(documentos.toArray()):"") + '}';
    }

    
}
