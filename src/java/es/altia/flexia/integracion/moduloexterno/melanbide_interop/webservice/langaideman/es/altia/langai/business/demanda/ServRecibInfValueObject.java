/**
 * ServRecibInfValueObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.demanda;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.util.AuditoriaValueObject;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.technical.ValueObject;

public class ServRecibInfValueObject  extends ValueObject  implements java.io.Serializable {
    private java.lang.String UAGInscripcion;

    private java.lang.String actividadEconom;

    private java.lang.String causaAltaPres;

    private java.lang.String causaSit;

    private java.lang.String centroInscripcion;

    private java.lang.String centroServicio;

    private java.lang.String codServicio;

    private java.lang.Long corr;

    private boolean crearInformacionServicio;

    private java.lang.String edad;

    private java.lang.String fechaInicServicio;

    private java.lang.String fechaInicioSitAdm;

    private java.lang.String fechaInicioSitLab;

    private java.lang.String fechaInscripcion;

    private java.lang.String fechaRealCausa;

    private java.lang.String nacionalidad;

    private java.lang.String nivelFormInt;

    private java.lang.String perceptorPrestaciones;

    private java.lang.String perceptorRGI;

    private java.lang.String sexo;

    private java.lang.String situacionAdm;

    private java.lang.String situacionLaboral;

    public ServRecibInfValueObject() {
    }

    public ServRecibInfValueObject(
           AuditoriaValueObject auditoria,
           long objectId,
           java.lang.String UAGInscripcion,
           java.lang.String actividadEconom,
           java.lang.String causaAltaPres,
           java.lang.String causaSit,
           java.lang.String centroInscripcion,
           java.lang.String centroServicio,
           java.lang.String codServicio,
           java.lang.Long corr,
           boolean crearInformacionServicio,
           java.lang.String edad,
           java.lang.String fechaInicServicio,
           java.lang.String fechaInicioSitAdm,
           java.lang.String fechaInicioSitLab,
           java.lang.String fechaInscripcion,
           java.lang.String fechaRealCausa,
           java.lang.String nacionalidad,
           java.lang.String nivelFormInt,
           java.lang.String perceptorPrestaciones,
           java.lang.String perceptorRGI,
           java.lang.String sexo,
           java.lang.String situacionAdm,
           java.lang.String situacionLaboral) {
        super(
            auditoria,
            objectId);
        this.UAGInscripcion = UAGInscripcion;
        this.actividadEconom = actividadEconom;
        this.causaAltaPres = causaAltaPres;
        this.causaSit = causaSit;
        this.centroInscripcion = centroInscripcion;
        this.centroServicio = centroServicio;
        this.codServicio = codServicio;
        this.corr = corr;
        this.crearInformacionServicio = crearInformacionServicio;
        this.edad = edad;
        this.fechaInicServicio = fechaInicServicio;
        this.fechaInicioSitAdm = fechaInicioSitAdm;
        this.fechaInicioSitLab = fechaInicioSitLab;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaRealCausa = fechaRealCausa;
        this.nacionalidad = nacionalidad;
        this.nivelFormInt = nivelFormInt;
        this.perceptorPrestaciones = perceptorPrestaciones;
        this.perceptorRGI = perceptorRGI;
        this.sexo = sexo;
        this.situacionAdm = situacionAdm;
        this.situacionLaboral = situacionLaboral;
    }


    /**
     * Gets the UAGInscripcion value for this ServRecibInfValueObject.
     * 
     * @return UAGInscripcion
     */
    public java.lang.String getUAGInscripcion() {
        return UAGInscripcion;
    }


    /**
     * Sets the UAGInscripcion value for this ServRecibInfValueObject.
     * 
     * @param UAGInscripcion
     */
    public void setUAGInscripcion(java.lang.String UAGInscripcion) {
        this.UAGInscripcion = UAGInscripcion;
    }


    /**
     * Gets the actividadEconom value for this ServRecibInfValueObject.
     * 
     * @return actividadEconom
     */
    public java.lang.String getActividadEconom() {
        return actividadEconom;
    }


    /**
     * Sets the actividadEconom value for this ServRecibInfValueObject.
     * 
     * @param actividadEconom
     */
    public void setActividadEconom(java.lang.String actividadEconom) {
        this.actividadEconom = actividadEconom;
    }


    /**
     * Gets the causaAltaPres value for this ServRecibInfValueObject.
     * 
     * @return causaAltaPres
     */
    public java.lang.String getCausaAltaPres() {
        return causaAltaPres;
    }


    /**
     * Sets the causaAltaPres value for this ServRecibInfValueObject.
     * 
     * @param causaAltaPres
     */
    public void setCausaAltaPres(java.lang.String causaAltaPres) {
        this.causaAltaPres = causaAltaPres;
    }


    /**
     * Gets the causaSit value for this ServRecibInfValueObject.
     * 
     * @return causaSit
     */
    public java.lang.String getCausaSit() {
        return causaSit;
    }


    /**
     * Sets the causaSit value for this ServRecibInfValueObject.
     * 
     * @param causaSit
     */
    public void setCausaSit(java.lang.String causaSit) {
        this.causaSit = causaSit;
    }


    /**
     * Gets the centroInscripcion value for this ServRecibInfValueObject.
     * 
     * @return centroInscripcion
     */
    public java.lang.String getCentroInscripcion() {
        return centroInscripcion;
    }


    /**
     * Sets the centroInscripcion value for this ServRecibInfValueObject.
     * 
     * @param centroInscripcion
     */
    public void setCentroInscripcion(java.lang.String centroInscripcion) {
        this.centroInscripcion = centroInscripcion;
    }


    /**
     * Gets the centroServicio value for this ServRecibInfValueObject.
     * 
     * @return centroServicio
     */
    public java.lang.String getCentroServicio() {
        return centroServicio;
    }


    /**
     * Sets the centroServicio value for this ServRecibInfValueObject.
     * 
     * @param centroServicio
     */
    public void setCentroServicio(java.lang.String centroServicio) {
        this.centroServicio = centroServicio;
    }


    /**
     * Gets the codServicio value for this ServRecibInfValueObject.
     * 
     * @return codServicio
     */
    public java.lang.String getCodServicio() {
        return codServicio;
    }


    /**
     * Sets the codServicio value for this ServRecibInfValueObject.
     * 
     * @param codServicio
     */
    public void setCodServicio(java.lang.String codServicio) {
        this.codServicio = codServicio;
    }


    /**
     * Gets the corr value for this ServRecibInfValueObject.
     * 
     * @return corr
     */
    public java.lang.Long getCorr() {
        return corr;
    }


    /**
     * Sets the corr value for this ServRecibInfValueObject.
     * 
     * @param corr
     */
    public void setCorr(java.lang.Long corr) {
        this.corr = corr;
    }


    /**
     * Gets the crearInformacionServicio value for this ServRecibInfValueObject.
     * 
     * @return crearInformacionServicio
     */
    public boolean isCrearInformacionServicio() {
        return crearInformacionServicio;
    }


    /**
     * Sets the crearInformacionServicio value for this ServRecibInfValueObject.
     * 
     * @param crearInformacionServicio
     */
    public void setCrearInformacionServicio(boolean crearInformacionServicio) {
        this.crearInformacionServicio = crearInformacionServicio;
    }


    /**
     * Gets the edad value for this ServRecibInfValueObject.
     * 
     * @return edad
     */
    public java.lang.String getEdad() {
        return edad;
    }


    /**
     * Sets the edad value for this ServRecibInfValueObject.
     * 
     * @param edad
     */
    public void setEdad(java.lang.String edad) {
        this.edad = edad;
    }


    /**
     * Gets the fechaInicServicio value for this ServRecibInfValueObject.
     * 
     * @return fechaInicServicio
     */
    public java.lang.String getFechaInicServicio() {
        return fechaInicServicio;
    }


    /**
     * Sets the fechaInicServicio value for this ServRecibInfValueObject.
     * 
     * @param fechaInicServicio
     */
    public void setFechaInicServicio(java.lang.String fechaInicServicio) {
        this.fechaInicServicio = fechaInicServicio;
    }


    /**
     * Gets the fechaInicioSitAdm value for this ServRecibInfValueObject.
     * 
     * @return fechaInicioSitAdm
     */
    public java.lang.String getFechaInicioSitAdm() {
        return fechaInicioSitAdm;
    }


    /**
     * Sets the fechaInicioSitAdm value for this ServRecibInfValueObject.
     * 
     * @param fechaInicioSitAdm
     */
    public void setFechaInicioSitAdm(java.lang.String fechaInicioSitAdm) {
        this.fechaInicioSitAdm = fechaInicioSitAdm;
    }


    /**
     * Gets the fechaInicioSitLab value for this ServRecibInfValueObject.
     * 
     * @return fechaInicioSitLab
     */
    public java.lang.String getFechaInicioSitLab() {
        return fechaInicioSitLab;
    }


    /**
     * Sets the fechaInicioSitLab value for this ServRecibInfValueObject.
     * 
     * @param fechaInicioSitLab
     */
    public void setFechaInicioSitLab(java.lang.String fechaInicioSitLab) {
        this.fechaInicioSitLab = fechaInicioSitLab;
    }


    /**
     * Gets the fechaInscripcion value for this ServRecibInfValueObject.
     * 
     * @return fechaInscripcion
     */
    public java.lang.String getFechaInscripcion() {
        return fechaInscripcion;
    }


    /**
     * Sets the fechaInscripcion value for this ServRecibInfValueObject.
     * 
     * @param fechaInscripcion
     */
    public void setFechaInscripcion(java.lang.String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }


    /**
     * Gets the fechaRealCausa value for this ServRecibInfValueObject.
     * 
     * @return fechaRealCausa
     */
    public java.lang.String getFechaRealCausa() {
        return fechaRealCausa;
    }


    /**
     * Sets the fechaRealCausa value for this ServRecibInfValueObject.
     * 
     * @param fechaRealCausa
     */
    public void setFechaRealCausa(java.lang.String fechaRealCausa) {
        this.fechaRealCausa = fechaRealCausa;
    }


    /**
     * Gets the nacionalidad value for this ServRecibInfValueObject.
     * 
     * @return nacionalidad
     */
    public java.lang.String getNacionalidad() {
        return nacionalidad;
    }


    /**
     * Sets the nacionalidad value for this ServRecibInfValueObject.
     * 
     * @param nacionalidad
     */
    public void setNacionalidad(java.lang.String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


    /**
     * Gets the nivelFormInt value for this ServRecibInfValueObject.
     * 
     * @return nivelFormInt
     */
    public java.lang.String getNivelFormInt() {
        return nivelFormInt;
    }


    /**
     * Sets the nivelFormInt value for this ServRecibInfValueObject.
     * 
     * @param nivelFormInt
     */
    public void setNivelFormInt(java.lang.String nivelFormInt) {
        this.nivelFormInt = nivelFormInt;
    }


    /**
     * Gets the perceptorPrestaciones value for this ServRecibInfValueObject.
     * 
     * @return perceptorPrestaciones
     */
    public java.lang.String getPerceptorPrestaciones() {
        return perceptorPrestaciones;
    }


    /**
     * Sets the perceptorPrestaciones value for this ServRecibInfValueObject.
     * 
     * @param perceptorPrestaciones
     */
    public void setPerceptorPrestaciones(java.lang.String perceptorPrestaciones) {
        this.perceptorPrestaciones = perceptorPrestaciones;
    }


    /**
     * Gets the perceptorRGI value for this ServRecibInfValueObject.
     * 
     * @return perceptorRGI
     */
    public java.lang.String getPerceptorRGI() {
        return perceptorRGI;
    }


    /**
     * Sets the perceptorRGI value for this ServRecibInfValueObject.
     * 
     * @param perceptorRGI
     */
    public void setPerceptorRGI(java.lang.String perceptorRGI) {
        this.perceptorRGI = perceptorRGI;
    }


    /**
     * Gets the sexo value for this ServRecibInfValueObject.
     * 
     * @return sexo
     */
    public java.lang.String getSexo() {
        return sexo;
    }


    /**
     * Sets the sexo value for this ServRecibInfValueObject.
     * 
     * @param sexo
     */
    public void setSexo(java.lang.String sexo) {
        this.sexo = sexo;
    }


    /**
     * Gets the situacionAdm value for this ServRecibInfValueObject.
     * 
     * @return situacionAdm
     */
    public java.lang.String getSituacionAdm() {
        return situacionAdm;
    }


    /**
     * Sets the situacionAdm value for this ServRecibInfValueObject.
     * 
     * @param situacionAdm
     */
    public void setSituacionAdm(java.lang.String situacionAdm) {
        this.situacionAdm = situacionAdm;
    }


    /**
     * Gets the situacionLaboral value for this ServRecibInfValueObject.
     * 
     * @return situacionLaboral
     */
    public java.lang.String getSituacionLaboral() {
        return situacionLaboral;
    }


    /**
     * Sets the situacionLaboral value for this ServRecibInfValueObject.
     * 
     * @param situacionLaboral
     */
    public void setSituacionLaboral(java.lang.String situacionLaboral) {
        this.situacionLaboral = situacionLaboral;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServRecibInfValueObject)) return false;
        ServRecibInfValueObject other = (ServRecibInfValueObject) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UAGInscripcion==null && other.getUAGInscripcion()==null) || 
             (this.UAGInscripcion!=null &&
              this.UAGInscripcion.equals(other.getUAGInscripcion()))) &&
            ((this.actividadEconom==null && other.getActividadEconom()==null) || 
             (this.actividadEconom!=null &&
              this.actividadEconom.equals(other.getActividadEconom()))) &&
            ((this.causaAltaPres==null && other.getCausaAltaPres()==null) || 
             (this.causaAltaPres!=null &&
              this.causaAltaPres.equals(other.getCausaAltaPres()))) &&
            ((this.causaSit==null && other.getCausaSit()==null) || 
             (this.causaSit!=null &&
              this.causaSit.equals(other.getCausaSit()))) &&
            ((this.centroInscripcion==null && other.getCentroInscripcion()==null) || 
             (this.centroInscripcion!=null &&
              this.centroInscripcion.equals(other.getCentroInscripcion()))) &&
            ((this.centroServicio==null && other.getCentroServicio()==null) || 
             (this.centroServicio!=null &&
              this.centroServicio.equals(other.getCentroServicio()))) &&
            ((this.codServicio==null && other.getCodServicio()==null) || 
             (this.codServicio!=null &&
              this.codServicio.equals(other.getCodServicio()))) &&
            ((this.corr==null && other.getCorr()==null) || 
             (this.corr!=null &&
              this.corr.equals(other.getCorr()))) &&
            this.crearInformacionServicio == other.isCrearInformacionServicio() &&
            ((this.edad==null && other.getEdad()==null) || 
             (this.edad!=null &&
              this.edad.equals(other.getEdad()))) &&
            ((this.fechaInicServicio==null && other.getFechaInicServicio()==null) || 
             (this.fechaInicServicio!=null &&
              this.fechaInicServicio.equals(other.getFechaInicServicio()))) &&
            ((this.fechaInicioSitAdm==null && other.getFechaInicioSitAdm()==null) || 
             (this.fechaInicioSitAdm!=null &&
              this.fechaInicioSitAdm.equals(other.getFechaInicioSitAdm()))) &&
            ((this.fechaInicioSitLab==null && other.getFechaInicioSitLab()==null) || 
             (this.fechaInicioSitLab!=null &&
              this.fechaInicioSitLab.equals(other.getFechaInicioSitLab()))) &&
            ((this.fechaInscripcion==null && other.getFechaInscripcion()==null) || 
             (this.fechaInscripcion!=null &&
              this.fechaInscripcion.equals(other.getFechaInscripcion()))) &&
            ((this.fechaRealCausa==null && other.getFechaRealCausa()==null) || 
             (this.fechaRealCausa!=null &&
              this.fechaRealCausa.equals(other.getFechaRealCausa()))) &&
            ((this.nacionalidad==null && other.getNacionalidad()==null) || 
             (this.nacionalidad!=null &&
              this.nacionalidad.equals(other.getNacionalidad()))) &&
            ((this.nivelFormInt==null && other.getNivelFormInt()==null) || 
             (this.nivelFormInt!=null &&
              this.nivelFormInt.equals(other.getNivelFormInt()))) &&
            ((this.perceptorPrestaciones==null && other.getPerceptorPrestaciones()==null) || 
             (this.perceptorPrestaciones!=null &&
              this.perceptorPrestaciones.equals(other.getPerceptorPrestaciones()))) &&
            ((this.perceptorRGI==null && other.getPerceptorRGI()==null) || 
             (this.perceptorRGI!=null &&
              this.perceptorRGI.equals(other.getPerceptorRGI()))) &&
            ((this.sexo==null && other.getSexo()==null) || 
             (this.sexo!=null &&
              this.sexo.equals(other.getSexo()))) &&
            ((this.situacionAdm==null && other.getSituacionAdm()==null) || 
             (this.situacionAdm!=null &&
              this.situacionAdm.equals(other.getSituacionAdm()))) &&
            ((this.situacionLaboral==null && other.getSituacionLaboral()==null) || 
             (this.situacionLaboral!=null &&
              this.situacionLaboral.equals(other.getSituacionLaboral())));
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
        if (getUAGInscripcion() != null) {
            _hashCode += getUAGInscripcion().hashCode();
        }
        if (getActividadEconom() != null) {
            _hashCode += getActividadEconom().hashCode();
        }
        if (getCausaAltaPres() != null) {
            _hashCode += getCausaAltaPres().hashCode();
        }
        if (getCausaSit() != null) {
            _hashCode += getCausaSit().hashCode();
        }
        if (getCentroInscripcion() != null) {
            _hashCode += getCentroInscripcion().hashCode();
        }
        if (getCentroServicio() != null) {
            _hashCode += getCentroServicio().hashCode();
        }
        if (getCodServicio() != null) {
            _hashCode += getCodServicio().hashCode();
        }
        if (getCorr() != null) {
            _hashCode += getCorr().hashCode();
        }
        _hashCode += (isCrearInformacionServicio() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getEdad() != null) {
            _hashCode += getEdad().hashCode();
        }
        if (getFechaInicServicio() != null) {
            _hashCode += getFechaInicServicio().hashCode();
        }
        if (getFechaInicioSitAdm() != null) {
            _hashCode += getFechaInicioSitAdm().hashCode();
        }
        if (getFechaInicioSitLab() != null) {
            _hashCode += getFechaInicioSitLab().hashCode();
        }
        if (getFechaInscripcion() != null) {
            _hashCode += getFechaInscripcion().hashCode();
        }
        if (getFechaRealCausa() != null) {
            _hashCode += getFechaRealCausa().hashCode();
        }
        if (getNacionalidad() != null) {
            _hashCode += getNacionalidad().hashCode();
        }
        if (getNivelFormInt() != null) {
            _hashCode += getNivelFormInt().hashCode();
        }
        if (getPerceptorPrestaciones() != null) {
            _hashCode += getPerceptorPrestaciones().hashCode();
        }
        if (getPerceptorRGI() != null) {
            _hashCode += getPerceptorRGI().hashCode();
        }
        if (getSexo() != null) {
            _hashCode += getSexo().hashCode();
        }
        if (getSituacionAdm() != null) {
            _hashCode += getSituacionAdm().hashCode();
        }
        if (getSituacionLaboral() != null) {
            _hashCode += getSituacionLaboral().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServRecibInfValueObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://demanda.business.langai.altia.es", "ServRecibInfValueObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UAGInscripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UAGInscripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actividadEconom");
        elemField.setXmlName(new javax.xml.namespace.QName("", "actividadEconom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("causaAltaPres");
        elemField.setXmlName(new javax.xml.namespace.QName("", "causaAltaPres"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("causaSit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "causaSit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("centroInscripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "centroInscripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("centroServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "centroServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codServicio"));
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
        elemField.setFieldName("crearInformacionServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "crearInformacionServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "edad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaInicServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicioSitAdm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaInicioSitAdm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInicioSitLab");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaInicioSitLab"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaInscripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaInscripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaRealCausa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaRealCausa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nacionalidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nacionalidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelFormInt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nivelFormInt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("perceptorPrestaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "perceptorPrestaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("perceptorRGI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "perceptorRGI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sexo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sexo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("situacionAdm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "situacionAdm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("situacionLaboral");
        elemField.setXmlName(new javax.xml.namespace.QName("", "situacionLaboral"));
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
