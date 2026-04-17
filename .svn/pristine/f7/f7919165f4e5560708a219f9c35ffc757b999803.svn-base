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
public class InteropJobsLog {
    
    private final SimpleDateFormat formatFechaddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");

    private long id;
    private int idOrganizacion;
    private String nombreProcesoJobBatch;
    private String codigoProcedimiento;
    private String numeroExpediente;
    private String tipoDocumentoId;
    private String documentoId;
    private String resultadoWS; 
    private String datosEnviados;
    private String datosRespuesta;
    private String observaciones; 
    private Integer personaProcesada; 
    private Date fechaCreacion;

    public InteropJobsLog() {
    }

    public InteropJobsLog(long id, int idOrganizacion, String nombreProcesoJobBatch, String codigoProcedimiento, String numeroExpediente, String tipoDocumentoId, String documentoId, String resultadoWS, String datosEnviados, String datosRespuesta, String observaciones, Integer personaProcesada,Date fechaCreacion) {
        this.id = id;
        this.idOrganizacion = idOrganizacion;
        this.nombreProcesoJobBatch = nombreProcesoJobBatch;
        this.codigoProcedimiento = codigoProcedimiento;
        this.numeroExpediente = numeroExpediente;
        this.tipoDocumentoId = tipoDocumentoId;
        this.documentoId = documentoId;
        this.resultadoWS = resultadoWS;
        this.datosEnviados = datosEnviados;
        this.datosRespuesta = datosRespuesta;
        this.observaciones = observaciones;
        this.personaProcesada = personaProcesada;
        this.fechaCreacion = fechaCreacion;
    }

    public InteropJobsLog(String nombreProcesoJobBatch, String codigoProcedimiento, String numeroExpediente, String tipoDocumentoId, String documentoId, String datosEnviados) {
        this.nombreProcesoJobBatch = nombreProcesoJobBatch;
        this.codigoProcedimiento = codigoProcedimiento;
        this.numeroExpediente = numeroExpediente;
        this.tipoDocumentoId = tipoDocumentoId;
        this.documentoId = documentoId;
        this.datosEnviados = datosEnviados;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(int idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public String getNombreProcesoJobBatch() {
        return nombreProcesoJobBatch;
    }

    public void setNombreProcesoJobBatch(String nombreProcesoJobBatch) {
        this.nombreProcesoJobBatch = nombreProcesoJobBatch;
    }

    public String getCodigoProcedimiento() {
        return codigoProcedimiento;
    }

    public void setCodigoProcedimiento(String codigoProcedimiento) {
        this.codigoProcedimiento = codigoProcedimiento;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getTipoDocumentoId() {
        return tipoDocumentoId;
    }

    public void setTipoDocumentoId(String tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }

    public String getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(String documentoId) {
        this.documentoId = documentoId;
    }

    public String getResultadoWS() {
        return resultadoWS;
    }

    public void setResultadoWS(String resultadoWS) {
        this.resultadoWS = resultadoWS;
    }

    public String getDatosEnviados() {
        return datosEnviados;
    }

    public void setDatosEnviados(String datosEnviados) {
        this.datosEnviados = datosEnviados;
    }

    public String getDatosRespuesta() {
        return datosRespuesta;
    }

    public void setDatosRespuesta(String datosRespuesta) {
        this.datosRespuesta = datosRespuesta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getPersonaProcesada() {
        return personaProcesada;
    }

    public void setPersonaProcesada(Integer personaProcesada) {
        this.personaProcesada = personaProcesada;
    }
    
    @Override
    public String toString() {
        return "InteropJobsLog{" + "id=" + id + ", idOrganizacion=" + idOrganizacion + ", nombreProcesoJobBatch=" + nombreProcesoJobBatch + ", codigoProcedimiento=" + codigoProcedimiento + ", numeroExpediente=" + numeroExpediente + ", tipoDocumentoId=" + tipoDocumentoId + ", documentoId=" + documentoId + ", resultadoWS=" + resultadoWS + ", datosEnviados=" + datosEnviados + ", datosRespuesta=" + datosRespuesta + ", observaciones=" + observaciones + ", personaProcesada=" + personaProcesada  + ", fechaCreacion=" + (fechaCreacion!=null?formatFechaddMMyyyy.format(fechaCreacion):"") + '}';
    }
    
}
