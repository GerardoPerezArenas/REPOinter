/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.dao;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.MeLanbideConvocatorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author INGDGC
 */
public class MeLanbideConvocatoriasDAO {
    // Formateador de Fecha RegistroLog
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private final Logger log = Logger.getLogger(MeLanbideConvocatoriasDAO.class);

    public List<MeLanbideConvocatorias> getDecretoTodos(Connection con) throws SQLException, Exception {
        log.info(" getDecretoTodos - Begin " + formatFechaLog.format(new Date()));
        List<MeLanbideConvocatorias> resultadoList = new ArrayList<MeLanbideConvocatorias>();
        String query = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            query = "select * "
                    + " from MELANBIDE_CONVOCATORIAS ";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultadoList.add(getMeLanbideConvocatorias(rs));
            }
        } catch (SQLException e) {
            log.error("Se ha producido recuperando Lista de Decretos/Convocatorias ", e);
            throw e;
        } catch (Exception e) {
            log.error("Se ha producido recuperando datos de Decretos/Convocatorias ", e);
            throw e;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("Procedemos a cerrar el resultset");
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" getDecretoTodos - End " + formatFechaLog.format(new Date()));
        return resultadoList;
    }

    public MeLanbideConvocatorias getDecretoById(int id, Connection con) throws SQLException, Exception {
        log.info(" getDecretoById - Begin " + id + formatFechaLog.format(new Date()));
        String query = "";
        MeLanbideConvocatorias resultado = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            query = "select * "
                    + " from MELANBIDE_CONVOCATORIAS "
                    + " where "
                    + " id=? ";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            ps.setLong(1, id);
            log.info("params = " + id);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado = getMeLanbideConvocatorias(rs);
            }
        } catch (SQLException e) {
            log.error("Se ha producido recuperando datos Decreto ", e);
            throw e;
        } catch (Exception e) {
            log.error("Se ha producido recuperando datos Decreto ", e);
            throw e;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("Procedemos a cerrar el resultset");
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" getDecretoById - End " + id + formatFechaLog.format(new Date()));
        return resultado;
    }

    public MeLanbideConvocatorias getDecretoAplicableExpediente(Date fechaReferenciaExpediente, String codProcedimiento, Connection con) throws SQLException, Exception {
        log.info(" getDecretoAplicableExpediente - Begin " + fechaReferenciaExpediente + " - " + formatFechaLog.format(new Date()));
        MeLanbideConvocatorias resultado = null;
        String query = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (fechaReferenciaExpediente != null) {
                query = "select * "
                        + " from MELANBIDE_CONVOCATORIAS "
                        + " where "
                        + " pro_cod= ? "
                        + " and decretoFecEntradaVigor <= ? "
                        + " and (nvl(decretoFecFinAplicacion,sysdate+1)) > ? ";
                log.info("sql = " + query);
                ps = con.prepareStatement(query);
                java.sql.Date fechaReferenciaExpedienteSQLDate = new java.sql.Date(fechaReferenciaExpediente.getTime());
                ps.setString(1, codProcedimiento);
                ps.setDate(2, fechaReferenciaExpedienteSQLDate);
                ps.setDate(3, fechaReferenciaExpedienteSQLDate);
                log.info("params(1) = " + codProcedimiento);
                log.info("params(2y3) = " + fechaReferenciaExpedienteSQLDate);
                rs = ps.executeQuery();
                while (rs.next()) {
                    resultado = getMeLanbideConvocatorias(rs);
                }
            } else {
                log.info("Fecha de referencia recibida a null, no devolvemos ningun decreto aplicable.");
            }
        } catch (SQLException e) {
            log.error("Se ha producido recuperando datos Decreto ", e);
            throw e;
        } catch (Exception e) {
            log.error("Se ha producido recuperando datos Decreto ", e);
            throw e;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("Procedemos a cerrar el resultset");
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" getDecretoAplicableExpediente - End " + fechaReferenciaExpediente + " " + formatFechaLog.format(new Date()));
        return resultado;
    }

    public MeLanbideConvocatorias getDecretoByCodigoDecreto(String decretoCodigo, Connection con) throws SQLException, Exception {
        log.info(" getDecretoByCodigoDecreto - Begin " + decretoCodigo + formatFechaLog.format(new Date()));
        MeLanbideConvocatorias resultado = null;
        String query = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            query = "select * "
                    + " from MELANBIDE_CONVOCATORIAS "
                    + " where "
                    + " decretoCodigo=? ";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            ps.setString(1, decretoCodigo);
            log.info("params = " + decretoCodigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado = getMeLanbideConvocatorias(rs);
            }
        } catch (SQLException e) {
            log.error("Se ha producido recuperando datos Decreto ", e);
            throw e;
        } catch (Exception e) {
            log.error("Se ha producido recuperando datos Decreto ", e);
            throw e;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("Procedemos a cerrar el resultset");
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" getDecretoByCodigoDecreto - End " + decretoCodigo + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    /**
     * Devuelve el ID En BBDD de la convocatoria solicitada por CODIGO DE DECRETO y procedimiento (Se pueden repetir codigo decreto por PROCEDIMIENTO)
     * @param decretoCodigo
     * @param codProcedimiento
     * @param con
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public Integer getIdByDecretoCodigoProcedimiento(String decretoCodigo,String codProcedimiento, Connection con) throws SQLException, Exception {
        log.info(" getIdByDecretoCodigo - Begin " + decretoCodigo + formatFechaLog.format(new Date()));
        Integer resultado = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * "
                    + " from MELANBIDE_CONVOCATORIAS "
                    + " where "
                    + " DECRETOCODIGO=? "
                    + " AND PRO_COD=? ";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            ps.setString(1, decretoCodigo);
            ps.setString(2, codProcedimiento);
            log.info("params = " + decretoCodigo+" ** "+codProcedimiento);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado=rs.getInt("id");
            }
        } catch (SQLException e) {
            log.error("Se ha producido recuperando datos Decreto ", e);
            throw e;
        } catch (Exception e) {
            log.error("Se ha producido recuperando datos Decreto ", e);
            throw e;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("Procedemos a cerrar el resultset");
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" getIdByDecretoCodigo - End " + decretoCodigo + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    
    public List<MeLanbideConvocatorias> getDecretosxCodigoProcedimientoRegexlan(String codProcedimiento, Connection con) throws SQLException, Exception {
        log.info(" getDecretosxCodigoProcedimientoRegexlan - Begin " + codProcedimiento +" "+formatFechaLog.format(new Date()));
        List<MeLanbideConvocatorias> resultado = new ArrayList<MeLanbideConvocatorias>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * "
                    + " from MELANBIDE_CONVOCATORIAS "
                    + " where "
                    + " PRO_COD=? "
                    + " order by id ";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            ps.setString(1, codProcedimiento);
            log.info("params = " +codProcedimiento);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(getMeLanbideConvocatorias(rs));
            }
        } catch (SQLException e) {
            log.error("Se ha producido recuperando datos Decreto ", e);
            throw e;
        } catch (Exception e) {
            log.error("Se ha producido recuperando datos Decreto ", e);
            throw e;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("Procedemos a cerrar el resultset");
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        log.info(" getDecretosxCodigoProcedimientoRegexlan - End " + codProcedimiento +" "+ formatFechaLog.format(new Date()));
        return resultado;
    }
    
    public Date getFechaAltaExpediente(Integer codOrganizacion, String numExpediente, Connection con) throws Exception {
        log.debug("getFechaAltaExpediente - Begin() ");
        Date respuesta = null;
        Statement st = null;
        ResultSet rs = null;
        if (numExpediente != null) {
            try {
                String query = " SELECT exp_fei from e_exp "
                        + " where exp_mun=" + (codOrganizacion != null ? codOrganizacion : "null")
                        + " and exp_num='" + numExpediente + "'";
                if (log.isDebugEnabled()) {
                    log.error("sql = " + query);
                }
                st = con.createStatement();
                rs = st.executeQuery(query);
                if (rs.next()) {
                    respuesta = rs.getDate("exp_fei");
                }
            } catch (Exception ex) {
                log.error("Se ha producido un error recuperando datos fecha alta expediente " + numExpediente, ex);
                throw new Exception(ex);
            } finally {
                try {
                    if (log.isDebugEnabled()) {
                        log.debug("Procedemos a cerrar el statement y el resultset");
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException e) {
                    log.error("Se ha producido un error cerrando el statement y el resulset", e);
                    throw new Exception(e);
                }
            }
        }
        return respuesta;
    }
    
    /**
     * Mapear objetos ResultSet a ValueObject de Java MeLanbideConvocatorias
     * @param rs
     * @return 
     */
    private MeLanbideConvocatorias getMeLanbideConvocatorias(ResultSet rs) {
        MeLanbideConvocatorias respuesta = null;
        try {
            if (rs != null) {
                respuesta = new MeLanbideConvocatorias(
                            rs.getInt("id")
                            ,rs.getString("PRO_COD")
                            ,rs.getString("PRO_COD_PLATEA")
                            ,rs.getString("NUM_BOPV")
                            ,rs.getDate("FEC_CONS_LANBIDE")
                            ,rs.getString("DECRETOCODIGO")
                            ,rs.getString("DECRETODESCRIPCION")
                            ,rs.getString("DECRETODESCRIPCIONEU")
                            ,rs.getDate("DECRETOFECPUBLICACION")
                            ,rs.getDate("DECRETOFECENTRADAVIGOR")
                            ,rs.getDate("DECRETOFECFINAPLICACION")
                            ,rs.getString("COD_SERVICIO_LANB")
                            ,rs.getString("COD_SERVICIO_CATGV")
                );
            }
        } catch (Exception e) {
            log.error("Exception - getMeLanbideConvocatorias - ", e);
            respuesta = null;
        }
        return respuesta;
    }
}
