
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for servRecibInfValueObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="servRecibInfValueObject">
 *   &lt;complexContent>
 *     &lt;extension base="{http://langaiDemanda.services.langaiWS.es/}valueObject">
 *       &lt;sequence>
 *         &lt;element name="actividadEconom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="causaAltaPres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="causaSit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="centroInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="centroServicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codServicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoPostal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corr" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="crearInformacionServicio" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="edad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="esDemandanteGJ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaInicServicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaInicioSitAdm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaInicioSitLab" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaRealCausa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nivelFormInt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="perceptorPrestaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="perceptorRGI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="situacionAdm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="situacionLaboral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UAGInscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "servRecibInfValueObject", propOrder = {
    "actividadEconom",
    "causaAltaPres",
    "causaSit",
    "centroInscripcion",
    "centroServicio",
    "codServicio",
    "codigoPostal",
    "corr",
    "crearInformacionServicio",
    "edad",
    "esDemandanteGJ",
    "fechaInicServicio",
    "fechaInicioSitAdm",
    "fechaInicioSitLab",
    "fechaInscripcion",
    "fechaRealCausa",
    "nacionalidad",
    "nivelFormInt",
    "numDoc",
    "perceptorPrestaciones",
    "perceptorRGI",
    "sexo",
    "situacionAdm",
    "situacionLaboral",
    "tipoDoc",
    "uagInscripcion"
})
public class ServRecibInfValueObject
    extends ValueObject
{

    protected String actividadEconom;
    protected String causaAltaPres;
    protected String causaSit;
    protected String centroInscripcion;
    protected String centroServicio;
    protected String codServicio;
    protected String codigoPostal;
    protected Long corr;
    protected boolean crearInformacionServicio;
    protected String edad;
    protected String esDemandanteGJ;
    protected String fechaInicServicio;
    protected String fechaInicioSitAdm;
    protected String fechaInicioSitLab;
    protected String fechaInscripcion;
    protected String fechaRealCausa;
    protected String nacionalidad;
    protected String nivelFormInt;
    protected String numDoc;
    protected String perceptorPrestaciones;
    protected String perceptorRGI;
    protected String sexo;
    protected String situacionAdm;
    protected String situacionLaboral;
    protected String tipoDoc;
    @XmlElement(name = "UAGInscripcion")
    protected String uagInscripcion;

    /**
     * Gets the value of the actividadEconom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividadEconom() {
        return actividadEconom;
    }

    /**
     * Sets the value of the actividadEconom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividadEconom(String value) {
        this.actividadEconom = value;
    }

    /**
     * Gets the value of the causaAltaPres property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausaAltaPres() {
        return causaAltaPres;
    }

    /**
     * Sets the value of the causaAltaPres property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausaAltaPres(String value) {
        this.causaAltaPres = value;
    }

    /**
     * Gets the value of the causaSit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausaSit() {
        return causaSit;
    }

    /**
     * Sets the value of the causaSit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausaSit(String value) {
        this.causaSit = value;
    }

    /**
     * Gets the value of the centroInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentroInscripcion() {
        return centroInscripcion;
    }

    /**
     * Sets the value of the centroInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentroInscripcion(String value) {
        this.centroInscripcion = value;
    }

    /**
     * Gets the value of the centroServicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentroServicio() {
        return centroServicio;
    }

    /**
     * Sets the value of the centroServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentroServicio(String value) {
        this.centroServicio = value;
    }

    /**
     * Gets the value of the codServicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodServicio() {
        return codServicio;
    }

    /**
     * Sets the value of the codServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodServicio(String value) {
        this.codServicio = value;
    }

    /**
     * Gets the value of the codigoPostal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Sets the value of the codigoPostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPostal(String value) {
        this.codigoPostal = value;
    }

    /**
     * Gets the value of the corr property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCorr() {
        return corr;
    }

    /**
     * Sets the value of the corr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCorr(Long value) {
        this.corr = value;
    }

    /**
     * Gets the value of the crearInformacionServicio property.
     * 
     */
    public boolean isCrearInformacionServicio() {
        return crearInformacionServicio;
    }

    /**
     * Sets the value of the crearInformacionServicio property.
     * 
     */
    public void setCrearInformacionServicio(boolean value) {
        this.crearInformacionServicio = value;
    }

    /**
     * Gets the value of the edad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEdad() {
        return edad;
    }

    /**
     * Sets the value of the edad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEdad(String value) {
        this.edad = value;
    }

    /**
     * Gets the value of the esDemandanteGJ property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsDemandanteGJ() {
        return esDemandanteGJ;
    }

    /**
     * Sets the value of the esDemandanteGJ property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsDemandanteGJ(String value) {
        this.esDemandanteGJ = value;
    }

    /**
     * Gets the value of the fechaInicServicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicServicio() {
        return fechaInicServicio;
    }

    /**
     * Sets the value of the fechaInicServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicServicio(String value) {
        this.fechaInicServicio = value;
    }

    /**
     * Gets the value of the fechaInicioSitAdm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicioSitAdm() {
        return fechaInicioSitAdm;
    }

    /**
     * Sets the value of the fechaInicioSitAdm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicioSitAdm(String value) {
        this.fechaInicioSitAdm = value;
    }

    /**
     * Gets the value of the fechaInicioSitLab property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicioSitLab() {
        return fechaInicioSitLab;
    }

    /**
     * Sets the value of the fechaInicioSitLab property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicioSitLab(String value) {
        this.fechaInicioSitLab = value;
    }

    /**
     * Gets the value of the fechaInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * Sets the value of the fechaInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInscripcion(String value) {
        this.fechaInscripcion = value;
    }

    /**
     * Gets the value of the fechaRealCausa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaRealCausa() {
        return fechaRealCausa;
    }

    /**
     * Sets the value of the fechaRealCausa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaRealCausa(String value) {
        this.fechaRealCausa = value;
    }

    /**
     * Gets the value of the nacionalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Sets the value of the nacionalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Gets the value of the nivelFormInt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivelFormInt() {
        return nivelFormInt;
    }

    /**
     * Sets the value of the nivelFormInt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivelFormInt(String value) {
        this.nivelFormInt = value;
    }

    /**
     * Gets the value of the numDoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDoc() {
        return numDoc;
    }

    /**
     * Sets the value of the numDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDoc(String value) {
        this.numDoc = value;
    }

    /**
     * Gets the value of the perceptorPrestaciones property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerceptorPrestaciones() {
        return perceptorPrestaciones;
    }

    /**
     * Sets the value of the perceptorPrestaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerceptorPrestaciones(String value) {
        this.perceptorPrestaciones = value;
    }

    /**
     * Gets the value of the perceptorRGI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerceptorRGI() {
        return perceptorRGI;
    }

    /**
     * Sets the value of the perceptorRGI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerceptorRGI(String value) {
        this.perceptorRGI = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Gets the value of the situacionAdm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSituacionAdm() {
        return situacionAdm;
    }

    /**
     * Sets the value of the situacionAdm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSituacionAdm(String value) {
        this.situacionAdm = value;
    }

    /**
     * Gets the value of the situacionLaboral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSituacionLaboral() {
        return situacionLaboral;
    }

    /**
     * Sets the value of the situacionLaboral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSituacionLaboral(String value) {
        this.situacionLaboral = value;
    }

    /**
     * Gets the value of the tipoDoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDoc() {
        return tipoDoc;
    }

    /**
     * Sets the value of the tipoDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDoc(String value) {
        this.tipoDoc = value;
    }

    /**
     * Gets the value of the uagInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUAGInscripcion() {
        return uagInscripcion;
    }

    /**
     * Sets the value of the uagInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUAGInscripcion(String value) {
        this.uagInscripcion = value;
    }

}
