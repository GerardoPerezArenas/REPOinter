/**
 * AuditoriaValueObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.util;

public class AuditoriaValueObject  implements java.io.Serializable {
    private java.lang.String acc_cod_accion;

    private java.lang.Long aud_corr;

    private java.lang.String aud_datos;

    private java.lang.String aud_fecha;

    private java.lang.Long aud_id_oferta;

    private java.lang.String centro_uag;

    private java.lang.String cod_centro;

    private java.lang.String cod_perfil;

    private java.lang.String cod_ubic;

    private boolean inicializarListas;

    private java.lang.String num_doc;

    private java.lang.String que;

    private java.lang.String tipo_doc;

    public AuditoriaValueObject() {
    }

    public AuditoriaValueObject(
           java.lang.String acc_cod_accion,
           java.lang.Long aud_corr,
           java.lang.String aud_datos,
           java.lang.String aud_fecha,
           java.lang.Long aud_id_oferta,
           java.lang.String centro_uag,
           java.lang.String cod_centro,
           java.lang.String cod_perfil,
           java.lang.String cod_ubic,
           boolean inicializarListas,
           java.lang.String num_doc,
           java.lang.String que,
           java.lang.String tipo_doc) {
           this.acc_cod_accion = acc_cod_accion;
           this.aud_corr = aud_corr;
           this.aud_datos = aud_datos;
           this.aud_fecha = aud_fecha;
           this.aud_id_oferta = aud_id_oferta;
           this.centro_uag = centro_uag;
           this.cod_centro = cod_centro;
           this.cod_perfil = cod_perfil;
           this.cod_ubic = cod_ubic;
           this.inicializarListas = inicializarListas;
           this.num_doc = num_doc;
           this.que = que;
           this.tipo_doc = tipo_doc;
    }


    /**
     * Gets the acc_cod_accion value for this AuditoriaValueObject.
     * 
     * @return acc_cod_accion
     */
    public java.lang.String getAcc_cod_accion() {
        return acc_cod_accion;
    }


    /**
     * Sets the acc_cod_accion value for this AuditoriaValueObject.
     * 
     * @param acc_cod_accion
     */
    public void setAcc_cod_accion(java.lang.String acc_cod_accion) {
        this.acc_cod_accion = acc_cod_accion;
    }


    /**
     * Gets the aud_corr value for this AuditoriaValueObject.
     * 
     * @return aud_corr
     */
    public java.lang.Long getAud_corr() {
        return aud_corr;
    }


    /**
     * Sets the aud_corr value for this AuditoriaValueObject.
     * 
     * @param aud_corr
     */
    public void setAud_corr(java.lang.Long aud_corr) {
        this.aud_corr = aud_corr;
    }


    /**
     * Gets the aud_datos value for this AuditoriaValueObject.
     * 
     * @return aud_datos
     */
    public java.lang.String getAud_datos() {
        return aud_datos;
    }


    /**
     * Sets the aud_datos value for this AuditoriaValueObject.
     * 
     * @param aud_datos
     */
    public void setAud_datos(java.lang.String aud_datos) {
        this.aud_datos = aud_datos;
    }


    /**
     * Gets the aud_fecha value for this AuditoriaValueObject.
     * 
     * @return aud_fecha
     */
    public java.lang.String getAud_fecha() {
        return aud_fecha;
    }


    /**
     * Sets the aud_fecha value for this AuditoriaValueObject.
     * 
     * @param aud_fecha
     */
    public void setAud_fecha(java.lang.String aud_fecha) {
        this.aud_fecha = aud_fecha;
    }


    /**
     * Gets the aud_id_oferta value for this AuditoriaValueObject.
     * 
     * @return aud_id_oferta
     */
    public java.lang.Long getAud_id_oferta() {
        return aud_id_oferta;
    }


    /**
     * Sets the aud_id_oferta value for this AuditoriaValueObject.
     * 
     * @param aud_id_oferta
     */
    public void setAud_id_oferta(java.lang.Long aud_id_oferta) {
        this.aud_id_oferta = aud_id_oferta;
    }


    /**
     * Gets the centro_uag value for this AuditoriaValueObject.
     * 
     * @return centro_uag
     */
    public java.lang.String getCentro_uag() {
        return centro_uag;
    }


    /**
     * Sets the centro_uag value for this AuditoriaValueObject.
     * 
     * @param centro_uag
     */
    public void setCentro_uag(java.lang.String centro_uag) {
        this.centro_uag = centro_uag;
    }


    /**
     * Gets the cod_centro value for this AuditoriaValueObject.
     * 
     * @return cod_centro
     */
    public java.lang.String getCod_centro() {
        return cod_centro;
    }


    /**
     * Sets the cod_centro value for this AuditoriaValueObject.
     * 
     * @param cod_centro
     */
    public void setCod_centro(java.lang.String cod_centro) {
        this.cod_centro = cod_centro;
    }


    /**
     * Gets the cod_perfil value for this AuditoriaValueObject.
     * 
     * @return cod_perfil
     */
    public java.lang.String getCod_perfil() {
        return cod_perfil;
    }


    /**
     * Sets the cod_perfil value for this AuditoriaValueObject.
     * 
     * @param cod_perfil
     */
    public void setCod_perfil(java.lang.String cod_perfil) {
        this.cod_perfil = cod_perfil;
    }


    /**
     * Gets the cod_ubic value for this AuditoriaValueObject.
     * 
     * @return cod_ubic
     */
    public java.lang.String getCod_ubic() {
        return cod_ubic;
    }


    /**
     * Sets the cod_ubic value for this AuditoriaValueObject.
     * 
     * @param cod_ubic
     */
    public void setCod_ubic(java.lang.String cod_ubic) {
        this.cod_ubic = cod_ubic;
    }


    /**
     * Gets the inicializarListas value for this AuditoriaValueObject.
     * 
     * @return inicializarListas
     */
    public boolean isInicializarListas() {
        return inicializarListas;
    }


    /**
     * Sets the inicializarListas value for this AuditoriaValueObject.
     * 
     * @param inicializarListas
     */
    public void setInicializarListas(boolean inicializarListas) {
        this.inicializarListas = inicializarListas;
    }


    /**
     * Gets the num_doc value for this AuditoriaValueObject.
     * 
     * @return num_doc
     */
    public java.lang.String getNum_doc() {
        return num_doc;
    }


    /**
     * Sets the num_doc value for this AuditoriaValueObject.
     * 
     * @param num_doc
     */
    public void setNum_doc(java.lang.String num_doc) {
        this.num_doc = num_doc;
    }


    /**
     * Gets the que value for this AuditoriaValueObject.
     * 
     * @return que
     */
    public java.lang.String getQue() {
        return que;
    }


    /**
     * Sets the que value for this AuditoriaValueObject.
     * 
     * @param que
     */
    public void setQue(java.lang.String que) {
        this.que = que;
    }


    /**
     * Gets the tipo_doc value for this AuditoriaValueObject.
     * 
     * @return tipo_doc
     */
    public java.lang.String getTipo_doc() {
        return tipo_doc;
    }


    /**
     * Sets the tipo_doc value for this AuditoriaValueObject.
     * 
     * @param tipo_doc
     */
    public void setTipo_doc(java.lang.String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AuditoriaValueObject)) return false;
        AuditoriaValueObject other = (AuditoriaValueObject) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.acc_cod_accion==null && other.getAcc_cod_accion()==null) || 
             (this.acc_cod_accion!=null &&
              this.acc_cod_accion.equals(other.getAcc_cod_accion()))) &&
            ((this.aud_corr==null && other.getAud_corr()==null) || 
             (this.aud_corr!=null &&
              this.aud_corr.equals(other.getAud_corr()))) &&
            ((this.aud_datos==null && other.getAud_datos()==null) || 
             (this.aud_datos!=null &&
              this.aud_datos.equals(other.getAud_datos()))) &&
            ((this.aud_fecha==null && other.getAud_fecha()==null) || 
             (this.aud_fecha!=null &&
              this.aud_fecha.equals(other.getAud_fecha()))) &&
            ((this.aud_id_oferta==null && other.getAud_id_oferta()==null) || 
             (this.aud_id_oferta!=null &&
              this.aud_id_oferta.equals(other.getAud_id_oferta()))) &&
            ((this.centro_uag==null && other.getCentro_uag()==null) || 
             (this.centro_uag!=null &&
              this.centro_uag.equals(other.getCentro_uag()))) &&
            ((this.cod_centro==null && other.getCod_centro()==null) || 
             (this.cod_centro!=null &&
              this.cod_centro.equals(other.getCod_centro()))) &&
            ((this.cod_perfil==null && other.getCod_perfil()==null) || 
             (this.cod_perfil!=null &&
              this.cod_perfil.equals(other.getCod_perfil()))) &&
            ((this.cod_ubic==null && other.getCod_ubic()==null) || 
             (this.cod_ubic!=null &&
              this.cod_ubic.equals(other.getCod_ubic()))) &&
            this.inicializarListas == other.isInicializarListas() &&
            ((this.num_doc==null && other.getNum_doc()==null) || 
             (this.num_doc!=null &&
              this.num_doc.equals(other.getNum_doc()))) &&
            ((this.que==null && other.getQue()==null) || 
             (this.que!=null &&
              this.que.equals(other.getQue()))) &&
            ((this.tipo_doc==null && other.getTipo_doc()==null) || 
             (this.tipo_doc!=null &&
              this.tipo_doc.equals(other.getTipo_doc())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAcc_cod_accion() != null) {
            _hashCode += getAcc_cod_accion().hashCode();
        }
        if (getAud_corr() != null) {
            _hashCode += getAud_corr().hashCode();
        }
        if (getAud_datos() != null) {
            _hashCode += getAud_datos().hashCode();
        }
        if (getAud_fecha() != null) {
            _hashCode += getAud_fecha().hashCode();
        }
        if (getAud_id_oferta() != null) {
            _hashCode += getAud_id_oferta().hashCode();
        }
        if (getCentro_uag() != null) {
            _hashCode += getCentro_uag().hashCode();
        }
        if (getCod_centro() != null) {
            _hashCode += getCod_centro().hashCode();
        }
        if (getCod_perfil() != null) {
            _hashCode += getCod_perfil().hashCode();
        }
        if (getCod_ubic() != null) {
            _hashCode += getCod_ubic().hashCode();
        }
        _hashCode += (isInicializarListas() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getNum_doc() != null) {
            _hashCode += getNum_doc().hashCode();
        }
        if (getQue() != null) {
            _hashCode += getQue().hashCode();
        }
        if (getTipo_doc() != null) {
            _hashCode += getTipo_doc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AuditoriaValueObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://langai.altia.es/business/util", "AuditoriaValueObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acc_cod_accion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "acc_cod_accion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aud_corr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aud_corr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aud_datos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aud_datos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aud_fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aud_fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aud_id_oferta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aud_id_oferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("centro_uag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "centro_uag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_centro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cod_centro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_perfil");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cod_perfil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_ubic");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cod_ubic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inicializarListas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "inicializarListas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("num_doc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "num_doc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("que");
        elemField.setXmlName(new javax.xml.namespace.QName("", "que"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo_doc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipo_doc"));
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
