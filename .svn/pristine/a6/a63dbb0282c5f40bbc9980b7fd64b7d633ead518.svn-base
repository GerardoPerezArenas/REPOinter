/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.dao;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.dto.MEInteropCargaTelemXMLExpediente;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.dto.MEInteropCargaTelemXMLParameters;
import es.altia.util.conexion.AdaptadorSQLBD;
import es.altia.util.conexion.BDException;
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
public class MEInteropCargaTelemXMLServiceDAO {
    
    private Logger log = Logger.getLogger(MEInteropCargaTelemXMLServiceDAO.class);
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public List<MEInteropCargaTelemXMLExpediente> getExpedientesAndDocumentosProcesar(Integer codOrganizacion, MEInteropCargaTelemXMLParameters mEInteropCargaTelemXMLParameters, AdaptadorSQLBD adaptador) throws Exception,BDException {
        log.info(" getExpedientesAndDocumentosProcesar - Begin "  + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.getExpedientesAndDocumentosProcesar(codOrganizacion,mEInteropCargaTelemXMLParameters, con);
        } catch (BDException e) {
            log.error("BDException recuperando Datos ", e);
            throw e;
        } catch (Exception ex) {
            log.error("Exception recuperando Datos ", ex);
            throw ex;
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage(), e);
            }
        }
    }
    public List<MEInteropCargaTelemXMLExpediente> getExpedientesAndDocumentosProcesar(Integer codOrganizacion, MEInteropCargaTelemXMLParameters mEInteropCargaTelemXMLParameters, Connection con) throws SQLException, Exception {
        log.info(" getExpedientesAndDocumentosProcesar - Begin " + formatFechaLog.format(new Date()));
        List<MEInteropCargaTelemXMLExpediente> resultado = new ArrayList<MEInteropCargaTelemXMLExpediente>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT  " +
                " exr_num,EXR_DEP,EXR_UOR,EXR_TIP,EXR_EJR,EXR_NRE,EXR_TOP,red_doc,red_fec_doc,RED_TIP_DOC,RED_NOM_DOC,RED_IDDOC_GESTOR " +
                " FROM  E_EXR " +
                " LEFT JOIN R_RED ON RED_DEP=EXR_DEP AND RED_UOR=EXR_UOR AND RED_TIP=EXR_TIP AND RED_EJE=EXR_EJR AND RED_NUM=EXR_NRE " +
                " WHERE  " +
                " exr_top=0 and exr_tip='E' AND r_red.red_nom_doc LIKE 'FLX_DATOS_INTEGRACION_SOLICITUD%' " +
                " AND EXR_NUM=?";
            int contadorParam = 1;
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            ps.setString(contadorParam++, mEInteropCargaTelemXMLParameters.getNumeroExpediente());
            rs = ps.executeQuery();
            while (rs.next()) {
                MEInteropCargaTelemXMLExpediente expedienteDocs = new MEInteropCargaTelemXMLExpediente();
                expedienteDocs.setExpedienteNumero(rs.getString("exr_num"));
                expedienteDocs.setRegistroDepartamento(rs.getInt("EXR_DEP"));
                expedienteDocs.setRegistroUor(rs.getInt("EXR_UOR"));
                expedienteDocs.setRegistroTipo(rs.getString("EXR_TIP"));
                expedienteDocs.setRegistroEjercicio(rs.getInt("EXR_EJR"));
                expedienteDocs.setRegistroNumero(rs.getInt("EXR_NRE"));
                expedienteDocs.setRegistroExrTop(rs.getInt("EXR_TOP"));
                expedienteDocs.setDocumentoRegistroContenido(rs.getBytes("red_doc"));
                expedienteDocs.setDocumentoRegistroFecha(rs.getDate("red_fec_doc"));
                expedienteDocs.setDocumentoRegistroMimeType(rs.getString("RED_TIP_DOC"));
                expedienteDocs.setDocumentoRegistroNombre(rs.getString("RED_NOM_DOC"));
                expedienteDocs.setDocumentoRegistroOid(rs.getString("RED_IDDOC_GESTOR"));
                resultado.add(expedienteDocs);
            }
        } catch (SQLException e) {
            log.error("getExpedientesAndDocumentosProcesar  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getExpedientesAndDocumentosProcesar - Exception ", e);
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
        log.info(" getExpedientesAndDocumentosProcesar - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
    
}
