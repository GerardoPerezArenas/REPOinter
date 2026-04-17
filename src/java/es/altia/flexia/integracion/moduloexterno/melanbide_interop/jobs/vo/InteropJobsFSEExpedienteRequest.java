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
public class InteropJobsFSEExpedienteRequest {
    
    private final SimpleDateFormat formatFechaddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
    
    private int ejercicio;
    private String codigoProcedimiento;
    private String numeroExpediente;
    private Date fechaInicioExpediente;
    private Date fechaFinExpediente;
    private int estadoExpediente;  //0 Pendiente, 1 Anulado, 9 Finalizado
    private Date fechaReferenciaExpedienteConv;
    private Date fechaResolucionExpediente;
    private Date fechaInicioTramiteNotifResol;
    private Date fechaFinTramiteNotifResol;
    private String codigoServicioLanbide;
    private String codigoServicioCatalogoGV;
    private String codigoCentroUsuario;
    private String codigoUbicacionUsuario;
    private String demServCentro;
    private String tipoDocUsuario;
    private String numDocUsuario;
    private String origen;
    private String demServsResultado;
    private String numDocOrientador;
    private String demServsServicio;
    private String demServsSubservicio;
    private List<InteropJobsFSEPersonaServicio> personasServicio;

    public InteropJobsFSEExpedienteRequest() {
    }

    public InteropJobsFSEExpedienteRequest(int ejercicio, String codigoProcedimiento, String numeroExpediente, Date fechaInicioExpediente, Date fechaFinExpediente, int estadoExpediente,Date fechaReferenciaExpedienteConv,Date fechaResolucionExpediente, Date fechaInicioTramiteNotifResol,Date fechaFinTramiteNotifResol) {
        this.ejercicio = ejercicio;
        this.codigoProcedimiento = codigoProcedimiento;
        this.numeroExpediente = numeroExpediente;
        this.fechaInicioExpediente = fechaInicioExpediente;
        this.fechaFinExpediente = fechaFinExpediente;
        this.estadoExpediente = estadoExpediente;
        this.fechaReferenciaExpedienteConv = fechaReferenciaExpedienteConv;
        this.fechaResolucionExpediente = fechaResolucionExpediente;
        this.fechaInicioTramiteNotifResol = fechaInicioTramiteNotifResol;
        this.fechaFinTramiteNotifResol = fechaFinTramiteNotifResol;
    }

    public int getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(int ejercicio) {
        this.ejercicio = ejercicio;
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

    public Date getFechaInicioExpediente() {
        return fechaInicioExpediente;
    }
    
    /**
     * * @return Fecha Inicio Expediente Formato dd/MM/yyyy
     */
    public String getFechaInicioExpedienteAsString() {
        if(fechaInicioExpediente!=null){
            return formatFechaddMMyyyy.format(fechaInicioExpediente);
        }else
            return null;
    }

    public void setFechaInicioExpediente(Date fechaInicioExpediente) {
        this.fechaInicioExpediente = fechaInicioExpediente;
    }

    public Date getFechaFinExpediente() {
        return fechaFinExpediente;
    }
    
    /**
     * * @return Fecha Fin Expediente Formato dd/MM/yyyy
     */
    public String getFechaFinExpedienteAsString() {
        if (fechaFinExpediente != null) {
            return formatFechaddMMyyyy.format(fechaFinExpediente);
        } else {
            return null;
        }
    }

    public void setFechaFinExpediente(Date fechaFinExpediente) {
        this.fechaFinExpediente = fechaFinExpediente;
    }

    public int getEstadoExpediente() {
        return estadoExpediente;
    }

    public void setEstadoExpediente(int estadoExpediente) {
        this.estadoExpediente = estadoExpediente;
    }

    public Date getFechaReferenciaExpedienteConv() {
        return fechaReferenciaExpedienteConv;
    }
    /**
     * * @return Fecha Referenia Expediente para saber la convocatoria a la que pertenece Formato dd/MM/yyyy
     */
    public String getFechaReferenciaExpedienteConvAsString() {
        if (fechaReferenciaExpedienteConv != null) {
            return formatFechaddMMyyyy.format(fechaReferenciaExpedienteConv);
        } else {
            return null;
        }
    }

    public void setFechaReferenciaExpedienteConv(Date fechaReferenciaExpedienteConv) {
        this.fechaReferenciaExpedienteConv = fechaReferenciaExpedienteConv;
    }

    public Date getFechaResolucionExpediente() {
        return fechaResolucionExpediente;
    }
    /**
     * * @return Fecha ResoucionExpediente Formato dd/MM/yyyy
     */
    public String getFechaResolucionExpedienteAsString() {
        if (fechaResolucionExpediente != null) {
            return formatFechaddMMyyyy.format(fechaResolucionExpediente);
        } else {
            return null;
        }
    }
    public void setFechaResolucionExpediente(Date fechaResolucionExpediente) {
        this.fechaResolucionExpediente = fechaResolucionExpediente;
    }
    
    public Date getFechaInicioTramiteNotifResol() {
        return fechaInicioTramiteNotifResol;
    }
    /**
     * * @return Fecha Inicio Tramite Notificacion resolucion Formato dd/MM/yyyy
     */
    public String getFechaInicioTramiteNotifResolAsString() {
        if (fechaInicioTramiteNotifResol != null) {
            return formatFechaddMMyyyy.format(fechaInicioTramiteNotifResol);
        } else {
            return null;
        }
    }
    
    public void setFechaInicioTramiteNotifResol(Date fechaInicioTramiteNotifResol) {
        this.fechaInicioTramiteNotifResol = fechaInicioTramiteNotifResol;
    }

    public Date getFechaFinTramiteNotifResol() {
        return fechaFinTramiteNotifResol;
    }
    
    /**
     * * @return Fecha Fin Tramite Notificacion resolucion Formato dd/MM/yyyy
     */
    public String getFechaFinTramiteNotifResolAsString() {
        if (fechaFinTramiteNotifResol != null) {
            return formatFechaddMMyyyy.format(fechaFinTramiteNotifResol);
        } else {
            return null;
        }
    }
    
    public void setFechaFinTramiteNotifResol(Date fechaFinTramiteNotifResol) {
        this.fechaFinTramiteNotifResol = fechaFinTramiteNotifResol;
    }
    
    public String getCodigoServicioLanbide() {
        return codigoServicioLanbide;
    }

    public void setCodigoServicioLanbide(String codigoServicioLanbide) {
        this.codigoServicioLanbide = codigoServicioLanbide;
    }

    public String getCodigoServicioCatalogoGV() {
        return codigoServicioCatalogoGV;
    }

    public void setCodigoServicioCatalogoGV(String codigoServicioCatalogoGV) {
        this.codigoServicioCatalogoGV = codigoServicioCatalogoGV;
    }

    public String getCodigoCentroUsuario() {
        return codigoCentroUsuario;
    }

    public void setCodigoCentroUsuario(String codigoCentroUsuario) {
        this.codigoCentroUsuario = codigoCentroUsuario;
    }

    public String getCodigoUbicacionUsuario() {
        return codigoUbicacionUsuario;
    }

    public void setCodigoUbicacionUsuario(String codigoUbicacionUsuario) {
        this.codigoUbicacionUsuario = codigoUbicacionUsuario;
    }

    public List<InteropJobsFSEPersonaServicio> getPersonasServicio() {
        return personasServicio;
    }

    public void setPersonasServicio(List<InteropJobsFSEPersonaServicio> personasServicio) {
        this.personasServicio = personasServicio;
    }

    public String getDemServCentro() {
        return demServCentro;
    }

    public void setDemServCentro(String demServCentro) {
        this.demServCentro = demServCentro;
    }

    public String getTipoDocUsuario() {
        return tipoDocUsuario;
    }

    public void setTipoDocUsuario(String tipoDocUsuario) {
        this.tipoDocUsuario = tipoDocUsuario;
    }

    public String getNumDocUsuario() {
        return numDocUsuario;
    }

    public void setNumDocUsuario(String numDocUsuario) {
        this.numDocUsuario = numDocUsuario;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDemServsResultado() {
        return demServsResultado;
    }

    public void setDemServsResultado(String demServsResultado) {
        this.demServsResultado = demServsResultado;
    }

    public String getNumDocOrientador() {
        return numDocOrientador;
    }

    public void setNumDocOrientador(String numDocOrientador) {
        this.numDocOrientador = numDocOrientador;
    }

    public String getDemServsServicio() {
        return demServsServicio;
    }

    public void setDemServsServicio(String demServsServicio) {
        this.demServsServicio = demServsServicio;
    }

    public String getDemServsSubservicio() {
        return demServsSubservicio;
    }

    public void setDemServsSubservicio(String demServsSubservicio) {
        this.demServsSubservicio = demServsSubservicio;
    }

    @Override
    public String toString() {
        return "InteropJobsFSEExpedienteRequest{" + "ejercicio=" + ejercicio + ", codigoProcedimiento=" + codigoProcedimiento + ", numeroExpediente=" + numeroExpediente + ", fechaInicioExpediente=" + getFechaInicioExpedienteAsString() + ", fechaFinExpediente=" + getFechaFinExpedienteAsString() + ", estadoExpediente=" + estadoExpediente + ", fechaReferenciaExpedienteConv=" + getFechaReferenciaExpedienteConvAsString() + ", fechaResolucionExpediente=" + getFechaResolucionExpedienteAsString() + ", fechaInicioTramiteNotifResol=" + getFechaInicioTramiteNotifResolAsString() + ", fechaFinTramiteNotifResol=" + getFechaFinTramiteNotifResolAsString() + ", codigoServicioLanbide=" + codigoServicioLanbide + ", codigoServicioCatalogoGV=" + codigoServicioCatalogoGV + ", codigoCentroUsuario=" + codigoCentroUsuario + ", codigoUbicacionUsuario=" + codigoUbicacionUsuario + ", demServCentro=" + demServCentro + ", tipoDocUsuario=" + tipoDocUsuario + ", numDocUsuario=" + numDocUsuario + ", origen=" + origen + ", demServResultado=" + demServsResultado + ", numDocOrientador=" + numDocOrientador + ", demServsServicio=" + demServsServicio + ", demServsSubservicio=" + demServsSubservicio + ", personasServicio=" + (personasServicio!= null ? Arrays.toString(personasServicio.toArray()) :"") + '}';
    }
    
}
