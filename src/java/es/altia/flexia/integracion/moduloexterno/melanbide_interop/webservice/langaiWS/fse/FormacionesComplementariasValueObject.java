
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for formacionesComplementariasValueObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="formacionesComplementariasValueObject">
 *   &lt;complexContent>
 *     &lt;extension base="{http://langaiDemanda.services.langaiWS.es/}valueObject">
 *       &lt;sequence>
 *         &lt;element name="corr" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="formaciones_complementarias" type="{http://langaiDemanda.services.langaiWS.es/}formComplValueObject" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lista_errores" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="num_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "formacionesComplementariasValueObject", propOrder = {
    "corr",
    "formacionesComplementarias",
    "listaErrores",
    "numDoc",
    "tipoDoc"
})
public class FormacionesComplementariasValueObject
    extends ValueObject
{

    protected Long corr;
    @XmlElement(name = "formaciones_complementarias", nillable = true)
    protected List<FormComplValueObject> formacionesComplementarias;
    @XmlElement(name = "lista_errores", nillable = true)
    protected List<Object> listaErrores;
    @XmlElement(name = "num_doc")
    protected String numDoc;
    @XmlElement(name = "tipo_doc")
    protected String tipoDoc;

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
     * Gets the value of the formacionesComplementarias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the formacionesComplementarias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFormacionesComplementarias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FormComplValueObject }
     * 
     * 
     */
    public List<FormComplValueObject> getFormacionesComplementarias() {
        if (formacionesComplementarias == null) {
            formacionesComplementarias = new ArrayList<FormComplValueObject>();
        }
        return this.formacionesComplementarias;
    }

    /**
     * Gets the value of the listaErrores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaErrores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaErrores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getListaErrores() {
        if (listaErrores == null) {
            listaErrores = new ArrayList<Object>();
        }
        return this.listaErrores;
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

}
