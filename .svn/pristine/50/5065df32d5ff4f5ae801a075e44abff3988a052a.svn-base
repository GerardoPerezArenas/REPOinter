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
import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6ErrorBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author INGDGC
 */
public class InteropJobsComprobarFichErrorEnviarMailAKSSGA implements Job {

    private static final Logger log = Logger.getLogger(InteropJobsComprobarFichErrorEnviarMailAKSSGA.class);
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
            String hostName = "";
            try {
                hostName = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException ex) {}

            if (servidor.equals(System.getProperty("weblogic.Name")) || hostName.equals("A03P0020")) {//PARA LOCAL QUITAR
                synchronized (jec) {
                    AdaptadorSQLBD adaptador = null;
                    String numExpedienteEnProceso = "";
                    String procedimientoEnProceso = "";

                    try {
                        log.info("Job lanzado : " + System.getProperty("weblogic.Name"));

                        int codOrg = Integer.parseInt(interopJobsUtils.getParameter(interopJobsUtils.ORGANIZACION_ESQUEMA, interopJobsUtils.FICHERO_PROPIEDADES));
                        boolean dosEntornos = Boolean.getBoolean(ConfigurationParameter.getParameter(interopJobsUtils.DOS_ENTORNOS,
                                interopJobsUtils.FICHERO_PROPIEDADES));
                        String urlSGACargaPIF = ConfigurationParameter.getParameter(InteropJobsAKSSGAConstantes.URL_PATH_CARGA_SGA_PIF,
                                interopJobsUtils.FICHERO_PROPIEDADES);
                        String urlSGACargaErrores = ConfigurationParameter.getParameter(InteropJobsAKSSGAConstantes.URL_PATH_CARGA_SGA_FILES_ERROR,
                                interopJobsUtils.FICHERO_PROPIEDADES);

                        while (codOrg < 2) {
                            adaptador = interopJobsUtils.getAdaptSQLBD(String.valueOf(codOrg));
                            log.info("While de tokens codOrg: " + codOrg);
                            // Comprobar existencia de  ficheros errores en carpeta salida y enviar correo en caso de existir
                            if (interopJobsArchivadoDocsAndExptsAKSSGAService.existeFicherosErrorUrlSGAcargaPIF(urlSGACargaPIF)) {
                                // Enviar correo de error y no generar ningun fichero
                                String mensajeExcepcion = "Existen ficheros de error en la carpeta de respuesta de SGA " + urlSGACargaPIF;
                                log.error(mensajeExcepcion);
                                try {
                                    log.info("Vamos a registrar el error en BD : " + mensajeExcepcion);
                                    String causaExcepcion = "Ficheros de error encontrados en el directorio " + urlSGACargaPIF;
                                    String trazaError = "Ficheros de error encontrados en el directorio " + urlSGACargaPIF;


                                    Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                                            trazaError, ConstantesMeLanbideInterop.ERROR_NISAE_SISTEMA_ORIGEN_FLEXIA,
                                            InteropJobsAKSSGAConstantes.ERROR_AKSSGA_CODIGO_ERROR_HAY_FICH_ERROR, MessageFormat.format(InteropJobsAKSSGAConstantes.ERROR_AKSSGA_MENSAJE_HAY_FICH_ERROR, urlSGACargaPIF, urlSGACargaErrores), 0);
                                    ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, new Exception(mensajeExcepcion, new Throwable(trazaError)));
                                    int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, "Procesando... " + numExpedienteEnProceso, "Procesando... " + procedimientoEnProceso, "", this.getClass().getName());
                                    log.info("Error Registrado en BD correctamente. " + idError);
                                } catch (final Exception ex1) {
                                    log.error(ex1.getMessage());
                                    log.error(this.getClass().getCanonicalName()
                                            + " Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : ", new Throwable(mensajeExcepcion));
                                }
                            }
                            if (dosEntornos) {
                                codOrg++;
                            } else {
                                codOrg = 2;
                            }
                        } // while (codOrg < 2) {
                    } catch (final Exception ex) {
                        log.error(ex.getMessage());
                        log.error(" Error Al ejecutar el job. ", ex);
                        String mensajeExcepcion = "";
                        try {
                            log.info("Vamos a registrar el error en BD : " + ex.getMessage());
                            String causaExcepcion = (ex.getCause() != null ? (ex.getCause().getMessage() != null ? ex.getCause().getMessage() + " - " + ex.getCause().toString() : ex.getCause().toString()) : "");
                            mensajeExcepcion = ex.getMessage();
                            String trazaError = ExceptionUtils.getStackTrace(ex);

                            Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                                    trazaError, ConstantesMeLanbideInterop.ERROR_NISAE_SISTEMA_ORIGEN_FLEXIA,
                                    InteropJobsAKSSGAConstantes.ERROR_AKSSGA_CODIGO_ERROR_GENERAL, MessageFormat.format(InteropJobsAKSSGAConstantes.ERROR_AKSSGA_MENSAJE_GENERAL, ex.getMessage()), 0);
                            ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, ex);
                            int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, "Procesando ... " + numExpedienteEnProceso, "Procesando... " + procedimientoEnProceso, "", this.getClass().getName());
                            log.info("Error Registrado en BD correctamente. " + idError);
                        } catch (final Exception ex1) {
                            log.error(ex1.getMessage());
                            log.error(this.getClass().getCanonicalName()
                                    + " Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : ", new Throwable(mensajeExcepcion));
                        }
                    } finally {
                        log.info(this.getClass().getName() + ".execute -  Fin " + formatFechaLog.format(new Date()));
                    }
                } //synchronized (jec) {
            } // if (servidor.equals(System.getProperty("weblogic.Name"))) {//PARA LOCAL QUITAR
        } catch (final Exception ex) {
            log.error(this.getClass().getName() + " Error: " + ex);
        }
    }
}
