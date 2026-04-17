/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.entities;

/**
 *
 * @author INGDGC
 */
public class InteropLlamadasServiciosNisae {

    private Integer id;
    private Integer codOrganizacion;
    private String ejercicioHHFF;
    private String procedimientoHHFF;
    private String estadoExpediente;
    private String numeroExpedienteDesde;
    private String numeroExpedienteHasta;
    private String textoJsonDatosEnviados;
    private String numeroExpediente;
    private String fechaHoraEnvioPeticion;
    private String codigoEstadoSecundario;
    private String estado;
    private String descripcionEstado;
    private String resultado;
    private String textoJsonDatosRecibidos;
    private String documentoInteresado;
    private String tiempoEstimadoRespuesta;
    private String territorioHistorico;
    private String observaciones;
    private Integer idPeticionPadre;
    private Integer fkWSSolicitado;
    private String soloPeticionesEstEnProceso;
    private String ejecutarFiltroExpedientesEspecificos;
    private String fechaDesdeCVL;
    private String fechaHastaCVL;
            
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodOrganizacion() {
        return codOrganizacion;
    }

    public void setCodOrganizacion(Integer codOrganizacion) {
        this.codOrganizacion = codOrganizacion;
    }

    public String getEjercicioHHFF() {
        return ejercicioHHFF;
    }

    public void setEjercicioHHFF(String ejercicioHHFF) {
        this.ejercicioHHFF = ejercicioHHFF;
    }

    public String getProcedimientoHHFF() {
        return procedimientoHHFF;
    }

    public void setProcedimientoHHFF(String procedimientoHHFF) {
        this.procedimientoHHFF = procedimientoHHFF;
    }

    public String getEstadoExpediente() {
        return estadoExpediente;
    }

    public void setEstadoExpediente(String estadoExpediente) {
        this.estadoExpediente = estadoExpediente;
    }

    public String getNumeroExpedienteDesde() {
        return numeroExpedienteDesde;
    }

    public void setNumeroExpedienteDesde(String numeroExpedienteDesde) {
        this.numeroExpedienteDesde = numeroExpedienteDesde;
    }

    public String getNumeroExpedienteHasta() {
        return numeroExpedienteHasta;
    }

    public void setNumeroExpedienteHasta(String numeroExpedienteHasta) {
        this.numeroExpedienteHasta = numeroExpedienteHasta;
    }

    public String getTextoJsonDatosEnviados() {
        return textoJsonDatosEnviados;
    }

    public void setTextoJsonDatosEnviados(String textoJsonDatosEnviados) {
        this.textoJsonDatosEnviados = textoJsonDatosEnviados;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getFechaHoraEnvioPeticion() {
        return fechaHoraEnvioPeticion;
    }

    public void setFechaHoraEnvioPeticion(String fechaHoraEnvioPeticion) {
        this.fechaHoraEnvioPeticion = fechaHoraEnvioPeticion;
    }

    public String getCodigoEstadoSecundario() {
        return codigoEstadoSecundario;
    }
    
    public void setCodigoEstadoSecundario(String codigoEstadoSecundario) {
        this.codigoEstadoSecundario = codigoEstadoSecundario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTextoJsonDatosRecibidos() {
        return textoJsonDatosRecibidos;
    }

    public void setTextoJsonDatosRecibidos(String textoJsonDatosRecibidos) {
        this.textoJsonDatosRecibidos = textoJsonDatosRecibidos;
    }

    public String getDocumentoInteresado() {
        return documentoInteresado;
    }

    public void setDocumentoInteresado(String documentoInteresado) {
        this.documentoInteresado = documentoInteresado;
    }

    public String getTiempoEstimadoRespuesta() {
        return tiempoEstimadoRespuesta;
    }

    public void setTiempoEstimadoRespuesta(String tiempoEstimadoRespuesta) {
        this.tiempoEstimadoRespuesta = tiempoEstimadoRespuesta;
    }

    public String getTerritorioHistorico() {
        return territorioHistorico;
    }

    public void setTerritorioHistorico(String territorioHistorico) {
        this.territorioHistorico = territorioHistorico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdPeticionPadre() {
        return idPeticionPadre;
    }

    public void setIdPeticionPadre(Integer idPeticionPadre) {
        this.idPeticionPadre = idPeticionPadre;
    }

    public Integer getFkWSSolicitado() {
        return fkWSSolicitado;
    }

    public void setFkWSSolicitado(Integer fkWSSolicitado) {
        this.fkWSSolicitado = fkWSSolicitado;
    }

    public String getSoloPeticionesEstEnProceso() {
        return soloPeticionesEstEnProceso;
    }

    public void setSoloPeticionesEstEnProceso(String soloPeticionesEstEnProceso) {
        this.soloPeticionesEstEnProceso = soloPeticionesEstEnProceso;
    }

    public String getEjecutarFiltroExpedientesEspecificos() {
        return ejecutarFiltroExpedientesEspecificos;
    }

    public void setEjecutarFiltroExpedientesEspecificos(String ejecutarFiltroExpedientesEspecificos) {
        this.ejecutarFiltroExpedientesEspecificos = ejecutarFiltroExpedientesEspecificos;
    }

    public String getFechaDesdeCVL() {
        return fechaDesdeCVL;
    }

    public void setFechaDesdeCVL(String fechaDesdeCVL) {
        this.fechaDesdeCVL = fechaDesdeCVL;
    }

    public String getFechaHastaCVL() {
        return fechaHastaCVL;
    }

    public void setFechaHastaCVL(String fechaHastaCVL) {
        this.fechaHastaCVL = fechaHastaCVL;
    }

}
