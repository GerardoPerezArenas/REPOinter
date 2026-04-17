/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.dao;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.dao.MeLanbideConvocatoriasDAO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager.InteropJobsUtils;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEExpedienteRequest;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEPersonaServicio;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEProcedi;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.MeLanbideConvocatorias;
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
public class InteropJobsFSEProcediDAO {
    
    private static final Logger log = Logger.getLogger(InteropJobsFSEProcediDAO.class);
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private final InteropJobsDaoMapping interopJobsDaoMapping = new InteropJobsDaoMapping();
    private final InteropJobsUtils interopJobsUtils = new InteropJobsUtils();
    private final MeLanbideConvocatoriasDAO meLanbideConvocatoriasDAO = new MeLanbideConvocatoriasDAO();
    
    public List<InteropJobsFSEProcedi> getProcedimientosProcesarFSE(Connection con) throws SQLException, Exception {
        log.info(" getProcedimientosProcesarFSE - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsFSEProcedi> resultado = new ArrayList<InteropJobsFSEProcedi>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * "
                    + " from INTEROP_JOBS_FSE_PROCEDI "
                    + " where "
                    + " procesar=1 ";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(interopJobsDaoMapping.getInteropJobsFSEProcedi(rs));
            }
        } catch (SQLException e) {
            log.error("getProcedimientosProcesarFSE  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getProcedimientosProcesarFSE - Exception ", e);
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
        log.info(" getProcedimientosProcesarFSE - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    public boolean actualizarEstadoInteropJobsFSEProcedi(InteropJobsFSEProcedi interopJobsFSEProcedi, Connection con) throws SQLException, Exception {
        log.info(" actualizarInteropJobsFSEProcedi - Begin " + formatFechaLog.format(new Date()));
        boolean resultado = true;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (interopJobsFSEProcedi != null) {
                String query = "";
                int contadorParam = 1;
                query = " update INTEROP_JOBS_LOG "
                        + " set "
                        + " procesar=? "
                        + " where "
                        + " codigoProcedimiento=?";
                ps = con.prepareStatement(query);
                ps.setInt(contadorParam++, interopJobsFSEProcedi.getProcesar());
                ps.setString(contadorParam++, interopJobsFSEProcedi.getCodigoProcedimiento());
                log.info("sql = " + query);
                log.info("params = "
                        + interopJobsFSEProcedi.toString()
                );
                resultado = ps.executeUpdate() > 0;
            } else {
                log.error("Objeto  a insertar/modificar recibido a null, no se procesa, respuesta devuelta : null");
            }
        } catch (SQLException e) {
            log.error("SQLException - actualizarInteropJobsFSEProcedi - ", e);
            throw e;
        } catch (Exception e) {
            log.error("Exception - actualizarInteropJobsFSEProcedi - ", e);
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
        log.info(" actualizarInteropJobsFSEProcedi - End " + formatFechaLog.format(new Date()));
        return resultado;
    }

    public List<InteropJobsFSEExpedienteRequest> getExpedientesTratarFSEByProcedimiento(InteropJobsFSEProcedi procedimiento, Connection con) throws SQLException, Exception {
        log.info(" getExpedientesTratarFSEByProcedimiento - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsFSEExpedienteRequest> resultado = new ArrayList<InteropJobsFSEExpedienteRequest>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT  distinct " +
                            " e.exp_eje ejercicio  " +
                            " ,e.exp_pro codigoProcedimiento  " +
                            " ,e.exp_num numeroExpediente  " +
                            " ,e.exp_fei fechaInicioExpediente    " +
                            " ,e.exp_fef fechaFinExpediente  " +
                            " ,e.exp_est estadoExpediente  " +
                            " ,case  " +
                            "    when ijfp.CODCAMPOTRAMFECRESOL is not null then frt.tfet_valor  " +
                            "    else fre.tfe_valor  " +
                            " end as fechaResolucionExpediente   " +
                            " ,case   " +
                            "    when frex.tfe_valor is not null then frex.tfe_valor  " +
                            "    else e.exp_fei  " +
                            " end as fechaReferenciaExpedienteConv   " +
                            " ,te.cro_fei fechaInicioTramiteNotifResol  " +
                            " ,te.cro_fef fechaFinTramiteNotifResol  " +
                            " ,convocatorias.COD_SERVICIO_LANB, convocatorias.COD_SERVICIO_CATGV " +
                            " FROM E_EXP e   " +
                            " inner join interop_jobs_fse_procedi ijfp on e.exp_pro=ijfp.codigoprocedimiento and ijfp.procesar=1 " +
                            " inner join e_tra td on td.tra_pro=ijfp.codigoprocedimiento and td.tra_cou=ijfp.codvistramnotifresol  " +
                            " inner join e_cro te on te.cro_mun=e.exp_mun and te.cro_eje=e.exp_eje and te.cro_pro=e.exp_pro and te.cro_num=e.exp_num and te.cro_tra=td.tra_cod and te.cro_fef is not null  " +
                            " LEFT join e_tfet frt on frt.tfet_mun=te.cro_mun and frt.tfet_eje=te.cro_eje and frt.tfet_pro=te.cro_pro and frt.tfet_num=te.cro_num and frt.tfet_tra=te.cro_tra and frt.tfet_ocu=te.cro_ocu and frt.tfet_cod=ijfp.codcampotramfecresol  " +
                            " LEFT join e_tfe fre on fre.tfe_mun=e.exp_mun and fre.tfe_eje=e.exp_eje and fre.tfe_num=e.exp_num and fre.tfe_cod=ijfp.codcampoexpfecresol  " +
                            " left join e_tra tdc on tdc.tra_pro=ijfp.codigoprocedimiento and tdc.tra_cou=ijfp.codvistramexpconcedido  " +
                            " left join (select cro2.cro_mun,cro2.cro_pro,cro2.cro_eje,cro2.cro_num,cro2.cro_tra,max(cro2.cro_ocu) cro_ocu from e_cro cro2  " +
                            "              where cro2.cro_pro in (select p.codigoProcedimiento from interop_jobs_fse_procedi p where p.procesar=1)  " +
                            "              group by cro2.cro_mun,cro2.cro_pro,cro2.cro_eje,cro2.cro_num,cro2.cro_tra)  " +
                            "    tec on tec.cro_mun=e.exp_mun and tec.cro_eje=e.exp_eje and tec.cro_pro=e.exp_pro and tec.cro_num=e.exp_num and tec.cro_tra=tdc.tra_cod   " +
                            " left join e_tfe frex on frex.tfe_mun=e.exp_mun and frex.tfe_eje=e.exp_eje and frex.tfe_num=e.exp_num and frex.tfe_cod=ijfp.codcampofecharefexpediente  " +
                            " left join e_tde de on de.tde_mun=e.exp_mun and de.tde_eje=e.exp_eje and de.tde_num=e.exp_num and de.tde_cod=ijfp.codcampoconcedido  " +
                            " left join e_tdet det on det.tdet_mun=e.exp_mun and det.tdet_pro=e.exp_pro and det.tdet_eje=e.exp_eje and det.tdet_num=e.exp_num and det.tdet_tra=tec.cro_tra and det.tdet_ocu=tec.cro_ocu and det.tdet_cod=ijfp.codcampoconcedido  " +
                            // -- Solo los expedientes que tengan convocatorias registradas 
                            " inner join MELANBIDE_CONVOCATORIAS convocatorias on convocatorias.pro_cod=e.exp_pro  " +
                            "        and convocatorias.decretoFecEntradaVigor <= (case  when frex.tfe_valor is not null then frex.tfe_valor else e.exp_fei end)   " +
                            "        and (nvl(convocatorias.decretoFecFinAplicacion,sysdate)) > (case  when frex.tfe_valor is not null then frex.tfe_valor else e.exp_fei end) " +
                            " LEFT JOIN interop_jobs_fse_expts_proce IJFEP ON ijfep.numeroExpediente=e.exp_num and ijfep.nombreprocesojobbatch='InteropJobsAltaServicioConvocatoriaFSE' " +
                            " WHERE   " +
                            " e.exp_est != 1 " +
                            " and e.exp_pro=? " +
                            // -- Solo expedientes que no se hayan procesado 100%
                            "  and  ( ijfep.expedienteprocesadocompl is null or  ijfep.expedienteprocesadocompl=0)  " +
                            //"-- Apartado para validar si expediente esta o no concedido " +
                            //"-- ECA se debe revisar por programacion Asignamos 1 a todos y validamos en codigo fuente " +
                            " and (case   " +
                            "    when e.exp_pro='ECA' THEN 1   " +
                            "    WHEN det.tdet_valor is not null AND det.tdet_valor=ijfp.valorcampoexpconcedido THEN 1  " +
                            "    when de.tde_valor is not null AND de.tde_valor=ijfp.valorcampoexpconcedido THEN 1  " +
                            "    else 0  " +
                            "    end  " +
                            " )=1  " +
                            " order by ejercicio,numeroExpediente"
                    ;
                    
                    /*" SELECT " +
                            " e.exp_eje ejercicio" +
                            " ,e.exp_pro codigoProcedimiento" +
                            " ,e.exp_num numeroExpediente" +
                            " ,e.exp_fei fechaInicioExpediente  " +
                            " ,e.exp_fef fechaFinExpediente" +
                            " ,e.exp_est estadoExpediente" +
                            " ,case" +
                            "    when ijfp.CODCAMPOTRAMFECRESOL is not null then frt.tfet_valor" +
                            "    else fre.tfe_valor" +
                            " end as fechaResolucionExpediente " +
                            " ,case " +
                            "    when frex.tfe_valor is not null then frex.tfe_valor" +
                            "    else e.exp_fei" +
                            " end as fechaReferenciaExpedienteConv " +
                            " ,te.cro_fei fechaInicioTramiteNotifResol" +
                            " ,te.cro_fef fechaFinTramiteNotifResol " +
                            " FROM E_EXP e " +
                            " inner join interop_jobs_fse_procedi ijfp on e.exp_pro=ijfp.codigoprocedimiento" +
                            " inner join e_tra td on td.tra_pro=ijfp.codigoprocedimiento and td.tra_cou=ijfp.codvistramnotifresol" +
                            " inner join e_cro te on te.cro_mun=e.exp_mun and te.cro_eje=e.exp_eje and te.cro_pro=e.exp_pro and te.cro_num=e.exp_num and te.cro_tra=td.tra_cod and te.cro_fef is not null" +
                            " LEFT join e_tfet frt on frt.tfet_mun=te.cro_mun and frt.tfet_eje=te.cro_eje and frt.tfet_pro=te.cro_pro and frt.tfet_num=te.cro_num and frt.tfet_tra=te.cro_tra and frt.tfet_ocu=te.cro_ocu and frt.tfet_cod=ijfp.codcampotramfecresol" +
                            " LEFT join e_tfe fre on fre.tfe_mun=e.exp_mun and fre.tfe_eje=e.exp_eje and fre.tfe_num=e.exp_num and fre.tfe_cod=ijfp.codcampoexpfecresol" +
                            " left join e_tra tdc on tdc.tra_pro=ijfp.codigoprocedimiento and tdc.tra_cou=ijfp.codvistramexpconcedido" +
                            " left join (select cro2.cro_mun,cro2.cro_pro,cro2.cro_eje,cro2.cro_num,cro2.cro_tra,max(cro2.cro_ocu) cro_ocu from e_cro cro2 "
                            + "             where cro2.cro_pro=? "
                            + "             group by cro2.cro_mun,cro2.cro_pro,cro2.cro_eje,cro2.cro_num,cro2.cro_tra)" +
                            "    tec on tec.cro_mun=e.exp_mun and tec.cro_eje=e.exp_eje and tec.cro_pro=e.exp_pro and tec.cro_num=e.exp_num and tec.cro_tra=tdc.tra_cod " +
                            " left join e_tfe frex on frex.tfe_mun=e.exp_mun and frex.tfe_eje=e.exp_eje and frex.tfe_num=e.exp_num and frex.tfe_cod=ijfp.codcampofecharefexpediente" +
                            " left join e_tde de on de.tde_mun=e.exp_mun and de.tde_eje=e.exp_eje and de.tde_num=e.exp_num and de.tde_cod=ijfp.codcampoconcedido" +
                            " left join e_tdet det on det.tdet_mun=e.exp_mun and det.tdet_pro=e.exp_pro and det.tdet_eje=e.exp_eje and det.tdet_num=e.exp_num and det.tdet_tra=tec.cro_tra and det.tdet_ocu=tec.cro_ocu and det.tdet_cod=ijfp.codcampoconcedido" +
                            " WHERE " +
                            " e.exp_est != 1" +
                            " and e.exp_pro=? " +
                            // Apartado para validar si expediente esta o no concedido
                            // ECA se debe revisar por programacion Asignamos 1 a todos y validamos en codigo fuente
                            " and (case " +
                            "    when e.exp_pro='ECA' THEN 1 " +
                            "    WHEN det.tdet_valor is not null AND det.tdet_valor=ijfp.valorcampoexpconcedido THEN 1" +
                            "    when de.tde_valor is not null AND de.tde_valor=ijfp.valorcampoexpconcedido THEN 1" +
                            "    else 0" +
                            "    end" +
                            " )=1" +
                            " and ( e.exp_eje>2020  and e.exp_num in('2021/REPLE/000150','2022/LAK/000003','2021/LAK/000003')) " +
                            " order by ejercicio,numeroExpediente"
                    ;*/
            int contadorParam = 1;
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            ps.setString(contadorParam++, procedimiento.getCodigoProcedimiento());
            log.info("params = "
                        + procedimiento.getCodigoProcedimiento()
                );
            rs = ps.executeQuery();
            // Parametros Fijos en Properties invocar al servicio Leemos solo una vez
            String cod_centro_usu = interopJobsUtils.getParameter(interopJobsUtils.FSE_CODIGO_CENTRO, interopJobsUtils.FICHERO_PROPIEDADES);
            String cod_ubic_usu = interopJobsUtils.getParameter(interopJobsUtils.FSE_CODIGO_UBICACION_CENTRO, interopJobsUtils.FICHERO_PROPIEDADES);
            String dem_servs_centro = interopJobsUtils.getParameter(interopJobsUtils.FSE_CENTRO_SERVICIO, interopJobsUtils.FICHERO_PROPIEDADES);
            String tipo_doc_usuario = interopJobsUtils.getParameter(interopJobsUtils.FSE_TIPO_DOC_USUARIO_LLAMADA, interopJobsUtils.FICHERO_PROPIEDADES);
            String num_doc_usuario = interopJobsUtils.getParameter(interopJobsUtils.FSE_IDENTIFICADOR_USUARIO_LLAMADA, interopJobsUtils.FICHERO_PROPIEDADES);
            String origen = interopJobsUtils.getParameter(interopJobsUtils.FSE_CONSTANTE_ORIGEN_ALTASERVICIO, interopJobsUtils.FICHERO_PROPIEDADES);
            String dem_servs_resultado = interopJobsUtils.getParameter(interopJobsUtils.FSE_CODIGO_RESULTADO_FAVORABLE, interopJobsUtils.FICHERO_PROPIEDADES);
            String num_doc_orientador = interopJobsUtils.getParameter(interopJobsUtils.FSE_IDENTIFICADOR_ORIENTADOR, interopJobsUtils.FICHERO_PROPIEDADES);
            String dem_servs_servicio = interopJobsUtils.getParameter(interopJobsUtils.FSE_DEM_SERVS_SERVICIO, interopJobsUtils.FICHERO_PROPIEDADES);
            String dem_servs_subservicio = interopJobsUtils.getParameter(interopJobsUtils.FSE_DEM_SERVS_SUBSERVICIO, interopJobsUtils.FICHERO_PROPIEDADES);
            while (rs.next()) {
                InteropJobsFSEExpedienteRequest expedienteRequest = new InteropJobsFSEExpedienteRequest( 
                        rs.getInt("ejercicio")
                        ,rs.getString("codigoProcedimiento")
                        ,rs.getString("numeroExpediente")
                        ,rs.getDate("fechaInicioExpediente")
                        ,rs.getDate("fechaFinExpediente")
                        ,rs.getInt("estadoExpediente")
                        ,rs.getDate("fechaReferenciaExpedienteConv")
                        ,rs.getDate("fechaResolucionExpediente")
                        ,rs.getDate("fechaInicioTramiteNotifResol")
                        ,rs.getDate("fechaFinTramiteNotifResol")
                );
                expedienteRequest.setCodigoCentroUsuario(cod_centro_usu);
                expedienteRequest.setCodigoUbicacionUsuario(cod_ubic_usu);
                expedienteRequest.setDemServCentro(dem_servs_centro);
                expedienteRequest.setTipoDocUsuario(tipo_doc_usuario);
                expedienteRequest.setNumDocUsuario(num_doc_usuario);
                expedienteRequest.setOrigen(origen);
                expedienteRequest.setDemServsResultado(dem_servs_resultado);
                expedienteRequest.setNumDocOrientador(num_doc_orientador);
                expedienteRequest.setDemServsServicio(dem_servs_servicio);
                expedienteRequest.setDemServsSubservicio(dem_servs_subservicio);
                MeLanbideConvocatorias convocatoriaExpediente = meLanbideConvocatoriasDAO.getDecretoAplicableExpediente(expedienteRequest.getFechaReferenciaExpedienteConv(),expedienteRequest.getCodigoProcedimiento(), con);
                if(convocatoriaExpediente!=null){
                    expedienteRequest.setCodigoServicioLanbide(convocatoriaExpediente.getCodServicioLanb());
                    expedienteRequest.setCodigoServicioCatalogoGV(convocatoriaExpediente.getCodServicioCatGV());
                }
                expedienteRequest.setPersonasServicio(getPersonasServicioFSEByProcedimiento(procedimiento, expedienteRequest, con));
                resultado.add(expedienteRequest);
            }
        } catch (SQLException e) {
            log.error("getExpedientesTratarFSEByProcedimiento  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getExpedientesTratarFSEByProcedimiento - Exception ", e);
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
        log.info(" getExpedientesTratarFSEByProcedimiento - End " + formatFechaLog.format(new Date()));
        return resultado;
    }

    private List<InteropJobsFSEPersonaServicio> getPersonasServicioFSEByProcedimiento(InteropJobsFSEProcedi procedimiento,InteropJobsFSEExpedienteRequest expedienteRequest, Connection con) throws SQLException, Exception {
        log.info(" getPersonasServicioFSEByProcedimiento - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsFSEPersonaServicio> resultado = new ArrayList<InteropJobsFSEPersonaServicio>();
        try {
            if (expedienteRequest != null) {
                // Procedimientos a los que lee uno de los roles Configurado en la tabla de INTEROP_JOBS_FSE_PROCEDI
                if(procedimiento!=null && procedimiento.getCodRolPersonaServicio()>0){
                    resultado=getListaFSEPersonasServicioxExpteRol(expedienteRequest,procedimiento,con);
                }else if("AEXCE".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){
                }else if("CEESC".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){
                }else if("DISCT".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){
                }else if("DLDUR".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){
                }else if("ECA".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){                    
                }else if("GEL".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){  
                    resultado=getFSEPersonasServicioxME82_GEL(expedienteRequest,con);
                }else if("LAK".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){ 
                    String codCampSupl = interopJobsUtils.getParameter(interopJobsUtils.FSE_CODIGO_CAMPOSUPLE_DOCUMENTO_PERSONA
                            +interopJobsUtils.BARRA_SEPARADORA
                            +expedienteRequest.getCodigoProcedimiento(), interopJobsUtils.FICHERO_PROPIEDADES);
                    resultado=getFSEPersonasServicioxCampSupExpteTxt(expedienteRequest,codCampSupl,con);
                }else if("LPEAL".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){                    
                }else if("LPEEL".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){                    
                }else if("LPEPE".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){                    
                }else if("REJUD".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){                    
                }else if("REJUV".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){                    
                }else if("REPLE".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){
                    resultado=getFSEPersonasServicioxME61_REPLE(expedienteRequest,con);
                }else if("UAAP".equalsIgnoreCase(expedienteRequest.getCodigoProcedimiento())){                    
                }
           } else {
                log.error("Objeto  expedienteRequest recibido a null, no se procesa, lista respuesta devuelta vacia ");
            }
        } catch (SQLException e) {
            log.error("SQLException - getPersonasServicioFSEByProcedimiento - ", e);
            throw e;
        } catch (Exception e) {
            log.error("Exception - getPersonasServicioFSEByProcedimiento - ", e);
            throw e;
        } finally {
        }
        log.info(" getPersonasServicioFSEByProcedimiento - End " + formatFechaLog.format(new Date()));
        return resultado;
    }

    private List<InteropJobsFSEPersonaServicio> getListaFSEPersonasServicioxExpteRol(InteropJobsFSEExpedienteRequest expedienteRequest, InteropJobsFSEProcedi procedimiento, Connection con) throws SQLException, Exception {
        log.info(" getListaFSEPersonasServicioxExpteRol - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsFSEPersonaServicio> resultado = new ArrayList<InteropJobsFSEPersonaServicio>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if(expedienteRequest!=null && procedimiento!=null){
                String query = "SELECT EXT_NUM numeroExpediente, "
                        + " HTE_TID tipoDocumentoRegexlan, "
                        + " CASE  "
                        + "    when HTE_TID=1 then 'D' "
                        + "    WHEN HTE_TID=3 THEN 'E' "
                        + "    WHEN HTE_TID=8 THEN 'U' "
                        + "    WHEN HTE_TID=9 THEN 'W' "
                        + " END tipoDocumetoLanbide,  "
                        + " UPPER(hte_doc) numeroDocumento, "
                        + " NULL fechaInicioServicio, "
                        + " NULL fechaFinServicio, "
                        + " '' resultado "
                        + " FROM  "
                        + " E_EXT  "
                        + " LEFT JOIN T_HTE ON HTE_TER=EXT_TER AND HTE_NVR=EXT_NVR "
                        + " left join (select numeroexpediente,documentoid, sum(personaProcesada) personaProcesada from  interop_jobs_log group by numeroexpediente,documentoid)  " 
                        + "    ijl on e_ext.ext_num=ijl.numeroexpediente and  t_hte.hte_doc=ijl.documentoid "
                        + " WHERE  "
                        + " EXT_NUM = ? "
                        + " AND EXT_ROL = ? "
                        + " and (nvl(ijl.personaProcesada,0))=0"
                        ;
                log.info("sql = " + query);
                ps = con.prepareStatement(query);
                int contparam = 1;
                ps.setString(contparam++, expedienteRequest.getNumeroExpediente());
                ps.setInt(contparam++, procedimiento.getCodRolPersonaServicio());
                log.info("params= " + expedienteRequest.getNumeroExpediente()
                        + ", " + procedimiento.getCodRolPersonaServicio()
                );
                rs = ps.executeQuery();
                while (rs.next()) {
                    InteropJobsFSEPersonaServicio per = interopJobsDaoMapping.getInteropJobsFSEPersonaServicio(rs);
                    if (expedienteRequest.getFechaResolucionExpediente()!=null) {
                        per.setFechaInicioServicio(expedienteRequest.getFechaResolucionExpediente());
                        per.setFechaFinServicio(expedienteRequest.getFechaResolucionExpediente());
                    }else{
                        per.setFechaInicioServicio(expedienteRequest.getFechaInicioTramiteNotifResol());
                        per.setFechaFinServicio(expedienteRequest.getFechaFinTramiteNotifResol());
                    }
                    resultado.add(per);
                }
            }else{
                log.info("Elementos recibidos a null, no hay datos que recuperar...");
            }
        } catch (SQLException e) {
            log.error("getListaFSEPersonasServicioxExpteRol  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getListaFSEPersonasServicioxExpteRol - Exception ", e);
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
        log.info(" getListaFSEPersonasServicioxExpteRol - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    private List<InteropJobsFSEPersonaServicio> getFSEPersonasServicioxCampSupExpteTxt(InteropJobsFSEExpedienteRequest expedienteRequest, String codCampoSup, Connection con) throws SQLException, Exception {
        log.info(" getFSEPersonasServicioxCampSupExpteTxt - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsFSEPersonaServicio> resultado = new ArrayList<InteropJobsFSEPersonaServicio>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if(codCampoSup!=null && expedienteRequest!=null){
                String query = "SELECT  "+
                                " TXT_NUM numeroExpediente, " +
                                " CASE " +
                                "    WHEN REGEXP_INSTR(UPPER(TXT_VALOR) ,'^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]') > 0 THEN 1 " +
                                "    WHEN REGEXP_INSTR(UPPER(TXT_VALOR) ,'^[KLMXYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKE]') > 0 THEN 3 " +
                                "    WHEN REGEXP_INSTR(UPPER(TXT_VALOR) ,'^[TRWAGMYFPDXBNJZSQVHLCKE]{1}[0-9]{8}') > 0 THEN 4 " +
                                "    ELSE 0 " +
                                " END tipoDocumentoRegexlan, " +
                                " CASE " +
                                "    WHEN REGEXP_INSTR(UPPER(TXT_VALOR) ,'^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]') > 0 THEN 'D' " +
                                "    WHEN REGEXP_INSTR(UPPER(TXT_VALOR) ,'^[KLMXYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKE]') > 0 THEN 'E' " +
                                " ELSE '' END tipoDocumetoLanbide,  " +
                                " UPPER(TXT_VALOR) numeroDocumento, " +
                                " NULL fechaInicioServicio, " +
                                " NULL fechaFinServicio, " +
                                " '' resultado " +
                                " FROM  E_TXT  " 
                                + " left join (select numeroexpediente,documentoid, sum(personaProcesada) personaProcesada from  interop_jobs_log group by numeroexpediente,documentoid)  "
                                + "    ijl on E_TXT.TXT_NUM=ijl.numeroexpediente and  E_TXT.TXT_VALOR=ijl.documentoid " 
                                + " WHERE " +
                                " TXT_NUM = ? " +
                                " AND TXT_COD = ?"
                                + " and (nvl(ijl.personaProcesada,0))=0 "
                        ;
                log.info("sql = " + query);
                ps = con.prepareStatement(query);
                int contparam = 1;
                ps.setString(contparam++, expedienteRequest.getNumeroExpediente());
                ps.setString(contparam++, codCampoSup);
                log.info("params= " + expedienteRequest.getNumeroExpediente()
                        + ", " + codCampoSup
                );
                rs = ps.executeQuery();
                while (rs.next()) {
                    InteropJobsFSEPersonaServicio per   = interopJobsDaoMapping.getInteropJobsFSEPersonaServicio(rs);
                    if (expedienteRequest.getFechaResolucionExpediente()!=null) {
                        per.setFechaInicioServicio(expedienteRequest.getFechaResolucionExpediente());
                        per.setFechaFinServicio(expedienteRequest.getFechaResolucionExpediente());
                    }else{
                        per.setFechaInicioServicio(expedienteRequest.getFechaInicioTramiteNotifResol());
                        per.setFechaFinServicio(expedienteRequest.getFechaFinTramiteNotifResol());
                    }
                    resultado.add(per);
                }
            }else{
                log.info("Elementos recibidos a null, no hay datos que recuperar...");
            }
        } catch (SQLException e) {
            log.error("getFSEPersonasServicioxCampSupExpteTxt  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getFSEPersonasServicioxCampSupExpteTxt - Exception ", e);
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
        log.info(" getFSEPersonasServicioxCampSupExpteTxt - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    private List<InteropJobsFSEPersonaServicio> getFSEPersonasServicioxME82_GEL(InteropJobsFSEExpedienteRequest expedienteRequest, Connection con) throws SQLException, Exception {
        log.info(" getFSEPersonasServicioxME82_GEL - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsFSEPersonaServicio> resultado = new ArrayList<InteropJobsFSEPersonaServicio>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if(expedienteRequest!=null){
                String query = "select NUM_EXP numeroExpediente, " +
                                " CASE " +
                                "    WHEN REGEXP_INSTR(UPPER(DNINIE3) ,'^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]') > 0 THEN 1 " +
                                "    WHEN REGEXP_INSTR(UPPER(DNINIE3) ,'^[KLMXYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKE]') > 0 THEN 3 " +
                                "    WHEN REGEXP_INSTR(UPPER(DNINIE3) ,'^[TRWAGMYFPDXBNJZSQVHLCKE]{1}[0-9]{8}') > 0 THEN 4 " +
                                "    ELSE 0 " +
                                " END tipoDocumentoRegexlan, " +
                                " CASE " +
                                "    WHEN REGEXP_INSTR(UPPER(DNINIE3) ,'^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]') > 0 THEN 'D' " +
                                "    WHEN REGEXP_INSTR(UPPER(DNINIE3) ,'^[KLMXYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKE]') > 0 THEN 'E' " +
                                " ELSE '' END tipoDocumetoLanbide,  " +
                                " UPPER(DNINIE3) numeroDocumento, " +
                                " NULL fechaInicioServicio, " +
                                " NULL fechaFinServicio, " +
                                " '' resultado " +
                                " from melanbide82_contratacion_fin "
                                + " left join (select numeroexpediente,documentoid, sum(personaProcesada) personaProcesada from  interop_jobs_log group by numeroexpediente,documentoid)  "
                                + "    ijl on melanbide82_contratacion_fin.NUM_EXP=ijl.numeroexpediente and  melanbide82_contratacion_fin.DNINIE3=ijl.documentoid "
                                + " where " +
                                " NUM_EXP = ? "
                                + " and (nvl(ijl.personaProcesada,0))=0 " +
                                " order by id "
                        ;
                log.info("sql = " + query);
                ps = con.prepareStatement(query);
                int contparam = 1;
                ps.setString(contparam++, expedienteRequest.getNumeroExpediente());
                log.info("params= " + expedienteRequest.getNumeroExpediente()
                );
                rs = ps.executeQuery();
                while (rs.next()) {
                    InteropJobsFSEPersonaServicio per   = interopJobsDaoMapping.getInteropJobsFSEPersonaServicio(rs);
                    if (expedienteRequest.getFechaResolucionExpediente()!=null) {
                        per.setFechaInicioServicio(expedienteRequest.getFechaResolucionExpediente());
                        per.setFechaFinServicio(expedienteRequest.getFechaResolucionExpediente());
                    }else{
                        per.setFechaInicioServicio(expedienteRequest.getFechaInicioTramiteNotifResol());
                        per.setFechaFinServicio(expedienteRequest.getFechaFinTramiteNotifResol());
                    }
                    resultado.add(per);
                }
            }else{
                log.info("Elementos recibidos a null, no hay datos que recuperar...");
            }
        } catch (SQLException e) {
            log.error("getFSEPersonasServicioxME82_GEL  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getFSEPersonasServicioxME82_GEL - Exception ", e);
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
        log.info(" getFSEPersonasServicioxME82_GEL - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
    
    private List<InteropJobsFSEPersonaServicio> getFSEPersonasServicioxME61_REPLE(InteropJobsFSEExpedienteRequest expedienteRequest,Connection con) throws SQLException, Exception {
        log.info(" getFSEPersonasServicioxME61_REPLE - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsFSEPersonaServicio> resultado = new ArrayList<InteropJobsFSEPersonaServicio>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if(expedienteRequest!=null){
                String query = "select PEXCO_NUM numeroExpediente, " +
                                " CASE " +
                                "    WHEN REGEXP_INSTR(UPPER(PEXCO_DOC) ,'^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]') > 0 THEN 1 " +
                                "    WHEN REGEXP_INSTR(UPPER(PEXCO_DOC) ,'^[KLMXYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKE]') > 0 THEN 3 " +
                                "    WHEN REGEXP_INSTR(UPPER(PEXCO_DOC) ,'^[TRWAGMYFPDXBNJZSQVHLCKE]{1}[0-9]{8}') > 0 THEN 4 " +
                                "    ELSE 0 " +
                                " END tipoDocumentoRegexlan, " +
                                " CASE " +
                                "    WHEN REGEXP_INSTR(UPPER(PEXCO_DOC) ,'^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]') > 0 THEN 'D' " +
                                "    WHEN REGEXP_INSTR(UPPER(PEXCO_DOC) ,'^[KLMXYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKE]') > 0 THEN 'E' " +
                                " ELSE '' END tipoDocumetoLanbide,  " +
                                " UPPER(PEXCO_DOC) numeroDocumento, " +
                                " NULL fechaInicioServicio, " +
                                " NULL fechaFinServicio, " +
                                " '' resultado " +
                                " from reple_pexco" 
                                + " left join (select numeroexpediente,documentoid, sum(personaProcesada) personaProcesada from  interop_jobs_log group by numeroexpediente,documentoid)  " 
                                + "    ijl on reple_pexco.PEXCO_NUM=ijl.numeroexpediente and  reple_pexco.PEXCO_DOC=ijl.documentoid "
                                + " where " +
                                " PEXCO_CTPE=2 " +
                                " AND PEXCO_NUM = ? "
                                + " and (nvl(ijl.personaProcesada,0))=0 " +
                                " order by PEXCO_NCON,PEXCO_FEINICON,(PEXCO_NOM || PEXCO_AP1) "
                        ;
                log.info("sql = " + query);
                ps = con.prepareStatement(query);
                int contparam = 1;
                ps.setString(contparam++, expedienteRequest.getNumeroExpediente());
                log.info("params= " + expedienteRequest.getNumeroExpediente()
                );
                rs = ps.executeQuery();
                while (rs.next()) {
                    InteropJobsFSEPersonaServicio per   = interopJobsDaoMapping.getInteropJobsFSEPersonaServicio(rs);
                    if (expedienteRequest.getFechaResolucionExpediente()!=null) {
                        per.setFechaInicioServicio(expedienteRequest.getFechaResolucionExpediente());
                        per.setFechaFinServicio(expedienteRequest.getFechaResolucionExpediente());
                    }else{
                        per.setFechaInicioServicio(expedienteRequest.getFechaInicioTramiteNotifResol());
                        per.setFechaFinServicio(expedienteRequest.getFechaFinTramiteNotifResol());
                    }
                    resultado.add(per);
                }
            }else{
                log.info("Elementos recibidos a null, no hay datos que recuperar...");
            }
        } catch (SQLException e) {
            log.error("getFSEPersonasServicioxME61_REPLE  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getFSEPersonasServicioxME61_REPLE - Exception ", e);
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
        log.info(" getFSEPersonasServicioxME61_REPLE - End " + formatFechaLog.format(new Date()));
        return resultado;
    }
}
