/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.MELANBIDE_INTEROP;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager.InteropJobsFSEExptsProceManager;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager.InteropJobsLogManager;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager.InteropJobsUtils;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEExpedienteRequest;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEExptsProce;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEPersonaServicio;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsFSEProcedi;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsLog;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConfigurationParameter;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaiWS.fse.LangaiDemanda;
import es.altia.util.conexion.AdaptadorSQLBD;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author INGDGC
 */
public class InteropJobsAltaServicioConvocatoriaFSE implements Job{
    
    private static final Logger log = Logger.getLogger(InteropJobsAltaServicioConvocatoriaFSE.class);
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private final InteropJobsLogManager interopJobsLogManager = new InteropJobsLogManager();
    private final InteropJobsUtils interopJobsUtils = new InteropJobsUtils();
    private final InteropJobsFSEExptsProceManager interopJobsFSEExptsProceManager = new InteropJobsFSEExptsProceManager();

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        log.info(this.getClass().getName() + ".execute -  Begin " + formatFechaLog.format(new Date()));
        try {
            int contador = jec.getRefireCount();
            log.info("jec.getRefireCount(): " + contador);
            String servidor = interopJobsUtils.getParameter(interopJobsUtils.SERVIDOR, interopJobsUtils.FICHERO_PROPIEDADES);
            //String servidor = "flexia1"; //DESA
            //String servidor = "paprergi1_flexia1"; //PRE
            //String servidor = "pargi1_flexia1"; //PRO
            log.info("servidor: " + servidor);
            String auxMensajeError = "";
            if (servidor.equals(System.getProperty("weblogic.Name"))) {//PARA LOCAL QUITAR
                synchronized (jec) {
                    AdaptadorSQLBD adaptador = null;
                    String numExpediente = "";
                    try {
                        log.info("Job lanzado : " + System.getProperty("weblogic.Name"));

                        int codOrg = Integer.parseInt(interopJobsUtils.getParameter(interopJobsUtils.ORGANIZACION_ESQUEMA, interopJobsUtils.FICHERO_PROPIEDADES));
                        boolean dosEntornos = Boolean.getBoolean(ConfigurationParameter.getParameter(interopJobsUtils.DOS_ENTORNOS,
                                interopJobsUtils.FICHERO_PROPIEDADES));
                        while (codOrg < 2) {
                            adaptador = interopJobsUtils.getAdaptSQLBD(String.valueOf(codOrg));
                            log.info("While de tokens codOrg: " + codOrg);
                            // Obtenemos Procedimeintos a Tratar
                            List<InteropJobsFSEProcedi> procedimientos = interopJobsLogManager.getProcedimientosProcesarFSE(adaptador);
                            if(procedimientos!=null){
                                for (InteropJobsFSEProcedi procedimiento : procedimientos) {
                                    log.info("Tratando Procedimiento : " + procedimiento.getCodigoProcedimiento());
                                    // Obtener los expedientes a tratar
                                    try {
                                        List<InteropJobsFSEExpedienteRequest> listaExpedientesRequest = interopJobsLogManager.getExpedientesTratarFSEByProcedimiento(procedimiento,adaptador);
                                        log.info("Expedientes a Tratar: " + (listaExpedientesRequest!=null?listaExpedientesRequest.size():0));
                                        if(listaExpedientesRequest!=null && listaExpedientesRequest.size()>0){
                                            for (InteropJobsFSEExpedienteRequest interopJobsFSEExpedienteRequest : listaExpedientesRequest) {
                                                log.info("Tratando Expediente : " + interopJobsFSEExpedienteRequest.getNumeroExpediente());
                                                log.info(interopJobsFSEExpedienteRequest.toString());
                                                InteropJobsFSEExptsProce interopJobsFSEExptsProce = interopJobsFSEExptsProceManager.getInteropJobsFSEExptsProceByNumeroExpedienteAndNombreJob(interopJobsFSEExpedienteRequest.getNumeroExpediente(), this.getClass().getSimpleName(), adaptador);
                                                if(interopJobsFSEExptsProce==null){
                                                    interopJobsFSEExptsProce = new InteropJobsFSEExptsProce(this.getClass().getSimpleName(), interopJobsFSEExpedienteRequest.getNumeroExpediente(),"");
                                                    log.info("-- Creamos nueva linea en INTEROP_JOBS_FSE_EXPTS_PROCE - Inicializado interopJobsFSEExptsProce. " + interopJobsFSEExptsProce.toString());
                                                    interopJobsFSEExptsProce = interopJobsFSEExptsProceManager.crearLineaInteropJobsFSEExptsProce(interopJobsFSEExptsProce, adaptador);
                                                }else{
                                                    log.info("Existe registro previo del expediente en INTEROP_JOBS_FSE_EXPTS_PROCE - Porcesamos para actualizar ");
                                                }
                                                int personasProcesadasOK = 0;
                                                for (InteropJobsFSEPersonaServicio interopJobsFSEPersonaServicio : interopJobsFSEExpedienteRequest.getPersonasServicio()) {
                                                    log.debug("Tratando Persona : " + interopJobsFSEPersonaServicio.getNumeroDocumento());
                                                    InteropJobsLog interopJobsLog = mapearDatosPersonaServicioFSEToInteropJobsLog(interopJobsFSEPersonaServicio);
                                                    interopJobsLog = interopJobsLogManager.crearLineaInteropJobsLog(interopJobsLog, adaptador);
                                                    try {
                                                        interopJobsLog.setIdOrganizacion(codOrg);
                                                        String respuestaServicio = "";
                                                        // LLamamos al servicio
                                                        String cod_centro_usu = interopJobsFSEExpedienteRequest.getCodigoCentroUsuario();
                                                        String cod_ubic_usu = interopJobsFSEExpedienteRequest.getCodigoUbicacionUsuario();
                                                        String num_doc = interopJobsFSEPersonaServicio.getNumeroDocumento();
                                                        String tipo_doc = interopJobsFSEPersonaServicio.getTipoDocumetoLanbide();
                                                        String dem_servs_cod_serv = interopJobsFSEExpedienteRequest.getCodigoServicioLanbide();
                                                        String dem_servs_fech_ini = interopJobsFSEPersonaServicio.getFechaInicioServicioAsString();
                                                        String dem_servs_fech_fin = interopJobsFSEPersonaServicio.getFechaFinServicioAsString();;
                                                        String dem_servs_nro_horas = "";
                                                        String dem_servs_via_financ = "";
                                                        String dem_servs_texto_desc = "";
                                                        String dem_servs_nro_min = "";
                                                        String dem_servs_fec_sol = "";
                                                        String dem_servs_fec_ofe = "";
                                                        String dem_servs_resultado = interopJobsFSEExpedienteRequest.getDemServsResultado(); // Valor fijo 70 Indica que es resoluion positiva,solo estos expedientes se deben enviar a FSE
                                                        String dem_servs_mot_fin_ofe = "";
                                                        String dem_servs_fec_fin_ofe = "";
                                                        String dem_servs_centro = interopJobsFSEExpedienteRequest.getDemServCentro();
                                                        String dem_servs_especialidad = "";
                                                        String dem_servs_acc_form = "";
                                                        String num_doc_orientador = interopJobsFSEExpedienteRequest.getNumDocOrientador();
                                                        String tipo_doc_usuario = interopJobsFSEExpedienteRequest.getTipoDocUsuario();
                                                        String num_doc_usuario = interopJobsFSEExpedienteRequest.getNumDocUsuario();
                                                        String password_usuario = "";
                                                        String cod_itinerario = "";
                                                        String os_ofe_id = "";
                                                        String firma_oid = "";
                                                        String idioma = "";
                                                        String origen = interopJobsFSEExpedienteRequest.getOrigen();
                                                        String cod_expediente = interopJobsFSEExpedienteRequest.getNumeroExpediente();
                                                        String resumen = "";
                                                        String dem_servs_servicio = interopJobsFSEExpedienteRequest.getDemServsServicio();
                                                        String dem_servs_subservicio = interopJobsFSEExpedienteRequest.getDemServsSubservicio();
                                                        String dem_servs_modalidad = procedimiento.getModalidad();
                                                        String Convocatoria = interopJobsFSEExpedienteRequest.getCodigoServicioCatalogoGV();
                                                                //String.valueOf(interopJobsFSEExpedienteRequest.getEjercicio());
                                                        MELANBIDE_INTEROP melanbideInterop = new MELANBIDE_INTEROP();
                                                        LangaiDemanda servicioFSE = melanbideInterop.getWSClientLangaiDemandaFSE();
                                                        interopJobsLog.setDatosEnviados("{"
                                                                + "cod_centro_usu :" + cod_centro_usu + ","
                                                                + "cod_ubic_usu :" + cod_ubic_usu + ","
                                                                + "num_doc :" + num_doc + ","
                                                                + "tipo_doc :" + tipo_doc + ","
                                                                + "dem_servs_cod_serv :" + dem_servs_cod_serv + ","
                                                                + "dem_servs_fech_ini :" + dem_servs_fech_ini + ","
                                                                + "dem_servs_fech_fin :" + dem_servs_fech_fin + ","
                                                                + "dem_servs_nro_horas :" + dem_servs_nro_horas + ","
                                                                + "dem_servs_via_financ :" + dem_servs_via_financ + ","
                                                                + "dem_servs_texto_desc :" + dem_servs_texto_desc + ","
                                                                + "dem_servs_nro_min :" + dem_servs_nro_min + ","
                                                                + "dem_servs_fec_sol :" + dem_servs_fec_sol + ","
                                                                + "dem_servs_fec_ofe :" + dem_servs_fec_ofe + ","
                                                                + "dem_servs_resultado :" + dem_servs_resultado + ","
                                                                + "dem_servs_mot_fin_ofe :" + dem_servs_mot_fin_ofe + ","
                                                                + "dem_servs_fec_fin_ofe :" + dem_servs_fec_fin_ofe + ","
                                                                + "dem_servs_centro :" + dem_servs_centro + ","
                                                                + "dem_servs_especialidad :" + dem_servs_especialidad + ","
                                                                + "dem_servs_acc_form :" + dem_servs_acc_form + ","
                                                                + "num_doc_orientador :" + num_doc_orientador + ","
                                                                + "tipo_doc_usuario :" + tipo_doc_usuario + ","
                                                                + "num_doc_usuario :" + num_doc_usuario + ","
                                                                + "password_usuario :" + password_usuario + ","
                                                                + "cod_itinerario :" + cod_itinerario + ","
                                                                + "os_ofe_id :" + os_ofe_id + ","
                                                                + "firma_oid :" + firma_oid + ","
                                                                + "idioma :" + idioma + ","
                                                                + "origen :" + origen + ","
                                                                + "cod_expediente :" + cod_expediente + ","
                                                                + "resumen :" + resumen + ","
                                                                + "dem_servs_servicio :" + dem_servs_servicio + ","
                                                                + "dem_servs_subservicio :" + dem_servs_subservicio + ","
                                                                + "dem_servs_modalidad :" + dem_servs_modalidad + ","
                                                                + "Convocatoria:" + Convocatoria
                                                                + "}");
                                                        // Si no hay datos de Convocatoria(ID Procedimiento Lanbide) y dem_servs_cod_serv (ID Procedimiento Catalogo Gobierno Vasco) no invocar, actualizar dato con observaciones
                                                        if(dem_servs_cod_serv!=null && !dem_servs_cod_serv.isEmpty() && Convocatoria!=null && !Convocatoria.isEmpty()){
                                                            respuestaServicio = servicioFSE.altaServicioConvocatoria(
                                                                    cod_centro_usu,
                                                                    cod_ubic_usu,
                                                                    num_doc,
                                                                    tipo_doc,
                                                                    dem_servs_cod_serv,
                                                                    dem_servs_fech_ini,
                                                                    dem_servs_fech_fin,
                                                                    dem_servs_nro_horas,
                                                                    dem_servs_via_financ,
                                                                    dem_servs_texto_desc,
                                                                    dem_servs_nro_min,
                                                                    dem_servs_fec_sol,
                                                                    dem_servs_fec_ofe,
                                                                    dem_servs_resultado,
                                                                    dem_servs_mot_fin_ofe,
                                                                    dem_servs_fec_fin_ofe,
                                                                    dem_servs_centro,
                                                                    dem_servs_especialidad,
                                                                    dem_servs_acc_form,
                                                                    num_doc_orientador,
                                                                    tipo_doc_usuario,
                                                                    num_doc_usuario,
                                                                    password_usuario,
                                                                    cod_itinerario,
                                                                    os_ofe_id,
                                                                    firma_oid,
                                                                    idioma,
                                                                    origen,
                                                                    cod_expediente,
                                                                    resumen,
                                                                    dem_servs_servicio,
                                                                    dem_servs_subservicio,
                                                                    dem_servs_modalidad,
                                                                    Convocatoria
                                                            );
                                                        }else{
                                                            interopJobsLog.setObservaciones("Servicio no invocado. "
                                                                    + "Datos de Id procedimiento en Lanbide y Catalogo Gobierno vasco no recuperados. "
                                                                    + "MeLanbideConvocatorias: COD_SERVICIO_LANB y COD_SERVICIO_CATGV");
                                                        }
                                                        // Fin llamada servicio
                                                        log.info("respuestaServicio: " + respuestaServicio);
                                                        interopJobsLog.setResultadoWS(respuestaServicio);
                                                        // 0 - proceso Correcto / 483 - Ya existe el servicio para la persona, no llamar de nuevo
                                                        interopJobsLog.setPersonaProcesada(("0".equalsIgnoreCase(respuestaServicio) ||"483".equalsIgnoreCase(respuestaServicio)?1:0));
                                                        interopJobsLog.setDatosRespuesta(interopJobsUtils.getDetalleRespuestaWS(respuestaServicio));
                                                        
                                                    } catch (Exception e) {
                                                        log.error("Error procesando Persona ...");
                                                        interopJobsLog.setObservaciones("Error : "
                                                                + (e.getMessage() + " **Traza**: " + ExceptionUtils.getStackTrace(e)).substring(0, 3999)
                                                        );
                                                    } finally {
                                                        // Actualziar contador persona procesada
                                                        if(1==interopJobsLog.getPersonaProcesada()){
                                                            personasProcesadasOK++;
                                                        }
                                                        // Update del resultado en el log llamada a WS
                                                        interopJobsLogManager.actualizarLineaInteropJobsLog(interopJobsLog, adaptador);
                                                    }
                                                }
                                                if(interopJobsFSEExpedienteRequest.getPersonasServicio()!=null 
                                                        && interopJobsFSEExpedienteRequest.getPersonasServicio().size()>0  
                                                        &&  interopJobsFSEExpedienteRequest.getPersonasServicio().size()==personasProcesadasOK){
                                                    interopJobsFSEExptsProce.setExpedienteProcesadoCompl(1);
                                                    interopJobsFSEExptsProceManager.actualizarLineaInteropJobsFSEExptsProce(interopJobsFSEExptsProce, adaptador);
                                                }
                                            }
                                        }else{
                                            log.info("No se han recuperado expedientes para tratar del procedimiento: " + procedimiento.getCodigoProcedimiento());
                                        }
                                    } catch (Exception e) {
                                        log.error("Error procesando expedientes... ", e);
                                    }
                                }
                            }else{
                                log.info("No hay procedimientos configurados para Tratar ...");
                            }
                            if (dosEntornos) {
                                codOrg++;
                            } else {
                                codOrg = 2;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(" Error Al ejecutar el job. ", e);
                    } finally {
                        log.info(this.getClass().getName() + ".execute -  Fin " + formatFechaLog.format(new Date()));
//                        if (adaptador != null) {
//                            try {
//                                adaptador.devolverConexion(con);
//                            } catch (BDException e) {
//                                log.error("Error devolviendo la conexion ... " + e.getErrorCode() + " " + e.getDescripcion() + " " + e.getMensaje(), e);
//                            }
//                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(this.getClass().getName() + " Error: " + ex);
        }
    }

    private InteropJobsLog mapearDatosPersonaServicioFSEToInteropJobsLog(InteropJobsFSEPersonaServicio interopJobsFSEPersonaServicio) {
        InteropJobsLog respuesta = null;
        if(interopJobsFSEPersonaServicio!=null){
            respuesta = new InteropJobsLog(this.getClass().getSimpleName()
                    , interopJobsUtils.getCodProcedimientoFromNumExpediente(interopJobsFSEPersonaServicio.getNumeroExpediente())
                    , interopJobsFSEPersonaServicio.getNumeroExpediente()
                    , interopJobsFSEPersonaServicio.getTipoDocumentoRegexlan()
                    , interopJobsFSEPersonaServicio.getNumeroDocumento()
                    , interopJobsFSEPersonaServicio.toString());
        }
        return respuesta;
    }
    
}
