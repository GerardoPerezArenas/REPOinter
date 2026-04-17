
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager;

import es.altia.agora.technical.ConstantesDatos;
import es.altia.common.exception.TechnicalException;
import es.altia.technical.PortableContext;
import es.altia.util.conexion.AdaptadorSQLBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author INGDGC
 */
public class InteropJobsUtils {
    
    private static final Logger log = Logger.getLogger(InteropJobsUtils.class);
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    
    // CONFIGURACION PARA EJECUCION PROCESOS JOBS - BATCH
    public final String FICHERO_PROPIEDADES = "MELANBIDE_INTEROP";
    public final String SERVIDOR = "SERVIDOR";
    public final String ORGANIZACION_ESQUEMA = "ORGANIZACION_ESQUEMA";
    public final String DOS_ENTORNOS = "DOS_ENTORNOS";
    public final String FSE_CODIGO_CENTRO = "FSE_CODIGO_CENTRO";
    public final String FSE_CODIGO_UBICACION_CENTRO = "FSE_CODIGO_UBICACION_CENTRO";
    public final String FSE_CENTRO_SERVICIO = "FSE_CENTRO_SERVICIO";
    public final String FSE_TIPO_DOC_USUARIO_LLAMADA = "FSE_TIPO_DOC_USUARIO_LLAMADA";
    public final String FSE_IDENTIFICADOR_USUARIO_LLAMADA = "FSE_IDENTIFICADOR_USUARIO_LLAMADA";
    public final String FSE_CONSTANTE_ORIGEN_ALTASERVICIO = "FSE_CONSTANTE_ORIGEN_ALTASERVICIO";
    public final String FSE_CODIGO_RESULTADO_FAVORABLE = "FSE_CODIGO_RESULTADO_FAVORABLE";
    public final String FSE_CODIGO_CAMPOSUPLE_DOCUMENTO_PERSONA = "FSE_CODIGO_CAMPOSUPLE_DOCUMENTO_PERSONA";
    public final String FSE_IDENTIFICADOR_ORIENTADOR = "FSE_IDENTIFICADOR_ORIENTADOR";
    public final String FSE_DEM_SERVS_SERVICIO = "FSE_DEM_SERVS_SERVICIO";
    public final String FSE_DEM_SERVS_SUBSERVICIO = "FSE_DEM_SERVS_SUBSERVICIO";
    public final String BARRA_SEPARADORA = "/";
    
    public AdaptadorSQLBD getAdaptSQLBD(String codOrganizacion) {
        log.info("getConnection ( codOrganizacion = " + codOrganizacion + " ) : BEGIN");
        ResourceBundle config = ResourceBundle.getBundle("techserver");
        String gestor = config.getString("CON.gestor");
        String jndiGenerico = config.getString("CON.jndi");
        Connection conGenerico = null;
        Statement st = null;
        ResultSet rs = null;
        String[] salida = null;
        Connection con = null;
        log.info("getJndi =========> ");
        log.info("parametro codOrganizacion: " + codOrganizacion);
        log.info("gestor: " + gestor);
        log.info("jndi: " + jndiGenerico);
        DataSource ds = null;
        AdaptadorSQLBD adapt = null;
        synchronized (this) {
            try {
                PortableContext pc = PortableContext.getInstance();
                if (log.isDebugEnabled()) {
                    log.info("He cogido el jndi: " + jndiGenerico);
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
                log.error("*** AdaptadorSQLBD: ", te);
            } catch (SQLException e) {
                e.printStackTrace();
                log.error("*** AdaptadorSQLBD: ", e);
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
                    log.error("*** AdaptadorSQLBD: ", e);
                }
            }
            log.info("getConnection() : END");
        }// synchronized
        return adapt;
    }
    
    /**
     * Metodo que recupera la propiedad de que le indicamos como parametro. Ademas le enviamos el nombre del fichero de propiedades.
     * 
     * @param property
     * @param FICHERO_CONFIGURACION
     * @return 
     */
    public String getParameter(String property,String FICHERO_CONFIGURACION){
        String valor = "";
        try{
            ResourceBundle bundle = ResourceBundle.getBundle(FICHERO_CONFIGURACION);
            valor = bundle.getString(property);
        }catch(Exception e){
            e.printStackTrace();
            log.error("Error recuperando Propiedad " + property + " desde " + FICHERO_CONFIGURACION, e);
        }
        return valor;
    }
    
    public String getCodProcedimientoFromNumExpediente(String numExpediente) {
        String returnValue = "";
        log.debug("getCodProcedimientoFromNumExpediente - Begin()" + numExpediente);
        if (numExpediente != null && !numExpediente.isEmpty()) {
            try {
                String[] arreglo = numExpediente.split(BARRA_SEPARADORA);
                log.debug("Tamano arreglo obtenido del Num. Expediente :" + arreglo.length);
                returnValue = arreglo[1];
            } catch (Exception e) {
                log.error("Error al obtener el codgo de procedimiento desde el numero de expediente, retornamos null", e);
                returnValue = null;
            }
        }
        log.debug("getCodProcedimientoFromNumExpediente - End()" + returnValue);
        return returnValue;
    }

    public String getDetalleRespuestaWS(String respuestaServicio) {
        String respuesta="";
        try {
            if(respuestaServicio!=null && !respuestaServicio.isEmpty()){
                String listaCodigoRespuesta[] = respuestaServicio.split(",");
                for (String codigoRespuesta : listaCodigoRespuesta) {
                    respuesta += (respuesta.isEmpty() ? "" : ", ") +
                            codigoRespuesta + ": " +getParameter(codigoRespuesta, "es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.NLANGAI_GEN_ERRORES_WS");
                }
            }else{
                respuesta="Codigos de respuesta del WS recibidos a Null o vacios.";
            }
        } catch (Exception e) {
            log.error("Error al traducir el codigo de respuesta...",e);
            respuesta="Los codigos de respuesta no se han podido Traducir, solicitar informacion al equipo de intermediacion esquema NLANGAI tabla GEN_ERRORES_WS: " + respuestaServicio;
        }
        return respuesta;
    }
}
