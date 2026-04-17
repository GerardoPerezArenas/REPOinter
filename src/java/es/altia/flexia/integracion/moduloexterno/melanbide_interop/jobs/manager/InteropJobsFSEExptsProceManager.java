/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEExptsProce;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.dao.InteropJobsFSEExptsProceDAO;
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
public class InteropJobsFSEExptsProceManager {
    
    private static final Logger log = Logger.getLogger(InteropJobsFSEExptsProceManager.class);
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private final InteropJobsFSEExptsProceDAO interopJobsFSEExptsProceDAO = new InteropJobsFSEExptsProceDAO();
    
    public InteropJobsFSEExptsProce getInteropJobsFSEExptsProceById(long id, Connection con) throws SQLException, Exception {
        log.info(" getInteropJobsFSEExptsProceById - Begin " + id + formatFechaLog.format(new Date()));
        return interopJobsFSEExptsProceDAO.getInteropJobsFSEExptsProceById(id, con);
    }
    public InteropJobsFSEExptsProce getInteropJobsFSEExptsProceById(long id, AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" getInteropJobsFSEExptsProceById - Begin " + id + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.getInteropJobsFSEExptsProceById(id, con);
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

    public List<InteropJobsFSEExptsProce> getInteropJobsFSEExptsProceByNumeroExpediente(String numeroExpediente,Connection con) throws SQLException, Exception {
        log.info(" getInteropJobsFSEExptsProceByNumeroExpediente - Begin " + formatFechaLog.format(new Date()));
        return interopJobsFSEExptsProceDAO.getInteropJobsFSEExptsProceByNumeroExpediente(numeroExpediente, con);
    }
    public List<InteropJobsFSEExptsProce> getInteropJobsFSEExptsProceByNumeroExpediente(String numeroExpediente,AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" getInteropJobsFSEExptsProceByNumeroExpediente - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.getInteropJobsFSEExptsProceByNumeroExpediente(numeroExpediente, con);
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
    
    public InteropJobsFSEExptsProce getInteropJobsFSEExptsProceByNumeroExpedienteAndNombreJob(String numeroExpediente,String nombreJob,Connection con) throws SQLException, Exception {
        log.info(" getInteropJobsFSEExptsProceByNumeroExpedienteAndNombreJob - Begin " + formatFechaLog.format(new Date()));
        return interopJobsFSEExptsProceDAO.getInteropJobsFSEExptsProceByNumeroExpedienteAndNombreJob(numeroExpediente,nombreJob, con);
    }
    public InteropJobsFSEExptsProce getInteropJobsFSEExptsProceByNumeroExpedienteAndNombreJob(String numeroExpediente,String nombreJob,AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" getInteropJobsFSEExptsProceByNumeroExpedienteAndNombreJob - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.getInteropJobsFSEExptsProceByNumeroExpedienteAndNombreJob(numeroExpediente,nombreJob, con);
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
    
    public InteropJobsFSEExptsProce crearLineaInteropJobsFSEExptsProce(InteropJobsFSEExptsProce interopJobsLog, Connection con) throws SQLException, Exception {
        log.info(" crearLineaInteropJobsFSEExptsProce - Begin " + formatFechaLog.format(new Date()));
        return interopJobsFSEExptsProceDAO.crearLineaInteropJobsFSEExptsProce(interopJobsLog, con);
    }
    public InteropJobsFSEExptsProce crearLineaInteropJobsFSEExptsProce(InteropJobsFSEExptsProce interopJobsLog, AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" crearLineaInteropJobsFSEExptsProce - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.crearLineaInteropJobsFSEExptsProce(interopJobsLog, con);
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
    
    public boolean actualizarLineaInteropJobsFSEExptsProce(InteropJobsFSEExptsProce interopJobsLog, Connection con) throws SQLException, Exception {
        log.info(" actualizarLineaInteropJobsFSEExptsProce - Begin " + formatFechaLog.format(new Date()));
        return interopJobsFSEExptsProceDAO.actualizarLineaInteropJobsFSEExptsProce(interopJobsLog, con);
    }
    public boolean actualizarLineaInteropJobsFSEExptsProce(InteropJobsFSEExptsProce interopJobsLog, AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" actualizarLineaInteropJobsFSEExptsProce - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.actualizarLineaInteropJobsFSEExptsProce(interopJobsLog, con);
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
    
    public boolean eliminarLineaInteropJobsFSEExptsProceByID(long interopJobsLog, Connection con) throws SQLException, Exception {
        log.info(" eliminarLineaInteropJobsFSEExptsProceByID - Begin " + formatFechaLog.format(new Date()));
        return interopJobsFSEExptsProceDAO.eliminarLineaInteropJobsFSEExptsProceByID(interopJobsLog, con);
    }
    public boolean eliminarLineaInteropJobsFSEExptsProceByID(long interopJobsLog, AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" eliminarLineaInteropJobsFSEExptsProceByID - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.eliminarLineaInteropJobsFSEExptsProceByID(interopJobsLog, con);
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
    
}
