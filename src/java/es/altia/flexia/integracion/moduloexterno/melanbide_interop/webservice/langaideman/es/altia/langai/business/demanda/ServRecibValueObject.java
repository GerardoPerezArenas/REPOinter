/**
 * ServRecibValueObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.demanda;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.util.AuditoriaValueObject;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.technical.ValueObject;

public class ServRecibValueObject  extends ValueObject  implements java.io.Serializable {
    private java.lang.String acc_form;

    private java.lang.String anular;

    private java.lang.String asignarItinerario;

    private java.lang.String ccaa;

    private java.lang.String cod_cen;

    private java.lang.String cod_itinerario;

    private java.lang.String cod_prov;

    private java.lang.String cod_serv;

    private java.lang.Long corr;

    private java.lang.Long corrGestor;

    private java.lang.String especialidad;

    private java.lang.String fec_fin_ofe;

    private java.lang.String fec_ofe;

    private java.lang.String fec_sol;

    private java.lang.String fech_fin;

    private java.lang.String fech_inic;

    private java.lang.String id_serv;

    private java.lang.Integer indBloq;

    private ServRecibInfValueObject informacionServicio;

    private java.lang.String mot_fin_ofe;

    private java.lang.String nro_horas;

    private java.lang.String nro_horas_fac;

    private java.lang.Long nro_min;

    private java.lang.Long nro_min_fac;

    private java.lang.String num_doc;

    private java.lang.Long num_seq;

    private java.lang.Long osOfeId;

    private java.lang.String resultado;

    private java.lang.Integer servOrden;

    private ServRecibSISPEValueObject servRecibSISPEInbound;

    private ServRecibSISPEValueObject servRecibSISPEValueObject;

    private java.lang.String servsRecibs_texto;

    private java.lang.String texto_desc;

    private java.lang.String tipoFO;

    private java.lang.String tipo_doc;

    private java.lang.String via_financ;

    public ServRecibValueObject() {
    }

    public ServRecibValueObject(
           AuditoriaValueObject auditoria,
           long objectId,
           java.lang.String acc_form,
           java.lang.String anular,
           java.lang.String asignarItinerario,
           java.lang.String ccaa,
           java.lang.String cod_cen,
           java.lang.String cod_itinerario,
           java.lang.String cod_prov,
           java.lang.String cod_serv,
           java.lang.Long corr,
           java.lang.Long corrGestor,
           java.lang.String especialidad,
           java.lang.String fec_fin_ofe,
           java.lang.String fec_ofe,
           java.lang.String fec_sol,
           java.lang.String fech_fin,
           java.lang.String fech_inic,
           java.lang.String id_serv,
           java.lang.Integer indBloq,
           ServRecibInfValueObject informacionServicio,
           java.lang.String mot_fin_ofe,
           java.lang.String nro_horas,
           java.lang.String nro_horas_fac,
           java.lang.Long nro_min,
           java.lang.Long nro_min_fac,
           java.lang.String num_doc,
           java.lang.Long num_seq,
           java.lang.Long osOfeId,
           java.lang.String resultado,
           java.lang.Integer servOrden,
           ServRecibSISPEValueObject servRecibSISPEInbound,
           ServRecibSISPEValueObject servRecibSISPEValueObject,
           java.lang.String servsRecibs_texto,
           java.lang.String texto_desc,
           java.lang.String tipoFO,
           java.lang.String tipo_doc,
           java.lang.String via_financ) {
        super(
            auditoria,
            objectId);
        this.acc_form = acc_form;
        this.anular = anular;
        this.asignarItinerario = asignarItinerario;
        this.ccaa = ccaa;
        this.cod_cen = cod_cen;
        this.cod_itinerario = cod_itinerario;
        this.cod_prov = cod_prov;
        this.cod_serv = cod_serv;
        this.corr = corr;
        this.corrGestor = corrGestor;
        this.especialidad = especialidad;
        this.fec_fin_ofe = fec_fin_ofe;
        this.fec_ofe = fec_ofe;
        this.fec_sol = fec_sol;
        this.fech_fin = fech_fin;
        this.fech_inic = fech_inic;
        this.id_serv = id_serv;
        this.indBloq = indBloq;
        this.informacionServicio = informacionServicio;
        this.mot_fin_ofe = mot_fin_ofe;
        this.nro_horas = nro_horas;
        this.nro_horas_fac = nro_horas_fac;
        this.nro_min = nro_min;
        this.nro_min_fac = nro_min_fac;
        this.num_doc = num_doc;
        this.num_seq = num_seq;
        this.osOfeId = osOfeId;
        this.resultado = resultado;
        this.servOrden = servOrden;
        this.servRecibSISPEInbound = servRecibSISPEInbound;
        this.servRecibSISPEValueObject = servRecibSISPEValueObject;
        this.servsRecibs_texto = servsRecibs_texto;
        this.texto_desc = texto_desc;
        this.tipoFO = tipoFO;
        this.tipo_doc = tipo_doc;
        this.via_financ = via_financ;
    }


    /**
     * Gets the acc_form value for this ServRecibValueObject.
     * 
     * @return acc_form
     */
    public java.lang.String getAcc_form() {
        return acc_form;
    }


    /**
     * Sets the acc_form value for this ServRecibValueObject.
     * 
     * @param acc_form
     */
    public void setAcc_form(java.lang.String acc_form) {
        this.acc_form = acc_form;
    }


    /**
     * Gets the anular value for this ServRecibValueObject.
     * 
     * @return anular
     */
    public java.lang.String getAnular() {
        return anular;
    }


    /**
     * Sets the anular value for this ServRecibValueObject.
     * 
     * @param anular
     */
    public void setAnular(java.lang.String anular) {
        this.anular = anular;
    }


    /**
     * Gets the asignarItinerario value for this ServRecibValueObject.
     * 
     * @return asignarItinerario
     */
    public java.lang.String getAsignarItinerario() {
        return asignarItinerario;
    }


    /**
     * Sets the asignarItinerario value for this ServRecibValueObject.
     * 
     * @param asignarItinerario
     */
    public void setAsignarItinerario(java.lang.String asignarItinerario) {
        this.asignarItinerario = asignarItinerario;
    }


    /**
     * Gets the ccaa value for this ServRecibValueObject.
     * 
     * @return ccaa
     */
    public java.lang.String getCcaa() {
        return ccaa;
    }


    /**
     * Sets the ccaa value for this ServRecibValueObject.
     * 
     * @param ccaa
     */
    public void setCcaa(java.lang.String ccaa) {
        this.ccaa = ccaa;
    }


    /**
     * Gets the cod_cen value for this ServRecibValueObject.
     * 
     * @return cod_cen
     */
    public java.lang.String getCod_cen() {
        return cod_cen;
    }


    /**
     * Sets the cod_cen value for this ServRecibValueObject.
     * 
     * @param cod_cen
     */
    public void setCod_cen(java.lang.String cod_cen) {
        this.cod_cen = cod_cen;
    }


    /**
     * Gets the cod_itinerario value for this ServRecibValueObject.
     * 
     * @return cod_itinerario
     */
    public java.lang.String getCod_itinerario() {
        return cod_itinerario;
    }


    /**
     * Sets the cod_itinerario value for this ServRecibValueObject.
     * 
     * @param cod_itinerario
     */
    public void setCod_itinerario(java.lang.String cod_itinerario) {
        this.cod_itinerario = cod_itinerario;
    }


    /**
     * Gets the cod_prov value for this ServRecibValueObject.
     * 
     * @return cod_prov
     */
    public java.lang.String getCod_prov() {
        return cod_prov;
    }


    /**
     * Sets the cod_prov value for this ServRecibValueObject.
     * 
     * @param cod_prov
     */
    public void setCod_prov(java.lang.String cod_prov) {
        this.cod_prov = cod_prov;
    }


    /**
     * Gets the cod_serv value for this ServRecibValueObject.
     * 
     * @return cod_serv
     */
    public java.lang.String getCod_serv() {
        return cod_serv;
    }


    /**
     * Sets the cod_serv value for this ServRecibValueObject.
     * 
     * @param cod_serv
     */
    public void setCod_serv(java.lang.String cod_serv) {
        this.cod_serv = cod_serv;
    }


    /**
     * Gets the corr value for this ServRecibValueObject.
     * 
     * @return corr
     */
    public java.lang.Long getCorr() {
        return corr;
    }


    /**
     * Sets the corr value for this ServRecibValueObject.
     * 
     * @param corr
     */
    public void setCorr(java.lang.Long corr) {
        this.corr = corr;
    }


    /**
     * Gets the corrGestor value for this ServRecibValueObject.
     * 
     * @return corrGestor
     */
    public java.lang.Long getCorrGestor() {
        return corrGestor;
    }


    /**
     * Sets the corrGestor value for this ServRecibValueObject.
     * 
     * @param corrGestor
     */
    public void setCorrGestor(java.lang.Long corrGestor) {
        this.corrGestor = corrGestor;
    }


    /**
     * Gets the especialidad value for this ServRecibValueObject.
     * 
     * @return especialidad
     */
    public java.lang.String getEspecialidad() {
        return especialidad;
    }


    /**
     * Sets the especialidad value for this ServRecibValueObject.
     * 
     * @param especialidad
     */
    public void setEspecialidad(java.lang.String especialidad) {
        this.especialidad = especialidad;
    }


    /**
     * Gets the fec_fin_ofe value for this ServRecibValueObject.
     * 
     * @return fec_fin_ofe
     */
    public java.lang.String getFec_fin_ofe() {
        return fec_fin_ofe;
    }


    /**
     * Sets the fec_fin_ofe value for this ServRecibValueObject.
     * 
     * @param fec_fin_ofe
     */
    public void setFec_fin_ofe(java.lang.String fec_fin_ofe) {
        this.fec_fin_ofe = fec_fin_ofe;
    }


    /**
     * Gets the fec_ofe value for this ServRecibValueObject.
     * 
     * @return fec_ofe
     */
    public java.lang.String getFec_ofe() {
        return fec_ofe;
    }


    /**
     * Sets the fec_ofe value for this ServRecibValueObject.
     * 
     * @param fec_ofe
     */
    public void setFec_ofe(java.lang.String fec_ofe) {
        this.fec_ofe = fec_ofe;
    }


    /**
     * Gets the fec_sol value for this ServRecibValueObject.
     * 
     * @return fec_sol
     */
    public java.lang.String getFec_sol() {
        return fec_sol;
    }


    /**
     * Sets the fec_sol value for this ServRecibValueObject.
     * 
     * @param fec_sol
     */
    public void setFec_sol(java.lang.String fec_sol) {
        this.fec_sol = fec_sol;
    }


    /**
     * Gets the fech_fin value for this ServRecibValueObject.
     * 
     * @return fech_fin
     */
    public java.lang.String getFech_fin() {
        return fech_fin;
    }


    /**
     * Sets the fech_fin value for this ServRecibValueObject.
     * 
     * @param fech_fin
     */
    public void setFech_fin(java.lang.String fech_fin) {
        this.fech_fin = fech_fin;
    }


    /**
     * Gets the fech_inic value for this ServRecibValueObject.
     * 
     * @return fech_inic
     */
    public java.lang.String getFech_inic() {
        return fech_inic;
    }


    /**
     * Sets the fech_inic value for this ServRecibValueObject.
     * 
     * @param fech_inic
     */
    public void setFech_inic(java.lang.String fech_inic) {
        this.fech_inic = fech_inic;
    }


    /**
     * Gets the id_serv value for this ServRecibValueObject.
     * 
     * @return id_serv
     */
    public java.lang.String getId_serv() {
        return id_serv;
    }


    /**
     * Sets the id_serv value for this ServRecibValueObject.
     * 
     * @param id_serv
     */
    public void setId_serv(java.lang.String id_serv) {
        this.id_serv = id_serv;
    }


    /**
     * Gets the indBloq value for this ServRecibValueObject.
     * 
     * @return indBloq
     */
    public java.lang.Integer getIndBloq() {
        return indBloq;
    }


    /**
     * Sets the indBloq value for this ServRecibValueObject.
     * 
     * @param indBloq
     */
    public void setIndBloq(java.lang.Integer indBloq) {
        this.indBloq = indBloq;
    }


    /**
     * Gets the informacionServicio value for this ServRecibValueObject.
     * 
     * @return informacionServicio
     */
    public ServRecibInfValueObject getInformacionServicio() {
        return informacionServicio;
    }


    /**
     * Sets the informacionServicio value for this ServRecibValueObject.
     * 
     * @param informacionServicio
     */
    public void setInformacionServicio(ServRecibInfValueObject informacionServicio) {
        this.informacionServicio = informacionServicio;
    }


    /**
     * Gets the mot_fin_ofe value for this ServRecibValueObject.
     * 
     * @return mot_fin_ofe
     */
    public java.lang.String getMot_fin_ofe() {
        return mot_fin_ofe;
    }


    /**
     * Sets the mot_fin_ofe value for this ServRecibValueObject.
     * 
     * @param mot_fin_ofe
     */
    public void setMot_fin_ofe(java.lang.String mot_fin_ofe) {
        this.mot_fin_ofe = mot_fin_ofe;
    }


    /**
     * Gets the nro_horas value for this ServRecibValueObject.
     * 
     * @return nro_horas
     */
    public java.lang.String getNro_horas() {
        return nro_horas;
    }


    /**
     * Sets the nro_horas value for this ServRecibValueObject.
     * 
     * @param nro_horas
     */
    public void setNro_horas(java.lang.String nro_horas) {
        this.nro_horas = nro_horas;
    }


    /**
     * Gets the nro_horas_fac value for this ServRecibValueObject.
     * 
     * @return nro_horas_fac
     */
    public java.lang.String getNro_horas_fac() {
        return nro_horas_fac;
    }


    /**
     * Sets the nro_horas_fac value for this ServRecibValueObject.
     * 
     * @param nro_horas_fac
     */
    public void setNro_horas_fac(java.lang.String nro_horas_fac) {
        this.nro_horas_fac = nro_horas_fac;
    }


    /**
     * Gets the nro_min value for this ServRecibValueObject.
     * 
     * @return nro_min
     */
    public java.lang.Long getNro_min() {
        return nro_min;
    }


    /**
     * Sets the nro_min value for this ServRecibValueObject.
     * 
     * @param nro_min
     */
    public void setNro_min(java.lang.Long nro_min) {
        this.nro_min = nro_min;
    }


    /**
     * Gets the nro_min_fac value for this ServRecibValueObject.
     * 
     * @return nro_min_fac
     */
    public java.lang.Long getNro_min_fac() {
        return nro_min_fac;
    }


    /**
     * Sets the nro_min_fac value for this ServRecibValueObject.
     * 
     * @param nro_min_fac
     */
    public void setNro_min_fac(java.lang.Long nro_min_fac) {
        this.nro_min_fac = nro_min_fac;
    }


    /**
     * Gets the num_doc value for this ServRecibValueObject.
     * 
     * @return num_doc
     */
    public java.lang.String getNum_doc() {
        return num_doc;
    }


    /**
     * Sets the num_doc value for this ServRecibValueObject.
     * 
     * @param num_doc
     */
    public void setNum_doc(java.lang.String num_doc) {
        this.num_doc = num_doc;
    }


    /**
     * Gets the num_seq value for this ServRecibValueObject.
     * 
     * @return num_seq
     */
    public java.lang.Long getNum_seq() {
        return num_seq;
    }


    /**
     * Sets the num_seq value for this ServRecibValueObject.
     * 
     * @param num_seq
     */
    public void setNum_seq(java.lang.Long num_seq) {
        this.num_seq = num_seq;
    }


    /**
     * Gets the osOfeId value for this ServRecibValueObject.
     * 
     * @return osOfeId
     */
    public java.lang.Long getOsOfeId() {
        return osOfeId;
    }


    /**
     * Sets the osOfeId value for this ServRecibValueObject.
     * 
     * @param osOfeId
     */
    public void setOsOfeId(java.lang.Long osOfeId) {
        this.osOfeId = osOfeId;
    }


    /**
     * Gets the resultado value for this ServRecibValueObject.
     * 
     * @return resultado
     */
    public java.lang.String getResultado() {
        return resultado;
    }


    /**
     * Sets the resultado value for this ServRecibValueObject.
     * 
     * @param resultado
     */
    public void setResultado(java.lang.String resultado) {
        this.resultado = resultado;
    }


    /**
     * Gets the servOrden value for this ServRecibValueObject.
     * 
     * @return servOrden
     */
    public java.lang.Integer getServOrden() {
        return servOrden;
    }


    /**
     * Sets the servOrden value for this ServRecibValueObject.
     * 
     * @param servOrden
     */
    public void setServOrden(java.lang.Integer servOrden) {
        this.servOrden = servOrden;
    }


    /**
     * Gets the servRecibSISPEInbound value for this ServRecibValueObject.
     * 
     * @return servRecibSISPEInbound
     */
    public ServRecibSISPEValueObject getServRecibSISPEInbound() {
        return servRecibSISPEInbound;
    }


    /**
     * Sets the servRecibSISPEInbound value for this ServRecibValueObject.
     * 
     * @param servRecibSISPEInbound
     */
    public void setServRecibSISPEInbound(ServRecibSISPEValueObject servRecibSISPEInbound) {
        this.servRecibSISPEInbound = servRecibSISPEInbound;
    }


    /**
     * Gets the servRecibSISPEValueObject value for this ServRecibValueObject.
     * 
     * @return servRecibSISPEValueObject
     */
    public ServRecibSISPEValueObject getServRecibSISPEValueObject() {
        return servRecibSISPEValueObject;
    }


    /**
     * Sets the servRecibSISPEValueObject value for this ServRecibValueObject.
     * 
     * @param servRecibSISPEValueObject
     */
    public void setServRecibSISPEValueObject(ServRecibSISPEValueObject servRecibSISPEValueObject) {
        this.servRecibSISPEValueObject = servRecibSISPEValueObject;
    }


    /**
     * Gets the servsRecibs_texto value for this ServRecibValueObject.
     * 
     * @return servsRecibs_texto
     */
    public java.lang.String getServsRecibs_texto() {
        return servsRecibs_texto;
    }


    /**
     * Sets the servsRecibs_texto value for this ServRecibValueObject.
     * 
     * @param servsRecibs_texto
     */
    public void setServsRecibs_texto(java.lang.String servsRecibs_texto) {
        this.servsRecibs_texto = servsRecibs_texto;
    }


    /**
     * Gets the texto_desc value for this ServRecibValueObject.
     * 
     * @return texto_desc
     */
    public java.lang.String getTexto_desc() {
        return texto_desc;
    }


    /**
     * Sets the texto_desc value for this ServRecibValueObject.
     * 
     * @param texto_desc
     */
    public void setTexto_desc(java.lang.String texto_desc) {
        this.texto_desc = texto_desc;
    }


    /**
     * Gets the tipoFO value for this ServRecibValueObject.
     * 
     * @return tipoFO
     */
    public java.lang.String getTipoFO() {
        return tipoFO;
    }


    /**
     * Sets the tipoFO value for this ServRecibValueObject.
     * 
     * @param tipoFO
     */
    public void setTipoFO(java.lang.String tipoFO) {
        this.tipoFO = tipoFO;
    }


    /**
     * Gets the tipo_doc value for this ServRecibValueObject.
     * 
     * @return tipo_doc
     */
    public java.lang.String getTipo_doc() {
        return tipo_doc;
    }


    /**
     * Sets the tipo_doc value for this ServRecibValueObject.
     * 
     * @param tipo_doc
     */
    public void setTipo_doc(java.lang.String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }


    /**
     * Gets the via_financ value for this ServRecibValueObject.
     * 
     * @return via_financ
     */
    public java.lang.String getVia_financ() {
        return via_financ;
    }


    /**
     * Sets the via_financ value for this ServRecibValueObject.
     * 
     * @param via_financ
     */
    public void setVia_financ(java.lang.String via_financ) {
        this.via_financ = via_financ;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServRecibValueObject)) return false;
        ServRecibValueObject other = (ServRecibValueObject) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.acc_form==null && other.getAcc_form()==null) || 
             (this.acc_form!=null &&
              this.acc_form.equals(other.getAcc_form()))) &&
            ((this.anular==null && other.getAnular()==null) || 
             (this.anular!=null &&
              this.anular.equals(other.getAnular()))) &&
            ((this.asignarItinerario==null && other.getAsignarItinerario()==null) || 
             (this.asignarItinerario!=null &&
              this.asignarItinerario.equals(other.getAsignarItinerario()))) &&
            ((this.ccaa==null && other.getCcaa()==null) || 
             (this.ccaa!=null &&
              this.ccaa.equals(other.getCcaa()))) &&
            ((this.cod_cen==null && other.getCod_cen()==null) || 
             (this.cod_cen!=null &&
              this.cod_cen.equals(other.getCod_cen()))) &&
            ((this.cod_itinerario==null && other.getCod_itinerario()==null) || 
             (this.cod_itinerario!=null &&
              this.cod_itinerario.equals(other.getCod_itinerario()))) &&
            ((this.cod_prov==null && other.getCod_prov()==null) || 
             (this.cod_prov!=null &&
              this.cod_prov.equals(other.getCod_prov()))) &&
            ((this.cod_serv==null && other.getCod_serv()==null) || 
             (this.cod_serv!=null &&
              this.cod_serv.equals(other.getCod_serv()))) &&
            ((this.corr==null && other.getCorr()==null) || 
             (this.corr!=null &&
              this.corr.equals(other.getCorr()))) &&
            ((this.corrGestor==null && other.getCorrGestor()==null) || 
             (this.corrGestor!=null &&
              this.corrGestor.equals(other.getCorrGestor()))) &&
            ((this.especialidad==null && other.getEspecialidad()==null) || 
             (this.especialidad!=null &&
              this.especialidad.equals(other.getEspecialidad()))) &&
            ((this.fec_fin_ofe==null && other.getFec_fin_ofe()==null) || 
             (this.fec_fin_ofe!=null &&
              this.fec_fin_ofe.equals(other.getFec_fin_ofe()))) &&
            ((this.fec_ofe==null && other.getFec_ofe()==null) || 
             (this.fec_ofe!=null &&
              this.fec_ofe.equals(other.getFec_ofe()))) &&
            ((this.fec_sol==null && other.getFec_sol()==null) || 
             (this.fec_sol!=null &&
              this.fec_sol.equals(other.getFec_sol()))) &&
            ((this.fech_fin==null && other.getFech_fin()==null) || 
             (this.fech_fin!=null &&
              this.fech_fin.equals(other.getFech_fin()))) &&
            ((this.fech_inic==null && other.getFech_inic()==null) || 
             (this.fech_inic!=null &&
              this.fech_inic.equals(other.getFech_inic()))) &&
            ((this.id_serv==null && other.getId_serv()==null) || 
             (this.id_serv!=null &&
              this.id_serv.equals(other.getId_serv()))) &&
            ((this.indBloq==null && other.getIndBloq()==null) || 
             (this.indBloq!=null &&
              this.indBloq.equals(other.getIndBloq()))) &&
            ((this.informacionServicio==null && other.getInformacionServicio()==null) || 
             (this.informacionServicio!=null &&
              this.informacionServicio.equals(other.getInformacionServicio()))) &&
            ((this.mot_fin_ofe==null && other.getMot_fin_ofe()==null) || 
             (this.mot_fin_ofe!=null &&
              this.mot_fin_ofe.equals(other.getMot_fin_ofe()))) &&
            ((this.nro_horas==null && other.getNro_horas()==null) || 
             (this.nro_horas!=null &&
              this.nro_horas.equals(other.getNro_horas()))) &&
            ((this.nro_horas_fac==null && other.getNro_horas_fac()==null) || 
             (this.nro_horas_fac!=null &&
              this.nro_horas_fac.equals(other.getNro_horas_fac()))) &&
            ((this.nro_min==null && other.getNro_min()==null) || 
             (this.nro_min!=null &&
              this.nro_min.equals(other.getNro_min()))) &&
            ((this.nro_min_fac==null && other.getNro_min_fac()==null) || 
             (this.nro_min_fac!=null &&
              this.nro_min_fac.equals(other.getNro_min_fac()))) &&
            ((this.num_doc==null && other.getNum_doc()==null) || 
             (this.num_doc!=null &&
              this.num_doc.equals(other.getNum_doc()))) &&
            ((this.num_seq==null && other.getNum_seq()==null) || 
             (this.num_seq!=null &&
              this.num_seq.equals(other.getNum_seq()))) &&
            ((this.osOfeId==null && other.getOsOfeId()==null) || 
             (this.osOfeId!=null &&
              this.osOfeId.equals(other.getOsOfeId()))) &&
            ((this.resultado==null && other.getResultado()==null) || 
             (this.resultado!=null &&
              this.resultado.equals(other.getResultado()))) &&
            ((this.servOrden==null && other.getServOrden()==null) || 
             (this.servOrden!=null &&
              this.servOrden.equals(other.getServOrden()))) &&
            ((this.servRecibSISPEInbound==null && other.getServRecibSISPEInbound()==null) || 
             (this.servRecibSISPEInbound!=null &&
              this.servRecibSISPEInbound.equals(other.getServRecibSISPEInbound()))) &&
            ((this.servRecibSISPEValueObject==null && other.getServRecibSISPEValueObject()==null) || 
             (this.servRecibSISPEValueObject!=null &&
              this.servRecibSISPEValueObject.equals(other.getServRecibSISPEValueObject()))) &&
            ((this.servsRecibs_texto==null && other.getServsRecibs_texto()==null) || 
             (this.servsRecibs_texto!=null &&
              this.servsRecibs_texto.equals(other.getServsRecibs_texto()))) &&
            ((this.texto_desc==null && other.getTexto_desc()==null) || 
             (this.texto_desc!=null &&
              this.texto_desc.equals(other.getTexto_desc()))) &&
            ((this.tipoFO==null && other.getTipoFO()==null) || 
             (this.tipoFO!=null &&
              this.tipoFO.equals(other.getTipoFO()))) &&
            ((this.tipo_doc==null && other.getTipo_doc()==null) || 
             (this.tipo_doc!=null &&
              this.tipo_doc.equals(other.getTipo_doc()))) &&
            ((this.via_financ==null && other.getVia_financ()==null) || 
             (this.via_financ!=null &&
              this.via_financ.equals(other.getVia_financ())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAcc_form() != null) {
            _hashCode += getAcc_form().hashCode();
        }
        if (getAnular() != null) {
            _hashCode += getAnular().hashCode();
        }
        if (getAsignarItinerario() != null) {
            _hashCode += getAsignarItinerario().hashCode();
        }
        if (getCcaa() != null) {
            _hashCode += getCcaa().hashCode();
        }
        if (getCod_cen() != null) {
            _hashCode += getCod_cen().hashCode();
        }
        if (getCod_itinerario() != null) {
            _hashCode += getCod_itinerario().hashCode();
        }
        if (getCod_prov() != null) {
            _hashCode += getCod_prov().hashCode();
        }
        if (getCod_serv() != null) {
            _hashCode += getCod_serv().hashCode();
        }
        if (getCorr() != null) {
            _hashCode += getCorr().hashCode();
        }
        if (getCorrGestor() != null) {
            _hashCode += getCorrGestor().hashCode();
        }
        if (getEspecialidad() != null) {
            _hashCode += getEspecialidad().hashCode();
        }
        if (getFec_fin_ofe() != null) {
            _hashCode += getFec_fin_ofe().hashCode();
        }
        if (getFec_ofe() != null) {
            _hashCode += getFec_ofe().hashCode();
        }
        if (getFec_sol() != null) {
            _hashCode += getFec_sol().hashCode();
        }
        if (getFech_fin() != null) {
            _hashCode += getFech_fin().hashCode();
        }
        if (getFech_inic() != null) {
            _hashCode += getFech_inic().hashCode();
        }
        if (getId_serv() != null) {
            _hashCode += getId_serv().hashCode();
        }
        if (getIndBloq() != null) {
            _hashCode += getIndBloq().hashCode();
        }
        if (getInformacionServicio() != null) {
            _hashCode += getInformacionServicio().hashCode();
        }
        if (getMot_fin_ofe() != null) {
            _hashCode += getMot_fin_ofe().hashCode();
        }
        if (getNro_horas() != null) {
            _hashCode += getNro_horas().hashCode();
        }
        if (getNro_horas_fac() != null) {
            _hashCode += getNro_horas_fac().hashCode();
        }
        if (getNro_min() != null) {
            _hashCode += getNro_min().hashCode();
        }
        if (getNro_min_fac() != null) {
            _hashCode += getNro_min_fac().hashCode();
        }
        if (getNum_doc() != null) {
            _hashCode += getNum_doc().hashCode();
        }
        if (getNum_seq() != null) {
            _hashCode += getNum_seq().hashCode();
        }
        if (getOsOfeId() != null) {
            _hashCode += getOsOfeId().hashCode();
        }
        if (getResultado() != null) {
            _hashCode += getResultado().hashCode();
        }
        if (getServOrden() != null) {
            _hashCode += getServOrden().hashCode();
        }
        if (getServRecibSISPEInbound() != null) {
            _hashCode += getServRecibSISPEInbound().hashCode();
        }
        if (getServRecibSISPEValueObject() != null) {
            _hashCode += getServRecibSISPEValueObject().hashCode();
        }
        if (getServsRecibs_texto() != null) {
            _hashCode += getServsRecibs_texto().hashCode();
        }
        if (getTexto_desc() != null) {
            _hashCode += getTexto_desc().hashCode();
        }
        if (getTipoFO() != null) {
            _hashCode += getTipoFO().hashCode();
        }
        if (getTipo_doc() != null) {
            _hashCode += getTipo_doc().hashCode();
        }
        if (getVia_financ() != null) {
            _hashCode += getVia_financ().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServRecibValueObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://langai.altia.es/business/demanda", "ServRecibValueObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acc_form");
        elemField.setXmlName(new javax.xml.namespace.QName("", "acc_form"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anular");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("asignarItinerario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "asignarItinerario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ccaa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ccaa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_cen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cod_cen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_itinerario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cod_itinerario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_prov");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cod_prov"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_serv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cod_serv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "corr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corrGestor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "corrGestor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("especialidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "especialidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fec_fin_ofe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fec_fin_ofe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fec_ofe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fec_ofe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fec_sol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fec_sol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fech_fin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fech_fin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fech_inic");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fech_inic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_serv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_serv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indBloq");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indBloq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("informacionServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "informacionServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://demanda.business.langai.altia.es", "ServRecibInfValueObject"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mot_fin_ofe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mot_fin_ofe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nro_horas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nro_horas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nro_horas_fac");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nro_horas_fac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nro_min");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nro_min"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nro_min_fac");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nro_min_fac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("num_doc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "num_doc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("num_seq");
        elemField.setXmlName(new javax.xml.namespace.QName("", "num_seq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("osOfeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "osOfeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "resultado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servOrden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servOrden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servRecibSISPEInbound");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servRecibSISPEInbound"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://demanda.business.langai.altia.es", "ServRecibSISPEValueObject"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servRecibSISPEValueObject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servRecibSISPEValueObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://demanda.business.langai.altia.es", "ServRecibSISPEValueObject"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_texto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_texto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("texto_desc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "texto_desc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoFO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoFO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo_doc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipo_doc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("via_financ");
        elemField.setXmlName(new javax.xml.namespace.QName("", "via_financ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
