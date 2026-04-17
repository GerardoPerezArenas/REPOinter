/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl;

/**
 *
 * @author jaime.hermoso
 */
public class Tramitador {
       
    private String codProcedimiento;
    
    private String nombreProcedimiento;
    
    private String finalidadProcedimiento;
    
    private String consentimientoFirmado;
    
    private String nifUsuarioTramitador;
    
    private String usuarioTramitador;

    public Tramitador() {
    }
    
    
    public Tramitador(String codProcedimiento, String nombreProcedimiento, String finalidadProcedimiento, String consentimientoFirmado, String nifUsuarioTramitador, String usuarioTramitador) {
        this.codProcedimiento = codProcedimiento;
        this.nombreProcedimiento = nombreProcedimiento;
        this.finalidadProcedimiento = finalidadProcedimiento;
        this.consentimientoFirmado = consentimientoFirmado;
        this.nifUsuarioTramitador = nifUsuarioTramitador;
        this.usuarioTramitador = usuarioTramitador;
    }    

    /**
     * @return the codProcedimiento
     */
    public String getCodProcedimiento() {
        return codProcedimiento;
    }

    /**
     * @param codProcedimiento the codProcedimiento to set
     */
    public void setCodProcedimiento(String codProcedimiento) {
        this.codProcedimiento = codProcedimiento;
    }

    /**
     * @return the nombreProcedimiento
     */
    public String getNombreProcedimiento() {
        return nombreProcedimiento;
    }

    /**
     * @param nombreProcedimiento the nombreProcedimiento to set
     */
    public void setNombreProcedimiento(String nombreProcedimiento) {
        this.nombreProcedimiento = nombreProcedimiento;
    }

    /**
     * @return the finalidadProcedimiento
     */
    public String getFinalidadProcedimiento() {
        return finalidadProcedimiento;
    }

    /**
     * @param finalidadProcedimiento the finalidadProcedimiento to set
     */
    public void setFinalidadProcedimiento(String finalidadProcedimiento) {
        this.finalidadProcedimiento = finalidadProcedimiento;
    }

    /**
     * @return the consentimientoFirmado
     */
    public String getConsentimientoFirmado() {
        return consentimientoFirmado;
    }

    /**
     * @param consentimientoFirmado the consentimientoFirmado to set
     */
    public void setConsentimientoFirmado(String consentimientoFirmado) {
        this.consentimientoFirmado = consentimientoFirmado;
    }

    /**
     * @return the nifUsuarioTramitador
     */
    public String getNifUsuarioTramitador() {
        return nifUsuarioTramitador;
    }

    /**
     * @param nifUsuarioTramitador the nifUsuarioTramitador to set
     */
    public void setNifUsuarioTramitador(String nifUsuarioTramitador) {
        this.nifUsuarioTramitador = nifUsuarioTramitador;
    }

    /**
     * @return the usuarioTramitador
     */
    public String getUsuarioTramitador() {
        return usuarioTramitador;
    }

    /**
     * @param usuarioTramitador the usuarioTramitador to set
     */
    public void setUsuarioTramitador(String usuarioTramitador) {
        this.usuarioTramitador = usuarioTramitador;
    }

    
    
}
