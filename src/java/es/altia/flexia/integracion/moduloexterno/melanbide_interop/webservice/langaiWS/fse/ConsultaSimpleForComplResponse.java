
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for consultaSimpleForComplResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consultaSimpleForComplResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://langaiDemanda.services.langaiWS.es/}formacionesComplementariasValueObject" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaSimpleForComplResponse", propOrder = {
    "_return"
})
public class ConsultaSimpleForComplResponse {

    @XmlElement(name = "return")
    protected FormacionesComplementariasValueObject _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link FormacionesComplementariasValueObject }
     *     
     */
    public FormacionesComplementariasValueObject getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormacionesComplementariasValueObject }
     *     
     */
    public void setReturn(FormacionesComplementariasValueObject value) {
        this._return = value;
    }

}
