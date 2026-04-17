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
public class InteropJobsFSEProcedi {
    
    private final SimpleDateFormat formatFechaddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
   
    private String codigoProcedimiento; // varchar2(20) primary key,
    private int procesar; // number(1) default 1,
    private String codVisTramNotifResol;// varchar2(5)
    private String codCampoTramFecResol; // varchar2(20)
    private String codCampoExpFecResol; //  c
    private String codVisTramExpConcedido; // varchar2(5)
    private String codCampoConcedido; // varchar2(20)
    private int TipoDatoCampoConcedido; // number
    private String valorCampoExpConcedido; // varchar(5)
    private String codCampoFechaRefExpediente; // varchar2(20)
    private int codRolPersonaServicio; // number
    private String modalidad; // varchar(5)
    private Date fechaCreacion; //timestamp default systimestamp

    public InteropJobsFSEProcedi() {
    }

    public InteropJobsFSEProcedi(String codigoProcedimiento, int procesar, String codVisTramNotifResol, String codCampoTramFecResol, String codCampoExpFecResol, String codVisTramExpConcedido, String codCampoConcedido, int TipoDatoCampoConcedido, String valorCampoExpConcedido, String codCampoFechaRefExpediente, int codRolPersonaServicio,String modalidad,Date fechaCreacion) {
        this.codigoProcedimiento = codigoProcedimiento;
        this.procesar = procesar;
        this.codVisTramNotifResol = codVisTramNotifResol;
        this.codCampoTramFecResol = codCampoTramFecResol;
        this.codCampoExpFecResol = codCampoExpFecResol;
        this.codVisTramExpConcedido = codVisTramExpConcedido;
        this.codCampoConcedido = codCampoConcedido;
        this.TipoDatoCampoConcedido = TipoDatoCampoConcedido;
        this.valorCampoExpConcedido = valorCampoExpConcedido;
        this.codCampoFechaRefExpediente = codCampoFechaRefExpediente;
        this.codRolPersonaServicio = codRolPersonaServicio;
        this.modalidad=modalidad;
        this.fechaCreacion = fechaCreacion;
    }

    public String getCodigoProcedimiento() {
        return codigoProcedimiento;
    }

    public void setCodigoProcedimiento(String codigoProcedimiento) {
        this.codigoProcedimiento = codigoProcedimiento;
    }

    public int getProcesar() {
        return procesar;
    }

    public void setProcesar(int procesar) {
        this.procesar = procesar;
    }

    public String getCodVisTramNotifResol() {
        return codVisTramNotifResol;
    }

    public void setCodVisTramNotifResol(String codVisTramNotifResol) {
        this.codVisTramNotifResol = codVisTramNotifResol;
    }

    public String getCodCampoTramFecResol() {
        return codCampoTramFecResol;
    }

    public void setCodCampoTramFecResol(String codCampoTramFecResol) {
        this.codCampoTramFecResol = codCampoTramFecResol;
    }

    public String getCodCampoExpFecResol() {
        return codCampoExpFecResol;
    }

    public void setCodCampoExpFecResol(String codCampoExpFecResol) {
        this.codCampoExpFecResol = codCampoExpFecResol;
    }

    public String getCodVisTramExpConcedido() {
        return codVisTramExpConcedido;
    }

    public void setCodVisTramExpConcedido(String codVisTramExpConcedido) {
        this.codVisTramExpConcedido = codVisTramExpConcedido;
    }

    public String getCodCampoConcedido() {
        return codCampoConcedido;
    }

    public void setCodCampoConcedido(String codCampoConcedido) {
        this.codCampoConcedido = codCampoConcedido;
    }

    public int getTipoDatoCampoConcedido() {
        return TipoDatoCampoConcedido;
    }

    public void setTipoDatoCampoConcedido(int TipoDatoCampoConcedido) {
        this.TipoDatoCampoConcedido = TipoDatoCampoConcedido;
    }

    public String getValorCampoExpConcedido() {
        return valorCampoExpConcedido;
    }

    public void setValorCampoExpConcedido(String valorCampoExpConcedido) {
        this.valorCampoExpConcedido = valorCampoExpConcedido;
    }

    public String getCodCampoFechaRefExpediente() {
        return codCampoFechaRefExpediente;
    }

    public void setCodCampoFechaRefExpediente(String codCampoFechaRefExpediente) {
        this.codCampoFechaRefExpediente = codCampoFechaRefExpediente;
    }

    public int getCodRolPersonaServicio() {
        return codRolPersonaServicio;
    }

    public void setCodRolPersonaServicio(int codRolPersonaServicio) {
        this.codRolPersonaServicio = codRolPersonaServicio;
    }
    
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public String getFechaAsStringddMMyyy(Date fecha){
        if(fecha!=null){
            return formatFechaddMMyyyy.format(fecha);
        }else return "";
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    @Override
    public String toString() {
        return "InteropJobsFSEProcedi{" + "formatFechaddMMyyyy=" + formatFechaddMMyyyy + ", codigoProcedimiento=" + codigoProcedimiento + ", procesar=" + procesar + ", codVisTramNotifResol=" + codVisTramNotifResol + ", codCampoTramFecResol=" + codCampoTramFecResol + ", codCampoExpFecResol=" + codCampoExpFecResol + ", codVisTramExpConcedido=" + codVisTramExpConcedido + ", codCampoConcedido=" + codCampoConcedido + ", TipoDatoCampoConcedido=" + TipoDatoCampoConcedido + ", valorCampoExpConcedido=" + valorCampoExpConcedido + ", codCampoFechaRefExpediente=" + codCampoFechaRefExpediente + ", codRolPersonaServicio=" + codRolPersonaServicio + ", modalidad=" + modalidad + ", fechaCreacion=" + formatFechaddMMyyyy.format(fechaCreacion) + '}';
    }
    
}
