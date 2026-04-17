/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo;

/**
 *
 * @author INGDGC
 */
public class Tramitador {

    private String nifUsuarioTramitador; 
    private String usuarioTramitador;
    private String procedimientoPadron;
    private String nombreProcedimientoAutorizado;
    private String finalidadProcedimiento;
    private String consentimientoFirmado;
    private String autorizacionLlamarINE;

    public String getNifUsuarioTramitador() {
        return nifUsuarioTramitador;
    }

    public void setNifUsuarioTramitador(String nifUsuarioTramitador) {
        this.nifUsuarioTramitador = nifUsuarioTramitador;
    }

    public String getUsuarioTramitador() {
        return usuarioTramitador;
    }

    public void setUsuarioTramitador(String usuarioTramitador) {
        this.usuarioTramitador = usuarioTramitador;
    }

    public String getProcedimientoPadron() {
        return procedimientoPadron;
    }

    public void setProcedimientoPadron(String procedimientoPadron) {
        this.procedimientoPadron = procedimientoPadron;
    }

    public String getNombreProcedimientoAutorizado() {
        return nombreProcedimientoAutorizado;
    }

    public void setNombreProcedimientoAutorizado(String nombreProcedimientoAutorizado) {
        this.nombreProcedimientoAutorizado = nombreProcedimientoAutorizado;
    }

    public String getFinalidadProcedimiento() {
        return finalidadProcedimiento;
    }

    public void setFinalidadProcedimiento(String finalidadProcedimiento) {
        this.finalidadProcedimiento = finalidadProcedimiento;
    }

    public String getConsentimientoFirmado() {
        return consentimientoFirmado;
    }

    public void setConsentimientoFirmado(String consentimientoFirmado) {
        this.consentimientoFirmado = consentimientoFirmado;
    }

    public String getAutorizacionLlamarINE() {
        return autorizacionLlamarINE;
    }

    public void setAutorizacionLlamarINE(String autorizacionLlamarINE) {
        this.autorizacionLlamarINE = autorizacionLlamarINE;
    }
    
    
    
}
