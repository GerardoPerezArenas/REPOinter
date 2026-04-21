package es.altia.flexia.integracion.moduloexterno.melanbide_interop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.altia.agora.business.escritorio.UsuarioValueObject;
import es.altia.agora.technical.ConstantesDatos;
import es.altia.common.exception.TechnicalException;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores.ErrorLan6ExcepcionBeanNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores.GestionarErroresDokusiNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.i18n.MeLanbideInteropI18n;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.manager.MeLanbideInteropManager;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.ComboNisae;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.ExpedienteNisaeVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.GestionServiciosNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.GestionServiciosNISAECVL;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.GestionServiciosNISAEEjecucionConsPadron;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.GestionServiciosNISAEEjecucionEIKA;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.GestionServiciosNISAEEjecucionHHFF;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.GestionServiciosNISAEEjecucionTGSS;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.entities.InteropLlamadasServiciosNisae;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.entities.InteropServiciosNisaeExpFi;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.entities.vo.RetornoResultadoNISAELog;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.dao.MEInteropCargaTelemXMLServiceDAO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.dto.MEInteropCargaTelemXMLExpediente;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.dto.MEInteropCargaTelemXMLParameters;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.services.MEInteropCargaTelemXMLService;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.utilities.MEInteropCargaTelemXML;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConfigurationParameter;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.MeLanbideInteropGeneralUtils;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.DocumentoPersona;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.FiltrosNisaeLogVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.RegistroVidaLaboralVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.RequestRestServicePadron;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.ResponseRestServicePadron;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.Tramitador;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.tercero.TerceroVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse.LangaiDemanda;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse.LangaiDemandaService;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.LangaiDem_pkg.LangaiDem;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.LangaiDem_pkg.LangaiDemServiceLocator;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.xml.tratarrespuesta.TratarRespuestasXMLWS;
import es.altia.flexia.integracion.moduloexterno.plugin.ModuloIntegracionExterno;
import es.altia.flexia.registro.digitalizacion.lanbide.util.GestionAdaptadoresLan6Config;
import es.altia.interoperabilidad.datamodel.getCorrientePagoTgssWS.peticion.*;
import es.altia.interoperabilidad.datamodel.getCorrientePagoTgssWS.respuesta.*;
import es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.*;
import es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.respuesta.*;
import es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.datosespecificos.GetEpigrafesWS;
//import es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.datosespecificos.GetEpigrafesWS;
import es.altia.interoperabilidad.jaxws.getCorrientePagoTgssWS.*;
import es.altia.interoperabilidad.jaxws.getDatosIdentidadV3WS.*;
import es.altia.interoperabilidad.jaxws.getDatosPadronV3User.PadronIndividualPortType;
import es.altia.interoperabilidad.jaxws.getDatosPadronV3User.X53JsGetDatosPadronV3;
import es.altia.interoperabilidad.jaxws.getDatosResidenciaWS.X53JsGetDatosResidenciaWSDelegate;
import es.altia.interoperabilidad.jaxws.getDatosResidenciaWS.X53JsGetDatosResidenciaWSService;
import es.altia.interoperabilidad.jaxws.getObligacionesAllWS.X53JsGetObligacionesAllWSDelegate;
import es.altia.interoperabilidad.jaxws.getObligacionesAllWS.X53JsGetObligacionesAllWSService;
import es.altia.technical.PortableContext;
import es.altia.util.conexion.AdaptadorSQLBD;
import es.redsara.intermediacion.scsp.esquemas.datosespecificos.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.BindingProvider;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import es.altia.interoperabilidad.jaxws.getDomicilioFiscalWS.X53JsGetDomicilioFiscalWSDelegate;
import es.altia.interoperabilidad.jaxws.getDomicilioFiscalWS.X53JsGetDomicilioFiscalWSService;
import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6ErrorBean;
import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6Excepcion;
import net.lanbide.interoperability.otddff.beans.LanNISAEEntrada;
import net.lanbide.interoperability.otddff.beans.LanNISAESalida;
import net.lanbide.interoperability.otddff.servicios.LanConsultaObligacionesTributariasServicio;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//Los tres import de clases de weblogic: comentar para desplegar / des comentar para subir a repo
/*
import weblogic.xml.crypto.wss.WSSecurityContext;
import weblogic.wsee.security.unt.ClientUNTCredentialProvider;
import weblogic.xml.crypto.wss.provider.CredentialProvider;
*/
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.MeLanbideInteropMappingUtils;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.RespuestaWSVidaLaboralVO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Persona;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.ws.client.vidalaboralws.clientws.ClientWSVidaLaboral;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.ws.client.vidalaboralws.response.Response;
import es.altia.util.ajax.respuesta.RespuestaAjaxUtils;
import javax.xml.bind.DatatypeConverter;
import org.apache.log4j.Logger;


/**
 *
 * @author davidg
 */
public class MELANBIDE_INTEROP extends ModuloIntegracionExterno {

    //Logger
    private static Logger log = Logger.getLogger(MELANBIDE_INTEROP.class);
    ResourceBundle m_Conf = ResourceBundle.getBundle("common");
    SimpleDateFormat formatDateLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    // Instancia del Servicio
    private DatosIdentidadPortType instanceDatIdent = null;
    private es.altia.interoperabilidad.jaxws.getEpigrafesIaeUser.PeticionPortType instanceEpigraf = null;
    private X53JsGetDomicilioFiscalWSDelegate instanceDomFisc = null;
    private PeticionPortType instanceCorrienteTgss = null;
    private X53JsGetObligacionesAllWSDelegate instanceObligacionesTribuDipu = null;
    private PadronIndividualPortType instanceDatosPadron = null;
    private X53JsGetDatosResidenciaWSDelegate instanceDatosResidencia = null;

    // otras constantes
    public static final String STS_POLICY = "StsWss11UntPolicy.xml";

    private GestionServiciosNISAE gestionServiciosNISAE = new GestionServiciosNISAE();
    private static final GestionAdaptadoresLan6Config gestionAdaptadoresLan6Config = new GestionAdaptadoresLan6Config();

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls();
    private Gson gson = gsonBuilder.serializeNulls().create();


    private AdaptadorSQLBD getAdaptSQLBD(String codOrganizacion){
        if(log.isDebugEnabled()) log.error("getConnection ( codOrganizacion = " + codOrganizacion + " ) : BEGIN");
        ResourceBundle config = ResourceBundle.getBundle("techserver");
        String gestor = config.getString("CON.gestor");
        String jndiGenerico = config.getString("CON.jndi");
        Connection conGenerico = null;
        Statement st = null;
        ResultSet rs = null;
        String[] salida = null;
        Connection con = null;

        if(log.isDebugEnabled()){
            log.error("getJndi =========> ");
            log.error("parametro codOrganizacion: " + codOrganizacion);
            log.error("gestor: " + gestor);
            log.error("jndi: " + jndiGenerico);
        }//if(log.isDebugEnabled())

        DataSource ds = null;
        AdaptadorSQLBD adapt = null;
        synchronized (this) {
            try{
                PortableContext pc = PortableContext.getInstance();
                if(log.isDebugEnabled())log.error("He cogido el jndi: " + jndiGenerico);
                ds = (DataSource)pc.lookup(jndiGenerico, DataSource.class);
                // Conexión al esquema genérico
                conGenerico = ds.getConnection();

                String sql = "SELECT EEA_BDE FROM A_EEA WHERE EEA_APL=" + ConstantesDatos.APP_GESTION_EXPEDIENTES + " AND AAE_ORG=" + codOrganizacion;
                st = conGenerico.createStatement();
                rs = st.executeQuery(sql);
                String jndi = null;
                while(rs.next()){
                    jndi = rs.getString("EEA_BDE");
                }//while(rs.next())

                st.close();
                rs.close();
                conGenerico.close();

                if(jndi!=null && gestor!=null && !"".equals(jndi) && !"".equals(gestor)){
                    salida = new String[7];
                    salida[0] = gestor;
                    salida[1] = "";
                    salida[2] = "";
                    salida[3] = "";
                    salida[4] = "";
                    salida[5] = "";
                    salida[6] = jndi;
                    adapt = new AdaptadorSQLBD(salida);
                }//if(jndi!=null && gestor!=null && !"".equals(jndi) && !"".equals(gestor))
            }catch(TechnicalException te){
                te.printStackTrace();
                log.error("*** AdaptadorSQLBD: " + te.toString());
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                try{
                    if(st!=null) st.close();
                    if(rs!=null) rs.close();
                    if(conGenerico!=null && !conGenerico.isClosed())
                        conGenerico.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }//try-catch
            }// finally
            if(log.isDebugEnabled()) log.error("getConnection() : END");
        }// synchronized
        return adapt;
    }//getConnection

    public String cargarPantallaPrincipalModulo(int codOrganizacion,  int codTramite, int ocurrenciaTramite,String numExpediente,HttpServletRequest request,HttpServletResponse response)
    {
        log.error("***  Entramos en la funcion principal de Interoperabilidad ****");
        try
        {
            String[] datosExpediente = numExpediente.split(ConstantesMeLanbideInterop.BARRA_SEPARADORA);
            String codProcExpte = datosExpediente[1];
            AdaptadorSQLBD adapt = null;
            try
            {
                adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
            }
            catch(Exception ex)
            {
                log.error("Error al recuperar el adaptador getAdaptSQLBD ", ex);
            }
            if(adapt != null)
            {
                try
                {
                    // Recogemos los Terceros del Expediente
                    /**
                     * 12/12/2017
                     * Añadimos los terceros desde el modulo de extension, segun procedimiento, si es necesario.
                     * Se hace en el manager. Asi se recoge igual tanto al listar como al llmar a cualquier WS
                     */
                    List<TerceroVO> listaTerceros = MeLanbideInteropManager.getInstance().getDatosTercerosxExpediente(codOrganizacion, numExpediente, adapt);
                    if(listaTerceros.size()>0)
                    {
                        request.setAttribute("listaTerceros", listaTerceros);
                    }
                }
                catch(Exception ex){
                    log.error("Error al cargar los datos del los teceros del Expedinete para el modulo Interoperabiliad "
                            + numExpediente + " - " + ex.getCause(), ex);
                }
            }
            else
                log.error("No se ha recuoperado el Adaptador par la conexions a BD");

            List<String> proAutoDatosIndent = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_DATIDENTIDAD,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoEpigrafesIAE = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_EPIGRAFESIAE,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoDomFiscal = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_DOMICIFISCAL,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoCorrientePagoTGSS = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_CORRPAGOTGSS,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoHHFF = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_HHFF,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoObligacTributDipu = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_OBLITRIBDIPU,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoLangaiDemanda = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_LANGAIDEMAND,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoLangaiDemandaFSE = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_LANGAIDEMANDFSE,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoDatosPadron = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_DATOSPADRON,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoDatosResidencia = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_DATOSRESIDENCIA,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));

            String hidenbtnDatosIdentidad= proAutoDatosIndent.contains(codProcExpte)?"1":"0";
            String hidenbtnEpigrafesIae=proAutoEpigrafesIAE.contains(codProcExpte)?"1":"0";
            String hidenbtnDomicilioFiscal=proAutoDomFiscal.contains(codProcExpte)?"1":"0";
            String hidenbtnCorrientePagoTGSS=proAutoCorrientePagoTGSS.contains(codProcExpte)?"1":"0";
            String hidenbtnHHFF=proAutoHHFF.contains(codProcExpte)?"1":"0";
            String hidenbtnObligacionesTribuDipu=proAutoObligacTributDipu.contains(codProcExpte)?"1":"0";
            String hidenbtnLangaiDemanda=proAutoLangaiDemanda.contains(codProcExpte)?"1":"0";
            String hidenbtnLangaiDemandaFSE=proAutoLangaiDemandaFSE.contains(codProcExpte)?"1":"0";
            String hidenbtnDatosPadron=proAutoDatosPadron.contains(codProcExpte)?"1":"0";
            String hidenbtnDatosResidencia=proAutoDatosResidencia.contains(codProcExpte)?"1":"0";

            request.setAttribute("hidenbtnDatosIdentidad", hidenbtnDatosIdentidad);
            request.setAttribute("hidenbtnEpigrafesIae",hidenbtnEpigrafesIae);
            request.setAttribute("hidenbtnDomicilioFiscal",hidenbtnDomicilioFiscal);
            request.setAttribute("hidenbtnCorrientePagoTGSS",hidenbtnCorrientePagoTGSS);
            request.setAttribute("hidenbtnHHFF",hidenbtnHHFF);
            request.setAttribute("hidenbtnObligacionesTribuDipu",hidenbtnObligacionesTribuDipu);
            request.setAttribute("hidenbtnLangaiDemanda",hidenbtnLangaiDemanda);
            request.setAttribute("hidenbtnLangaiDemandaFSE",hidenbtnLangaiDemandaFSE);
            request.setAttribute("hidenbtnDatosPadron",hidenbtnDatosPadron);
            request.setAttribute("hidenbtnDatosResidencia",hidenbtnDatosResidencia);
        }
        catch(Exception ex)
        {
            log.error("Eror intentado cargar DATOS pantalla principal del modulo interoperabilidad ", ex);
        }
        log.error("***  Finalizamos y Retornamos url Principal de Interoperabilidad ****");
        return "/jsp/extension/melanbide_interop/interoperabilidad.jsp";
    }

    public String cargarPantallaPrincipalModuloGen(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response)
    {
        log.error("***  Entramos en cargarPantallaPrincipalModuloGen (sin tabla terceros) ****");
        try
        {
            String[] datosExpediente = numExpediente.split(ConstantesMeLanbideInterop.BARRA_SEPARADORA);
            String codProcExpte = datosExpediente[1];
            AdaptadorSQLBD adapt = null;
            try
            {
                adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
            }
            catch(Exception ex)
            {
                log.error("Error al recuperar el adaptador getAdaptSQLBD ", ex);
            }
            if(adapt != null)
            {
                try
                {
                    List<TerceroVO> listaTerceros = MeLanbideInteropManager.getInstance().getDatosTercerosxExpediente(codOrganizacion, numExpediente, adapt);
                    if(listaTerceros.size()>0)
                    {
                        request.setAttribute("listaTerceros", listaTerceros);
                    }
                }
                catch(Exception ex){
                    log.error("Error al cargar terceros para interoperabilidadGen " + numExpediente, ex);
                }
            }

            List<String> proAutoDatosIndent = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_DATIDENTIDAD,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoEpigrafesIAE = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_EPIGRAFESIAE,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoDomFiscal = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_DOMICIFISCAL,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoCorrientePagoTGSS = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_CORRPAGOTGSS,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoHHFF = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_HHFF,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoObligacTributDipu = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_OBLITRIBDIPU,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoLangaiDemanda = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_LANGAIDEMAND,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoLangaiDemandaFSE = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_LANGAIDEMANDFSE,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoDatosPadron = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_DATOSPADRON,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));
            List<String> proAutoDatosResidencia = Arrays.asList(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.COD_PROC_AUTO_DATOSRESIDENCIA,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES).split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF));

            request.setAttribute("hidenbtnDatosIdentidad",       proAutoDatosIndent.contains(codProcExpte)       ? "1" : "0");
            request.setAttribute("hidenbtnEpigrafesIae",         proAutoEpigrafesIAE.contains(codProcExpte)      ? "1" : "0");
            request.setAttribute("hidenbtnDomicilioFiscal",      proAutoDomFiscal.contains(codProcExpte)         ? "1" : "0");
            request.setAttribute("hidenbtnCorrientePagoTGSS",    proAutoCorrientePagoTGSS.contains(codProcExpte) ? "1" : "0");
            request.setAttribute("hidenbtnHHFF",                 proAutoHHFF.contains(codProcExpte)              ? "1" : "0");
            request.setAttribute("hidenbtnObligacionesTribuDipu",proAutoObligacTributDipu.contains(codProcExpte) ? "1" : "0");
            request.setAttribute("hidenbtnLangaiDemanda",        proAutoLangaiDemanda.contains(codProcExpte)     ? "1" : "0");
            request.setAttribute("hidenbtnLangaiDemandaFSE",     proAutoLangaiDemandaFSE.contains(codProcExpte)  ? "1" : "0");
            request.setAttribute("hidenbtnDatosPadron",          proAutoDatosPadron.contains(codProcExpte)       ? "1" : "0");
            request.setAttribute("hidenbtnDatosResidencia",      proAutoDatosResidencia.contains(codProcExpte)   ? "1" : "0");
        }
        catch(Exception ex)
        {
            log.error("Error cargando pantalla interoperabilidadGen ", ex);
        }
        log.error("***  Retornamos interoperabilidadGen.jsp ****");
        return "/jsp/extension/melanbide_interop/interoperabilidadGen.jsp";
    }

    public void GetVerificacionDatosIdentificacion(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response) throws SAXException, IOException, ParserConfigurationException
    {
        String codigoOperacion = "0";
        List<TerceroVO> lista = new ArrayList<TerceroVO>();
        TerceroVO _tercero = new TerceroVO();
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        String respuestaServicio = "";
        String[] datosExpediente = numExpediente.split(ConstantesMeLanbideInterop.BARRA_SEPARADORA);
        String codProcExpte = datosExpediente[1];


        //String separador = "��";
        //String separadortemp = "��";


        try
        {
            // Cogemos los datos del Usuario conectado a la aplicación.
            UsuarioValueObject usuario = new UsuarioValueObject();
            String usuario_nif = "";
            HttpSession session = request.getSession();
            try {
                if (session != null) {
                    if (usuario != null) {
                        usuario = (UsuarioValueObject) session.getAttribute("usuario");
                    }
                }
            }catch(Exception ex){
                log.error("Error al recuperar los datos del Usuario desde la session", ex);
            }
            usuario_nif = MeLanbideInteropManager.getInstance().getUsuarioNIF(usuario.getIdUsuario(), adapt);
            // Recojo Lista de rerceros relacionados Con el expediente para recuperar sus datos.
            /*
            String listaCodTercerosExp = (String)request.getParameter("listaTercerosExp");
            if(listaCodTercerosExp.contains(separadortemp))
                listaCodTercerosExp = listaCodTercerosExp.replaceAll(separadortemp,separador);
            String[]  listaTercerosExp = listaCodTercerosExp.split(separador);
            */

            //Recojo los parametros
            String codTer = (String)request.getParameter("codTer");
            String documento = (String)request.getParameter("documento");
            lista = MeLanbideInteropManager.getInstance().getDatosTercerosxExpediente(codOrganizacion, numExpediente, adapt);
            if(lista.size()>0)
            {
                for(TerceroVO ter : lista)
                {
                    if(ter.getCodTer().equals(codTer) && ter.getDoc().equals(documento)){
                        _tercero = ter;
                        break;
                    }
                }
            }else{
                // Tenemos que devolver error !! No se ha recuperado datos de los terceros de BD
                // lanzo excepcion y capturo en el catch con codigo actualizado.
                codigoOperacion = "1";
                respuestaServicio="Error al recuperar la lista de terceros";
                log.error("Error al recoger los interesado del expediente . La lista ha retornado vacía de BBDD");
            }

            // PARAMETROS LLMADA AL SERVCIO
            /////  cargar datos de ejemplo  -- recogeremos de Tercero ////

            //GetDatosIdentidadWS _getDatosIdentidadWS = new GetDatosIdentidadWS();
            //_getDatosIdentidadWS.setPOrganoSolicitante("Eusko Jaurlaritza - Ejie Desarrollo");

            String organoSolicitante    = ""; // Lanbide - Servicio Vasco Empleo --- Eusko Jaurlaritza - Ejie Desarrollo";
            String unidadTramitadora    = usuario.getDepCod() + " - " + usuario.getDep();//"Departamento de Educación";

            // Para primera pruebas sino existe en properties cogemo el de conciliacion

            String codigoProcedimiento  = "";// 10133 Conciliacion CONCM     "SVDI_000233";
            codigoProcedimiento = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            if("".equals(codigoProcedimiento)){
                // Sino se recupera del properties deberá devolver error
                // Sin embargo en estas primeras pruebas recogemos el CONCM
                log.error("Codigo procedimiento no  configurado para consultas en MELANBIDE_INTEROP.properties :  "  + ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO+codProcExpte + " --> " + codigoProcedimiento);
                codigoOperacion="5";
                respuestaServicio="Codigo procedimiento no  configurado para consultas en MELANBIDE_INTEROP.properties. "  + ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO+codProcExpte + " : " + codigoProcedimiento;
                //codigoProcedimiento = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + "CONCM", ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            }
            String nombreProcedimiento  = MeLanbideInteropManager.getInstance().getDescripcionProcedimiento(codOrganizacion, codProcExpte, adapt); // Solicitud y matriculación del alumnado";
            String finalidad            = nombreProcedimiento;  //"Solicitud y matriculación del alumnado";

            //"Si";  // Valores Si o Ley. Al realizar la solicitus el interesdo debe firmar el consentimiento
            String consentimiento =ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONSENTIMIENTO_SI, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);


            String nifTramitador = usuario_nif; //"12345678Z";
            String nombreTramitador = usuario.getApp() + " - " + usuario.getNombreUsu(); //"JUAN JOSE PEREZ ARTEAGA";

            String idExpediente         = numExpediente;//"R02K0000000000000001";
            String tipoTramite          = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TIPO_TRAMITE_VERIFICACION, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);//"VERIFICACION";//CONSULTA

            // datos del interesado a verificar
            String tipoDocumento        = "";// (/DNI/NIE/Pasaporte "DNI";
            tipoDocumento=MeLanbideInteropGeneralUtils.parsearTipoDocFlexiaToTipoDocEJIE_WS(_tercero.getTipoDoc());
            if(tipoDocumento==null || tipoDocumento.equalsIgnoreCase("")){
                log.error("Tipo Documento no Soportado : "  + _tercero.getDoc() + " / " + _tercero.getTipoDoc());
                codigoOperacion="6";
                respuestaServicio="Tipo Documento no soportado para el WS de verificacion de identidad. Documentos admitidos : DNI/NIE/Pasaporte";
            }

            String numDocumento         = _tercero.getDoc();
            String nombreTitular        = _tercero.getNombre(); //"MANUELA";
            String apellido1Titular     = _tercero.getApellido1();  // "BLANCO";
            String apellido2Titular     = _tercero.getApellido2();  //"VIDAL";

            String numSoporte=""; // No son Obligatorios no los pasamos "";
            String Sexo                = "";// No son Obligatorios no los pasamos "M";
            String FechaNacimiento	= "";// No son Obligatorios no los pasamos "20020905";
            String ProvinciaNacimiento  = "";// No son Obligatorios no los pasamos "36"; //PONTEVEDRA
            String PaisNacimiento 	= "";// No son Obligatorios no los pasamos "ESP";
            String ProvinciaResidencia  = "";// No son Obligatorios no los pasamos "24";  //LEON
            String PaisResidencia       = "";// No son Obligatorios no los pasamos "108";//ESPAÑA

            // Llamamo solo si no han habido errores abteriormente
            if("".equals(respuestaServicio)){
//            MELANBIDE_INTEROP_WS accesoInteroperabilidad = new MELANBIDE_INTEROP_WS();
                respuestaServicio += this.WebServiceGetDatosIdentificacion(codOrganizacion, numExpediente,
                        organoSolicitante,
                        unidadTramitadora,
                        codigoProcedimiento,
                        nombreProcedimiento,
                        finalidad,
                        consentimiento,
                        nifTramitador,
                        nombreTramitador,
                        idExpediente,
                        tipoTramite,
                        tipoDocumento,
                        numDocumento,
                        nombreTitular,
                        apellido1Titular,
                        apellido2Titular,
                        numSoporte,
                        Sexo,
                        FechaNacimiento,
                        ProvinciaNacimiento,
                        PaisNacimiento,
                        ProvinciaResidencia,
                        PaisResidencia);
            }else
                log.error("No realizamos llamada el WS porque hay errores de validacion antes de llamar : " + respuestaServicio);
            if("".equals(respuestaServicio))
            {
                log.error("Erro - No se ha podido recibir respuesta del Servicio Verificacion de Identidad : "
                        + numExpediente
                        + " - " + numDocumento
                        + " - " + nombreTitular
                        + "  " + apellido1Titular
                );
                respuestaServicio = "No se ha podido obtener respuesta del WS de Verificacion de Indentidad. Para: \n "
                        + numExpediente
                        + " - " + numDocumento
                        + " - " + nombreTitular
                        + "  " + apellido1Titular
                ;
            }

        }
        catch(Exception ex)
        {
            log.error("Error General en la llamada a WS de Verificación de Identidad.", ex);
            codigoOperacion = "2";
        }

        /*
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //un factory
        DocumentBuilder builder = factory.newDocumentBuilder(); //el documento
        Document document = builder.parse(new InputSource(new StringReader(respuestaServicio))); //aqui le paso al Document
        String uuu = document.getTextContent();
        NodeList codigoOperacin2 =  document.getElementsByTagName("RESPUESTA");
        */

        StringBuffer xmlSalida = new StringBuffer();
        //xmlSalida.append(respuestaServicio);

        xmlSalida.append("<RESPUESTA>");
        xmlSalida.append("<CODIGO_OPERACION>");
        xmlSalida.append(codigoOperacion);
        xmlSalida.append("</CODIGO_OPERACION>");
        xmlSalida.append("<RESULTADO>");
        xmlSalida.append(respuestaServicio);
        xmlSalida.append("</RESULTADO>");
        /*    for(DotacionVO fila : lista)
            {
                xmlSalida.append("<FILA>");
                    xmlSalida.append("<ID>");
                        xmlSalida.append(fila.getId()!= null ? fila.getId().toString() : "");
                    xmlSalida.append("</ID>");
                    xmlSalida.append("<ID_ESPSOL>");
                        xmlSalida.append(fila.getIdEspSol()!= null ? fila.getIdEspSol().toString() : "");
                    xmlSalida.append("</ID_ESPSOL>");
                    xmlSalida.append("<DOT_NUM>");
                        xmlSalida.append(fila.getNumExp());
                    xmlSalida.append("</DOT_NUM>");
                    xmlSalida.append("<DOT_CANT>");
                        xmlSalida.append(fila.getCantidad()!= null ? fila.getCantidad().toString() : "");
                    xmlSalida.append("</DOT_CANT>");
                    xmlSalida.append("<DOT_DET>");
                        xmlSalida.append(fila.getDenominacionET());
                    xmlSalida.append("</DOT_DET>");
                    xmlSalida.append("<DOT_FAD>");
                        xmlSalida.append(fila.getFechaAdq());
                    xmlSalida.append("</DOT_FAD>");
                xmlSalida.append("</FILA>");
            }
        */
        xmlSalida.append("</RESPUESTA>");
        log.error("XML de salida : " + xmlSalida.toString());
        try{
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(xmlSalida.toString());
            out.flush();
            out.close();
        }catch(Exception e){
            log.error("Error preparando el response con el XML de salida : ", e);
        }//try-catch

    }

    public void GetConsultaEpigrafesIae(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response)
    {
        String codigoOperacion = "0";
        List<TerceroVO> lista = new ArrayList<TerceroVO>();
        TerceroVO _tercero = new TerceroVO();
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        String respuestaServicio = "";
        try{
            String[] datosExpediente = numExpediente.split(ConstantesMeLanbideInterop.BARRA_SEPARADORA);
            String codProcExpte = datosExpediente[1];
            //Recojo los parametros
            String codTer = (String)request.getParameter("codTer");
            String documento = (String)request.getParameter("documento");
            lista = MeLanbideInteropManager.getInstance().getDatosTercerosxExpediente(codOrganizacion, numExpediente, adapt);
            if(lista.size()>0)
            {
                for(TerceroVO ter : lista)
                {
                    if(ter.getCodTer().equals(codTer) && ter.getDoc().equals(documento)){
                        _tercero = ter;
                        break;
                    }
                }
            }
            // Cogemos los datos del Usuario conectadp a la aplicación.
            UsuarioValueObject usuario = new UsuarioValueObject();
            String usuario_nif = "";
            HttpSession session = request.getSession();
            try {
                if (session != null) {
                    if (usuario != null) {
                        usuario = (UsuarioValueObject) session.getAttribute("usuario");
                    }
                }
            } catch (Exception ex) {
                log.error("Error al recuperar los datos del Usuario desde la session", ex);
            }
            usuario_nif = MeLanbideInteropManager.getInstance().getUsuarioNIF(usuario.getIdUsuario(), adapt);

            String organoSolicitante = "Lanbide - Servicio Vasco Empleo";
            String unidadTramitadora = usuario.getDepCod() + " - " + usuario.getDep();

            String codigoProcedimiento = "";
            codigoProcedimiento = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            if ("".equals(codigoProcedimiento)) {
                log.error("Codigo procedimiento no  configurado para consultas en MELANBIDE_INTEROP.properties :  " + ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte + " --> " + codigoProcedimiento);
                codigoOperacion = "5";
                respuestaServicio = "Codigo procedimiento no  configurado para consultas en MELANBIDE_INTEROP.properties. " + ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte + " : " + codigoProcedimiento;
            }
            String nombreProcedimiento = MeLanbideInteropManager.getInstance().getDescripcionProcedimiento(codOrganizacion, codProcExpte, adapt);
            String finalidad = nombreProcedimiento;
            String consentimiento = "Si";  // Valores Si o Ley. Al realizar la solicitus el interesdo debe firmar el consentimiento

            String nifTramitador = usuario_nif;
            String nombreTramitador = usuario.getApp() + " - " + usuario.getNombreUsu();

            String idExpediente = numExpediente;
            String tipoTramite = "";

            // datos del interesado a verificar
            String tipoDocumento = "";// (/DNI/NIE/Pasaporte "DNI";
            tipoDocumento=MeLanbideInteropGeneralUtils.parsearTipoDocFlexiaToTipoDocEJIE_WS(_tercero.getTipoDoc());
            if(tipoDocumento==null || tipoDocumento.equalsIgnoreCase("")){
                log.error("Tipo Documento no Soportado : " + _tercero.getDoc() + " / " + _tercero.getTipoDoc());
                codigoOperacion = "6";
                respuestaServicio = "Tipo Documento no soportado para el WS de consulta de epígrafes IAE. Documentos admitidos : CIF/DNI/NIE";
            }

            String numDocumento = _tercero.getDoc();
            String nombreTitular = _tercero.getNombre();
            String apellido1Titular = _tercero.getApellido1();
            String apellido2Titular = _tercero.getApellido2();
            //para pruebas Tomamos ALava -- Mirar como se manejara el tema de las Diputacion a consultar.
            String territorio = "01";    //01, 20, 48
            // nuevos parametros que apareciron en la nueva definicion, no tenemos documentacion
            String paraNuevo1 = "";
            String paraNuevo2 = "";

            if("".equals(respuestaServicio)){
                respuestaServicio = this.WebServiceGetEpigrafesIae(codOrganizacion, numExpediente,
                        organoSolicitante,
                        unidadTramitadora,
                        codigoProcedimiento,
                        nombreProcedimiento,
                        finalidad,
                        consentimiento,
                        nifTramitador,
                        nombreTramitador,
                        idExpediente,
                        tipoTramite,
                        tipoDocumento,
                        numDocumento,
                        nombreTitular,
                        apellido1Titular,
                        apellido2Titular,
                        territorio,
                        paraNuevo1,
                        paraNuevo2);
            }

            if(respuestaServicio==null || respuestaServicio.isEmpty())
            {
                log.error("Erro - No se ha podido recibir respuesta del Servicio Consulta de Epigrafes IAE : "
                        + numExpediente
                        + " - " + numDocumento
                        + " - " + nombreTitular
                        + "  " + apellido1Titular
                );
            }else{
                log.error(" Servicio Consulta de Epigrafes IAE : "
                        + numExpediente
                        + " - " + numDocumento
                        + " - " + nombreTitular
                        + "  " + apellido1Titular
                        + " - RespuestaWS  :  " + respuestaServicio
                );
                if(respuestaServicio.startsWith("<?xml")){
                    log.error("Recibimos un XML formateado como salida el WS");
                    DOMParser parser = new DOMParser();
                    try {
                        parser.parse(new InputSource(new java.io.StringReader(respuestaServicio)));
                        Document doc = parser.getDocument();
                        Node tagRespuestas = doc.getFirstChild();
                        Node respuesta = tagRespuestas.getFirstChild();
                        if(respuesta.hasChildNodes()){
                            NodeList contRespuesta = respuesta.getChildNodes();
                            for(int i=0;i<contRespuesta.getLength();i++){
                                if("Estado".equals(contRespuesta.item(i).getNodeName())){
                                    respuestaServicio = contRespuesta.item(i).getFirstChild().getTextContent() +
                                            " - " + contRespuesta.item(i).getLastChild().getTextContent();
                                }
                            }
                        }

                    } catch (SAXException e) {
                        log.error("SAXException al tratar el XML de salida del WS Epigrafes IAE", e);
                        codigoOperacion="7";
                        respuestaServicio = "Error al tratar el XML de salida retornado por el Servicio";
                    } catch (IOException e) {
                        log.error("IOException al tratar el XML de salida del WS Epigrafes IAE", e);
                        codigoOperacion="7";
                        respuestaServicio = "Error al tratar el XML de salida retornado por el Servicio";
                    }
                }
            }

        }
        catch(Exception ex)
        {
            log.error("Error en la llamada al WS de epigrafes : " ,  ex);
            codigoOperacion = "2";
        }

        StringBuilder xmlSalida = new StringBuilder();
        xmlSalida.append("<RESPUESTA>");
        xmlSalida.append("<CODIGO_OPERACION>");
        xmlSalida.append(codigoOperacion);
        xmlSalida.append("</CODIGO_OPERACION>");
        xmlSalida.append("<RESULTADO>");
        xmlSalida.append(respuestaServicio);
        xmlSalida.append("</RESULTADO>");
        xmlSalida.append("</RESPUESTA>");
        try{
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(xmlSalida.toString());
            out.flush();
            out.close();
        }catch(Exception ex){
            log.error("Error preparando el XML para retornar la salida el WS de Epigragfes IAE " , ex);
        }//try-catch

    }

    public void GetConsultaDomicilioFiscal(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response)
    {
        String codigoOperacion = "0";
        List<TerceroVO> lista = new ArrayList<TerceroVO>();
        TerceroVO _tercero = new TerceroVO();
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        String respuestaServicio = "";
        try
        {
            String[] datosExpediente = numExpediente.split(ConstantesMeLanbideInterop.BARRA_SEPARADORA);
            String codProcExpte = datosExpediente[1];
            //Recojo los parametros
            String codTer = (String)request.getParameter("codTer");
            String documento = (String)request.getParameter("documento");
            lista = MeLanbideInteropManager.getInstance().getDatosTercerosxExpediente(codOrganizacion, numExpediente, adapt);
            if(lista.size()>0)
            {
                for(TerceroVO ter : lista)
                {
                    if(ter.getCodTer().equals(codTer) && ter.getDoc().equals(documento)){
                        _tercero = ter;
                        break;
                    }
                }
            }

            // Cogemos los datos del Usuario conectadp a la aplicación.
            UsuarioValueObject usuario = new UsuarioValueObject();
            String usuario_nif = "";
            HttpSession session = request.getSession();
            try {
                if (session != null) {
                    if (usuario != null) {
                        usuario = (UsuarioValueObject) session.getAttribute("usuario");
                    }
                }
            } catch (Exception ex) {
                log.error("Error al recuperar los datos del Usuario desde la session", ex);
            }
            usuario_nif = MeLanbideInteropManager.getInstance().getUsuarioNIF(usuario.getIdUsuario(), adapt);

            String organoSolicitante = "Lanbide - Servicio Vasco Empleo";
            String unidadTramitadora = usuario.getDepCod() + " - " + usuario.getDep();

            String codigoProcedimiento = "";
            codigoProcedimiento = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            if ("".equals(codigoProcedimiento)) {
                log.error("Codigo procedimiento no  configurado para consultas en MELANBIDE_INTEROP.properties :  " + ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte + " --> " + codigoProcedimiento);
                codigoOperacion = "5";
                respuestaServicio = "Codigo procedimiento no  configurado para consultas en MELANBIDE_INTEROP.properties. " + ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte + " : " + codigoProcedimiento;
            }
            String nombreProcedimiento = MeLanbideInteropManager.getInstance().getDescripcionProcedimiento(codOrganizacion, codProcExpte, adapt);
            String finalidad = nombreProcedimiento;
            String consentimiento = "Si";  // Valores Si o Ley. Al realizar la solicitus el interesdo debe firmar el consentimiento

            String nifTramitador = usuario_nif;
            String nombreTramitador = usuario.getApp() + " - " + usuario.getNombreUsu();

            String idExpediente = numExpediente;
            String tipoTramite = "";

            // datos del interesado a verificar
            String tipoDocumento = "";// (/DNI/NIE/Pasaporte "DNI";
            tipoDocumento=MeLanbideInteropGeneralUtils.parsearTipoDocFlexiaToTipoDocEJIE_WS(_tercero.getTipoDoc());
            if(tipoDocumento==null || tipoDocumento.equalsIgnoreCase("")){
                log.error("Tipo Documento no Soportado : " + _tercero.getDoc() + " / " + _tercero.getTipoDoc());
                codigoOperacion = "6";
                respuestaServicio = "Tipo Documento no soportado para el WS de consulta de epígrafes IAE. Documentos admitidos : CIF/DNI/NIE";
            }

            String numDocumento = _tercero.getDoc();
            // No se especifica en la documentacion
            String titularDocumento     = "";
            String nombreTitular = _tercero.getNombre();
            String apellido1Titular = _tercero.getApellido1();
            String apellido2Titular = _tercero.getApellido2();
            //para pruebas Tomamos ALava -- Mirar como se manejara el tema de las Diputacion a consultar.
            String territorio = "01";    //01, 20, 48
            // nuevos parametros que apareciron en la nueva definicion, no tenemos documentacion
            String paraNuevo1 = "";

            if ("".equals(respuestaServicio)) {
                respuestaServicio = this.WebServiceGetDomicilioFiscal(codOrganizacion, numExpediente,
                        organoSolicitante,
                        unidadTramitadora,
                        codigoProcedimiento,
                        nombreProcedimiento,
                        finalidad,
                        consentimiento,
                        nifTramitador,
                        nombreTramitador,
                        idExpediente,
                        tipoTramite,
                        tipoDocumento,
                        numDocumento,
                        titularDocumento,
                        nombreTitular,
                        apellido1Titular,
                        apellido2Titular,
                        territorio,
                        paraNuevo1);
            }

            if (respuestaServicio == null || respuestaServicio.isEmpty()) {
                log.error("Error - No se ha podido recibir respuesta del Servicio Consulta Domicilio Fiscal: "
                        + numExpediente
                        + " - " + numDocumento
                        + " - " + nombreTitular
                        + "  " + apellido1Titular
                );
            } else {
                log.error(" Servicio Consulta de Consulta Consulta Domicilio Fiscal: : "
                        + numExpediente
                        + " - " + numDocumento
                        + " - " + nombreTitular
                        + "  " + apellido1Titular
                        + " - RespuestaWS  :  " + respuestaServicio
                );
            }

        }
        catch(Exception ex)
        {
            log.error("Error general al procesar la solicitu de consulta de domicilio fiscal", ex);
            codigoOperacion = "2";
        }

        StringBuffer xmlSalida = new StringBuffer();
        xmlSalida.append("<RESPUESTA>");
        xmlSalida.append("<CODIGO_OPERACION>");
        xmlSalida.append(codigoOperacion);
        xmlSalida.append("</CODIGO_OPERACION>");
        xmlSalida.append("<RESULTADO>");
        xmlSalida.append(respuestaServicio);
        xmlSalida.append("</RESULTADO>");
        xmlSalida.append("</RESPUESTA>");
        try{
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(xmlSalida.toString());
            out.flush();
            out.close();
        }catch(Exception e){
            log.error("Error preparando el XML de salida para el WS de COnsulta de domicilio fiscal", e);
        }//try-catch

    }

    public void GetConsultaLangaiDemanda(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response)
    {
        //String codigoOperacion = "0";
        List<TerceroVO> lista = new ArrayList<TerceroVO>();
        TerceroVO _tercero = new TerceroVO();
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        String respuestaServicio = "";
        /*
        //String separador = "��";
        //String separadortemp = "��";
        */
        try
        {

            // Recojo Lista de rerceros relacionados Con el expediente para recuperar sus datos.
           /*
            String listaCodTercerosExp = (String)request.getParameter("listaTercerosExp");
            if(listaCodTercerosExp.contains(separadortemp))
                listaCodTercerosExp = listaCodTercerosExp.replaceAll(separadortemp,separador);
            String[]  listaTercerosExp = listaCodTercerosExp.split(separador);
            */

            //Recojo los parametros
            String codTer = (String)request.getParameter("codTer");
            String versionTer = (String)request.getParameter("versionTer");
            String documento = (String)request.getParameter("documento");

            lista = MeLanbideInteropManager.getInstance().getDatosTercerosxExpediente(codOrganizacion, numExpediente, adapt);

            if(lista.size()>0)
            {
                for(TerceroVO ter : lista)
                {
                    if(ter.getCodTer().equals(codTer) && ter.getVersionTercero().equals(versionTer)
                            && ter.getDoc().equals(documento)){
                        _tercero = ter;
                        break;
                    }
                }
            }

            // PARAMETROS LLMADA AL SERVCIO
            /////  cargar datos de ejemplo  -- recogeremos de Tercero ////

            // Entorno de despliegue -- para definir la url de llamada al servicio
            /*  0 - Desarrollo
                1 - Pruebas
                2 -Produccion
            */
            // Pendiente definir funcion..... Pribamo con la url de pruebas - la de desa no va

            //Integer entornoDespliegue = 1;

            String tipo_doc = "";
            String num_doc = "";
            if(_tercero != null){
                if(_tercero.getTipoDoc().equals("1"))
                    tipo_doc="D";
                else if(_tercero.getTipoDoc().equals("3"))
                    tipo_doc="E";
                else if(_tercero.getTipoDoc().equals("8"))
                    tipo_doc="W";
                else if(_tercero.getTipoDoc().equals("9"))
                    tipo_doc="U";
                num_doc=_tercero.getDoc();
            }

//            MELANBIDE_INTEROP_WS accesoInteroperabilidad = new MELANBIDE_INTEROP_WS();

            respuestaServicio = this.WebServiceGetLangaiDemanda(codOrganizacion, numExpediente,
                    //entornoDespliegue,
                    tipo_doc,
                    num_doc);

            if(respuestaServicio==null || respuestaServicio.isEmpty())
            {
                log.error("Error - No se ha podido recibir respuesta del Servicio Langai Demanda : "
                                + numExpediente
                                + " - " + num_doc
                        //+ " - Entorno1Desa-2Pru-3Pro: " + entornoDespliegue
                );
                respuestaServicio = "-1";
            }

        }
        catch(Exception ex)
        {
            log.error("GetConsultaLangaiDemanda - Exception general al llamar al WS de langai Demanda. "
                    + numExpediente  + ex.getMessage()
            );
            if(respuestaServicio==null || respuestaServicio.isEmpty())
                respuestaServicio = "-2";
        }
        /***
         * -1 WS No llamado o respuesta obtenida a null
         * -2 Exception General
         * -3 Fallo de Conexion WS o AxisFault de otro tipo
         */

        StringBuffer xmlSalida = new StringBuffer();
        xmlSalida.append("<RESPUESTA>");
        xmlSalida.append("<CODIGO_OPERACION>");
        xmlSalida.append(respuestaServicio);
        xmlSalida.append("</CODIGO_OPERACION>");
            /*
            xmlSalida.append("<RESULTADO>");
                xmlSalida.append(respuestaServicio);
            xmlSalida.append("</RESULTADO>");
            */
            /*for(DotacionVO fila : lista)
            {
                xmlSalida.append("<FILA>");
                    xmlSalida.append("<ID>");
                        xmlSalida.append(fila.getId()!= null ? fila.getId().toString() : "");
                    xmlSalida.append("</ID>");
                    xmlSalida.append("<ID_ESPSOL>");
                        xmlSalida.append(fila.getIdEspSol()!= null ? fila.getIdEspSol().toString() : "");
                    xmlSalida.append("</ID_ESPSOL>");
                    xmlSalida.append("<DOT_NUM>");
                        xmlSalida.append(fila.getNumExp());
                    xmlSalida.append("</DOT_NUM>");
                    xmlSalida.append("<DOT_CANT>");
                        xmlSalida.append(fila.getCantidad()!= null ? fila.getCantidad().toString() : "");
                    xmlSalida.append("</DOT_CANT>");
                    xmlSalida.append("<DOT_DET>");
                        xmlSalida.append(fila.getDenominacionET());
                    xmlSalida.append("</DOT_DET>");
                    xmlSalida.append("<DOT_FAD>");
                        xmlSalida.append(fila.getFechaAdq());
                    xmlSalida.append("</DOT_FAD>");
                xmlSalida.append("</FILA>");
            }*/
        xmlSalida.append("</RESPUESTA>");
        log.error("XML Retornado a jsp: " + xmlSalida.toString());
        try{
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(xmlSalida.toString());
            out.flush();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }//try-catch

    }

    public void wsFSE_altaServicio(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response)
    {
        log.error("wsFSE_altaServicio - Begin()");
        String respuestaServicio = "";
        List<TerceroVO> lista = new ArrayList<TerceroVO>();
        TerceroVO _tercero = new TerceroVO();
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        int idiomaUsuario = 1;
        try
        {
            if (request.getParameter("idioma") != null) {
                try {
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                } catch (Exception ex) {
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }
            //Recojo los parametros
            String codTer = (String)request.getParameter("codTer");
            String versionTer = (String)request.getParameter("versionTer");
            String documento = (String)request.getParameter("documento");
            log.error("wsFSE_altaServicio - Parametros recibidos Request : codTer-" + codTer + "/ versionTer-" + versionTer + "/ documento-"+documento);
            lista = MeLanbideInteropManager.getInstance().getDatosTercerosxExpediente(codOrganizacion, numExpediente, adapt);
            if(lista.size()>0)
            {
                for(TerceroVO ter : lista)
                {
                    if(ter.getCodTer().equals(codTer) && ter.getVersionTercero().equals(versionTer)
                            && ter.getDoc().equals(documento)){
                        _tercero = ter;
                        break;
                    }
                }
            }

            /**
             * Parametros del Metodo */
            String cod_centro_usu="";
            String cod_ubic_usu="";
            String num_doc="";
            String tipo_doc="";
            String dem_servs_cod_serv="";
            String dem_servs_fech_ini="";
            String dem_servs_fech_fin="";
            String dem_servs_nro_horas="";
            String dem_servs_via_financ="";
            String dem_servs_texto_desc="";
            String dem_servs_nro_min="";
            String dem_servs_fec_sol="";
            String dem_servs_fec_ofe="";
            String dem_servs_resultado="";
            String dem_servs_mot_fin_ofe="";
            String dem_servs_fec_fin_ofe="";
            String dem_servs_centro="";
            String dem_servs_especialidad="";
            String dem_servs_acc_form="";
            String num_doc_orientador="";
            String tipo_doc_usuario="";
            String num_doc_usuario="";
            String password_usuario="";
            String cod_itinerario="";
            String os_ofe_id="";
            String firma_oid="";
            String idioma="";
            String origen="";
            String codigo_expediente="";

            // Cumplimentamos los parametros obligatorios
            //Código de centro el que realiza la llamada al WS
            cod_centro_usu=ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_CODIGO_CENTRO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            //Código de la ubicación del centro el que realiza la llamada al WS
            cod_ubic_usu=ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_CODIGO_UBICACION_CENTRO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            //Número de identificador más letra del demandante que se está consultando
            //Tipo de documento del demandante que se está consultando
            if(_tercero != null){
                if(_tercero.getTipoDoc().equals("1"))
                    tipo_doc="D";
                else if(_tercero.getTipoDoc().equals("3"))
                    tipo_doc="E";
                else if(_tercero.getTipoDoc().equals("8"))
                    tipo_doc="W";
                else if(_tercero.getTipoDoc().equals("9"))
                    tipo_doc="U";
                num_doc=_tercero.getDoc();
                log.error("wsFSE_altaServicio - TipoDocumento Tecero Calculado " + tipo_doc);
            }
            //Código del servicio a dar de alta	 	 	Número de 6 dígitos identificativo del procedimiento
            String[] datosExpediente = numExpediente.split(ConstantesMeLanbideInterop.BARRA_SEPARADORA);
            String codProcExpte = datosExpediente[1];
            dem_servs_cod_serv=ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_TIPOWS_FSE+ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO+codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            //Centro del servicio a dar de alta	 	 	00010-0002	Valor fijo
            dem_servs_centro=ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_CENTRO_SERVICIO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            //Número de identificador del orientador	 	 	1016G	El que inicia el expediente.
            num_doc_orientador=ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_IDENTIFICADOR_ORIENTADOR, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            // 	Tipo de documento del usuario que realiza la llamada al WS	 	 	D
            tipo_doc_usuario=ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_TIPO_DOC_USUARIO_LLAMADA, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            //	Número de identificador del usuario que realiza la llamada al WS	 	 	1016G	Valor fijo (¿en properties?)
            num_doc_usuario =ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_IDENTIFICADOR_USUARIO_LLAMADA, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            origen = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_CONSTANTE_ORIGEN_ALTASERVICIO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            codigo_expediente=numExpediente;
            // Mod. 2017/12/05
            HashMap<String,String> mapaParamAdicWhere = new HashMap<String, String>();
            if("LAK".equalsIgnoreCase(codProcExpte)){
                String campoAdicionalWhereLak = "DNI_TRAB";
                // Buscamos tambien con el documento al interesado,
                //en la tabla pueden haber mas de uno con mismo expediente
                mapaParamAdicWhere.put(campoAdicionalWhereLak, _tercero.getDoc());
            }
            dem_servs_fech_ini=getFechaInicioServicioFSE(codProcExpte,numExpediente,mapaParamAdicWhere,adapt);
            dem_servs_fech_fin=getFechaFinServicioFSE(codProcExpte,numExpediente,mapaParamAdicWhere,adapt);
            dem_servs_resultado=getResultadoParametroWSFSE(codProcExpte,numExpediente,adapt);
            log.error("Parametros pasados al Servicio :  cod_centro_usu,\n" +
                    "cod_ubic_usu,\n" +
                    "num_doc,\n" +
                    "tipo_doc,\n" +
                    "dem_servs_cod_serv,\n" +
                    "dem_servs_fech_ini,\n" +
                    "dem_servs_fech_fin,\n" +
                    "dem_servs_nro_horas,\n" +
                    "dem_servs_via_financ,\n" +
                    "dem_servs_texto_desc,\n" +
                    "dem_servs_nro_min,\n" +
                    "dem_servs_fec_sol,\n" +
                    "dem_servs_fec_ofe,\n" +
                    "dem_servs_resultado,\n" +
                    "dem_servs_mot_fin_ofe,\n" +
                    "dem_servs_fec_fin_ofe,\n" +
                    "dem_servs_centro,\n" +
                    "dem_servs_especialidad,\n" +
                    "dem_servs_acc_form,\n" +
                    "num_doc_orientador,\n" +
                    "tipo_doc_usuario,\n" +
                    "num_doc_usuario,\n" +
                    "password_usuario,\n" +
                    "cod_itinerario,\n" +
                    "os_ofe_id,\n" +
                    "firma_oid,\n" +
                    "idioma" +
                    "origen"
                    + "codigo_expediente");
            log.error(cod_centro_usu+","+
                    cod_ubic_usu+","+
                    num_doc+","+
                    tipo_doc+","+
                    dem_servs_cod_serv+","+
                    dem_servs_fech_ini+","+
                    dem_servs_fech_fin+","+
                    dem_servs_nro_horas+","+
                    dem_servs_via_financ+","+
                    dem_servs_texto_desc+","+
                    dem_servs_nro_min+","+
                    dem_servs_fec_sol+","+
                    dem_servs_fec_ofe+","+
                    dem_servs_resultado+","+
                    dem_servs_mot_fin_ofe+","+
                    dem_servs_fec_fin_ofe+","+
                    dem_servs_centro+","+
                    dem_servs_especialidad+","+
                    dem_servs_acc_form+","+
                    num_doc_orientador+","+
                    tipo_doc_usuario+","+
                    num_doc_usuario+","+
                    password_usuario+","+
                    cod_itinerario+","+
                    os_ofe_id+","+
                    firma_oid+","+
                    idioma+","+
                    origen+","+
                    codigo_expediente);
            //Invocamos la creacion del cliente

            LangaiDemanda  servicioFSE = getWSClientLangaiDemandaFSE();
            respuestaServicio = servicioFSE.altaServicio(cod_centro_usu,
                    cod_ubic_usu,
                    num_doc,
                    tipo_doc,
                    dem_servs_cod_serv,
                    dem_servs_fech_ini,
                    dem_servs_fech_fin,
                    dem_servs_nro_horas,
                    dem_servs_via_financ,
                    dem_servs_texto_desc,
                    dem_servs_nro_min,
                    dem_servs_fec_sol,
                    dem_servs_fec_ofe,
                    dem_servs_resultado,
                    dem_servs_mot_fin_ofe,
                    dem_servs_fec_fin_ofe,
                    dem_servs_centro,
                    dem_servs_especialidad,
                    dem_servs_acc_form,
                    num_doc_orientador,
                    tipo_doc_usuario,
                    num_doc_usuario,
                    password_usuario,
                    cod_itinerario,
                    os_ofe_id,
                    firma_oid,
                    idioma,
                    origen,
                    codigo_expediente
                    ,""
                    ,""
                    ,""
                    ,""
            );

            if(respuestaServicio==null || respuestaServicio.isEmpty() || respuestaServicio.length()==0)
            {
                log.error(" wsFSE_altaServicio - Error - No se ha podido recibir respuesta del Servicio LangaiDemanda FSE : "
                        + numExpediente
                        + " - " + num_doc
                );
                respuestaServicio = "-1";
            }

        }
        catch(Exception ex)
        {
            log.error("wsFSE_altaServicio - Exception general al llamar al WS de langai Demanda. "
                    + numExpediente  + ex.getMessage()
            );
            if(respuestaServicio==null || respuestaServicio.isEmpty())
                respuestaServicio = "-2";
        }
        log.error("respuestaServicio" + respuestaServicio);
        // Traducimos los codigos del Mesaje de salida ( Son mas de 1000 no se pueden traducir en JSP)
        String respuestaServicioTraducido = traducirCodigosMensajesSalidaWSLangaiDemandaFSE(respuestaServicio,idiomaUsuario);
        StringBuffer xmlSalida = new StringBuffer();
        xmlSalida.append("<RESPUESTA>");
        xmlSalida.append("<CODIGO_OPERACION>");
        xmlSalida.append(respuestaServicioTraducido);
        xmlSalida.append("</CODIGO_OPERACION>");
        xmlSalida.append("</RESPUESTA>");
        log.error("XML Retornado a jsp: " + xmlSalida.toString());
        try{
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(xmlSalida.toString());
            out.flush();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }//try-catch

    }
    public void WS_verificarDatosPadron(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response)
    {
        log.error("WS_verificarDatosPadron - Begin()");
        String respuestaServicio = "";
        List<TerceroVO> lista = new ArrayList<TerceroVO>();
        TerceroVO _tercero = new TerceroVO();

        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        int idiomaUsuario = 1;
        try
        {
            if (request.getParameter("idioma") != null) {
                try {
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                } catch (Exception ex) {
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }
            String codigoProcFlexia = MeLanbideInteropGeneralUtils.getCodProcedimientoFromNumExpediente(numExpediente);
            //Recojo los parametros
            String codTer = (String)request.getParameter("codTer");
            String versionTer = (String)request.getParameter("versionTer");
            String documento = (String)request.getParameter("documento");
            log.error("WS_verificarDatosPadron - Parametros recibidos Request : codTer-" + codTer + "/ versionTer-" + versionTer + "/ documento-"+documento);
            lista = MeLanbideInteropManager.getInstance().getDatosTercerosxExpediente(codOrganizacion, numExpediente, adapt);
            if(lista.size()>0)
            {
                for(TerceroVO ter : lista)
                {
                    if(ter.getCodTer().equals(codTer) && ter.getVersionTercero().equals(versionTer)
                            && ter.getDoc().equals(documento)){
                        _tercero = ter;
                        break;
                    }
                }
            }
            String num_doc="";
            String tipo_doc="";
            //Tipo de documento del demandante que se está consultando
            if(_tercero != null){
                tipo_doc=MeLanbideInteropGeneralUtils.parsearTipoDocFlexiaToTipoDocEJIE_WS(_tercero.getTipoDoc());
                log.error("WS_verificarDatosPadron - TipoDocumento Tecero Calculado " + tipo_doc);
            }
            // Parametrizamos la llamada
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Peticion peticion = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Peticion();
            String idPeticion = codigoProcFlexia + new Date();
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Atributos atributos = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Atributos();
            atributos.setIdPeticion(idPeticion);
            atributos.setNumElementos(1);
            atributos.setTimeStamp(new Date().toString());
            atributos.setCodigoCertificado(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_CERTIFICADO+ConstantesMeLanbideInterop.CODIGO_WS_DATOSPADRON, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));

            es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Estado estado = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Estado();
            estado.setCodigoEstado(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.EJIE_CODIGO_ESTADO_PETICION,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            estado.setLiteralError(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.EJIE_ESTADO_PETICION,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            atributos.setEstado(estado);

            es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Solicitudes solicitudes = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Solicitudes();
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.SolicitudTransmision solicitudTransmision = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.SolicitudTransmision();
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.DatosGenericos datosGenericos = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.DatosGenericos();
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Emisor emisor = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Emisor();
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Solicitante solicitante  = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Solicitante();
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Procedimiento procedimiento  = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Procedimiento();
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Funcionario funcionario  = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Funcionario();
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Titular titular  = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Titular();
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Transmision transmision  = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.peticionDatosPadron.Transmision();
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.DatosEspecificosPadronIndividual datosEspecificosPadronIndividual = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.DatosEspecificosPadronIndividual();
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.Consulta consulta = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.Consulta();
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.DatosEntradaConsulta datosEntradaConsulta = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.DatosEntradaConsulta();
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.DatosEntradaPadron datosEntradaPadron = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.DatosEntradaPadron();

            datosEntradaConsulta.setTerritorioConsulta(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.EJIE_CODIGO_TERRITORIOHIST_ALAVA,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            datosEntradaConsulta.setMunicipioConsulta(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.EJIE_CODIGO_MUNICIPIO_VITORIA,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));

            datosEntradaPadron.setTipoDocumento(codTer);
            datosEntradaPadron.setNumDocumento(num_doc);
            datosEntradaPadron.setNombre(_tercero.getNombre());
            datosEntradaPadron.setApellido1(_tercero.getApellido1());

            consulta.setDatosEntradaConsulta(datosEntradaConsulta);
            consulta.setDatosEntradaPadron(datosEntradaPadron);

            datosEspecificosPadronIndividual.setConsulta(consulta);

            emisor.setNifEmisor(ConfigurationParameter.getParameter("WS_DATOSPADRON_EMISOR_NIF",ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            emisor.setNombreEmisor(ConfigurationParameter.getParameter("WS_DATOSPADRON_EMISOR_NOMBRE",ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));

            solicitante.setIdentificadorSolicitante(ConfigurationParameter.getParameter("ID_ORGANO_SOLICITANTE",ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            solicitante.setNombreSolicitante(ConfigurationParameter.getParameter("ORGANO_SOLICITANTE",ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            solicitante.setUnidadTramitadora(ConfigurationParameter.getParameter("UNIDAD_TRAMITADORA",ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            procedimiento.setCodProcedimiento(getCodProcedimientoDACIMAFromProperties(codigoProcFlexia));
            procedimiento.setNombreProcedimiento(codigoProcFlexia);
            solicitante.setProcedimiento(procedimiento);
            solicitante.setFinalidad(codigoProcFlexia);
            solicitante.setConsentimiento(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONSENTIMIENTO_SI,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            UsuarioValueObject usuarioValueObject = getUsarioLogueadoEnSession(request);
            String cifUsuarioLogueado="";
            String nombreUsuarioLogueado="";
            if(usuarioValueObject!=null){
                cifUsuarioLogueado = MeLanbideInteropManager.getInstance().getUsuarioNIF(usuarioValueObject.getIdUsuario(),adapt);
                funcionario.setNifFuncionario(cifUsuarioLogueado);
                nombreUsuarioLogueado=usuarioValueObject.getNombreUsu();
                funcionario.setNombreCompletoFuncionario(usuarioValueObject.getNombreUsu());
            }else{
                log.error("Usuario no recogido desde session al llmar al WS de Datos de Padron.");
            }
            solicitante.setFuncionario(funcionario);
            solicitante.setIdExpediente(numExpediente);

            titular.setTipoDocumentacion(tipo_doc);
            titular.setDocumentacion(documento);

            if(atributos!=null){
                transmision.setCodigoCertificado(atributos.getCodigoCertificado());
                transmision.setIdSolicitud(atributos.getIdPeticion());
            }else{
                log.error("Clase atributos a null, no cargamos datos de trasmision");
            }

            datosGenericos.setEmisor(emisor);
            datosGenericos.setSolicitante(solicitante);
            datosGenericos.setTitular(titular);
            datosGenericos.setTransmision(transmision);

            solicitudTransmision.setDatosEspecificos(datosEspecificosPadronIndividual);
            solicitudTransmision.setDatosGenericos(datosGenericos);

            solicitudes.setSolicitudTransmision(solicitudTransmision);

            peticion.setAtributos(atributos);
            peticion.setSolicitudes(solicitudes);


            //Invocamos la creacion del cliente
            String nombreModulo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_MODULO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            String urlString = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.URL_SERVICIO_DATOSPADRON, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("URL: " + urlString);
            //log.error("URL-VO-: "+ datosWS.getUrlWebService());
            String nameSpaceUri = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.QNAME_NAMESPACEURI_DATOSPADRON, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("NameSpaceUri: " + nameSpaceUri);
            String localPart = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.QNAME_LOCALPART_DATOSPADRON, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("LocalPart: " + localPart);

            /***
             * Vamos a probar las llamadas mediante RestService
             *
             */
            String urlRestServiceStr=ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.URL_REST_SERVICE_PADRON, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.info("Url del Rest Service : " + urlRestServiceStr);
            URL urlRestService = new URL (urlRestServiceStr);
            HttpURLConnection conRS = (HttpURLConnection)urlRestService.openConnection();
            conRS.setRequestMethod("POST");
            // Formato de la peticion
            conRS.setRequestProperty("Content-Type", "application/json");
            // Set the ?Accept? request header to ?application/json? to read the response in the desired format
            conRS.setRequestProperty("Accept", "application/json");
            // Ensure the Connection Will Be Used to Send Content : //To send request content, let's enable the URLConnection object's doOutput property to true. Otherwise, we'll not be able to write content to the connection output stream:
            conRS.setDoOutput(true);
            conRS.setDoInput(true);
            // Preparamos el cuerpo del JSON
            RequestRestServicePadron requestRestServicePadron = new RequestRestServicePadron();
            Tramitador tramitador = new Tramitador();
            DocumentoPersona documentoPersona = new DocumentoPersona();
            List<DocumentoPersona> listaDocumentosPersonas = new ArrayList<DocumentoPersona>();
            requestRestServicePadron.setApellido1(_tercero.getApellido1());
            requestRestServicePadron.setApellido2(_tercero.getApellido2());
            DateFormat dFormat = new SimpleDateFormat("dd/mm/yyyy");
            if(_tercero.getTFecNacimiento()!=null){
                String dateString = dFormat.format(_tercero.getTFecNacimiento());
                requestRestServicePadron.setFechaNacimiento(dateString);
            }
            requestRestServicePadron.setMunicipioNoraReferencia(_tercero.getCodigoMunicipioDom());
            requestRestServicePadron.setNombrePersona(_tercero.getNombre());
            requestRestServicePadron.setProvinciaNoraReferencia(_tercero.getCodigoProvinciaDom());
            documentoPersona.setNumDoc(_tercero.getDoc());
            String tipDoc = ("1".equalsIgnoreCase(_tercero.getTipoDoc()) ? "D" : "3".equalsIgnoreCase(_tercero.getTipoDoc()) ? "E" : "2".equalsIgnoreCase(_tercero.getTipoDoc()) ? "P" :"");
            documentoPersona.setTipoDoc(tipDoc);
            listaDocumentosPersonas.add(documentoPersona);
            requestRestServicePadron.setDocumentosPersona(listaDocumentosPersonas);
            tramitador.setNifUsuarioTramitador(cifUsuarioLogueado);
            tramitador.setUsuarioTramitador(nombreUsuarioLogueado);

            tramitador.setProcedimientoPadron(ConfigurationParameter.getParameter("CODIGO_PROCEDIMIENTO_PADRON_"+codigoProcFlexia,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            tramitador.setNombreProcedimientoAutorizado(ConfigurationParameter.getParameter("NOMBRE_PROCEDIMIENTO_AUTORIZADO_PADRON_"+codigoProcFlexia,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            tramitador.setFinalidadProcedimiento(ConfigurationParameter.getParameter("FINALIDAD_PROCEDIMIENTO_PADRON_"+codigoProcFlexia,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            tramitador.setConsentimientoFirmado(ConfigurationParameter.getParameter("CONSENTIMIENTO_FIRMADO_PADRON_"+codigoProcFlexia,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            tramitador.setAutorizacionLlamarINE(ConfigurationParameter.getParameter("AUTORIZACION_LLAMAR_INE_"+codigoProcFlexia,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            requestRestServicePadron.setTramitador(tramitador);
            Gson gson = new Gson();
            GsonBuilder gsonBuilder = new GsonBuilder();
            //gsonBuilder.serializeNulls();
            gson = gsonBuilder.create();
            String JsonString = gson.toJson(requestRestServicePadron);
            log.info("Texto LLamada : " + JsonString);
            try{
                OutputStream os = conRS.getOutputStream();
                byte[] input = JsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }catch(Exception ex){
                log.error("Error preparando la llamada al WS .. " + ex.getMessage(), ex);
                respuestaServicio="-7 Error al preparar la llamada al WS... " + ex.getMessage();
            }
            log.info("Hemos escrito la llamada .. Proeemos  recoger la respuesta ..");
            try {
                log.info(" Respuesta : " + conRS.getResponseCode() + " - " + conRS.getResponseMessage());
                BufferedReader br = new BufferedReader(new InputStreamReader(conRS.getInputStream(), "utf-8"));
                StringBuilder responseJSON = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    responseJSON.append(responseLine.trim());
                }
                log.info("Despues de leer la respuesta... " + responseJSON);
                ResponseRestServicePadron responseRestServicePadron = gson.fromJson(responseJSON.toString(), ResponseRestServicePadron.class);
                respuestaServicio=responseRestServicePadron.getCodRespuesta() + " - " + responseRestServicePadron.getDescRespuesta();
                String respuestaDetalle=" " +
                        "	Tipo Padron : " + responseRestServicePadron.getPadron().getTipoPadron() + " " +
                        "	Tipo Doc : " + responseRestServicePadron.getPadron().getTipoDoc() + " " +
                        "	Num Doc : " + responseRestServicePadron.getPadron().getNumDoc() + " " +
//                                        "	Nombre Persona : " + responseRestServicePadron.getPadron().getNombrePersona() + " " +
//                                        "	Apellido1Persona : " + responseRestServicePadron.getPadron().getApellido1Persona() + " " +
//                                        "	apellido2Persona : "+ responseRestServicePadron.getPadron().getApellido2Persona() + " " +
//                                        "	Fecha Nacimiento : " + responseRestServicePadron.getPadron().getFechaNacimiento() + " " +
                        "	Cod Provincia : " + responseRestServicePadron.getPadron().getCodProvincia() + " " +
                        "	Desc Provincia : " + responseRestServicePadron.getPadron().getDescProvincia() + " " +
                        "	Cod Municio : " + responseRestServicePadron.getPadron().getCodMunicio() + " " +
                        "	Desc Municipio : " + responseRestServicePadron.getPadron().getDescMunicipio() + " " +
                        "	Codigo Via : " + responseRestServicePadron.getPadron().getCodigoVia() + " " +
                        "	NombreVia : " + responseRestServicePadron.getPadron().getNombreVia() + " " +
//                                        "	NombreViaEusk : " + responseRestServicePadron.getPadron().getNombreViaEusk() + " " +
//                                        "	EntidadSingular : " + responseRestServicePadron.getPadron().getEntidadSingular() + " " +
//                                        "	EntidadColectiva : " + responseRestServicePadron.getPadron().getEntidadColectiva() + " " +
                        "	Nucleo : " + responseRestServicePadron.getPadron().getNumDoc() + " " +
//                                        "	CodUnidadPoblacional : " + responseRestServicePadron.getPadron().getCodUnidadPoblacional() + " " +
                        "	CodUltVariacion : " + responseRestServicePadron.getPadron().getCodUltVariacion() + " " +
                        "	DescUltVariacion : " + responseRestServicePadron.getPadron().getDescUltVariacion() + " " +
                        "	Bloque : " + responseRestServicePadron.getPadron().getBloque() + " " +
                        "	Portal : " + responseRestServicePadron.getPadron().getPortal() + " " +
                        "	Numero : " + responseRestServicePadron.getPadron().getNumero() + " " +
                        "	Kmt : " + responseRestServicePadron.getPadron().getKmt() + " " +
                        "	Hmt : " + responseRestServicePadron.getPadron().getHmt() + " " +
                        "	Bis : " + responseRestServicePadron.getPadron().getBis() + " " +
                        "	Escalera : " + responseRestServicePadron.getPadron().getEscalera() + " " +
                        "	Planta : " + responseRestServicePadron.getPadron().getPlanta() + " " +
                        "	Puerta : " + responseRestServicePadron.getPadron().getPuerta() + " " +
                        "	Cod Postal : " + responseRestServicePadron.getPadron().getCodPostal() + " " +
//                                        "	Codigo Pais Nacimiento : " + responseRestServicePadron.getPadron().getCodigoPaisNacimiento() + " " +
//                                        "	Nombre Pais Nacimiento : " + responseRestServicePadron.getPadron().getNombrePaisNacimiento() + " " +
//                                        "	Codigo Provincia Nacimiento : " + responseRestServicePadron.getPadron().getCodigoProvinciaNacimiento() + " " +
//                                        "	Nombre Provincia Nacimiento : " + responseRestServicePadron.getPadron().getNombreProvinciaNacimiento() + " " +
//                                        "	Codigo Municipio Nacimiento : " + responseRestServicePadron.getPadron().getCodigoMunicipioNacimiento() + " " +
//                                        "	Nombre Municipio Nacimiento : " + responseRestServicePadron.getPadron().getNombreMunicipioNacimiento() + " " +
//                                        "	Sexo : " + responseRestServicePadron.getPadron().getSexo() + " " +
                        "	Fecha Alta Padron : " + responseRestServicePadron.getPadron().getFechaAltaPadron() + " " +
                        "	Fecha Ultima Variacion : " + responseRestServicePadron.getPadron().getFechaUltimaVariacion() + " " +
                        "	Nora Calle : " + responseRestServicePadron.getPadron().getNoraCalle() + " " +
                        "	Nora Portal : " + responseRestServicePadron.getPadron().getNoraPortal() + " " +
                        " ";
                respuestaServicio += " -  " + respuestaDetalle;
            } catch (Exception ex) {
                log.error("Error preparando la respuesta al WS .. " + ex.getMessage(), ex);
                respuestaServicio+= "-8 Error al procesar la respuesta del WS... " + ex.getMessage();
            }

            /***
             *  FIn de la prueba Invocacion RestService
             *
             */
            /*
            PadronIndividualPortType  servicioDatosPadron = getWSClientDatosPadron(urlString, nameSpaceUri, localPart);
            es.altia.interoperabilidad.datamodel.getDatosPadronWS.respuestaDatosPadron.Respuesta respuesta = servicioDatosPadron.getPadronIndividualWS(peticion);

            if (respuesta != null) {
                log.error("Respuesta del WS Diferente  a Null -- ");
                es.altia.interoperabilidad.datamodel.getDatosPadronWS.respuestaDatosPadron.Atributos atributos1 = respuesta.getAtributos();
                es.altia.interoperabilidad.datamodel.getDatosPadronWS.respuestaDatosPadron.Transmisiones transmisiones = respuesta.getTransmisiones();
                if (atributos1 != null) {
                    es.altia.interoperabilidad.datamodel.getDatosPadronWS.respuestaDatosPadron.Estado estadoRespuesta = atributos1.getEstado();
                    if (estadoRespuesta != null) {
                        log.error("-- Atributos.getEstado Diferente de null --- ASignamos respuesta del WService");
                        respuestaServicio = estadoRespuesta.getCodigoEstado();
                        log.error("-- estadoRespuesta.getCodigoEstado() : " + estadoRespuesta.getCodigoEstado());
                        if (estadoRespuesta.getCodigoEstadoSecundario() != null && estadoRespuesta.getCodigoEstadoSecundario()!="") {
                            respuestaServicio += "/" + estadoRespuesta.getCodigoEstadoSecundario();
                            log.error("-- estadoRespuesta.getCodigoEstadoSecundario() : " + estadoRespuesta.getCodigoEstadoSecundario());
                        }
                        log.error("-- estadoRespuesta.getLiteralError() : " + estadoRespuesta.getLiteralError());
                        respuestaServicio += " - " + estadoRespuesta.getLiteralError();
                        log.error("-- Texto Return Preparado - Salida WS  : " + respuestaServicio);
                    }
                    log.error("Atributos.getNumElementos" + atributos1.getNumElementos());
                    log.error("Atributos.getIdPeticion" + atributos1.getIdPeticion());
                    log.error("Atributos.getTimeStamp" + atributos1.getTimeStamp());
                    log.error("Atributos.getCodigoCertificado" + atributos1.getCodigoCertificado());
                } else {
                    log.error(" atributos de la respuesta viene a null");
                }

                if (transmisiones != null) {
                    es.altia.interoperabilidad.datamodel.getDatosPadronWS.respuestaDatosPadron.TransmisionDatos transmisionDatos = transmisiones.getTransmisionDatos();
                    if (transmisionDatos != null) {
                        es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.DatosEspecificosPadronIndividual datosEspecificos1 = transmisionDatos.getDatosEspecificos();
                        es.altia.interoperabilidad.datamodel.getDatosPadronWS.respuestaDatosPadron.DatosGenericos datosGenericos1 = transmisionDatos.getDatosGenericos();
                        if (datosGenericos1 != null) {
                            log.error("transmisiones.getTransmisionDatos().getDatosGenericos() - Trae datos");
                        } else {
                            log.error("transmisiones.getTransmisionDatos().getDatosGenericos() de la respuesta viene a null");
                        }
                        if (datosEspecificos1 != null) {
                            es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.Retorno retorno = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.Retorno();
                            if(retorno!=null){
                                es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.EstadoResultado estadoResultado = new es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.EstadoResultado();
                                estadoResultado = retorno.getEstadoResultado();
                                if(estadoResultado!=null){
                                    respuestaServicio+="\n Interesado Encontrado: " + estadoResultado.getResultado() + " -  " + estadoResultado.getDescripcion();
                                    if(estadoResultado.getMotivosError()!=null && estadoResultado.getMotivosError()!=""){
                                        respuestaServicio+="\n " + estadoResultado.getMotivosError();
                                        log.error("datosEspecificos1.getRetorno().getEstadoResultado().getMotivosError() : " + datosEspecificos1.getRetorno().getEstadoResultado().getMotivosError());
                                    }else{
                                        log.error("datosEspecificos1.getRetorno().getEstadoResultado().getMotivosError() es Null");
                                    }
                                    log.error("datosEspecificos1.getRetorno().getEstadoResultado().getDescripcion() : " + estadoResultado.getDescripcion());
                                    log.error("datosEspecificos1.getRetorno().getEstadoResultado().getResultado() : " + estadoResultado.getResultado());
                                }
                                es.altia.interoperabilidad.datamodel.getDatosPadronWS.datosEspecificosDatosPadron.DatosSalidaPadron datosSalidaPadron =retorno.getDatosSalidaPadron();
                                if(datosSalidaPadron!=null){
                                    Habitantes habitantes = datosSalidaPadron.getHabitantes();
                                    if(habitantes!=null){
                                        if(habitantes.getHabitante()!=null){
                                            String datosObtenidos = "\n Datos Obtenidos:";
                                            for (Habitante h : habitantes.getHabitante()) {
                                                datosObtenidos+="\n " + h.getNombre() + " " + h.getApellido1() + " " + h.getApellido2();
                                                datosObtenidos+="\n " + h.getDatosInteres();
                                                DomicilioPadron domicilioPadron= h.getDomicilioPadron();
                                                if(domicilioPadron!=null){
                                                    String textoDireccion = domicilioPadron.getNombreViaCastellano()
                                                            + " " + domicilioPadron.getPortal()
                                                            + " " + domicilioPadron.getBloque()
                                                            + " " + domicilioPadron.getEscalera()
                                                            + " " + domicilioPadron.getPlanta()
                                                            + " " + domicilioPadron.getPuerta()
                                                            + "\n " + domicilioPadron.getCodigoPostal()
                                                            + "\n " + domicilioPadron.getNombreMunicipioResidencia()
                                                            + "\n " + domicilioPadron.getNombreProvinciaResidencia()
                                                            ;
                                                    datosObtenidos+="\n " + textoDireccion;
                                                }

                                            }
                                            respuestaServicio+="\n " + datosObtenidos;
                                        }
                                    }
                                }
                            }
                        } else {
                            log.error("transmisiones.getTransmisionDatos().getDatosEspecificos() de la respuesta viene a null");
                        }
                    } else {
                        log.error("transmisiones.getTransmisionDatos() de la respuesta viene a null");
                    }
                } else {
                    log.error("trasmisiones de la respuesta viene a null");
                }
            } else {
                log.error(" WS_verificarDatosPadron - Error - No se ha podido recibir respuesta del Servicio Verificacion Datos de InteropCvlMasivoResultadoVO: "
                        + numExpediente
                        + " - " + num_doc
                        );
                respuestaServicio = "-1 : No se obtuvo respuesta del servicio.";
            }
            */
        }
        catch(Exception ex)
        {
            log.error("WS_verificarDatosPadron - Exception general al llamar al WS de datos del padron. "
                    + numExpediente  + ex.getMessage()
            );
            if(respuestaServicio==null || respuestaServicio.isEmpty())
                respuestaServicio = "-2 :  Error general al acceder al Servicio. Consulte al equipo de soporte para mas detalles.";
        }
        log.error("respuestaServicio : " + respuestaServicio);
        /*
        Código estado	Código negocio	Descripción del resultado del negocio	Descripción estado /Motivos de error del negocio
        0226                                                    Error de validación en parámetros de entrada
        0003	S	Sí se han encontrado Habitantes.	TRAMITADA
        0233	N	No se han encontrado Habitantes.	Titular no identificado.
        0231	E	Existen incidencias.                    [00] - Tipo de documento incorrecto.
        0231	E	Existen incidencias.                    [01] - Formato de DNI/NIE incorrecto.
        0252	E	Existen incidencias.                    [02] - Formato de Fecha erróneo.
        0254	E	Existen incidencias.                    [03] - Faltan datos obligatorios.
        0227	E	Existen incidencias.                    [04] - Error en Base de datos del servicio.
        0901	E	Existen incidencias.                    [05] - Error interno del servicio.
        0901	E	Existen incidencias.                    [99] - Error general o desconocido.

        */
        StringBuffer xmlSalida = new StringBuffer();
        xmlSalida.append("<RESPUESTA>");
        xmlSalida.append("<CODIGO_OPERACION>");
        xmlSalida.append(respuestaServicio);
        xmlSalida.append("</CODIGO_OPERACION>");
        xmlSalida.append("</RESPUESTA>");
        log.error("XML Retornado a jsp: " + xmlSalida.toString());
        try{
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(xmlSalida.toString());
            out.flush();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }//try-catch

    }

    public void WS_verificarDatosResidencia(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response)
    {
        log.error("WS_verificarDatosResidencia - Begin()");
        String respuestaServicio = "";
        List<TerceroVO> lista = new ArrayList<TerceroVO>();
        TerceroVO _tercero = new TerceroVO();
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        int idiomaUsuario = 1;
        try
        {
            if (request.getParameter("idioma") != null) {
                try {
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                } catch (Exception ex) {
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }
            String codigoProcFlexia = MeLanbideInteropGeneralUtils.getCodProcedimientoFromNumExpediente(numExpediente);
            //Recojo los parametros
            String codTer = (String)request.getParameter("codTer");
            String versionTer = (String)request.getParameter("versionTer");
            String documento = (String)request.getParameter("documento");
            log.error("WS_verificarDatosResidencia - Parametros recibidos Request : codTer-" + codTer + "/ versionTer-" + versionTer + "/ documento-"+documento);
            lista = MeLanbideInteropManager.getInstance().getDatosTercerosxExpediente(codOrganizacion, numExpediente, adapt);
            if(lista.size()>0)
            {
                for(TerceroVO ter : lista)
                {
                    if(ter.getCodTer().equals(codTer) && ter.getVersionTercero().equals(versionTer)
                            && ter.getDoc().equals(documento)){
                        _tercero = ter;
                        break;
                    }
                }
            }
            String num_doc="";
            String tipo_doc="";
            //Tipo de documento del demandante que se está consultando
            if(_tercero != null){
                tipo_doc=MeLanbideInteropGeneralUtils.parsearTipoDocFlexiaToTipoDocEJIE_WS(_tercero.getTipoDoc());
                num_doc=_tercero.getDoc();
                log.error("WS_verificarDatosResidencia - TipoDocumento Tecero Calculado " + tipo_doc);
            }
            // Parametrizamos la llamada

            String pOrganoSolicitante= ConfigurationParameter.getParameter("ORGANO_SOLICITANTE",ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            String pUnidadTramitadora= ConfigurationParameter.getParameter("UNIDAD_TRAMITADORA",ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            String pCodigoProcedimiento= getCodProcedimientoDACIMAFromProperties(codigoProcFlexia);
            String pNombreProcedimiento= codigoProcFlexia;
            String pFinalidad= codigoProcFlexia;
            String pConsentimiento=ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONSENTIMIENTO_SI,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);

            String pNifTramitador= "";
            String pNombreTramitador= "";
            UsuarioValueObject usuarioValueObject = getUsarioLogueadoEnSession(request);
            if(usuarioValueObject!=null){
                pNifTramitador = MeLanbideInteropManager.getInstance().getUsuarioNIF(usuarioValueObject.getIdUsuario(),adapt);
                pNombreTramitador = usuarioValueObject.getNombreUsu();
            }else{
                log.error("Usuario no recogido desde session al llmar al WS de Datos de Padron.");
            }

            String pIdExpediente= numExpediente;
            String pTipoTramite=ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TIPO_TRAMITE_VERIFICACION, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            String pTipoDocumento= tipo_doc;
            String pNumDocumento= num_doc;

            log.error("Parametros pasados al WS : (pOrganoSolicitante" +
                    ",pUnidadTramitadora" +
                    ",pCodigoProcedimiento" +
                    ",pNombreProcedimiento" +
                    ",pFinalidad" +
                    ",pConsentimiento" +
                    ",pNifTramitador" +
                    ",pNombreTramitador" +
                    ",pIdExpediente" +
                    ",pTipoTramite" +
                    ",pTipoDocumento" +
                    ",pNumDocumento) : "
                    + pOrganoSolicitante
                    +","+pUnidadTramitadora
                    +","+pCodigoProcedimiento
                    +","+pNombreProcedimiento
                    +","+pFinalidad
                    +","+pConsentimiento
                    +","+pNifTramitador
                    +","+pNombreTramitador
                    +","+pIdExpediente
                    +","+pTipoTramite
                    +","+pTipoDocumento
                    +","+pNumDocumento);

            //Invocamos la creacion del cliente
            String nombreModulo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_MODULO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            String urlString = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.URL_SERVICIO_DATOSRESIDENCIA, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("URL: " + urlString);
            String nameSpaceUri = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.QNAME_NAMESPACEURI_DATOSRESIDENCIA, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("NameSpaceUri: " + nameSpaceUri);
            String localPart = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.QNAME_LOCALPART_DATOSRESIDENCIA, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("LocalPart: " + localPart);

            X53JsGetDatosResidenciaWSDelegate  servicioDatosResidencia = getWSClientDatosResidencia(urlString, nameSpaceUri, localPart);
            String respuesta = servicioDatosResidencia.getDatosResidenciaWS(pOrganoSolicitante
                    ,pUnidadTramitadora
                    ,pCodigoProcedimiento
                    ,pNombreProcedimiento
                    ,pFinalidad
                    ,pConsentimiento
                    ,pNifTramitador
                    ,pNombreTramitador
                    ,pIdExpediente
                    ,pTipoTramite
                    ,pTipoDocumento
                    ,pNumDocumento
                    ,"","","","","","","","","","","",""
            );

            if (respuesta != null) {
                log.error("Respuesta del WS : " + respuesta);
                respuestaServicio = tratarRespuestaXMLWServices(respuesta);
            } else {
                log.error(" WS_verificarDatosResidencia - Error - No se ha podido recibir respuesta del Servicio Verificacion Datos de Padron: "
                        + numExpediente
                        + " - " + num_doc
                );
                respuestaServicio = "-1 : No se obtuvo respuesta del servicio.";
            }
        }
        catch(Exception ex)
        {
            log.error("WS_verificarDatosResidencia - Exception general al llamar al WS de datos del padron. "
                    + numExpediente  + ex.getMessage()
            );
            if(respuestaServicio==null || respuestaServicio.isEmpty())
                respuestaServicio = "-2 :  Error general al acceder al Servicio. Consulte al equipo de soporte para mas detalles. \n" + ex.getMessage() ;
        }
        log.error("respuestaServicio : " + respuestaServicio);
        StringBuffer xmlSalida = new StringBuffer();
        xmlSalida.append("<RESPUESTA>");
        xmlSalida.append("<CODIGO_OPERACION>");
        xmlSalida.append(respuestaServicio);
        xmlSalida.append("</CODIGO_OPERACION>");
        xmlSalida.append("</RESPUESTA>");
        log.error("XML Retornado a jsp: " + xmlSalida.toString());
        try{
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(xmlSalida.toString());
            out.flush();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }//try-catch

    }
   public void GetConsultaCorrientePagoTGSS(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response)
{
    String codigoOperacion = "0";
    List<TerceroVO> lista = new ArrayList<TerceroVO>();
    TerceroVO _tercero = new TerceroVO();
    AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
    String respuestaServicio = "";

    try
    {
        String[] datosExpediente = numExpediente.split(ConstantesMeLanbideInterop.BARRA_SEPARADORA);
        String codProcExpte = datosExpediente[1];
        //Recojo los parametros
        String codTer = (String)request.getParameter("codTer");
        String documento = (String)request.getParameter("documento");

        lista = MeLanbideInteropManager.getInstance().getDatosTercerosxExpediente(codOrganizacion, numExpediente, adapt);
        if(lista.size()>0)
        {
            if(codTer != null && documento != null)
            {
                // Pantalla con tabla de terceros: usar el tercero seleccionado
                for(TerceroVO ter : lista)
                {
                    if(codTer.equals(ter.getCodTer()) && documento.equals(ter.getDoc())){
                        _tercero = ter;
                        break;
                    }
                }

                // Si no se encuentra el tercero seleccionado, usar fallback
                if(_tercero.getCodTer() == null)
                {
                    for(TerceroVO ter : lista)
                    {
                        if("1".equals(ter.getCodRol())){
                            _tercero = ter;
                            break;
                        }
                    }

                    // Si no hay titular con rol 1, usar el primero
                    if(_tercero.getCodTer() == null){
                        _tercero = lista.get(0);
                        log.info("GetConsultaCorrientePagoTGSS - No se encontro el tercero seleccionado (" + codTer + " / " + documento + "), usando primer tercero: " + _tercero.getCodTer() + " / " + _tercero.getDoc());
                    } else {
                        log.info("GetConsultaCorrientePagoTGSS - No se encontro el tercero seleccionado (" + codTer + " / " + documento + "), usando titular rol 1: " + _tercero.getCodTer() + " / " + _tercero.getDoc());
                    }
                }
            }
            else
            {
                // Pantalla sin tabla de terceros (Gen): usar el titular (rol 1)
                for(TerceroVO ter : lista)
                {
                    if("1".equals(ter.getCodRol())){
                        _tercero = ter;
                        break;
                    }
                }

                // Si no hay titular con rol 1, usar el primero como fallback
                if(_tercero.getCodTer() == null){
                    _tercero = lista.get(0);
                    log.info("GetConsultaCorrientePagoTGSS - No se encontro titular rol 1, usando primer tercero: " + _tercero.getCodTer() + " / " + _tercero.getDoc());
                } else {
                    log.info("GetConsultaCorrientePagoTGSS - Usando titular rol 1: " + _tercero.getCodTer() + " / " + _tercero.getDoc());
                }
            }
        }

        // Cogemos los datos del Usuario conectadp a la aplicaci�n.
        UsuarioValueObject usuario = new UsuarioValueObject();
        String usuario_nif = "";
        HttpSession session = request.getSession();
        try {
            if (session != null) {
                if (usuario != null) {
                    usuario = (UsuarioValueObject) session.getAttribute("usuario");
                }
            }
        } catch (Exception ex) {
            log.error("Error al recuperar los datos del Usuario desde la session", ex);
        }
        usuario_nif = MeLanbideInteropManager.getInstance().getUsuarioNIF(usuario.getIdUsuario(), adapt);

        String unidadTramitadora = usuario.getDepCod() + " - " + usuario.getDep();//"Departamento de Educaci�n";

        // Para primera pruebas sino existe en properties cogemo el de conciliacion
        String codigoProcedimiento = "";// 10133 Conciliacion CONCM     "SVDI_000233";
        codigoProcedimiento = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
        if ("".equals(codigoProcedimiento)) {
            // Sino se recupera del properties deber� devolver error
            // Sin embargo en estas primeras pruebas recogemos el CONCM
            log.error("Codigo procedimiento no  configurado para consultas en MELANBIDE_INTEROP.properties :  " + ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte + " --> " + codigoProcedimiento);
            codigoOperacion = "5";
            respuestaServicio = "Codigo procedimiento no  configurado para consultas en MELANBIDE_INTEROP.properties. " + ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte + " : " + codigoProcedimiento;
            //codigoProcedimiento = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + "CONCM", ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
        }
        String nombreProcedimiento = MeLanbideInteropManager.getInstance().getDescripcionProcedimiento(codOrganizacion, codProcExpte, adapt); // Solicitud y matriculaci�n del alumnado";
        String finalidad = nombreProcedimiento;  //"Solicitud y matriculaci�n del alumnado";
        String consentimiento = "Si";  // Valores Si o Ley. Al realizar la solicitus el interesdo debe firmar el consentimiento

        String nifTramitador = usuario_nif; //"12345678Z";
        String nombreTramitador = usuario.getApp() + " - " + usuario.getNombreUsu(); //"JUAN JOSE PEREZ ARTEAGA";

        String idExpediente = numExpediente;//

        // datos del interesado a verificar
        String tipoDocumento = "";// (/DNI/NIE/Pasaporte "DNI";
        tipoDocumento = MeLanbideInteropGeneralUtils.parsearTipoDocFlexiaToTipoDocEJIE_WS(_tercero.getTipoDoc());
        if (tipoDocumento == null || tipoDocumento.equalsIgnoreCase("")){
            log.error("Tipo Documento no Soportado : " + _tercero.getDoc() + " / " + _tercero.getTipoDoc());
            codigoOperacion = "6";
            respuestaServicio = "Tipo Documento no soportado para el WS de verificacion estar corriente pagos TGSS. Documentos admitidos : CIF/DNI/NIE/Pasaporte";
        }

        String numDocumento = _tercero.getDoc();
        String nombreTitular = _tercero.getNombre();
        String apellido1Titular = _tercero.getApellido1();
        String apellido2Titular = _tercero.getApellido2();

        if("".equals(respuestaServicio)){
            respuestaServicio = this.WebServiceGetCorrientePagoTgss(codOrganizacion, numExpediente, _tercero, adapt);
        }

        if(respuestaServicio==null || respuestaServicio.isEmpty())
        {
            log.error("Error - No se ha podido recibir respuesta del Servicio Consulta Corriente Pagos TGSS : "
                    + numExpediente
                    + " - " + numDocumento
                    + " - " + nombreTitular
                    + "  " + apellido1Titular
            );
        }else{
            log.error(" Servicio Consulta de Consulta Corriente Pagos TGSS : "
                    + numExpediente
                    + " - " + numDocumento
                    + " - " + nombreTitular
                    + "  " + apellido1Titular
                    + " - RespuestaWS  :  " + respuestaServicio
            );
        }

    }
    catch(Exception ex)
    {
        log.error("Error en la llamada al WS de Consulta Corriente Pagos TGSS : " ,  ex);
        codigoOperacion = "2";
    }

    StringBuilder xmlSalida = new StringBuilder();
    xmlSalida.append("<RESPUESTA>");
    xmlSalida.append("<CODIGO_OPERACION>");
    xmlSalida.append(codigoOperacion);
    xmlSalida.append("</CODIGO_OPERACION>");
    xmlSalida.append("<RESULTADO>");
    xmlSalida.append(respuestaServicio);
    xmlSalida.append("</RESULTADO>");
    xmlSalida.append("</RESPUESTA>");
    try{
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(xmlSalida.toString());
        out.flush();
        out.close();
    }catch(Exception ex){
        log.error("Error preparando el xml para retornar  en  el WS de Consulta Corriente Pagos TGSS" , ex);
    }//try-catch

}
    public void GetConsultaCorrientePagoHHFF(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response)
    {
        String codigoOperacion = "0";
        List<TerceroVO> lista = new ArrayList<TerceroVO>();
        TerceroVO _tercero = new TerceroVO();
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        String respuestaServicio = "";

        try
        {
            String[] datosExpediente = numExpediente.split(ConstantesMeLanbideInterop.BARRA_SEPARADORA);
            String codProcExpte = datosExpediente[1];
            //Recojo los parametros
            String codTer = (String)request.getParameter("codTer");
            String documento = (String)request.getParameter("documento");
            lista = MeLanbideInteropManager.getInstance().getDatosTercerosxExpediente(codOrganizacion, numExpediente, adapt);
            if(lista.size()>0)
            {
                if(codTer != null && documento != null)
                {
                    // Pantalla con tabla de terceros: usar el tercero seleccionado
                    for(TerceroVO ter : lista)
                    {
                        if(ter.getCodTer().equals(codTer) && ter.getDoc().equals(documento)){
                            _tercero = ter;
                            break;
                        }
                    }
                }
                else
                {
                    // Pantalla sin tabla de terceros (Gen): usar el titular (rol 1)
                    for(TerceroVO ter : lista)
                    {
                        if("1".equals(ter.getCodRol())){
                            _tercero = ter;
                            break;
                        }
                    }
                    // Si no hay titular con rol 1, usar el primero como fallback
                    if(_tercero.getCodTer() == null){
                        _tercero = lista.get(0);
                        log.info("GetConsultaCorrientePagoHHFF - No se encontro titular rol 1, usando primer tercero: " + _tercero.getCodTer() + " / " + _tercero.getDoc());
                    } else {
                        log.info("GetConsultaCorrientePagoHHFF - Usando titular rol 1: " + _tercero.getCodTer() + " / " + _tercero.getDoc());
                    }
                }
            }


            // Cogemos los datos del Usuario conectadp a la aplicación.
            UsuarioValueObject usuario = new UsuarioValueObject();
            String usuario_nif = "";
            HttpSession session = request.getSession();
            try {
                if (session != null) {
                    if (usuario != null) {
                        usuario = (UsuarioValueObject) session.getAttribute("usuario");
                    }
                }
            } catch (Exception ex) {
                log.error("Error al recuperar los datos del Usuario desde la session", ex);
            }
            usuario_nif = MeLanbideInteropManager.getInstance().getUsuarioNIF(usuario.getIdUsuario(), adapt);

            String unidadTramitadora = usuario.getDepCod() + " - " + usuario.getDep();//"Departamento de Educación";

            // Para primera pruebas sino existe en properties cogemo el de conciliacion
            String codigoProcedimiento = "";// 10133 Conciliacion CONCM     "SVDI_000233";
            codigoProcedimiento = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            if ("".equals(codigoProcedimiento)) {
                // Sino se recupera del properties deberá devolver error
                // Sin embargo en estas primeras pruebas recogemos el CONCM
                log.error("Codigo procedimiento no  configurado para consultas en MELANBIDE_INTEROP.properties :  " + ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte + " --> " + codigoProcedimiento);
                codigoOperacion = "5";
                respuestaServicio = "Codigo procedimiento no  configurado para consultas en MELANBIDE_INTEROP.properties. " + ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte + " : " + codigoProcedimiento;
                //codigoProcedimiento = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + "CONCM", ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            }
            String nombreProcedimiento = MeLanbideInteropManager.getInstance().getDescripcionProcedimiento(codOrganizacion, codProcExpte, adapt); // Solicitud y matriculación del alumnado";
            String finalidad = nombreProcedimiento;  //"Solicitud y matriculación del alumnado";
            String consentimiento = "Si";  // Valores Si o Ley. Al realizar la solicitus el interesdo debe firmar el consentimiento

            String nifTramitador = usuario_nif; //"12345678Z";
            String nombreTramitador = usuario.getApp() + " - " + usuario.getNombreUsu(); //"JUAN JOSE PEREZ ARTEAGA";

            String idExpediente = numExpediente;//


            // datos del interesado a verificar
            String tipoDocumento = "";// (/DNI/NIE/Pasaporte "DNI";
            tipoDocumento=MeLanbideInteropGeneralUtils.parsearTipoDocFlexiaToTipoDocEJIE_WS(_tercero.getTipoDoc());
            if (tipoDocumento == null || tipoDocumento.equalsIgnoreCase("")){
                log.error("Tipo Documento no Soportado : " + _tercero.getDoc() + " / " + _tercero.getTipoDoc());
                codigoOperacion = "6";
                respuestaServicio = "Tipo Documento no soportado para el WS de verificacion estar corriente pagos HHFF. Documentos admitidos : CIF/DNI/NIE/Pasaporte";
            }

            String numDocumento = _tercero.getDoc();
            String nombreTitular = _tercero.getNombre();
            String apellido1Titular = _tercero.getApellido1();
            String apellido2Titular = _tercero.getApellido2();

            if("".equals(respuestaServicio)){
                // Llamada al servicio HHFF mediante NISAE
                respuestaServicio = this.WebServiceGetCorrientePagoHHFF(codOrganizacion, numExpediente,
                        _tercero, codigoProcedimiento, nombreProcedimiento, adapt);
            }

            if(respuestaServicio==null || respuestaServicio.isEmpty())
            {
                log.error("Error - No se ha podido recibir respuesta del Servicio Consulta Corriente Pagos HHFF : "
                        + numExpediente
                        + " - " + numDocumento
                        + " - " + nombreTitular
                        + "  " + apellido1Titular
                );
            }else{
                log.error(" Servicio Consulta de Consulta Corriente Pagos TGSS : "
                        + numExpediente
                        + " - " + numDocumento
                        + " - " + nombreTitular
                        + "  " + apellido1Titular
                        + " - RespuestaWS  :  " + respuestaServicio
                );
            }

        }
        catch(Exception ex)
        {
            log.error("Error en la llamada al WS de Consulta Corriente Pagos TGSS : " ,  ex);
            codigoOperacion = "2";
        }

        StringBuilder xmlSalida = new StringBuilder();
        xmlSalida.append("<RESPUESTA>");
        xmlSalida.append("<CODIGO_OPERACION>");
        xmlSalida.append(codigoOperacion);
        xmlSalida.append("</CODIGO_OPERACION>");
        xmlSalida.append("<RESULTADO>");
        xmlSalida.append(respuestaServicio);
        xmlSalida.append("</RESULTADO>");
        xmlSalida.append("</RESPUESTA>");
        try{
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(xmlSalida.toString());
            out.flush();
            out.close();
        }catch(Exception ex){
            log.error("Error preparando el xml para retornar  en  el WS de Consulta Corriente Pagos TGSS" , ex);
        }//try-catch

    }

    public void GetConsultaObligacionesTribuDipu(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response)
    {
        String codigoOperacion = "0";
        List<TerceroVO> lista = new ArrayList<TerceroVO>();
        TerceroVO _tercero = new TerceroVO();
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        String respuestaServicio = "";
        try
        {
            String[] datosExpediente = numExpediente.split(ConstantesMeLanbideInterop.BARRA_SEPARADORA);
            String codProcExpte = datosExpediente[1];
            //Recojo los parametros
            String codTer = (String)request.getParameter("codTer");
            String documento = (String)request.getParameter("documento");
            lista = MeLanbideInteropManager.getInstance().getDatosTercerosxExpediente(codOrganizacion, numExpediente, adapt);
            if(lista.size()>0)
            {
                for(TerceroVO ter : lista)
                {
                    if(ter.getCodTer().equals(codTer) && ter.getDoc().equals(documento)){
                        _tercero = ter;
                        break;
                    }
                }
            }

            // Cogemos los datos del Usuario conectadp a la aplicación.
            UsuarioValueObject usuario = new UsuarioValueObject();
            String usuario_nif = "";
            HttpSession session = request.getSession();
            try {
                if (session != null) {
                    if (usuario != null) {
                        usuario = (UsuarioValueObject) session.getAttribute("usuario");
                    }
                }
            } catch (Exception ex) {
                log.error("Error al recuperar los datos del Usuario desde la session", ex);
            }
            usuario_nif = MeLanbideInteropManager.getInstance().getUsuarioNIF(usuario.getIdUsuario(), adapt);

            String pOrganoSolicitante = "Lanbide - Servicio Vasco Empleo"; //Eusko Jaurlaritza - Ejie Desarrollo";
            String pUnidadTramitadora = usuario.getDepCod() + " - " + usuario.getDep();//"Departamento de Educación";

            // Para primera pruebas sino existe en properties cogemo el de conciliacion
            String pCodigoProcedimiento = "";// 10133 Conciliacion CONCM     "SVDI_000233";
            pCodigoProcedimiento = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            if ("".equals(pCodigoProcedimiento)) {
                // Sino se recupera del properties deberá devolver error
                // Sin embargo en estas primeras pruebas recogemos el CONCM
                log.error("Codigo procedimiento no  configurado para consultas en MELANBIDE_INTEROP.properties :  " + ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte + " --> " + pCodigoProcedimiento);
                codigoOperacion = "5";
                respuestaServicio = "Codigo procedimiento no  configurado para consultas en MELANBIDE_INTEROP.properties. " + ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcExpte + " : " + pCodigoProcedimiento;
                //codigoProcedimiento = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + "CONCM", ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            }
            String pNombreProcedimiento = MeLanbideInteropManager.getInstance().getDescripcionProcedimiento(codOrganizacion, codProcExpte, adapt); // Solicitud y matriculación del alumnado";
            String pFinalidad = pNombreProcedimiento;  //"Solicitud y matriculación del alumnado";
            String pConsentimiento = "Si";  // Valores Si o Ley. Al realizar la solicitus el interesdo debe firmar el consentimiento

            String pNIFTramitador = usuario_nif; //"12345678Z";
            String pNombreTramitador = usuario.getApp() + " - " + usuario.getNombreUsu(); //"JUAN JOSE PEREZ ARTEAGA";

            String pIdExpediente = numExpediente;//"R02K0000000000000001";
            String pTipoTramite = ConstantesMeLanbideInterop.TIPO_SOLICI_OBLIGTRIBUT_SUBVENCIONES;// CONTRATACION, SUBVENCIONES

            // datos del interesado a verificar
            String pTipoDocumento = "";// (/DNI/NIE/Pasaporte "DNI";
            pTipoDocumento=MeLanbideInteropGeneralUtils.parsearTipoDocFlexiaToTipoDocEJIE_WS(_tercero.getTipoDoc());
            if (pTipoDocumento== null || pTipoDocumento.equalsIgnoreCase("")){
                log.error("Tipo Documento no Soportado : " + _tercero.getDoc() + " / " + _tercero.getTipoDoc());
                codigoOperacion = "6";
                respuestaServicio = "Tipo Documento no soportado para el WS de verificacion obligaciones tributarias. Documentos admitidos : CIF/DNI/NIE";
            }

            String pNumDocumento = _tercero.getDoc();
            String pNombreTitular = _tercero.getNombre(); //"MANUELA";
            String pApellido1Titular = _tercero.getApellido1();  // "BLANCO";
            String pApellido2Titular = _tercero.getApellido2();  //"VIDAL";


            String pTipoEmisor = "";    // No sabemos qe debe ir, en la documentacion no se especifica.
            //para pruebas Tomamos ALava -- Mirar como se manejara el tema de las Diputacion a consultar.
            String pTerritorio = "01";    //01, 20, 48

            if("".equals(respuestaServicio)){
                respuestaServicio = this.WebServiceGetObligacionesTributariasDipu(codOrganizacion, numExpediente,
                        pOrganoSolicitante, pUnidadTramitadora,
                        pCodigoProcedimiento, pNombreProcedimiento,
                        pFinalidad, pConsentimiento,
                        pNIFTramitador, pNombreTramitador,
                        pIdExpediente, pTipoTramite,
                        pTipoDocumento, pNumDocumento,
                        pNumDocumento, pNombreTitular, pApellido1Titular, pApellido2Titular,
                        pTipoEmisor,
                        pTerritorio);
            }

            if(respuestaServicio==null || respuestaServicio.isEmpty())
            {
                log.error("Error - No se ha podido recibir respuesta del Servicio Verificacion de Obligaciones tributarias : "
                        + numExpediente
                        + " - " + pNumDocumento
                        + " - " + pNombreTitular
                        + "  " + pApellido1Titular
                );
            }else{
                log.error(" Servicio Verificacion de Obligaciones tributarias : "
                        + numExpediente
                        + " - " + pNumDocumento
                        + " - " + pNombreTitular
                        + "  " + pApellido1Titular
                        + " - RespuestaWS  :  " + respuestaServicio
                );
            }

        }
        catch(Exception ex)
        {
            log.error("Error en la llamada al WS de Verificacion de Obligaciones tributarias : " ,  ex);
            codigoOperacion = "2";
        }

        StringBuilder xmlSalida = new StringBuilder();
        xmlSalida.append("<RESPUESTA>");
        xmlSalida.append("<CODIGO_OPERACION>");
        xmlSalida.append(codigoOperacion);
        xmlSalida.append("</CODIGO_OPERACION>");
        xmlSalida.append("<RESULTADO>");
        xmlSalida.append(tratarRespuestaXMLWServices(respuestaServicio));
        xmlSalida.append("</RESULTADO>");
        xmlSalida.append("</RESPUESTA>");
        try{
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(xmlSalida.toString());
            out.flush();
            out.close();
        }catch(Exception ex){
            log.error("Error preparando el XML de repsuesta para el WS de verificacion de obligaciones tributarias" , ex);
        }//try-catch

    }




    /********************************************************************************************************************************
     ******************************************************* WS**********************************************************************
     *******************************************************************************************************************************/

    public String WebServiceGetDatosIdentificacion(int codOrganizacion, String numExpediente,
                                                   String organoSolicitante, String unidadTramitadora,
                                                   String codigoProcedimiento, String nombreProcedimiento, String finalidad,
                                                   String consentimiento,
                                                   String nifTramitador, String nombreTramitador,
                                                   String idExpediente,
                                                   String tipoTramite,
                                                   String tipoDocumento, String numDocumento,
                                                   String nombreTitular, String apellido1Titular, String apellido2Titular,
                                                   String numSoporte,
                                                   String Sexo, String FechaNacimiento, String ProvinciaNacimiento, String PaisNacimiento,
                                                   String ProvinciaResidencia, String PaisResidencia
    ) //int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response
    {
        String codigoOperacion = "0";
        String resultadoDatIden = "";

        try {
            String nombreModulo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_MODULO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            String urlString = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.URL_SERVICIO_DATIDENTIDAD, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("URL: " + urlString);
            //log.error("URL-VO-: "+ datosWS.getUrlWebService());
            String nameSpaceUri = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.QNAME_NAMESPACEURI_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("NameSpaceUri: " + nameSpaceUri);
            String localPart = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.QNAME_LOCALPART_DATIDENTIDAD, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("LocalPart: " + localPart);
            //log.error("LocalPart(NombreWS)-VO-: "+ datosWS.getNombreWebService());

            /////  cargar datos de ejemplo  -- recogeremos de Tercero ////
            //GetDatosIdentidadWS _getDatosIdentidadWS = new GetDatosIdentidadWS();
            //_getDatosIdentidadWS.setPOrganoSolicitante("Eusko Jaurlaritza - Ejie Desarrollo");
            //_getDatosIdentidadWS.setPOrganoSolicitante("Eusko Jaurlaritza - Ejie Desarrollo");
            ///// datos prueba hay que cargarlos desde el tercero
            /////
            //urlString=datosWS.getUrlWebService();
            //localPart=datosWS.getNombreWebService();
            ////
            DatosIdentidadPortType service = getWebServiceDatosIdentidadClient(urlString, nameSpaceUri, localPart);

            try {
                //resultadoDatIdenResponse = service.getDatosIdentidadWS(_getDatosIdentidadWS);
                /*resultadoDatIden = service.getDatosIdentidadWS(organoSolicitante,
                        unidadTramitadora,
                        codigoProcedimiento,
                        nombreProcedimiento,
                        finalidad,
                        consentimiento,
                        nifTramitador,
                        nombreTramitador,
                        idExpediente,
                        tipoTramite,
                        tipoDocumento,
                        numDocumento,
                        nombreTitular,
                        apellido1Titular,
                        apellido2Titular,
                        numSoporte,
                        Sexo,
                        FechaNacimiento,
                        ProvinciaNacimiento,
                        PaisNacimiento,
                        ProvinciaResidencia,
                        PaisResidencia);
                */

                // Preparamos la peticion

                Peticion peticion = new Peticion();
                Respuesta respuesta = new Respuesta();
                es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Atributos atributos = new es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Atributos();

                es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Estado estado = new es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Estado();
                estado.setCodigoEstado(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.EJIE_CODIGO_ESTADO_PETICION, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                //estado.setCodigoEstadoSecundario("");
                estado.setLiteralError(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.EJIE_ESTADO_PETICION, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                //estado.setTiempoEstimadoRespuesta(1);

                atributos.setCodigoCertificado(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.EJIE_CODIGO_CERTIFICADO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES)); // Para consulta datos de indentidad SVDISFWS01
                atributos.setEstado(estado);
                atributos.setIdPeticion("PRUEBA-1");
                atributos.setNumElementos(1);
                atributos.setTimeStamp(new Date().toString());

                Solicitudes solicitudes = new Solicitudes();

                es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Solicitante solicitante = new es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Solicitante();
                solicitante.setIdentificadorSolicitante(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.ID_ORGANO_SOLICITANTE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                solicitante.setNombreSolicitante(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.ORGANO_SOLICITANTE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                solicitante.setUnidadTramitadora(unidadTramitadora);

                es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Procedimiento procedimiento = new es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Procedimiento();
                procedimiento.setCodProcedimiento(codigoProcedimiento);
                procedimiento.setNombreProcedimiento(nombreProcedimiento);

                solicitante.setProcedimiento(procedimiento);
                solicitante.setFinalidad(finalidad);
                solicitante.setConsentimiento(consentimiento);

                es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Funcionario funcionario = new es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Funcionario();
                funcionario.setNifFuncionario(nifTramitador);
                funcionario.setNombreCompletoFuncionario(nombreTramitador);

                solicitante.setFuncionario(funcionario);
                solicitante.setIdExpediente(idExpediente);

                TipoTramiteConsulta  tipoTramiteConsulta = new TipoTramiteConsulta();
                tipoTramiteConsulta.setTipoTramite(tipoTramite);

                solicitante.setIdentificadorSolicitante(numDocumento);
                solicitante.setNombreSolicitante(nombreTitular);
                es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Titular titular = new es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Titular();

                titular.setTipoDocumentacion(tipoDocumento);
                titular.setDocumentacion(numDocumento);
                titular.setNombre(nombreTitular);
                titular.setApellido1(apellido1Titular);
                titular.setApellido2(apellido2Titular);

                DatosAdicionalesTitular datosAdicionalesTitular = new DatosAdicionalesTitular();
                datosAdicionalesTitular.setNumSoporte(numSoporte);
                datosAdicionalesTitular.setSexo(Sexo);
                datosAdicionalesTitular.setFechaNacimiento(FechaNacimiento);
                datosAdicionalesTitular.setCodProvNacimiento(ProvinciaNacimiento);
                datosAdicionalesTitular.setCodPaisNacimiento(PaisNacimiento);
                datosAdicionalesTitular.setCodPaisNacimiento(PaisNacimiento);
                datosAdicionalesTitular.setCodProvNacimiento(ProvinciaNacimiento);


                SolicitudTransmision solicitudTransmision = new  SolicitudTransmision();

                es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.DatosGenericos datosGenericos = new es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.DatosGenericos();
                DatosEspecificos datosEspecificos = new DatosEspecificos();

                // Valores Segun documentación de Guía de Uso Consulta Datos Identidad SCSPv3
                es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Emisor emisor = new es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Emisor();
                emisor.setNifEmisor("S2816015H"); // S2811001C
                emisor.setNombreEmisor("Dirección General de la Policía.");

                es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Transmision transmision = new es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.peticion.Transmision();
                transmision.setCodigoCertificado("");
                transmision.setFechaGeneracion("");
                transmision.setIdSolicitud("");
                transmision.setIdTransmision("");

                datosGenericos.setEmisor(emisor);
                datosGenericos.setSolicitante(solicitante);
                datosGenericos.setTitular(titular);
                datosGenericos.setTransmision(transmision);

                Consulta consulta = new Consulta();
                consulta.setDatosAdicionalesTitular(datosAdicionalesTitular);
                consulta.setTipoTramiteConsulta(tipoTramiteConsulta);

                DatosConsultaRetorno datosConsultaRetorno = new DatosConsultaRetorno();
                datosConsultaRetorno.setFechaEmisionCertificado("");
                datosConsultaRetorno.setIdSolicitud("");
                datosConsultaRetorno.setIdTraza("");
                datosConsultaRetorno.setTipoTramite(tipoTramite);

                EstadoResultado estadoResultado = new EstadoResultado();
                estadoResultado.setCodigoEstado("");
                estadoResultado.setLiteralError("");

                DatosDireccionType datosDireccionType = new DatosDireccionType();
                datosDireccionType.setDatosViaResidencia("");
                datosDireccionType.setLocalidadResidencia("");
                datosDireccionType.setPaisResidencia("");
                datosDireccionType.setProvinciaResidencia("");

                DatosNacimientoType datosNacimientoType = new DatosNacimientoType();
                datosNacimientoType.setFechaNacimiento("");
                datosNacimientoType.setLocalidadNacimiento("");
                datosNacimientoType.setPaisNacimiento("");
                datosNacimientoType.setProvinciaNacimiento("");

                DatosTitularRetorno datosTitularRetorno = new DatosTitularRetorno();
                datosTitularRetorno.setApellido1("");
                datosTitularRetorno.setApellido2("");
                datosTitularRetorno.setDatosDireccion(datosDireccionType);
                datosTitularRetorno.setDatosNacimiento(datosNacimientoType);
                datosTitularRetorno.setFechaCaducidad("");
                datosTitularRetorno.setIdentificador("");
                datosTitularRetorno.setNacionalidad("");
                datosTitularRetorno.setNombre("");
                datosTitularRetorno.setNombreMadre("");
                datosTitularRetorno.setNombrePadre("");
                datosTitularRetorno.setNumSoporte("");
                datosTitularRetorno.setSexo("");


                Retorno retorno = new Retorno();
                retorno.setDatosConsultaRetorno(datosConsultaRetorno);
                retorno.setDatosTitularRetorno(datosTitularRetorno);
                retorno.setEstadoResultado(estadoResultado);

                datosEspecificos.setConsulta(consulta);
                datosEspecificos.setId("");
                datosEspecificos.setRetorno(retorno);


                solicitudTransmision.setDatosGenericos(datosGenericos);
                solicitudTransmision.setDatosEspecificos(datosEspecificos);

                solicitudes.setSolicitudTransmision(solicitudTransmision);

                peticion.setAtributos(atributos);
                peticion.setSolicitudes(solicitudes);



                log.error("Antes de hacer la llamada al WS - Peticion : " + peticion.toString());
                respuesta = service.getDatosIdentidadWS(peticion);

                if(respuesta!=null){
                    log.error("Respuesta del WS Diferente  a Null -- " +  respuesta.toString());
                    es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.respuesta.Atributos atributos1 = respuesta.getAtributos();
                    Transmisiones transmisiones = respuesta.getTransmisiones();
                    if(atributos1!=null){
                        es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.respuesta.Estado estadoRespuesta = atributos1.getEstado();
                        if(estadoRespuesta!=null){
                            log.error("-- Atributos.getEstado Diferente de null --- ASignamos respuesta del WService");
                            resultadoDatIden = estadoRespuesta.getCodigoEstado();
                            log.error("-- estadoRespuesta.getCodigoEstado() : " + estadoRespuesta.getCodigoEstado());
                            if(estadoRespuesta.getCodigoEstadoSecundario()!=null){
                                resultadoDatIden += "/" + estadoRespuesta.getCodigoEstadoSecundario();
                                log.error("-- estadoRespuesta.getCodigoEstadoSecundario() : " + estadoRespuesta.getCodigoEstadoSecundario());
                            }
                            log.error("-- estadoRespuesta.getLiteralError() : " + estadoRespuesta.getLiteralError());
                            resultadoDatIden += " - " +  estadoRespuesta.getLiteralError();
                            log.error("-- Texto Return Preparado - Salida WS  : " + resultadoDatIden);
                        }
                        log.error("Atributos.getNumElementos"+atributos1.getNumElementos());
                        log.error("Atributos.getIdPeticion"+atributos1.getIdPeticion());
                        log.error("Atributos.getTimeStamp"+atributos1.getTimeStamp());
                        log.error("Atributos.getCodigoCertificado"+atributos1.getCodigoCertificado());
                    }else{
                        log.error(" atributos de la respuesta viene a null");
                    }

                    if(transmisiones!=null){
                        TransmisionDatos transmisionDatos = transmisiones.getTransmisionDatos();
                        if(transmisionDatos!=null){
                            DatosEspecificos datosEspecificos1 = transmisionDatos.getDatosEspecificos();
                            es.altia.interoperabilidad.datamodel.getDatosIdentidadV3WS.respuesta.DatosGenericos  datosGenericos1 = transmisionDatos.getDatosGenericos();
                            if(datosGenericos1!=null){
                                log.error("transmisiones.getTransmisionDatos().getDatosGenericos() - Trae datos");
                            }else{
                                log.error("transmisiones.getTransmisionDatos().getDatosGenericos() de la respuesta viene a null");
                            }
                            if(datosEspecificos1!=null){
                                log.error("transmisiones.getDatosEspecificos().getId() : " + transmisionDatos.getDatosEspecificos().getId());
                                log.error("datosEspecificos1.getRetorno().getEstadoResultado().getCodigoEstado() : " + datosEspecificos1.getRetorno().getEstadoResultado().getCodigoEstado());
                                log.error("datosEspecificos1.getRetorno().getEstadoResultado().getLiteralError() : " + datosEspecificos1.getRetorno().getEstadoResultado().getLiteralError());
                            }else{
                                log.error("transmisiones.getTransmisionDatos().getDatosEspecificos() de la respuesta viene a null");
                            }
                        }else{
                            log.error("transmisiones.getTransmisionDatos() de la respuesta viene a null");
                        }
                    }else{
                        log.error("trasmisiones de la respuesta viene a null");
                    }
                }else{
                    log.error(" Respuesta WS es NULL : " );    //resultadoDatIden
                }


            } catch (Exception ex) {
                log.error(ex.getMessage() + "  - Error al llamar al Servicio - Causa: " + ex.getCause(), ex);
                codigoOperacion="1";
                resultadoDatIden="Error al intentar procesar la llamada al servicio de verificacion de identidad.";
            }

        } catch (Exception ex) {
            log.error("Error Generico al realizar la llamada WebServiceGetDatosIdentificacion : ", ex);
            codigoOperacion = "2";
            resultadoDatIden="Error preparando los parametros para la llamada al servicio de verificacion de identidad.";
        }

        //    StringBuffer xmlSalida = new StringBuffer();
        //    xmlSalida.append("<RESPUESTA>");
        //    xmlSalida.append("<CODIGO_OPERACION>");
        //    xmlSalida.append(codigoOperacion);
        //    xmlSalida.append("</CODIGO_OPERACION>");
        //    xmlSalida.append("<RESULTADO>");
        //    xmlSalida.append(resultadoDatIden);
        //    xmlSalida.append("</RESULTADO>");
        /*for(EspacioFormVO fila : lista)
         {
         xmlSalida.append("<FILA>");
         xmlSalida.append("<ID>");
         xmlSalida.append(fila.getId()!= null ? fila.getId().toString() : "");
         xmlSalida.append("</ID>");
         xmlSalida.append("<EPF_NUM>");
         xmlSalida.append(fila.getNumExp());
         xmlSalida.append("</EPF_NUM>");
         xmlSalida.append("<EPF_DES>");
         xmlSalida.append(fila.getDescripcion());
         xmlSalida.append("</EPF_DES>");
         xmlSalida.append("<EPF_SUP>");
         xmlSalida.append(fila.getSuperficie()!= null ? fila.getSuperficie().toString() : "");
         xmlSalida.append("</EPF_SUP>");
         xmlSalida.append("</FILA>");
         }*/
        //    xmlSalida.append("</RESPUESTA>");
        /*try{
         response.setContentType("text/xml");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out = response.getWriter();
         out.print(xmlSalida.toString());
         out.flush();
         out.close();
         }catch(Exception e){
         e.printStackTrace();
         }//try-catch
         */

        //return resultadoDatIdenResponse.getReturn0();
        log.error(" Salida Retornada  : " + resultadoDatIden);
        //return xmlSalida.toString();
        return resultadoDatIden;
    }

    private DatosIdentidadPortType getWebServiceDatosIdentidadClient(String urlString, String nameSpaceUri, String localPart) throws Exception, IOException {
        if (log.isDebugEnabled()) {
            log.error("getWebServiceDatosIdentidadClient() : BEGIN");
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceDatosIdentidadClient() : urlString: " + urlString);
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceDatosIdentidadClient() : nameSpaceUri: " + nameSpaceUri);
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceDatosIdentidadClient() : localPart: " + localPart);
        }

        if (instanceDatIdent == null) {
            if (log.isDebugEnabled()) {
                log.error("No existe instancia, creamos una nueva");
            }
            URL url = null;
            QName qName = null;

            if (log.isDebugEnabled()) {
                log.error("Recuperamos la URL del servicio web");
            }
            try {
                url = new URL(urlString);
            } catch (Exception ex) {
                log.error("Se ha producido un error parseando la URL del servicio web datos identidad " + ex.getMessage());
                throw new Exception("Se ha producido un error parseando la URL del servicio web datos identidad", ex);
            }

            if (log.isDebugEnabled()) {
                log.error("Recuperamos el service name del servicio web");
            }
            try {
                qName = new QName(nameSpaceUri, localPart);
            } catch (Exception ex) {
                log.error("Se ha producido un error recuperando el qName del servicio web daos identidad " + ex.getMessage());
                throw new Exception("Se ha producido un error recuperando el qName del servicio web datos indentidad ", ex);
            }//try-catch


            try {





                String _url = urlString;
                //Cargamos el fichero xml con las políticas de seguridad
                log.error("Realizamos el URL pathXml" );
                URL pathXml = Thread.currentThread().getContextClassLoader().getResource("META-INF/x53jsGetDatosIdentidadV3WS.xml");
                QName _qName = new QName("http://www.map.es/xml-schemas", "x53jsGetDatosIdentidadV3");

                //Instanciamos la clase del cliente del WS
                //X53JsGetDatosPadronV3 prestacionesWS = new X53JsGetDatosPadronV3(pathXml, qName);
                log.error(" - X53JsGetDatosIdentidadV3(pathXml, _qName) - " + pathXml.toString() + "  -**-  "  + _qName.toString() + " Qname PERPARADO CON DATOS DEL PROPERTIES : " + qName.toString());
                X53JsGetDatosIdentidadV3 datosIdentidadWS = new X53JsGetDatosIdentidadV3(pathXml, _qName);

                //Obtenemos el port del WS
                //PadronIndividualPortType port = prestacionesWS.getGetPadronIndividualPort();
                DatosIdentidadPortType port = datosIdentidadWS.getGetDatosIdentidadPort();
                log.error("Obtenemos el port");

                //Asignamos la URL del WS
                ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, _url);
                log.error("Actualizamos la URL del port");

                log.error("Vamos a poner la seguridad de weblogic");
                // Prueba User / Contraseña en la request
                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                ((BindingProvider) port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, _USER);
                ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, _PASS);
                log.error("Hemos Asignado User/pass en ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, _PASS);");

                // Inicio Invocacion WSS + User Token
                //Asignamos un Credential Provider con el Usuario y Password para la autenticación
                /*
                List<CredentialProvider> credProviders = new ArrayList<CredentialProvider>();
                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                CredentialProvider cp = new ClientUNTCredentialProvider(_USER.getBytes(), _PASS.getBytes());  //"USER".getBytes(), "PASSWORD".getBytes()
                credProviders.add(cp);
                Map<String, Object> rc = ((BindingProvider) port).getRequestContext();
                rc.put(WSSecurityContext.CREDENTIAL_PROVIDER_LIST, credProviders);
                */
                // Fin Invocacion WSS + User Token

                // Inicio Seguridad de WebLogic WSS + Certificado
                //((BindingProvider) port).getRequestContext().put("weblogic.xml.crypto.wss.PKI_Initiator", "lanbide"); // Lanbide esta configurado en el weblogic como PKI
                // Fin Seguridad de WebLogic WSS + Certificado

                log.error("Seguridad de weblogic asignada correctamente");

                instanceDatIdent = port;
                ///
                if (log.isDebugEnabled()) {
                    log.error("Retornamos la instancia del cliente");
                }

            }/*catch(ServiceException e) {
             e.printStackTrace();
             log.error("Error al crear la instacia del cleinte webservce " + e.getMessage() + " --  Causa _: " + e.getCause() , e);
             } catch(RemoteException e) {
             e.printStackTrace();
             log.error("Error al crear la instacia del cleinte webservce " + e.getMessage() + " --  Causa _: " + e.getCause() , e);
             }*/ catch (Exception ex) {
                log.error("Error al crear la instacia del cleinte webservce " + ex.getMessage() + " --  Causa _: " + ex.getCause(), ex);
            }
        } else {
            if (log.isDebugEnabled()) {
                log.error("Existe una instancia del cliente del servicio");
            }
        }//if(instanceDatIdent == null)
        if (log.isDebugEnabled()) {
            log.error("getWebServiceClient() : END");
        }
        return instanceDatIdent;
    }//getWebServiceDatosIdentidadClient

    public String WebServiceGetEpigrafesIae(int codOrganizacion, String numExpediente,
                                            String organoSolicitante, String unidadTramitadora,
                                            String codigoProcedimiento, String nombreProcedimiento, String finalidad,
                                            String consentimiento,
                                            String nifTramitador, String nombreTramitador,
                                            String idExpediente,
                                            String tipoTramite,
                                            String tipoDocumento, String numDocumento,
                                            String nombreTitular, String apellido1Titular, String apellido2Titular,
                                            String territorio,
                                            String paraNuevo1,
                                            String paraNuevo2) {
        String codigoOperacion = "0";
        String resultadoEpigraf = "";

        try {
            //Recuperamos Datos: Nombre Modulo, el valor de la URL y QName  del Sercvicio
            String nombreModulo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_MODULO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            String urlString = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.URL_SERVICIO_EPIGRAFESIAE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("URL: " + urlString);
            String nameSpaceUri = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.QNAME_NAMESPACEURI_EPIGRAFESIAE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("NameSpaceUri: " + nameSpaceUri);
            String localPart = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.QNAME_LOCALPART_EPIGRAFESIAE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("LocalPart: " + localPart);

            es.altia.interoperabilidad.jaxws.getEpigrafesIaeUser.PeticionPortType service = getWebServiceEpigrafesIaeClient(urlString, nameSpaceUri, localPart);

            try {
                log.error("organoSolicitante,\n" +
                        "                        unidadTramitadora,\n" +
                        "                        codigoProcedimiento,\n" +
                        "                        nombreProcedimiento,\n" +
                        "                        finalidad,\n" +
                        "                        consentimiento,\n" +
                        "                        nifTramitador,\n" +
                        "                        nombreTramitador,\n" +
                        "                        idExpediente,\n" +
                        "                        tipoTramite,\n" +
                        "                        tipoDocumento,\n" +
                        "                        numDocumento,\n" +
                        "                        nombreTitular,\n" +
                        "                        apellido1Titular,\n" +
                        "                        apellido2Titular,\n" +
                        "                        territorio,\n" +
                        "                        paraNuevo1,\n" +
                        "                        paraNuevo2");
                log.error(organoSolicitante+","+
                        unidadTramitadora+","+
                        codigoProcedimiento+","+
                        nombreProcedimiento+","+
                        finalidad+","+
                        consentimiento+","+
                        nifTramitador+","+
                        nombreTramitador+","+
                        idExpediente+","+
                        tipoTramite+","+
                        tipoDocumento+","+
                        numDocumento+","+
                        nombreTitular+","+
                        apellido1Titular+","+
                        apellido2Titular+","+
                        territorio+","+
                        paraNuevo1+","+
                        paraNuevo2);
                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Peticion peticion = new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Peticion();
                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Atributos atributos = new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Atributos();
                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Estado estado = new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Estado();

                estado.setCodigoEstado(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.EJIE_CODIGO_ESTADO_PETICION, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                estado.setLiteralError(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.EJIE_ESTADO_PETICION, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));

                atributos.setCodigoCertificado(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.EJIE_CODIGO_CERTIFICADO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                atributos.setEstado(estado);
                atributos.setIdPeticion(codigoProcedimiento + (new Date().toString()));
                atributos.setNumElementos(1);
                atributos.setTimeStamp(new Date().toString());

                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Solicitante solicitante = new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Solicitante();
                solicitante.setIdentificadorSolicitante(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.ID_ORGANO_SOLICITANTE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                solicitante.setNombreSolicitante(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.ORGANO_SOLICITANTE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                solicitante.setUnidadTramitadora(unidadTramitadora);

                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Procedimiento procedimiento = new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Procedimiento();
                procedimiento.setCodProcedimiento(codigoProcedimiento);
                procedimiento.setNombreProcedimiento(nombreProcedimiento);

                solicitante.setProcedimiento(procedimiento);
                solicitante.setFinalidad(finalidad);
                solicitante.setConsentimiento(consentimiento);

                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Funcionario funcionario = new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Funcionario();
                funcionario.setNifFuncionario(nifTramitador);
                funcionario.setNombreCompletoFuncionario(nombreTramitador);

                solicitante.setFuncionario(funcionario);
                solicitante.setIdExpediente(idExpediente);
                solicitante.setIdentificadorSolicitante(numDocumento);
                solicitante.setNombreSolicitante(nombreTitular);
                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Titular titular=new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Titular();

                titular.setTipoDocumentacion(tipoDocumento);
                titular.setDocumentacion(numDocumento);
                titular.setNombre(nombreTitular);
                titular.setApellido1(apellido1Titular);
                titular.setApellido2(apellido2Titular);
                titular.setNombreCompleto((nombreTitular + (apellido1Titular!=null? " " + apellido1Titular :"") + (apellido2Titular!=null? " " + apellido2Titular:"")) );

                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.DatosGenericos datosGenericos = new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.DatosGenericos();

                // Valores Segun documentación de Guía de Uso Consulta Datos Identidad SCSPv3
                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Emisor emisor = new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Emisor();
                emisor.setNifEmisor("S2816015H"); // S2811001C
                emisor.setNombreEmisor("Dirección General de la Policía.");

                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Transmision transmision = new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Transmision();
                transmision.setCodigoCertificado(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.EJIE_CODIGO_CERTIFICADO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                transmision.setFechaGeneracion("");
                transmision.setIdSolicitud("");
                transmision.setIdTransmision("");


                datosGenericos.setEmisor(emisor);
                datosGenericos.setSolicitante(solicitante);
                datosGenericos.setTitular(titular);
                datosGenericos.setTransmision(transmision);

                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.datosespecificos.DatosEspecificos datosEspecificos = new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.datosespecificos.DatosEspecificos();
                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.datosespecificos.GetEpigrafesWS epigrafesWS = new GetEpigrafesWS();
                epigrafesWS.setPTerritorioCAV(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.EJIE_CODIGO_TERRITORIOHIST_ALAVA, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                datosEspecificos.setGetEpigrafesWS(epigrafesWS);

                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.SolicitudTransmision solicitudTransmision = new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.SolicitudTransmision();
                solicitudTransmision.setDatosEspecificos(datosEspecificos);
                solicitudTransmision.setDatosGenericos(datosGenericos);

                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Solicitudes solicitudes = new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.peticion.Solicitudes();
                solicitudes.setId("");
                log.error("Vamos a asignar la solicitud de Transmision ->  Parametros Peticion->Solicitudes : ");
                if(solicitudes.getSolicitudTransmision()!=null)
                    log.error("solicitudes1.getSolicitudTransmision() es diferente de null, deberia funcionar la asignacion");
                else
                    log.error("solicitudes1.getSolicitudTransmision() es null asi que seguro petara......!!!!");
                solicitudes.getSolicitudTransmision().add(solicitudTransmision);
                log.error("Asiganada Correctamente la solicitud de Transmision ->  Parametros Peticion->Solicitudes : ");

                log.error("Asignamos  Parametros a la Peticion : "
                        + "_peticion.setAtributos(atributos);\n"
                        + "_peticion.setSolicitudes(solicitudes);");

                peticion.setAtributos(atributos);
                peticion.setSolicitudes(solicitudes);

                log.error("Epigrafes - Asignados Parametros, vamos a llamar al WS de Epigrafes -- ");
                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.respuesta.Respuesta respuesta = service.getEpigrafesWS(peticion);
                log.error("Epigrafes - LLamada ejecutada vamos a comprobar la repsuesta del WS.");
                if(respuesta!=null){
                    log.error("Epigrafes - Respuesta del WS Diferente  a Null -- ");
                    es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.respuesta.Atributos atributosResp = new es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.respuesta.Atributos();
                    atributosResp=respuesta.getAtributos();
                    if(atributosResp!=null){
                        es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.respuesta.Estado estadoRespuesta = atributosResp.getEstado();
                        if(estadoRespuesta!=null){
                            log.error("-- Epigrafes - Atributos.getEstado Diferente de null --- Asignamos respuesta del WService");
                            resultadoEpigraf = estadoRespuesta.getCodigoEstado();
                            log.error("-- Epigrafes - estadoRespuesta.getCodigoEstado() : " + estadoRespuesta.getCodigoEstado());
                            if(estadoRespuesta.getLiteralError()!=null){
                                resultadoEpigraf += "/" + estadoRespuesta.getCodigoEstadoSecundario();
                                log.error("-- Epigrafes - estadoRespuesta.getCodigoEstadoSecundario() : " + estadoRespuesta.getCodigoEstadoSecundario());
                            }
                            log.error("-- Epigrafes - estadoRespuesta.getLiteralError() : " + estadoRespuesta.getLiteralError());
                            resultadoEpigraf += " - " +  estadoRespuesta.getLiteralError();
                            log.error("-- Epigrafes - Texto Return Preparado - Salida WS  : " + resultadoEpigraf);
                        }
                        log.error("Epigrafes - Atributos.getNumElementos"+atributosResp.getNumElementos());
                        log.error("Epigrafes - Atributos.getIdPeticion"+atributosResp.getIdPeticion());
                        log.error("Epigrafes - Atributos.getTimeStamp"+atributosResp.getTimeStamp());
                        log.error("Epigrafes - Atributos.getCodigoCertificado"+atributosResp.getCodigoCertificado());
                    }else{
                        log.error(" Epigrafes - Atributos de la respuesta viene a null");
                    }
                    es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.respuesta.Transmisiones transmisiones = respuesta.getTransmisiones();
                    if(transmisiones!=null){
                        List<es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.respuesta.TransmisionDatos> listaTransmisionDatos=transmisiones.getTransmisionDatos();
                        if(listaTransmisionDatos!=null){
                            for(es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.respuesta.TransmisionDatos transmisionDatos : listaTransmisionDatos){
                                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.datosespecificos.DatosEspecificos datosEspecificos1 = transmisionDatos.getDatosEspecificos();
                                es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.respuesta.DatosGenericos datosGenericos1 = transmisionDatos.getDatosGenericos();
                                if (datosGenericos1 != null) {
                                    log.error("-Epigrafes - transmisiones.getTransmisionDatos().getDatosGenericos() - Trae datos");
                                } else {
                                    log.error("-Epigrafes - transmisiones.getTransmisionDatos().getDatosGenericos() de la respuesta viene a null");
                                }
                                if (datosEspecificos1 != null) {
                                    es.altia.interoperabilidad.datamodel.getEpigrafesIaeUser.datosespecificos.GetEpigrafesWSResponse epigrafesWSResponse = datosEspecificos.getGetEpigrafesWSResponse();
                                    if(epigrafesWSResponse!=null){
                                        log.error("-Epigrafes - transmisiones.getDatosEspecificos().getGetEpigrafesWSResponse().getResultado() : " + epigrafesWSResponse.getResultado());
                                        log.error("-Epigrafes - transmisiones.getDatosEspecificos().getGetEpigrafesWSResponse().getDescripcion() : " + epigrafesWSResponse.getDescripcion());
                                        log.error("-Epigrafes - transmisiones.getDatosEspecificos().getGetEpigrafesWSResponse().getTerritorioCAV() : " + epigrafesWSResponse.getTerritorioCAV());
                                        resultadoEpigraf += "\n " + epigrafesWSResponse.getResultado()
                                                + " - " + epigrafesWSResponse.getDescripcion() + ""
                                                + "\n " + epigrafesWSResponse.getTerritorioCAV();
                                    }else{
                                        log.error("-Epigrafes - transmisiones.getDatosEspecificos().getGetEpigrafesWSResponse() : Viene a null. No se ha obtenido respuesta del WS");
                                    }
                                } else {
                                    log.error("-Epigrafes - transmisiones.getTransmisionDatos().getDatosEspecificos() de la respuesta viene a null");
                                }
                            }
                        }else{
                            log.error("-- Epigrafes Lista transmisiones.getTransmisionDatos() de la respuesta viene a null");
                        }
                    }else{
                        log.error("-- Epigrafes - trasmisiones de la respuesta viene a null");
                    }
                }else{
                    log.error(" Epigrafes - Respuesta WS es NULL " );
                }

            } catch (Exception ex) {
                log.error("Error al llamar al Servicio Epigrafes - Causa: " + ex.getCause() + "\n" + ex.getMessage(), ex);
                resultadoEpigraf="Error al procesar la llamada al Servicio de consulta de Epigrafes IAE";
            }
        } catch (Exception ex) {
            log.error("Error al preparar los parametros para la llamada al servicio de consulta de Epigrafes IAE", ex);
            resultadoEpigraf="Error al preparar los parametros para la llamada al servicio de consulta de Epigrafes IAE";
        }
        log.error("Salida del WS EpigrafesIAE  :" + resultadoEpigraf);
        return resultadoEpigraf;
    }

    private es.altia.interoperabilidad.jaxws.getEpigrafesIaeUser.PeticionPortType getWebServiceEpigrafesIaeClient(String urlString, String nameSpaceUri, String localPart) throws Exception, IOException {
        if (log.isDebugEnabled()) {
            log.error("getWebServiceEpigrafesIaeClient() : BEGIN");
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceEpigrafesIaeClient() : urlString: " + urlString);
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceEpigrafesIaeClient() : nameSpaceUri: " + nameSpaceUri);
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceEpigrafesIaeClient() : localPart: " + localPart);
        }

        if (instanceEpigraf == null) {
            if (log.isDebugEnabled()) {
                log.error("No existe instancia, creamos una nueva");
            }
            URL url = null;
            QName qName = null;

            if (log.isDebugEnabled()) {
                log.error("Recuperamos la URL del servicio web");
            }
            try {
                url = new URL(urlString);
            } catch (Exception ex) {
                log.error("Se ha producido un error parseando la URL del servicio web Epigrafes IAE " + ex.getMessage());
                throw new Exception("Se ha producido un error parseando la URL del servicio web Epigrafes IAE", ex);
            }

            if (log.isDebugEnabled()) {
                log.error("Recuperamos el service name del servicio web Epigrafes IAE");
            }
            try {
                qName = new QName(nameSpaceUri, localPart);
            } catch (Exception ex) {
                log.error("Se ha producido un error recuperando el qName del servicio web Epigrafes IAE " + ex.getMessage());
                throw new Exception("Se ha producido un error recuperando el qName del servicio web Epigrafes IAE ", ex);
            }//try-catch
            if (log.isDebugEnabled()) {
                log.error("Creamos la instancia del cliente");
            }
            try {
                String _url = urlString;

                //Cargamos el fichero xml con las políticas de seguridad
                log.error("Realizamos el URL pathXml");
                URL pathXml = Thread.currentThread().getContextClassLoader().getResource("META-INF/x53jsEpigrafesIaeEJGVUser.xml");
                QName _qName = new QName(nameSpaceUri,localPart );

                //Instanciamos la clase del cliente del WS
                //X53JsGetDatosPadronV3 prestacionesWS = new X53JsGetDatosPadronV3(pathXml, qName);
                log.error(" - X53JsGetEpigrafesWSService(pathXml, _qName) - " + pathXml.toString() + "  -**-  " + _qName.toString() + " Qname PERPARADO CON DATOS DEL PROPERTIES : " + qName.toString());
                log.error(" - Instanciamos el WS con la URL del properties - X53JsGetEpigrafesIaeCAV datosEpigrafesIaeWS = new es.altia.interoperabilidad.jaxws.getEpigrafesIaeUser.X53JsGetEpigrafesIaeCAVStub("+url.toString()+");");
                es.altia.interoperabilidad.jaxws.getEpigrafesIaeUser.X53JsGetEpigrafesIaeCAV  datosEpigrafesIaeWS = new es.altia.interoperabilidad.jaxws.getEpigrafesIaeUser.X53JsGetEpigrafesIaeCAV(pathXml, _qName);

                //Obtenemos el port del WS
                es.altia.interoperabilidad.jaxws.getEpigrafesIaeUser.PeticionPortType port = datosEpigrafesIaeWS.getGetEpigrafesWSPort();
                log.error("Obtenemos el port");
                //ServiceClient cliente = datosEpigrafesIaeWS._getServiceClient();
                /*OMFactory omFactory = OMAbstractFactory.getOMFactory();
                OMElement omSecurityElement = omFactory.createOMElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security", "wsse"), null);

                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);

                OMElement omusertoken = omFactory.createOMElement(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "UsernameToken", "wsu"), null);

                OMElement omuserName = omFactory.createOMElement(new QName("", "Username", "wsse"), null);
                omuserName.setText(_USER);

                OMElement omPassword = omFactory.createOMElement(new QName("", "Password", "wsse"), null);
                omPassword.addAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText", null);
                omPassword.setText(_PASS);

                omusertoken.addChild(omuserName);
                omusertoken.addChild(omPassword);
                omSecurityElement.addChild(omusertoken);
                datosEpigrafesIaeWS._getServiceClient().addHeader(omSecurityElement);
                */

                //Asignamos la URL del WS
                ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, _url);
                log.error("Actualizamos la URL del port");

                log.error("Vamos a poner la seguridad de weblogic");
                // Llamdas con Usuario Basico. Pasando User/pass
                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                ((BindingProvider) port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, _USER);
                ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, _PASS);
                log.error("Asignadas las claves de Seguridad. ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY");

                // Inicio LLamada con WSS + User Token
                //Asignamos un Credential Provider con el Usuario y Password para la autenticación
                /*
                List<CredentialProvider> credProviders = new ArrayList<CredentialProvider>();
                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                CredentialProvider cp = new ClientUNTCredentialProvider(_USER.getBytes(), _PASS.getBytes());  //"USER".getBytes(), "PASSWORD".getBytes()
                credProviders.add(cp);
                Map<String, Object> rc = ((BindingProvider) port).getRequestContext();
                rc.put(WSSecurityContext.CREDENTIAL_PROVIDER_LIST, credProviders);
                */
                // Fin Llamada WSS + User Token

                // Llamada con WSS + Certificado
                //Aplicamos la seguridad de WebLogic
//                ((BindingProvider) port).getRequestContext().put("weblogic.xml.crypto.wss.PKI_Initiator", "lanbide"); // Lanbide esta configurado en el weblogic como PKI
                // Fin LLamada con WSS + Certificado

                log.error("Seguridad de weblogic asignada correctamente");

                instanceEpigraf = port;
                ///
                if (log.isDebugEnabled()) {
                    log.error("Asignamos y Retornamos la instancia del cliente - Epigrafes IAE");
                }
            }catch (Exception ex) {
                log.error("Error al crear la instacia del cliente webservce " + ex.getMessage() + " --  Causa _: " + ex.getCause(), ex);
                throw ex;
            }
        } else {
            if (log.isDebugEnabled()) {
                log.error("Existe una instancia del cliente del servicio");
            }
        }//if(instanceEpigraf == null)
        if (log.isDebugEnabled()) {
            log.error("getWebServiceClient() : END");
        }
        return instanceEpigraf;
    }

    public String WebServiceGetDomicilioFiscal(int codOrganizacion, String numExpediente,
                                               String organoSolicitante, String unidadTramitadora,
                                               String codigoProcedimiento, String nombreProcedimiento, String finalidad,
                                               String consentimiento,
                                               String nifTramitador, String nombreTramitador,
                                               String idExpediente,
                                               String tipoTramite,
                                               String tipoDocumento, String numDocumento,
                                               String titularDocumento, String nombreTitular, String apellido1Titular, String apellido2Titular,
                                               String territorio,
                                               String paraNuevo1) {
        String codigoOperacion = "0";
        String resultadoDomFiscal = "";
        try {
            String nombreModulo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_MODULO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            String urlString = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.URL_SERVICIO_DOMFISCAL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("URL: " + urlString);
            String nameSpaceUri = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.QNAME_NAMESPACEURI_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("NameSpaceUri: " + nameSpaceUri);
            String localPart = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.QNAME_LOCALPART_DOMFISCAL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("LocalPart: " + localPart);

            X53JsGetDomicilioFiscalWSDelegate service = getWebServiceDomicilioFiscalClient(urlString, nameSpaceUri, localPart);

            try {
                resultadoDomFiscal = service.getDomicilioFiscalWS(organoSolicitante,
                        unidadTramitadora,
                        codigoProcedimiento,
                        nombreProcedimiento,
                        finalidad,
                        consentimiento,
                        nifTramitador,
                        nombreTramitador,
                        idExpediente,
                        tipoTramite,
                        tipoDocumento,
                        numDocumento,
                        titularDocumento,
                        nombreTitular,
                        apellido1Titular,
                        apellido2Titular,
                        territorio,
                        "");
            } catch (Exception ex) {
                log.error(ex.getMessage() + "  - Error al llamar al Servicio Domicilio Fiscal  - Causa: " + ex.getCause(), ex);
                resultadoDomFiscal="Error al procesar la petición en el WS de consulta domicilio fiscal";
            }

        } catch (Exception ex) {
            log.error("Error llmada al WS de Datos de Domicilio Fiscal -preparando parametros- " + ex);
            resultadoDomFiscal="Error al preparar los parametros para la llamada  al WS de consulta domicilio fiscal";
        }

        log.error("Respuesta de WS_DomicilioFiscal : " + resultadoDomFiscal);
        return resultadoDomFiscal;
    }

    private X53JsGetDomicilioFiscalWSDelegate getWebServiceDomicilioFiscalClient(String urlString, String nameSpaceUri, String localPart) throws Exception, IOException {
        if (log.isDebugEnabled()) {
            log.error("getWebServiceDomicilioFiscalClient() : BEGIN");
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceDomicilioFiscalClient() : urlString: " + urlString);
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceDomicilioFiscalClient() : nameSpaceUri: " + nameSpaceUri);
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceDomicilioFiscalClient() : localPart: " + localPart);
        }

        if (instanceDomFisc == null) {
            if (log.isDebugEnabled()) {
                log.error("No existe instancia, creamos una nueva");
            }
            URL url = null;
            QName qName = null;

            if (log.isDebugEnabled()) {
                log.error("Recuperamos la URL del servicio web");
            }
            try {
                url = new URL(urlString);
            } catch (Exception ex) {
                log.error("Se ha producido un error parseando la URL del servicio web Domicilio Fiscal " + ex.getMessage());
                throw new Exception("Se ha producido un error parseando la URL del servicio web Domicilio Fiscal", ex);
            }

            if (log.isDebugEnabled()) {
                log.error("Recuperamos el service name del servicio web Epigrafes IAE");
            }
            try {
                qName = new QName(nameSpaceUri, localPart);
            } catch (Exception ex) {
                log.error("Se ha producido un error recuperando el qName del servicio web Domicilio Fiscal " + ex.getMessage());
                throw new Exception("Se ha producido un error recuperando el qName del servicio web Domicilio Fiscal ", ex);
            }//try-catch
            if (log.isDebugEnabled()) {
                log.error("Creamos la instancia del cliente");
            }
            try {
                String _url = urlString;

                //Cargamos el fichero xml con las políticas de seguridad
                log.error("Realizamos el URL pathXml");
                URL pathXml = Thread.currentThread().getContextClassLoader().getResource("META-INF/x53jsGetDomicilioFiscalWS.xml");
                QName _qName = new QName(nameSpaceUri,localPart );

                //Instanciamos la clase del cliente del WS
                log.error(" - X53JsGetDomicilioFiscalWSService(pathXml, _qName) - " + pathXml.toString() + "  -**-  " + _qName.toString() + " Qname PERPARADO CON DATOS DEL PROPERTIES : " + qName.toString());
                X53JsGetDomicilioFiscalWSService datosDomicilioFiscalWS = new X53JsGetDomicilioFiscalWSService(pathXml, _qName);

                //Obtenemos el port del WS
                X53JsGetDomicilioFiscalWSDelegate port = datosDomicilioFiscalWS.getX53JsGetDomicilioFiscalWSPort();
                log.error("Obtenemos el port");

                //Asignamos la URL del WS
                ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, _url);
                log.error("Actualizamos la URL del port");

                log.error("Vamos a poner la seguridad de weblogic");
                // Llamdas con Usuario Basico. Pasando User/pass
                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                ((BindingProvider) port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, _USER);
                ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, _PASS);
                log.error("Asignadas las claves de Seguridad. ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY");

                // Inicio LLamada con WSS + User Token
                //Asignamos un Credential Provider con el Usuario y Password para la autenticación
                /*log.error(" - DomiclioFiscal WSS + User Token");
                List<CredentialProvider> credProviders = new ArrayList<CredentialProvider>();
                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                CredentialProvider cp = new ClientUNTCredentialProvider(_USER.getBytes(), _PASS.getBytes());  //"USER".getBytes(), "PASSWORD".getBytes()
                credProviders.add(cp);
                Map<String, Object> rc = ((BindingProvider) port).getRequestContext();
                rc.put(WSSecurityContext.CREDENTIAL_PROVIDER_LIST, credProviders);
                // Fin Llamada WSS + User Token
                */
                // Llamada con WSS + Certificado
                //Aplicamos la seguridad de WebLogic
//                log.error(" - DomiclioFiscal WSS + Certificado");
//                ((BindingProvider) port).getRequestContext().put("weblogic.xml.crypto.wss.PKI_Initiator", "lanbide"); // Lanbide esta configurado en el weblogic como PKI
                // Fin LLamada con WSS + Certificado

                log.error("Seguridad de weblogic asignada correctamente");

                instanceDomFisc = port;
            }catch (Exception ex) {
                log.error("Error al crear la instacia del cliente webservce - Domicilio Fiscal " + ex.getMessage() + " --  Causa _: " + ex.getCause(), ex);
                throw ex;
            }
        } else {
            if (log.isDebugEnabled()) {
                log.error("Existe una instancia del cliente del servicio - Domicilio Fiscal");
            }
        }//if(instanceDomFisc == null)
        if (log.isDebugEnabled()) {
            log.error("getWebServiceClient() : END");
        }
        return instanceDomFisc;
    }

    public String WebServiceGetLangaiDemanda(int codOrganizacion, String numExpediente,
                                             //Integer entornoDespliegue,
                                             String tipo_doc, String num_doc) {
        //String codigoOperacion = "0";
        int resultadoLangaiDeman = -1;

        try {
            String nombreModulo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_MODULO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            String urlLocationString = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION+ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.LOCATION_SERVICIO_LANGAIDEMAN, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);

            log.error("URL - LangaiDeman: " + urlLocationString);

            try {
                /*
                 * 0 - No existe demandante o se ha producido un error
                 * 1 - Existe demandante y está en alta (SituAdm A)
                 * 2 - Existe demandante y está en suspensión (SituAdm S)
                 * 9 - Existe demandante y el estado es otro (SituAdm 'else')
                 */

                //  Preparamos el Servicio
                log.error("Intentamos Crear instacia del servicio ");
                LangaiDemServiceLocator locator = new LangaiDemServiceLocator();
                log.error("Instancia Creada ... LangaiDemServiceLocator");
                //// cambiar url location  de cada entorno
                log.error("Intentamos cambiar URL llamando al metodo setLangaiDemEndpointAddress URL a Cambiar : " + locator.getLangaiDemAddress());
                locator.setLangaiDemEndpointAddress(urlLocationString);
                log.error("URL Modificada : " + urlLocationString);
                log.error("Intentamos Obtener la Instancia de la interfaz que da acceso a las operaciones del servicio");
                LangaiDem langaiDem = locator.getLangaiDem();
                log.error("Existe Demanda - Instacia Creada - Disponemos a obtener prametros y llamar al metodo.");

                String cod_centro_usu = ConstantesMeLanbideInterop.COD_CENTRO_USU;
                String cod_ubic_usu = ConstantesMeLanbideInterop.COD_UBIC_USU;

                /**
                 * ******************** PERSONA FISICA - ACCESO
                 * *********************
                 */
                log.error("******** DATOS ENTRADA **********");
                log.error("CÓDIGO CENTRO: " + cod_centro_usu);
                log.error("CÓDIGO UBICACIÓN: " + cod_ubic_usu);
                log.error("NÚMERO DOCUMENTO: " + num_doc);
                log.error("TIPO DOCUMENTO: " + tipo_doc);
                log.error("*********************************");

                resultadoLangaiDeman = langaiDem.existeDemanda(cod_centro_usu, cod_ubic_usu, num_doc, tipo_doc);
            } catch (Exception ex) {
                log.error(ex.getMessage() + "  - Error al llamar al Servicio Langai Demanda - Causa: " + ex.getCause(), ex);
                throw ex;
            }

        } catch (Exception ex) {
            if(ex instanceof  org.apache.axis.AxisFault)
                resultadoLangaiDeman =-3;
            else
                resultadoLangaiDeman = -2;
        }

    /* StringBuilder xmlSalida = new StringBuilder();
        xmlSalida.append("<RESPUESTA>");
        xmlSalida.append("<CODIGO_OPERACION>");
        xmlSalida.append(codigoOperacion);
        xmlSalida.append("</CODIGO_OPERACION>");
        xmlSalida.append("<RESULTADO>");
        xmlSalida.append(resultadoLangaiDeman);
        xmlSalida.append("</RESULTADO>");
    */
        /*for(EspacioFormVO fila : lista)
         {
         xmlSalida.append("<FILA>");
         xmlSalida.append("<ID>");
         xmlSalida.append(fila.getId()!= null ? fila.getId().toString() : "");
         xmlSalida.append("</ID>");
         xmlSalida.append("<EPF_NUM>");
         xmlSalida.append(fila.getNumExp());
         xmlSalida.append("</EPF_NUM>");
         xmlSalida.append("<EPF_DES>");
         xmlSalida.append(fila.getDescripcion());
         xmlSalida.append("</EPF_DES>");
         xmlSalida.append("<EPF_SUP>");
         xmlSalida.append(fila.getSuperficie()!= null ? fila.getSuperficie().toString() : "");
         xmlSalida.append("</EPF_SUP>");
         xmlSalida.append("</FILA>");
         }*/
     /*
        xmlSalida.append("</RESPUESTA>");
     */
        /*
         try{
         response.setContentType("text/xml");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out = response.getWriter();
         out.print(xmlSalida.toString());
         out.flush();
         out.close();
         }catch(Exception e){
         e.printStackTrace();
         }//try-catch
         */

        //return resultadoDomFiscalResponse.getReturn0();

        //return xmlSalida.toString();
        return String.valueOf(resultadoLangaiDeman);
    }

        public String WebServiceGetCorrientePagoTgss(int codOrganizacion, String numExpediente,
                                                 TerceroVO _tercero, AdaptadorSQLBD adapt) {
        String resultadoCorrPagTgss = "";

        try {
            log.info("WebServiceGetCorrientePagoTgss - Inicio llamada NISAE unificada para TGSS");

            // Extraer el codigo de procedimiento (ej: LAK)
            String[] datosExpediente = numExpediente.split(ConstantesMeLanbideInterop.BARRA_SEPARADORA);
            String codProcExpte = datosExpediente[1];

            // Obtener el ID del procedimiento en formato LAN6
            String idProcoFlexia_LAN6 = gestionAdaptadoresLan6Config.getCodProcedimientoPlatea(codProcExpte);
            log.info("ID Procedimiento LAN6: " + idProcoFlexia_LAN6);

            // Preparar los objetos para el motor unificado
            ExpedienteNisaeVO expedienteNisaeVO = new ExpedienteNisaeVO();
            expedienteNisaeVO.setNumeroExpediente(numExpediente);
            expedienteNisaeVO.setTitularExpediente(_tercero);
            expedienteNisaeVO.setObservaciones("Consulta manual desde Interfaz de Interoperabilidad");

            InteropLlamadasServiciosNisae interopLlamadasServiciosNisae = new InteropLlamadasServiciosNisae();
            interopLlamadasServiciosNisae.setCodOrganizacion(codOrganizacion);
            interopLlamadasServiciosNisae.setProcedimientoHHFF(codProcExpte);
            interopLlamadasServiciosNisae.setNumeroExpediente(numExpediente);
            interopLlamadasServiciosNisae.setEjercicioHHFF(datosExpediente[0]);
            interopLlamadasServiciosNisae.setFkWSSolicitado(2); // 2 = TGSS en tabla INTEROP_SERVICIOS_NISAE

            // Llamada al motor comun
            resultadoCorrPagTgss = gestionServiciosNISAE.ejecutarConsultaIndividualTGSS(
                    codOrganizacion, interopLlamadasServiciosNisae, idProcoFlexia_LAN6, expedienteNisaeVO, adapt);

        } catch (Exception ex) {
            log.error("Exception general al llamar al WS TGSS NISAE", ex);
            resultadoCorrPagTgss = "Error al procesar la peticion en el servicio TGSS NISAE: " + ex.getMessage();
        }

        log.info("Retorno del WS TGSS NISAE: " + resultadoCorrPagTgss);
        return resultadoCorrPagTgss;
    }

    private PeticionPortType getWebServiceCorrientePagoTgss(String urlString, String nameSpaceUri, String localPart) throws Exception, IOException {
        if (log.isDebugEnabled()) {
            log.error("getWebServiceCorrientePagoTgss() : BEGIN");
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceCorrientePagoTgss() : urlString: " + urlString);
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceCorrientePagoTgss() : nameSpaceUri: " + nameSpaceUri);
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceCorrientePagoTgss() : localPart: " + localPart);
        }

        if (instanceCorrienteTgss == null) {
            if (log.isDebugEnabled()) {
                log.error("No existe instancia, creamos una nueva");
            }
            URL url = null;
            QName qName = null;

            if (log.isDebugEnabled()) {
                log.error("Recuperamos la URL del servicio web");
            }
            try {
                url = new URL(urlString);
            } catch (Exception ex) {
                log.error("Se ha producido un error parseando la URL del servicio web Corriente pago TGSS " + ex.getMessage());
                throw new Exception("Se ha producido un error parseando la URL del servicio web Corriente pago TGSS", ex);
            }

            if (log.isDebugEnabled()) {
                log.error("Recuperamos el service name del servicio web Corriente pago TGSS");
            }
            try {
                qName = new QName(nameSpaceUri, localPart);
            } catch (Exception ex) {
                log.error("Se ha producido un error recuperando el qName del servicio web Corriente pago TGSS " + ex.getMessage());
                throw new Exception("Se ha producido un error recuperando el qName del servicio web Corriente pago TGSS ", ex);
            }//try-catch
            if (log.isDebugEnabled()) {
                log.error("Creamos la instancia del cliente");
            }
            try {
                String _url = urlString;

                //Cargamos el fichero xml con las políticas de seguridad
                log.error("Realizamos el URL pathXml");
                URL pathXml = Thread.currentThread().getContextClassLoader().getResource("META-INF/x53jsGetCorrientePagoTgssWS.xml");
                QName _qName = new QName(nameSpaceUri,localPart );

                //Instanciamos la clase del cliente del WS
                log.error(" - X53JsGetEpigrafesWSService(pathXml, _qName) - " + pathXml.toString() + "  -**-  " + _qName.toString() + " Qname PERPARADO CON DATOS DEL PROPERTIES : " + qName.toString());
                X53JsCorrientePagosService datosCorrientePagoTgssWS = new X53JsCorrientePagosService(pathXml, _qName);

                //Obtenemos el port del WS
                //PadronIndividualPortType port = prestacionesWS.getGetPadronIndividualPort();
                PeticionPortType port = datosCorrientePagoTgssWS.getX53JsCorrientePagosServicePort();
                log.error("Obtenemos el port");

                //Asignamos la URL del WS
                ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, _url);
                log.error("Actualizamos la URL del port");

                log.error("Vamos a poner la seguridad de weblogic");
                // Llamdas con Usuario Basico. Pasando User/pass
                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                ((BindingProvider) port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, _USER);
                ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, _PASS);
                log.error("Asignadas las claves de Seguridad. ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY");

                // Inicio LLamada con WSS + User Token
                //Asignamos un Credential Provider con el Usuario y Password para la autenticación
               /*
                List<CredentialProvider> credProviders = new ArrayList<CredentialProvider>();
                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                CredentialProvider cp = new ClientUNTCredentialProvider(_USER.getBytes(), _PASS.getBytes());  //"USER".getBytes(), "PASSWORD".getBytes()
                credProviders.add(cp);
                Map<String, Object> rc = ((BindingProvider) port).getRequestContext();
                rc.put(WSSecurityContext.CREDENTIAL_PROVIDER_LIST, credProviders);
                */
                // Fin Llamada WSS + User Token

                // Llamada con WSS + Certificado
                //Aplicamos la seguridad de WebLogic
//                ((BindingProvider) port).getRequestContext().put("weblogic.xml.crypto.wss.PKI_Initiator", "lanbide"); // Lanbide esta configurado en el weblogic como PKI
                // Fin LLamada con WSS + Certificado

                log.error("Seguridad de weblogic asignada correctamente");

                instanceCorrienteTgss = port;
                ///
                if (log.isDebugEnabled()) {
                    log.error("Asignamos y Retornamos la instancia del cliente - Corriente pagos TGSS");
                }
            }catch (Exception ex) {
                log.error("Error al crear la instacia del cliente webservce " + ex.getMessage() + " --  Causa _: " + ex.getCause(), ex);
                throw ex;
            }
        } else {
            if (log.isDebugEnabled()) {
                log.error("Existe una instancia del cliente del servicio");
            }
        }//if(instanceCorrienteTgss == null)
        if (log.isDebugEnabled()) {
            log.error("getWebServiceClient() : END");
        }
        return instanceCorrienteTgss;
    }

    public String WebServiceGetCorrientePagoHHFF(int codOrganizacion, String numExpediente,
                                                   TerceroVO _tercero, String codigoProcedimiento, 
                                                   String nombreProcedimiento, AdaptadorSQLBD adapt) {
        String resultadoCorrPagHHFF = "";

        try {
            log.info("WebServiceGetCorrientePagoHHFF - Inicio llamada NISAE unificada para HHFF");
            
            // 1. Extraer el código de procedimiento (ej: LAK)
            String[] datosExpediente = numExpediente.split(ConstantesMeLanbideInterop.BARRA_SEPARADORA);
            String codProcExpte = datosExpediente[1];
            
            // Obtener el ID del procedimiento en formato LAN6
            String idProcoFlexia_LAN6 = gestionAdaptadoresLan6Config.getCodProcedimientoPlatea(codProcExpte);
            log.info("ID Procedimiento LAN6: " + idProcoFlexia_LAN6);
            
            // 3. Preparar los objetos para el motor unificado
            ExpedienteNisaeVO expedienteNisaeVO = new ExpedienteNisaeVO();
            expedienteNisaeVO.setNumeroExpediente(numExpediente);
            expedienteNisaeVO.setTitularExpediente(_tercero);
            expedienteNisaeVO.setObservaciones("Consulta manual desde Interfaz de Interoperabilidad");
            
            InteropLlamadasServiciosNisae interopLlamadasServiciosNisae = new InteropLlamadasServiciosNisae();
            interopLlamadasServiciosNisae.setCodOrganizacion(codOrganizacion);
            interopLlamadasServiciosNisae.setProcedimientoHHFF(codProcExpte);
            interopLlamadasServiciosNisae.setNumeroExpediente(numExpediente);
            interopLlamadasServiciosNisae.setEjercicioHHFF(datosExpediente[0]);
            interopLlamadasServiciosNisae.setFkWSSolicitado(1); // 1 = HHFF en tabla INTEROP_SERVICIOS_NISAE

            // 4. LLAMADA AL MOTOR COMÚN (Aquí es donde se hace todo el trabajo ahora)
            resultadoCorrPagHHFF = gestionServiciosNISAE.ejecutarConsultaIndividualHHFF(codOrganizacion, interopLlamadasServiciosNisae, idProcoFlexia_LAN6, expedienteNisaeVO, adapt);

        } catch (Exception ex) {
            log.error("Exception general al llamar al WS HHFF NISAE", ex);
            resultadoCorrPagHHFF = "Error al procesar la petición en el servicio HHFF NISAE: " + ex.getMessage();
        }

        log.info("Retorno del WS HHFF NISAE: " + resultadoCorrPagHHFF);
        return resultadoCorrPagHHFF;
    }

    public String WebServiceGetObligacionesTributariasDipu(int codOrganizacion, String numExpediente,
                                                           String pOrganoSolicitante, String pUnidadTramitadora,
                                                           String pCodigoProcedimiento, String pNombreProcedimiento,
                                                           String pFinalidad, String pConsentimiento,
                                                           String pNIFTramitador, String pNombreTramitador,
                                                           String pIdExpediente, String pTipoTramite,
                                                           String pTipoDocumento, String pNumDocumento,
                                                           String pTitularDocumento, String pNombreTitular, String pApellido1Titular, String pApellido2Titular,
                                                           String pTipoEmisor, String pTerritorio) {
        String codigoOperacion = "0";
        String resultadoObligaTribuDipu = "";

        try {
            //Recuperamos Datos: Nombre Modulo, el valor de la URL y QName  del Sercvicio
            String nombreModulo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_MODULO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            String urlString = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.URL_SERVICIO_OBLIGTRIBUTARIAS, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("URL: " + urlString);
            String nameSpaceUri = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.QNAME_NAMESPACEURI_OBLIGTRIBUTARIAS, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("NameSpaceUri: " + nameSpaceUri);
            String localPart = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                    + ConstantesMeLanbideInterop.QNAME_LOCALPART_OBLIGTRIBUTARIAS, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.error("LocalPart: " + localPart);

            X53JsGetObligacionesAllWSDelegate service = getWebServiceObligacionesTributariasDipu(urlString, nameSpaceUri, localPart);

            try {
                resultadoObligaTribuDipu = service.getObligacionesAllWS(pOrganoSolicitante, pUnidadTramitadora,
                        pCodigoProcedimiento, pNombreProcedimiento,
                        pFinalidad, pConsentimiento,
                        pNIFTramitador, pNombreTramitador,
                        pIdExpediente, pTipoTramite,
                        pTipoDocumento, pNumDocumento, pTitularDocumento, pNombreTitular, pApellido1Titular, pApellido2Titular,
                        pTipoEmisor, pTerritorio);

                if(resultadoObligaTribuDipu!=null && !resultadoObligaTribuDipu.isEmpty()){
                    log.error("Respuesta de Obligaciones Tributarias Dipu :  " + resultadoObligaTribuDipu);
                }else{
                    log.error("Respuesta de servicio recibida a null o vacia ");
                }
            } catch (Exception ex) {
                log.error(ex.getMessage() + "  - Error al llamar al Servicio Obligaciones Tributarias Dipu - Causa: " + ex.getLocalizedMessage(), ex);
                resultadoObligaTribuDipu="Error al intentar procesar la llamada al servicio de verificacion de obligaciones tributarias.";
            }
        } catch (Exception ex) {
            log.error("Exception - General al llamar al WS de Obligaciones Tributarias Dipu. ", ex);
            codigoOperacion = "2";
            resultadoObligaTribuDipu="Error preparando los parametros para la llamada al servicio de verificacion de obligaciones tributarias";
        }

        //    StringBuilder xmlSalida = new StringBuilder();
        //    xmlSalida.append("<RESPUESTA>");
        //    xmlSalida.append("<CODIGO_OPERACION>");
        //    xmlSalida.append(codigoOperacion);
        //    xmlSalida.append("</CODIGO_OPERACION>");
        //    xmlSalida.append("<RESULTADO>");
        //    xmlSalida.append(resultadoObligaTribuDipu);
        //    xmlSalida.append("</RESULTADO>");
        /*for(EspacioFormVO fila : lista)
         {
         xmlSalida.append("<FILA>");
         xmlSalida.append("<ID>");
         xmlSalida.append(fila.getId()!= null ? fila.getId().toString() : "");
         xmlSalida.append("</ID>");
         xmlSalida.append("<EPF_NUM>");
         xmlSalida.append(fila.getNumExp());
         xmlSalida.append("</EPF_NUM>");
         xmlSalida.append("<EPF_DES>");
         xmlSalida.append(fila.getDescripcion());
         xmlSalida.append("</EPF_DES>");
         xmlSalida.append("<EPF_SUP>");
         xmlSalida.append(fila.getSuperficie()!= null ? fila.getSuperficie().toString() : "");
         xmlSalida.append("</EPF_SUP>");
         xmlSalida.append("</FILA>");
         }*/
        //    xmlSalida.append("</RESPUESTA>");
        /*
         try{
         response.setContentType("text/xml");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out = response.getWriter();
         out.print(xmlSalida.toString());
         out.flush();
         out.close();
         }catch(Exception e){
         e.printStackTrace();
         }//try-catch
         */
        //return resultadoEpigrafResponse.getReturn0();
        //    return xmlSalida.toString();
        log.error("Salida de la llamada al WS retornada : " + resultadoObligaTribuDipu);
        return resultadoObligaTribuDipu;
    }

    private X53JsGetObligacionesAllWSDelegate getWebServiceObligacionesTributariasDipu(String urlString, String nameSpaceUri, String localPart) throws Exception, IOException {
        if (log.isDebugEnabled()) {
            log.error("getWebServiceObligacionesTributariasDipu() : BEGIN");
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceObligacionesTributariasDipu() : urlString: " + urlString);
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceObligacionesTributariasDipu() : nameSpaceUri: " + nameSpaceUri);
        }
        if (log.isDebugEnabled()) {
            log.error("getWebServiceObligacionesTributariasDipu() : localPart: " + localPart);
        }

        if (instanceObligacionesTribuDipu == null) {
            if (log.isDebugEnabled()) {
                log.error("No existe instancia, creamos una nueva");
            }
            URL url = null;
            QName qName = null;

            if (log.isDebugEnabled()) {
                log.error("Recuperamos la URL del servicio web");
            }
            try {
                url = new URL(urlString);
            } catch (Exception ex) {
                log.error("Se ha producido un error parseando la URL del servicio web obligaicones tributarias dipu " + ex.getMessage());
                throw new Exception("Se ha producido un error parseando la URL del servicio web obligaicones tributarias dipu", ex);
            }

            if (log.isDebugEnabled()) {
                log.error("Recuperamos el service name del servicio web obligaicones tributarias dipu");
            }
            try {
                qName = new QName(nameSpaceUri, localPart);
            } catch (Exception ex) {
                log.error("Se ha producido un error recuperando el qName del servicio web obligaicones tributarias dipu " + ex.getMessage());
                throw new Exception("Se ha producido un error recuperando el qName del servicio web obligaicones tributarias dipu ", ex);
            }//try-catch
            if (log.isDebugEnabled()) {
                log.error("Creamos la instancia del cliente");
            }
            try {
                String _url = urlString;

                //Cargamos el fichero xml con las políticas de seguridad
                log.error("Realizamos el URL pathXml");
                URL pathXml = Thread.currentThread().getContextClassLoader().getResource("META-INF/x53jsGetObligacionesGenericoUser.xml");
                QName _qName = new QName(nameSpaceUri,localPart );

                //Instanciamos la clase del cliente del WS
                log.error(" - x53jsGetObligacionesGenericoUser.xml(pathXml, _qName) - " + pathXml.toString() + "  -**-  " + _qName.toString() + " Qname PERPARADO CON DATOS DEL PROPERTIES : " + qName.toString());
                X53JsGetObligacionesAllWSService datosObligacionesTrubuDipuWS = new X53JsGetObligacionesAllWSService(pathXml, _qName);

                //Obtenemos el port del WS
                X53JsGetObligacionesAllWSDelegate port = datosObligacionesTrubuDipuWS.getX53JsGetObligacionesAllWSPort();
                log.error("Obtenemos el port");

                //Asignamos la URL del WS
                ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, _url);
                log.error("Actualizamos la URL del port");

                log.error("Vamos a poner la seguridad de weblogic");

                // Llamdas con Usuario Basico. Pasando User/pass
                //String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                //String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                //((BindingProvider) port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, _USER);
                //((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, _PASS);
                //log.error("Asignadas las claves de Seguridad. ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY");

                // Inicio LLamada con WSS + User Token    Opcion User Seguridad
                //Asignamos un Credential Provider con el Usuario y Password para la autenticación

                //Este bloque que usa clases de weblogic: Comentar para desplegar / des comentar para subir a repo
                /*
                List<CredentialProvider> credProviders = new ArrayList<CredentialProvider>();
                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                CredentialProvider cp = new ClientUNTCredentialProvider(_USER.getBytes(), _PASS.getBytes());  //"USER".getBytes(), "PASSWORD".getBytes()
                credProviders.add(cp);
                Map<String, Object> rc = ((BindingProvider) port).getRequestContext();
                rc.put(WSSecurityContext.CREDENTIAL_PROVIDER_LIST, credProviders);
*/
                // Fin Llamada WSS + User Token

                // Llamada con WSS + Certificado
                //Aplicamos la seguridad de WebLogic
//                ((BindingProvider) port).getRequestContext().put("weblogic.xml.crypto.wss.PKI_Initiator", "lanbide"); // Lanbide esta configurado en el weblogic como PKI
                // Fin LLamada con WSS + Certificado

                log.error("Seguridad de weblogic asignada correctamente");

                instanceObligacionesTribuDipu = port;
                ///
                if (log.isDebugEnabled()) {
                    log.error("Asignamos y Retornamos la instancia del cliente - obligaicones tributarias dipu");
                }
            }catch (Exception ex) {
                log.error("Error al crear la instacia del cliente webservce " + ex.getMessage() + " --  Causa _: " + ex.getMessage(), ex);
                throw ex;
            }
        } else {
            if (log.isDebugEnabled()) {
                log.error("Existe una instancia del cliente del servicio");
            }
        }//if(instanceObligacionesTribuDipu == null)
        if (log.isDebugEnabled()) {
            log.info("getWebServiceClient() : END");
        }
        return instanceObligacionesTribuDipu;
    }

    public LangaiDemanda getWSClientLangaiDemandaFSE() throws Exception {
        log.info("getWSClientLangaiDemandaFSE - Begin()");
        LangaiDemanda cliente = null;
        LangaiDemandaService servicio = null;

        try {
            String nombreModulo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_MODULO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            String urlLocationString = ConfigurationParameter.getParameter(
                    ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                            + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                            + ConstantesMeLanbideInterop.WS_LANGAIDEMANDA_FSE_URL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            //  Preparamos el Servicio
            log.info("Creamos QNAME de instacia del servicio ");
            String targetNameSpaceWS = ConfigurationParameter.getParameter(
                    ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                            + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                            + ConstantesMeLanbideInterop.WS_LANGAIDEMANDA_FSE_TARGETNAMESPACE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            String NameWS = ConfigurationParameter.getParameter(
                    ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                            + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                            + ConstantesMeLanbideInterop.WS_LANGAIDEMANDA_FSE_NAME, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            log.info("Configuracion Obtenida del Properties : "
                    + "URl : " + urlLocationString
                    + "TargetNameSpace : " + targetNameSpaceWS
                    + "nameWS : " + NameWS);
            QName qnameCliente =  new QName(targetNameSpaceWS,NameWS);
            log.info("Qname Creado getNamespaceURI()/getLocalPart() : " + qnameCliente.getNamespaceURI() + "/" + qnameCliente.getLocalPart());
            URL urlWS = new URL(urlLocationString);
            log.info("URL Creado  : " + urlWS.toString());
            log.info("Creamos instacia del servicio -  Con URL de acuerdo al entorno");
            servicio = new LangaiDemandaService(urlWS, qnameCliente);
            log.info("Obtenemos getLangaiDemandaPort para retornarlo");
            cliente = servicio.getLangaiDemandaPort();
        }catch(Exception ex){
            log.error("getWSClientLangaiDemandaFSE - Error al crear la instacia del cliente." + ex.getMessage(), ex);
            throw ex;
        }
        log.error("getWSClientLangaiDemandaFSE - End()");
        return cliente;
    }

    private String traducirCodigosMensajesSalidaWSLangaiDemandaFSE(String respuestaServicio, int Idioma){
        log.error("traducirCodigosMensajesSalidaWSLangaiDemandaFSE - Begin()");
        String respuesta="";
        log.error("traducirCodigosMensajesSalidaWSLangaiDemandaFSE - Parametros: respuestaServicio, Idioma " + respuestaServicio + "," + Idioma);
        try {
            if(respuestaServicio != null){
                String[] codigosSalida = respuestaServicio.split(ConstantesMeLanbideInterop.SEPARADOR_VALORES_CONF);
                log.error("traducirCodigosMensajesSalidaWSLangaiDemandaFSE - Tamaño del Arreglo de Codigos Respuesta del WS " + codigosSalida.length);
                for (int i = 0; i < codigosSalida.length; i++) {
                    if(respuesta!=""){
                        respuesta = respuesta + " \n " + codigosSalida[i] + " - " + MeLanbideInteropI18n.getInstance().getMensaje(Idioma, "msg.respuesta.langaidemaWS.fse."+codigosSalida[i]);
                    }else{
                        respuesta = codigosSalida[i] + " - " + MeLanbideInteropI18n.getInstance().getMensaje(Idioma, "msg.respuesta.langaidemaWS.fse."+codigosSalida[i]);
                    }
                }
            }
        } catch (Exception e) {
            log.error("traducirCodigosMensajesSalidaWSLangaiDemandaFSE - Error() " + e.getMessage());
            respuesta = respuestaServicio + " - Error al traducir la respuesta del servicio. " + e.getMessage();
        }
        log.error("traducirCodigosMensajesSalidaWSLangaiDemandaFSE - Retorno : " + respuesta);
        log.error("traducirCodigosMensajesSalidaWSLangaiDemandaFSE - End()");
        return respuesta;
    }

    private String tratarRespuestaXMLWServices(String respuestaXML_WS) {
        String respuestaTratada = "";
        try{
            TratarRespuestasXMLWS instanciaTratarRespuestasXMLWS = new TratarRespuestasXMLWS();
            respuestaTratada = instanciaTratarRespuestasXMLWS.obtenerStringRespuestaFromXMLWSDiputacion(respuestaXML_WS);
        }catch(Exception ex){
            log.error("tratarRespuestaXMLWServices -  Error al tratar la respuesta XML de un WS " + ex.getMessage(),ex);
            respuestaXML_WS.replaceAll("\n|\r","");
        }
        return respuestaTratada;
    }

    private String getCodProcedimientoDACIMAFromProperties(String codProcedimientoFlexia) {
        return ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + codProcedimientoFlexia,ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
    }

    private UsuarioValueObject getUsarioLogueadoEnSession(HttpServletRequest request) {
        UsuarioValueObject usuario = new UsuarioValueObject();
        try
        {
            HttpSession session = request!=null?request.getSession():null;
            if (session != null)
            {
                usuario = (UsuarioValueObject) session.getAttribute("usuario");
                //idiomaUsuario = usuario.getIdioma();
                //apl = usuario.getAppCod();
                //css = usuario.getCss();
            }
        }
        catch(Exception ex)
        {
            log.error("Error al recuperar los datos del usaurio desde la session activa del sistema", ex);
        }
        return usuario;
    }

    private PadronIndividualPortType getWSClientDatosPadron(String urlString, String nameSpaceUri, String localPart) throws Exception, IOException {
        if (log.isDebugEnabled()) {
            log.error("getWSClientDatosPadron() : BEGIN");
        }
        if (log.isDebugEnabled()) {
            log.error("getWSClientDatosPadron() : urlString: " + urlString);
        }
        if (log.isDebugEnabled()) {
            log.error("getWSClientDatosPadron() : nameSpaceUri: " + nameSpaceUri);
        }
        if (log.isDebugEnabled()) {
            log.error("getWSClientDatosPadron() : localPart: " + localPart);
        }

        if (instanceDatosPadron == null) {
            if (log.isDebugEnabled()) {
                log.error("No existe instancia, creamos una nueva");
            }
            URL url = null;
            QName qName = null;

            if (log.isDebugEnabled()) {
                log.error("Recuperamos la URL del servicio web");
            }
            try {
                url = new URL(urlString);
            } catch (Exception ex) {
                log.error("Se ha producido un error parseando la URL del servicio web datos padron " + ex.getMessage());
                throw new Exception("Se ha producido un error parseando la URL del servicio web datos padron", ex);
            }

            if (log.isDebugEnabled()) {
                log.error("Recuperamos el service name del servicio web");
            }
            try {
                qName = new QName(nameSpaceUri, localPart);
            } catch (Exception ex) {
                log.error("Se ha producido un error recuperando el qName del servicio web datos padron " + ex.getMessage());
                throw new Exception("Se ha producido un error recuperando el qName del servicio web datos padron ", ex);
            }//try-catch

            try {
                String _url = urlString;
                //Cargamos el fichero xml con las políticas de seguridad
                log.error("Realizamos el URL pathXml" );
                URL pathXml = Thread.currentThread().getContextClassLoader().getResource("META-INF/x53jsGetDatosPadronV3User.xml");
                QName _qName = new QName("http://www.map.es/xml-schemas", "x53jsGetDatosPadronV3");

                //Instanciamos la clase del cliente del WS
                log.error(" - x53jsGetDatosPadronV3(pathXml, _qName) - " + pathXml.toString() + "  -**-  "  + _qName.toString() + " Qname PERPARADO CON DATOS DEL PROPERTIES : " + qName.toString());
                X53JsGetDatosPadronV3 datosPadrondWS = new X53JsGetDatosPadronV3(pathXml, _qName);

                //Obtenemos el port del WS
                PadronIndividualPortType port = datosPadrondWS.getGetPadronIndividualPort();
                log.error("Obtenemos el port");

                //Asignamos la URL del WS
                ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, _url);
                log.error("Actualizamos la URL del port");

                log.error("Vamos a poner la seguridad de weblogic");
                // Prueba User / Contraseña en la request
                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                ((BindingProvider) port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, _USER);
                ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, _PASS);
                log.error("Hemos Asignado User/pass en ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, _PASS);");

                // Inicio Invocacion WSS + User Token
                //Asignamos un Credential Provider con el Usuario y Password para la autenticación
                /*
                List<CredentialProvider> credProviders = new ArrayList<CredentialProvider>();
                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                CredentialProvider cp = new ClientUNTCredentialProvider(_USER.getBytes(), _PASS.getBytes());  //"USER".getBytes(), "PASSWORD".getBytes()
                credProviders.add(cp);
                Map<String, Object> rc = ((BindingProvider) port).getRequestContext();
                rc.put(WSSecurityContext.CREDENTIAL_PROVIDER_LIST, credProviders);
                */
                // Fin Invocacion WSS + User Token

                // Inicio Seguridad de WebLogic WSS + Certificado
                //((BindingProvider) port).getRequestContext().put("weblogic.xml.crypto.wss.PKI_Initiator", "lanbide"); // Lanbide esta configurado en el weblogic como PKI
                // Fin Seguridad de WebLogic WSS + Certificado

                log.error("Seguridad de weblogic asignada correctamente");

                instanceDatosPadron = port;
                ///
                if (log.isDebugEnabled()) {
                    log.error("Retornamos la instancia del cliente");
                }

            }/*catch(ServiceException e) {
             e.printStackTrace();
             log.error("Error al crear la instacia del cleinte webservce " + e.getMessage() + " --  Causa _: " + e.getCause() , e);
             } catch(RemoteException e) {
             e.printStackTrace();
             log.error("Error al crear la instacia del cleinte webservce " + e.getMessage() + " --  Causa _: " + e.getCause() , e);
             }*/ catch (Exception ex) {
                log.error("Error al crear la instacia del cliente webservce " + ex.getMessage() + " --  Causa _: " + ex.getCause(), ex);
            }
        } else {
            if (log.isDebugEnabled()) {
                log.error("Existe una instancia del cliente del servicio");
            }
        }//if(instanceDatosPadron == null)
        if (log.isDebugEnabled()) {
            log.error("getWSClientDatosPadron() : END");
        }
        return instanceDatosPadron;
    }//getWSClientDatosPadron

    private X53JsGetDatosResidenciaWSDelegate getWSClientDatosResidencia(String urlString, String nameSpaceUri, String localPart) throws Exception, IOException {
        if (log.isDebugEnabled()) {
            log.error("getWSClientDatosResidencia() : BEGIN");
        }
        if (log.isDebugEnabled()) {
            log.error("getWSClientDatosResidencia() : urlString: " + urlString);
        }
        if (log.isDebugEnabled()) {
            log.error("getWSClientDatosResidencia() : nameSpaceUri: " + nameSpaceUri);
        }
        if (log.isDebugEnabled()) {
            log.error("getWSClientDatosResidencia() : localPart: " + localPart);
        }

        if (instanceDatosResidencia == null) {
            if (log.isDebugEnabled()) {
                log.error("No existe instancia, creamos una nueva");
            }
            URL url = null;
            QName qName = null;

            if (log.isDebugEnabled()) {
                log.error("Recuperamos la URL del servicio web");
            }
            try {
                url = new URL(urlString);
            } catch (Exception ex) {
                log.error("Se ha producido un error parseando la URL del servicio web datos residencia " + ex.getMessage());
                throw new Exception("Se ha producido un error parseando la URL del servicio web datos residencia", ex);
            }

            if (log.isDebugEnabled()) {
                log.error("Recuperamos el service name del servicio web");
            }
            try {
                qName = new QName(nameSpaceUri, localPart);
            } catch (Exception ex) {
                log.error("Se ha producido un error recuperando el qName del servicio web datos residencia" + ex.getMessage());
                throw new Exception("Se ha producido un error recuperando el qName del servicio web datos residencia ", ex);
            }//try-catch

            try {
                String _url = urlString;
                //Cargamos el fichero xml con las políticas de seguridad
                log.error("Realizamos el URL pathXml" );
                URL pathXml = Thread.currentThread().getContextClassLoader().getResource("META-INF/x53jsGetDatosResidenciaBasic.xml");
                QName _qName = new QName("http://requirenteWS/", "X53jsGetDatosResidenciaWSService");

                //Instanciamos la clase del cliente del WS
                log.error(" - X53jsGetDatosResidenciaWSService(pathXml, _qName) - " + pathXml.toString() + "  -**-  "  + _qName.toString() + " Qname PERPARADO CON DATOS DEL PROPERTIES : " + qName.toString());
                X53JsGetDatosResidenciaWSService datosResidenciaWS = new X53JsGetDatosResidenciaWSService(pathXml, _qName);

                //Obtenemos el port del WS
                X53JsGetDatosResidenciaWSDelegate port = datosResidenciaWS.getX53JsGetDatosResidenciaWSPort();
                log.error("Obtenemos el port");

                //Asignamos la URL del WS
                ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, _url);
                log.error("Actualizamos la URL del port");

                log.error("Vamos a poner la seguridad de weblogic");
                // Prueba User / Contraseña en la request
                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                ((BindingProvider) port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, _USER);
                ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, _PASS);
                log.error("Hemos Asignado User/pass en ((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, _PASS);");

                // Inicio Invocacion WSS + User Token
                //Asignamos un Credential Provider con el Usuario y Password para la autenticación
                /*
                List<CredentialProvider> credProviders = new ArrayList<CredentialProvider>();
                String _USER = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.USUARIO_TOKEN_WSEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                String _PASS = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONTRAS_TOKEN_ESEJIE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                CredentialProvider cp = new ClientUNTCredentialProvider(_USER.getBytes(), _PASS.getBytes());  //"USER".getBytes(), "PASSWORD".getBytes()
                credProviders.add(cp);
                Map<String, Object> rc = ((BindingProvider) port).getRequestContext();
                rc.put(WSSecurityContext.CREDENTIAL_PROVIDER_LIST, credProviders);
                */
                // Fin Invocacion WSS + User Token

                // Inicio Seguridad de WebLogic WSS + Certificado
                //((BindingProvider) port).getRequestContext().put("weblogic.xml.crypto.wss.PKI_Initiator", "lanbide"); // Lanbide esta configurado en el weblogic como PKI
                //log.error("Asignado como weblogic.xml.crypto.wss.PKI_Initiator certificado de lanbide en weblogic en el port");
                //Fin Seguridad de WebLogic WSS + Certificado

                log.error("Seguridad de weblogic asignada correctamente");

                instanceDatosResidencia = port;
                ///
                if (log.isDebugEnabled()) {
                    log.error("Retornamos la instancia del cliente");
                }

            }catch (Exception ex) {
                log.error("Error al crear la instacia del cliente webservce " + ex.getMessage() + " --  Causa _: " + ex.getCause()!=null?ex.getCause().getMessage():"-", ex);
            }
        } else {
            if (log.isDebugEnabled()) {
                log.error("Existe una instancia del cliente del servicio");
            }
        }//if(instanceDatosPadron == null)
        if (log.isDebugEnabled()) {
            log.error("getWSClientDatosResidencia() : END");
        }
        return instanceDatosResidencia;
    }//getWSClientDatosResidencia

    private String getFechaInicioServicioFSE(String codProcExpte,String numExpediente,HashMap<String,String> paramAdicioWhereModExten,AdaptadorSQLBD adapt){
        log.error("getFechaInicioServicioFSE - Begin()");
        log.error("Parametros Entrada : codProcExpte,numExpediente = "+codProcExpte+","+numExpediente);
        String fechaInicioServ="";
        try {
            MeLanbideInteropManager interopManager = MeLanbideInteropManager.getInstance();
            String tabla = "";
            String nombreCampo = "";
            String codCampoSuple = "";
            String nombreCampoAuxWhere = "";
            String campoIDNumExp = "";
            if("LAK".equalsIgnoreCase(codProcExpte)){
                tabla = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_INICIO_SERV_TABLA_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                nombreCampo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_INICIO_SERV_CAMPO_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                campoIDNumExp = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_INICIO_SERV_IDEXP_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                fechaInicioServ=interopManager.getFechaInicioFinServicioFSE_DSD_ModuloExte(codProcExpte,numExpediente,tabla,nombreCampo,campoIDNumExp,paramAdicioWhereModExten,adapt);
            }else if("APEC".equalsIgnoreCase(codProcExpte)){

                tabla = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_INICIO_SERV_TABLA_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                nombreCampo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_INICIO_SERV_CAMPO_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                codCampoSuple = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_INICIO_SERV_CODCS_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                nombreCampoAuxWhere = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_INICIO_SERV_CAMPOAUX_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                campoIDNumExp = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_INICIO_SERV_IDEXP_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                fechaInicioServ = interopManager.getFechaInicioServicioFSE_DSD_CSuplementario(codProcExpte, numExpediente, tabla, nombreCampo, codCampoSuple, campoIDNumExp, nombreCampoAuxWhere, adapt);
            }
            log.error("Parametros Recuperados desde el Properties : tabla,nombreCampo,codCampoSuple,campoIDNumExp,nombreCampoAuxWhere = " + tabla+","+nombreCampo+","+codCampoSuple+","+campoIDNumExp+","+nombreCampoAuxWhere);
        } catch (Exception e) {
            log.error("Error al recuperar la fecha inicio de servicio para el WS FSE",e);
            fechaInicioServ="";
        }
        log.error("getFechaInicioServicioFSE - End() - fechaInicioServ = " + fechaInicioServ);
        return fechaInicioServ;
    }

    private String getFechaFinServicioFSE(String codProcExpte,String numExpediente,HashMap<String,String> paramAdicioWhereModExten,AdaptadorSQLBD adapt) {
        log.error("getFechaFinServicioFSE - Begin()");
        log.error("Parametros Entrada : codProcExpte,numExpediente = "+codProcExpte+","+numExpediente);
        String fechaFinServ="";
        try {
            MeLanbideInteropManager interopManager = MeLanbideInteropManager.getInstance();
            String tabla = "";
            String nombreCampo = "";
            String codCampoSuple = "";
            String nombreCampoAuxWhere = "";
            String campoIDNumExp = "";
            if ("LAK".equalsIgnoreCase(codProcExpte)) {
                tabla = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_FIN_SERV_TABLA_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                nombreCampo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_FIN_SERV_CAMPO_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                campoIDNumExp = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_FIN_SERV_IDEXP_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                fechaFinServ = interopManager.getFechaInicioFinServicioFSE_DSD_ModuloExte(codProcExpte, numExpediente, tabla, nombreCampo, campoIDNumExp, paramAdicioWhereModExten, adapt);
            } else if ("APEC".equalsIgnoreCase(codProcExpte)) {
                tabla = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_FIN_SERV_TABLA_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                nombreCampo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_FIN_SERV_CAMPO_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                nombreCampoAuxWhere = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_FIN_SERV_CAMPOAUX_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                codCampoSuple = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_FIN_SERV_CODCS_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                campoIDNumExp = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_FECHA_FIN_SERV_IDEXP_ + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                fechaFinServ=MeLanbideInteropManager.getInstance().getFechaFinServicioFSE(codProcExpte,numExpediente,tabla,nombreCampo,codCampoSuple,campoIDNumExp,nombreCampoAuxWhere,adapt);
            }
            log.error("Parametros Recuperados desde el Properties : tabla,nombreCampo,codCampoSuple,campoIDNumExp,nombreCampoAuxWhere = " + tabla + "," + nombreCampo + "," + codCampoSuple + "," + campoIDNumExp+","+nombreCampoAuxWhere);

        } catch (Exception e) {
            log.error("Error al recuperar la fecha fin de servicio para el WS FSE",e);
            fechaFinServ="";
        }
        log.error("getFechaFinServicioFSE - End() - fechaFinServ = " + fechaFinServ);
        return fechaFinServ;
    }

    private String getResultadoParametroWSFSE(String codProcExpte, String numExpediente, AdaptadorSQLBD adapt) {
        log.error("getResultadoParametroWSFSE - Begin()");
        log.error("Parametros Entrada : codProcExpte,numExpediente = " + codProcExpte + "," + numExpediente);
        String resultado = "";
        try {
            /**
             * SEGUN PROCEDIMIENTO EJECUTAR UN METODO U OTRO.
             * PUEDE GUARDARSE EN CS DE EXPEDIENTE, DE TRAMITE O EN TABLAS DE MODULOS DE EXTENSION
             */
            MeLanbideInteropManager interopManager = MeLanbideInteropManager.getInstance();
            if("APEC".equalsIgnoreCase(codProcExpte)
                    || "LAK".equalsIgnoreCase(codProcExpte)    ){
                String codCampoSuple=ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.FSE_CODIGO_CAMPOSUPLE_RESULTADO + codProcExpte, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                resultado=interopManager.getResultadoParametroWSFSE_DSD_CSE_DESPLEGABLE(codProcExpte,numExpediente,codCampoSuple,adapt);
            }
        } catch (Exception e) {
            log.error("Error al recuperar parametro resultado expediente para WS FSE", e);
            resultado = "";
        }
        log.error("getResultadoParametroWSFSE - End() - resultado = " + resultado);
        return resultado;
    }

    public String cargarPantallaPrincipalServiciosNISAE(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        log.info("cargarPantallaPrincipalServiciosNISAE - Begin()" + new Date().toString());
        String respuestaServicio = "/jsp/extension/melanbide_interop/nisae/integracionNisaeService.jsp";
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        int idiomaUsuario = 1;
        try {
            if (request.getParameter("idioma") != null && !request.getParameter("idioma").isEmpty()) {
                try {
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                } catch (Exception ex) {
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }

            List<ComboNisae> listaProcedimiento = gestionServiciosNISAE.getComboNisaeProcedimiento(codOrganizacion, adapt);
            List<ComboNisae> listaWebServices = gestionServiciosNISAE.getComboServiciosWebDisponibles(codOrganizacion, adapt);
            request.setAttribute("listaProcedimiento",listaProcedimiento);
            request.setAttribute("listaWebServices",listaWebServices);

        } catch (Exception ex) {
            log.error("Se ha producido un error a cargar la pantalla principal Servicios NISAE " + numExpediente, ex);
            respuestaServicio = "/jsp/extension/melanbide_interop/nisae/integracionNisaeServiceError.jsp";
            request.setAttribute("mensajeInicial", "Se ha presentado un error al cargar la pantalla de los servcios NISAE ...Contacte con el ADMIN para mas detalles. " + ex.getMessage());
        }
        log.info("cargarPantallaPrincipalServiciosNISAE - End()" + new Date().toString() + " " + respuestaServicio);
        return respuestaServicio;
    }

    public String cargarPantallaLogServiciosNISAE(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        log.info("cargarPantallaLogServiciosNISAE - Begin()" + new Date().toString());
        String respuestaServicio = "/jsp/extension/melanbide_interop/nisae/resultadoNisaeService.jsp";
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        int idiomaUsuario = 1;
        try{
            if(request.getParameter("idioma") != null && !request.getParameter("idioma").isEmpty()){
                try{
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                }catch(Exception ex){
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }
            List<ComboNisae> listaProcedimiento = gestionServiciosNISAE.getComboNisaeProcedimiento(codOrganizacion, adapt);
            List<ComboNisae> listaWebServices = gestionServiciosNISAE.getComboServiciosWebDisponibles(codOrganizacion, adapt);
            request.setAttribute("listaProcedimiento", listaProcedimiento);
            request.setAttribute("listaWebServices",listaWebServices);

        }catch (Exception ex) {
            log.error("Se ha producido un error a cargar la pantalla de log de servicios NISAE " + numExpediente, ex);
            respuestaServicio = "/jsp/extension/melanbide_interop/nisae/integracionNisaeServiceError.jsp";
            request.setAttribute("mensajeInicial", "Se ha presentado un error al cargar la pantalla de los servcios NISAE ...Contacte con el ADMIN para mas detalles. " + ex.getMessage());
        }
        log.info("cargarPantallaLogServiciosNISAE - End()" + new Date().toString());
        return respuestaServicio;
    }

    public void cargarPantallaLogServiciosNISAEFiltros(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        log.info("cargarPantallaLogServiciosNISAEFiltros - Begin()" + new Date().toString());
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        RetornoResultadoNISAELog retorno = null;
        int idiomaUsuario = 1;
        int startTable = 0;
        int finishTable = 0;
        int numEntries = 0;
        int draw = 0;
        String ejercicioHHFF = "";
        String procedimientoHHFF = "";
        String webservices = "";
        String estadoExpediente = "";
        String numeroExpedienteDesde = "";
        String numeroExpedienteHasta = "";
        String fechaEnvioPeticion = "";
        String estado = "";
        String resultado = "";
        String documentoInteresado = "";
        String fechaDesdeCVL = "";
        String fechaHastaCVL = "";

        try {
            if (request.getParameter("idioma") != null && !request.getParameter("idioma").isEmpty()) {
                try {
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                } catch (Exception ex) {
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }
            //Recoger los parametros
            startTable = Integer.parseInt(request.getParameter("start"));
            finishTable = startTable + Integer.parseInt(request.getParameter("length"));
            numEntries = Integer.parseInt(request.getParameter("length"));
            draw = Integer.parseInt(request.getParameter("draw"));
            ejercicioHHFF = (String) request.getParameter("ejercicioHHFF");
            procedimientoHHFF = (String) request.getParameter("procedimientoHHFF");
            webservices = (String) request.getParameter("webservices");
            estadoExpediente = (String) request.getParameter("estadoExpediente");
            numeroExpedienteDesde = (String) request.getParameter("numeroExpedienteDesde");
            numeroExpedienteHasta = (String) request.getParameter("numeroExpedienteHasta");
            fechaEnvioPeticion = (String) request.getParameter("fechaEnvioPeticion");
            estado = (String) request.getParameter("estado");
            resultado = (String) request.getParameter("resultado");
            documentoInteresado = (String) request.getParameter("documentoInteresado");
            fechaDesdeCVL = (String) request.getParameter("fechaDesdeCVL");
            fechaHastaCVL = (String) request.getParameter("fechaHastaCVL");

            FiltrosNisaeLogVO filtros = new FiltrosNisaeLogVO(startTable, finishTable, numEntries, draw,
                    ejercicioHHFF, procedimientoHHFF, webservices, estadoExpediente, numeroExpedienteDesde,
                    numeroExpedienteHasta, fechaEnvioPeticion, estado, resultado, documentoInteresado, fechaDesdeCVL, fechaHastaCVL);

            //cargar lista de resultados del log
            retorno = gestionServiciosNISAE.getLogInteropServiciosNISAE(codOrganizacion, adapt, filtros);

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.serializeNulls();
            Gson gson = gsonBuilder.create();
            String sResponse = gson.toJson(retorno);
            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print(sResponse);
                out.flush();
                out.close();
            } catch (Exception e) {
                log.error("Error preparando respuesta " + e.getMessage(), e);
                e.printStackTrace();
            }

            log.info("Retorno size: " + retorno.getLstRegistros().size());

        } catch (Exception ex) {
            log.error("Se ha producido un error a cargar la pantalla de log de servicios NISAE " + numExpediente, ex);
            retorno = null;
            request.setAttribute("mensajeInicial", "Se ha presentado un error al cargar la pantalla de los servcios NISAE ...Contacte con el ADMIN para mas detalles. " + ex.getMessage());
        }
        log.info("cargarPantallaLogServiciosNISAEFiltros - End()" + new Date().toString());

    }
    /**
     * Exporta la tabla log a un fichero excel
     * @param codOrganizacion
     * @param codTramite
     * @param ocurrenciaTramite
     * @param numExpediente
     * @param request
     * @param response
     */
    public void exportarLogServiciosNISAEFiltros(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response){
        log.info("exportarLogServiciosNISAEFiltros - Begin() " + new Date().toString());

        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        List<InteropLlamadasServiciosNisae> lstDatos = null;
        int idiomaUsuario = 1;
        String ejercicioHHFF = "";
        String procedimientoHHFF = "";
        String webservices = "";
        String estadoExpediente = "";
        String numeroExpedienteDesde = "";
        String numeroExpedienteHasta = "";
        String fechaEnvioPeticion = "";
        String estado = "";
        String documentoInteresado = "";
        String fechaDesdeCVL = "";
        String fechaHastaCVL = "";
        String resultado = "";
        HSSFWorkbook wb = null;

        try{
            if(request.getParameter("idioma") != null){
                try{
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                }catch (Exception ex){
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }

            //Recoger los parametros
            ejercicioHHFF = (String) request.getParameter("ejercicioHHFF");
            log.info("Ejercicio : " + ejercicioHHFF);
            procedimientoHHFF = (String) request.getParameter("procedimientoHHFF");
            webservices = (String) request.getParameter("webservices");
            estadoExpediente = (String) request.getParameter("estadoExpediente");
            numeroExpedienteDesde = (String) request.getParameter("numeroExpedienteDesde");
            numeroExpedienteHasta = (String) request.getParameter("numeroExpedienteHasta");
            fechaEnvioPeticion = (String) request.getParameter("fechaEnvioPeticion");
            estado = (String) request.getParameter("estado");
            resultado = (String) request.getParameter("resultado");
            documentoInteresado = (String) request.getParameter("documentoInteresado");
            fechaDesdeCVL = (String) request.getParameter("fechaDesdeCVL");
            fechaHastaCVL = (String) request.getParameter("fechaHastaCVL");

            FiltrosNisaeLogVO filtros = new FiltrosNisaeLogVO(ejercicioHHFF,
                    procedimientoHHFF, webservices, estadoExpediente, numeroExpedienteDesde,
                    numeroExpedienteHasta, fechaEnvioPeticion, estado, resultado, documentoInteresado, fechaDesdeCVL, fechaHastaCVL);

            //cargar lista de resultados del log
            lstDatos = gestionServiciosNISAE.getListaLogInteropServiciosNISAE(codOrganizacion, adapt, filtros);
            wb = MeLanbideInteropManager.getInstance().descargarExcelLogNisae(lstDatos);
            String fechaActual = new SimpleDateFormat("ddMMyyyy").format(new Date());
            String filename = "LogResultadosNISAE " + fechaActual + ".xls";

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);

            OutputStream out = response.getOutputStream();
            wb.write(out);

        } catch (Exception ex) {
            log.error("Se ha producido un error al exportar la pantalla de log de servicios NISAE " + numExpediente, ex);
            lstDatos = null;
            request.setAttribute("mensajeInicial", "Se ha presentado un error al exportar la pantalla de los servcios NISAE... Contacte con el ADMIN para mas detalles." + ex.getMessage());
        }
        log.info("exportarLogServiciosNISAEFiltros - End() " + new Date().toString());
    }
    public void estasCorrienteHaciendaForalHHFFBatch(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response)
    {
        log.info("estasCorrienteHaciendaForalHHFFBatch - Begin()" + new Date().toString());
        String respuestaServicio = "Respuesta del Servicio....";
        List<TerceroVO> lista = new ArrayList<TerceroVO>();
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        int idiomaUsuario = 1;
        String idProcoFlexia_LAN6="";  //String idPeticionPrevia="",numExpedientePeticion="";
        String ejercicioHHFF = "";
        String procedimientoHHFF = "";
        String estadoExpediente = "";
        String numeroExpedienteDesde = "";
        String numeroExpedienteHasta = "";
        String soloPeticionesEstEnProceso = "";
        Integer fkWSSolicitado = 0;
        int cantidadExpedientesProcesar =0;
        String ejecutarFiltroExpedientesEspecificos="";
        try
        {
            if (request.getParameter("idioma") != null) {
                try {
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                } catch (Exception ex) {
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }
            //Recojo los parametros
            ejercicioHHFF = (String)request.getParameter("ejercicioHHFF");
            procedimientoHHFF = (String)request.getParameter("procedimientoHHFF");
            estadoExpediente = (String)request.getParameter("estadoexpediente");
            numeroExpedienteDesde = (String)request.getParameter("numeroExpedienteDesde");
            numeroExpedienteHasta = (String)request.getParameter("numeroExpedienteHasta");
            soloPeticionesEstEnProceso = (String)request.getParameter("soloPeticionesEstEnProceso");
            fkWSSolicitado = ((String)request.getParameter("fkWSSolicitado") != null && !((String)request.getParameter("fkWSSolicitado")).isEmpty() ? Integer.valueOf((String)request.getParameter("fkWSSolicitado")):0);
            ejecutarFiltroExpedientesEspecificos=(String)request.getParameter("ejecutarFiltroExpedientesEspecificos");
            log.info("parametrosRecibidos : " + ejercicioHHFF +" "+procedimientoHHFF+" "+estadoExpediente
                    + " " + numeroExpedienteDesde + " " + numeroExpedienteHasta + " " +fkWSSolicitado + " " + ejecutarFiltroExpedientesEspecificos);
            idProcoFlexia_LAN6 = gestionAdaptadoresLan6Config.getCodProcedimientoPlatea(procedimientoHHFF);
            log.info("idProcoFlexia_LAN6 : "+idProcoFlexia_LAN6);
            InteropLlamadasServiciosNisae interopLlamadasServiciosNisae = new InteropLlamadasServiciosNisae();
            interopLlamadasServiciosNisae.setCodOrganizacion(codOrganizacion);
            interopLlamadasServiciosNisae.setEjercicioHHFF(ejercicioHHFF);
            interopLlamadasServiciosNisae.setProcedimientoHHFF(procedimientoHHFF);
            interopLlamadasServiciosNisae.setEstadoExpediente(estadoExpediente);
            interopLlamadasServiciosNisae.setNumeroExpedienteDesde(numeroExpedienteDesde);
            interopLlamadasServiciosNisae.setNumeroExpedienteHasta(numeroExpedienteHasta);
            interopLlamadasServiciosNisae.setSoloPeticionesEstEnProceso(soloPeticionesEstEnProceso);
            interopLlamadasServiciosNisae.setFkWSSolicitado(fkWSSolicitado);
            interopLlamadasServiciosNisae.setEjecutarFiltroExpedientesEspecificos(ejecutarFiltroExpedientesEspecificos!=null && !ejecutarFiltroExpedientesEspecificos.isEmpty() ? ejecutarFiltroExpedientesEspecificos : "0");

            // Recogemos expedientes
            log.info("-- Leer Expedientes");
            cantidadExpedientesProcesar = gestionServiciosNISAE.getExpedientesProcesarNISAENumeroTotalProcesar(codOrganizacion, interopLlamadasServiciosNisae, adapt);

            // Inciamos proceso segundo plano
            log.info("Llamar  THREAD GestionServiciosNISAEEjecucionHHFF");
            GestionServiciosNISAEEjecucionHHFF ejecutaThread = new GestionServiciosNISAEEjecucionHHFF();
            ejecutaThread.start(codOrganizacion,interopLlamadasServiciosNisae,idProcoFlexia_LAN6,adapt);
            log.info("Proceso THREAD GestionServiciosNISAEEjecucionHHFF Lanzado Correctamente..");
            respuestaServicio="Proceso segundo plano Lanzado Correctamente. ";
        } catch (Exception ex) {
            log.error("Se ha producido una al procesar llamada al WS " + numExpediente, ex);
            String mensajeExcepcion = "";
            respuestaServicio="Error al invocar el proceso en segundo plano : " + ex.getMessage();
            try {
                log.info("Vamos a registrar el error en BD : " + ex.getMessage());
                String causaExcepcion = (ex.getCause() != null ? (ex.getCause().getMessage() != null ? ex.getCause().getMessage() + " - " + ex.getCause().toString() : ex.getCause().toString()) : "");
                mensajeExcepcion = ex.getMessage();
                String trazaError = ExceptionUtils.getStackTrace(ex);  // ExceptionUtils.getStackTrace(ex);

                Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                        trazaError, ConstantesMeLanbideInterop.ERROR_NISAE_SISTEMA_ORIGEN_FLEXIA,
                        ConstantesMeLanbideInterop.ERROR_NISAE_CODIGO_ERROR_HHFF, ConstantesMeLanbideInterop.ERROR_NISAE_MENSAJE_HHFF, 1);
                ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, ex);
                int idError=GestionarErroresDokusiNISAE.grabarError(errorLan6Bean,numeroExpedienteDesde+"-"+numeroExpedienteHasta, idProcoFlexia_LAN6, ejercicioHHFF+"-"+procedimientoHHFF,"estasCorrienteHaciendaForalHHFFBatch");
                log.info("Error Registrado en BD correctamente. " + idError);
                respuestaServicio += " - Mas detalles en el Gestor Errores : ID:"+idError;
            } catch (Exception ex1) {
                log.error("Dokusi.Flexia Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : " + ex.getMessage(), ex1);
                respuestaServicio += " - Error al intentar registrar y notificar un error en la ejecucion del proceso : " + ex1.getMessage();
                //throw ex1;
            }
        }

        respuestaServicio += " - Peticion procesada con los parametros: "
                + " Ejercicio : "+(ejercicioHHFF!=null && !ejercicioHHFF.isEmpty() ? ejercicioHHFF : "")
                + " Procedimiento : "+(procedimientoHHFF!= null && !procedimientoHHFF.isEmpty() ? procedimientoHHFF : "")
                + " Estado Expedientes : "+(estadoExpediente != null && !estadoExpediente.isEmpty()? estadoExpediente :"")
                + " Numero Expediente Desde : "+(numeroExpedienteDesde != null && !numeroExpedienteDesde.isEmpty() ? numeroExpedienteDesde : "")
                + " Numero Expediente Hasta : "+(numeroExpedienteHasta != null && !numeroExpedienteHasta.isEmpty() ? numeroExpedienteHasta : "")
                + " Expedientes a Procesar : "+cantidadExpedientesProcesar ;
        ;
        log.info("respuestaServicio : " + respuestaServicio);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        String presupuestoJsonString = gson.toJson(respuestaServicio);
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(presupuestoJsonString);
            out.flush();
            out.close();
        }catch(Exception e){
            log.error("Error preparando respuesta " + e.getMessage(),e);
            e.printStackTrace();
        }
        /*
        StringBuffer xmlSalida = new StringBuffer();
        xmlSalida.append("<RESPUESTA>");
            xmlSalida.append("<CODIGO_OPERACION>");
                xmlSalida.append(respuestaServicio);
            xmlSalida.append("</CODIGO_OPERACION>");
        xmlSalida.append("</RESPUESTA>");
        log.info("XML Retornado a jsp: " + xmlSalida.toString());
        try{
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(xmlSalida.toString());
            out.flush();
            out.close();
        }catch(Exception e){
            log.error("Error preparando respuesta " + e.getMessage(),e);
            e.printStackTrace();
        }//try-catch
        */
        log.info("estasCorrienteHaciendaForalHHFFBatch - End()" + new Date().toString());
    }

    public void estasCorrienteSeguridadSocialTGSSBatch(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        log.info("estasCorrienteSeguridadSocialTGSSBatch - Begin()" + new Date().toString());
        String respuestaServicio = "Respuesta del Servicio....";
        List<TerceroVO> lista = new ArrayList<TerceroVO>();
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        int idiomaUsuario = 1;
        String idProcoFlexia_LAN6 = "";  //String idPeticionPrevia="",numExpedientePeticion="";
        String ejercicioHHFF = "";
        String procedimientoHHFF = "";
        String estadoExpediente = "";
        String numeroExpedienteDesde = "";
        String numeroExpedienteHasta = "";
        String soloPeticionesEstEnProceso = "";
        Integer fkWSSolicitado = 0;
        int cantidadExpedientesProcesar =0;
        String ejecutarFiltroExpedientesEspecificos="";
        try {
            if (request.getParameter("idioma") != null) {
                try {
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                } catch (Exception ex) {
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }
            //Recojo los parametros
            ejercicioHHFF = (String) request.getParameter("ejercicioHHFF");
            procedimientoHHFF = (String) request.getParameter("procedimientoHHFF");
            estadoExpediente = (String) request.getParameter("estadoexpediente");
            numeroExpedienteDesde = (String) request.getParameter("numeroExpedienteDesde");
            numeroExpedienteHasta = (String) request.getParameter("numeroExpedienteHasta");
            soloPeticionesEstEnProceso = (String) request.getParameter("soloPeticionesEstEnProceso");
            fkWSSolicitado = ((String) request.getParameter("fkWSSolicitado") != null && !((String) request.getParameter("fkWSSolicitado")).isEmpty() ? Integer.valueOf((String) request.getParameter("fkWSSolicitado")) : 0);
            ejecutarFiltroExpedientesEspecificos=(String)request.getParameter("ejecutarFiltroExpedientesEspecificos");
            log.info("parametrosRecibidos : " + ejercicioHHFF + " " + procedimientoHHFF + " " + estadoExpediente
                    + " " + numeroExpedienteDesde + " " + numeroExpedienteHasta + " " + fkWSSolicitado + " " + ejecutarFiltroExpedientesEspecificos);
            idProcoFlexia_LAN6 = gestionAdaptadoresLan6Config.getCodProcedimientoPlatea(procedimientoHHFF);
            log.info("idProcoFlexia_LAN6 : " + idProcoFlexia_LAN6);
            InteropLlamadasServiciosNisae interopLlamadasServiciosNisae = new InteropLlamadasServiciosNisae();
            interopLlamadasServiciosNisae.setCodOrganizacion(codOrganizacion);
            interopLlamadasServiciosNisae.setEjercicioHHFF(ejercicioHHFF);
            interopLlamadasServiciosNisae.setProcedimientoHHFF(procedimientoHHFF);
            interopLlamadasServiciosNisae.setEstadoExpediente(estadoExpediente);
            interopLlamadasServiciosNisae.setNumeroExpedienteDesde(numeroExpedienteDesde);
            interopLlamadasServiciosNisae.setNumeroExpedienteHasta(numeroExpedienteHasta);
            interopLlamadasServiciosNisae.setSoloPeticionesEstEnProceso(soloPeticionesEstEnProceso);
            interopLlamadasServiciosNisae.setFkWSSolicitado(fkWSSolicitado);
            interopLlamadasServiciosNisae.setEjecutarFiltroExpedientesEspecificos(ejecutarFiltroExpedientesEspecificos!=null && !ejecutarFiltroExpedientesEspecificos.isEmpty() ? ejecutarFiltroExpedientesEspecificos : "0");

            // Recogemos expedientes
            log.info("-- Leer Expedientes");
            cantidadExpedientesProcesar = gestionServiciosNISAE.getExpedientesProcesarNISAENumeroTotalProcesar(codOrganizacion, interopLlamadasServiciosNisae, adapt);
//            if ("1".equalsIgnoreCase(interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos())) {
//                cantidadExpedientesProcesar = gestionServiciosNISAE.getExpedientesProcesarNISAEFiltroExpedientesEspecificosTotalProcesar(codOrganizacion, interopLlamadasServiciosNisae, adapt);
//            } else {
//                cantidadExpedientesProcesar = gestionServiciosNISAE.getExpedientesProcesarNISAENumeroTotalProcesar(codOrganizacion, interopLlamadasServiciosNisae, adapt);
//            }

            // Inciamos proceso segundo plano
            log.info("Llamar  THREAD estasCorrienteSeguridadSocialTGSSBatch");
            GestionServiciosNISAEEjecucionTGSS ejecutaThread = new GestionServiciosNISAEEjecucionTGSS();
            ejecutaThread.start(codOrganizacion, interopLlamadasServiciosNisae, idProcoFlexia_LAN6,adapt);
            log.info("Proceso THREAD estasCorrienteSeguridadSocialTGSSBatch Lanzado Correctamente..");
            respuestaServicio = "Proceso segundo plano Lanzado Correctamente. ";
        } catch (Exception ex) {
            log.error("Se ha producido una al procesar llamada al WS " + numExpediente, ex);
            String mensajeExcepcion = "";
            respuestaServicio = "Error al invocar el proceso en segundo plano : " + ex.getMessage();
            try {
                log.info("Vamos a registrar el error en BD : " + ex.getMessage());
                String causaExcepcion = (ex.getCause() != null ? (ex.getCause().getMessage() != null ? ex.getCause().getMessage() + " - " + ex.getCause().toString() : ex.getCause().toString()) : "");
                mensajeExcepcion = ex.getMessage();
                String trazaError = ExceptionUtils.getStackTrace(ex);  // ExceptionUtils.getStackTrace(ex);

                Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                        trazaError, ConstantesMeLanbideInterop.ERROR_NISAE_SISTEMA_ORIGEN_FLEXIA,
                        ConstantesMeLanbideInterop.ERROR_NISAE_CODIGO_ERROR_HHFF, ConstantesMeLanbideInterop.ERROR_NISAE_MENSAJE_HHFF, 1);
                ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, ex);
                int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, numeroExpedienteDesde + "-" + numeroExpedienteHasta, idProcoFlexia_LAN6, ejercicioHHFF + "-" + procedimientoHHFF, "estasCorrienteHaciendaForalHHFFBatch");
                log.info("Error Registrado en BD correctamente. " + idError);
                respuestaServicio += " - Mas detalles en el Gestor Errores : ID:" + idError;
            } catch (Exception ex1) {
                log.error("Dokusi.Flexia Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : " + ex.getMessage(), ex1);
                respuestaServicio += " - Error al intentar registrar y notificar un error en la ejecucion del proceso : " + ex1.getMessage();
                //throw ex1;
            }
        }

        respuestaServicio += " - Peticion procesada con los parametros: "
                + " Ejercicio : " + (ejercicioHHFF != null && !ejercicioHHFF.isEmpty() ? ejercicioHHFF : "")
                + " Procedimiento : " + (procedimientoHHFF != null && !procedimientoHHFF.isEmpty() ? procedimientoHHFF : "")
                + " Estado Expedientes : " + (estadoExpediente != null && !estadoExpediente.isEmpty() ? estadoExpediente : "")
                + " Numero Expediente Desde : " + (numeroExpedienteDesde != null && !numeroExpedienteDesde.isEmpty() ? numeroExpedienteDesde : "")
                + " Numero Expediente Hasta : " + (numeroExpedienteHasta != null && !numeroExpedienteHasta.isEmpty() ? numeroExpedienteHasta : "")
                + " Expedientes a Procesar : " + cantidadExpedientesProcesar;
        log.info("respuestaServicio : " + respuestaServicio);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        String presupuestoJsonString = gson.toJson(respuestaServicio);
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(presupuestoJsonString);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("Error preparando respuesta " + e.getMessage(), e);
            e.printStackTrace();
        }
        log.info("estasCorrienteSeguridadSocialTGSSBatch - End()" + new Date().toString());
    }

    public void consultaVidaLaboralCVLBatch(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response){
        log.info("consultaVidaLaboralCVLBatch - Begin()" + new Date().toString());
        String respuestaServicio = "";
        List<ExpedienteNisaeVO> lstExpedientes = new ArrayList<ExpedienteNisaeVO>();
        int idiomaUsuario = 1;
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        String idProcoFlexia_LAN6 = "";
        String ejercicioHHFF = "";
        String procedimientoAERTE = "" ;
        String estadoExpediente= "";
        String numeroExpedienteDesde = "";
        String numeroExpedienteHasta = "";
        String soloPeticionesEstEnProceso = "";
        String fechaDesdeCVL = "";
        String fechaHastaCVL = "";
        Integer fkWSSolicitado = 0;
        int cantidadExpedientesProcesar=0;
        String ejecutarFiltroExpedientesEspecificos ="";
        try{
            if(request.getParameter("idioma") != null){
                try{
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                }catch(Exception ex){
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }
            //Recojo los parametros
            ejercicioHHFF = (String) request.getParameter("ejercicioHHFF");
            procedimientoAERTE = (String) request.getParameter("procedimientoHHFF");
            estadoExpediente = (String) request.getParameter("estadoexpediente");
            numeroExpedienteDesde = (String) request.getParameter("numeroExpedienteDesde");
            numeroExpedienteHasta = (String) request.getParameter("numeroExpedienteHasta");
            soloPeticionesEstEnProceso = (String) request.getParameter("soloPeticionesEstEnProceso");
            fechaDesdeCVL = (String) request.getParameter("fechaDesdeCVL");
            fechaHastaCVL = (String) request.getParameter("fechaHastaCVL");
            fkWSSolicitado = ((String) request.getParameter("fkWSSolicitado") != null && !((String) request.getParameter("fkWSSolicitado")).isEmpty() ? Integer.valueOf((String) request.getParameter("fkWSSolicitado")) : 0);
            ejecutarFiltroExpedientesEspecificos=(String)request.getParameter("ejecutarFiltroExpedientesEspecificos");
            log.info("parametrosRecibidos : " + ejercicioHHFF + " " + procedimientoAERTE + " " + estadoExpediente
                    + " " + numeroExpedienteDesde + " " + numeroExpedienteHasta + " " + fkWSSolicitado + " " + ejecutarFiltroExpedientesEspecificos);

            idProcoFlexia_LAN6 = gestionAdaptadoresLan6Config.getCodProcedimientoPlatea(procedimientoAERTE);
            log.info("idProcoFlexia_LAN6 : " + idProcoFlexia_LAN6);

            InteropLlamadasServiciosNisae interopLlamadasServiciosNisae = new InteropLlamadasServiciosNisae();
            interopLlamadasServiciosNisae.setCodOrganizacion(codOrganizacion);
            interopLlamadasServiciosNisae.setEjercicioHHFF(ejercicioHHFF);
            interopLlamadasServiciosNisae.setProcedimientoHHFF(procedimientoAERTE);
            interopLlamadasServiciosNisae.setEstadoExpediente(estadoExpediente);
            interopLlamadasServiciosNisae.setNumeroExpedienteDesde(numeroExpedienteDesde);
            interopLlamadasServiciosNisae.setNumeroExpedienteHasta(numeroExpedienteHasta);
            interopLlamadasServiciosNisae.setSoloPeticionesEstEnProceso(soloPeticionesEstEnProceso);
            interopLlamadasServiciosNisae.setFkWSSolicitado(fkWSSolicitado);
            interopLlamadasServiciosNisae.setFechaDesdeCVL(fechaDesdeCVL);
            interopLlamadasServiciosNisae.setFechaHastaCVL(fechaHastaCVL);
            interopLlamadasServiciosNisae.setEjecutarFiltroExpedientesEspecificos(ejecutarFiltroExpedientesEspecificos!=null && !ejecutarFiltroExpedientesEspecificos.isEmpty() ? ejecutarFiltroExpedientesEspecificos : "0");

            //Numero de Expedientes a procesar
            cantidadExpedientesProcesar = gestionServiciosNISAE.getExpedientesProcesarNISAENumeroTotalProcesar(codOrganizacion, interopLlamadasServiciosNisae, adapt);
            // Inciamos proceso segundo plano
            log.info("Llamar  THREAD estasCorrientePadronBatch");
            GestionServiciosNISAECVL ejecutaThread = new GestionServiciosNISAECVL();
            ejecutaThread.start(codOrganizacion, interopLlamadasServiciosNisae, idProcoFlexia_LAN6, adapt);
            log.info("Proceso THREAD estasCorrienteSeguridadSocialTGSSBatch Lanzado Correctamente..");
            // Mensaje para el Alert
            String respuestaDetalle = "Peticion procesada con los parametros: "
                    + " Ejercicio : " + (ejercicioHHFF != null && !ejercicioHHFF.isEmpty() ? ejercicioHHFF : "")
                    + " Procedimiento : " + (procedimientoAERTE != null && !procedimientoAERTE.isEmpty() ? procedimientoAERTE : "")
                    + " Estado Expedientes : " + (estadoExpediente != null && !estadoExpediente.isEmpty() ? estadoExpediente : "")
                    + " Numero Expediente Desde : " + (numeroExpedienteDesde != null && !numeroExpedienteDesde.isEmpty() ? numeroExpedienteDesde : "")
                    + " Numero Expediente Hasta : " + (numeroExpedienteHasta != null && !numeroExpedienteHasta.isEmpty() ? numeroExpedienteHasta : "")
                    + " Fecha Desde : " + (fechaDesdeCVL != null && !fechaDesdeCVL.isEmpty() ? fechaDesdeCVL : "")
                    + " Fecha Hasta : " + (fechaHastaCVL != null && !fechaHastaCVL.isEmpty() ? fechaHastaCVL : "")
                    + " Expedientes a Procesar : " + cantidadExpedientesProcesar;
            respuestaServicio += respuestaDetalle;
        }catch(Exception ex){
            log.error("WS_verificarDatosPadron - Exception general al llamar al WS de datos del padron. "
                    + numExpediente  + ex.getMessage()
            );
            if(respuestaServicio==null || respuestaServicio.isEmpty())
                respuestaServicio = "-2 :  Error general al acceder al Servicio. Consulte al equipo de soporte para mas detalles.";
        }

        log.error("respuestaServicio : " + respuestaServicio);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        String presupuestoJsonString = gson.toJson(respuestaServicio);
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(presupuestoJsonString);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("Error preparando respuesta " + e.getMessage(), e);
            e.printStackTrace();
        }

        log.info("consultaVidaLaboralCVLBatch - End()" + new Date().toString());
    }

    public void estasCorrientePadronBatch(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response){
        log.info("estasCorrientePadronBatch - Begin()" + new Date().toString());
        String respuestaServicio = "";
        List<ExpedienteNisaeVO> lstExpedientes = new ArrayList<ExpedienteNisaeVO>();
        int idiomaUsuario = 1;
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        String idProcoFlexia_LAN6 = "";
        String ejercicioHHFF = "";
        String procedimientoAERTE = "" ;
        String estadoExpediente= "";
        String numeroExpedienteDesde = "";
        String numeroExpedienteHasta = "";
        String soloPeticionesEstEnProceso = "";
        Integer fkWSSolicitado = 0;
        int cantidadExpedientesProcesar=0;
        String ejecutarFiltroExpedientesEspecificos ="";
        try{
            if(request.getParameter("idioma") != null){
                try{
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                }catch(Exception ex){
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }
            //Recojo los parametros
            ejercicioHHFF = (String) request.getParameter("ejercicioHHFF");
            procedimientoAERTE = (String) request.getParameter("procedimientoHHFF");
            estadoExpediente = (String) request.getParameter("estadoexpediente");
            numeroExpedienteDesde = (String) request.getParameter("numeroExpedienteDesde");
            numeroExpedienteHasta = (String) request.getParameter("numeroExpedienteHasta");
            soloPeticionesEstEnProceso = (String) request.getParameter("soloPeticionesEstEnProceso");
            fkWSSolicitado = ((String) request.getParameter("fkWSSolicitado") != null && !((String) request.getParameter("fkWSSolicitado")).isEmpty() ? Integer.valueOf((String) request.getParameter("fkWSSolicitado")) : 0);
            ejecutarFiltroExpedientesEspecificos=(String)request.getParameter("ejecutarFiltroExpedientesEspecificos");
            log.info("parametrosRecibidos : " + ejercicioHHFF + " " + procedimientoAERTE + " " + estadoExpediente
                    + " " + numeroExpedienteDesde + " " + numeroExpedienteHasta + " " + fkWSSolicitado + " " + ejecutarFiltroExpedientesEspecificos);

            idProcoFlexia_LAN6 = gestionAdaptadoresLan6Config.getCodProcedimientoPlatea(procedimientoAERTE);
            log.info("idProcoFlexia_LAN6 : " + idProcoFlexia_LAN6);

            InteropLlamadasServiciosNisae interopLlamadasServiciosNisae = new InteropLlamadasServiciosNisae();
            interopLlamadasServiciosNisae.setCodOrganizacion(codOrganizacion);
            interopLlamadasServiciosNisae.setEjercicioHHFF(ejercicioHHFF);
            interopLlamadasServiciosNisae.setProcedimientoHHFF(procedimientoAERTE);
            interopLlamadasServiciosNisae.setEstadoExpediente(estadoExpediente);
            interopLlamadasServiciosNisae.setNumeroExpedienteDesde(numeroExpedienteDesde);
            interopLlamadasServiciosNisae.setNumeroExpedienteHasta(numeroExpedienteHasta);
            interopLlamadasServiciosNisae.setSoloPeticionesEstEnProceso(soloPeticionesEstEnProceso);
            interopLlamadasServiciosNisae.setFkWSSolicitado(fkWSSolicitado);
            interopLlamadasServiciosNisae.setEjecutarFiltroExpedientesEspecificos(ejecutarFiltroExpedientesEspecificos!=null && !ejecutarFiltroExpedientesEspecificos.isEmpty() ? ejecutarFiltroExpedientesEspecificos : "0");

            //Numero de Expedientes a procesar
            cantidadExpedientesProcesar = gestionServiciosNISAE.getExpedientesProcesarNISAENumeroTotalProcesar(codOrganizacion, interopLlamadasServiciosNisae, adapt);
            // Inciamos proceso segundo plano
            log.info("Llamar  THREAD estasCorrientePadronBatch");
            GestionServiciosNISAEEjecucionConsPadron ejecutaThread = new GestionServiciosNISAEEjecucionConsPadron();
            ejecutaThread.start(codOrganizacion, interopLlamadasServiciosNisae, idProcoFlexia_LAN6, adapt);
            log.info("Proceso THREAD estasCorrienteSeguridadSocialTGSSBatch Lanzado Correctamente..");
            // Mensaje para el Alert
            String respuestaDetalle = "Peticion procesada con los parametros: "
                    + " Ejercicio : " + (ejercicioHHFF != null && !ejercicioHHFF.isEmpty() ? ejercicioHHFF : "")
                    + " Procedimiento : " + (procedimientoAERTE != null && !procedimientoAERTE.isEmpty() ? procedimientoAERTE : "")
                    + " Estado Expedientes : " + (estadoExpediente != null && !estadoExpediente.isEmpty() ? estadoExpediente : "")
                    + " Numero Expediente Desde : " + (numeroExpedienteDesde != null && !numeroExpedienteDesde.isEmpty() ? numeroExpedienteDesde : "")
                    + " Numero Expediente Hasta : " + (numeroExpedienteHasta != null && !numeroExpedienteHasta.isEmpty() ? numeroExpedienteHasta : "")
                    + " Expedientes a Procesar : " + cantidadExpedientesProcesar;
            respuestaServicio += respuestaDetalle;
        }catch(Exception ex){
            log.error("WS_verificarDatosPadron - Exception general al llamar al WS de datos del padron. "
                    + numExpediente  + ex.getMessage()
            );
            if(respuestaServicio==null || respuestaServicio.isEmpty())
                respuestaServicio = "-2 :  Error general al acceder al Servicio. Consulte al equipo de soporte para mas detalles.";
        }

        log.error("respuestaServicio : " + respuestaServicio);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        String presupuestoJsonString = gson.toJson(respuestaServicio);
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(presupuestoJsonString);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("Error preparando respuesta " + e.getMessage(), e);
            e.printStackTrace();
        }

        log.info("consultaVidaLaboralCVLBatch - End()" + new Date().toString());
    }

    public void cargarListaFiltroExpedientesEspecificos(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        log.info("cargarListaFiltroExpedientesEspecificos - Begin()" + new Date().toString());
        String respuestaServicio = "Respuesta del Servicio....";
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        String jsonListaExpedientes = "";
        int jsonListaExpedientesCount = 0;
        try {
            //Recojo los parametros
            jsonListaExpedientes = (String) request.getParameter("jsonListaExpedientes");
            log.info("jsonListaExpedientes : " + jsonListaExpedientes);
            if (jsonListaExpedientes != null && !jsonListaExpedientes.isEmpty()) {
                String[] listaExptes = jsonListaExpedientes.split(";");
                List<InteropServiciosNisaeExpFi> listaGuardar = new ArrayList<InteropServiciosNisaeExpFi>();
                if (listaExptes != null && listaExptes.length > 0) {
                    for (int i = 0; i < listaExptes.length; i++) {
                        InteropServiciosNisaeExpFi objeto = new InteropServiciosNisaeExpFi();
                        objeto.setCodOrganizacion(codOrganizacion);
                        objeto.setNumeroExpediente(listaExptes[i]);
                        listaGuardar.add(objeto);
                    }
                    jsonListaExpedientesCount = listaGuardar.size();
                    gestionServiciosNISAE.registarFiltroExpedienteEspcificoLista(codOrganizacion, listaGuardar, adapt);
                }
            } else {
                respuestaServicio = "Lista de expedientes a insertar para el filtro recibida vacia.";
            }
            respuestaServicio += jsonListaExpedientesCount + " Expedientes regitrados correctamente. "
                    + " Puede solicitar las consulta a los webservice NISAE seleccionando la opcion Solo Expedientes Especificos.";
        } catch (Exception e) {
            log.error("Se ha presentado un erro al registrar la lista de expedientes especificos..", e);
            respuestaServicio ="- Se ha presentado un error al guardar la lista de para el Filtro Expedientes Especificos. " + e.getMessage();
        }
        log.info("respuestaServicio : " + respuestaServicio);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        String presupuestoJsonString = gson.toJson(respuestaServicio);
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(presupuestoJsonString);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("Error preparando respuesta " + e.getMessage(), e);
            e.printStackTrace();
        }
        log.info("estasCorrienteHaciendaForalHHFFBatch - End()" + new Date().toString());
    }

    public void getListaFiltroExpedientesEspecificos(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        log.info("cargarListaFiltroExpedientesEspecificos - Begin()" + new Date().toString());
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        List<InteropServiciosNisaeExpFi> listaGuardar = new ArrayList<InteropServiciosNisaeExpFi>();
        String jsonString = "";
        try {
            //Recojo los parametros
            listaGuardar = gestionServiciosNISAE.getListaFiltroExpedientesEspecificos(codOrganizacion, adapt);
        } catch (Exception e) {
            log.error("Se ha presentado un erro al registrar la lista de expedientes especificos..", e);
            listaGuardar=null;
            jsonString ="- Se ha presentado un error al recuperar los datos del filtro Expedientes Especificos. " + e.getMessage();
        }

        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("dd/MM/yyyy");
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        if(listaGuardar!=null)
            jsonString = gson.toJson(listaGuardar);
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(jsonString);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("Error preparando respuesta " + e.getMessage(), e);
            e.printStackTrace();
        }
        log.info("estasCorrienteHaciendaForalHHFFBatch - End()" + new Date().toString());
    }

    public void getExpedientesProcesarNISAENumeroTotalProcesar(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        log.info("getExpedientesProcesarNISAENumeroTotalProcesar - Begin()" + new Date().toString());
        String[] respuestaServicio = new String[2];
        int idiomaUsuario = 1;
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        String ejercicio = "";
        String procedimiento = "";
        String estadoExpediente = "";
        String numeroExpedienteDesde = "";
        String numeroExpedienteHasta = "";
        String soloPeticionesEstEnProceso = "";
        Integer fkWSSolicitado = 0;
        int cantidadExpedientesProcesar = 0;
        String ejecutarFiltroExpedientesEspecificos = "";
        try {
            if (request.getParameter("idioma") != null) {
                try {
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                } catch (Exception ex) {
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }
            //Recojo los parametros
            ejercicio = (String) request.getParameter("ejercicioHHFF");
            procedimiento = (String) request.getParameter("procedimientoHHFF");
            estadoExpediente = (String) request.getParameter("estadoexpediente");
            numeroExpedienteDesde = (String) request.getParameter("numeroExpedienteDesde");
            numeroExpedienteHasta = (String) request.getParameter("numeroExpedienteHasta");
            soloPeticionesEstEnProceso = (String) request.getParameter("soloPeticionesEstEnProceso");
            fkWSSolicitado = ((String) request.getParameter("fkWSSolicitado") != null && !((String) request.getParameter("fkWSSolicitado")).isEmpty() ? Integer.valueOf((String) request.getParameter("fkWSSolicitado")) : 0);
            ejecutarFiltroExpedientesEspecificos = (String) request.getParameter("ejecutarFiltroExpedientesEspecificos");
            log.info("parametrosRecibidos : " + ejercicio + " " + procedimiento + " " + estadoExpediente
                    + " " + numeroExpedienteDesde + " " + numeroExpedienteHasta + " " + fkWSSolicitado + " " + ejecutarFiltroExpedientesEspecificos);

            String idProcoFlexia_LAN6 = gestionAdaptadoresLan6Config.getCodProcedimientoPlatea(procedimiento);
            log.info("idProcoFlexia_LAN6 : " + idProcoFlexia_LAN6);

            InteropLlamadasServiciosNisae interopLlamadasServiciosNisae = new InteropLlamadasServiciosNisae();
            interopLlamadasServiciosNisae.setCodOrganizacion(codOrganizacion);
            interopLlamadasServiciosNisae.setEjercicioHHFF(ejercicio);
            interopLlamadasServiciosNisae.setProcedimientoHHFF(procedimiento);
            interopLlamadasServiciosNisae.setEstadoExpediente(estadoExpediente);
            interopLlamadasServiciosNisae.setNumeroExpedienteDesde(numeroExpedienteDesde);
            interopLlamadasServiciosNisae.setNumeroExpedienteHasta(numeroExpedienteHasta);
            interopLlamadasServiciosNisae.setSoloPeticionesEstEnProceso(soloPeticionesEstEnProceso);
            interopLlamadasServiciosNisae.setFkWSSolicitado(fkWSSolicitado);
            interopLlamadasServiciosNisae.setEjecutarFiltroExpedientesEspecificos(ejecutarFiltroExpedientesEspecificos != null && !ejecutarFiltroExpedientesEspecificos.isEmpty() ? ejecutarFiltroExpedientesEspecificos : "0");

            //Numero de Expedientes a procesar
            cantidadExpedientesProcesar = gestionServiciosNISAE.getExpedientesProcesarNISAENumeroTotalProcesar(codOrganizacion, interopLlamadasServiciosNisae, adapt);
            log.info("-- Numero de expedientes a tratar: " + cantidadExpedientesProcesar);
            // Mensaje para el Alert
            respuestaServicio[0] = " - Seguro de ejecutar esta Operacion? "
                    + " Ejercicio : " + (ejercicio != null && !ejercicio.isEmpty() ? ejercicio : "")
                    + " Procedimiento : " + (procedimiento != null && !procedimiento.isEmpty() ? procedimiento : "")
                    + " Estado Expedientes : " + (estadoExpediente != null && !estadoExpediente.isEmpty() ? estadoExpediente : "")
                    + " Numero Expediente Desde : " + (numeroExpedienteDesde != null && !numeroExpedienteDesde.isEmpty() ? numeroExpedienteDesde : "")
                    + " Numero Expediente Hasta : " + (numeroExpedienteHasta != null && !numeroExpedienteHasta.isEmpty() ? numeroExpedienteHasta : "")
            ;
            respuestaServicio[1] = " Se van a procesar " + cantidadExpedientesProcesar + " Expedientes. ";

        } catch (Exception ex) {
            log.error("WS_verificarDatosPadron - Exception general al llamar al WS de datos del padron. "
                    + numExpediente + ex.getMessage()
            );
            if (respuestaServicio==null || respuestaServicio[0].isEmpty()) {
                respuestaServicio[0] = "-2";
                respuestaServicio[1] = "Error general al acceder al Servicio. Consulte al equipo de soporte para mas detalles.";
            }
        }

        log.debug("respuestaServicio : " + respuestaServicio!=null ? respuestaServicio.toString(): "");

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        String presupuestoJsonString = gson.toJson(respuestaServicio);
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(presupuestoJsonString);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("Error preparando respuesta " + e.getMessage(), e);
            e.printStackTrace();
        }

        log.info("getExpedientesProcesarNISAENumeroTotalProcesar - End()" + new Date().toString());
    }

    public void consultaDatosFiscalesEIKABatch(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        log.info("consultaDatosFiscalesEIKABatch - Begin()" + new Date().toString());
        String respuestaServicio = "Respuesta del Servicio....";
        List<TerceroVO> lista = new ArrayList<TerceroVO>();
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        int idiomaUsuario = 1;
        String idProcoFlexia_LAN6 = "";  //String idPeticionPrevia="",numExpedientePeticion="";
        String ejercicioHHFF = "";
        String procedimientoHHFF = "";
        String estadoExpediente = "";
        String numeroExpedienteDesde = "";
        String numeroExpedienteHasta = "";
        String soloPeticionesEstEnProceso = "";
        Integer fkWSSolicitado = 0;
        int cantidadExpedientesProcesar =0;
        String ejecutarFiltroExpedientesEspecificos="";
        try {
            if (request.getParameter("idioma") != null) {
                try {
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                } catch (Exception ex) {
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }
            //Recojo los parametros
            ejercicioHHFF = (String) request.getParameter("ejercicioHHFF");
            procedimientoHHFF = (String) request.getParameter("procedimientoHHFF");
            estadoExpediente = (String) request.getParameter("estadoexpediente");
            numeroExpedienteDesde = (String) request.getParameter("numeroExpedienteDesde");
            numeroExpedienteHasta = (String) request.getParameter("numeroExpedienteHasta");
            soloPeticionesEstEnProceso = (String) request.getParameter("soloPeticionesEstEnProceso");
            fkWSSolicitado = ((String) request.getParameter("fkWSSolicitado") != null && !((String) request.getParameter("fkWSSolicitado")).isEmpty() ? Integer.valueOf((String) request.getParameter("fkWSSolicitado")) : 0);
            ejecutarFiltroExpedientesEspecificos=(String)request.getParameter("ejecutarFiltroExpedientesEspecificos");
            log.info("parametrosRecibidos : " + ejercicioHHFF + " " + procedimientoHHFF + " " + estadoExpediente
                    + " " + numeroExpedienteDesde + " " + numeroExpedienteHasta + " " + fkWSSolicitado + " " + ejecutarFiltroExpedientesEspecificos);
            idProcoFlexia_LAN6 = gestionAdaptadoresLan6Config.getCodProcedimientoPlatea("procedimientoHHFF"); ///



            log.info("idProcoFlexia_LAN6 : " + idProcoFlexia_LAN6);
            InteropLlamadasServiciosNisae interopLlamadasServiciosNisae = new InteropLlamadasServiciosNisae();
            interopLlamadasServiciosNisae.setCodOrganizacion(codOrganizacion);
            interopLlamadasServiciosNisae.setEjercicioHHFF(ejercicioHHFF);
            interopLlamadasServiciosNisae.setProcedimientoHHFF(procedimientoHHFF);
            interopLlamadasServiciosNisae.setEstadoExpediente(estadoExpediente);
            interopLlamadasServiciosNisae.setNumeroExpedienteDesde(numeroExpedienteDesde);
            interopLlamadasServiciosNisae.setNumeroExpedienteHasta(numeroExpedienteHasta);
            interopLlamadasServiciosNisae.setSoloPeticionesEstEnProceso(soloPeticionesEstEnProceso);
            interopLlamadasServiciosNisae.setFkWSSolicitado(fkWSSolicitado);
            interopLlamadasServiciosNisae.setEjecutarFiltroExpedientesEspecificos(ejecutarFiltroExpedientesEspecificos!=null && !ejecutarFiltroExpedientesEspecificos.isEmpty() ? ejecutarFiltroExpedientesEspecificos : "0");

            // Recogemos expedientes
            log.info("-- Leer Expedientes");
            cantidadExpedientesProcesar = gestionServiciosNISAE.getExpedientesProcesarNISAENumeroTotalProcesar(codOrganizacion, interopLlamadasServiciosNisae, adapt);
            // Inciamos proceso segundo plano
            log.info("Llamar  THREAD consultaDatosFiscalesEIKABatch");
            GestionServiciosNISAEEjecucionEIKA ejecutaThread = new GestionServiciosNISAEEjecucionEIKA();
            ejecutaThread.start(codOrganizacion, interopLlamadasServiciosNisae, idProcoFlexia_LAN6,adapt);
            log.info("Proceso THREAD GestionServiciosNISAEEjecucionEIKA Lanzado Correctamente..");
            respuestaServicio = "Proceso segundo plano Lanzado Correctamente. ";
        } catch (Exception ex) {
            log.error("Se ha producido una al procesar llamada al WS " + numExpediente, ex);
            String mensajeExcepcion = "";
            respuestaServicio = "Error al invocar el proceso en segundo plano : " + ex.getMessage();
            try {
                log.info("Vamos a registrar el error en BD : " + ex.getMessage());
                String causaExcepcion = (ex.getCause() != null ? (ex.getCause().getMessage() != null ? ex.getCause().getMessage() + " - " + ex.getCause().toString() : ex.getCause().toString()) : "");
                mensajeExcepcion = ex.getMessage();
                String trazaError = ExceptionUtils.getStackTrace(ex);  // ExceptionUtils.getStackTrace(ex);

                Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                        trazaError, ConstantesMeLanbideInterop.ERROR_NISAE_SISTEMA_ORIGEN_FLEXIA,
                        ConstantesMeLanbideInterop.ERROR_NISAE_CODIGO_ERROR_EIKA, ConstantesMeLanbideInterop.ERROR_NISAE_MENSAJE_EIKA, 1);
                ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, ex);
                int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, numeroExpedienteDesde + "-" + numeroExpedienteHasta, idProcoFlexia_LAN6, ejercicioHHFF + "-" + procedimientoHHFF, "estasCorrienteHaciendaForalHHFFBatch");
                log.info("Error Registrado en BD correctamente. " + idError);
                respuestaServicio += " - Mas detalles en el Gestor Errores : ID:" + idError;
            } catch (Exception ex1) {
                log.error("NISAE-EIKA.Flexia Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : " + ex.getMessage(), ex1);
                respuestaServicio += " - Error al intentar registrar y notificar un error en la ejecucion del proceso : " + ex1.getMessage();
                //throw ex1;
            }
        }

        respuestaServicio += " - Peticion procesada con los parametros: "
                + " Ejercicio : " + (ejercicioHHFF != null && !ejercicioHHFF.isEmpty() ? ejercicioHHFF : "")
                + " Procedimiento : " + (procedimientoHHFF != null && !procedimientoHHFF.isEmpty() ? procedimientoHHFF : "")
                + " Estado Expedientes : " + (estadoExpediente != null && !estadoExpediente.isEmpty() ? estadoExpediente : "")
                + " Numero Expediente Desde : " + (numeroExpedienteDesde != null && !numeroExpedienteDesde.isEmpty() ? numeroExpedienteDesde : "")
                + " Numero Expediente Hasta : " + (numeroExpedienteHasta != null && !numeroExpedienteHasta.isEmpty() ? numeroExpedienteHasta : "")
                + " Expedientes a Procesar : " + cantidadExpedientesProcesar;
        log.info("respuestaServicio : " + respuestaServicio);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        String presupuestoJsonString = gson.toJson(respuestaServicio);
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(presupuestoJsonString);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("Error preparando respuesta " + e.getMessage(), e);
            e.printStackTrace();
        }
        log.info("GestionServiciosNISAEEjecucionEIKA - End()" + new Date().toString());
    }

    public String cargarPantallaPrincipalCargaTelematicaMEFromXML(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        log.info("cargarPantallaPrincipalCargaTelematicaMEFromXML - Begin()" + formatDateLog.format(new Date()));
        String respuestaServicio = "/jsp/extension/melanbide_interop/telematico/utilities/telematicoUtilCargaDatExtensionXML.jsp";
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        int idiomaUsuario = 1;
        try {
            if (request.getParameter("idioma") != null && !request.getParameter("idioma").isEmpty()) {
                try {
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                } catch (Exception ex) {
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }
            List<ComboNisae> listaProcedimiento = gestionServiciosNISAE.getComboNisaeProcedimiento(codOrganizacion, adapt);
            request.setAttribute("listaProcedimiento", listaProcedimiento);
        } catch (Exception ex) {
            log.error("Se ha producido un error a cargar la pantalla principal Carga telematica ME from XML ", ex);
            request.setAttribute("mensajeInicial", "Se ha presentado un error al cargar la pantalla de los servcios NISAE ...Contacte con el ADMIN para mas detalles. " + ex.getMessage());
        }
        log.info("cargarPantallaPrincipalCargaTelematicaMEFromXML - End()" + formatDateLog.format(new Date()) + " " + respuestaServicio);
        return respuestaServicio;
    }

    public void cargarDatosMEFromXMlEntradaRegistroTelematica(int codOrganizacion, int codTramite, int ocurrenciaTramite, String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        log.info("cargarDatosMEFromXMlEntradaRegistroTelematica - Begin()" + formatDateLog.format(new Date()));
        String respuestaServicio = "Respuesta del Servicio....";
        AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        int idiomaUsuario = 1;
        int cantidadExpedientesProcesar = 0;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        MEInteropCargaTelemXMLParameters mEInteropCargaTelemXMLParameters = new MEInteropCargaTelemXMLParameters();
        try {
            if (request.getParameter("idioma") != null) {
                try {
                    idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
                } catch (Exception ex) {
                    log.error("Error al parsear request.getParameter(idioma) a Entero. Asignamos idioma por defecto 1 Castellano.", ex);
                }
            }
            //Recojo los parametros
            String jsonParametrosLLamada = request.getParameter("jsonParametrosLLamada");
            mEInteropCargaTelemXMLParameters = (MEInteropCargaTelemXMLParameters) gson.fromJson(jsonParametrosLLamada, MEInteropCargaTelemXMLParameters.class);
            log.info("parametrosRecibidos : " + mEInteropCargaTelemXMLParameters.toString());

            // Recogemos expedientes
            log.info("-- Leer Expedientes");
            MEInteropCargaTelemXMLServiceDAO mEInteropCargaTelemXMLServiceDAO = new MEInteropCargaTelemXMLServiceDAO();
            List<MEInteropCargaTelemXMLExpediente> listaExpedientes = mEInteropCargaTelemXMLServiceDAO.getExpedientesAndDocumentosProcesar(codOrganizacion, mEInteropCargaTelemXMLParameters, adapt);
            cantidadExpedientesProcesar = (listaExpedientes != null ? listaExpedientes.size() : 0);

            // Inciamos proceso segundo plano
            log.info("Llamar  THREAD MEInteropCargaTelemXMLService");
            MEInteropCargaTelemXMLService ejecutaThread = new MEInteropCargaTelemXMLService();
            ejecutaThread.start(codOrganizacion, mEInteropCargaTelemXMLParameters, listaExpedientes, adapt);
            log.info("Proceso THREAD MEInteropCargaTelemXMLService Lanzado Correctamente..");
            respuestaServicio = "Proceso segundo plano Lanzado Correctamente. ";
        } catch (Exception ex) {
            log.error("Se ha producido una al procesar llamada al WS " + numExpediente, ex);
            String mensajeExcepcion = "";
            respuestaServicio = "Error al invocar el proceso en segundo plano : " + ex.getMessage();
            try {
                log.info("Vamos a registrar el error en BD : " + ex.getMessage());
                String causaExcepcion = (ex.getCause() != null ? (ex.getCause().getMessage() != null ? ex.getCause().getMessage() + " - " + ex.getCause().toString() : ex.getCause().toString()) : "");
                mensajeExcepcion = ex.getMessage();
                String trazaError = (ex.getStackTrace() != null ? Arrays.toString(ex.getStackTrace()) : "");

                Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                        trazaError, ConstantesMeLanbideInterop.ERROR_NISAE_SISTEMA_ORIGEN_FLEXIA,
                        "TELEMATICO_01", "Error carga datos ME telematicamente desde XML", 1);
                ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, ex);
                int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, mEInteropCargaTelemXMLParameters.getNumeroExpediente() + "-" + mEInteropCargaTelemXMLParameters.getNumeroExpedienteDesde() + "-" + mEInteropCargaTelemXMLParameters.getNumeroExpedienteHasta(), "-", mEInteropCargaTelemXMLParameters.getEjercicio() + "-" + mEInteropCargaTelemXMLParameters.getProcedimiento(), "cargarDatosMEFromXMlEntradaRegistroTelematica");
                log.info("Error Registrado en BD correctamente. " + idError);
                respuestaServicio += " - Mas detalles en el Gestor Errores : ID:" + idError;
            } catch (Exception ex1) {
                log.error("RegErrores.Flexia Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : " + ex.getMessage(), ex1);
                respuestaServicio += " - Error al intentar registrar y notificar un error en la ejecucion del proceso : " + ex1.getMessage();
            }
        }

        respuestaServicio += " - Peticion procesada con los parametros: "
                + " Numero Expediente: " + (mEInteropCargaTelemXMLParameters.getNumeroExpediente() != null && !mEInteropCargaTelemXMLParameters.getNumeroExpediente().isEmpty() ? mEInteropCargaTelemXMLParameters.getNumeroExpediente() : "")
                + " Ejercicio : " + (mEInteropCargaTelemXMLParameters.getEjercicio() != null ? mEInteropCargaTelemXMLParameters.getEjercicio() : "")
                + " Procedimiento : " + (mEInteropCargaTelemXMLParameters.getProcedimiento() != null && !mEInteropCargaTelemXMLParameters.getProcedimiento().isEmpty() ? mEInteropCargaTelemXMLParameters.getProcedimiento() : "")
                + " Numero Expediente Desde : " + (mEInteropCargaTelemXMLParameters.getNumeroExpedienteDesde() != null && !mEInteropCargaTelemXMLParameters.getNumeroExpedienteDesde().isEmpty() ? mEInteropCargaTelemXMLParameters.getNumeroExpedienteDesde() : "")
                + " Numero Expediente Hasta : " + (mEInteropCargaTelemXMLParameters.getNumeroExpedienteHasta() != null && !mEInteropCargaTelemXMLParameters.getNumeroExpedienteHasta().isEmpty() ? mEInteropCargaTelemXMLParameters.getNumeroExpedienteHasta() : "")
                + " Expedientes a Procesar : " + cantidadExpedientesProcesar;
        ;
        log.info("respuestaServicio : " + respuestaServicio);

        String presupuestoJsonString = gson.toJson(respuestaServicio);
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(presupuestoJsonString);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("Error preparando respuesta " + e.getMessage(), e);
            e.printStackTrace();
        }
        log.info("cargarDatosMEFromXMlEntradaRegistroTelematica - End()" + new Date().toString());
    }

    // INICIO Tarea  IKER - [40984] - Interoperabilidad - Consulta vida laboral desde la ficha de expediente
    public String cargarVidaLaboral(int codOrganizacion, int codTramite, int ocurrenciaTramite,
                                    final List<Persona> personas, String numExpediente,
                                    HttpServletRequest request, HttpServletResponse response) {
        List<RegistroVidaLaboralVO> listaRegistros = new ArrayList<RegistroVidaLaboralVO>();

        try {
            log.info("=========== ENTRO en cargarVidaLaboral");
            final AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));

            listaRegistros = MeLanbideInteropManager.getInstance().getDatosVidaLaboralIntermediacion(numExpediente, personas,
                    adapt);

            final List<RegistroVidaLaboralVO> listaRegConProv = new ArrayList<RegistroVidaLaboralVO>();
            for (final RegistroVidaLaboralVO registro : listaRegistros) {
                final RegistroVidaLaboralVO regConProvincia = registro;
                regConProvincia.setProvincia(MeLanbideInteropManager.getInstance().getNombreProvincia(registro.getProvincia(), adapt));
                listaRegConProv.add(regConProvincia);
            }
            listaRegistros = listaRegConProv;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MELANBIDE_INTEROP.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("listaRegistros", listaRegistros);
        log.info("=========== End en cargarVidaLaboral");
        return "";
    }

    public void consultaVidaLaboralCVLBatchExpediente(int codOrganizacion, int codTramite, int ocurrenciaTramite,
                                                      final List<Persona> personas,
                                                      String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        log.info("Entramos en consultaVidaLaboralCVLBatchExpediente de " + this.getClass().getName());

        List<RegistroVidaLaboralVO> listaRegistros = new ArrayList<RegistroVidaLaboralVO>();
        Response responseWS = null;
        String codRespuesta = "-2";
        final List<Response> responseWSs = new ArrayList<Response>();

        AdaptadorSQLBD adapt = null;
        try {
            adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
        } catch (final Exception ex) {
            log.error("consultaVidaLaboralCVLBatchExpediente: Error obteniendo adaptador " + ex.getMessage());
        }


        final String idProcoFlexia_LAN6 = gestionAdaptadoresLan6Config.getCodProcedimientoPlatea((String) request.getParameter("procedimientoHHFF"));
        log.debug("MELANBIDE_INTEROP consultaVidaLaboralCVLBatchExpediente idProcoFlexia_LAN6 : " + idProcoFlexia_LAN6);

        final InteropLlamadasServiciosNisae interopLlamadasServiciosNisae = new InteropLlamadasServiciosNisae();
        interopLlamadasServiciosNisae.setCodOrganizacion(codOrganizacion);
        interopLlamadasServiciosNisae.setEjercicioHHFF((String) request.getParameter("ejercicioHHFF"));
        interopLlamadasServiciosNisae.setProcedimientoHHFF((String) request.getParameter("procedimientoHHFF"));
        interopLlamadasServiciosNisae.setEstadoExpediente(null);
        interopLlamadasServiciosNisae.setNumeroExpedienteDesde((String) request.getParameter("numeroExpedienteDesde"));
        interopLlamadasServiciosNisae.setNumeroExpedienteHasta((String) request.getParameter("numeroExpedienteDesde"));
        interopLlamadasServiciosNisae.setSoloPeticionesEstEnProceso(null);
        interopLlamadasServiciosNisae.setFkWSSolicitado(new Integer((String) request.getParameter("fkWSSolicitado")).intValue());
        interopLlamadasServiciosNisae.setFechaDesdeCVL((String) request.getParameter("fechaDesdeCVL"));
        interopLlamadasServiciosNisae.setFechaHastaCVL((String) request.getParameter("fechaHastaCVL"));
        interopLlamadasServiciosNisae.setEjecutarFiltroExpedientesEspecificos("0");

        ExecutorService executor = Executors.newFixedThreadPool(1);
        final List<Future<?>> futures = new ArrayList<Future<?>>();

        if (personas.size() > 0) {
            final GestionServiciosNISAECVL ejecutaThread = new GestionServiciosNISAECVL();
            ejecutaThread.startWithoutThread(codOrganizacion, interopLlamadasServiciosNisae,
                    idProcoFlexia_LAN6, personas, adapt);
            final Future<?> f = executor.submit(ejecutaThread);
            futures.add(f);
            log.info("MELANBIDE_INTEROP consultaVidaLaboralCVLBatchExpediente Proceso THREAD Lanzado Correctamente..");
        }

        for (final Persona p : personas) {
            log.debug("MELANBIDE_INTEROP consultaVidaLaboralCVLBatchExpediente numDocumento = "
                    + p.getNumDocumento() + ", tipoDocumento = " + p.getTipoDocumento()
                    + ", fechaDesde = " + request.getParameter("fechaDesdeCVL")
                    + ", fechaHasta = " + request.getParameter("fechaHastaCVL")
                    + ", numExpediente = " + numExpediente);
            responseWS = ClientWSVidaLaboral.getVidaLaboral(p,
                    request.getParameter("fechaDesdeCVL"),
                    request.getParameter("fechaHastaCVL"), codOrganizacion, numExpediente, request.getParameter("fkWSSolicitado"));
            responseWSs.add(responseWS);
            if (responseWS != null) {
                log.debug("MELANBIDE_INTEROP consultaVidaLaboralCVLBatchExpediente responseWS = " + responseWS.getCodRespuesta() + ", " + responseWS.getDescRespuesta()  + ", " + responseWS);
                codRespuesta = responseWS.getCodRespuesta();
                if (responseWS.getCodRespuesta().equals("0000")) {
                    log.debug("MELANBIDE_INTEROP consultaVidaLaboralCVLBatchExpediente respuesta correcta");
                }
                else {
                    log.debug("MELANBIDE_INTEROP consultaVidaLaboralCVLBatchExpediente respuesta incorrecta");
                }
            }
        }

        if (personas.size() > 0) {
            try {
                futures.get(0).get();
                log.debug("MELANBIDE_INTEROP consultaVidaLaboralCVLBatchExpediente Proceso THREAD finalizado Correctamente..");
            } catch (InterruptedException ex) {
                log.error("MELANBIDE_INTEROP consultaVidaLaboralCVLBatchExpediente Proceso THREAD finalizado con error " +  ex.getMessage());
            } catch (ExecutionException ex) {
                log.error("MELANBIDE_INTEROP consultaVidaLaboralCVLBatchExpediente Proceso THREAD finalizado con error " +  ex.getMessage());
            }
        }

        int i = 0;
        for (final Persona p : personas) {
            if (responseWSs.get(i) != null &&
                    responseWSs.get(i).getCodRespuesta().equals("0000")) {
                try {
                    List<RegistroVidaLaboralVO> listaRegistrosSinNombreProv
                            = MeLanbideInteropMappingUtils.mapListaSituacionToListaVidaLaboral(responseWSs.get(i).getIdentidad(), p,
                            request.getParameter("fechaDesdeCVL"), request.getParameter("fechaHastaCVL"));
                    MeLanbideInteropManager.getInstance().gestionarVidaLaboralIntermediacion(listaRegistrosSinNombreProv, numExpediente, adapt);
                    listaRegistrosSinNombreProv = MeLanbideInteropManager.getInstance().getDatosVidaLaboralIntermediacion(numExpediente,
                            p.getNumDocumento(), p.getTipoDocumento(), adapt);
                    for (final RegistroVidaLaboralVO registro : listaRegistrosSinNombreProv) {
                        final RegistroVidaLaboralVO regConProvincia = registro;
                        regConProvincia.setProvincia(MeLanbideInteropManager.getInstance().getNombreProvincia(registro.getProvincia(), adapt));
                        listaRegistros.add(regConProvincia);
                    }
                } catch (Exception ex) {
                    log.error("MELANBIDE_INTEROP consultaVidaLaboralCVLBatchExpediente WS_vidaLaboral - Exception general al llamar al WS de vida laboral. "
                            + numExpediente + " " + ex.getMessage());
                }
            }
            i++;
        }
        for (final Response r : responseWSs) {
            if (r.getCodRespuesta().equals("0000")) {
                codRespuesta = r.getCodRespuesta();
            }
        }
        if (personas.isEmpty()) {
            codRespuesta = "0000";
        }

        try {
            listaRegistros = MeLanbideInteropManager.getInstance().getDatosVidaLaboralIntermediacion(numExpediente,
                    adapt);
            final List<RegistroVidaLaboralVO> listaRegConProv = new ArrayList<RegistroVidaLaboralVO>();
            for (final RegistroVidaLaboralVO registro : listaRegistros) {
                final RegistroVidaLaboralVO regConProvincia = registro;
                regConProvincia.setProvincia(MeLanbideInteropManager.getInstance().getNombreProvincia(registro.getProvincia(), adapt));
                listaRegConProv.add(regConProvincia);
            }
            listaRegistros = listaRegConProv;
        } catch (Exception ex) {
            log.error("MELANBIDE_INTEROP consultaVidaLaboralCVLBatchExpediente WS_vidaLaboral - Exception general al llamar al WS de vida laboral. "
                    + numExpediente + " " + ex.getMessage());
        }

        final RespuestaWSVidaLaboralVO respuesta = new RespuestaWSVidaLaboralVO(codRespuesta, listaRegistros);

        log.debug("MELANBIDE_INTEROP consultaVidaLaboralCVLBatchExpediente objeto con la informacion = " + respuesta);
        log.info("consultaVidaLaboralCVLBatchExpediente - End() " + new Date().toString());
        RespuestaAjaxUtils.retornarJSON(gson.toJson(respuesta), response);
    }

    // Generar Excel Vida laboral
    public void generarExcelVidaLaboral(int codOrganizacion, int codTramite, int ocurrenciaTramite,
                                        final List<Persona> personas, String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        log.debug(">>>> ENTRA en generarExcelVidaLaboral  de " + this.getClass().getSimpleName());
        String rutaArchivoSalida = null;
        List<RegistroVidaLaboralVO> listaRegistros = new ArrayList<RegistroVidaLaboralVO>();

        int idioma = 1;

        try {
            numExpediente = request.getParameter("numExp");
            final AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));

            listaRegistros = MeLanbideInteropManager.getInstance().getDatosVidaLaboralIntermediacion(numExpediente, personas,
                    adapt);


            final List<RegistroVidaLaboralVO> listaRegConProv = new ArrayList<RegistroVidaLaboralVO>();
            for (final RegistroVidaLaboralVO registro : listaRegistros) {
                final RegistroVidaLaboralVO regConProvincia = registro;
                regConProvincia.setProvincia(MeLanbideInteropManager.getInstance().getNombreProvincia(registro.getProvincia(), adapt));
                listaRegConProv.add(regConProvincia);
            }
            listaRegistros = listaRegConProv;

        } catch (Exception ex) {
            log.error("Error recuperando datos de vida laboral : " + ex);
        }
        try {
            FileInputStream istr = null;
            try {
                HSSFWorkbook libro = new HSSFWorkbook();

                HSSFPalette palette = libro.getCustomPalette();
                HSSFColor hssfColor = null;
                try {
                    hssfColor = palette.findColor((byte) 75, (byte) 149, (byte) 211);
                    if (hssfColor == null) {
                        hssfColor = palette.getColor(HSSFColor.ROYAL_BLUE.index);
                    }
                } catch (Exception e) {
                }

                HSSFFont negrita = libro.createFont();
                negrita.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

                HSSFFont normal = libro.createFont();
                normal.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
                normal.setFontHeight((short) 150);

                HSSFFont negritaTitulo = libro.createFont();
                negritaTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                negritaTitulo.setColor(HSSFColor.WHITE.index);
                negritaTitulo.setFontHeight((short) 200);
                int numFila = 0;

                HSSFRow fila = null;
                HSSFCell celda = null;
                HSSFCellStyle estiloCelda = null;

                HSSFSheet hoja = libro.createSheet("Datos Vida Laboral - Plantilla");

                //Se establece el ancho de cada columnas
                hoja.setColumnWidth(0, 4000); // Tipo documentación
                hoja.setColumnWidth(1, 4000); // Documentacion
                hoja.setColumnWidth(2, 3200); // Fecha desde
                hoja.setColumnWidth(3, 3200); // Fecha hasta
                hoja.setColumnWidth(4, 4000); // Número afiliación
                hoja.setColumnWidth(5, 3200); // Fecha nacimiento
                hoja.setColumnWidth(6, 5000); // Resumen completo
                hoja.setColumnWidth(7, 3000); // Régimen empresa
                hoja.setColumnWidth(8, 5000); // Código cuenta cotización
                hoja.setColumnWidth(9, 5000); // Provincia
                hoja.setColumnWidth(10, 3200); // Fecha alta
                hoja.setColumnWidth(11, 3200); // Fecha efectos
                hoja.setColumnWidth(12, 3200); // Fecha baja
                hoja.setColumnWidth(13, 5000); // Contrato trabajo
                hoja.setColumnWidth(14, 5000); // Contrato parcial
                hoja.setColumnWidth(15, 5000); // Grupo cotización
                hoja.setColumnWidth(16, 3000); // Días alta

                fila = hoja.createRow(numFila);
                //Se establece el alto de las columnas
                fila.setHeight((short) 750);

                crearEstiloInformeVidaLaboral(libro, fila, celda, estiloCelda, idioma);

                estiloCelda = libro.createCellStyle();
                estiloCelda.setWrapText(true);
                estiloCelda.setFont(normal);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                //estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                //Insertamos los datos, fila a fila

                for (final RegistroVidaLaboralVO registro : listaRegistros) {
                    numFila++;
                    fila = hoja.createRow(numFila);

                    //COLUMNA: Tipo documentación
                    celda = fila.createCell(0);
                    celda.setCellValue(registro.getTipoDocumentacion());
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Documentacion
                    celda = fila.createCell(1);
                    celda.setCellValue(registro.getDocumentacion());
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Fecha desde
                    celda = fila.createCell(2);
                    celda.setCellValue(registro.getFechaDesde() != null ? dateFormat.format(registro.getFechaDesde()) : "");
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Fecha hasta
                    celda = fila.createCell(3);
                    celda.setCellValue(registro.getFechaHasta() != null ? dateFormat.format(registro.getFechaHasta()) : "");
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Número afiliación
                    celda = fila.createCell(4);
                    celda.setCellValue(registro.getNumeroAfiliacionL());
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Fecha nacimiento
                    celda = fila.createCell(5);
                    celda.setCellValue(registro.getFechaNacimiento() != null ? dateFormat.format(registro.getFechaNacimiento()) : "");
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Resumen completo
                    celda = fila.createCell(6);
                    celda.setCellValue(registro.getResumenConplAniosAlta());
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Régimen empresa
                    celda = fila.createCell(7);
                    celda.setCellValue(registro.getRegimen());
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Código cuenta cotización
                    celda = fila.createCell(8);
                    celda.setCellValue(registro.getCodCuentaCot());
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Provincia
                    celda = fila.createCell(9);
                    celda.setCellValue(registro.getProvincia());
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Fecha alta
                    celda = fila.createCell(10);
                    celda.setCellValue(registro.getFechaAlta() != null ? dateFormat.format(registro.getFechaAlta()) : "");
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Fecha efectos
                    celda = fila.createCell(11);
                    celda.setCellValue(registro.getFechaEfectos() != null ? dateFormat.format(registro.getFechaEfectos()) : "");
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Fecha baja
                    celda = fila.createCell(12);
                    celda.setCellValue(registro.getFechaBaja() != null ? dateFormat.format(registro.getFechaBaja()) : "");
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Contrato trabajo
                    celda = fila.createCell(13);
                    celda.setCellValue(registro.getContratoTrabajo());
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Contrato parcial
                    celda = fila.createCell(14);
                    celda.setCellValue(registro.getContratoTParcial());
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Grupo cotizaci�n
                    celda = fila.createCell(15);
                    celda.setCellValue(registro.getGrupoCotizacion());
                    celda.setCellStyle(estiloCelda);

                    //COLUMNA: Grupo Días alta
                    celda = fila.createCell(16);
                    celda.setCellValue(registro.getDiasAlta());
                    celda.setCellStyle(estiloCelda);
                }

                File directorioTemp = new File(m_Conf.getString("PDF.base_dir"));
                File informe = File.createTempFile("resumenVidaLaboral", ".xls", directorioTemp);

                FileOutputStream archivoSalida = new FileOutputStream(informe);
                libro.write(archivoSalida);
                archivoSalida.close();

                rutaArchivoSalida = informe.getAbsolutePath();

                istr = new FileInputStream(rutaArchivoSalida);

                BufferedInputStream bstr = new BufferedInputStream(istr); // promote

                int size = (int) informe.length();
                byte[] data = new byte[size];
                bstr.read(data, 0, size);
                bstr.close();

                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "inline; filename=" + informe.getName());
                response.setHeader("Content-Transfer-Encoding", "binary");
                response.setContentLength(data.length);
                response.getOutputStream().write(data, 0, data.length);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (IOException ioe) {
                log.error("EXCEPCION informe generarExcelVidaLaboral");

            } finally {
                if (istr != null) {
                    istr.close();
                }
            }
        } catch (IOException ex) {
            log.error("EXCEPCION informe generarExcelVidaLaboral");
        }
    }



    private void crearEstiloInformeVidaLaboral(HSSFWorkbook libro, HSSFRow fila, HSSFCell celda, HSSFCellStyle estiloCelda, int idioma) {
        log.debug(">>>> ENTRA en crearEstiloInformeVidaLaboral de " + this.getClass().getSimpleName());
        try {
            MeLanbideInteropI18n meLanbideInteropI18n = MeLanbideInteropI18n.getInstance();

            HSSFPalette palette = libro.getCustomPalette();
            HSSFColor hssfColor = null;
            try {
                hssfColor = palette.findSimilarColor(153, 0, 0);  // DARK_RED //palette.findColor((byte) 75, (byte) 149, (byte) 211);
                if (hssfColor == null) {
                    hssfColor = palette.findSimilarColor(153, 0, 0);  // DARK_RED
                }
                palette.getColor(HSSFColor.GREY_50_PERCENT.index);

                HSSFFont negrita = libro.createFont();
                negrita.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

                HSSFFont normal = libro.createFont();
                normal.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
                normal.setFontHeight((short) 150);

                HSSFFont negritaTitulo = libro.createFont();
                negritaTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                negritaTitulo.setFontHeight((short) 170);
                negritaTitulo.setColor(HSSFColor.WHITE.index);
                //celda = fila.

                //cabeceras
                // Tipo documentación
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(0);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col1").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Documentacion
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(1);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col2").toUpperCase());
                celda.setCellStyle(estiloCelda);

                //Fecha desde
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(2);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col3").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Fecha hasta
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(3);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col4").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Número afiliación
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(4);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col5").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Fecha nacimiento
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(5);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col6").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Resumen completo
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(6);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col7").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Régimen empresa
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(7);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col8").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Código cuenta cotización
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(8);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col9").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Provincia
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(9);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col10").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Fecha alta
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(10);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col11").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Fecha efectos
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(11);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col12").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Fecha baja
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(12);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col13").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Contrato trabajo
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(13);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col14").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Contrato parcial
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(14);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col15").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Grupo cotización
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(15);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col16").toUpperCase());
                celda.setCellStyle(estiloCelda);

                // Días alta
                estiloCelda = libro.createCellStyle();
                estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                estiloCelda.setFillForegroundColor(hssfColor.getIndex());
                estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THICK);
                estiloCelda.setWrapText(true);
                estiloCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                estiloCelda.setFont(negritaTitulo);
                celda = fila.createCell(16);
                celda.setCellValue(meLanbideInteropI18n.getMensaje(idioma, "label.vidalaboral.col17").toUpperCase());
                celda.setCellStyle(estiloCelda);
            } catch (Exception e) {
            }
        } catch (Exception ex) {
        }
    }
    // Fin tarea IKER - [40984] - Interoperabilidad - Consulta vida laboral desde la ficha de expediente

    /**
     * PRUEBA E2E — PASO 5 (Servidor): Punto de entrada del proceso CVL masivo.
     * Recibe el POST de enviarPeticionCvlMasivoExcel, decodifica el Excel en base64,
     * extrae los NIFs y lanza el procesamiento de cada uno contra el WS de Vida Laboral.
     *
     * <h3>Parámetros de entrada recibidos del request HTTP:</h3>
     * <pre>
     *   codOrganizacion   = 15           (tomado de sesión o del parámetro codOrganizacionModulo)
     *   codTramite        = 120
     *   ocurrenciaTramite = 1
     *   numExpediente     = "EXP2024/000123"
     *   fechaDesdeCVL     = "2023-01-01"
     *   fechaHastaCVL     = "2023-12-31"
     *   fkWSSolicitado    = "1"
     *   listaDocsMasivo   = ""           (vacío -> se genera desde excelBase64)
     *   excelBase64       = "UEsDBBQA..." (base64 del fichero prueba_cvl_masivo.xlsx)
     *   usuario           = "TRAMITADOR01" (tomado de la sesión)
     * </pre>
     *
     * <h3>Flujo interno:</h3>
     * <ol>
     *   <li>Resuelve codOrganizacion efectivo: 15</li>
     *   <li>Como listaDocsMasivo está vacío, llama a convertirExcelBase64AListaDocs(excelBase64)
     *       y obtiene: {@code "12345678A;NIF\nX1234567L;NIE\n87654321B;NIF"}</li>
     *   <li>Abre conexión a BD de la organización 15.</li>
     *   <li>Instancia InteropCvlMasivoCsvService y llama a procesarCsv(...).</li>
     *   <li>Obtiene resumen: totalLeidos=3, Procesados=3, Correctos=2, Errores=1</li>
     * </ol>
     *
     * <h3>XML de respuesta generado (numExpediente="EXP2024/000123" se usa tal cual al ser no vacío):</h3>
     * <pre>
     * &lt;RESPUESTA&gt;
     *   &lt;CODIGO_OPERACION&gt;0&lt;/CODIGO_OPERACION&gt;
     *   &lt;RESULTADO&gt;&lt;![CDATA[Expediente contexto=EXP2024/000123,
     *     Leidos=3, Procesados=3, Correctos=2, Errores=1 |
     *     Detalle errores: [Linea 3: 87654321B -&gt; ERR01 Persona no encontrada]]]&gt;&lt;/RESULTADO&gt;
     * &lt;/RESPUESTA&gt;
     * </pre>
     * <p>
     * Nota: si numExpediente fuera vacío o nulo, se generaría un número técnico automático
     * con el formato {@code "CVL_MASIVO/YYYY/NNNNNN"} (p.ej. "CVL_MASIVO/2024/000042")
     * consultando la secuencia configurada en BD.
     * </p>
     *
     * <h3>Códigos de operación posibles:</h3>
     * <ul>
     *   <li>"0" -> Proceso completado (puede haber errores parciales en el detalle)</li>
     *   <li>"2" -> Error técnico inesperado en la ejecución</li>
     *   <li>"3" -> Error de validación (lista vacía, organización inválida, Excel ilegible)</li>
     * </ul>
     *
     * @param codOrganizacion    Código de organización de contexto (puede ser 0 si viene en el request).
     * @param codTramite         Código del trámite asociado al expediente.
     * @param ocurrenciaTramite  Número de ocurrencia del trámite.
     * @param numExpediente      Número de expediente de contexto (p.ej. "EXP2024/000123").
     * @param request            HttpServletRequest con los parámetros del POST.
     * @param response           HttpServletResponse donde se escribe el XML de respuesta.
     */
    public void ejecutarCvlMasivoDesdeTexto(int codOrganizacion, int codTramite, int ocurrenciaTramite,
            String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        String codigoOperacion = "0";
        String resultado = "";
        java.sql.Connection con = null;
        try {
            final String DEFAULT_FK_WS_SOLICITADO_VIDA_LABORAL = "1";
            int codOrganizacionEfectivo = codOrganizacion;
            if (codOrganizacionEfectivo <= 0) {
                final String codOrgRequest = request.getParameter("codOrganizacionModulo");
                if (codOrgRequest != null && codOrgRequest.trim().length() > 0) {
                    try {
                        codOrganizacionEfectivo = Integer.parseInt(codOrgRequest.trim());
                    } catch (Exception ex) {
                        log.error("ejecutarCvlMasivoDesdeTexto - codOrganizacionModulo invalido: " + codOrgRequest, ex);
                    }
                }
            }
            String listaDocsMasivo = request.getParameter("listaDocsMasivo");
            final String excelBase64 = request.getParameter("excelBase64");
            final String fechaDesdeCVL = request.getParameter("fechaDesdeCVL");
            final String fechaHastaCVL = request.getParameter("fechaHastaCVL");
            String fkWSSolicitado = request.getParameter("fkWSSolicitado");
            if (fkWSSolicitado == null || fkWSSolicitado.trim().length() == 0) {
                fkWSSolicitado = DEFAULT_FK_WS_SOLICITADO_VIDA_LABORAL;
            }

            String usuario = "SISTEMA";
            final UsuarioValueObject usuarioSesion = getUsarioLogueadoEnSession(request);
            if (usuarioSesion != null) {
                if (usuarioSesion.getNombreUsu() != null && usuarioSesion.getNombreUsu().trim().length() > 0) {
                    usuario = usuarioSesion.getNombreUsu();
                } else if (usuarioSesion.getIdUsuario() > 0) {
                    usuario = String.valueOf(usuarioSesion.getIdUsuario());
                }
            }

            log.info("ejecutarCvlMasivoDesdeTexto - Inicio"
                    + " codOrganizacion=" + codOrganizacionEfectivo
                    + ", numExpediente=" + numExpediente
                    + ", fechaDesdeCVL=" + fechaDesdeCVL
                    + ", fechaHastaCVL=" + fechaHastaCVL
                    + ", fkWSSolicitado=" + fkWSSolicitado
                    + ", usuario=" + usuario);

            if ((listaDocsMasivo == null || listaDocsMasivo.trim().length() == 0)
                    && excelBase64 != null && excelBase64.trim().length() > 0) {
                try {
                    listaDocsMasivo = convertirExcelBase64AListaDocs(excelBase64);
                } catch (Exception ex) {
                    codigoOperacion = "3";
                    resultado = "No se pudo leer el Excel recibido. Revise el formato y contenido del fichero.";
                    log.error("ejecutarCvlMasivoDesdeTexto - Error parseando excelBase64", ex);
                }
            }

            if (codOrganizacionEfectivo <= 0) {
                codigoOperacion = "3";
                resultado = "No se ha podido determinar la organización de ejecución.";
                log.error("ejecutarCvlMasivoDesdeTexto - codOrganizacion no valido: " + codOrganizacionEfectivo);
            } else if (listaDocsMasivo == null || listaDocsMasivo.trim().length() == 0) {
                codigoOperacion = "3";
                resultado = "Debe indicar una lista de NIF/NIE para procesar.";
                log.info("ejecutarCvlMasivoDesdeTexto - Lista vacia");
            } else {
                final AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacionEfectivo));
                con = adapt.getConnection();

                final es.altia.flexia.integracion.moduloexterno.melanbide_interop.services.InteropCvlMasivoCsvService servicio
                        = new es.altia.flexia.integracion.moduloexterno.melanbide_interop.services.InteropCvlMasivoCsvService();

                final es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.InteropCvlMasivoResultadoVO resumen
                        = servicio.procesarCsv(
                                new java.io.StringReader(listaDocsMasivo),
                                fechaDesdeCVL, fechaHastaCVL, codOrganizacionEfectivo,
                                codTramite, ocurrenciaTramite,
                                numExpediente, fkWSSolicitado, usuario, con);

                resultado = "Expediente contexto=" + resumen.getNumExpedienteContexto()
                        + ", Leidos=" + resumen.getTotalLeidos()
                        + ", Procesados=" + resumen.getTotalProcesados()
                        + ", Correctos=" + resumen.getTotalCorrectos()
                        + ", Errores=" + resumen.getTotalErrores();

                if (!resumen.getErrores().isEmpty()) {
                    resultado = resultado + " | Detalle errores: " + resumen.getErrores().toString();
                }

                log.info("ejecutarCvlMasivoDesdeTexto - Fin OK: " + resultado);
            }
        } catch (Exception ex) {
            codigoOperacion = "2";
            resultado = "Error ejecutando proceso masivo CVL: " + ex.getMessage();
            log.error("Error en ejecutarCvlMasivoDesdeTexto", ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    log.error("Error cerrando conexion ejecutarCvlMasivoDesdeTexto", e);
                }
            }
        }

        final StringBuffer xmlSalida = new StringBuffer();
        xmlSalida.append("<RESPUESTA>");
        xmlSalida.append("<CODIGO_OPERACION>").append(codigoOperacion).append("</CODIGO_OPERACION>");
        xmlSalida.append("<RESULTADO><![CDATA[")
                .append(resultado != null ? resultado : "")
                .append("]]></RESULTADO>");
        xmlSalida.append("</RESPUESTA>");

        try {
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            final PrintWriter out = response.getWriter();
            out.print(xmlSalida.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("Error preparando response ejecutarCvlMasivoDesdeTexto", e);
        }
    }

    public void consultarCvlMasivoPorFechas(int codOrganizacion, int codTramite, int ocurrenciaTramite,
            String numExpediente, HttpServletRequest request, HttpServletResponse response) {
        String codigoOperacion = "0";
        String resultado = "OK";
        Connection con = null;
        final java.util.List<java.util.Map<String, Object>> registrosSalida = new java.util.ArrayList<java.util.Map<String, Object>>();
        int total = 0;
        try {
            final String fechaDesde = request.getParameter("fechaDesdeCVL");
            final String fechaHasta = request.getParameter("fechaHastaCVL");
            final String dni = request.getParameter("dni");

            final Timestamp fechaDesdeTs = parsearFechaConsultaCvlMasivo(fechaDesde, false);
            final Timestamp fechaHastaTs = parsearFechaConsultaCvlMasivo(fechaHasta, true);

            if (fechaDesdeTs == null || fechaHastaTs == null) {
                codigoOperacion = "3";
                resultado = "Debe indicar las fechas desde/hasta para la consulta.";
            } else {
                final AdaptadorSQLBD adapt = this.getAdaptSQLBD(String.valueOf(codOrganizacion));
                con = adapt.getConnection();

                java.util.List<es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.InteropCvlMasivoNifVO> registros;
                if (dni != null && dni.trim().length() > 0) {
                    registros = es.altia.flexia.integracion.moduloexterno.melanbide_interop.dao.InteropCvlMasivoNifDAO
                            .getInstance().getRegistrosByFechaEjecucionYDni(fechaDesdeTs, fechaHastaTs, dni, con);
                } else {
                    registros = es.altia.flexia.integracion.moduloexterno.melanbide_interop.dao.InteropCvlMasivoNifDAO
                            .getInstance().getRegistrosByFechaEjecucion(fechaDesdeTs, fechaHastaTs, con);
                }

                total = registros != null ? registros.size() : 0;
                if (registros != null) {
                    for (int i = 0; i < registros.size(); i++) {
                        final es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.InteropCvlMasivoNifVO registro = registros.get(i);
                        final java.util.Map<String, Object> fila = new java.util.LinkedHashMap<String, Object>();
                        fila.put("id", registro.getId());
                        fila.put("fechaEjecucion", registro.getFechaEjecucion());
                        fila.put("dni", registro.getNif());
                        fila.put("tipoDoc", registro.getTipoDoc());
                        fila.put("codRespuesta", registro.getCodRespuesta());
                        fila.put("descRespuesta", registro.getDescRespuesta());
                        fila.put("numExpediente", registro.getNumExpediente());
                        fila.put("procedimiento", registro.getCodTramite());
                        fila.put("ocurrenciaTramite", registro.getOcurrenciaTramite());
                        fila.put("codOrganizacion", registro.getCodOrganizacion());
                        fila.put("fkWSSolicitado", registro.getFkWSSolicitado());
                        fila.put("fechaDesdeCVL", registro.getFechaDesdeCVL());
                        fila.put("fechaHastaCVL", registro.getFechaHastaCVL());
                        fila.put("payloadResumen", registro.getPayloadResumen());
                        fila.put("usuario", registro.getUsuario());
                        registrosSalida.add(fila);
                    }
                }
                resultado = "OK";
            }
        } catch (Exception ex) {
            codigoOperacion = "2";
            resultado = "Error consultando CVL masivo: " + ex.getMessage();
            log.error("Error en consultarCvlMasivoPorFechas", ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    log.error("Error cerrando conexion consultarCvlMasivoPorFechas", ex);
                }
            }
        }

        final java.util.Map<String, Object> salida = new java.util.LinkedHashMap<String, Object>();
        salida.put("codigoOperacion", codigoOperacion);
        salida.put("resultado", resultado);
        salida.put("total", Integer.valueOf(total));
        salida.put("registros", registrosSalida);
        try {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.serializeNulls();
            final Gson gson = gsonBuilder.create();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            final PrintWriter out = response.getWriter();
            out.print(gson.toJson(salida));
            out.flush();
            out.close();
        } catch (Exception ex) {
            log.error("Error preparando response consultarCvlMasivoPorFechas", ex);
        }
    }

    private Timestamp parsearFechaConsultaCvlMasivo(final String fecha, final boolean finDia) {
        if (fecha == null || fecha.trim().length() == 0) {
            return null;
        }
        final String valor = fecha.trim();
        final String[] formatos = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy"};
        for (int i = 0; i < formatos.length; i++) {
            try {
                final SimpleDateFormat sdf = new SimpleDateFormat(formatos[i]);
                sdf.setLenient(false);
                final Date parsed = sdf.parse(valor);
                final Calendar cal = Calendar.getInstance();
                cal.setTime(parsed);
                if (formatos[i].indexOf("HH:mm:ss") < 0) {
                    if (finDia) {
                        cal.set(Calendar.HOUR_OF_DAY, 23);
                        cal.set(Calendar.MINUTE, 59);
                        cal.set(Calendar.SECOND, 59);
                        cal.set(Calendar.MILLISECOND, 999);
                    } else {
                        cal.set(Calendar.HOUR_OF_DAY, 0);
                        cal.set(Calendar.MINUTE, 0);
                        cal.set(Calendar.SECOND, 0);
                        cal.set(Calendar.MILLISECOND, 0);
                    }
                }
                return new Timestamp(cal.getTimeInMillis());
            } catch (Exception ex) {
                log.debug("parsearFechaConsultaCvlMasivo - Formato no valido para fecha '" + valor + "': " + formatos[i]);
            }
        }
        return null;
    }

    /**
     * PRUEBA E2E — PASO 6 (Servidor): Convierte un fichero Excel en base64 a una lista CSV de documentos.
     *
     * <h3>Entrada:</h3>
     * <pre>
     *   excelBase64 = "UEsDBBQA..."  (base64 del fichero prueba_cvl_masivo.xlsx)
     * </pre>
     *
     * <h3>Contenido del Excel (primera hoja):</h3>
     * <pre>
     *   Columna A (índice 0) | Columna B (índice 1)
     *   ---------------------|--------------------
     *   TIPO_DOC             | DOCUMENTO          <- fila cabecera -> se descarta
     *   NIF                  | 12345678A
     *   NIE                  | X1234567L
     *   NIF                  | 87654321B
     * </pre>
     *
     * <h3>Procesamiento fila a fila:</h3>
     * <ul>
     *   <li>Fila 0: valor0="TIPO_DOC", valor1="DOCUMENTO" -> esCabeceraFilaDocumento=true -> se salta</li>
     *   <li>Fila 1: valor0="NIF"(esTipoDocumento=true), valor1="12345678A"(esTipoDocumento=false)
     *       -> documento="12345678A", tipoDocumento="NIF" -> añade "12345678A;NIF"</li>
     *   <li>Fila 2: valor0="NIE"(esTipoDocumento=true), valor1="X1234567L"(esTipoDocumento=false)
     *       -> documento="X1234567L", tipoDocumento="NIE" -> añade "X1234567L;NIE"</li>
     *   <li>Fila 3: valor0="NIF"(esTipoDocumento=true), valor1="87654321B"(esTipoDocumento=false)
     *       -> documento="87654321B", tipoDocumento="NIF" -> añade "87654321B;NIF"</li>
     * </ul>
     *
     * <h3>Salida:</h3>
     * <pre>
     *   "12345678A;NIF\nX1234567L;NIE\n87654321B;NIF"
     * </pre>
     *
     * @param excelBase64 Contenido del fichero Excel (.xls / .xlsx) codificado en base64.
     * @return String con las líneas "DOCUMENTO;TIPO_DOC" separadas por saltos de línea.
     * @throws Exception Si el base64 no es válido o el fichero no es un Excel reconocible.
     */
    private String convertirExcelBase64AListaDocs(final String excelBase64) throws Exception {
        Workbook workbook = null;
        InputStream inputStream = null;
        final StringBuilder salida = new StringBuilder();
        try {
            final byte[] excelBytes = DatatypeConverter.parseBase64Binary(excelBase64);
            inputStream = new ByteArrayInputStream(excelBytes);
            workbook = WorkbookFactory.create(inputStream);
            final Sheet hoja = workbook.getNumberOfSheets() > 0 ? workbook.getSheetAt(0) : null;
            final DataFormatter dataFormatter = new DataFormatter();

            if (hoja == null) {
                return "";
            }

            for (int i = hoja.getFirstRowNum(); i <= hoja.getLastRowNum(); i++) {
                final Row fila = hoja.getRow(i);
                if (fila == null) {
                    continue;
                }

                final String valor0 = normalizarCeldaExcel(fila.getCell(0), dataFormatter);
                final String valor1 = normalizarCeldaExcel(fila.getCell(1), dataFormatter);

                if (valor0.length() == 0 && valor1.length() == 0) {
                    continue;
                }

                if (esCabeceraFilaDocumento(valor0, valor1)) {
                    continue;
                }

                String documento = valor0;
                String tipoDocumento = valor1;

                final boolean valor0EsTipo = esTipoDocumento(valor0);
                final boolean valor1EsTipo = esTipoDocumento(valor1);
                final boolean valor0PareceDocumento = pareceDocumento(valor0);
                final boolean valor1PareceDocumento = pareceDocumento(valor1);

                if (valor0EsTipo && !valor1EsTipo) {
                    documento = valor1;
                    tipoDocumento = valor0;
                } else if (valor1EsTipo && !valor0EsTipo) {
                    documento = valor0;
                    tipoDocumento = valor1;
                } else if (valor1PareceDocumento && !valor0PareceDocumento) {
                    documento = valor1;
                    tipoDocumento = valor0;
                }

                documento = documento != null ? documento.replaceAll("\\s+", "").toUpperCase() : "";
                tipoDocumento = tipoDocumento != null ? tipoDocumento.trim().toUpperCase() : "";

                if (documento.length() == 0) {
                    continue;
                }

                if (tipoDocumento.length() == 0) {
                    tipoDocumento = "NIF";
                }

                if (salida.length() > 0) {
                    salida.append('\n');
                }
                salida.append(documento).append(';').append(tipoDocumento);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return salida.toString();
    }

    private String normalizarCeldaExcel(final Cell celda, final DataFormatter dataFormatter) {
        if (celda == null) {
            return "";
        }
        final String valor = dataFormatter.formatCellValue(celda);
        if (valor == null) {
            return "";
        }
        return valor.replace('\u00A0', ' ').trim();
    }

    private boolean esCabeceraFilaDocumento(final String valor0, final String valor1) {
        final String campo0 = valor0 != null ? valor0.trim().toUpperCase() : "";
        final String campo1 = valor1 != null ? valor1.trim().toUpperCase() : "";
        return (esCabeceraTipoDoc(campo0) && esCabeceraDocumento(campo1))
                || (esCabeceraTipoDoc(campo1) && esCabeceraDocumento(campo0));
    }

    private boolean esCabeceraTipoDoc(final String valor) {
        return "TIPO_DOC".equals(valor)
                || "TIPODOC".equals(valor)
                || "TIPO DOCUMENTO".equals(valor)
                || "TIPO".equals(valor);
    }

    private boolean esCabeceraDocumento(final String valor) {
        return "DOCUMENTO".equals(valor)
                || "NIF".equals(valor)
                || "NIE".equals(valor);
    }

    private boolean esTipoDocumento(final String valor) {
        if (valor == null) {
            return false;
        }
        final String tipo = valor.trim().toUpperCase();
        return "NIF".equals(tipo)
                || "NIE".equals(tipo)
                || "DNI".equals(tipo)
                || "CIF".equals(tipo)
                || "PASAPORTE".equals(tipo)
                || "PAS".equals(tipo)
                || "ID".equals(tipo)
                || "TIE".equals(tipo);
    }

    private boolean pareceDocumento(final String valor) {
        if (valor == null) {
            return false;
        }
        final String documento = valor.trim().toUpperCase();
        if (documento.length() < 5) {
            return false;
        }
        for (int i = 0; i < documento.length(); i++) {
            if (Character.isDigit(documento.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
