
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for formComplValueObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="formComplValueObject">
 *   &lt;complexContent>
 *     &lt;extension base="{http://langaiDemanda.services.langaiWS.es/}valueObject">
 *       &lt;sequence>
 *         &lt;element name="af_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cencapv_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_curso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_inf_curso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corr" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="desc_cent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="es_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fam_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fec_fin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inf_af_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nivf_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nro_horas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="texto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "formComplValueObject", propOrder = {
    "afCod",
    "cencapvCod",
    "codCurso",
    "codInfCurso",
    "corr",
    "descCent",
    "esCod",
    "famCod",
    "fecFin",
    "infAfCod",
    "nivfCod",
    "nroHoras",
    "texto"
})
public class FormComplValueObject
    extends ValueObject
{

    @XmlElement(name = "af_cod")
    protected String afCod;
    @XmlElement(name = "cencapv_cod")
    protected String cencapvCod;
    @XmlElement(name = "cod_curso")
    protected String codCurso;
    @XmlElement(name = "cod_inf_curso")
    protected String codInfCurso;
    protected Long corr;
    @XmlElement(name = "desc_cent")
    protected String descCent;
    @XmlElement(name = "es_cod")
    protected String esCod;
    @XmlElement(name = "fam_cod")
    protected String famCod;
    @XmlElement(name = "fec_fin")
    protected String fecFin;
    @XmlElement(name = "inf_af_cod")
    protected String infAfCod;
    @XmlElement(name = "nivf_cod")
    protected String nivfCod;
    @XmlElement(name = "nro_horas")
    protected String nroHoras;
    protected String texto;

    /**
     * Gets the value of the afCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAfCod() {
        return afCod;
    }

    /**
     * Sets the value of the afCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAfCod(String value) {
        this.afCod = value;
    }

    /**
     * Gets the value of the cencapvCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCencapvCod() {
        return cencapvCod;
    }

    /**
     * Sets the value of the cencapvCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCencapvCod(String value) {
        this.cencapvCod = value;
    }

    /**
     * Gets the value of the codCurso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodCurso() {
        return codCurso;
    }

    /**
     * Sets the value of the codCurso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodCurso(String value) {
        this.codCurso = value;
    }

    /**
     * Gets the value of the codInfCurso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodInfCurso() {
        return codInfCurso;
    }

    /**
     * Sets the value of the codInfCurso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodInfCurso(String value) {
        this.codInfCurso = value;
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
     * Gets the value of the descCent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescCent() {
        return descCent;
    }

    /**
     * Sets the value of the descCent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescCent(String value) {
        this.descCent = value;
    }

    /**
     * Gets the value of the esCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsCod() {
        return esCod;
    }

    /**
     * Sets the value of the esCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsCod(String value) {
        this.esCod = value;
    }

    /**
     * Gets the value of the famCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamCod() {
        return famCod;
    }

    /**
     * Sets the value of the famCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamCod(String value) {
        this.famCod = value;
    }

    /**
     * Gets the value of the fecFin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecFin() {
        return fecFin;
    }

    /**
     * Sets the value of the fecFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecFin(String value) {
        this.fecFin = value;
    }

    /**
     * Gets the value of the infAfCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfAfCod() {
        return infAfCod;
    }

    /**
     * Sets the value of the infAfCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfAfCod(String value) {
        this.infAfCod = value;
    }

    /**
     * Gets the value of the nivfCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivfCod() {
        return nivfCod;
    }

    /**
     * Sets the value of the nivfCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivfCod(String value) {
        this.nivfCod = value;
    }

    /**
     * Gets the value of the nroHoras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroHoras() {
        return nroHoras;
    }

    /**
     * Sets the value of the nroHoras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroHoras(String value) {
        this.nroHoras = value;
    }

    /**
     * Gets the value of the texto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Sets the value of the texto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTexto(String value) {
        this.texto = value;
    }

}
