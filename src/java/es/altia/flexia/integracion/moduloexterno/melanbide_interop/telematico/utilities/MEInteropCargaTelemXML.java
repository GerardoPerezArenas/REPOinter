/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.altia.agora.technical.ConstantesDatos;
import es.altia.common.exception.TechnicalException;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores.ErrorLan6ExcepcionBeanNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores.GestionarErroresDokusiNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.ComboNisae;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.GestionServiciosNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.dao.MEInteropCargaTelemXMLServiceDAO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.dto.MEInteropCargaTelemXMLExpediente;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.dto.MEInteropCargaTelemXMLParameters;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.services.MEInteropCargaTelemXMLService;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;
import es.altia.flexia.integracion.moduloexterno.plugin.ModuloIntegracionExterno;
import es.altia.technical.PortableContext;
import es.altia.util.conexion.AdaptadorSQLBD;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6ErrorBean;
import java.text.SimpleDateFormat;



/**
 *
 * @author davidg
 */
public class MEInteropCargaTelemXML extends ModuloIntegracionExterno {
    
    private static Logger log = Logger.getLogger(MEInteropCargaTelemXML.class);   
    ResourceBundle commonConf = ResourceBundle.getBundle("common");
    SimpleDateFormat formatDateLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private GestionServiciosNISAE gestionServiciosNISAE = new GestionServiciosNISAE();
    private final MEInteropCargaTelemXMLServiceDAO mEInteropCargaTelemXMLServiceDAO = new MEInteropCargaTelemXMLServiceDAO();
    
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
            List<MEInteropCargaTelemXMLExpediente> listaExpedientes = mEInteropCargaTelemXMLServiceDAO.getExpedientesAndDocumentosProcesar(codOrganizacion,mEInteropCargaTelemXMLParameters,adapt);
            cantidadExpedientesProcesar = (listaExpedientes!=null ? listaExpedientes.size() : 0);

            // Inciamos proceso segundo plano
            log.info("Llamar  THREAD MEInteropCargaTelemXMLService");
            MEInteropCargaTelemXMLService ejecutaThread = new MEInteropCargaTelemXMLService();
            ejecutaThread.start(codOrganizacion, mEInteropCargaTelemXMLParameters,listaExpedientes, adapt);
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
                String trazaError = (ex.getStackTrace()!=null? Arrays.toString(ex.getStackTrace()):"");  

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
                + " Ejercicio : " + (mEInteropCargaTelemXMLParameters.getEjercicio() != null  ? mEInteropCargaTelemXMLParameters.getEjercicio() : "")
                + " Procedimiento : " + (mEInteropCargaTelemXMLParameters.getProcedimiento() != null && !mEInteropCargaTelemXMLParameters.getProcedimiento().isEmpty() ? mEInteropCargaTelemXMLParameters.getProcedimiento() : "")
                + " Numero Expediente Desde : " + (mEInteropCargaTelemXMLParameters.getNumeroExpedienteDesde()!= null && !mEInteropCargaTelemXMLParameters.getNumeroExpedienteDesde().isEmpty() ? mEInteropCargaTelemXMLParameters.getNumeroExpedienteDesde() : "")
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
    
    private AdaptadorSQLBD getAdaptSQLBD(String codOrganizacion) {
        if (log.isDebugEnabled()) {
            log.error("getConnection ( codOrganizacion = " + codOrganizacion + " ) : BEGIN");
        }
        ResourceBundle config = ResourceBundle.getBundle("techserver");
        String gestor = config.getString("CON.gestor");
        String jndiGenerico = config.getString("CON.jndi");
        Connection conGenerico = null;
        Statement st = null;
        ResultSet rs = null;
        String[] salida = null;
        Connection con = null;

        if (log.isDebugEnabled()) {
            log.error("getJndi =========> ");
            log.error("parametro codOrganizacion: " + codOrganizacion);
            log.error("gestor: " + gestor);
            log.error("jndi: " + jndiGenerico);
        }//if(log.isDebugEnabled())

        DataSource ds = null;
        AdaptadorSQLBD adapt = null;
        synchronized (this) {
            try {
                PortableContext pc = PortableContext.getInstance();
                if (log.isDebugEnabled()) {
                    log.error("He cogido el jndi: " + jndiGenerico);
                }
                ds = (DataSource) pc.lookup(jndiGenerico, DataSource.class);
                // Conexión al esquema genérico
                conGenerico = ds.getConnection();

                String sql = "SELECT EEA_BDE FROM A_EEA WHERE EEA_APL=" + ConstantesDatos.APP_GESTION_EXPEDIENTES + " AND AAE_ORG=" + codOrganizacion;
                st = conGenerico.createStatement();
                rs = st.executeQuery(sql);
                String jndi = null;
                while (rs.next()) {
                    jndi = rs.getString("EEA_BDE");
                }//while(rs.next())

                st.close();
                rs.close();
                conGenerico.close();

                if (jndi != null && gestor != null && !"".equals(jndi) && !"".equals(gestor)) {
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
            } catch (TechnicalException te) {
                te.printStackTrace();
                log.error("*** AdaptadorSQLBD: " + te.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                    if (conGenerico != null && !conGenerico.isClosed()) {
                        conGenerico.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }//try-catch
            }// finally
            if (log.isDebugEnabled()) {
                log.error("getConnection() : END");
            }
        }// synchronized
        return adapt;
    }//getConnection
    
}
