/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.dao.InteropJobsFSEProcediDAO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.dao.InteropJobsLogDAO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEExpedienteRequest;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEProcedi;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsLog;
import es.altia.util.conexion.AdaptadorSQLBD;
import es.altia.util.conexion.BDException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author INGDGC
 */
public class InteropJobsLogManager {
    
    private static final Logger log = Logger.getLogger(InteropJobsLogManager.class);
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private final InteropJobsLogDAO interopJobsLogDAO = new InteropJobsLogDAO();
    private final InteropJobsFSEProcediDAO interopJobsFSEProcediDAO = new InteropJobsFSEProcediDAO();
    
    private static InteropJobsLogManager instance = null;

    public static InteropJobsLogManager getInstance() {
        if (instance == null) {
            synchronized (InteropJobsLogManager.class) {
                instance = new InteropJobsLogManager();
            }
        }
        return instance;
    }    
    
    public InteropJobsLog getInteropJobsLogById(long id, Connection con) throws SQLException, Exception {
        log.info(" getInteropJobsLogById - Begin " + id + formatFechaLog.format(new Date()));
        return interopJobsLogDAO.getInteropJobsLogById(id, con);
    }
    public InteropJobsLog getInteropJobsLogById(long id, AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" getInteropJobsLogById - Begin " + id + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.getInteropJobsLogById(id, con);
        } catch (BDException e) {
            log.error("BDException recuperando Datos ", e);
            throw new Exception(e);
        } catch (Exception ex) {
            log.error("Exception recuperando Datos ", ex);
            throw new Exception(ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }

    public List<InteropJobsLog> getInteropJobsLogByDocumentoId(String documentId,Connection con) throws SQLException, Exception {
        log.info(" getInteropJobsLogByDocumentoId - Begin " + formatFechaLog.format(new Date()));
        return interopJobsLogDAO.getInteropJobsLogByDocumentoId(documentId, con);
    }
    public List<InteropJobsLog> getInteropJobsLogByDocumentoId(String documentId,AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" getInteropJobsLogByDocumentoId - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.getInteropJobsLogByDocumentoId(documentId, con);
        } catch (BDException e) {
            log.error("BDException recuperando Datos ", e);
            throw new Exception(e);
        } catch (Exception ex) {
            log.error("Exception recuperando Datos ", ex);
            throw new Exception(ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }
    
    public List<InteropJobsLog> getInteropJobsLogBynumeroExpediente(String numeroExpediente,Connection con) throws SQLException, Exception {
        log.info(" getInteropJobsLogBynumeroExpediente - Begin " + formatFechaLog.format(new Date()));
        return interopJobsLogDAO.getInteropJobsLogBynumeroExpediente(numeroExpediente, con);
    }
    public List<InteropJobsLog> getInteropJobsLogBynumeroExpediente(String numeroExpediente,AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" getInteropJobsLogBynumeroExpediente - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.getInteropJobsLogBynumeroExpediente(numeroExpediente, con);
        } catch (BDException e) {
            log.error("BDException recuperando Datos ", e);
            throw new Exception(e);
        } catch (Exception ex) {
            log.error("Exception recuperando Datos ", ex);
            throw new Exception(ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }
    
    public InteropJobsLog crearLineaInteropJobsLog(InteropJobsLog interopJobsLog, Connection con) throws SQLException, Exception {
        log.info(" crearLineaInteropJobsLog - Begin " + formatFechaLog.format(new Date()));
        return interopJobsLogDAO.crearLineaInteropJobsLog(interopJobsLog, con);
    }
    public InteropJobsLog crearLineaInteropJobsLog(InteropJobsLog interopJobsLog, AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" crearLineaInteropJobsLog - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.crearLineaInteropJobsLog(interopJobsLog, con);
        } catch (BDException e) {
            log.error("BDException Creando Linea Datos ", e);
            throw new Exception(e);
        } catch (Exception ex) {
            log.error("Exception creando Linea Datos ", ex);
            throw new Exception(ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }
    
    public boolean actualizarLineaInteropJobsLog(InteropJobsLog interopJobsLog, Connection con) throws SQLException, Exception {
        log.info(" actualizarLineaInteropJobsLog - Begin " + formatFechaLog.format(new Date()));
        return interopJobsLogDAO.actualizarLineaInteropJobsLog(interopJobsLog, con);
    }
    public boolean actualizarLineaInteropJobsLog(InteropJobsLog interopJobsLog, AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" actualizarLineaInteropJobsLog - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.actualizarLineaInteropJobsLog(interopJobsLog, con);
        } catch (BDException e) {
            log.error("BDException Actualizando Linea Datos ", e);
            throw new Exception(e);
        } catch (Exception ex) {
            log.error("Exception Actualizando Linea Datos ", ex);
            throw new Exception(ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }
    
    public boolean eliminarLineaInteropJobsLogByID(long interopJobsLog, Connection con) throws SQLException, Exception {
        log.info(" eliminarLineaInteropJobsLogByID - Begin " + formatFechaLog.format(new Date()));
        return interopJobsLogDAO.eliminarLineaInteropJobsLogByID(interopJobsLog, con);
    }
    public boolean eliminarLineaInteropJobsLogByID(long interopJobsLog, AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" eliminarLineaInteropJobsLogByID - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.eliminarLineaInteropJobsLogByID(interopJobsLog, con);
        } catch (BDException e) {
            log.error("BDException Eliminando Linea Datos ", e);
            throw new Exception(e);
        } catch (Exception ex) {
            log.error("Exception Eliminando Linea Datos ", ex);
            throw new Exception(ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }
    
    public List<InteropJobsFSEProcedi> getProcedimientosProcesarFSE(Connection con) throws SQLException, Exception {
        log.info(" getProcedimientosProcesarFSE - Begin " + formatFechaLog.format(new Date()));
        return interopJobsFSEProcediDAO.getProcedimientosProcesarFSE(con);
    }
    public List<InteropJobsFSEProcedi> getProcedimientosProcesarFSE(AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" getProcedimientosProcesarFSE - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.getProcedimientosProcesarFSE(con);
        } catch (BDException e) {
            log.error("BDException Recuperando Linea Datos ", e);
            throw new Exception(e);
        } catch (Exception ex) {
            log.error("Exception Recuperando Linea Datos ", ex);
            throw new Exception(ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }
    
    public boolean actualizarEstadoInteropJobsFSEProcedi(InteropJobsFSEProcedi interopJobsFSEProcedi,Connection con) throws SQLException, Exception {
        log.info(" actualizarEstadoInteropJobsFSEProcedi - Begin " + formatFechaLog.format(new Date()));
        return interopJobsFSEProcediDAO.actualizarEstadoInteropJobsFSEProcedi(interopJobsFSEProcedi,con);
    }
    public boolean actualizarEstadoInteropJobsFSEProcedi(InteropJobsFSEProcedi interopJobsFSEProcedi,AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" actualizarEstadoInteropJobsFSEProcedi - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.actualizarEstadoInteropJobsFSEProcedi(interopJobsFSEProcedi,con);
        } catch (BDException e) {
            log.error("BDException Actualizando Linea Datos ", e);
            throw new Exception(e);
        } catch (Exception ex) {
            log.error("Exception Actualizando Linea Datos ", ex);
            throw new Exception(ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }

    public List<InteropJobsFSEExpedienteRequest> getExpedientesTratarFSEByProcedimiento(InteropJobsFSEProcedi procedimiento, Connection con) throws Exception {
        log.info(" getExpedientesTratarFSEByProcedimiento - Begin " + formatFechaLog.format(new Date()));
        return interopJobsFSEProcediDAO.getExpedientesTratarFSEByProcedimiento(procedimiento, con);
    }
    public List<InteropJobsFSEExpedienteRequest> getExpedientesTratarFSEByProcedimiento(InteropJobsFSEProcedi procedimiento, AdaptadorSQLBD adaptador) throws Exception {
        log.info(" getExpedientesTratarFSEByProcedimiento - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.getExpedientesTratarFSEByProcedimiento(procedimiento, con);
        } catch (BDException e) {
            log.error("BDException Recogiendo Datos ", e);
            throw new Exception(e);
        } catch (Exception ex) {
            log.error("Exception Recogiendo Datos ", ex);
            throw new Exception(ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }
    
}
