
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for personaFisicaWSValueObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="personaFisicaWSValueObject">
 *   &lt;complexContent>
 *     &lt;extension base="{http://langaiDemanda.services.langaiWS.es/}valueObject">
 *       &lt;sequence>
 *         &lt;element name="ape1Reconocido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ape2Reconocido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellido1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellido2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bloqAgr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cert_poli" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cert_ssss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_ambitoComarca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_ambitoMunicipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_ambitoProv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_ambitoccaa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_ambitoisla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_tipo_autoriz_admin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="condicionesEspeciales" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="corr" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="correlec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpApdoCorreos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="demandConVoluntad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc_notmuni_c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc_notmuni_e" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc_notprov_c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc_notprov_e" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc_remuni_c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc_remuni_e" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc_reprov_c" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc_reprov_e" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="doc_incompl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="existeLanbide" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="existeSISPE" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecConf" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fecResolReconocido" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fec_fin_min" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fec_fin_vigencia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fec_ini_min" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fec_sol_comunic_ren" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fecha_nac" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="generoReconocido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="grado_min" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="histor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indBloq" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="indConf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="indSispe" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="inter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intermedia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lista_errores" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="muniApdoCorreos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nivfor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noapartcorreos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nobisdup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nocopos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noescale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noextcodpost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noexttexto1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noexttexto2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nolepu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreReconocido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomuni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nonovp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nonuvp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nopais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nopiso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noprovin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notivipu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="num_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="num_exp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="num_exp_act" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="num_ssss" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pais_fecha_fin" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="pais_marca_eee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pasaporte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rebisdup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reccaa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recopos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reescale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reextcodpost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reexttexto1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reexttexto2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relepu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remuni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="renovp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="renut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="renuvp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="repiso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reprovin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="restricciones_Actividad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="restricciones_Ocupacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retivipu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rgi_activa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rgi_importe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_benef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tlfno1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tlfno2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tlfno3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tlfno4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tlfno_notif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ultAgr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personaFisicaWSValueObject", propOrder = {
    "ape1Reconocido",
    "ape2Reconocido",
    "apellido1",
    "apellido2",
    "bloqAgr",
    "certPoli",
    "certSsss",
    "codAmbitoComarca",
    "codAmbitoMunicipio",
    "codAmbitoProv",
    "codAmbitoccaa",
    "codAmbitoisla",
    "codTipoAutorizAdmin",
    "condicionesEspeciales",
    "corr",
    "correlec",
    "cpApdoCorreos",
    "demandConVoluntad",
    "descNotmuniC",
    "descNotmuniE",
    "descNotprovC",
    "descNotprovE",
    "descRemuniC",
    "descRemuniE",
    "descReprovC",
    "descReprovE",
    "docIncompl",
    "estado",
    "existeLanbide",
    "existeSISPE",
    "fax",
    "fecConf",
    "fecResolReconocido",
    "fecFinMin",
    "fecFinVigencia",
    "fecIniMin",
    "fecSolComunicRen",
    "fechaNac",
    "generoReconocido",
    "gradoMin",
    "histor",
    "indBloq",
    "indConf",
    "indSispe",
    "inter",
    "intermedia",
    "listaErrores",
    "muniApdoCorreos",
    "nacion",
    "nivfor",
    "noapartcorreos",
    "nobisdup",
    "nocopos",
    "noescale",
    "noextcodpost",
    "noexttexto1",
    "noexttexto2",
    "nolepu",
    "nombre",
    "nombreReconocido",
    "nomuni",
    "nonovp",
    "nonuvp",
    "nopais",
    "nopiso",
    "noprovin",
    "notivipu",
    "numDoc",
    "numExp",
    "numExpAct",
    "numSsss",
    "paisFechaFin",
    "paisMarcaEee",
    "pasaporte",
    "rebisdup",
    "reccaa",
    "recopos",
    "reescale",
    "reextcodpost",
    "reexttexto1",
    "reexttexto2",
    "relepu",
    "remuni",
    "renovp",
    "renut",
    "renuvp",
    "repais",
    "repiso",
    "reprovin",
    "restriccionesActividad",
    "restriccionesOcupacion",
    "retivipu",
    "rgiActiva",
    "rgiImporte",
    "sexo",
    "tipoBenef",
    "tipoDoc",
    "tlfno1",
    "tlfno2",
    "tlfno3",
    "tlfno4",
    "tlfnoNotif",
    "ultAgr"
})
public class PersonaFisicaWSValueObject
    extends ValueObject
{

    protected String ape1Reconocido;
    protected String ape2Reconocido;
    protected String apellido1;
    protected String apellido2;
    protected String bloqAgr;
    @XmlElement(name = "cert_poli")
    protected String certPoli;
    @XmlElement(name = "cert_ssss")
    protected String certSsss;
    @XmlElement(name = "cod_ambitoComarca")
    protected String codAmbitoComarca;
    @XmlElement(name = "cod_ambitoMunicipio")
    protected String codAmbitoMunicipio;
    @XmlElement(name = "cod_ambitoProv")
    protected String codAmbitoProv;
    @XmlElement(name = "cod_ambitoccaa")
    protected String codAmbitoccaa;
    @XmlElement(name = "cod_ambitoisla")
    protected String codAmbitoisla;
    @XmlElement(name = "cod_tipo_autoriz_admin")
    protected String codTipoAutorizAdmin;
    @XmlElement(nillable = true)
    protected List<Object> condicionesEspeciales;
    protected Long corr;
    protected String correlec;
    protected String cpApdoCorreos;
    protected String demandConVoluntad;
    @XmlElement(name = "desc_notmuni_c")
    protected String descNotmuniC;
    @XmlElement(name = "desc_notmuni_e")
    protected String descNotmuniE;
    @XmlElement(name = "desc_notprov_c")
    protected String descNotprovC;
    @XmlElement(name = "desc_notprov_e")
    protected String descNotprovE;
    @XmlElement(name = "desc_remuni_c")
    protected String descRemuniC;
    @XmlElement(name = "desc_remuni_e")
    protected String descRemuniE;
    @XmlElement(name = "desc_reprov_c")
    protected String descReprovC;
    @XmlElement(name = "desc_reprov_e")
    protected String descReprovE;
    @XmlElement(name = "doc_incompl")
    protected String docIncompl;
    protected String estado;
    protected boolean existeLanbide;
    protected boolean existeSISPE;
    protected String fax;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecConf;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecResolReconocido;
    @XmlElement(name = "fec_fin_min")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecFinMin;
    @XmlElement(name = "fec_fin_vigencia")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecFinVigencia;
    @XmlElement(name = "fec_ini_min")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecIniMin;
    @XmlElement(name = "fec_sol_comunic_ren")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecSolComunicRen;
    @XmlElement(name = "fecha_nac")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaNac;
    protected String generoReconocido;
    @XmlElement(name = "grado_min")
    protected Long gradoMin;
    protected String histor;
    protected Integer indBloq;
    protected String indConf;
    protected Integer indSispe;
    protected String inter;
    protected String intermedia;
    @XmlElement(name = "lista_errores", nillable = true)
    protected List<Object> listaErrores;
    protected String muniApdoCorreos;
    protected String nacion;
    protected String nivfor;
    protected String noapartcorreos;
    protected String nobisdup;
    protected String nocopos;
    protected String noescale;
    protected String noextcodpost;
    protected String noexttexto1;
    protected String noexttexto2;
    protected String nolepu;
    protected String nombre;
    protected String nombreReconocido;
    protected String nomuni;
    protected String nonovp;
    protected String nonuvp;
    protected String nopais;
    protected String nopiso;
    protected String noprovin;
    protected String notivipu;
    @XmlElement(name = "num_doc")
    protected String numDoc;
    @XmlElement(name = "num_exp")
    protected String numExp;
    @XmlElement(name = "num_exp_act")
    protected String numExpAct;
    @XmlElement(name = "num_ssss")
    protected String numSsss;
    @XmlElement(name = "pais_fecha_fin")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar paisFechaFin;
    @XmlElement(name = "pais_marca_eee")
    protected String paisMarcaEee;
    protected String pasaporte;
    protected String rebisdup;
    protected String reccaa;
    protected String recopos;
    protected String reescale;
    protected String reextcodpost;
    protected String reexttexto1;
    protected String reexttexto2;
    protected String relepu;
    protected String remuni;
    protected String renovp;
    protected String renut;
    protected String renuvp;
    protected String repais;
    protected String repiso;
    protected String reprovin;
    @XmlElement(name = "restricciones_Actividad")
    protected String restriccionesActividad;
    @XmlElement(name = "restricciones_Ocupacion")
    protected String restriccionesOcupacion;
    protected String retivipu;
    @XmlElement(name = "rgi_activa")
    protected String rgiActiva;
    @XmlElement(name = "rgi_importe")
    protected String rgiImporte;
    protected String sexo;
    @XmlElement(name = "tipo_benef")
    protected String tipoBenef;
    @XmlElement(name = "tipo_doc")
    protected String tipoDoc;
    protected String tlfno1;
    protected String tlfno2;
    protected String tlfno3;
    protected String tlfno4;
    @XmlElement(name = "tlfno_notif")
    protected String tlfnoNotif;
    protected Integer ultAgr;

    /**
     * Gets the value of the ape1Reconocido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApe1Reconocido() {
        return ape1Reconocido;
    }

    /**
     * Sets the value of the ape1Reconocido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApe1Reconocido(String value) {
        this.ape1Reconocido = value;
    }

    /**
     * Gets the value of the ape2Reconocido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApe2Reconocido() {
        return ape2Reconocido;
    }

    /**
     * Sets the value of the ape2Reconocido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApe2Reconocido(String value) {
        this.ape2Reconocido = value;
    }

    /**
     * Gets the value of the apellido1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Sets the value of the apellido1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido1(String value) {
        this.apellido1 = value;
    }

    /**
     * Gets the value of the apellido2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Sets the value of the apellido2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido2(String value) {
        this.apellido2 = value;
    }

    /**
     * Gets the value of the bloqAgr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBloqAgr() {
        return bloqAgr;
    }

    /**
     * Sets the value of the bloqAgr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBloqAgr(String value) {
        this.bloqAgr = value;
    }

    /**
     * Gets the value of the certPoli property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertPoli() {
        return certPoli;
    }

    /**
     * Sets the value of the certPoli property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertPoli(String value) {
        this.certPoli = value;
    }

    /**
     * Gets the value of the certSsss property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertSsss() {
        return certSsss;
    }

    /**
     * Sets the value of the certSsss property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertSsss(String value) {
        this.certSsss = value;
    }

    /**
     * Gets the value of the codAmbitoComarca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAmbitoComarca() {
        return codAmbitoComarca;
    }

    /**
     * Sets the value of the codAmbitoComarca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAmbitoComarca(String value) {
        this.codAmbitoComarca = value;
    }

    /**
     * Gets the value of the codAmbitoMunicipio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAmbitoMunicipio() {
        return codAmbitoMunicipio;
    }

    /**
     * Sets the value of the codAmbitoMunicipio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAmbitoMunicipio(String value) {
        this.codAmbitoMunicipio = value;
    }

    /**
     * Gets the value of the codAmbitoProv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAmbitoProv() {
        return codAmbitoProv;
    }

    /**
     * Sets the value of the codAmbitoProv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAmbitoProv(String value) {
        this.codAmbitoProv = value;
    }

    /**
     * Gets the value of the codAmbitoccaa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAmbitoccaa() {
        return codAmbitoccaa;
    }

    /**
     * Sets the value of the codAmbitoccaa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAmbitoccaa(String value) {
        this.codAmbitoccaa = value;
    }

    /**
     * Gets the value of the codAmbitoisla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAmbitoisla() {
        return codAmbitoisla;
    }

    /**
     * Sets the value of the codAmbitoisla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAmbitoisla(String value) {
        this.codAmbitoisla = value;
    }

    /**
     * Gets the value of the codTipoAutorizAdmin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodTipoAutorizAdmin() {
        return codTipoAutorizAdmin;
    }

    /**
     * Sets the value of the codTipoAutorizAdmin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodTipoAutorizAdmin(String value) {
        this.codTipoAutorizAdmin = value;
    }

    /**
     * Gets the value of the condicionesEspeciales property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the condicionesEspeciales property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCondicionesEspeciales().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getCondicionesEspeciales() {
        if (condicionesEspeciales == null) {
            condicionesEspeciales = new ArrayList<Object>();
        }
        return this.condicionesEspeciales;
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
     * Gets the value of the correlec property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrelec() {
        return correlec;
    }

    /**
     * Sets the value of the correlec property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrelec(String value) {
        this.correlec = value;
    }

    /**
     * Gets the value of the cpApdoCorreos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpApdoCorreos() {
        return cpApdoCorreos;
    }

    /**
     * Sets the value of the cpApdoCorreos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpApdoCorreos(String value) {
        this.cpApdoCorreos = value;
    }

    /**
     * Gets the value of the demandConVoluntad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemandConVoluntad() {
        return demandConVoluntad;
    }

    /**
     * Sets the value of the demandConVoluntad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemandConVoluntad(String value) {
        this.demandConVoluntad = value;
    }

    /**
     * Gets the value of the descNotmuniC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescNotmuniC() {
        return descNotmuniC;
    }

    /**
     * Sets the value of the descNotmuniC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescNotmuniC(String value) {
        this.descNotmuniC = value;
    }

    /**
     * Gets the value of the descNotmuniE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescNotmuniE() {
        return descNotmuniE;
    }

    /**
     * Sets the value of the descNotmuniE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescNotmuniE(String value) {
        this.descNotmuniE = value;
    }

    /**
     * Gets the value of the descNotprovC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescNotprovC() {
        return descNotprovC;
    }

    /**
     * Sets the value of the descNotprovC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescNotprovC(String value) {
        this.descNotprovC = value;
    }

    /**
     * Gets the value of the descNotprovE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescNotprovE() {
        return descNotprovE;
    }

    /**
     * Sets the value of the descNotprovE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescNotprovE(String value) {
        this.descNotprovE = value;
    }

    /**
     * Gets the value of the descRemuniC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescRemuniC() {
        return descRemuniC;
    }

    /**
     * Sets the value of the descRemuniC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescRemuniC(String value) {
        this.descRemuniC = value;
    }

    /**
     * Gets the value of the descRemuniE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescRemuniE() {
        return descRemuniE;
    }

    /**
     * Sets the value of the descRemuniE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescRemuniE(String value) {
        this.descRemuniE = value;
    }

    /**
     * Gets the value of the descReprovC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescReprovC() {
        return descReprovC;
    }

    /**
     * Sets the value of the descReprovC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescReprovC(String value) {
        this.descReprovC = value;
    }

    /**
     * Gets the value of the descReprovE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescReprovE() {
        return descReprovE;
    }

    /**
     * Sets the value of the descReprovE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescReprovE(String value) {
        this.descReprovE = value;
    }

    /**
     * Gets the value of the docIncompl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocIncompl() {
        return docIncompl;
    }

    /**
     * Sets the value of the docIncompl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocIncompl(String value) {
        this.docIncompl = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the existeLanbide property.
     * 
     */
    public boolean isExisteLanbide() {
        return existeLanbide;
    }

    /**
     * Sets the value of the existeLanbide property.
     * 
     */
    public void setExisteLanbide(boolean value) {
        this.existeLanbide = value;
    }

    /**
     * Gets the value of the existeSISPE property.
     * 
     */
    public boolean isExisteSISPE() {
        return existeSISPE;
    }

    /**
     * Sets the value of the existeSISPE property.
     * 
     */
    public void setExisteSISPE(boolean value) {
        this.existeSISPE = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * Gets the value of the fecConf property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecConf() {
        return fecConf;
    }

    /**
     * Sets the value of the fecConf property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecConf(XMLGregorianCalendar value) {
        this.fecConf = value;
    }

    /**
     * Gets the value of the fecResolReconocido property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecResolReconocido() {
        return fecResolReconocido;
    }

    /**
     * Sets the value of the fecResolReconocido property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecResolReconocido(XMLGregorianCalendar value) {
        this.fecResolReconocido = value;
    }

    /**
     * Gets the value of the fecFinMin property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecFinMin() {
        return fecFinMin;
    }

    /**
     * Sets the value of the fecFinMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecFinMin(XMLGregorianCalendar value) {
        this.fecFinMin = value;
    }

    /**
     * Gets the value of the fecFinVigencia property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecFinVigencia() {
        return fecFinVigencia;
    }

    /**
     * Sets the value of the fecFinVigencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecFinVigencia(XMLGregorianCalendar value) {
        this.fecFinVigencia = value;
    }

    /**
     * Gets the value of the fecIniMin property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecIniMin() {
        return fecIniMin;
    }

    /**
     * Sets the value of the fecIniMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecIniMin(XMLGregorianCalendar value) {
        this.fecIniMin = value;
    }

    /**
     * Gets the value of the fecSolComunicRen property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecSolComunicRen() {
        return fecSolComunicRen;
    }

    /**
     * Sets the value of the fecSolComunicRen property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecSolComunicRen(XMLGregorianCalendar value) {
        this.fecSolComunicRen = value;
    }

    /**
     * Gets the value of the fechaNac property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaNac() {
        return fechaNac;
    }

    /**
     * Sets the value of the fechaNac property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaNac(XMLGregorianCalendar value) {
        this.fechaNac = value;
    }

    /**
     * Gets the value of the generoReconocido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneroReconocido() {
        return generoReconocido;
    }

    /**
     * Sets the value of the generoReconocido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneroReconocido(String value) {
        this.generoReconocido = value;
    }

    /**
     * Gets the value of the gradoMin property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGradoMin() {
        return gradoMin;
    }

    /**
     * Sets the value of the gradoMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGradoMin(Long value) {
        this.gradoMin = value;
    }

    /**
     * Gets the value of the histor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHistor() {
        return histor;
    }

    /**
     * Sets the value of the histor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHistor(String value) {
        this.histor = value;
    }

    /**
     * Gets the value of the indBloq property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIndBloq() {
        return indBloq;
    }

    /**
     * Sets the value of the indBloq property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIndBloq(Integer value) {
        this.indBloq = value;
    }

    /**
     * Gets the value of the indConf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndConf() {
        return indConf;
    }

    /**
     * Sets the value of the indConf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndConf(String value) {
        this.indConf = value;
    }

    /**
     * Gets the value of the indSispe property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIndSispe() {
        return indSispe;
    }

    /**
     * Sets the value of the indSispe property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIndSispe(Integer value) {
        this.indSispe = value;
    }

    /**
     * Gets the value of the inter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInter() {
        return inter;
    }

    /**
     * Sets the value of the inter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInter(String value) {
        this.inter = value;
    }

    /**
     * Gets the value of the intermedia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntermedia() {
        return intermedia;
    }

    /**
     * Sets the value of the intermedia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntermedia(String value) {
        this.intermedia = value;
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
     * Gets the value of the muniApdoCorreos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMuniApdoCorreos() {
        return muniApdoCorreos;
    }

    /**
     * Sets the value of the muniApdoCorreos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMuniApdoCorreos(String value) {
        this.muniApdoCorreos = value;
    }

    /**
     * Gets the value of the nacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacion() {
        return nacion;
    }

    /**
     * Sets the value of the nacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacion(String value) {
        this.nacion = value;
    }

    /**
     * Gets the value of the nivfor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivfor() {
        return nivfor;
    }

    /**
     * Sets the value of the nivfor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivfor(String value) {
        this.nivfor = value;
    }

    /**
     * Gets the value of the noapartcorreos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoapartcorreos() {
        return noapartcorreos;
    }

    /**
     * Sets the value of the noapartcorreos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoapartcorreos(String value) {
        this.noapartcorreos = value;
    }

    /**
     * Gets the value of the nobisdup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNobisdup() {
        return nobisdup;
    }

    /**
     * Sets the value of the nobisdup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNobisdup(String value) {
        this.nobisdup = value;
    }

    /**
     * Gets the value of the nocopos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNocopos() {
        return nocopos;
    }

    /**
     * Sets the value of the nocopos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNocopos(String value) {
        this.nocopos = value;
    }

    /**
     * Gets the value of the noescale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoescale() {
        return noescale;
    }

    /**
     * Sets the value of the noescale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoescale(String value) {
        this.noescale = value;
    }

    /**
     * Gets the value of the noextcodpost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoextcodpost() {
        return noextcodpost;
    }

    /**
     * Sets the value of the noextcodpost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoextcodpost(String value) {
        this.noextcodpost = value;
    }

    /**
     * Gets the value of the noexttexto1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoexttexto1() {
        return noexttexto1;
    }

    /**
     * Sets the value of the noexttexto1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoexttexto1(String value) {
        this.noexttexto1 = value;
    }

    /**
     * Gets the value of the noexttexto2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoexttexto2() {
        return noexttexto2;
    }

    /**
     * Sets the value of the noexttexto2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoexttexto2(String value) {
        this.noexttexto2 = value;
    }

    /**
     * Gets the value of the nolepu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNolepu() {
        return nolepu;
    }

    /**
     * Sets the value of the nolepu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNolepu(String value) {
        this.nolepu = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the nombreReconocido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreReconocido() {
        return nombreReconocido;
    }

    /**
     * Sets the value of the nombreReconocido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreReconocido(String value) {
        this.nombreReconocido = value;
    }

    /**
     * Gets the value of the nomuni property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomuni() {
        return nomuni;
    }

    /**
     * Sets the value of the nomuni property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomuni(String value) {
        this.nomuni = value;
    }

    /**
     * Gets the value of the nonovp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNonovp() {
        return nonovp;
    }

    /**
     * Sets the value of the nonovp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonovp(String value) {
        this.nonovp = value;
    }

    /**
     * Gets the value of the nonuvp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNonuvp() {
        return nonuvp;
    }

    /**
     * Sets the value of the nonuvp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonuvp(String value) {
        this.nonuvp = value;
    }

    /**
     * Gets the value of the nopais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNopais() {
        return nopais;
    }

    /**
     * Sets the value of the nopais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNopais(String value) {
        this.nopais = value;
    }

    /**
     * Gets the value of the nopiso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNopiso() {
        return nopiso;
    }

    /**
     * Sets the value of the nopiso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNopiso(String value) {
        this.nopiso = value;
    }

    /**
     * Gets the value of the noprovin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoprovin() {
        return noprovin;
    }

    /**
     * Sets the value of the noprovin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoprovin(String value) {
        this.noprovin = value;
    }

    /**
     * Gets the value of the notivipu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotivipu() {
        return notivipu;
    }

    /**
     * Sets the value of the notivipu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotivipu(String value) {
        this.notivipu = value;
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
     * Gets the value of the numExp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumExp() {
        return numExp;
    }

    /**
     * Sets the value of the numExp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumExp(String value) {
        this.numExp = value;
    }

    /**
     * Gets the value of the numExpAct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumExpAct() {
        return numExpAct;
    }

    /**
     * Sets the value of the numExpAct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumExpAct(String value) {
        this.numExpAct = value;
    }

    /**
     * Gets the value of the numSsss property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumSsss() {
        return numSsss;
    }

    /**
     * Sets the value of the numSsss property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumSsss(String value) {
        this.numSsss = value;
    }

    /**
     * Gets the value of the paisFechaFin property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPaisFechaFin() {
        return paisFechaFin;
    }

    /**
     * Sets the value of the paisFechaFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPaisFechaFin(XMLGregorianCalendar value) {
        this.paisFechaFin = value;
    }

    /**
     * Gets the value of the paisMarcaEee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisMarcaEee() {
        return paisMarcaEee;
    }

    /**
     * Sets the value of the paisMarcaEee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisMarcaEee(String value) {
        this.paisMarcaEee = value;
    }

    /**
     * Gets the value of the pasaporte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPasaporte() {
        return pasaporte;
    }

    /**
     * Sets the value of the pasaporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPasaporte(String value) {
        this.pasaporte = value;
    }

    /**
     * Gets the value of the rebisdup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRebisdup() {
        return rebisdup;
    }

    /**
     * Sets the value of the rebisdup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRebisdup(String value) {
        this.rebisdup = value;
    }

    /**
     * Gets the value of the reccaa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReccaa() {
        return reccaa;
    }

    /**
     * Sets the value of the reccaa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReccaa(String value) {
        this.reccaa = value;
    }

    /**
     * Gets the value of the recopos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecopos() {
        return recopos;
    }

    /**
     * Sets the value of the recopos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecopos(String value) {
        this.recopos = value;
    }

    /**
     * Gets the value of the reescale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReescale() {
        return reescale;
    }

    /**
     * Sets the value of the reescale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReescale(String value) {
        this.reescale = value;
    }

    /**
     * Gets the value of the reextcodpost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReextcodpost() {
        return reextcodpost;
    }

    /**
     * Sets the value of the reextcodpost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReextcodpost(String value) {
        this.reextcodpost = value;
    }

    /**
     * Gets the value of the reexttexto1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReexttexto1() {
        return reexttexto1;
    }

    /**
     * Sets the value of the reexttexto1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReexttexto1(String value) {
        this.reexttexto1 = value;
    }

    /**
     * Gets the value of the reexttexto2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReexttexto2() {
        return reexttexto2;
    }

    /**
     * Sets the value of the reexttexto2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReexttexto2(String value) {
        this.reexttexto2 = value;
    }

    /**
     * Gets the value of the relepu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelepu() {
        return relepu;
    }

    /**
     * Sets the value of the relepu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelepu(String value) {
        this.relepu = value;
    }

    /**
     * Gets the value of the remuni property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemuni() {
        return remuni;
    }

    /**
     * Sets the value of the remuni property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemuni(String value) {
        this.remuni = value;
    }

    /**
     * Gets the value of the renovp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenovp() {
        return renovp;
    }

    /**
     * Sets the value of the renovp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenovp(String value) {
        this.renovp = value;
    }

    /**
     * Gets the value of the renut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenut() {
        return renut;
    }

    /**
     * Sets the value of the renut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenut(String value) {
        this.renut = value;
    }

    /**
     * Gets the value of the renuvp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenuvp() {
        return renuvp;
    }

    /**
     * Sets the value of the renuvp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenuvp(String value) {
        this.renuvp = value;
    }

    /**
     * Gets the value of the repais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepais() {
        return repais;
    }

    /**
     * Sets the value of the repais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepais(String value) {
        this.repais = value;
    }

    /**
     * Gets the value of the repiso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepiso() {
        return repiso;
    }

    /**
     * Sets the value of the repiso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepiso(String value) {
        this.repiso = value;
    }

    /**
     * Gets the value of the reprovin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReprovin() {
        return reprovin;
    }

    /**
     * Sets the value of the reprovin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReprovin(String value) {
        this.reprovin = value;
    }

    /**
     * Gets the value of the restriccionesActividad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestriccionesActividad() {
        return restriccionesActividad;
    }

    /**
     * Sets the value of the restriccionesActividad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestriccionesActividad(String value) {
        this.restriccionesActividad = value;
    }

    /**
     * Gets the value of the restriccionesOcupacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestriccionesOcupacion() {
        return restriccionesOcupacion;
    }

    /**
     * Sets the value of the restriccionesOcupacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestriccionesOcupacion(String value) {
        this.restriccionesOcupacion = value;
    }

    /**
     * Gets the value of the retivipu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetivipu() {
        return retivipu;
    }

    /**
     * Sets the value of the retivipu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetivipu(String value) {
        this.retivipu = value;
    }

    /**
     * Gets the value of the rgiActiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRgiActiva() {
        return rgiActiva;
    }

    /**
     * Sets the value of the rgiActiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRgiActiva(String value) {
        this.rgiActiva = value;
    }

    /**
     * Gets the value of the rgiImporte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRgiImporte() {
        return rgiImporte;
    }

    /**
     * Sets the value of the rgiImporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRgiImporte(String value) {
        this.rgiImporte = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Gets the value of the tipoBenef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoBenef() {
        return tipoBenef;
    }

    /**
     * Sets the value of the tipoBenef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoBenef(String value) {
        this.tipoBenef = value;
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
     * Gets the value of the tlfno1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTlfno1() {
        return tlfno1;
    }

    /**
     * Sets the value of the tlfno1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTlfno1(String value) {
        this.tlfno1 = value;
    }

    /**
     * Gets the value of the tlfno2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTlfno2() {
        return tlfno2;
    }

    /**
     * Sets the value of the tlfno2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTlfno2(String value) {
        this.tlfno2 = value;
    }

    /**
     * Gets the value of the tlfno3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTlfno3() {
        return tlfno3;
    }

    /**
     * Sets the value of the tlfno3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTlfno3(String value) {
        this.tlfno3 = value;
    }

    /**
     * Gets the value of the tlfno4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTlfno4() {
        return tlfno4;
    }

    /**
     * Sets the value of the tlfno4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTlfno4(String value) {
        this.tlfno4 = value;
    }

    /**
     * Gets the value of the tlfnoNotif property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTlfnoNotif() {
        return tlfnoNotif;
    }

    /**
     * Sets the value of the tlfnoNotif property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTlfnoNotif(String value) {
        this.tlfnoNotif = value;
    }

    /**
     * Gets the value of the ultAgr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUltAgr() {
        return ultAgr;
    }

    /**
     * Sets the value of the ultAgr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUltAgr(Integer value) {
        this.ultAgr = value;
    }

}
