
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for auditoriaValueObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="auditoriaValueObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acc_cod_accion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aud_corr" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="aud_datos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aud_fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aud_id_oferta" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="centro_uag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_centro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_perfil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_ubic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inicializarListas" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="num_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="que" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "auditoriaValueObject", propOrder = {
    "accCodAccion",
    "audCorr",
    "audDatos",
    "audFecha",
    "audIdOferta",
    "centroUag",
    "codCentro",
    "codPerfil",
    "codUbic",
    "inicializarListas",
    "numDoc",
    "que",
    "tipoDoc"
})
public class AuditoriaValueObject {

    @XmlElement(name = "acc_cod_accion")
    protected String accCodAccion;
    @XmlElement(name = "aud_corr")
    protected Long audCorr;
    @XmlElement(name = "aud_datos")
    protected String audDatos;
    @XmlElement(name = "aud_fecha")
    protected String audFecha;
    @XmlElement(name = "aud_id_oferta")
    protected Long audIdOferta;
    @XmlElement(name = "centro_uag")
    protected String centroUag;
    @XmlElement(name = "cod_centro")
    protected String codCentro;
    @XmlElement(name = "cod_perfil")
    protected String codPerfil;
    @XmlElement(name = "cod_ubic")
    protected String codUbic;
    protected boolean inicializarListas;
    @XmlElement(name = "num_doc")
    protected String numDoc;
    protected String que;
    @XmlElement(name = "tipo_doc")
    protected String tipoDoc;

    /**
     * Gets the value of the accCodAccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccCodAccion() {
        return accCodAccion;
    }

    /**
     * Sets the value of the accCodAccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccCodAccion(String value) {
        this.accCodAccion = value;
    }

    /**
     * Gets the value of the audCorr property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAudCorr() {
        return audCorr;
    }

    /**
     * Sets the value of the audCorr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAudCorr(Long value) {
        this.audCorr = value;
    }

    /**
     * Gets the value of the audDatos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAudDatos() {
        return audDatos;
    }

    /**
     * Sets the value of the audDatos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAudDatos(String value) {
        this.audDatos = value;
    }

    /**
     * Gets the value of the audFecha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAudFecha() {
        return audFecha;
    }

    /**
     * Sets the value of the audFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAudFecha(String value) {
        this.audFecha = value;
    }

    /**
     * Gets the value of the audIdOferta property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAudIdOferta() {
        return audIdOferta;
    }

    /**
     * Sets the value of the audIdOferta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAudIdOferta(Long value) {
        this.audIdOferta = value;
    }

    /**
     * Gets the value of the centroUag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentroUag() {
        return centroUag;
    }

    /**
     * Sets the value of the centroUag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentroUag(String value) {
        this.centroUag = value;
    }

    /**
     * Gets the value of the codCentro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodCentro() {
        return codCentro;
    }

    /**
     * Sets the value of the codCentro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodCentro(String value) {
        this.codCentro = value;
    }

    /**
     * Gets the value of the codPerfil property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPerfil() {
        return codPerfil;
    }

    /**
     * Sets the value of the codPerfil property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPerfil(String value) {
        this.codPerfil = value;
    }

    /**
     * Gets the value of the codUbic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodUbic() {
        return codUbic;
    }

    /**
     * Sets the value of the codUbic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodUbic(String value) {
        this.codUbic = value;
    }

    /**
     * Gets the value of the inicializarListas property.
     * 
     */
    public boolean isInicializarListas() {
        return inicializarListas;
    }

    /**
     * Sets the value of the inicializarListas property.
     * 
     */
    public void setInicializarListas(boolean value) {
        this.inicializarListas = value;
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
     * Gets the value of the que property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQue() {
        return que;
    }

    /**
     * Sets the value of the que property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQue(String value) {
        this.que = value;
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
