
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for firmaServicio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="firmaServicio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="corr_demanda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_centro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_ubic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_doc_usu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Num_doc_usu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Servicios" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Firma_oid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "firmaServicio", propOrder = {
    "corrDemanda",
    "codigoCentro",
    "codigoUbic",
    "tipoDocUsu",
    "numDocUsu",
    "servicios",
    "firmaOid"
})
public class FirmaServicio {

    @XmlElement(name = "corr_demanda")
    protected String corrDemanda;
    @XmlElement(name = "codigo_centro")
    protected String codigoCentro;
    @XmlElement(name = "codigo_ubic")
    protected String codigoUbic;
    @XmlElement(name = "Tipo_doc_usu")
    protected String tipoDocUsu;
    @XmlElement(name = "Num_doc_usu")
    protected String numDocUsu;
    @XmlElement(name = "Servicios")
    protected List<String> servicios;
    @XmlElement(name = "Firma_oid")
    protected String firmaOid;

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
     * Gets the value of the servicios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the servicios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServicios().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getServicios() {
        if (servicios == null) {
            servicios = new ArrayList<String>();
        }
        return this.servicios;
    }

    /**
     * Gets the value of the firmaOid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmaOid() {
        return firmaOid;
    }

    /**
     * Sets the value of the firmaOid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmaOid(String value) {
        this.firmaOid = value;
    }

}
