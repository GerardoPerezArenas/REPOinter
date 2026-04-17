
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bajaForOcup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bajaForOcup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cod_centro_usu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_ubic_usu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="num_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dem_foocu_ind_tifo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gen_es_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dem_foocu_fam_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dem_foocu_especialidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idioma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bajaForOcup", propOrder = {
    "codCentroUsu",
    "codUbicUsu",
    "numDoc",
    "tipoDoc",
    "demFoocuIndTifo",
    "genEsCod",
    "demFoocuFamCod",
    "demFoocuEspecialidad",
    "idioma"
})
public class BajaForOcup {

    @XmlElement(name = "cod_centro_usu")
    protected String codCentroUsu;
    @XmlElement(name = "cod_ubic_usu")
    protected String codUbicUsu;
    @XmlElement(name = "num_doc")
    protected String numDoc;
    @XmlElement(name = "tipo_doc")
    protected String tipoDoc;
    @XmlElement(name = "dem_foocu_ind_tifo")
    protected String demFoocuIndTifo;
    @XmlElement(name = "gen_es_cod")
    protected String genEsCod;
    @XmlElement(name = "dem_foocu_fam_cod")
    protected String demFoocuFamCod;
    @XmlElement(name = "dem_foocu_especialidad")
    protected String demFoocuEspecialidad;
    protected String idioma;

    /**
     * Gets the value of the codCentroUsu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodCentroUsu() {
        return codCentroUsu;
    }

    /**
     * Sets the value of the codCentroUsu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodCentroUsu(String value) {
        this.codCentroUsu = value;
    }

    /**
     * Gets the value of the codUbicUsu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodUbicUsu() {
        return codUbicUsu;
    }

    /**
     * Sets the value of the codUbicUsu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodUbicUsu(String value) {
        this.codUbicUsu = value;
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
     * Gets the value of the demFoocuIndTifo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemFoocuIndTifo() {
        return demFoocuIndTifo;
    }

    /**
     * Sets the value of the demFoocuIndTifo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemFoocuIndTifo(String value) {
        this.demFoocuIndTifo = value;
    }

    /**
     * Gets the value of the genEsCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenEsCod() {
        return genEsCod;
    }

    /**
     * Sets the value of the genEsCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenEsCod(String value) {
        this.genEsCod = value;
    }

    /**
     * Gets the value of the demFoocuFamCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemFoocuFamCod() {
        return demFoocuFamCod;
    }

    /**
     * Sets the value of the demFoocuFamCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemFoocuFamCod(String value) {
        this.demFoocuFamCod = value;
    }

    /**
     * Gets the value of the demFoocuEspecialidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemFoocuEspecialidad() {
        return demFoocuEspecialidad;
    }

    /**
     * Sets the value of the demFoocuEspecialidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemFoocuEspecialidad(String value) {
        this.demFoocuEspecialidad = value;
    }

    /**
     * Gets the value of the idioma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Sets the value of the idioma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdioma(String value) {
        this.idioma = value;
    }

}
