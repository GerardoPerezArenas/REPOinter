/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.dao;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsAKSSGAConfig;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsAKSSGADocumento;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsAKSSGAExpedienteRequest;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsAKSSGAProceso;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEExptsProce;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEPersonaServicio;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEProcedi;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsLog;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

/**
 *
 * @author INGDGC
 */
public class InteropJobsDaoMapping {
    
    private static final Logger log = Logger.getLogger(InteropJobsDaoMapping.class);
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    
    public InteropJobsLog getInteropJobsLog(ResultSet rs){
        InteropJobsLog respuesta = null;
        try {
            if(rs!=null){
                Clob datosEnviados = rs.getClob("datosEnviados");
                Clob datosRespuesta = rs.getClob("datosRespuesta");
                respuesta = new InteropJobsLog(
                        rs.getLong("id")
                        ,rs.getInt("idOrganizacion")
                        ,rs.getString("nombreProcesoJobBatch")
                        ,rs.getString("codigoProcedimiento")
                        ,rs.getString("numeroExpediente")
                        ,rs.getString("tipoDocumentoId")
                        ,rs.getString("documentoId")
                        ,rs.getString("resultadoWS")
                        ,(datosEnviados != null ? datosEnviados.getSubString(1, (int) datosEnviados.length()) : null)
                        ,(datosRespuesta != null ? datosRespuesta.getSubString(1, (int) datosRespuesta.length()) : null)
                        ,rs.getString("observaciones")
                        ,rs.getInt("personaProcesada")
                        ,rs.getDate("fechaCreacion")
                );
            }
        } catch (Exception e) {
            log.error("Exception - getInteropJobsLog - ", e);
            respuesta=null;
        }
        return respuesta;
    }

    public InteropJobsFSEProcedi getInteropJobsFSEProcedi(ResultSet rs) {
        InteropJobsFSEProcedi respuesta = null;
        try {
            if (rs != null) {
                respuesta = new InteropJobsFSEProcedi(
                        rs.getString("codigoProcedimiento")
                        ,rs.getInt("procesar")
                        ,rs.getString("codVisTramNotifResol")
                        ,rs.getString("codCampoTramFecResol")
                        ,rs.getString("codCampoExpFecResol")
                        ,rs.getString("codVisTramExpConcedido")
                        ,rs.getString("codCampoConcedido")
                        ,rs.getInt("TipoDatoCampoConcedido")
                        ,rs.getString("valorCampoExpConcedido")
                        ,rs.getString("codCampoFechaRefExpediente")
                        ,rs.getInt("codRolPersonaServicio")
                        ,rs.getString("modalidad")
                        ,rs.getDate("fechaCreacion")
                );
            }
        } catch (Exception e) {
            log.error("Exception - getInteropJobsLog - ", e);
            respuesta = null;
        }
        return respuesta;
    }
        
    public InteropJobsFSEPersonaServicio getInteropJobsFSEPersonaServicio(ResultSet rs) {
        InteropJobsFSEPersonaServicio respuesta = null;
        try {
            if (rs != null) {
                respuesta = new InteropJobsFSEPersonaServicio(
                        rs.getString("numeroExpediente")
                        ,rs.getString("tipoDocumentoRegexlan")
                        ,rs.getString("tipoDocumetoLanbide")
                        ,rs.getString("numeroDocumento")
                        ,rs.getDate("fechaInicioServicio")
                        ,rs.getDate("fechaFinServicio")
                        ,rs.getString("resultado")
                );
            }
        } catch (Exception e) {
            log.error("Exception - getInteropJobsFSEPersonaServicio - ", e);
            respuesta = null;
        }
        return respuesta;
    }
    
    public InteropJobsFSEExptsProce getInteropJobsFSEExptsProce(ResultSet rs) {
        InteropJobsFSEExptsProce respuesta = null;
        try {
            if (rs != null) {
                respuesta = new InteropJobsFSEExptsProce(
                        rs.getLong("id")
                        ,rs.getString("nombreProcesoJobBatch")
                        ,rs.getString("numeroExpediente")
                        ,rs.getString("observaciones")
                        ,rs.getInt("expedienteProcesadoCompl")
                        ,rs.getDate("fechaCreacion")
                );
            }
        } catch (Exception e) {
            log.error("Exception - getInteropJobsFSEExptsProce - ", e);
            respuesta = null;
        }
        return respuesta;
    }

    InteropJobsAKSSGAConfig getInteropJobsAKSSGAConfig(ResultSet rs) {
        InteropJobsAKSSGAConfig respuesta = null;
        try {
            /*
            CODPROC, MESES, NIVEL, CUADRO, PRODUCTOR, FORMADEINGRESO, FORMADEINGRESO_E, ARCHIVERO, DEPOSITO, TIPO_UI, ID_UI, RESPONSABLE
            ,procesarProcedEnBatch,procesarExpedientesHistorico
            */
            if (rs != null) {
                respuesta = new InteropJobsAKSSGAConfig(
                        rs.getString("CodProc")
                        ,rs.getInt("meses")
                        ,rs.getInt("nivel")
                        ,rs.getInt("cuadro")
                        ,rs.getInt("productor")
                        ,rs.getString("formaDeIngreso")
                        ,rs.getString("formaDeIngreso_E")
                        ,rs.getString("archivero")
                        ,rs.getString("deposito")
                        ,rs.getInt("tipo_UI")
                        ,rs.getString("id_UI")
                        ,rs.getString("responsable")
                        ,rs.getInt("procesarProcedEnBatch")
                        ,rs.getInt("limiteExpedientesProceso")
                        ,rs.getInt("procesarExpedientesHistorico")
                );
            }
        } catch (Exception e) {
            log.error("Exception - getInteropJobsAKSSGAConfig - ", e);
            respuesta = null;
        }
        return respuesta;
    }
    
    InteropJobsAKSSGAProceso getInteropJobsAKSSGAProceso(ResultSet rs) {
        InteropJobsAKSSGAProceso respuesta = null;
        try {
            if (rs != null) {
                respuesta = new InteropJobsAKSSGAProceso(
                        rs.getLong("id")
                        ,rs.getLong("fechaCarga")
                        ,rs.getClob("xmlSga")
                );
            }
        } catch (Exception e) {
            log.error("Exception - getInteropJobsAKSSGAProceso - ", e);
            respuesta = null;
        }
        return respuesta;
    }
    
    InteropJobsAKSSGAExpedienteRequest getInteropJobsAKSSGAExpedienteRequest(ResultSet rs) {
        InteropJobsAKSSGAExpedienteRequest respuesta = null;
        try {
            if (rs != null) {
                respuesta = new InteropJobsAKSSGAExpedienteRequest(
                        rs.getString("EXP_NUM"),
                        rs.getDate("EXP_FEI"),
                        rs.getDate("EXP_FEF"),
                        rs.getString("EXP_UBICACION_DOC"),
                        rs.getString("expedienteTitulo"),
                        rs.getString("expedienteLugares")
                );
            }
        } catch (Exception e) {
            log.error("Exception - getInteropJobsAKSSGAExpedienteRequest - ", e);
            respuesta = null;
        }
        return respuesta;
    }
    
    InteropJobsAKSSGADocumento getInteropJobsAKSSGADocumento(ResultSet rs) {
        InteropJobsAKSSGADocumento respuesta = null;
        try {
            if (rs != null) {
                Blob blob = rs.getBlob("DOCCONTENIDOBLOB");
                byte[] contenido = (blob!=null ? blob.getBytes(1,(int)blob.length()):rs.getBytes("DOCCONTENIDORAW"));
                respuesta = new InteropJobsAKSSGADocumento(
                        rs.getString("docOrigen")
                        ,rs.getString("docCodigoID")
                        ,rs.getString("DOCNOMBRE")
                        ,rs.getString("DOCMIMETYPE")
                        ,rs.getString("DOCOID") //
                        ,contenido// DOCCONTENIDORAW, DOCCONTENIDOBLOB
                        ,rs.getDate("DOCFECHA")
                );
            }
        } catch (Exception e) {
            log.error("Exception - getInteropJobsAKSSGADocumento - ", e);
            respuesta = null;
        }
        return respuesta;
    }
}
