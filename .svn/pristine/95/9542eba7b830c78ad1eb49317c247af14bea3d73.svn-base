/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.dao;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.MELANBIDE_INTEROP;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConfigurationParameter;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.RegistroVidaLaboralVO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author pablo.bugia
 */
public class MeLanbideInteropVidaLaboralDAO {
    //Logger

    private static Logger log = Logger.getLogger(MeLanbideInteropVidaLaboralDAO.class);

    //Instancia
    private static MeLanbideInteropVidaLaboralDAO instance = null;

    private MeLanbideInteropVidaLaboralDAO() {
    }

    public static MeLanbideInteropVidaLaboralDAO getInstance() {
        if (instance == null) {
            synchronized (MeLanbideInteropVidaLaboralDAO.class) {
                instance = new MeLanbideInteropVidaLaboralDAO();
            }
        }
        return instance;
    }

    public List<RegistroVidaLaboralVO> getRegistrosVidaLaboral(final String docIdentificacion,
            final String numExp, final Connection con) throws Exception {
        final List<RegistroVidaLaboralVO> result = new ArrayList<RegistroVidaLaboralVO>();
        Statement st = null;
        ResultSet rs = null;

        final String query = "SELECT * FROM "
                + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_INTEROP_VIDALABORAL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES)
                + " WHERE NUMEXP = '" + numExp + "' AND DOCUMENTACION = '" + docIdentificacion + "'" 
                + " ORDER BY TIPODOCUMENTACION, FECHAALTA";

        if (log.isDebugEnabled()) {
            log.debug("sql = " + query);
        }

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                result.add(new RegistroVidaLaboralVO(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6),
                        rs.getString(7), rs.getDate(8), rs.getString(9), rs.getInt(10),
                        rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14),
                        rs.getInt(15), rs.getInt(16), rs.getInt(17), rs.getInt(18),
                        rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22),
                        rs.getString(23), rs.getDate(24), rs.getDate(25), rs.getDate(26),
                        rs.getString(27), rs.getString(28), rs.getString(29), rs.getString(30),
                        rs.getInt(31), rs.getFloat(32), rs.getInt(33)));
            }
        } catch (Exception ex) {
            log.error("Se ha producido un error recuperando el registro laboral con el document " + docIdentificacion + " ", ex);
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
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el statement y el resulset", e);
                throw new Exception(e);
            }
        }
        return result;
    }
    
    public List<RegistroVidaLaboralVO> getRegistrosVidaLaboral(final String numExp, final Connection con) throws Exception {
        final List<RegistroVidaLaboralVO> result = new ArrayList<RegistroVidaLaboralVO>();
        Statement st = null;
        ResultSet rs = null;

        final String query = "SELECT * FROM "
                + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_INTEROP_VIDALABORAL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES)
                + " WHERE NUMEXP = '" + numExp + "'"
                + " ORDER BY TIPODOCUMENTACION, DOCUMENTACION, FECHAALTA";

        if (log.isDebugEnabled()) {
            log.debug("sql = " + query);
        }

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                result.add(new RegistroVidaLaboralVO(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6),
                        rs.getString(7), rs.getDate(8), rs.getString(9), rs.getInt(10),
                        rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14),
                        rs.getInt(15), rs.getInt(16), rs.getInt(17), rs.getInt(18),
                        rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22),
                        rs.getString(23), rs.getDate(24), rs.getDate(25), rs.getDate(26),
                        rs.getString(27), rs.getString(28), rs.getString(29), rs.getString(30),
                        rs.getInt(31), rs.getFloat(32), rs.getInt(33)));
            }
        } catch (Exception ex) {
            log.error("Se ha producido un error recuperando el registro laboral del expediente " + numExp + " ", ex);
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
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el statement y el resulset", e);
                throw new Exception(e);
            }
        }
        return result;
    }

    public List<RegistroVidaLaboralVO> getRegistrosVidaLaboralByDoc(final String numDoc,
                                                                    final String tipoDoc, final Connection con) throws Exception {
        final List<RegistroVidaLaboralVO> result = new ArrayList<RegistroVidaLaboralVO>();
        Statement st = null;
        ResultSet rs = null;

        final String query = "SELECT * FROM "
                + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_INTEROP_VIDALABORAL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES)
                + " WHERE DOCUMENTACION = '" + numDoc + "' AND TIPODOCUMENTACION = '" + tipoDoc + "'"
                + " ORDER BY TIPODOCUMENTACION, DOCUMENTACION, FECHAALTA";

        if (log.isDebugEnabled()) {
            log.debug("sql = " + query);
        }

        try {
            st = con.prepareStatement(query);;
            rs = st.executeQuery(query);
            while (rs.next()) {
                result.add(new RegistroVidaLaboralVO(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6),
                        rs.getString(7), rs.getDate(8), rs.getString(9), rs.getInt(10),
                        rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14),
                        rs.getInt(15), rs.getInt(16), rs.getInt(17), rs.getInt(18),
                        rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22),
                        rs.getString(23), rs.getDate(24), rs.getDate(25), rs.getDate(26),
                        rs.getString(27), rs.getString(28), rs.getString(29), rs.getString(30),
                        rs.getInt(31), rs.getFloat(32), rs.getInt(33)));
            }
        } catch (Exception ex) {
            log.error("Se ha producido un error recuperando el registro laboral del expediente " + numDoc + " ", ex);
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
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el statement y el resulset", e);
                throw new Exception(e);
            }
        }
        return result;
    }

    public boolean peticionPreviaVidaLaboral(final String numExp, final String numDoc, final Connection con) throws Exception {
        boolean found = false;
        Statement st = null;
        ResultSet rs = null;

        final String query = "SELECT * FROM "
                + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_TABLA_LOG_LLAMADAS_NISAE, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES)
                + " WHERE NUMEROEXPEDIENTE = '" + numExp + "' AND DOCUMENTOINTERESADO = '" + numDoc + "' AND RESULTADO = '0000'" ;

        if (log.isDebugEnabled()) {
            log.debug("sql = " + query);
        }

        try {
            st = con.prepareStatement(query);;
            rs = st.executeQuery(query);
            if (rs.next()) {
               found = true;
            }
        } catch (Exception ex) {
            log.error("Se ha producido un error recuperando el registro de llamada a terceros del expediente " + numExp + " ", ex);
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
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el statement y el resulset", e);
                throw new Exception(e);
            }
        }
        return found;
    }

    public RegistroVidaLaboralVO getRegistroVidaLaboral(final RegistroVidaLaboralVO registro, final String numExp,
            final Connection con) throws Exception {
        RegistroVidaLaboralVO result = null;
        Statement st = null;
        ResultSet rs = null;
        final SimpleDateFormat format = new SimpleDateFormat(ConstantesMeLanbideInterop.FORMATO_FECHA);

        final StringBuilder query = new StringBuilder();
        
        log.info("getRegistroVidaLaboral inicio");
        query.append("SELECT * FROM ");
        query.append(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_INTEROP_VIDALABORAL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
        query.append(" WHERE NUMEXP = '" + numExp + "'");
        query.append(" AND DOCUMENTACION = '" + registro.getDocumentacion() + "'");        
        query.append(" AND TIPODOCUMENTACION = '" + registro.getTipoDocumentacion() + "'");        
        query.append(registro.getFechaAlta() != null ? " AND FECHAALTA = TO_DATE('" + format.format(registro.getFechaAlta()) + "', 'dd/mm/yyyy') " : " AND FECHAALTA IS NULL ");
        query.append(registro.getFechaEfectos() != null ? " AND FECHAEFECTOS = TO_DATE('" + format.format(registro.getFechaEfectos()) + "', 'dd/mm/yyyy') " : " AND FECHAFECTOS IS NULL");
//        query.append(registro.getFechaBaja() != null ? " AND FECHABAJA = TO_DATE('" + format.format(registro.getFechaBaja()) + "', 'dd/mm/yyyy')" : " AND FECHABAJA IS NULL");
        query.append(registro.getCodCuentaCot() != null ? " AND CODCUENTACOT = '" + registro.getCodCuentaCot() + "'" : " AND CODCUENTACOT IS NULL");
        query.append(registro.getRegimen() != null ? " AND REGIMEN = '" + registro.getRegimen() + "'" : " AND REGIMEN IS NULL");
        query.append(registro.getEmpresa() != null ? " AND EMPRESA = '" + registro.getEmpresa() + "'" : " AND EMPRESA IS NULL");
                      
        if (log.isDebugEnabled()) {
            log.debug("sql = " + query);
        }

        try {
            st = con.createStatement();
            rs = st.executeQuery(query.toString());
            if (rs.next()) {
                result = new RegistroVidaLaboralVO(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6),
                        rs.getString(7), rs.getDate(8), rs.getString(9), rs.getInt(10),
                        rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14),
                        rs.getInt(15), rs.getInt(16), rs.getInt(17), rs.getInt(18),
                        rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22),
                        rs.getString(23), rs.getDate(24), rs.getDate(25), rs.getDate(26),
                        rs.getString(27), rs.getString(28), rs.getString(29), rs.getString(30),
                        rs.getInt(31), rs.getFloat(32), rs.getInt(33));
            }
        } catch (Exception ex) {
            log.error("Se ha producido un error recuperando el registro laboral con el document " + registro.getDocumentacion() + " ", ex);
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
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el statement y el resulset", e);
                throw new Exception(e);
            }
        }
        return result;
    }

    public int insertarRegistroVidaLaboral(final RegistroVidaLaboralVO registro, final String numExp,
            final Connection con) throws Exception {
        Statement st = null;
        final SimpleDateFormat format = new SimpleDateFormat(ConstantesMeLanbideInterop.FORMATO_FECHA);
        int result = 0;
        try {
            int idPeticion = this.getNextId(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.SEQ_VIDALABORAL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES), con);
            StringBuilder query = new StringBuilder();
            query.append("INSERT INTO ");
            query.append(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_INTEROP_VIDALABORAL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
            query.append(" (IDPETICION, TIPODOCUMENTACION, DOCUMENTACION, FECHADESDE, FECHAHASTA, NUMEROSITUACIONES, ");
            query.append("NUMEROAFILIACION_L, FECHANACIMIENTO, TRANSFDERECHOSCEE, RESUMEN_CONPL_TOTALDIASALTA, ");
            query.append("RESUMEN_CONPL_DIASPLURIEMPLEO, RESUMEN_CONPL_ANIOSALTA, RESUMEN_CONPL_MESESALTA, ");
            query.append("RESUMEN_CONPL_DIASALTA, RESUMEN_TOTALDIASALTA, RESUMEN_ANIOSALTA, RESUMEN_MESESALTA, ");
            query.append("RESUMEN_DIASALTA, NUMEROAFILIACION, REGIMEN, EMPRESA, CODCUENTACOT, PROVINCIA, ");
            query.append("FECHAALTA, FECHAEFECTOS, FECHABAJA, CONTRATOTRABAJO, DESCCONTRATOTRABAJO, CONTRATOTPARCIAL, ");
            query.append("GRUPOCOTIZACION, DIASALTA, DIASALTA_CALCULADOS, ORDEN_SITLAB, NUMEXP) VALUES (");
            query.append("'" + idPeticion + "', '" + registro.getTipoDocumentacion() + "', ");
            query.append("'" + registro.getDocumentacion() + "', ");
            query.append(registro.getFechaDesde() != null ? "TO_DATE('" + format.format(registro.getFechaDesde()) + "', 'dd/mm/yyyy'), " : null + ", ");
            query.append(registro.getFechaHasta() != null ? "TO_DATE('" + format.format(registro.getFechaHasta()) + "', 'dd/mm/yyyy'), " : null + ", ");
            query.append(registro.getNumeroSituaciones() + ", ");
            query.append("'" + registro.getNumeroAfiliacionL() + "', ");
            query.append(registro.getFechaNacimiento() != null ? "TO_DATE('" + format.format(registro.getFechaNacimiento()) + "', 'dd/mm/yyyy'), " : null + ", ");
            query.append("'" + registro.getTransfDerechosCEE() + "', " + registro.getResumenConplTotalDiasAlta() + ", ");
            query.append(registro.getResumenConplDiasPluriempleo() + ", " + registro.getResumenConplAniosAlta() + ", ");
            query.append(registro.getResumenConplMesesAlta() + ", " + registro.getResumenConplDiasAlta() + ", ");
            query.append(registro.getResumenTotalDiasAlta() + ", " + registro.getResumenAniosAlta() + ", ");
            query.append(registro.getResumenMesesAlta() + ", " + registro.getResumenDiasAlta() + ", ");
            query.append(registro.getNumeroAfiliacion() != null ? "'" + registro.getNumeroAfiliacion() + "', " : null + ", ");
            query.append(registro.getRegimen() != null ? "'" + registro.getRegimen() + "', " : null + ", ");
            query.append(registro.getEmpresa() != null ? "'" + registro.getEmpresa() + "', " : null + ", ");
            query.append(registro.getCodCuentaCot() != null ? "'" + registro.getCodCuentaCot() + "', " : null + ", ");
            query.append(registro.getProvincia() != null ? "'" + registro.getProvincia() + "', " : null + ", ");
            query.append(registro.getFechaAlta() != null ? "TO_DATE('" + format.format(registro.getFechaAlta()) + "', 'dd/mm/yyyy'), " : null + ", ");
            query.append(registro.getFechaEfectos() != null ? "TO_DATE('" + format.format(registro.getFechaEfectos()) + "', 'dd/mm/yyyy'), " : null + ", ");
            query.append(registro.getFechaBaja() != null ? "TO_DATE('" + format.format(registro.getFechaBaja()) + "', 'dd/mm/yyyy'), " : null + ", ");
            query.append(registro.getContratoTrabajo() != null ? "'" + registro.getContratoTrabajo() + "', " : null + ", ");
            query.append(registro.getDescContratoTrabajo() != null ? "'" + registro.getDescContratoTrabajo() + "', " : null + ", ");
            query.append(registro.getContratoTParcial() != null ? "'" + registro.getContratoTParcial() + "', " : null + ", ");
            query.append(registro.getGrupoCotizacion() != null ? "'" + registro.getGrupoCotizacion() + "', " : null + ", ");
            query.append(registro.getDiasAlta() + ", " + registro.getDiasAltaCalculados() + ", " + registro.getOrdenSitLab() + ", ");
            query.append("'" + numExp + "'");
            query.append(")");
            if (log.isDebugEnabled()) {
                log.debug("sql insertarRegistroVidaLaboral= " + query);
            }
            st = con.createStatement();
            result = st.executeUpdate(query.toString());
        } catch (final Exception ex) {
            log.error(registro.getDocumentacion() + "Se ha producido un error grabando el registro laboral con el documento "
                    + registro.getDocumentacion() + " para el expediente " + numExp, ex);
            throw new Exception(ex);
        } finally {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("Procedemos a cerrar el statement y el resultset");
                }
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el statement y el resulset", e);
                throw new Exception(e);
            }
        }
        return result;
    }

    public int actualizarRegistroVidaLaboral(final RegistroVidaLaboralVO registro, final String numExp,
            final Connection con) throws Exception {
        
        Statement st = null;
        final SimpleDateFormat format = new SimpleDateFormat(ConstantesMeLanbideInterop.FORMATO_FECHA);
        int result = 0;
        try {
            final StringBuilder query = new StringBuilder();
            query.append("UPDATE ");
            query.append(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.TABLA_INTEROP_VIDALABORAL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
//            query.append(" SET TIPODOCUMENTACION = '" + registro.getTipoDocumentacion() + "', ");
            query.append(registro.getFechaDesde() != null ? " SET FECHADESDE = TO_DATE('" + format.format(registro.getFechaDesde()) + "', 'dd/mm/yyyy'), " : "FECHADESDE = NULL, ");
            query.append(registro.getFechaHasta() != null ? "FECHAHASTA = TO_DATE('" + format.format(registro.getFechaHasta()) + "', 'dd/mm/yyyy'), " : "FECHAHASTA = NULL, ");
            query.append(registro.getFechaBaja() != null ? "FECHABAJA = TO_DATE('" + format.format(registro.getFechaBaja()) + "', 'dd/mm/yyyy'), " : "");
            query.append("NUMEROSITUACIONES = " + registro.getNumeroSituaciones() + ", ");
            query.append("NUMEROAFILIACION_L = '" + registro.getNumeroAfiliacionL() + "', ");
//            query.append(registro.getFechaNacimiento() != null ? "FECHANACIMIENTO = TO_DATE('" + format.format(registro.getFechaNacimiento()) + "', 'dd/mm/yyyy'), " : "FECHANACIMIENTO = NULL, ");
            query.append("TRANSFDERECHOSCEE = '" + registro.getTransfDerechosCEE() + "', ");
            query.append("RESUMEN_CONPL_TOTALDIASALTA = " + registro.getResumenConplDiasAlta() + ", ");
            query.append("RESUMEN_CONPL_DIASPLURIEMPLEO = " + registro.getResumenConplDiasPluriempleo() + ", ");
            query.append("RESUMEN_CONPL_ANIOSALTA = " + registro.getResumenConplAniosAlta() + ", ");
            query.append("RESUMEN_CONPL_MESESALTA = " + registro.getResumenConplMesesAlta() + ", ");
            query.append("RESUMEN_CONPL_DIASALTA = " + registro.getResumenConplDiasAlta() + ", ");
            query.append("RESUMEN_TOTALDIASALTA = " + registro.getResumenTotalDiasAlta() + ", ");
            query.append("RESUMEN_ANIOSALTA = " + registro.getResumenAniosAlta() + ", ");
            query.append("RESUMEN_MESESALTA = " + registro.getResumenMesesAlta() + ", ");
            query.append("RESUMEN_DIASALTA = " + registro.getResumenDiasAlta() + ", ");
            query.append("NUMEROAFILIACION ='" + registro.getNumeroAfiliacion() + "', ");
//            query.append(registro.getRegimen() != null ? "REGIMEN = '" + registro.getRegimen() + "', " : "REGIMEN = NULL, ");
//            query.append(registro.getEmpresa() != null ? "EMPRESA = '" + registro.getEmpresa() + "', " : "EMPRESA = NULL, ");
            query.append(registro.getProvincia() != null ? "PROVINCIA = '" + registro.getProvincia() + "', " : "PROVINCIA = NULL, ");
            query.append(registro.getContratoTrabajo() != null ? "CONTRATOTRABAJO = '" + registro.getContratoTrabajo() + "', " : "CONTRATOTRABAJO = NULL, ");
            query.append(registro.getDescContratoTrabajo() != null ? "DESCCONTRATOTRABAJO = '" + registro.getDescContratoTrabajo() + "', " : "DESCCONTRATOTRABAJO = NULL, ");
            query.append(registro.getContratoTParcial() != null ? "CONTRATOTPARCIAL = '" + registro.getContratoTParcial() + "', " : "CONTRATOTPARCIAL = NULL, ");
            query.append(registro.getGrupoCotizacion() != null ? "GRUPOCOTIZACION = '" + registro.getGrupoCotizacion() + "', " : "GRUPOCOTIZACION = NULL, ");
            query.append("DIASALTA = " + registro.getDiasAlta() + ", ");
            query.append("DIASALTA_CALCULADOS = " + registro.getDiasAltaCalculados() + ", ");
            query.append("ORDEN_SITLAB = " + registro.getOrdenSitLab() + " ");
            
            query.append("WHERE NUMEXP = '" + numExp + "' ");
            query.append("AND DOCUMENTACION = '" + registro.getDocumentacion() + "' ");        
            query.append("AND TIPODOCUMENTACION = '" + registro.getTipoDocumentacion() + "' ");        
            query.append(registro.getFechaAlta() != null ? "AND FECHAALTA = TO_DATE('" + format.format(registro.getFechaAlta()) + "', 'dd/mm/yyyy') " : " AND FECHAALTA IS NULL ");
            query.append(registro.getFechaEfectos() != null ? "AND FECHAEFECTOS = TO_DATE('" + format.format(registro.getFechaEfectos()) + "', 'dd/mm/yyyy') " : " AND FECHAFECTOS IS NULL ");
//            query.append(registro.getFechaBaja() != null ? "AND FECHABAJA = TO_DATE('" + format.format(registro.getFechaBaja()) + "', 'dd/mm/yyyy') " : " AND FECHABAJA IS NULL ");
            query.append(registro.getCodCuentaCot() != null ? "AND CODCUENTACOT = '" + registro.getCodCuentaCot() + "' " : " AND CODCUENTACOT IS NULL ");
            query.append(registro.getRegimen() != null ? "AND REGIMEN = '" + registro.getRegimen() + "' " : " AND REGIMEN IS NULL ");
            query.append(registro.getEmpresa() != null ? "AND EMPRESA = '" + registro.getEmpresa() + "'" : " AND EMPRESA IS NULL");            
            
            if (log.isDebugEnabled()) {
                log.debug("sql actualizarRegistroVidaLaboral= " + query);
            }
            st = con.createStatement();
            result = st.executeUpdate(query.toString());
        } catch (final Exception ex) {
            log.error("Se ha producido un error actualizando el registro laboral de documentación " + registro.getDocumentacion(), ex);
            throw new Exception(ex);
        } finally {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("Procedemos a cerrar el statement y el resultset");
                }
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el statement y el resulset", e);
                throw new Exception(e);
            }
        }
        return result;
    }

    private Integer getNextId(String seqName, Connection con) throws Exception {

        Statement st = null;
        ResultSet rs = null;
        Integer numSec = null;
        try {
            String query = null;
            //Creo el id con la secuencia
            query = "select " + seqName + ".nextval from dual";
            if (log.isDebugEnabled()) {
                log.debug("sql = " + query);
            }
            st = con.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                numSec = rs.getInt(1);
                if (rs.wasNull()) {
                    throw new Exception();
                }
            }
        } catch (Exception ex) {
            log.error("Se ha producido un error generando el siguiente valor de la secuencia " + seqName, ex);
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
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el statement y el resulset", e);
                throw new Exception(e);
            }
        }
        return numSec;
    }

    public String getNombreProvincia(final String codigoProvincia,
            final Connection con) throws Exception {
        String nombreProvincia = null;
        Statement st = null;
        ResultSet rs = null;

        final String query = "SELECT DS_O FROM "
                + ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.VISTA_PROVINCIA, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES)
                + " WHERE ID = '" + codigoProvincia + "'";

        if (log.isDebugEnabled()) {
            log.debug("sql = " + query);
        }

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                nombreProvincia = rs.getString(1);
            }
        } catch (Exception ex) {
            log.error("Se ha producido un error recuperando el nombre de la provincia de " + codigoProvincia, ex);
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
            } catch (Exception e) {
                log.error("Se ha producido un error cerrando el statement y el resulset", e);
                throw new Exception(e);
            }
        }
        return nombreProvincia;
    }
}
