/**
 * ValueObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.technical;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.util.AuditoriaValueObject;

public abstract class ValueObject  implements java.io.Serializable {
    private AuditoriaValueObject auditoria;

    private long objectId;

    public ValueObject() {
    }

    public ValueObject(
           AuditoriaValueObject auditoria,
           long objectId) {
           this.auditoria = auditoria;
           this.objectId = objectId;
    }


    /**
     * Gets the auditoria value for this ValueObject.
     * 
     * @return auditoria
     */
    public AuditoriaValueObject getAuditoria() {
        return auditoria;
    }


    /**
     * Sets the auditoria value for this ValueObject.
     * 
     * @param auditoria
     */
    public void setAuditoria(AuditoriaValueObject auditoria) {
        this.auditoria = auditoria;
    }


    /**
     * Gets the objectId value for this ValueObject.
     * 
     * @return objectId
     */
    public long getObjectId() {
        return objectId;
    }


    /**
     * Sets the objectId value for this ValueObject.
     * 
     * @param objectId
     */
    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValueObject)) return false;
        ValueObject other = (ValueObject) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.auditoria==null && other.getAuditoria()==null) || 
             (this.auditoria!=null &&
              this.auditoria.equals(other.getAuditoria()))) &&
            this.objectId == other.getObjectId();
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
        if (getAuditoria() != null) {
            _hashCode += getAuditoria().hashCode();
        }
        _hashCode += new Long(getObjectId()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValueObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://technical.altia.es", "ValueObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auditoria");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auditoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://langai.altia.es/business/util", "AuditoriaValueObject"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objectId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
