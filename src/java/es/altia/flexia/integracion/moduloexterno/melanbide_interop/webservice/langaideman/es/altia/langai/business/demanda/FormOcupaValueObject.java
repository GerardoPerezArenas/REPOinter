/**
 * FormOcupaValueObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.demanda;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.util.AuditoriaValueObject;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.technical.ValueObject;

public class FormOcupaValueObject  extends ValueObject  implements java.io.Serializable {
    private java.lang.String af_cod;

    private java.lang.Long corr;

    private java.lang.Long dem_foocu_corr;

    private java.lang.String es_cod;

    private java.lang.String esfo;

    private java.lang.String fam_cod;

    private java.lang.String fec_fin;

    private FormOcupaSISPEValueObject formOcupaSISPEInbound;

    private FormOcupaSISPEValueObject formOcupaSISPEValueObject;

    private java.lang.Integer indBloq;

    private java.lang.String ind_tifo;

    private java.lang.String inf_af_cod;

    private java.lang.String nivf_cod;

    private java.lang.String nro_horas;

    private java.lang.String num_doc;

    private java.lang.String text_libre;

    private java.lang.String tipo_doc;

    private java.lang.String todas_unids;

    private java.util.Vector unidadesComp;

    private boolean valid;

    public FormOcupaValueObject() {
    }

    public FormOcupaValueObject(
           AuditoriaValueObject auditoria,
           long objectId,
           java.lang.String af_cod,
           java.lang.Long corr,
           java.lang.Long dem_foocu_corr,
           java.lang.String es_cod,
           java.lang.String esfo,
           java.lang.String fam_cod,
           java.lang.String fec_fin,
           FormOcupaSISPEValueObject formOcupaSISPEInbound,
           FormOcupaSISPEValueObject formOcupaSISPEValueObject,
           java.lang.Integer indBloq,
           java.lang.String ind_tifo,
           java.lang.String inf_af_cod,
           java.lang.String nivf_cod,
           java.lang.String nro_horas,
           java.lang.String num_doc,
           java.lang.String text_libre,
           java.lang.String tipo_doc,
           java.lang.String todas_unids,
           java.util.Vector unidadesComp,
           boolean valid) {
        super(
            auditoria,
            objectId);
        this.af_cod = af_cod;
        this.corr = corr;
        this.dem_foocu_corr = dem_foocu_corr;
        this.es_cod = es_cod;
        this.esfo = esfo;
        this.fam_cod = fam_cod;
        this.fec_fin = fec_fin;
        this.formOcupaSISPEInbound = formOcupaSISPEInbound;
        this.formOcupaSISPEValueObject = formOcupaSISPEValueObject;
        this.indBloq = indBloq;
        this.ind_tifo = ind_tifo;
        this.inf_af_cod = inf_af_cod;
        this.nivf_cod = nivf_cod;
        this.nro_horas = nro_horas;
        this.num_doc = num_doc;
        this.text_libre = text_libre;
        this.tipo_doc = tipo_doc;
        this.todas_unids = todas_unids;
        this.unidadesComp = unidadesComp;
        this.valid = valid;
    }


    /**
     * Gets the af_cod value for this FormOcupaValueObject.
     * 
     * @return af_cod
     */
    public java.lang.String getAf_cod() {
        return af_cod;
    }


    /**
     * Sets the af_cod value for this FormOcupaValueObject.
     * 
     * @param af_cod
     */
    public void setAf_cod(java.lang.String af_cod) {
        this.af_cod = af_cod;
    }


    /**
     * Gets the corr value for this FormOcupaValueObject.
     * 
     * @return corr
     */
    public java.lang.Long getCorr() {
        return corr;
    }


    /**
     * Sets the corr value for this FormOcupaValueObject.
     * 
     * @param corr
     */
    public void setCorr(java.lang.Long corr) {
        this.corr = corr;
    }


    /**
     * Gets the dem_foocu_corr value for this FormOcupaValueObject.
     * 
     * @return dem_foocu_corr
     */
    public java.lang.Long getDem_foocu_corr() {
        return dem_foocu_corr;
    }


    /**
     * Sets the dem_foocu_corr value for this FormOcupaValueObject.
     * 
     * @param dem_foocu_corr
     */
    public void setDem_foocu_corr(java.lang.Long dem_foocu_corr) {
        this.dem_foocu_corr = dem_foocu_corr;
    }


    /**
     * Gets the es_cod value for this FormOcupaValueObject.
     * 
     * @return es_cod
     */
    public java.lang.String getEs_cod() {
        return es_cod;
    }


    /**
     * Sets the es_cod value for this FormOcupaValueObject.
     * 
     * @param es_cod
     */
    public void setEs_cod(java.lang.String es_cod) {
        this.es_cod = es_cod;
    }


    /**
     * Gets the esfo value for this FormOcupaValueObject.
     * 
     * @return esfo
     */
    public java.lang.String getEsfo() {
        return esfo;
    }


    /**
     * Sets the esfo value for this FormOcupaValueObject.
     * 
     * @param esfo
     */
    public void setEsfo(java.lang.String esfo) {
        this.esfo = esfo;
    }


    /**
     * Gets the fam_cod value for this FormOcupaValueObject.
     * 
     * @return fam_cod
     */
    public java.lang.String getFam_cod() {
        return fam_cod;
    }


    /**
     * Sets the fam_cod value for this FormOcupaValueObject.
     * 
     * @param fam_cod
     */
    public void setFam_cod(java.lang.String fam_cod) {
        this.fam_cod = fam_cod;
    }


    /**
     * Gets the fec_fin value for this FormOcupaValueObject.
     * 
     * @return fec_fin
     */
    public java.lang.String getFec_fin() {
        return fec_fin;
    }


    /**
     * Sets the fec_fin value for this FormOcupaValueObject.
     * 
     * @param fec_fin
     */
    public void setFec_fin(java.lang.String fec_fin) {
        this.fec_fin = fec_fin;
    }


    /**
     * Gets the formOcupaSISPEInbound value for this FormOcupaValueObject.
     * 
     * @return formOcupaSISPEInbound
     */
    public FormOcupaSISPEValueObject getFormOcupaSISPEInbound() {
        return formOcupaSISPEInbound;
    }


    /**
     * Sets the formOcupaSISPEInbound value for this FormOcupaValueObject.
     * 
     * @param formOcupaSISPEInbound
     */
    public void setFormOcupaSISPEInbound(FormOcupaSISPEValueObject formOcupaSISPEInbound) {
        this.formOcupaSISPEInbound = formOcupaSISPEInbound;
    }


    /**
     * Gets the formOcupaSISPEValueObject value for this FormOcupaValueObject.
     * 
     * @return formOcupaSISPEValueObject
     */
    public FormOcupaSISPEValueObject getFormOcupaSISPEValueObject() {
        return formOcupaSISPEValueObject;
    }


    /**
     * Sets the formOcupaSISPEValueObject value for this FormOcupaValueObject.
     * 
     * @param formOcupaSISPEValueObject
     */
    public void setFormOcupaSISPEValueObject(FormOcupaSISPEValueObject formOcupaSISPEValueObject) {
        this.formOcupaSISPEValueObject = formOcupaSISPEValueObject;
    }


    /**
     * Gets the indBloq value for this FormOcupaValueObject.
     * 
     * @return indBloq
     */
    public java.lang.Integer getIndBloq() {
        return indBloq;
    }


    /**
     * Sets the indBloq value for this FormOcupaValueObject.
     * 
     * @param indBloq
     */
    public void setIndBloq(java.lang.Integer indBloq) {
        this.indBloq = indBloq;
    }


    /**
     * Gets the ind_tifo value for this FormOcupaValueObject.
     * 
     * @return ind_tifo
     */
    public java.lang.String getInd_tifo() {
        return ind_tifo;
    }


    /**
     * Sets the ind_tifo value for this FormOcupaValueObject.
     * 
     * @param ind_tifo
     */
    public void setInd_tifo(java.lang.String ind_tifo) {
        this.ind_tifo = ind_tifo;
    }


    /**
     * Gets the inf_af_cod value for this FormOcupaValueObject.
     * 
     * @return inf_af_cod
     */
    public java.lang.String getInf_af_cod() {
        return inf_af_cod;
    }


    /**
     * Sets the inf_af_cod value for this FormOcupaValueObject.
     * 
     * @param inf_af_cod
     */
    public void setInf_af_cod(java.lang.String inf_af_cod) {
        this.inf_af_cod = inf_af_cod;
    }


    /**
     * Gets the nivf_cod value for this FormOcupaValueObject.
     * 
     * @return nivf_cod
     */
    public java.lang.String getNivf_cod() {
        return nivf_cod;
    }


    /**
     * Sets the nivf_cod value for this FormOcupaValueObject.
     * 
     * @param nivf_cod
     */
    public void setNivf_cod(java.lang.String nivf_cod) {
        this.nivf_cod = nivf_cod;
    }


    /**
     * Gets the nro_horas value for this FormOcupaValueObject.
     * 
     * @return nro_horas
     */
    public java.lang.String getNro_horas() {
        return nro_horas;
    }


    /**
     * Sets the nro_horas value for this FormOcupaValueObject.
     * 
     * @param nro_horas
     */
    public void setNro_horas(java.lang.String nro_horas) {
        this.nro_horas = nro_horas;
    }


    /**
     * Gets the num_doc value for this FormOcupaValueObject.
     * 
     * @return num_doc
     */
    public java.lang.String getNum_doc() {
        return num_doc;
    }


    /**
     * Sets the num_doc value for this FormOcupaValueObject.
     * 
     * @param num_doc
     */
    public void setNum_doc(java.lang.String num_doc) {
        this.num_doc = num_doc;
    }


    /**
     * Gets the text_libre value for this FormOcupaValueObject.
     * 
     * @return text_libre
     */
    public java.lang.String getText_libre() {
        return text_libre;
    }


    /**
     * Sets the text_libre value for this FormOcupaValueObject.
     * 
     * @param text_libre
     */
    public void setText_libre(java.lang.String text_libre) {
        this.text_libre = text_libre;
    }


    /**
     * Gets the tipo_doc value for this FormOcupaValueObject.
     * 
     * @return tipo_doc
     */
    public java.lang.String getTipo_doc() {
        return tipo_doc;
    }


    /**
     * Sets the tipo_doc value for this FormOcupaValueObject.
     * 
     * @param tipo_doc
     */
    public void setTipo_doc(java.lang.String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }


    /**
     * Gets the todas_unids value for this FormOcupaValueObject.
     * 
     * @return todas_unids
     */
    public java.lang.String getTodas_unids() {
        return todas_unids;
    }


    /**
     * Sets the todas_unids value for this FormOcupaValueObject.
     * 
     * @param todas_unids
     */
    public void setTodas_unids(java.lang.String todas_unids) {
        this.todas_unids = todas_unids;
    }


    /**
     * Gets the unidadesComp value for this FormOcupaValueObject.
     * 
     * @return unidadesComp
     */
    public java.util.Vector getUnidadesComp() {
        return unidadesComp;
    }


    /**
     * Sets the unidadesComp value for this FormOcupaValueObject.
     * 
     * @param unidadesComp
     */
    public void setUnidadesComp(java.util.Vector unidadesComp) {
        this.unidadesComp = unidadesComp;
    }


    /**
     * Gets the valid value for this FormOcupaValueObject.
     * 
     * @return valid
     */
    public boolean isValid() {
        return valid;
    }


    /**
     * Sets the valid value for this FormOcupaValueObject.
     * 
     * @param valid
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FormOcupaValueObject)) return false;
        FormOcupaValueObject other = (FormOcupaValueObject) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.af_cod==null && other.getAf_cod()==null) || 
             (this.af_cod!=null &&
              this.af_cod.equals(other.getAf_cod()))) &&
            ((this.corr==null && other.getCorr()==null) || 
             (this.corr!=null &&
              this.corr.equals(other.getCorr()))) &&
            ((this.dem_foocu_corr==null && other.getDem_foocu_corr()==null) || 
             (this.dem_foocu_corr!=null &&
              this.dem_foocu_corr.equals(other.getDem_foocu_corr()))) &&
            ((this.es_cod==null && other.getEs_cod()==null) || 
             (this.es_cod!=null &&
              this.es_cod.equals(other.getEs_cod()))) &&
            ((this.esfo==null && other.getEsfo()==null) || 
             (this.esfo!=null &&
              this.esfo.equals(other.getEsfo()))) &&
            ((this.fam_cod==null && other.getFam_cod()==null) || 
             (this.fam_cod!=null &&
              this.fam_cod.equals(other.getFam_cod()))) &&
            ((this.fec_fin==null && other.getFec_fin()==null) || 
             (this.fec_fin!=null &&
              this.fec_fin.equals(other.getFec_fin()))) &&
            ((this.formOcupaSISPEInbound==null && other.getFormOcupaSISPEInbound()==null) || 
             (this.formOcupaSISPEInbound!=null &&
              this.formOcupaSISPEInbound.equals(other.getFormOcupaSISPEInbound()))) &&
            ((this.formOcupaSISPEValueObject==null && other.getFormOcupaSISPEValueObject()==null) || 
             (this.formOcupaSISPEValueObject!=null &&
              this.formOcupaSISPEValueObject.equals(other.getFormOcupaSISPEValueObject()))) &&
            ((this.indBloq==null && other.getIndBloq()==null) || 
             (this.indBloq!=null &&
              this.indBloq.equals(other.getIndBloq()))) &&
            ((this.ind_tifo==null && other.getInd_tifo()==null) || 
             (this.ind_tifo!=null &&
              this.ind_tifo.equals(other.getInd_tifo()))) &&
            ((this.inf_af_cod==null && other.getInf_af_cod()==null) || 
             (this.inf_af_cod!=null &&
              this.inf_af_cod.equals(other.getInf_af_cod()))) &&
            ((this.nivf_cod==null && other.getNivf_cod()==null) || 
             (this.nivf_cod!=null &&
              this.nivf_cod.equals(other.getNivf_cod()))) &&
            ((this.nro_horas==null && other.getNro_horas()==null) || 
             (this.nro_horas!=null &&
              this.nro_horas.equals(other.getNro_horas()))) &&
            ((this.num_doc==null && other.getNum_doc()==null) || 
             (this.num_doc!=null &&
              this.num_doc.equals(other.getNum_doc()))) &&
            ((this.text_libre==null && other.getText_libre()==null) || 
             (this.text_libre!=null &&
              this.text_libre.equals(other.getText_libre()))) &&
            ((this.tipo_doc==null && other.getTipo_doc()==null) || 
             (this.tipo_doc!=null &&
              this.tipo_doc.equals(other.getTipo_doc()))) &&
            ((this.todas_unids==null && other.getTodas_unids()==null) || 
             (this.todas_unids!=null &&
              this.todas_unids.equals(other.getTodas_unids()))) &&
            ((this.unidadesComp==null && other.getUnidadesComp()==null) || 
             (this.unidadesComp!=null &&
              this.unidadesComp.equals(other.getUnidadesComp()))) &&
            this.valid == other.isValid();
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
        if (getAf_cod() != null) {
            _hashCode += getAf_cod().hashCode();
        }
        if (getCorr() != null) {
            _hashCode += getCorr().hashCode();
        }
        if (getDem_foocu_corr() != null) {
            _hashCode += getDem_foocu_corr().hashCode();
        }
        if (getEs_cod() != null) {
            _hashCode += getEs_cod().hashCode();
        }
        if (getEsfo() != null) {
            _hashCode += getEsfo().hashCode();
        }
        if (getFam_cod() != null) {
            _hashCode += getFam_cod().hashCode();
        }
        if (getFec_fin() != null) {
            _hashCode += getFec_fin().hashCode();
        }
        if (getFormOcupaSISPEInbound() != null) {
            _hashCode += getFormOcupaSISPEInbound().hashCode();
        }
        if (getFormOcupaSISPEValueObject() != null) {
            _hashCode += getFormOcupaSISPEValueObject().hashCode();
        }
        if (getIndBloq() != null) {
            _hashCode += getIndBloq().hashCode();
        }
        if (getInd_tifo() != null) {
            _hashCode += getInd_tifo().hashCode();
        }
        if (getInf_af_cod() != null) {
            _hashCode += getInf_af_cod().hashCode();
        }
        if (getNivf_cod() != null) {
            _hashCode += getNivf_cod().hashCode();
        }
        if (getNro_horas() != null) {
            _hashCode += getNro_horas().hashCode();
        }
        if (getNum_doc() != null) {
            _hashCode += getNum_doc().hashCode();
        }
        if (getText_libre() != null) {
            _hashCode += getText_libre().hashCode();
        }
        if (getTipo_doc() != null) {
            _hashCode += getTipo_doc().hashCode();
        }
        if (getTodas_unids() != null) {
            _hashCode += getTodas_unids().hashCode();
        }
        if (getUnidadesComp() != null) {
            _hashCode += getUnidadesComp().hashCode();
        }
        _hashCode += (isValid() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FormOcupaValueObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://langai.altia.es/business/demanda", "FormOcupaValueObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("af_cod");
        elemField.setXmlName(new javax.xml.namespace.QName("", "af_cod"));
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
        elemField.setFieldName("dem_foocu_corr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dem_foocu_corr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("es_cod");
        elemField.setXmlName(new javax.xml.namespace.QName("", "es_cod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fam_cod");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fam_cod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fec_fin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fec_fin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formOcupaSISPEInbound");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formOcupaSISPEInbound"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://demanda.business.langai.altia.es", "FormOcupaSISPEValueObject"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formOcupaSISPEValueObject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formOcupaSISPEValueObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://demanda.business.langai.altia.es", "FormOcupaSISPEValueObject"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indBloq");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indBloq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ind_tifo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ind_tifo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inf_af_cod");
        elemField.setXmlName(new javax.xml.namespace.QName("", "inf_af_cod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivf_cod");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nivf_cod"));
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
        elemField.setFieldName("num_doc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "num_doc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("text_libre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "text_libre"));
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
        elemField.setFieldName("todas_unids");
        elemField.setXmlName(new javax.xml.namespace.QName("", "todas_unids"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadesComp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadesComp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Vector"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
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
