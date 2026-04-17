/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.dao;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEExptsProce;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author INGDGC
 */
public class InteropJobsFSEExptsProceDAO {
    
    private static final Logger log = Logger.getLogger(InteropJobsFSEExptsProceDAO.class);
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private final InteropJobsDaoMapping interopJobsDaoMapping = new InteropJobsDaoMapping();
    
    private long getNextIdFromSEQ_INTEROP_JOBS_FSE_EXP_PRO(Connection con) throws SQLException, Exception{
        log.info(" getNextIdFromSEQ_INTEROP_JOBS_FSE_EXP_PRO - Begin "  + formatFechaLog.format(new Date()));
        long resultado = -1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select SEQ_INTEROP_JOBS_FSE_EXP_PRO.nextval id from dual";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado = rs.getLong("id");
            }
        } catch (SQLException e) {
            log.error("getNextIdFromSEQ_INTEROP_JOBS_FSE_EXP_PRO  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getNextIdFromSEQ_INTEROP_JOBS_FSE_EXP_PRO - Exception ", e);
            throw e;
        } finally {
            log.debug("Procedemos a cerrar el resultset");
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" getNextIdFromSEQ_INTEROP_JOBS_FSE_EXP_PRO - End " + resultado + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    public InteropJobsFSEExptsProce getInteropJobsFSEExptsProceById(Long id, Connection con) throws SQLException, Exception {
        log.info(" getInteropJobsFSEExptsProceById - Begin " + id + formatFechaLog.format(new Date()));
        InteropJobsFSEExptsProce resultado = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * "
                    + " from INTEROP_JOBS_FSE_EXPTS_PROCE "
                    + " where "
                    + " id=? ";
            log.info("sql = " + query);
            int contadorParam = 1;
            ps = con.prepareStatement(query);
            ps.setLong(contadorParam++, id);
            log.info("params = " + id);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado = interopJobsDaoMapping.getInteropJobsFSEExptsProce(rs);
            }
        } catch (SQLException e) {
            log.error("getInteropJobsFSEExptsProceById  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getInteropJobsFSEExptsProceById - Exception ", e);
            throw e;
        } finally {
            log.debug("Procedemos a cerrar el resultset");
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" getInteropJobsFSEExptsProceById - End " + id + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    public List<InteropJobsFSEExptsProce> getInteropJobsFSEExptsProceByNumeroExpediente(String numeroExpediente, Connection con) throws SQLException, Exception {
        log.info(" getInteropJobsFSEExptsProceByNumeroExpediente - Begin " + numeroExpediente + formatFechaLog.format(new Date()));
        List<InteropJobsFSEExptsProce> resultado = new ArrayList<InteropJobsFSEExptsProce>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * "
                    + " from INTEROP_JOBS_FSE_EXPTS_PROCE "
                    + " where "
                    + " numeroExpediente=? ";
            log.info("sql = " + query);
            int contadorParam = 1;
            ps = con.prepareStatement(query);
            ps.setString(contadorParam++, numeroExpediente);
            log.info("params = " + numeroExpediente);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(
                        interopJobsDaoMapping.getInteropJobsFSEExptsProce(rs)
                );
            }
        } catch (SQLException e) {
            log.error("getInteropJobsFSEExptsProceByNumeroExpediente  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getInteropJobsFSEExptsProceByNumeroExpediente - Exception ", e);
            throw e;
        } finally {
            log.debug("Procedemos a cerrar el resultset");
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" getInteropJobsFSEExptsProceByNumeroExpediente - End " + numeroExpediente + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    public InteropJobsFSEExptsProce getInteropJobsFSEExptsProceByNumeroExpedienteAndNombreJob(String numeroExpediente,String nombreJob, Connection con) throws SQLException, Exception {
        log.info(" getInteropJobsFSEExptsProceByNumeroExpedienteAndNombreJob - Begin " + numeroExpediente + formatFechaLog.format(new Date()));
        InteropJobsFSEExptsProce resultado = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * "
                    + " from INTEROP_JOBS_FSE_EXPTS_PROCE "
                    + " where "
                    + " numeroExpediente=? "
                    + " and nombreProcesoJobBatch=? ";
            log.info("sql = " + query);
            int contadorParam = 1;
            ps = con.prepareStatement(query);
            ps.setString(contadorParam++, numeroExpediente);
            ps.setString(contadorParam++, nombreJob);
            log.info("params = " + numeroExpediente 
                    +","+nombreJob
            );
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado=interopJobsDaoMapping.getInteropJobsFSEExptsProce(rs);
            }
        } catch (SQLException e) {
            log.error("getInteropJobsFSEExptsProceByNumeroExpedienteAndNombreJob  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getInteropJobsFSEExptsProceByNumeroExpedienteAndNombreJob - Exception ", e);
            throw e;
        } finally {
            log.debug("Procedemos a cerrar el resultset");
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" getInteropJobsFSEExptsProceByNumeroExpedienteAndNombreJob - End " + numeroExpediente + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    public InteropJobsFSEExptsProce crearLineaInteropJobsFSEExptsProce(InteropJobsFSEExptsProce interopJobsFSEExptsProce, Connection con) throws SQLException, Exception {
        log.info(" crearLineaInteropJobsFSEExptsProce - Begin " + formatFechaLog.format(new Date()));
        InteropJobsFSEExptsProce resultado = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (interopJobsFSEExptsProce != null) {
                String query = "";
                int contadorParam = 1;
                query = " insert into INTEROP_JOBS_FSE_EXPTS_PROCE "
                        + "("
                        + " id "
                        + " ,nombreProcesoJobBatch "
                        + " ,numeroExpediente  "
                        + " ,observaciones  "
                        + ")"
                        + " values"
                        + "("
                        + " ?,?,?,?"
                        + ") ";
                log.info("sql = " + query);
                ps = con.prepareStatement(query);
                interopJobsFSEExptsProce.setId(getNextIdFromSEQ_INTEROP_JOBS_FSE_EXP_PRO(con));
                if (interopJobsFSEExptsProce.getId() <= 0) {
                    throw new SQLException("Id Unico no recuperado correctamente desde SEQ_INTEROP_JOBS_FSE_EXP_PRO", "SQL-NO-ID-RECUPERADO", interopJobsFSEExptsProce.getId().intValue());
                }
                ps.setLong(contadorParam++, interopJobsFSEExptsProce.getId());
                ps.setString(contadorParam++, interopJobsFSEExptsProce.getNombreProcesoJobBatch());
                ps.setString(contadorParam++, interopJobsFSEExptsProce.getNumeroExpediente());
                ps.setString(contadorParam++, interopJobsFSEExptsProce.getObservaciones());
                log.info("params = "
                        + interopJobsFSEExptsProce.toString()
                );
                if (ps.executeUpdate() > 0) {
                    log.error("Insercion Correcta...");
                    resultado = this.getInteropJobsFSEExptsProceById(interopJobsFSEExptsProce.getId(), con);
                } else {
                    log.error("Linea de InteropJobsFSEExptsProce No creada....!!!");
                }
            } else {
                log.info("Objeto  a insertar/modificar recibido a null, no se procesa, respuesta devuelta : null");
            }
        } catch (SQLException e) {
            log.error("SQLException - crearLineaInteropJobsFSEExptsProce - ", e);
            throw e;
        } catch (Exception e) {
            log.error("Exception - crearLineaInteropJobsFSEExptsProce - ", e);
            throw e;
        } finally {
            log.debug("Procedemos a cerrar el resultset");
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" crearLineaInteropJobsFSEExptsProce - End " + formatFechaLog.format(new Date()));
        return resultado;
    }

    public boolean actualizarLineaInteropJobsFSEExptsProce(InteropJobsFSEExptsProce interopJobsFSEExptsProce, Connection con) throws SQLException, Exception {
        log.info(" actualizarLineaInteropJobsFSEExptsProce - Begin " + formatFechaLog.format(new Date()));
        boolean resultado = true;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (interopJobsFSEExptsProce != null) {
                String query = "";
                int contadorParam = 1;
                query = " update INTEROP_JOBS_FSE_EXPTS_PROCE "
                        + " set "
                        + " nombreProcesoJobBatch=? "
                        + " ,numeroExpediente=?"
                        + " ,expedienteProcesadoCompl=?"
                        + " ,observaciones=?"
                        + " where "
                        + " id=?";
                log.info("sql = " + query);
                ps = con.prepareStatement(query);
                ps.setString(contadorParam++, interopJobsFSEExptsProce.getNombreProcesoJobBatch());
                ps.setString(contadorParam++, interopJobsFSEExptsProce.getNumeroExpediente());
                ps.setInt(contadorParam++, interopJobsFSEExptsProce.getExpedienteProcesadoCompl());
                ps.setString(contadorParam++, interopJobsFSEExptsProce.getObservaciones());
                ps.setLong(contadorParam++, interopJobsFSEExptsProce.getId());
                log.info("params = "
                        + interopJobsFSEExptsProce.toString()
                );
                resultado = ps.executeUpdate() > 0;
            } else {
                log.error("Objeto  a insertar/modificar recibido a null, no se procesa, respuesta devuelta : null");
            }
        } catch (SQLException e) {
            log.error("SQLException - actualizarLineaInteropJobsFSEExptsProce - ", e);
            throw e;
        } catch (Exception e) {
            log.error("Exception - actualizarLineaInteropJobsFSEExptsProce - ", e);
            throw e;
        } finally {
            log.debug("Procedemos a cerrar el resultset");
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" actualizarLineaInteropJobsFSEExptsProce - End " + formatFechaLog.format(new Date()));
        return resultado;
    }

    public boolean eliminarLineaInteropJobsFSEExptsProceByID(long id, Connection con) throws SQLException, Exception {
        log.info(" eliminarLineaInteropJobsFSEExptsProceByID - Begin " + formatFechaLog.format(new Date()));
        boolean resultado = true;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "";
            int contadorParam = 1;
            query = " delete from  INTEROP_JOBS_FSE_EXPTS_PROCE "
                    + " where "
                    + " id=?";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            ps.setLong(contadorParam++, id);
            log.info("params = "
                    + id
            );
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("SQLException - eliminarLineaInteropJobsFSEExptsProceByID - ", e);
            throw e;
        } catch (Exception e) {
            log.error("Exception - eliminarLineaInteropJobsFSEExptsProceByID - ", e);
            throw e;
        } finally {
            log.debug("Procedemos a cerrar el resultset");
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" eliminarLineaInteropJobsFSEExptsProceByID - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
}
