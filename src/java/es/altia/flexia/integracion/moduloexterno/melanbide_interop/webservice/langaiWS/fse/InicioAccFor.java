
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Inicio_AccFor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Inicio_AccFor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="corr_demanda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_inicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_fin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_servicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="via_financiacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="especialidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accion_formativa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc_libre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_centro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_ubic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_doc_usu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Num_doc_usu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Os_ofe_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Suspender_demanda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subserv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Inicio_AccFor", propOrder = {
    "corrDemanda",
    "fechaInicio",
    "fechaFin",
    "tipoServicio",
    "viaFinanciacion",
    "especialidad",
    "accionFormativa",
    "descLibre",
    "codigoCentro",
    "codigoUbic",
    "tipoDocUsu",
    "numDocUsu",
    "osOfeId",
    "suspenderDemanda",
    "serv",
    "subserv",
    "modalidad"
})
public class InicioAccFor {

    @XmlElement(name = "corr_demanda")
    protected String corrDemanda;
    @XmlElement(name = "fecha_inicio")
    protected String fechaInicio;
    @XmlElement(name = "fecha_fin")
    protected String fechaFin;
    @XmlElement(name = "tipo_servicio")
    protected String tipoServicio;
    @XmlElement(name = "via_financiacion")
    protected String viaFinanciacion;
    protected String especialidad;
    @XmlElement(name = "accion_formativa")
    protected String accionFormativa;
    @XmlElement(name = "desc_libre")
    protected String descLibre;
    @XmlElement(name = "codigo_centro")
    protected String codigoCentro;
    @XmlElement(name = "codigo_ubic")
    protected String codigoUbic;
    @XmlElement(name = "Tipo_doc_usu")
    protected String tipoDocUsu;
    @XmlElement(name = "Num_doc_usu")
    protected String numDocUsu;
    @XmlElement(name = "Os_ofe_id")
    protected String osOfeId;
    @XmlElement(name = "Suspender_demanda")
    protected String suspenderDemanda;
    protected String serv;
    protected String subserv;
    protected String modalidad;

    /**
     * Gets the value of the corrDemanda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrDemanda() {
        return corrDemanda;
    }

    /**
     * Sets the value of the corrDemanda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrDemanda(String value) {
        this.corrDemanda = value;
    }

    /**
     * Gets the value of the fechaInicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the value of the fechaInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicio(String value) {
        this.fechaInicio = value;
    }

    /**
     * Gets the value of the fechaFin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * Sets the value of the fechaFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaFin(String value) {
        this.fechaFin = value;
    }

    /**
     * Gets the value of the tipoServicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoServicio() {
        return tipoServicio;
    }

    /**
     * Sets the value of the tipoServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoServicio(String value) {
        this.tipoServicio = value;
    }

    /**
     * Gets the value of the viaFinanciacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViaFinanciacion() {
        return viaFinanciacion;
    }

    /**
     * Sets the value of the viaFinanciacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViaFinanciacion(String value) {
        this.viaFinanciacion = value;
    }

    /**
     * Gets the value of the especialidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Sets the value of the especialidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEspecialidad(String value) {
        this.especialidad = value;
    }

    /**
     * Gets the value of the accionFormativa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccionFormativa() {
        return accionFormativa;
    }

    /**
     * Sets the value of the accionFormativa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccionFormativa(String value) {
        this.accionFormativa = value;
    }

    /**
     * Gets the value of the descLibre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescLibre() {
        return descLibre;
    }

    /**
     * Sets the value of the descLibre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescLibre(String value) {
        this.descLibre = value;
    }

    /**
     * Gets the value of the codigoCentro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCentro() {
        return codigoCentro;
    }

    /**
     * Sets the value of the codigoCentro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCentro(String value) {
        this.codigoCentro = value;
    }

    /**
     * Gets the value of the codigoUbic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoUbic() {
        return codigoUbic;
    }

    /**
     * Sets the value of the codigoUbic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoUbic(String value) {
        this.codigoUbic = value;
    }

    /**
     * Gets the value of the tipoDocUsu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocUsu() {
        return tipoDocUsu;
    }

    /**
     * Sets the value of the tipoDocUsu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocUsu(String value) {
        this.tipoDocUsu = value;
    }

    /**
     * Gets the value of the numDocUsu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDocUsu() {
        return numDocUsu;
    }

    /**
     * Sets the value of the numDocUsu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDocUsu(String value) {
        this.numDocUsu = value;
    }

    /**
     * Gets the value of the osOfeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOsOfeId() {
        return osOfeId;
    }

    /**
     * Sets the value of the osOfeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOsOfeId(String value) {
        this.osOfeId = value;
    }

    /**
     * Gets the value of the suspenderDemanda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuspenderDemanda() {
        return suspenderDemanda;
    }

    /**
     * Sets the value of the suspenderDemanda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuspenderDemanda(String value) {
        this.suspenderDemanda = value;
    }

    /**
     * Gets the value of the serv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServ() {
        return serv;
    }

    /**
     * Sets the value of the serv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServ(String value) {
        this.serv = value;
    }

    /**
     * Gets the value of the subserv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubserv() {
        return subserv;
    }

    /**
     * Sets the value of the subserv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubserv(String value) {
        this.subserv = value;
    }

    /**
     * Gets the value of the modalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModalidad() {
        return modalidad;
    }

    /**
     * Sets the value of the modalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModalidad(String value) {
        this.modalidad = value;
    }

}
