/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.manager;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.dao.MeLanbideConvocatoriasDAO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.dao.InteropJobsDaoMapping;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.dao.InteropJobsFSEProcediDAO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConfigurationParameter;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.MeLanbideConvocatorias;
import es.altia.flexia.integracion.moduloexterno.plugin.ModuloIntegracionExternoCampoSupFactoria;
import es.altia.flexia.integracion.moduloexterno.plugin.camposuplementario.IModuloIntegracionExternoCamposFlexia;
import es.altia.flexia.integracion.moduloexterno.plugin.persistence.vo.CampoSuplementarioModuloIntegracionVO;
import es.altia.flexia.integracion.moduloexterno.plugin.persistence.vo.SalidaIntegracionVO;
import es.altia.util.conexion.AdaptadorSQLBD;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author INGDGC
 */
public class MeLanbideConvocatoriasManager {
    
    private static final String BARRA_SEPARADORA = "/";
    private static final String CODIGO_CAMPO_SUP_FECHA_REFE_EXPTE_DECRETO_APLI = "CODIGO_CAMPO_SUP_FECHA_REFE_EXPTE_DECRETO_APLI";
    
    private static final Logger log = Logger.getLogger(MeLanbideConvocatoriasManager.class);
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    SimpleDateFormat formatFechaddMMyyy = new SimpleDateFormat("dd/MM/yyyy");
    private final MeLanbideConvocatoriasDAO meLanbideConvocatoriasDAO = new MeLanbideConvocatoriasDAO();
    
    /**
     * Metodo que devuelve el dereto o convocatoria a la que pertenece el expediente en funcion de una fecha de referencia recbida: Ejemplo, Fecha
     * presentacion expediente (campo suplementario).
     * @param codOrganizacion
     * @param numExpediente
     * @param adaptador
     * @return Objeto con los datos de la convocatoria o decreto aplicacble segun la fecha de referecia : Tipo MeLanbideConvocatorias tabla MELANBIDE_CONVOCATORIAS
     * @throws Exception
     */
    public MeLanbideConvocatorias getDecretoAplicableExpediente(Integer codOrganizacion, String numExpediente, AdaptadorSQLBD adaptador) throws Exception {
        log.info("getDecretoAplicableExpediente() - Manager - : BEGIN " + formatFechaLog.format(new Date()) + " " + numExpediente);
        Connection con = null;
        try {
            // Recogemos los datos Necesarios
            Date fechaReferenciaExpediente = null;
            fechaReferenciaExpediente = getFechaReferenciaDecretoExpediente(codOrganizacion, numExpediente, adaptador);
            log.info("Fecha Referencia Expediente: " + formatFechaddMMyyy.format(fechaReferenciaExpediente));
            String codProcedimiento = getCodProcedimientoDeExpediente(numExpediente);
            con = adaptador.getConnection();
            return meLanbideConvocatoriasDAO.getDecretoAplicableExpediente(fechaReferenciaExpediente, codProcedimiento, con);
        } catch (Exception ex) {
            log.error("Se ha producido una excepción general al recuperar datos del DECRETO " + ex.getMessage(), ex);
            throw new Exception("Error al leer Datos de DECRETO. " + ex.getMessage(), ex);
        } finally {
            try {
                adaptador.devolverConexion(con);
                log.info("getDecretoAplicableExpediente() - Manager - End  " + formatFechaLog.format(new Date()));
            } catch (Exception e) {
                log.error("Error al cerrar conexión a la BBDD: getDecretoAplicableExpediente - " + e.getMessage());
            }
        }
    }

    public Date getFechaReferenciaDecretoExpediente(Integer codOrganizacion, String numExpediente, AdaptadorSQLBD adapt) throws Exception {
        log.info("getFechaReferenciaDecretoExpediente() - Manager - : BEGIN " + formatFechaLog.format(new Date()) + " " + numExpediente);
        Connection con = null;
        Date respuesta = null;
        try {
            con = adapt.getConnection();
            String campoSuplementarioFechRefExpe="";
            try {
                campoSuplementarioFechRefExpe = ConfigurationParameter.getParameter(CODIGO_CAMPO_SUP_FECHA_REFE_EXPTE_DECRETO_APLI, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
            } catch (Exception e) {
                log.error("No se ha recuperado el codigo del campo sup. de referencia para expediente, Properties .."+ConstantesMeLanbideInterop.FICHERO_PROPIEDADES, e);
            }
            
            if (campoSuplementarioFechRefExpe != null && !campoSuplementarioFechRefExpe.isEmpty()) {
                Integer ejercicio = getEjercicioDeExpediente(numExpediente);
                String codProcedimiento = getCodProcedimientoDeExpediente(numExpediente);
                respuesta = getCampoSuplementarioFecha(codOrganizacion, ejercicio, codProcedimiento, numExpediente, campoSuplementarioFechRefExpe);
            } else {
                respuesta = meLanbideConvocatoriasDAO.getFechaAltaExpediente(codOrganizacion, numExpediente, con);
            }
        } catch (Exception ex) {
            log.error("Se ha producido una excepción general al recuperar datos de fecha referencia del expedientes para el decreto aplicable " + ex.getMessage(), ex);
            throw new Exception("Error al leer Datos de DECRETO. " + ex.getMessage(), ex);
        } finally {
            try {
                adapt.devolverConexion(con);
                log.info("getDecretoAplicableExpediente() - Manager - End  " + formatFechaLog.format(new Date()));
            } catch (Exception e) {
                log.error("Error al cerrar conexión a la BBDD: getDecretoAplicableExpediente - " + e.getMessage());
            }
        }
        return respuesta;
    }

    public Date getCampoSuplementarioFecha(Integer codOrganizacion, Integer ejercicio, String codProcedimiento, String numExpediente, String codigoCampo) throws Exception {
        log.info("getCampoSuplementarioFecha() : BEGIN ");
        Date valor = null;
        IModuloIntegracionExternoCamposFlexia el = ModuloIntegracionExternoCampoSupFactoria.getInstance().getImplClass();
        SalidaIntegracionVO salida = new SalidaIntegracionVO();
        CampoSuplementarioModuloIntegracionVO campoSuplementario = new CampoSuplementarioModuloIntegracionVO();
        salida = el.getCampoSuplementarioExpediente(String.valueOf(codOrganizacion), String.valueOf(ejercicio), numExpediente,
                codProcedimiento, codigoCampo, 3);
        if (salida.getStatus() == 0) {
            campoSuplementario = salida.getCampoSuplementario();
            if (campoSuplementario != null && campoSuplementario.getValorFecha() != null) {
                valor = campoSuplementario.getValorFecha().getTime();
            }
        }//if(salida.getStatus() == 0)
        log.info("getCampoSuplementarioFecha() : END ");
        return valor;
    }
    
    public static Integer getEjercicioDeExpediente(String numExpediente) {
        try {
            String[] datos = numExpediente.split(BARRA_SEPARADORA);
            return Integer.parseInt(datos[0]);
        } catch (Exception ex) {
            log.error("getEjercicioDeExpediente - ", ex);
            return null;
        }
    }

    public static String getCodProcedimientoDeExpediente(String numExpediente) {
        try {
            String[] datos = numExpediente.split(BARRA_SEPARADORA);
            return datos[1];
        } catch (Exception ex) {
            log.error("getCodProcedimientoDeExpediente - ", ex);
            return null;
        }
    }
    
}
