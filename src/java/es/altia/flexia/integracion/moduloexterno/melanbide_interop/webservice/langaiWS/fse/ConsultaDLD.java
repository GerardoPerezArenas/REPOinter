
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for consultaDLD complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consultaDLD">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipo_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="num_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_ref" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="num_meses" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaDLD", propOrder = {
    "tipoDoc",
    "numDoc",
    "fechaRef",
    "numMeses"
})
public class ConsultaDLD {

    @XmlElement(name = "tipo_doc")
    protected String tipoDoc;
    @XmlElement(name = "num_doc")
    protected String numDoc;
    @XmlElement(name = "fecha_ref")
    protected String fechaRef;
    @XmlElement(name = "num_meses")
    protected String numMeses;

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

    /**
     * Gets the value of the fechaRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaRef() {
        return fechaRef;
    }

    /**
     * Sets the value of the fechaRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaRef(String value) {
        this.fechaRef = value;
    }

    /**
     * Gets the value of the numMeses property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumMeses() {
        return numMeses;
    }

    /**
     * Sets the value of the numMeses property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumMeses(String value) {
        this.numMeses = value;
    }

}
