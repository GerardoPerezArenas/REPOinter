/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.dao;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager.InteropJobsUtils;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsAKSSGAConfig;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsAKSSGADocumento;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsAKSSGAExpedienteRequest;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.MelanbideDokusiPlateaPro;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlTypeValue;

/**
 *
 * @author INGDGC
 */
public class InteropJobsAKSSGAConfigDAO {

    private static final Logger log = Logger.getLogger(InteropJobsAKSSGAConfigDAO.class);

    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static final SimpleDateFormat formatFechaSGAYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    private final InteropJobsDaoMapping interopJobsDaoMapping = new InteropJobsDaoMapping();
    private final InteropJobsUtils interopJobsUtils = new InteropJobsUtils();

    public List<InteropJobsAKSSGAConfig> getProcedimientosProcesarAKSSGA(Connection con) throws SQLException, Exception {
        log.info(" getProcedimientosProcesarAKSSGA - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsAKSSGAConfig> resultado = new ArrayList<InteropJobsAKSSGAConfig>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * "
                    + " from SGA_CONFIG "
                    + " where procesarProcedEnBatch=1 "
                    + " order by CODPROC ";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(interopJobsDaoMapping.getInteropJobsAKSSGAConfig(rs));
            }
        } catch (SQLException e) {
            log.error("getProcedimientosProcesarAKSSGA  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getProcedimientosProcesarAKSSGA - Exception ", e);
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
        log.info(" getProcedimientosProcesarAKSSGA - End " + formatFechaLog.format(new Date()));
        return resultado;
    }

    public List<InteropJobsAKSSGAExpedienteRequest> getExpedientesTratarAKSSGAByProcedimiento(InteropJobsAKSSGAConfig procedimiento, Connection con) throws Exception {
        log.info(" getExpedientesTratarAKSSGAByProcedimiento - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsAKSSGAExpedienteRequest> resultado = new ArrayList<InteropJobsAKSSGAExpedienteRequest>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select datos.* from ( "
                    + " select ROWNUM as nroFila, datosExp.* from ( "
                    + " SELECT distinct E_EXP.* ,SGA_CONFIG.* "
                    + " ,(hte_doc || '-'|| hte_nom || NVL2(TRIM(hte_ap1), ' ' || hte_ap1, '') || NVL2(TRIM(hte_ap2), ' ' || hte_ap2, '')) expedienteTitulo  "
                    + " ,mun_nom expedienteLugares "
                    + " ,FECIERDOC_PRO.PCA_COD existeCampoFECIERDOC, FECIERDOC_EXP.TFE_VALOR valorCampoFECIERDOC "
                    + " FROM E_EXP "
                    + " INNER JOIN (select DISTINCT documentosExpediente.expediente,documentosExpediente.docOID from ( "
                    + "    SELECT 'DTRA' docOrigen,crd_num expediente, crd_tra||'_'||crd_ocu||'_'||crd_nud docCodigoID, crd_des docNombre, reldoc_oid docOID "
                    + "    from e_crd left join melanbide_dokusi_reldoc_tramit on crd_mun=RELDOC_MUN AND crd_eje=RELDOC_EJE AND crd_num=RELDOC_NUM AND crd_tra=RELDOC_tra and crd_ocu=reldoc_ocu and crd_nud=reldoc_nud "
                    + "    union all  "
                    + "    SELECT 'DEXT' docOrigen,doc_ext_num expediente, to_char(doc_ext_cod) docCodigoID, doc_ext_nom docNombre,reldoc_oid docOID "
                    + "    FROM e_doc_ext LEFT JOIN melanbide_dokusi_reldoc_docext on doc_ext_num=reldoc_num AND DOC_EXT_COD=reldoc_cod "
                    + "    union all  "
                    + "    SELECT 'DPRE' docOrigen, presentado_num expediente, presentado_cod_doc||'_'||presentado_cod docCodigoID, presentado_nombre docNombre,reldoc_oid docOID "
                    + "    FROM e_docs_presentados LEFT JOIN melanbide_dokusi_reldoc_docpre on presentado_num=reldoc_num AND presentado_COD=reldoc_cod "
                    + "    union all  "
                    + "    SELECT 'DCSE' docOrigen, TFI_num expediente, TFI_COD docCodigoID, TFI_NOMFICH docNombre, reldoc_oid docOID "
                    + "    FROM E_TFI LEFT JOIN melanbide_dokusi_reldoc_docCSE on TFI_num=reldoc_num AND TFI_COD=reldoc_cod "
                    + "    union all  "
                    + "    SELECT 'DCST' docOrigen, TFIT_num expediente, TFIT_TRA||'_'||TFIT_OCU||'_'||TFIT_COD docCodigoID, TFIT_NOMFICH docNombre,reldoc_oid docOID "
                    + "    FROM E_TFIT LEFT JOIN melanbide_dokusi_reldoc_docCST on TFIT_num=reldoc_num AND TFIT_TRA=RELDOC_TRA AND TFIT_OCU=RELDOC_OCU AND TFIT_COD=reldoc_cod "
                    + "    union all  "
                    + "    SELECT 'DREX' docOrigen, CRD_num expediente, crd_tra||'_'||crd_ocu||'_'||crd_nud docCodigoID, crd_des docNombre, reldoc_oid docOID "
                    + "    FROM G_CRD LEFT JOIN melanbide_dokusi_reldoc_relexp ON crd_mun=RELDOC_MUN AND crd_eje=RELDOC_EJE AND crd_num=RELDOC_NUM AND crd_tra=RELDOC_tra and crd_ocu=reldoc_ocu and crd_nud=reldoc_nud "
                    + "    union all  "
                    + "    SELECT 'DAEN' docOrigen, num_expediente expediente, cod_tramite||'_'||ocu_tramite||'_'||ID docCodigoID, NOMBRE docNombre, reldoc_oid docOID "
                    + "    FROM adjunto_ext_notificacion LEFT JOIN melanbide_dokusi_reldoc_extnot ON COD_MUNICIPIO=RELDOC_MUN AND num_expediente=RELDOC_NUM AND cod_tramite=RELDOC_tra and ocu_tramite=reldoc_ocu and ID=reldoc_ID "
                    + "    UNION ALL "
                    + "    SELECT 'DREG' docOrigen, EXR_NUM expediente,  RED_TIP||'_'||RED_EJE||'/'||RED_NUM||'_'||red_doc_id docCodigoID, RED_NOM_DOC docNombre, RED_IDDOC_GESTOR docOID "
                    + "    FROM r_red LEFT JOIN E_EXR ON RED_DEP=EXR_DEP AND RED_UOR=EXR_UOR AND RED_EJE=EXR_EJR AND RED_NUM=EXR_NRE AND RED_TIP=EXR_TIP "
                    + "    ) documentosExpediente "
                    + " ) docConOID ON docConOID.expediente=e_exp.exp_num and docConOID.docOID is not null "
                    + " LEFT JOIN SGA_CONFIG ON EXP_PRO=CODPROC "
                    + " left join e_ext on ext_mun=exp_mun and ext_eje=exp_eje and ext_pro=exp_pro and ext_num=exp_num and ext_rol=1 "
                    + " left join t_hte on hte_ter=ext_ter and hte_nvr=ext_nvr "
                    + " LEFT JOIN t_dnn ON dnn_dom=hte_dot "
                    + " LEFT JOIN t_mun ON dnn_mun=mun_cod AND dnn_prv=mun_prv AND dnn_pai=mun_pai "
                    + " LEFT JOIN E_PCA FECIERDOC_PRO ON FECIERDOC_PRO.PCA_PRO=SGA_CONFIG.CODPROC AND FECIERDOC_PRO.PCA_COD = 'FECIERDOC'  "
                    + " LEFT JOIN E_TFE FECIERDOC_EXP ON FECIERDOC_EXP.TFE_COD = FECIERDOC_PRO.PCA_COD AND FECIERDOC_EXP.TFE_NUM = EXP_NUM  "
                    + " WHERE  "
                    + " EXP_EST=9 "
                    + " and EXP_PRO=:codProcedimiento  "
                    + " AND To_date(to_char(e_exp.exp_fef,'dd/MM/yyy'),'dd/MM/yyyy') < to_date(to_char(ADD_MONTHS(SYSDATE,-NVL(MESES,0)),'dd/MM/yyyy'),'dd/MM/yyyy')  "
                    + " and (EXP_UBICACION_DOC is null or length(EXP_UBICACION_DOC)=0) "
                    + "ORDER BY EXP_PRO,EXP_EJE,EXP_NUM "
                    + " ) datosExp "
                    + " WHERE (datosExp.existeCampoFECIERDOC IS NULL  OR (datosExp.existeCampoFECIERDOC IS not NULL AND datosExp.valorCampoFECIERDOC IS not null)) "
                    + " ) datos where datos.nroFila<=limiteExpedientesProceso order by datos.EXP_NUM ";
            String queryHistorico = "select datos.*  " +
                    " from (select ROWNUM as nroFila, datosExp.* from (  " +
                    "        SELECT distinct H_EXP.* ,SGA_CONFIG.*  " +
                    "        ,(hte_doc || '-'|| hte_nom || NVL2(TRIM(hte_ap1), ' ' || hte_ap1, '') || NVL2(TRIM(hte_ap2), ' ' || hte_ap2, '')) expedienteTitulo   " +
                    "        ,mun_nom expedienteLugares  " +
                    "        ,FECIERDOC_PRO.PCA_COD existeCampoFECIERDOC, FECIERDOC_EXP.TFE_VALOR valorCampoFECIERDOC  " +
                    "        FROM HIST_E_EXP  H_EXP " +
                    "        INNER JOIN (select DISTINCT documentosExpediente.expediente,docOrigen,docCodigoID,documentosExpediente.docOID from (  " +
                    "           SELECT 'DTRA' docOrigen,crd_num expediente, crd_tra||'_'||crd_ocu||'_'||crd_nud docCodigoID, crd_des docNombre, reldoc_oid docOID  " +
                    "           from HIST_e_crd left join melanbide_dokusi_reldoc_tramit on crd_mun=RELDOC_MUN AND crd_eje=RELDOC_EJE AND crd_num=RELDOC_NUM AND crd_tra=RELDOC_tra and crd_ocu=reldoc_ocu and crd_nud=reldoc_nud  " +
                    "           union all   " +
                    "           SELECT 'DEXT' docOrigen,doc_ext_num expediente, to_char(doc_ext_cod) docCodigoID, doc_ext_nom docNombre,reldoc_oid docOID  " +
                    "           FROM HIST_e_doc_ext LEFT JOIN melanbide_dokusi_reldoc_docext on doc_ext_num=reldoc_num AND DOC_EXT_COD=reldoc_cod  " +
                    "           union all   " +
                    "           SELECT 'DPRE' docOrigen, presentado_num expediente, presentado_cod_doc||'_'||presentado_cod docCodigoID, presentado_nombre docNombre,reldoc_oid docOID  " +
                    "           FROM HIST_e_docs_presentados LEFT JOIN melanbide_dokusi_reldoc_docpre on presentado_num=reldoc_num AND presentado_COD=reldoc_cod  " +
                    "           union all   " +
                    "           SELECT 'DCSE' docOrigen, TFI_num expediente, TFI_COD docCodigoID, TFI_NOMFICH docNombre, reldoc_oid docOID  " +
                    "           FROM HIST_E_TFI LEFT JOIN melanbide_dokusi_reldoc_docCSE on TFI_num=reldoc_num AND TFI_COD=reldoc_cod  " +
                    "           union all   " +
                    "           SELECT 'DCST' docOrigen, TFIT_num expediente, TFIT_TRA||'_'||TFIT_OCU||'_'||TFIT_COD docCodigoID, TFIT_NOMFICH docNombre,reldoc_oid docOID  " +
                    "           FROM HIST_E_TFIT LEFT JOIN melanbide_dokusi_reldoc_docCST on TFIT_num=reldoc_num AND TFIT_TRA=RELDOC_TRA AND TFIT_OCU=RELDOC_OCU AND TFIT_COD=reldoc_cod  " +
                    "           union all   " +
                    "           SELECT 'DREX' docOrigen, CRD_num expediente, crd_tra||'_'||crd_ocu||'_'||crd_nud docCodigoID, crd_des docNombre, reldoc_oid docOID  " +
                    "           FROM G_CRD LEFT JOIN melanbide_dokusi_reldoc_relexp ON crd_mun=RELDOC_MUN AND crd_eje=RELDOC_EJE AND crd_num=RELDOC_NUM AND crd_tra=RELDOC_tra and crd_ocu=reldoc_ocu and crd_nud=reldoc_nud  " +
                    "           union all   " +
                    "           SELECT 'DAEN' docOrigen, num_expediente expediente, cod_tramite||'_'||ocu_tramite||'_'||ID docCodigoID, NOMBRE docNombre, reldoc_oid docOID  " +
                    "           FROM HIST_adjunto_ext_notificacion LEFT JOIN melanbide_dokusi_reldoc_extnot ON COD_MUNICIPIO=RELDOC_MUN AND num_expediente=RELDOC_NUM AND cod_tramite=RELDOC_tra and ocu_tramite=reldoc_ocu and ID=reldoc_ID  " +
                    "           UNION ALL  " +
                    "           SELECT 'DREG' docOrigen, EXR_NUM expediente,  RED_TIP||'_'||RED_EJE||'/'||RED_NUM||'_'||red_doc_id docCodigoID, RED_NOM_DOC docNombre, RED_IDDOC_GESTOR docOID  " +
                    "           FROM r_red LEFT JOIN HIST_E_EXR  ON RED_DEP=EXR_DEP AND RED_UOR=EXR_UOR AND RED_EJE=EXR_EJR AND RED_NUM=EXR_NRE AND RED_TIP=EXR_TIP  " +
                    "           ) documentosExpediente   " +
                    "        ) docConOID ON docConOID.expediente=H_EXP.exp_num and docConOID.docOID is not null  " +
                    "        LEFT JOIN SGA_CONFIG ON EXP_PRO=CODPROC  " +
                    "        left join HIST_e_ext on ext_mun=exp_mun and ext_eje=exp_eje and ext_pro=exp_pro and ext_num=exp_num and ext_rol=1  " +
                    "        left join t_hte on hte_ter=ext_ter and hte_nvr=ext_nvr  " +
                    "        LEFT JOIN t_dnn ON dnn_dom=hte_dot  " +
                    "        LEFT JOIN t_mun ON dnn_mun=mun_cod AND dnn_prv=mun_prv AND dnn_pai=mun_pai  " +
                    "        LEFT JOIN E_PCA FECIERDOC_PRO ON FECIERDOC_PRO.PCA_PRO=SGA_CONFIG.CODPROC AND FECIERDOC_PRO.PCA_COD = 'FECIERDOC'  " +
                    "        LEFT JOIN HIST_E_TFE FECIERDOC_EXP ON FECIERDOC_EXP.TFE_COD = FECIERDOC_PRO.PCA_COD AND FECIERDOC_EXP.TFE_NUM = EXP_NUM   " +
                    "        WHERE   " +
                    "        EXP_EST=9  " +
                    "        and EXP_PRO=:codProcedimiento   " +
                    "        AND To_date(to_char(H_EXP.exp_fef,'dd/MM/yyyy'),'dd/MM/yyyy') < to_date(to_char(ADD_MONTHS(SYSDATE,-NVL(MESES,0)),'dd/MM/yyyy'),'dd/MM/yyyy')   " +
                    "        and (EXP_UBICACION_DOC is null or length(EXP_UBICACION_DOC)=0)  " +
                    "        ORDER BY EXP_PRO,EXP_EJE,EXP_NUM  " +
                    "      ) datosExp  " +
                    "      WHERE (datosExp.existeCampoFECIERDOC IS NULL  OR (datosExp.existeCampoFECIERDOC IS not NULL AND datosExp.valorCampoFECIERDOC IS not null))  " +
                    "    ) datos  " +
                    " where datos.nroFila<=limiteExpedientesProceso  " +
                    " order by datos.EXP_NUM ";

            if(procedimiento.getProcesarExpedientesHistorico() == 1){
                query = queryHistorico;
            }


            log.info("sql = " + query);
            int contadorParam = 1;
            ps = con.prepareStatement(query);
            ps.setString(contadorParam, procedimiento.getCodigoProcedimiento());
            log.info("params = " + procedimiento.getCodigoProcedimiento());
            rs = ps.executeQuery();
            while (rs.next()) {
                InteropJobsAKSSGAExpedienteRequest expedienteRequest = interopJobsDaoMapping.getInteropJobsAKSSGAExpedienteRequest(rs);
                if (procedimiento.getProcesarExpedientesHistorico()==1)
                    expedienteRequest.setDocumentos(getDocumentosAKSSGAFromHistoricoByExpediente(expedienteRequest.getExpedienteNumero(), con));
                else
                    expedienteRequest.setDocumentos(getDocumentosAKSSGAByExpediente(expedienteRequest.getExpedienteNumero(), con));

                resultado.add(expedienteRequest);
            }
        } catch (SQLException e) {
            log.error("getExpedientesTratarAKSSGAByProcedimiento  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getExpedientesTratarAKSSGAByProcedimiento - Exception ", e);
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
        log.info(" getExpedientesTratarAKSSGAByProcedimiento - End " + formatFechaLog.format(new Date()));
        return resultado;
    }

    public List<InteropJobsAKSSGADocumento> getDocumentosAKSSGAByExpediente(String expediente, Connection con) throws Exception {
        log.info(" getDocumentosAKSSGAByExpediente - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsAKSSGADocumento> resultado = new ArrayList<InteropJobsAKSSGADocumento>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = " select documentosExpediente.* from ( "
                    + " SELECT 'DTRA' docOrigen,crd_num expediente, crd_tra||'_'||crd_ocu||'_'||crd_nud docCodigoID, crd_des docNombre, '' docMimeType,crd_fal docFecha,crd_FIL docContenidoRAW,NULL docContenidoBLOB, reldoc_oid docOID "
                    + " from e_crd left join melanbide_dokusi_reldoc_tramit on crd_mun=RELDOC_MUN AND crd_eje=RELDOC_EJE AND crd_num=RELDOC_NUM AND crd_tra=RELDOC_tra and crd_ocu=reldoc_ocu and crd_nud=reldoc_nud "
                    + " union all  "
                    + " SELECT 'DEXT' docOrigen,doc_ext_num expediente, to_char(doc_ext_cod) docCodigoID, doc_ext_nom docNombre, DOC_EXT_TIP docMimeType,DOC_EXT_FAL docFecha,DOC_EXT_FIL docContenidoRAW,NULL docContenidoBLOB,reldoc_oid docOID "
                    + " FROM e_doc_ext LEFT JOIN melanbide_dokusi_reldoc_docext on doc_ext_num=reldoc_num AND DOC_EXT_COD=reldoc_cod "
                    + " union all  "
                    + " SELECT 'DPRE' docOrigen, presentado_num expediente, presentado_cod_doc||'_'||presentado_cod docCodigoID, presentado_nombre docNombre, PRESENTADO_TIPO docMimeType,PRESENTADO_FECHA_ALTA docFecha,PRESENTADO_CONTENIDO docContenidoRAW, (NULL) docContenidoBLOB,reldoc_oid docOID "
                    + " FROM e_docs_presentados LEFT JOIN melanbide_dokusi_reldoc_docpre on presentado_num=reldoc_num AND presentado_COD=reldoc_cod "
                    + " union all  "
                    + " SELECT 'DCSE' docOrigen, TFI_num expediente, TFI_COD docCodigoID, TFI_NOMFICH docNombre, TFI_MIME docMimeType,NULL docFecha,NULL docContenidoRAW, TFI_VALOR docContenidoBLOB, reldoc_oid docOID "
                    + " FROM E_TFI LEFT JOIN melanbide_dokusi_reldoc_docCSE on TFI_num=reldoc_num AND TFI_COD=reldoc_cod "
                    + " union all  "
                    + " SELECT 'DCST' docOrigen, TFIT_num expediente, TFIT_TRA||'_'||TFIT_OCU||'_'||TFIT_COD docCodigoID, TFIT_NOMFICH docNombre, TFIT_MIME docMimeType,NULL docFecha,NULL docContenidoRAW,TFIT_VALOR docContenidoBLOB,reldoc_oid docOID "
                    + " FROM E_TFIT LEFT JOIN melanbide_dokusi_reldoc_docCST on TFIT_num=reldoc_num AND TFIT_TRA=RELDOC_TRA AND TFIT_OCU=RELDOC_OCU AND TFIT_COD=reldoc_cod "
                    + " union all  "
                    + " SELECT 'DREX' docOrigen, CRD_num expediente, crd_tra||'_'||crd_ocu||'_'||crd_nud docCodigoID, crd_des docNombre,  '' docMimeType,crd_fal docFecha,crd_FIL docContenidoRAW, NULL docContenidoBLOB, reldoc_oid docOID "
                    + " FROM G_CRD LEFT JOIN melanbide_dokusi_reldoc_relexp ON crd_mun=RELDOC_MUN AND crd_eje=RELDOC_EJE AND crd_num=RELDOC_NUM AND crd_tra=RELDOC_tra and crd_ocu=reldoc_ocu and crd_nud=reldoc_nud "
                    + " union all  "
                    + " SELECT 'DAEN' docOrigen, num_expediente expediente, cod_tramite||'_'||ocu_tramite||'_'||ID docCodigoID, NOMBRE docNombre,  TIPO_MIME docMimeType,FECHA docFecha,NULL docContenidoRAW,CONTENIDO docContenidoBLOB, reldoc_oid docOID "
                    + " FROM adjunto_ext_notificacion LEFT JOIN melanbide_dokusi_reldoc_extnot ON COD_MUNICIPIO=RELDOC_MUN AND num_expediente=RELDOC_NUM AND cod_tramite=RELDOC_tra and ocu_tramite=reldoc_ocu and ID=reldoc_ID "
                    + " UNION ALL "
                    + " SELECT 'DREG' docOrigen, EXR_NUM expediente,  RED_TIP||'_'||RED_EJE||'/'||RED_NUM||'_'||red_doc_id docCodigoID, RED_NOM_DOC docNombre,  RED_TIP_DOC docMimeType,RED_FEC_DOC docFecha,NULL docContenidoRAW,RED_DOC docContenidoBLOB, RED_IDDOC_GESTOR docOID "
                    + " FROM r_red LEFT JOIN E_EXR ON RED_DEP=EXR_DEP AND RED_UOR=EXR_UOR AND RED_EJE=EXR_EJR AND RED_NUM=EXR_NRE AND RED_TIP=EXR_TIP "
                    + ") documentosExpediente "
                    + "where expediente=? and documentosExpediente.docOID is not null "
                    + "ORDER BY DOCORIGEN, EXPEDIENTE, DOCCODIGOID, DOCNOMBRE";
            log.info("sql = " + query);
            int contadorParam = 1;
            ps = con.prepareStatement(query);
            ps.setString(contadorParam, expediente);
            log.info("params = " + expediente);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(interopJobsDaoMapping.getInteropJobsAKSSGADocumento(rs));
            }
        } catch (SQLException e) {
            log.error("getDocumentosAKSSGAByExpediente  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getDocumentosAKSSGAByExpediente - Exception ", e);
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
        log.info(" getDocumentosAKSSGAByExpediente - End " + formatFechaLog.format(new Date()));
        return resultado;
    }

    public List<InteropJobsAKSSGADocumento> getDocumentosAKSSGAFromHistoricoByExpediente(String expediente, Connection con) throws Exception {
        log.info(" getDocumentosAKSSGAByExpediente - Begin " + formatFechaLog.format(new Date()));
        List<InteropJobsAKSSGADocumento> resultado = new ArrayList<InteropJobsAKSSGADocumento>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = " select * from (  " +
                    "           SELECT 'DTRA' docOrigen,crd_num expediente, crd_tra||'_'||crd_ocu||'_'||crd_nud docCodigoID, crd_des docNombre, reldoc_oid docOID, crd_fal docFecha, NULL docContenidoBLOB, '' docMimeType, NULL docContenidoRAW  " +
                    "           from HIST_e_crd left join melanbide_dokusi_reldoc_tramit on crd_mun=RELDOC_MUN AND crd_eje=RELDOC_EJE AND crd_num=RELDOC_NUM AND crd_tra=RELDOC_tra and crd_ocu=reldoc_ocu and crd_nud=reldoc_nud  " +
                    "           union all   " +
                    "           SELECT 'DEXT' docOrigen,doc_ext_num expediente, to_char(doc_ext_cod) docCodigoID, doc_ext_nom docNombre,reldoc_oid docOID, DOC_EXT_FAL docFecha, NULL docContenidoBLOB, '' docMimeType, NULL docContenidoRAW " +
                    "           FROM HIST_e_doc_ext LEFT JOIN melanbide_dokusi_reldoc_docext on doc_ext_num=reldoc_num AND DOC_EXT_COD=reldoc_cod  " +
                    "           union all   " +
                    "           SELECT 'DPRE' docOrigen, presentado_num expediente, presentado_cod_doc||'_'||presentado_cod docCodigoID, presentado_nombre docNombre,reldoc_oid docOID,  PRESENTADO_FECHA_ALTA docFecha, NULL docContenidoBLOB, '' docMimeType, NULL docContenidoRAW  " +
                    "           FROM HIST_e_docs_presentados LEFT JOIN melanbide_dokusi_reldoc_docpre on presentado_num=reldoc_num AND presentado_COD=reldoc_cod  " +
                    "           union all   " +
                    "           SELECT 'DCSE' docOrigen, TFI_num expediente, TFI_COD docCodigoID, TFI_NOMFICH docNombre, reldoc_oid docOID, NULL docFecha, NULL docContenidoBLOB, '' docMimeType, NULL docContenidoRAW  " +
                    "           FROM HIST_E_TFI LEFT JOIN melanbide_dokusi_reldoc_docCSE on TFI_num=reldoc_num AND TFI_COD=reldoc_cod  " +
                    "           union all   " +
                    "           SELECT 'DCST' docOrigen, TFIT_num expediente, TFIT_TRA||'_'||TFIT_OCU||'_'||TFIT_COD docCodigoID, TFIT_NOMFICH docNombre,reldoc_oid docOID,  NULL docFecha, NULL docContenidoBLOB, '' docMimeType, NULL docContenidoRAW " +
                    "           FROM HIST_E_TFIT LEFT JOIN melanbide_dokusi_reldoc_docCST on TFIT_num=reldoc_num AND TFIT_TRA=RELDOC_TRA AND TFIT_OCU=RELDOC_OCU AND TFIT_COD=reldoc_cod  " +
                    "           union all   " +
                    "           SELECT 'DREX' docOrigen, CRD_num expediente, crd_tra||'_'||crd_ocu||'_'||crd_nud docCodigoID, crd_des docNombre, reldoc_oid docOID,  crd_fal docFecha, NULL docContenidoBLOB, '' docMimeType, NULL docContenidoRAW " +
                    "           FROM G_CRD LEFT JOIN melanbide_dokusi_reldoc_relexp ON crd_mun=RELDOC_MUN AND crd_eje=RELDOC_EJE AND crd_num=RELDOC_NUM AND crd_tra=RELDOC_tra and crd_ocu=reldoc_ocu and crd_nud=reldoc_nud  " +
                    "           union all   " +
                    "           SELECT 'DAEN' docOrigen, num_expediente expediente, cod_tramite||'_'||ocu_tramite||'_'||ID docCodigoID, NOMBRE docNombre, reldoc_oid docOID,FECHA docFecha, NULL docContenidoBLOB, '' docMimeType, NULL docContenidoRAW " +
                    "           FROM HIST_adjunto_ext_notificacion LEFT JOIN melanbide_dokusi_reldoc_extnot ON COD_MUNICIPIO=RELDOC_MUN AND num_expediente=RELDOC_NUM AND cod_tramite=RELDOC_tra and ocu_tramite=reldoc_ocu and ID=reldoc_ID  " +
                    "           UNION ALL  " +
                    "           SELECT 'DREG' docOrigen, EXR_NUM expediente,  RED_TIP||'_'||RED_EJE||'/'||RED_NUM||'_'||red_doc_id docCodigoID, RED_NOM_DOC docNombre, RED_IDDOC_GESTOR docOID,  RED_FEC_DOC docFecha, NULL docContenidoBLOB, '' docMimeType , NULL docContenidoRAW " +
                    "           FROM r_red LEFT JOIN HIST_E_EXR  ON RED_DEP=EXR_DEP AND RED_UOR=EXR_UOR AND RED_EJE=EXR_EJR AND RED_NUM=EXR_NRE AND RED_TIP=EXR_TIP  " +
                    "           ) documentosExpediente   " +
                    "where expediente=? and documentosExpediente.docOID is not null  " +
                    "ORDER BY DOCORIGEN, EXPEDIENTE, DOCCODIGOID, DOCNOMBRE";
            log.info("sql = " + query);
            int contadorParam = 1;
            ps = con.prepareStatement(query);
            ps.setString(contadorParam, expediente);
            log.info("params = " + expediente);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(interopJobsDaoMapping.getInteropJobsAKSSGADocumento(rs));
            }
        } catch (SQLException e) {
            log.error("getDocumentosAKSSGAByExpediente  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getDocumentosAKSSGAByExpediente - Exception ", e);
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
        log.info(" getDocumentosAKSSGAByExpediente - End " + formatFechaLog.format(new Date()));
        return resultado;
    }

    public long guardarFiheroXMLRespuesta(String ficherosRespuestaSGA, Connection con) throws SQLException, Exception {
        log.info(" guardarFiheroXMLRespuesta - Begin " + formatFechaLog.format(new Date()));
        long idRespuesta = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (ficherosRespuestaSGA != null) {
                String query = "";
                int contadorParam = 1;
                query = " insert into sga_proceso "
                        + "("
                        + " id "
                        + " ,fechaCarga "
                        + " ,xmlSga "
                        + ")"
                        + " values"
                        + "("
                        + " ?,?,?"
                        + ") ";
                ps = con.prepareStatement(query);
                idRespuesta = getNextIdFromSGA_PROCESO(con);
                if (idRespuesta <= 0) {
                    throw new SQLException("Id Unico no recuperado correctamente desde SEQ_PROCESO_CARGA_SGA", "SQL-NO-ID-RECUPERADO", (int) idRespuesta);
                }
                ps.setLong(contadorParam++, idRespuesta);
                ps.setInt(contadorParam++, Integer.valueOf(formatFechaSGAYYYYMMDD.format(new Date())));
                if (ficherosRespuestaSGA != null && !ficherosRespuestaSGA.isEmpty()) {
                    Clob datosEnviados = con.createClob();
                    datosEnviados.setString(1, ficherosRespuestaSGA);
                    ps.setClob(contadorParam++, datosEnviados);
                } else {
                    ps.setNull(contadorParam++, java.sql.Types.NULL);
                }
                log.info("sql = " + query);
                log.info("params = "
                        + idRespuesta + ","
                        + formatFechaSGAYYYYMMDD.format(new Date()) + ","
                        + ficherosRespuestaSGA
                );
                if (ps.executeUpdate() > 0) {
                    log.info("Insercion Correcta... " + idRespuesta);
                } else {
                    log.error("Linea No Insertada ....!!!");
                }
            } else {
                log.info("Objeto  a insertar/modificar recibido a null, no se procesa, respuesta id devuelta : 0 ");
            }
        } catch (SQLException e) {
            log.error("SQLException - guardarFiheroXMLRespuesta - ", e);
            throw e;
        } catch (Exception e) {
            log.error("Exception - guardarFiheroXMLRespuesta - ", e);
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
        log.info(" guardarFiheroXMLRespuesta - End " + formatFechaLog.format(new Date()));
        return idRespuesta;
    }

    private long getNextIdFromSGA_PROCESO(Connection con) throws SQLException, Exception {
        log.info(" getNextIdFromSGA_PROCESO - Begin " + formatFechaLog.format(new Date()));
        long resultado = -1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select SEQ_PROCESO_CARGA_SGA.nextval id from dual";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado = rs.getLong("id");
            }
        } catch (SQLException e) {
            log.error("getNextIdFromSGA_PROCESO  - SQLException ", e);
            throw e;
        } catch (Exception e) {
            log.error("getNextIdFromSGA_PROCESO - Exception ", e);
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
        log.info(" getNextIdFromSGA_PROCESO - End " + resultado + formatFechaLog.format(new Date()));
        return resultado;
    }

    public void ejecutarProcedureGrabar_codigos_SGA(Integer fechaProcesar, Connection con) throws SQLException, Exception {
        log.info(" ejecutarProcedureGrabar_codigos_SGA - Begin " + formatFechaLog.format(new Date()));
        CallableStatement ps = null;
        ResultSet rs = null;
        try {
            if (fechaProcesar != null) {
                String query = "{call grabar_codigos_SGA(?,?)}";
                int contadorParam = 1;
                ps = con.prepareCall(query);
                ps.setInt(contadorParam++, fechaProcesar);
                ps.registerOutParameter(contadorParam++, java.sql.Types.CLOB);
                log.info("sql = " + query);
                log.info("params = "
                        + fechaProcesar
                );
                ps.executeQuery();
                Clob salida = ps.getClob(2);
                log.info("Salida Procedure: " + (salida != null ? salida.getSubString(1, (int) salida.length()) : ""));
            } else {
                log.info("fechaProcesar recibido a null, no se lanza procedure ");
            }
        } catch (SQLException e) {
            log.error("SQLException - ejecutarProcedureGrabar_codigos_SGA - ", e);
            throw e;
        } catch (Exception e) {
            log.error("Exception - ejecutarProcedureGrabar_codigos_SGA - ", e);
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
        log.info(" ejecutarProcedureGrabar_codigos_SGA - End " + formatFechaLog.format(new Date()));
    }

    public String getMelanbideDokusiPlateaProByCodigoProcedimientoFlexia(String codigoProcedimientoFlexia, Connection con) throws SQLException, Exception {
        log.info(" getMelanbideDokusiPlateaProByCodigoProcedimientoFlexia - Begin " + codigoProcedimientoFlexia + " " + formatFechaLog.format(new Date()));
        String resultado = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * "
                    + "    from Melanbide_Dokusi_PlateaPro "
                    + "    where "
                    + "    codigoProcedimientoFlexia=? ";
            log.info("sql = " + query);
            ps = con.prepareStatement(query);
            int params = 1;
            ps.setString(params++, codigoProcedimientoFlexia);
            log.info("params = " + codigoProcedimientoFlexia
            );
            rs = ps.executeQuery();
            while (rs.next()) {
                resultado = rs.getString("codigoProcedimientoPlatea");
            }
        } catch (SQLException e) {
            log.error("Se ha producido SQLException getMelanbideDokusiPlateaProByCodigoProcedimientoFlexia ", e);
            throw e;
        } catch (Exception e) {
            log.error("Se ha producido Exception getMelanbideDokusiPlateaProByCodigoProcedimientoFlexia ", e);
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
        log.info(" getMelanbideDokusiPlateaProByCodigoProcedimientoFlexia - End - " + formatFechaLog.format(new Date())
                + " " + resultado
        );
        return resultado;
    }

}
