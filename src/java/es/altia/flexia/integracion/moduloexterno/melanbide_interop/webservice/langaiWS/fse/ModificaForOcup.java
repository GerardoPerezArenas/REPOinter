
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for modificaForOcup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modificaForOcup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cod_centro_usu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_ubic_usu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="num_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dem_foocu_ind_tifo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gen_es_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gen_fam_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dem_foocu_especialidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dem_foocu_text_libre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dem_foocu_fec_fin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dem_foocu_nro_horas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="for_af_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="for_inf_af_cod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="list_unids_comp" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "modificaForOcup", propOrder = {
    "codCentroUsu",
    "codUbicUsu",
    "numDoc",
    "tipoDoc",
    "demFoocuIndTifo",
    "genEsCod",
    "genFamCod",
    "demFoocuEspecialidad",
    "demFoocuTextLibre",
    "demFoocuFecFin",
    "demFoocuNroHoras",
    "forAfCod",
    "forInfAfCod",
    "listUnidsComp",
    "idioma"
})
public class ModificaForOcup {

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
    @XmlElement(name = "gen_fam_cod")
    protected String genFamCod;
    @XmlElement(name = "dem_foocu_especialidad")
    protected String demFoocuEspecialidad;
    @XmlElement(name = "dem_foocu_text_libre")
    protected String demFoocuTextLibre;
    @XmlElement(name = "dem_foocu_fec_fin")
    protected String demFoocuFecFin;
    @XmlElement(name = "dem_foocu_nro_horas")
    protected String demFoocuNroHoras;
    @XmlElement(name = "for_af_cod")
    protected String forAfCod;
    @XmlElement(name = "for_inf_af_cod")
    protected String forInfAfCod;
    @XmlElement(name = "list_unids_comp")
    protected List<String> listUnidsComp;
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
     * Gets the value of the demFoocuTextLibre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemFoocuTextLibre() {
        return demFoocuTextLibre;
    }

    /**
     * Sets the value of the demFoocuTextLibre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemFoocuTextLibre(String value) {
        this.demFoocuTextLibre = value;
    }

    /**
     * Gets the value of the demFoocuFecFin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemFoocuFecFin() {
        return demFoocuFecFin;
    }

    /**
     * Sets the value of the demFoocuFecFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemFoocuFecFin(String value) {
        this.demFoocuFecFin = value;
    }

    /**
     * Gets the value of the demFoocuNroHoras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemFoocuNroHoras() {
        return demFoocuNroHoras;
    }

    /**
     * Sets the value of the demFoocuNroHoras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemFoocuNroHoras(String value) {
        this.demFoocuNroHoras = value;
    }

    /**
     * Gets the value of the forAfCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForAfCod() {
        return forAfCod;
    }

    /**
     * Sets the value of the forAfCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForAfCod(String value) {
        this.forAfCod = value;
    }

    /**
     * Gets the value of the forInfAfCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForInfAfCod() {
        return forInfAfCod;
    }

    /**
     * Sets the value of the forInfAfCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForInfAfCod(String value) {
        this.forInfAfCod = value;
    }

    /**
     * Gets the value of the listUnidsComp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listUnidsComp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListUnidsComp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getListUnidsComp() {
        if (listUnidsComp == null) {
            listUnidsComp = new ArrayList<String>();
        }
        return this.listUnidsComp;
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
