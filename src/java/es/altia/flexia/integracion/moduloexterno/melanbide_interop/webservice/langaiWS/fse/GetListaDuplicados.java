
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getListaDuplicados complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getListaDuplicados">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo_centro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_ubic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_doc_usu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Num_doc_usu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="num_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getListaDuplicados", propOrder = {
    "codigoCentro",
    "codigoUbic",
    "tipoDocUsu",
    "numDocUsu",
    "tipoDoc",
    "numDoc"
})
public class GetListaDuplicados {

    @XmlElement(name = "codigo_centro")
    protected String codigoCentro;
    @XmlElement(name = "codigo_ubic")
    protected String codigoUbic;
    @XmlElement(name = "Tipo_doc_usu")
    protected String tipoDocUsu;
    @XmlElement(name = "Num_doc_usu")
    protected String numDocUsu;
    @XmlElement(name = "tipo_doc")
    protected String tipoDoc;
    @XmlElement(name = "num_doc")
    protected String numDoc;

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

}
