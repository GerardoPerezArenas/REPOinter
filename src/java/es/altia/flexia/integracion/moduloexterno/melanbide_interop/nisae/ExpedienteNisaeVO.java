/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.tercero.TerceroVO;

/**
 *
 * @author INGDGC
 */
public class ExpedienteNisaeVO {
    
    private String numeroExpediente;
    private TerceroVO titularExpediente;
    private String documentoInteresadoPeticion;
    private String estadoPeticion;
    private String codigoEstadoSecundarioPeticion;
    private Integer idPeticionPreviaBBDDLog;
    private String observaciones;
    private Integer fkWSSolicitado;
    private String oposicionObligacionesTributariasHF; // OPOSVERIAUTOHHFF
    private String oposicionCertificadoPagoTGSS; // OPOSVERIAUTOTGSS
    private String oposicionTarjetaIdentificacionFiscalEmpresa; // OPOSVERIAUTOTIFE
    private String oposicionCertificadoAltaIAE; // OPOSVERIIAE

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public TerceroVO getTitularExpediente() {
        return titularExpediente;
    }

    public void setTitularExpediente(TerceroVO titularExpediente) {
        this.titularExpediente = titularExpediente;
    }

    public String getDocumentoInteresadoPeticion() {
        return documentoInteresadoPeticion;
    }

    public void setDocumentoInteresadoPeticion(String documentoInteresadoPeticion) {
        this.documentoInteresadoPeticion = documentoInteresadoPeticion;
    }

    public String getEstadoPeticion() {
        return estadoPeticion;
    }

    public void setEstadoPeticion(String estadoPeticion) {
        this.estadoPeticion = estadoPeticion;
    }

    public String getCodigoEstadoSecundarioPeticion() {
        return codigoEstadoSecundarioPeticion;
    }

    public void setCodigoEstadoSecundarioPeticion(String codigoEstadoSecundarioPeticion) {
        this.codigoEstadoSecundarioPeticion = codigoEstadoSecundarioPeticion;
    }

    public Integer getIdPeticionPreviaBBDDLog() {
        return idPeticionPreviaBBDDLog;
    }

    public void setIdPeticionPreviaBBDDLog(Integer idPeticionPreviaBBDDLog) {
        this.idPeticionPreviaBBDDLog = idPeticionPreviaBBDDLog;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getFkWSSolicitado() {
        return fkWSSolicitado;
    }

    public void setFkWSSolicitado(Integer fkWSSolicitado) {
        this.fkWSSolicitado = fkWSSolicitado;
    }

    public String getOposicionObligacionesTributariasHF() {
        return oposicionObligacionesTributariasHF;
    }

    public void setOposicionObligacionesTributariasHF(String oposicionObligacionesTributariasHF) {
        this.oposicionObligacionesTributariasHF = oposicionObligacionesTributariasHF;
    }

    public String getOposicionCertificadoPagoTGSS() {
        return oposicionCertificadoPagoTGSS;
    }

    public void setOposicionCertificadoPagoTGSS(String oposicionCertificadoPagoTGSS) {
        this.oposicionCertificadoPagoTGSS = oposicionCertificadoPagoTGSS;
    }

    public String getOposicionTarjetaIdentificacionFiscalEmpresa() {
        return oposicionTarjetaIdentificacionFiscalEmpresa;
    }

    public void setOposicionTarjetaIdentificacionFiscalEmpresa(String oposicionTarjetaIdentificacionFiscalEmpresa) {
        this.oposicionTarjetaIdentificacionFiscalEmpresa = oposicionTarjetaIdentificacionFiscalEmpresa;
    }

    public String getOposicionCertificadoAltaIAE() {
        return oposicionCertificadoAltaIAE;
    }

    public void setOposicionCertificadoAltaIAE(String oposicionCertificadoAltaIAE) {
        this.oposicionCertificadoAltaIAE = oposicionCertificadoAltaIAE;
    }
}
