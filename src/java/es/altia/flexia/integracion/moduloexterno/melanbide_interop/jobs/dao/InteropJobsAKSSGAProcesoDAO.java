/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.dao;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager.InteropJobsUtils;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsAKSSGAProceso;
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
public class InteropJobsAKSSGAProcesoDAO {
    
    private static final Logger log = Logger.getLogger(InteropJobsAKSSGAProcesoDAO.class);
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private final InteropJobsDaoMapping interopJobsDaoMapping = new InteropJobsDaoMapping();
    private final InteropJobsUtils interopJobsUtils = new InteropJobsUtils();
    
    public List<InteropJobsAKSSGAProceso> getProcesosAKSSGAByFechacarga(long fechaCarga,Connection con) throws SQLException, Exception {
        log.info(" getProcesosAKSSGAByFechacarga - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsAKSSGAProceso> resultado = new ArrayList<InteropJobsAKSSGAProceso>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * "
                    + " from select * from SGA_PROCESO "
                    + " where FECHACARGA=?"
                    ;
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            int contadorParam  = 1;
            ps.setLong(contadorParam++, fechaCarga);
            log.info("params = " + fechaCarga);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(interopJobsDaoMapping.getInteropJobsAKSSGAProceso(rs));
            }
        } catch (SQLException e) {
            log.error("getProcesosAKSSGAByFechacarga  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getProcesosAKSSGAByFechacarga - Exception ", e);
            throw e;
        } finally {
            log.debug("Procedemos a getProcesosAKSSGAByFechacarga el resultset");
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" getProcedimientosProcesarAKSSGA - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    public InteropJobsAKSSGAProceso getProcesosAKSSGAById(long id,Connection con) throws SQLException, Exception {
        log.info(" getProcesosAKSSGAByFechacarga - Begin " + formatFechaLog.format(new Date()));
        InteropJobsAKSSGAProceso resultado = null; 
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * "
                    + " from select * from SGA_PROCESO "
                    + " where id=?"
                    ;
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            int contadorParam  = 1;
            ps.setLong(contadorParam++, id);
            log.info("params = " + id);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado=interopJobsDaoMapping.getInteropJobsAKSSGAProceso(rs);
            }
        } catch (SQLException e) {
            log.error("getProcesosAKSSGAByFechacarga  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getProcesosAKSSGAByFechacarga - Exception ", e);
            throw e;
        } finally {
            log.debug("Procedemos a getProcesosAKSSGAByFechacarga el resultset");
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" getProcedimientosProcesarAKSSGA - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    public InteropJobsAKSSGAProceso saveInteropJobsAKSSGAProceso(InteropJobsAKSSGAProceso data,Connection con) throws SQLException, Exception {
        log.info(" saveInteropJobsAKSSGAProceso - Begin " + formatFechaLog.format(new Date()));
        int resultado = 0; 
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if(data!=null){
                String query = "";
                if(data.getId()>0 ){
                    // update
                    query = "update  SGA_PROCESO "
                            + " set FECHACARGA=?, XMLSGA=? "
                            + " where id=?"
                            ;
                    log.info("sql = " + query);
                    ps = con.prepareStatement(query);
                    int contadorParam = 1;
                    ps.setLong(contadorParam++,data.getFechaCarga());
                    ps.setClob(contadorParam++,data.getXmlSga());
                    ps.setLong(contadorParam++,data.getId());
                }else{
                    // nuevo registro
                    data.setId(this.getNextIdFromSEQ_SGA_PROCESO(con));
                    query = "insert into SGA_PROCESO (ID,FECHACARGA,XMLSGA) "
                            + " values (?,?,?) "
                            ;
                    log.info("sql = " + query);
                    ps = con.prepareStatement(query);
                    int contadorParam = 1;
                    ps.setLong(contadorParam++,data.getId());
                    ps.setLong(contadorParam++,data.getFechaCarga());
                    ps.setClob(contadorParam++,data.getXmlSga());
                }
                log.info("params = " + data.toString()
                );
                resultado = ps.executeUpdate();
            }else{
                log.error("Objeto Datos a guardar recibido a null, no se procesa");
            }
            
        } catch (SQLException e) {
            log.error("saveInteropJobsAKSSGAProceso  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("saveInteropJobsAKSSGAProceso - Exception ", e);
            throw e;
        } finally {
            log.debug("Procedemos a saveInteropJobsAKSSGAProceso el resultset");
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" saveInteropJobsAKSSGAProceso - End " + formatFechaLog.format(new Date()));
        return (resultado>0 ? data : null);
    }
    
    private long getNextIdFromSEQ_SGA_PROCESO(Connection con) throws SQLException, Exception {
        log.info(" getNextIdFromSEQ_SGA_PROCESO - Begin " + formatFechaLog.format(new Date()));
        long resultado = -1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select SEQ_SGA_PROCESO.nextval id from dual";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado = rs.getLong("id");
            }
        } catch (SQLException e) {
            log.error("getNextIdFromSEQ_SGA_PROCESO  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getNextIdFromSEQ_SGA_PROCESO - Exception ", e);
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
        log.info(" getNextIdFromSEQ_SGA_PROCESO - End " + resultado + " "+ formatFechaLog.format(new Date()));
        return resultado;
    }
}
