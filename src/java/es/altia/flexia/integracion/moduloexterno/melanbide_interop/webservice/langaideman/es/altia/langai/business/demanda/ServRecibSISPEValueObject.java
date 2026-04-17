/**
 * ServRecibSISPEValueObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.demanda;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.technical.ValueObjectSISPE;

public class ServRecibSISPEValueObject  extends ValueObjectSISPE  implements java.io.Serializable {
    private java.lang.String ind_bloq_clave_servs;

    private boolean ind_bloq_clave_servs_modif;

    private java.lang.String ind_bloq_servs_recibs;

    private boolean ind_bloq_servs_recibs_modif;

    private java.lang.String letra_nif;

    private java.lang.String mensaje;

    private java.lang.String num_documento;

    private java.lang.String servsRecibs_codigo;

    private boolean servsRecibs_codigo_modif;

    private java.lang.String servsRecibs_fec_fin;

    private boolean servsRecibs_fec_fin_modif;

    private java.lang.String servsRecibs_fec_ini;

    private boolean servsRecibs_fec_ini_modif;

    private boolean servsRecibs_letra_nif_modif;

    private boolean servsRecibs_num_documento_modif;

    private java.lang.String servsRecibs_num_horas;

    private boolean servsRecibs_num_horas_modif;

    private java.lang.String servsRecibs_texto;

    private boolean servsRecibs_texto_modif;

    private boolean servsRecibs_tipo_doc_modif;

    private java.lang.String servsRecibs_via_finan;

    private boolean servsRecibs_via_finan_modif;

    private boolean tieneModificacionDatos;

    private java.lang.String tipo_documento;

    public ServRecibSISPEValueObject() {
    }

    public ServRecibSISPEValueObject(
           java.lang.String UAG_entidad_gestora,
           java.lang.String accion,
           java.lang.String cod_com_origen,
           java.lang.String datos,
           java.lang.String ident_usuario,
           java.lang.String ind_niv_entidad,
           java.lang.Object[] lista_errores,
           long objectId,
           ValueObjectSISPE usuario,
           java.lang.String ind_bloq_clave_servs,
           boolean ind_bloq_clave_servs_modif,
           java.lang.String ind_bloq_servs_recibs,
           boolean ind_bloq_servs_recibs_modif,
           java.lang.String letra_nif,
           java.lang.String mensaje,
           java.lang.String num_documento,
           java.lang.String servsRecibs_codigo,
           boolean servsRecibs_codigo_modif,
           java.lang.String servsRecibs_fec_fin,
           boolean servsRecibs_fec_fin_modif,
           java.lang.String servsRecibs_fec_ini,
           boolean servsRecibs_fec_ini_modif,
           boolean servsRecibs_letra_nif_modif,
           boolean servsRecibs_num_documento_modif,
           java.lang.String servsRecibs_num_horas,
           boolean servsRecibs_num_horas_modif,
           java.lang.String servsRecibs_texto,
           boolean servsRecibs_texto_modif,
           boolean servsRecibs_tipo_doc_modif,
           java.lang.String servsRecibs_via_finan,
           boolean servsRecibs_via_finan_modif,
           boolean tieneModificacionDatos,
           java.lang.String tipo_documento) {
        super(
            UAG_entidad_gestora,
            accion,
            cod_com_origen,
            datos,
            ident_usuario,
            ind_niv_entidad,
            lista_errores,
            objectId,
            usuario);
        this.ind_bloq_clave_servs = ind_bloq_clave_servs;
        this.ind_bloq_clave_servs_modif = ind_bloq_clave_servs_modif;
        this.ind_bloq_servs_recibs = ind_bloq_servs_recibs;
        this.ind_bloq_servs_recibs_modif = ind_bloq_servs_recibs_modif;
        this.letra_nif = letra_nif;
        this.mensaje = mensaje;
        this.num_documento = num_documento;
        this.servsRecibs_codigo = servsRecibs_codigo;
        this.servsRecibs_codigo_modif = servsRecibs_codigo_modif;
        this.servsRecibs_fec_fin = servsRecibs_fec_fin;
        this.servsRecibs_fec_fin_modif = servsRecibs_fec_fin_modif;
        this.servsRecibs_fec_ini = servsRecibs_fec_ini;
        this.servsRecibs_fec_ini_modif = servsRecibs_fec_ini_modif;
        this.servsRecibs_letra_nif_modif = servsRecibs_letra_nif_modif;
        this.servsRecibs_num_documento_modif = servsRecibs_num_documento_modif;
        this.servsRecibs_num_horas = servsRecibs_num_horas;
        this.servsRecibs_num_horas_modif = servsRecibs_num_horas_modif;
        this.servsRecibs_texto = servsRecibs_texto;
        this.servsRecibs_texto_modif = servsRecibs_texto_modif;
        this.servsRecibs_tipo_doc_modif = servsRecibs_tipo_doc_modif;
        this.servsRecibs_via_finan = servsRecibs_via_finan;
        this.servsRecibs_via_finan_modif = servsRecibs_via_finan_modif;
        this.tieneModificacionDatos = tieneModificacionDatos;
        this.tipo_documento = tipo_documento;
    }


    /**
     * Gets the ind_bloq_clave_servs value for this ServRecibSISPEValueObject.
     * 
     * @return ind_bloq_clave_servs
     */
    public java.lang.String getInd_bloq_clave_servs() {
        return ind_bloq_clave_servs;
    }


    /**
     * Sets the ind_bloq_clave_servs value for this ServRecibSISPEValueObject.
     * 
     * @param ind_bloq_clave_servs
     */
    public void setInd_bloq_clave_servs(java.lang.String ind_bloq_clave_servs) {
        this.ind_bloq_clave_servs = ind_bloq_clave_servs;
    }


    /**
     * Gets the ind_bloq_clave_servs_modif value for this ServRecibSISPEValueObject.
     * 
     * @return ind_bloq_clave_servs_modif
     */
    public boolean isInd_bloq_clave_servs_modif() {
        return ind_bloq_clave_servs_modif;
    }


    /**
     * Sets the ind_bloq_clave_servs_modif value for this ServRecibSISPEValueObject.
     * 
     * @param ind_bloq_clave_servs_modif
     */
    public void setInd_bloq_clave_servs_modif(boolean ind_bloq_clave_servs_modif) {
        this.ind_bloq_clave_servs_modif = ind_bloq_clave_servs_modif;
    }


    /**
     * Gets the ind_bloq_servs_recibs value for this ServRecibSISPEValueObject.
     * 
     * @return ind_bloq_servs_recibs
     */
    public java.lang.String getInd_bloq_servs_recibs() {
        return ind_bloq_servs_recibs;
    }


    /**
     * Sets the ind_bloq_servs_recibs value for this ServRecibSISPEValueObject.
     * 
     * @param ind_bloq_servs_recibs
     */
    public void setInd_bloq_servs_recibs(java.lang.String ind_bloq_servs_recibs) {
        this.ind_bloq_servs_recibs = ind_bloq_servs_recibs;
    }


    /**
     * Gets the ind_bloq_servs_recibs_modif value for this ServRecibSISPEValueObject.
     * 
     * @return ind_bloq_servs_recibs_modif
     */
    public boolean isInd_bloq_servs_recibs_modif() {
        return ind_bloq_servs_recibs_modif;
    }


    /**
     * Sets the ind_bloq_servs_recibs_modif value for this ServRecibSISPEValueObject.
     * 
     * @param ind_bloq_servs_recibs_modif
     */
    public void setInd_bloq_servs_recibs_modif(boolean ind_bloq_servs_recibs_modif) {
        this.ind_bloq_servs_recibs_modif = ind_bloq_servs_recibs_modif;
    }


    /**
     * Gets the letra_nif value for this ServRecibSISPEValueObject.
     * 
     * @return letra_nif
     */
    public java.lang.String getLetra_nif() {
        return letra_nif;
    }


    /**
     * Sets the letra_nif value for this ServRecibSISPEValueObject.
     * 
     * @param letra_nif
     */
    public void setLetra_nif(java.lang.String letra_nif) {
        this.letra_nif = letra_nif;
    }


    /**
     * Gets the mensaje value for this ServRecibSISPEValueObject.
     * 
     * @return mensaje
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this ServRecibSISPEValueObject.
     * 
     * @param mensaje
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }


    /**
     * Gets the num_documento value for this ServRecibSISPEValueObject.
     * 
     * @return num_documento
     */
    public java.lang.String getNum_documento() {
        return num_documento;
    }


    /**
     * Sets the num_documento value for this ServRecibSISPEValueObject.
     * 
     * @param num_documento
     */
    public void setNum_documento(java.lang.String num_documento) {
        this.num_documento = num_documento;
    }


    /**
     * Gets the servsRecibs_codigo value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_codigo
     */
    public java.lang.String getServsRecibs_codigo() {
        return servsRecibs_codigo;
    }


    /**
     * Sets the servsRecibs_codigo value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_codigo
     */
    public void setServsRecibs_codigo(java.lang.String servsRecibs_codigo) {
        this.servsRecibs_codigo = servsRecibs_codigo;
    }


    /**
     * Gets the servsRecibs_codigo_modif value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_codigo_modif
     */
    public boolean isServsRecibs_codigo_modif() {
        return servsRecibs_codigo_modif;
    }


    /**
     * Sets the servsRecibs_codigo_modif value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_codigo_modif
     */
    public void setServsRecibs_codigo_modif(boolean servsRecibs_codigo_modif) {
        this.servsRecibs_codigo_modif = servsRecibs_codigo_modif;
    }


    /**
     * Gets the servsRecibs_fec_fin value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_fec_fin
     */
    public java.lang.String getServsRecibs_fec_fin() {
        return servsRecibs_fec_fin;
    }


    /**
     * Sets the servsRecibs_fec_fin value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_fec_fin
     */
    public void setServsRecibs_fec_fin(java.lang.String servsRecibs_fec_fin) {
        this.servsRecibs_fec_fin = servsRecibs_fec_fin;
    }


    /**
     * Gets the servsRecibs_fec_fin_modif value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_fec_fin_modif
     */
    public boolean isServsRecibs_fec_fin_modif() {
        return servsRecibs_fec_fin_modif;
    }


    /**
     * Sets the servsRecibs_fec_fin_modif value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_fec_fin_modif
     */
    public void setServsRecibs_fec_fin_modif(boolean servsRecibs_fec_fin_modif) {
        this.servsRecibs_fec_fin_modif = servsRecibs_fec_fin_modif;
    }


    /**
     * Gets the servsRecibs_fec_ini value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_fec_ini
     */
    public java.lang.String getServsRecibs_fec_ini() {
        return servsRecibs_fec_ini;
    }


    /**
     * Sets the servsRecibs_fec_ini value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_fec_ini
     */
    public void setServsRecibs_fec_ini(java.lang.String servsRecibs_fec_ini) {
        this.servsRecibs_fec_ini = servsRecibs_fec_ini;
    }


    /**
     * Gets the servsRecibs_fec_ini_modif value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_fec_ini_modif
     */
    public boolean isServsRecibs_fec_ini_modif() {
        return servsRecibs_fec_ini_modif;
    }


    /**
     * Sets the servsRecibs_fec_ini_modif value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_fec_ini_modif
     */
    public void setServsRecibs_fec_ini_modif(boolean servsRecibs_fec_ini_modif) {
        this.servsRecibs_fec_ini_modif = servsRecibs_fec_ini_modif;
    }


    /**
     * Gets the servsRecibs_letra_nif_modif value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_letra_nif_modif
     */
    public boolean isServsRecibs_letra_nif_modif() {
        return servsRecibs_letra_nif_modif;
    }


    /**
     * Sets the servsRecibs_letra_nif_modif value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_letra_nif_modif
     */
    public void setServsRecibs_letra_nif_modif(boolean servsRecibs_letra_nif_modif) {
        this.servsRecibs_letra_nif_modif = servsRecibs_letra_nif_modif;
    }


    /**
     * Gets the servsRecibs_num_documento_modif value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_num_documento_modif
     */
    public boolean isServsRecibs_num_documento_modif() {
        return servsRecibs_num_documento_modif;
    }


    /**
     * Sets the servsRecibs_num_documento_modif value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_num_documento_modif
     */
    public void setServsRecibs_num_documento_modif(boolean servsRecibs_num_documento_modif) {
        this.servsRecibs_num_documento_modif = servsRecibs_num_documento_modif;
    }


    /**
     * Gets the servsRecibs_num_horas value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_num_horas
     */
    public java.lang.String getServsRecibs_num_horas() {
        return servsRecibs_num_horas;
    }


    /**
     * Sets the servsRecibs_num_horas value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_num_horas
     */
    public void setServsRecibs_num_horas(java.lang.String servsRecibs_num_horas) {
        this.servsRecibs_num_horas = servsRecibs_num_horas;
    }


    /**
     * Gets the servsRecibs_num_horas_modif value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_num_horas_modif
     */
    public boolean isServsRecibs_num_horas_modif() {
        return servsRecibs_num_horas_modif;
    }


    /**
     * Sets the servsRecibs_num_horas_modif value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_num_horas_modif
     */
    public void setServsRecibs_num_horas_modif(boolean servsRecibs_num_horas_modif) {
        this.servsRecibs_num_horas_modif = servsRecibs_num_horas_modif;
    }


    /**
     * Gets the servsRecibs_texto value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_texto
     */
    public java.lang.String getServsRecibs_texto() {
        return servsRecibs_texto;
    }


    /**
     * Sets the servsRecibs_texto value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_texto
     */
    public void setServsRecibs_texto(java.lang.String servsRecibs_texto) {
        this.servsRecibs_texto = servsRecibs_texto;
    }


    /**
     * Gets the servsRecibs_texto_modif value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_texto_modif
     */
    public boolean isServsRecibs_texto_modif() {
        return servsRecibs_texto_modif;
    }


    /**
     * Sets the servsRecibs_texto_modif value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_texto_modif
     */
    public void setServsRecibs_texto_modif(boolean servsRecibs_texto_modif) {
        this.servsRecibs_texto_modif = servsRecibs_texto_modif;
    }


    /**
     * Gets the servsRecibs_tipo_doc_modif value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_tipo_doc_modif
     */
    public boolean isServsRecibs_tipo_doc_modif() {
        return servsRecibs_tipo_doc_modif;
    }


    /**
     * Sets the servsRecibs_tipo_doc_modif value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_tipo_doc_modif
     */
    public void setServsRecibs_tipo_doc_modif(boolean servsRecibs_tipo_doc_modif) {
        this.servsRecibs_tipo_doc_modif = servsRecibs_tipo_doc_modif;
    }


    /**
     * Gets the servsRecibs_via_finan value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_via_finan
     */
    public java.lang.String getServsRecibs_via_finan() {
        return servsRecibs_via_finan;
    }


    /**
     * Sets the servsRecibs_via_finan value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_via_finan
     */
    public void setServsRecibs_via_finan(java.lang.String servsRecibs_via_finan) {
        this.servsRecibs_via_finan = servsRecibs_via_finan;
    }


    /**
     * Gets the servsRecibs_via_finan_modif value for this ServRecibSISPEValueObject.
     * 
     * @return servsRecibs_via_finan_modif
     */
    public boolean isServsRecibs_via_finan_modif() {
        return servsRecibs_via_finan_modif;
    }


    /**
     * Sets the servsRecibs_via_finan_modif value for this ServRecibSISPEValueObject.
     * 
     * @param servsRecibs_via_finan_modif
     */
    public void setServsRecibs_via_finan_modif(boolean servsRecibs_via_finan_modif) {
        this.servsRecibs_via_finan_modif = servsRecibs_via_finan_modif;
    }


    /**
     * Gets the tieneModificacionDatos value for this ServRecibSISPEValueObject.
     * 
     * @return tieneModificacionDatos
     */
    public boolean isTieneModificacionDatos() {
        return tieneModificacionDatos;
    }


    /**
     * Sets the tieneModificacionDatos value for this ServRecibSISPEValueObject.
     * 
     * @param tieneModificacionDatos
     */
    public void setTieneModificacionDatos(boolean tieneModificacionDatos) {
        this.tieneModificacionDatos = tieneModificacionDatos;
    }


    /**
     * Gets the tipo_documento value for this ServRecibSISPEValueObject.
     * 
     * @return tipo_documento
     */
    public java.lang.String getTipo_documento() {
        return tipo_documento;
    }


    /**
     * Sets the tipo_documento value for this ServRecibSISPEValueObject.
     * 
     * @param tipo_documento
     */
    public void setTipo_documento(java.lang.String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServRecibSISPEValueObject)) return false;
        ServRecibSISPEValueObject other = (ServRecibSISPEValueObject) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ind_bloq_clave_servs==null && other.getInd_bloq_clave_servs()==null) || 
             (this.ind_bloq_clave_servs!=null &&
              this.ind_bloq_clave_servs.equals(other.getInd_bloq_clave_servs()))) &&
            this.ind_bloq_clave_servs_modif == other.isInd_bloq_clave_servs_modif() &&
            ((this.ind_bloq_servs_recibs==null && other.getInd_bloq_servs_recibs()==null) || 
             (this.ind_bloq_servs_recibs!=null &&
              this.ind_bloq_servs_recibs.equals(other.getInd_bloq_servs_recibs()))) &&
            this.ind_bloq_servs_recibs_modif == other.isInd_bloq_servs_recibs_modif() &&
            ((this.letra_nif==null && other.getLetra_nif()==null) || 
             (this.letra_nif!=null &&
              this.letra_nif.equals(other.getLetra_nif()))) &&
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje()))) &&
            ((this.num_documento==null && other.getNum_documento()==null) || 
             (this.num_documento!=null &&
              this.num_documento.equals(other.getNum_documento()))) &&
            ((this.servsRecibs_codigo==null && other.getServsRecibs_codigo()==null) || 
             (this.servsRecibs_codigo!=null &&
              this.servsRecibs_codigo.equals(other.getServsRecibs_codigo()))) &&
            this.servsRecibs_codigo_modif == other.isServsRecibs_codigo_modif() &&
            ((this.servsRecibs_fec_fin==null && other.getServsRecibs_fec_fin()==null) || 
             (this.servsRecibs_fec_fin!=null &&
              this.servsRecibs_fec_fin.equals(other.getServsRecibs_fec_fin()))) &&
            this.servsRecibs_fec_fin_modif == other.isServsRecibs_fec_fin_modif() &&
            ((this.servsRecibs_fec_ini==null && other.getServsRecibs_fec_ini()==null) || 
             (this.servsRecibs_fec_ini!=null &&
              this.servsRecibs_fec_ini.equals(other.getServsRecibs_fec_ini()))) &&
            this.servsRecibs_fec_ini_modif == other.isServsRecibs_fec_ini_modif() &&
            this.servsRecibs_letra_nif_modif == other.isServsRecibs_letra_nif_modif() &&
            this.servsRecibs_num_documento_modif == other.isServsRecibs_num_documento_modif() &&
            ((this.servsRecibs_num_horas==null && other.getServsRecibs_num_horas()==null) || 
             (this.servsRecibs_num_horas!=null &&
              this.servsRecibs_num_horas.equals(other.getServsRecibs_num_horas()))) &&
            this.servsRecibs_num_horas_modif == other.isServsRecibs_num_horas_modif() &&
            ((this.servsRecibs_texto==null && other.getServsRecibs_texto()==null) || 
             (this.servsRecibs_texto!=null &&
              this.servsRecibs_texto.equals(other.getServsRecibs_texto()))) &&
            this.servsRecibs_texto_modif == other.isServsRecibs_texto_modif() &&
            this.servsRecibs_tipo_doc_modif == other.isServsRecibs_tipo_doc_modif() &&
            ((this.servsRecibs_via_finan==null && other.getServsRecibs_via_finan()==null) || 
             (this.servsRecibs_via_finan!=null &&
              this.servsRecibs_via_finan.equals(other.getServsRecibs_via_finan()))) &&
            this.servsRecibs_via_finan_modif == other.isServsRecibs_via_finan_modif() &&
            this.tieneModificacionDatos == other.isTieneModificacionDatos() &&
            ((this.tipo_documento==null && other.getTipo_documento()==null) || 
             (this.tipo_documento!=null &&
              this.tipo_documento.equals(other.getTipo_documento())));
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
        if (getInd_bloq_clave_servs() != null) {
            _hashCode += getInd_bloq_clave_servs().hashCode();
        }
        _hashCode += (isInd_bloq_clave_servs_modif() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getInd_bloq_servs_recibs() != null) {
            _hashCode += getInd_bloq_servs_recibs().hashCode();
        }
        _hashCode += (isInd_bloq_servs_recibs_modif() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getLetra_nif() != null) {
            _hashCode += getLetra_nif().hashCode();
        }
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
        }
        if (getNum_documento() != null) {
            _hashCode += getNum_documento().hashCode();
        }
        if (getServsRecibs_codigo() != null) {
            _hashCode += getServsRecibs_codigo().hashCode();
        }
        _hashCode += (isServsRecibs_codigo_modif() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getServsRecibs_fec_fin() != null) {
            _hashCode += getServsRecibs_fec_fin().hashCode();
        }
        _hashCode += (isServsRecibs_fec_fin_modif() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getServsRecibs_fec_ini() != null) {
            _hashCode += getServsRecibs_fec_ini().hashCode();
        }
        _hashCode += (isServsRecibs_fec_ini_modif() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isServsRecibs_letra_nif_modif() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isServsRecibs_num_documento_modif() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getServsRecibs_num_horas() != null) {
            _hashCode += getServsRecibs_num_horas().hashCode();
        }
        _hashCode += (isServsRecibs_num_horas_modif() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getServsRecibs_texto() != null) {
            _hashCode += getServsRecibs_texto().hashCode();
        }
        _hashCode += (isServsRecibs_texto_modif() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isServsRecibs_tipo_doc_modif() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getServsRecibs_via_finan() != null) {
            _hashCode += getServsRecibs_via_finan().hashCode();
        }
        _hashCode += (isServsRecibs_via_finan_modif() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isTieneModificacionDatos() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTipo_documento() != null) {
            _hashCode += getTipo_documento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServRecibSISPEValueObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://demanda.business.langai.altia.es", "ServRecibSISPEValueObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ind_bloq_clave_servs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ind_bloq_clave_servs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ind_bloq_clave_servs_modif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ind_bloq_clave_servs_modif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ind_bloq_servs_recibs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ind_bloq_servs_recibs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ind_bloq_servs_recibs_modif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ind_bloq_servs_recibs_modif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("letra_nif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "letra_nif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("num_documento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "num_documento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_codigo_modif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_codigo_modif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_fec_fin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_fec_fin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_fec_fin_modif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_fec_fin_modif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_fec_ini");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_fec_ini"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_fec_ini_modif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_fec_ini_modif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_letra_nif_modif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_letra_nif_modif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_num_documento_modif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_num_documento_modif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_num_horas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_num_horas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_num_horas_modif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_num_horas_modif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_texto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_texto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_texto_modif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_texto_modif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_tipo_doc_modif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_tipo_doc_modif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_via_finan");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_via_finan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servsRecibs_via_finan_modif");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servsRecibs_via_finan_modif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tieneModificacionDatos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tieneModificacionDatos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo_documento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipo_documento"));
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
