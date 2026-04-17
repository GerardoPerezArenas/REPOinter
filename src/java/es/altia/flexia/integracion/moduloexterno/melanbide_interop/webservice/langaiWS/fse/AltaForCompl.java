
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for altaForCompl complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="altaForCompl">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cod_centro_usu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_ubic_usu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="num_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gen_nivf_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gen_Es_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gen_fam_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dem_focom_fec_fin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dem_focom_nro_horas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dem_focom_texto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "altaForCompl", propOrder = {
    "codCentroUsu",
    "codUbicUsu",
    "numDoc",
    "tipoDoc",
    "genNivfCod",
    "genEsCod",
    "genFamCod",
    "demFocomFecFin",
    "demFocomNroHoras",
    "demFocomTexto",
    "idioma"
})
public class AltaForCompl {

    @XmlElement(name = "cod_centro_usu")
    protected String codCentroUsu;
    @XmlElement(name = "cod_ubic_usu")
    protected String codUbicUsu;
    @XmlElement(name = "num_doc")
    protected String numDoc;
    @XmlElement(name = "tipo_doc")
    protected String tipoDoc;
    @XmlElement(name = "gen_nivf_cod")
    protected String genNivfCod;
    @XmlElement(name = "gen_Es_cod")
    protected String genEsCod;
    @XmlElement(name = "gen_fam_cod")
    protected String genFamCod;
    @XmlElement(name = "dem_focom_fec_fin")
    protected String demFocomFecFin;
    @XmlElement(name = "dem_focom_nro_horas")
    protected String demFocomNroHoras;
    @XmlElement(name = "dem_focom_texto")
    protected String demFocomTexto;
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
     * Gets the value of the genNivfCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenNivfCod() {
        return genNivfCod;
    }

    /**
     * Sets the value of the genNivfCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenNivfCod(String value) {
        this.genNivfCod = value;
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
     * Gets the value of the genFamCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenFamCod() {
        return genFamCod;
    }

    /**
     * Sets the value of the genFamCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenFamCod(String value) {
        this.genFamCod = value;
    }

    /**
     * Gets the value of the demFocomFecFin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemFocomFecFin() {
        return demFocomFecFin;
    }

    /**
     * Sets the value of the demFocomFecFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemFocomFecFin(String value) {
        this.demFocomFecFin = value;
    }

    /**
     * Gets the value of the demFocomNroHoras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemFocomNroHoras() {
        return demFocomNroHoras;
    }

    /**
     * Sets the value of the demFocomNroHoras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemFocomNroHoras(String value) {
        this.demFocomNroHoras = value;
    }

    /**
     * Gets the value of the demFocomTexto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemFocomTexto() {
        return demFocomTexto;
    }

    /**
     * Sets the value of the demFocomTexto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemFocomTexto(String value) {
        this.demFocomTexto = value;
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
