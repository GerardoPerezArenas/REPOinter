/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.dao;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsLog;
import java.sql.Clob;
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
public class InteropJobsLogDAO {
    
    private static final Logger log = Logger.getLogger(InteropJobsLogDAO.class);
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private final InteropJobsDaoMapping interopJobsDaoMapping = new InteropJobsDaoMapping();

    public InteropJobsLog getInteropJobsLogById(long id, Connection con) throws SQLException, Exception {
        log.info(" getInteropJobsLogById - Begin " + id + formatFechaLog.format(new Date()));
        InteropJobsLog resultado = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * "
                    + " from INTEROP_JOBS_LOG "
                    + " where "
                    + " id=? ";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            ps.setLong(1, id);
            log.info("params = " + id);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado=interopJobsDaoMapping.getInteropJobsLog(rs);
            }
        } catch (SQLException e) {
            log.error("getInteropJobsLogById  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getInteropJobsLogById - Exception ", e);
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
        log.info(" getInteropJobsLogById - End " + id + formatFechaLog.format(new Date()));
        return resultado;
    }

    public List<InteropJobsLog> getInteropJobsLogByDocumentoId(String documentId,Connection con) throws SQLException, Exception {
        log.info(" getInteropJobsLogByDocumentoId - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsLog> resultado = new ArrayList<InteropJobsLog>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * "
                    + " from INTEROP_JOBS_LOG "
                    + " where "
                    + " documentoId=? ";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            ps.setString(1, documentId);
            log.info("params = " + documentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(interopJobsDaoMapping.getInteropJobsLog(rs));
            }
        } catch (SQLException e) {
            log.error("getInteropJobsLogByDocumentoId  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getInteropJobsLogByDocumentoId - Exception ", e);
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
        log.info(" getInteropJobsLogByDocumentoId - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    public List<InteropJobsLog> getInteropJobsLogBynumeroExpediente(String numeroExpediente,Connection con) throws SQLException, Exception {
        log.info(" getInteropJobsLogBynumeroExpediente - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsLog> resultado = new ArrayList<InteropJobsLog>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * "
                    + " from INTEROP_JOBS_LOG "
                    + " where "
                    + " numeroExpediente=? ";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            ps.setString(1, numeroExpediente);
            log.info("params = " + numeroExpediente);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(interopJobsDaoMapping.getInteropJobsLog(rs));
            }
        } catch (SQLException e) {
            log.error("getInteropJobsLogByDocumentoId  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getInteropJobsLogByDocumentoId - Exception ", e);
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
        log.info(" getInteropJobsLogByDocumentoId - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    private long getNextIdFromSEQ_INTEROP_JOBS_LOG(Connection con) throws SQLException, Exception{
        log.info(" getNextIdFromSEQ_INTEROP_JOBS_LOG - Begin "  + formatFechaLog.format(new Date()));
        long resultado = -1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select SEQ_INTEROP_JOBS_LOG.nextval id from dual";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado = rs.getLong("id");
            }
        } catch (SQLException e) {
            log.error("getNextIdFromSEQ_INTEROP_JOBS_LOG  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getNextIdFromSEQ_INTEROP_JOBS_LOG - Exception ", e);
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
        log.info(" getNextIdFromSEQ_INTEROP_JOBS_LOG - End " + resultado + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    public InteropJobsLog crearLineaInteropJobsLog(InteropJobsLog interopJobsLog, Connection con) throws SQLException, Exception {
        log.info(" crearLineaInteropJobsLog - Begin " + formatFechaLog.format(new Date()));
        InteropJobsLog resultado = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (interopJobsLog != null) {
                String query = "";
                int contadorParam = 1;
                    query = " insert into INTEROP_JOBS_LOG "
                            + "("
                            + " id "
                            + " ,idOrganizacion "
                            + " ,nombreProcesoJobBatch "
                            + " ,codigoProcedimiento  "
                            + " ,numeroExpediente  "
                            + " ,tipoDocumentoId  "
                            + " ,documentoId  "
                            + " ,resultadoWS  "
                            + " ,datosEnviados  "
                            + " ,datosRespuesta  "
                            + " ,observaciones"
                            + ")"
                            + " values"
                            + "("
                            + " ?,?,?,?,?,?,?,?,?,?,?"
                            + ") ";
                    ps = con.prepareStatement(query);
                    interopJobsLog.setId(getNextIdFromSEQ_INTEROP_JOBS_LOG(con));
                    if(interopJobsLog.getId()<=0){
                        throw new SQLException("Id Unico no recuperado correctamente desde SEQ_INTEROP_JOBS_LOG", "SQL-NO-ID-RECUPERADO",(int)interopJobsLog.getId());
                    }
                    ps.setLong(contadorParam++, interopJobsLog.getId());
                    ps.setInt(contadorParam++, interopJobsLog.getIdOrganizacion());
                    ps.setString(contadorParam++, interopJobsLog.getNombreProcesoJobBatch());
                    ps.setString(contadorParam++, interopJobsLog.getCodigoProcedimiento());
                    ps.setString(contadorParam++, interopJobsLog.getNumeroExpediente());
                    ps.setString(contadorParam++, interopJobsLog.getTipoDocumentoId());
                    ps.setString(contadorParam++, interopJobsLog.getDocumentoId());
                    ps.setString(contadorParam++, interopJobsLog.getResultadoWS());
                    if(interopJobsLog.getDatosEnviados()!=null && !interopJobsLog.getDatosEnviados().isEmpty()){
                        Clob datosEnviados = con.createClob();
                        datosEnviados.setString(1,interopJobsLog.getDatosEnviados());
                        ps.setClob(contadorParam++, datosEnviados);
                    }else{
                        ps.setNull(contadorParam++,java.sql.Types.NULL);
                    }
                    if(interopJobsLog.getDatosRespuesta()!=null && !interopJobsLog.getDatosRespuesta().isEmpty()){
                        Clob datosRespuesta = con.createClob();
                        datosRespuesta.setString(1,interopJobsLog.getDatosRespuesta());
                        ps.setClob(contadorParam++, datosRespuesta);
                    }else{
                        ps.setNull(contadorParam++,java.sql.Types.NULL);
                    }
                    ps.setString(contadorParam++, interopJobsLog.getObservaciones());
                log.info("sql = " + query);
                log.info("params = " 
                        + interopJobsLog.toString()
                );
                if (ps.executeUpdate() > 0) {
                    log.error("Insercion Correcta...");
                    resultado=this.getInteropJobsLogById(interopJobsLog.getId(), con);
                } else {
                    log.error("Linea de log No creada....!!!");
                }
            } else {
                log.info("Objeto  a insertar/modificar recibido a null, no se procesa, respuesta devuelta : null");
            }
        } catch (SQLException e) {
            log.error("SQLException - crearLineaInteropJobsLog - ", e);
            throw e;
        } catch (Exception e) {
            log.error("Exception - crearLineaInteropJobsLog - ", e);
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
        log.info(" crearLineaInteropJobsLog - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    public boolean actualizarLineaInteropJobsLog(InteropJobsLog interopJobsLog, Connection con) throws SQLException, Exception {
        log.info(" actualizarLineaInteropJobsLog - Begin " + formatFechaLog.format(new Date()));
        boolean resultado = true;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (interopJobsLog != null) {
                String query = "";
                int contadorParam = 1;
                    query = " update INTEROP_JOBS_LOG "
                            + " set "
                            + " idOrganizacion=? "
                            + " ,nombreProcesoJobBatch=? "
                            + " ,codigoProcedimiento=? "
                            + " ,numeroExpediente=?"
                            + " ,tipoDocumentoId=?"
                            + " ,documentoId=?"
                            + " ,resultadoWS=?"
                            + " ,datosEnviados=?"
                            + " ,datosRespuesta=?"
                            + " ,observaciones=?"
                            + " ,personaProcesada=?"
                            + " where "
                            + " id=?"
                            ;
                    ps = con.prepareStatement(query);
                    ps.setInt(contadorParam++, interopJobsLog.getIdOrganizacion());
                    ps.setString(contadorParam++, interopJobsLog.getNombreProcesoJobBatch());
                    ps.setString(contadorParam++, interopJobsLog.getCodigoProcedimiento());
                    ps.setString(contadorParam++, interopJobsLog.getNumeroExpediente());
                    ps.setString(contadorParam++, interopJobsLog.getTipoDocumentoId());
                    ps.setString(contadorParam++, interopJobsLog.getDocumentoId());
                    ps.setString(contadorParam++, interopJobsLog.getResultadoWS());
                    if(interopJobsLog.getDatosEnviados()!=null && !interopJobsLog.getDatosEnviados().isEmpty()){
                        Clob datosEnviados = con.createClob();
                        datosEnviados.setString(1,interopJobsLog.getDatosEnviados());
                        ps.setClob(contadorParam++, datosEnviados);
                    }else{
                        ps.setNull(contadorParam++,java.sql.Types.NULL);
                    }
                    if(interopJobsLog.getDatosRespuesta()!=null && !interopJobsLog.getDatosRespuesta().isEmpty()){
                        Clob datosRespuesta = con.createClob();
                        datosRespuesta.setString(1,interopJobsLog.getDatosRespuesta());
                        ps.setClob(contadorParam++, datosRespuesta);
                    }else{
                        ps.setNull(contadorParam++,java.sql.Types.NULL);
                    }
                    ps.setString(contadorParam++, interopJobsLog.getObservaciones());
                    ps.setInt(contadorParam++, interopJobsLog.getPersonaProcesada());
                    ps.setLong(contadorParam++, interopJobsLog.getId());
                log.info("sql = " + query);
                log.info("params = " 
                        + interopJobsLog.toString()
                );
                resultado = ps.executeUpdate() > 0;
            } else {
                log.error("Objeto  a insertar/modificar recibido a null, no se procesa, respuesta devuelta : null");
            }
        } catch (SQLException e) {
            log.error("SQLException - actualizarLineaInteropJobsLog - ", e);
            throw e;
        } catch (Exception e) {
            log.error("Exception - actualizarLineaInteropJobsLog - ", e);
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
        log.info(" actualizarLineaInteropJobsLog - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    public boolean eliminarLineaInteropJobsLogByID(long interopJobsLog, Connection con) throws SQLException, Exception {
        log.info(" eliminarLineaInteropJobsLogByID - Begin " + formatFechaLog.format(new Date()));
        boolean resultado = true;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "";
            int contadorParam = 1;
            query = " delete from  INTEROP_JOBS_LOG "
                    + " where "
                    + " id=?"
                    ;
                ps = con.prepareStatement(query);
                ps.setLong(contadorParam++, interopJobsLog);
            log.info("sql = " + query);
            log.info("params = " 
                    + interopJobsLog
            );
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("SQLException - eliminarLineaInteropJobsLogByID - ", e);
            throw e;
        } catch (Exception e) {
            log.error("Exception - eliminarLineaInteropJobsLogByID - ", e);
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
        log.info(" eliminarLineaInteropJobsLogByID - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
    
}
