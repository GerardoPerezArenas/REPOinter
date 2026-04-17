/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager.InteropJobsArchivadoDocsAndExptsAKSSGAService;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager.InteropJobsUtils;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.util.InteropJobsAKSSGAConstantes;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores.ErrorLan6ExcepcionBeanNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores.GestionarErroresDokusiNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConfigurationParameter;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;
import es.altia.util.conexion.AdaptadorSQLBD;
import es.lanbide.lan6.adaptadoresPlatea.dokusi.beans.Lan6Documento;
import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6ErrorBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author INGDGC
 */
public class InteropJobsArchivadoDocsAndExptsAKSSGALeerRespuesta implements Job {

    private static final Logger log = Logger.getLogger(InteropJobsArchivadoDocsAndExptsAKSSGALeerRespuesta.class);
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static final SimpleDateFormat formatFechaSGAYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    private final InteropJobsArchivadoDocsAndExptsAKSSGAService interopJobsArchivadoDocsAndExptsAKSSGAService = new InteropJobsArchivadoDocsAndExptsAKSSGAService();
    private final InteropJobsUtils interopJobsUtils = new InteropJobsUtils();

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
                    String numExpedienteEnProceso = "";
                    String procedimientoEnProceso = "";
                    int contadorFicheros = 0;
                    int contadorExpedientes = 0;
                    try {
                        log.info("Job lanzado : " + System.getProperty("weblogic.Name"));

                        int codOrg = Integer.parseInt(interopJobsUtils.getParameter(interopJobsUtils.ORGANIZACION_ESQUEMA, interopJobsUtils.FICHERO_PROPIEDADES));
                        boolean dosEntornos = Boolean.getBoolean(ConfigurationParameter.getParameter(interopJobsUtils.DOS_ENTORNOS,
                                interopJobsUtils.FICHERO_PROPIEDADES));
                         String urlSGACargaErrores = ConfigurationParameter.getParameter(InteropJobsAKSSGAConstantes.URL_PATH_CARGA_SGA_FILES_ERROR,
                                interopJobsUtils.FICHERO_PROPIEDADES);
                        String rutaLanPIFRead = interopJobsUtils.getParameter(InteropJobsAKSSGAConstantes.URL_PATH_CARGA_SGA_PIF,
                                interopJobsUtils.FICHERO_PROPIEDADES);
                        log.info("rutaLanPIFRead : " + rutaLanPIFRead);
                        String limiteFicheroStr = ConfigurationParameter.getParameter(InteropJobsAKSSGAConstantes.LIMITE_FICHEROS_SGA,
                                interopJobsUtils.FICHERO_PROPIEDADES);
                        String limiteExpedientesFicheroStr = ConfigurationParameter.getParameter(InteropJobsAKSSGAConstantes.LIMITE_EXPEDIENTES_SGA,
                                interopJobsUtils.FICHERO_PROPIEDADES);
                        // Por defecto asignamos 20 desde codigo como documento analisis
                        int limiteFicheros = (limiteFicheroStr != null && !limiteFicheroStr.isEmpty() ? Integer.valueOf(limiteFicheroStr) : 20);
                        // Por defecto asignamos 299 desde codigo como documento analisis
                        int limiteExpedientesFichero = (limiteExpedientesFicheroStr != null && !limiteExpedientesFicheroStr.isEmpty() ? Integer.valueOf(limiteExpedientesFicheroStr) : 299);
                        log.info("limiteFicheros: " + limiteFicheros + " - limiteExpedientesFichero: " + limiteExpedientesFichero);
                        while (codOrg < 2) {
                            adaptador = interopJobsUtils.getAdaptSQLBD(String.valueOf(codOrg));
                            log.info("While de tokens codOrg: " + codOrg);
                            // Leer ficheros de la ruta Carga 
                            List<Lan6Documento> listaDocumentosRespuesta = interopJobsArchivadoDocsAndExptsAKSSGAService.getListaDocumentosFromURLPIF(rutaLanPIFRead, true);
                            // Procesar los ficheros OK del dia
                            log.info("Procesando Ficheros: \n" +
                                    interopJobsArchivadoDocsAndExptsAKSSGAService.procesarFicheroRespuestaOK(listaDocumentosRespuesta,adaptador));
                            // Comprobar existencia de ficheros errores en carpeta salida y enviar correo en caso de existir
                            if (interopJobsArchivadoDocsAndExptsAKSSGAService.existeFicherosErroInListLan6Document(listaDocumentosRespuesta)) {
                                // Enviar correo de error
                                String mensajeExcepcion = "Existen ficheros de error en la carpeta de respuesta de SGA " + rutaLanPIFRead;
                                log.error(mensajeExcepcion);
                                try {
                                    log.info("Vamos a registrar el error en BD : " + mensajeExcepcion);
                                    String causaExcepcion = "No se puede procesar la lectura de respuesta de AKSSGA y carga en Regexlan. Ficheros de error encontrados en el directorio " + rutaLanPIFRead;
                                    String trazaError = "No se puede procesar la lectura de respuesta de AKSSGA y carga en Regexlan. Ficheros de error encontrados en el directorio " + rutaLanPIFRead;

                                    // Copiamos los ficheros al server para ser revisados
                                    try {
                                        List<Lan6Documento> listaDocumentosRespuestaError = interopJobsArchivadoDocsAndExptsAKSSGAService.getListaDocumentosFromURLPIF(rutaLanPIFRead, true);
                                        // Obtenemos Ficheros
                                        for (Lan6Documento ficherosRespuestaSGAPIF : listaDocumentosRespuestaError) {
                                            log.info("Copiando fichero: " + ficherosRespuestaSGAPIF.getNombre());
                                            if (ficherosRespuestaSGAPIF.getNombre() != null && !ficherosRespuestaSGAPIF.getNombre().isEmpty()
                                                    && ficherosRespuestaSGAPIF.getNombre().toLowerCase().contains("_erro")) {
                                                // Leemos contenido y lo almcenamos en BD 
                                                File ficherosRespuestaSGA = new File(urlSGACargaErrores + "/" + ficherosRespuestaSGAPIF.getNombre() + "." + ficherosRespuestaSGAPIF.getFormat());
                                                ficherosRespuestaSGA.getParentFile().mkdirs();
                                                FileUtils.writeByteArrayToFile(ficherosRespuestaSGA, ficherosRespuestaSGAPIF.getContenido());
                                                log.info("Fichero copiado en el server correctamente ... ");
                                            } else {
                                                log.info("Fichero no procesado... Nombre no cumple las condiciones: ficherosRespuestaSGAPIF.getNombre() != null && !ficherosRespuestaSGAPIF.getNombre().isEmpty() "
                                                        + "                                                    && ficherosRespuestaSGAPIF.getNombre().toLowerCase().contains(_erro)");
                                            }
                                        }
                                    } catch (Exception e) {
                                        log.error("Error al copiar los ficheros de error al server de flexia", e);
                                    }

                                    Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                                            trazaError, ConstantesMeLanbideInterop.ERROR_NISAE_SISTEMA_ORIGEN_FLEXIA,
                                            InteropJobsAKSSGAConstantes.ERROR_AKSSGA_CODIGO_ERROR_HAY_FICH_ERROR, MessageFormat.format(InteropJobsAKSSGAConstantes.ERROR_AKSSGA_MENSAJE_HAY_FICH_ERROR, rutaLanPIFRead, urlSGACargaErrores), 0);
                                    ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, new Exception(mensajeExcepcion, new Throwable(trazaError)));
                                    int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, "Procesando... " + numExpedienteEnProceso, "Procesando... " + procedimientoEnProceso, "", this.getClass().getName());
                                    log.info("Error Registrado en BD correctamente. " + idError);
                                } catch (Exception ex1) {
                                    ex1.printStackTrace();
                                    log.error(this.getClass().getCanonicalName()
                                            + " Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : ", new Throwable(mensajeExcepcion));
                                }
                            }
                            if (dosEntornos) {
                                codOrg++;
                            } else {
                                codOrg = 2;
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        log.error(" Error Al ejecutar el job. " + this.getClass().getSimpleName(), ex);
                        String mensajeExcepcion = "";
                        try {
                            log.info("Vamos a registrar el error en BD : " + ex.getMessage());
                            String causaExcepcion = (ex.getCause() != null ? (ex.getCause().getMessage() != null ? ex.getCause().getMessage() + " - " + ex.getCause().toString() : ex.getCause().toString()) : "");
                            mensajeExcepcion = ex.getMessage();
                            String trazaError = ExceptionUtils.getStackTrace(ex);

                            Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                                    trazaError, ConstantesMeLanbideInterop.ERROR_NISAE_SISTEMA_ORIGEN_FLEXIA,
                                    InteropJobsAKSSGAConstantes.ERROR_AKSSGA_CODIGO_ERROR_GENERAL, MessageFormat.format(InteropJobsAKSSGAConstantes.ERROR_AKSSGA_MENSAJE_GENERAL, "Error Procesando Ficheros de respuesta AKS/SGA" + ex.getMessage()), 0);
                            ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, ex);
                            int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, "Procesando ... " + numExpedienteEnProceso, "Procesando... " + procedimientoEnProceso, "", this.getClass().getName());
                            log.info("Error Registrado en BD correctamente. " + idError);
                        } catch (Exception ex1) {
                            ex1.printStackTrace();
                            log.error(this.getClass().getCanonicalName()
                                    + " Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : ", new Throwable(mensajeExcepcion));
                        }
                    } finally {
                        log.info(this.getClass().getName() + ".execute -  Fin " + formatFechaLog.format(new Date()));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(this.getClass().getName() + " Error: " + ex);
        }
    }

}
