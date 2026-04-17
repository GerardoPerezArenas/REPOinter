/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.dao.InteropJobsAKSSGAConfigDAO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.dao.InteropJobsAKSSGAProcesoDAO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.util.InteropJobsAKSSGAConstantes;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsAKSSGAConfig;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsAKSSGAExpedienteRequest;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo.InteropJobsAKSSGAProceso;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConfigurationParameter;
import es.altia.flexia.registro.digitalizacion.lanbide.util.GestionAdaptadoresLan6Config;
import es.altia.util.conexion.AdaptadorSQLBD;
import es.altia.util.conexion.BDException;
import es.lanbide.lan6.adaptadoresPlatea.dokusi.beans.Lan6Documento;
import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6UtilExcepcion;
import es.lanbide.lan6.adaptadoresPlatea.utilidades.ficheros.Lan6Ficheros;
import es.lanbide.lan6.adaptadoresPlatea.utilidades.pif.Lan6GestionFicherosPif;
import es.lanbide.lan6.adaptadoresPlatea.utilidades.pif.transformaciones.Lan6PifTransformaciones;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author INGDGC
 */
public class InteropJobsArchivadoDocsAndExptsAKSSGAService {

    private static final Logger log = Logger.getLogger(InteropJobsLogManager.class);
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static final SimpleDateFormat formatFechaSGAYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    private final InteropJobsAKSSGAConfigDAO interopJobsAKSSGAConfigDAO = new InteropJobsAKSSGAConfigDAO();
    private final InteropJobsAKSSGAProcesoDAO interopJobsAKSSGAProcesoDAO = new InteropJobsAKSSGAProcesoDAO();
    private final InteropJobsUtils interopJobsUtils = new InteropJobsUtils();
    private static final GestionAdaptadoresLan6Config gestionAdaptadoresLan6Config = new GestionAdaptadoresLan6Config();

    public List<InteropJobsAKSSGAConfig> getProcedimientosProcesarAKSSGA(Connection con) throws SQLException, Exception {
        log.info(" getProcedimientosProcesarAKSSGA - Begin " + formatFechaLog.format(new Date()));
        return interopJobsAKSSGAConfigDAO.getProcedimientosProcesarAKSSGA(con);
    }

    public List<InteropJobsAKSSGAConfig> getProcedimientosProcesarAKSSGA(AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" getProcedimientosProcesarAKSSGA - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.getProcedimientosProcesarAKSSGA(con);
        } catch (BDException e) {
            log.error("BDException Recuperando Linea Datos ", e);
            throw (e);
        } catch (Exception ex) {
            log.error("Exception Recuperando Linea Datos ", ex);
            throw (ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }

    public List<InteropJobsAKSSGAProceso> getProcesosAKSSGAByFechacarga(long fechaCarga, Connection con) throws SQLException, Exception {
        log.info(" getProcesosAKSSGAByFechacarga - Begin " + formatFechaLog.format(new Date()));
        return interopJobsAKSSGAProcesoDAO.getProcesosAKSSGAByFechacarga(fechaCarga, con);
    }

    public List<InteropJobsAKSSGAProceso> getProcesosAKSSGAByFechacarga(long fechaCarga, AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" getProcesosAKSSGAByFechacarga - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.getProcesosAKSSGAByFechacarga(fechaCarga, con);
        } catch (BDException e) {
            log.error("BDException Recuperando Linea Datos ", e);
            throw (e);
        } catch (Exception ex) {
            log.error("Exception Recuperando Linea Datos ", ex);
            throw (ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }

    public InteropJobsAKSSGAProceso getProcesosAKSSGAById(long id, Connection con) throws SQLException, Exception {
        log.info(" getProcesosAKSSGAById - Begin " + formatFechaLog.format(new Date()));
        return interopJobsAKSSGAProcesoDAO.getProcesosAKSSGAById(id, con);
    }

    public InteropJobsAKSSGAProceso getProcesosAKSSGAById(long id, AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" getProcesosAKSSGAById - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.getProcesosAKSSGAById(id, con);
        } catch (BDException e) {
            log.error("BDException Recuperando Linea Datos ", e);
            throw (e);
        } catch (Exception ex) {
            log.error("Exception Recuperando Linea Datos ", ex);
            throw (ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }

    public InteropJobsAKSSGAProceso saveInteropJobsAKSSGAProceso(InteropJobsAKSSGAProceso data, Connection con) throws SQLException, Exception {
        log.info(" saveInteropJobsAKSSGAProceso - Begin " + formatFechaLog.format(new Date()));
        return interopJobsAKSSGAProcesoDAO.saveInteropJobsAKSSGAProceso(data, con);
    }

    public InteropJobsAKSSGAProceso saveInteropJobsAKSSGAProceso(InteropJobsAKSSGAProceso data, AdaptadorSQLBD adaptador) throws SQLException, Exception {
        log.info(" saveInteropJobsAKSSGAProceso - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.saveInteropJobsAKSSGAProceso(data, con);
        } catch (BDException e) {
            log.error("BDException Recuperando Linea Datos ", e);
            throw (e);
        } catch (Exception ex) {
            log.error("Exception Recuperando Linea Datos ", ex);
            throw (ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }

    public List<InteropJobsAKSSGAExpedienteRequest> getExpedientesTratarAKSSGAByProcedimiento(InteropJobsAKSSGAConfig procedimiento, Connection con) throws SQLException, Exception {
        log.info(" getExpedientesTratarAKSSGAByProcedimiento - Begin " + formatFechaLog.format(new Date()));
        return interopJobsAKSSGAConfigDAO.getExpedientesTratarAKSSGAByProcedimiento(procedimiento, con);
    }

    public List<InteropJobsAKSSGAExpedienteRequest> getExpedientesTratarAKSSGAByProcedimiento(InteropJobsAKSSGAConfig procedimiento, AdaptadorSQLBD adaptador) throws Exception {
        log.info(" getExpedientesTratarAKSSGAByProcedimiento - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.getExpedientesTratarAKSSGAByProcedimiento(procedimiento, con);
        } catch (BDException e) {
            log.error("BDException Recuperando Linea Datos ", e);
            throw (e);
        } catch (Exception ex) {
            log.error("Exception Recuperando Linea Datos ", ex);
            throw (ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }

    public boolean existeFicherosErrorUrlSGAcargaPIF(String urlSGACargaPIF) throws Lan6UtilExcepcion, Exception {
        log.info("existeFicherosErrorUrlSGAcargaPIF : " + urlSGACargaPIF);
        boolean respuesta = false;
        List<Lan6Documento> lan6ListaDocumentos = getListaDocumentosFromURLPIF(urlSGACargaPIF, false);
        if (lan6ListaDocumentos != null) {
            for (Lan6Documento lan6ListaDocumento : lan6ListaDocumentos) {
                if (lan6ListaDocumento.getNombre() != null && !lan6ListaDocumento.getNombre().isEmpty()
                        && lan6ListaDocumento.getNombre().toLowerCase().contains("_error")) {
                    respuesta = true;
                    break;
                }
            }
        } else {
            log.info("No se han recibido documentos en el directorio carga PIF: " + urlSGACargaPIF);
        }

        // Pruebas lectura desde url locales
        /*File directorioCarga = new File(urlSGACargaPIF);
        if (directorioCarga.exists()) {
            for (File ficherosRespuestaSGA : directorioCarga.listFiles()) {
                if (ficherosRespuestaSGA.getName().toLowerCase().endsWith("_error.xml")) {
                    respuesta = true;
                    break;
                }
            }
        } else {
            log.info("No se ha creado aun la estructura del directorio de respuesta para el procesamieto de ficheros SGA. " + urlSGACarga);
        }
         */
        log.info("existeFicherosErrorUrlSGAcargaPIF : End - " + respuesta);
        return respuesta;
    }

    public boolean existeFicherosErroInListLan6Document(List<Lan6Documento> listaDocumentos) throws Lan6UtilExcepcion, Exception {
        log.info("existeFicherosErroInListLan6Document ");
        boolean respuesta = false;
        if (listaDocumentos != null) {
            for (Lan6Documento lan6ListaDocumento : listaDocumentos) {
                if (lan6ListaDocumento.getNombre() != null && !lan6ListaDocumento.getNombre().isEmpty()
                        && lan6ListaDocumento.getNombre().toLowerCase().contains("_error")) {
                    respuesta = true;
                    break;
                }
            }
        } else {
            log.info("No se han recibido documentos la lista a validar : ");
        }

        log.info("existeFicherosErroInListLan6Document : End - " + respuesta);
        return respuesta;
    }

    public String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try {
            if (inputStream != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("readFromInputStream - Error : " + e.getMessage(), e);
        }
        return resultStringBuilder.toString();
    }

    public long guardarFiheroXMLRespuesta(File ficherosRespuestaSGA, Connection con) throws SQLException, Exception {
        log.info(" guardarFiheroXMLRespuesta - Begin " + formatFechaLog.format(new Date()));
        return interopJobsAKSSGAConfigDAO.guardarFiheroXMLRespuesta(getStringFromFileCotent(ficherosRespuestaSGA), con);
    }

    public long guardarFiheroXMLRespuesta(File ficherosRespuestaSGA, AdaptadorSQLBD adaptador) throws BDException, Exception {
        log.info(" guardarFiheroXMLRespuesta - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return this.guardarFiheroXMLRespuesta(ficherosRespuestaSGA, con);
        } catch (BDException e) {
            log.error("BDException Guardando Fichero XMl Respuesta ", e);
            throw (e);
        } catch (Exception ex) {
            log.error("Exception Guardando Fichero XMl Respuesta ", ex);
            throw (ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }

    private String getStringFromFileCotent(File ficherosRespuestaSGA) throws FileNotFoundException, IOException {
        InputStream inputStream = null;
        String respuesta = null;
        try {
            if (ficherosRespuestaSGA != null && ficherosRespuestaSGA.isFile()) {
                inputStream = new FileInputStream(ficherosRespuestaSGA);
                respuesta = readFromInputStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error al cerrar el inputstring en convertir contenido de Objeto File a String", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("Error al cerrar el inputstring en convertir contenido de Objeto File a String .. " + e.getMessage(), e);
                }
            }
        }
        return respuesta;
    }

    public void ejecutarProcedureGrabar_codigos_SGA(Integer fechaProcesar, Connection con) throws Exception {
        log.info(" ejecutarProcedureGrabar_codigos_SGA - Begin " + formatFechaLog.format(new Date()));
        interopJobsAKSSGAConfigDAO.ejecutarProcedureGrabar_codigos_SGA(fechaProcesar, con);
    }

    public void ejecutarProcedureGrabar_codigos_SGA(Integer fechaProcesar, AdaptadorSQLBD adaptador) throws BDException, Exception {
        log.info(" ejecutarProcedureGrabar_codigos_SGA - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            this.ejecutarProcedureGrabar_codigos_SGA(fechaProcesar, con);
        } catch (BDException e) {
            log.error("BDException ejecutarProcedureGrabar_codigos_SGA ", e);
            throw (e);
        } catch (Exception ex) {
            log.error("Exception ejecutarProcedureGrabar_codigos_SGA ", ex);
            throw (ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }

    public String getMelanbideDokusiPlateaProByCodigoProcedimientoFlexia(String codigoProcedimientoFlexia, Connection con) throws Exception {
        log.info(" getMelanbideDokusiPlateaProByCodProcedimientoPlatea - Begin " + formatFechaLog.format(new Date()));
        return interopJobsAKSSGAConfigDAO.getMelanbideDokusiPlateaProByCodigoProcedimientoFlexia(codigoProcedimientoFlexia, con);
    }

    public String getMelanbideDokusiPlateaProByCodigoProcedimientoFlexia(String codigoProcedimientoFlexia, AdaptadorSQLBD adaptador) throws BDException, Exception {
        log.info(" getMelanbideDokusiPlateaProByCodigoProcedimientoFlexia - Begin " + formatFechaLog.format(new Date()));
        Connection con = null;
        try {
            con = adaptador.getConnection();
            return getMelanbideDokusiPlateaProByCodigoProcedimientoFlexia(codigoProcedimientoFlexia, con);
        } catch (BDException e) {
            log.error("BDException getMelanbideDokusiPlateaProByCodigoProcedimientoFlexia ", e);
            throw (e);
        } catch (Exception ex) {
            log.error("Exception getMelanbideDokusiPlateaProByCodigoProcedimientoFlexia ", ex);
            throw (ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
            } catch (BDException e) {
                log.error("Error al cerrar conexion a la BBDD: " + e.getMessage());
            }
        }
    }

    public List<Lan6Documento> getListaDocumentosFromURLPIF(String urlPFIfrom, boolean conContenido) throws Lan6UtilExcepcion, Exception {
        log.info("getListaDocumentosFromURLPIF : " + urlPFIfrom + " contenido?=" + conContenido);
        List<Lan6Documento> respuesta = new ArrayList<Lan6Documento>();
        String app = interopJobsUtils.getParameter(InteropJobsAKSSGAConstantes.SGA_APP_PIF, interopJobsUtils.FICHERO_PROPIEDADES);
        log.info("app : " + app);
        String urlToken = (gestionAdaptadoresLan6Config.getElementoConfigFicheroAdaptadoresProperties("URL_TOKEN")+ "?app=" + app); //(Lan6Config.getProperty("AdaptadoresPlateaLan6.properties", "URL_TOKEN") + "?app=" + app);
        log.info("urlToken : " + urlToken);
        String sessionToken = Lan6Ficheros.obtenerFicheroURL(urlToken);
        log.info("sessionToken : " + sessionToken);
        String rutaURLPIF = gestionAdaptadoresLan6Config.getElementoConfigFicheroAdaptadoresProperties("URL_PIF"); // Lan6Config.getProperty("AdaptadoresPlateaLan6.properties", "URL_PIF");
        log.info("rutaURLPIF : " + rutaURLPIF);

        log.info("urlSGACargaPIF : " + urlPFIfrom);
        String usuarioPIF = interopJobsUtils.getParameter(InteropJobsAKSSGAConstantes.SGA_USUARIO_PIF, interopJobsUtils.FICHERO_PROPIEDADES);
        log.info("usuarioPIF : " + usuarioPIF);
        String passwordPIF = interopJobsUtils.getParameter(InteropJobsAKSSGAConstantes.SGA_PASS_PIF, interopJobsUtils.FICHERO_PROPIEDADES);
        log.info("passwordPIF : " + passwordPIF);

        Lan6GestionFicherosPif gestionFicherosPif = new Lan6GestionFicherosPif(sessionToken, rutaURLPIF, usuarioPIF, passwordPIF, null);
        log.info("Instanciada clase : Lan6GestionFicherosPif " + passwordPIF);
        byte[] listaFicheros = gestionFicherosPif.recuperarListaFicheros(urlPFIfrom);
        log.info("Invocado : gestionFicherosPif.recuperarListaFicheros ");
        String strListaJSON = new String(listaFicheros);
        log.info("strListaJSON : " + strListaJSON);
        List<Lan6Documento> lan6ListaDocumentos = Lan6PifTransformaciones.y31ListServletToListLan6Documento(strListaJSON);
        log.info("Invocado Lan6PifTransformaciones.y31ListServletToListLan6Documento : " + (lan6ListaDocumentos != null ? lan6ListaDocumentos.size() : 0));
        if (lan6ListaDocumentos != null) {
            for (Lan6Documento lan6ListaDocumento : lan6ListaDocumentos) {
                if (conContenido) {
                    log.info("Recuperando Contenido: " + lan6ListaDocumento.getIdRutaPif());
                    lan6ListaDocumento.setContenido(gestionFicherosPif.recuperarFichero(lan6ListaDocumento.getIdRutaPif()));
                }
                respuesta.add(lan6ListaDocumento);
            }
        } else {
            log.info("No se han recibido documentos en el directorio carga PIF: " + urlPFIfrom);
        }
        log.info("getListaDocumentosFromURLPIF - End : " + respuesta.size());
        return respuesta;
    }

    public String procesarFicheroRespuestaOK(List<Lan6Documento> ficherosRespuestaOK, AdaptadorSQLBD adaptador){
        String respuesta = "";
        try {
            String urlSGACargaProcesados = ConfigurationParameter.getParameter(InteropJobsAKSSGAConstantes.URL_PATH_CARGA_SGA_PROCESADOS,
                    interopJobsUtils.FICHERO_PROPIEDADES);
            // Obtenemos Ficheros del Dia anterior y los cargamos e BBDD
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_MONTH, -1);
            String tokenDay = formatFechaSGAYYYYMMDD.format(cal.getTime());
            log.info("Evaluando Ficheros del Dia : " + tokenDay);
            if(ficherosRespuestaOK != null && !ficherosRespuestaOK.isEmpty()){
                for (Lan6Documento ficherosRespuestaSGAPIF : ficherosRespuestaOK) {
                    log.info("Tratando fichero: " + ficherosRespuestaSGAPIF.getNombre());
                    if (ficherosRespuestaSGAPIF.getNombre() != null && !ficherosRespuestaSGAPIF.getNombre().isEmpty()
                            && ficherosRespuestaSGAPIF.getNombre().toLowerCase().contains("_ok")
                            && ficherosRespuestaSGAPIF.getNombre().toLowerCase().contains(tokenDay)) {
                        // Leemos contenido y lo almcenamos en BD
                        File ficherosRespuestaSGA = new File(urlSGACargaProcesados + "/" + ficherosRespuestaSGAPIF.getNombre());
                        ficherosRespuestaSGA.getParentFile().mkdirs();
                        FileUtils.writeByteArrayToFile(ficherosRespuestaSGA, ficherosRespuestaSGAPIF.getContenido());
                        log.info("Fichero registrado correctamente con el id: "
                                + guardarFiheroXMLRespuesta(ficherosRespuestaSGA, adaptador)
                        );
                    } else {
                        log.info("Fichero no procesado... Nombre no cumple las condiciones: "
                                + "fecha hoy (" + formatFechaSGAYYYYMMDD.format(new Date()) + ")/fichero tipo OK ");
                    }
                }
                respuesta = MessageFormat.format("Ficheros de respuesta OK del dia {0}, procesados correctamente, vamos a ejecutar el procedure para actualizar los expedientes", formatFechaSGAYYYYMMDD.format(new Date()));
                // Invocar procedure y procesar los ficheros para actualizar los datos del expediente.
                ejecutarProcedureGrabar_codigos_SGA(Integer.valueOf(formatFechaSGAYYYYMMDD.format(new Date())), adaptador);
            }else{
                respuesta = MessageFormat.format("No hay Ficheros de respuesta OK del dia {0}. ", formatFechaSGAYYYYMMDD.format(new Date()));
            }
        } catch (Exception e) {
            respuesta="Error al procesar los ficheros de respuesta " + e.getMessage() + " => " + ExceptionUtils.getRootCauseMessage(e);
            log.error(respuesta,e);
        }
        log.info(respuesta);
        return respuesta;
    }
}
