/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.entities.InteropLlamadasServiciosNisae;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores.ErrorLan6ExcepcionBeanNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores.GestionarErroresDokusiNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConfigurationParameter;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.MeLanbideInteropGeneralUtils;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.tercero.TerceroVO;
import es.altia.util.conexion.AdaptadorSQLBD;
import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6ErrorBean;
import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6Excepcion;
import es.lanbide.lan6.cliente.ConsultaTerceroClient;
import es.lanbide.lan6.dto.ConsultaTercerosResponse;
import java.util.List;
import net.lanbide.interoperability.tgss.beans.LanNISAESalida;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author INGDGC
 */
public class GestionServiciosNISAEEjecucionEIKA implements Runnable{
    
    private final Logger m_Log = Logger.getLogger(this.getClass());

    private Thread comprobAdecAutThread = null;
    AdaptadorSQLBD adaptador = null;
    Integer codOrganizacion = null;
    String idProcoFlexia_LAN6;
    InteropLlamadasServiciosNisae interopLlamadasServiciosNisae;
    List<ExpedienteNisaeVO> listExpedientes;
    
    public void start(Integer _codOrganizacion,InteropLlamadasServiciosNisae _interopLlamadasServiciosNisae,String _idProcoFlexia_LAN6,AdaptadorSQLBD adapt) {
        comprobAdecAutThread = new Thread(this, "GestionServiciosNISAEEjecucionEIKA");
        adaptador = adapt;
        codOrganizacion=_codOrganizacion;
        interopLlamadasServiciosNisae=(_interopLlamadasServiciosNisae!=null ? _interopLlamadasServiciosNisae : new InteropLlamadasServiciosNisae());
        idProcoFlexia_LAN6=_idProcoFlexia_LAN6;
        comprobAdecAutThread.start();
    }

    @Override
    public void run() {
        int i = 0;
        String resultadoProceso = "OK";
        String mensajeErrorProceso = "";
        String respuestaServicio = "";
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
       
        //Connection con = null;
        try {
            //con = adaptador.getConnection();
            
            m_Log.info("idProcedimiento : " + idProcoFlexia_LAN6);
            String tipo_doc = "";
            String cifUsuarioLogueado = "";
            String nombreUsuarioLogueado = "";
            // Expedientes a Tratar
            GestionServiciosNISAE servicioNISAERegexla = new GestionServiciosNISAE();
            listExpedientes = servicioNISAERegexla.getExpedientesProcesarNISAE(codOrganizacion, interopLlamadasServiciosNisae, adaptador);
            if (listExpedientes != null && listExpedientes.size() > 0) {
                ExpedienteNisaeVO expedienteNisaeVO = new ExpedienteNisaeVO();
                for (ExpedienteNisaeVO listExpediente : listExpedientes) {
                    expedienteNisaeVO = listExpediente;
                    m_Log.info("-- Expediente Tratado : " + expedienteNisaeVO.getNumeroExpediente() + " - Peticion : " + expedienteNisaeVO.getIdPeticionPreviaBBDDLog() + " - " + expedienteNisaeVO.getCodigoEstadoSecundarioPeticion());
                    interopLlamadasServiciosNisae.setNumeroExpediente(expedienteNisaeVO.getNumeroExpediente());
                    try {
                        TerceroVO _tercero = expedienteNisaeVO.getTitularExpediente();
                        if (_tercero != null) {
                            tipo_doc = MeLanbideInteropGeneralUtils.parsearTipoDocFlexiaToTipoDocEIKA_WS(_tercero.getTipoDoc());
                            m_Log.info("GestionServiciosNISAEEjecucionEIKA - TipoDocumento Tecero Calculado " + tipo_doc);
                                             
                                m_Log.info("-- Nueva Peticion");
                                interopLlamadasServiciosNisae.setDocumentoInteresado(_tercero.getDoc());
                                interopLlamadasServiciosNisae.setTerritorioHistorico(_tercero.getCodigoProvinciaDom());
                                
                                ConsultaTercerosResponse respuesta = null;
                                ConsultaTerceroClient servicio = new ConsultaTerceroClient();

                                // Registramos la llamada en la tabla log para obtener nuestro secuncial 
                                String jsonEnviado = "{tipoIdFiscal:"+tipo_doc+", nif:"+_tercero.getDoc()+"}";                                 
                                int idRegistroLog = servicioNISAERegexla.crearRegistroBBDDLogLlamadaEIKA(codOrganizacion, interopLlamadasServiciosNisae,jsonEnviado, adaptador);
                                //Actualizamos estado de la tabla filtros exp. especificos
                                if (interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos() != null && !interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos().isEmpty() && interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos().equalsIgnoreCase("1")) {
                                    servicioNISAERegexla.actualizarEstadoExpedienteInListaFiltroExptsEspecificos(codOrganizacion, interopLlamadasServiciosNisae.getNumeroExpediente(),1,"ID="+idRegistroLog, adaptador);
                                }
                                //Asigamos al objeto expedientes por si falla que se muestre el id en el mail de errores
                                expedienteNisaeVO.setIdPeticionPreviaBBDDLog(idRegistroLog);
                                interopLlamadasServiciosNisae.setIdPeticionPadre(0);
                                interopLlamadasServiciosNisae.setId(idRegistroLog);
                                m_Log.info("ID Log Llamada " + idRegistroLog);                               
                                
                                //Ejecutamos llamada al Servicio
                                respuesta = servicio.requestSOAP(tipo_doc,_tercero.getDoc());
                                
                                if(respuesta!=null){
                                    // Tratar repsueta del Servicio
                                    respuestaServicio = "Respuesta: " + gson.toJson(respuesta);
                                    // Registramos el en BD el Id peticion, campo suplementario respuesta y actualizamos el a tabla de log
                                    servicioNISAERegexla.actualizarCamposSuplementariosNISAE_EIKA(codOrganizacion, expedienteNisaeVO.getNumeroExpediente(), respuesta, adaptador);
                                    servicioNISAERegexla.actualizarRegistroBBDDLogLlamadaEIKA(idRegistroLog,respuesta, adaptador);
                                    expedienteNisaeVO = new ExpedienteNisaeVO();
                                }else {
                                    m_Log.error("OBjeto de respuesta servicio recibida a Null no se puede procesar..." + expedienteNisaeVO.getDocumentoInteresadoPeticion() + " " + expedienteNisaeVO.getNumeroExpediente());
                                    servicioNISAERegexla.actualizarRegistroBBDDLogLlamadaTGSS(expedienteNisaeVO.getIdPeticionPreviaBBDDLog(), new LanNISAESalida(), "Respuesta del servicio recibida a NUll no se pudo procesar la peticion. ", adaptador);
                                }
                        } else {
                            m_Log.info("-- Datos tercero no recuperado para el expediente " + expedienteNisaeVO.getNumeroExpediente());
                        }
                    } catch (Lan6Excepcion ex) {
                        m_Log.info(" Error de tipo Lan6Excepcion : " + ex.getMessage());
                        try {
                            m_Log.info("Registrar el error en BD : " + (ex.getMessages().size() > 0 ? ex.getMessages().get(0).toString().toString() : ""));
                            String causaExcepcion = (ex.getCause() != null ? (ex.getCause().getMessage() != null ? ex.getCause().getMessage() + " - " + ex.getCause().toString() : ex.getCause().toString()) : "");
                            String mensajeExcepcion = (ex.getMessages().size() > 0 ? ex.getMessages().get(0).toString() : "Mensaje de error no recibido en la peticion");
                            String trazaError = ex.getTrazaExcepcion();  

                            Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                                    trazaError, ConstantesMeLanbideInterop.ERROR_NISAE_SISTEMA_ORIGEN_FLEXIA,
                                    ConstantesMeLanbideInterop.ERROR_NISAE_CODIGO_ERROR_EIKA, ConstantesMeLanbideInterop.ERROR_NISAE_MENSAJE_EIKA, ex.gettipologia());
                            ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, ex);
                            int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, expedienteNisaeVO.getNumeroExpediente(), idProcoFlexia_LAN6, expedienteNisaeVO.getIdPeticionPreviaBBDDLog() + " - " +expedienteNisaeVO.getCodigoEstadoSecundarioPeticion(), "estasCorrienteSeguridadSocialTGSSBatch");
                            m_Log.info("Error Registrado en BD correctamente. " + idError);
                        } catch (Exception ex1) {
                            m_Log.error("Dokusi.Flexia Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : " + ex.getMessage(), ex1);
                            //throw ex1;
                        }
                    }
                }
            } else {
                m_Log.info("Lista de expedientes para tramitar recibida vacia.. ");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            m_Log.error("Error la ejecutando proceso en segundo plano ",ex);
            String mensajeExcepcion = "";
            respuestaServicio = "Error al invocar el proceso en segundo plano : " + ex.getMessage();
            try {
                m_Log.info("Vamos a registrar el error en BD : " + ex.getMessage());
                String causaExcepcion = (ex.getCause() != null ? (ex.getCause().getMessage() != null ? ex.getCause().getMessage() + " - " + ex.getCause().toString() : ex.getCause().toString()) : "");
                mensajeExcepcion = ex.getMessage();
                String trazaError = ExceptionUtils.getStackTrace(ex);  // ExceptionUtils.getStackTrace(ex);

                Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                        trazaError, ConstantesMeLanbideInterop.ERROR_NISAE_SISTEMA_ORIGEN_FLEXIA,
                        ConstantesMeLanbideInterop.ERROR_NISAE_CODIGO_ERROR_EIKA, ConstantesMeLanbideInterop.ERROR_NISAE_MENSAJE_EIKA, 1);
                ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, ex);
                int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, interopLlamadasServiciosNisae.getNumeroExpedienteDesde()+ "-" + interopLlamadasServiciosNisae.getNumeroExpedienteHasta(), idProcoFlexia_LAN6, interopLlamadasServiciosNisae.getEjercicioHHFF() + "-" + interopLlamadasServiciosNisae.getProcedimientoHHFF(), "consultaDatosFiscalesEIKABatch");
                m_Log.info("Error Registrado en BD correctamente. " + idError);
                respuestaServicio += " - Mas detalles en el Gestor Errores : ID:" + idError;
            } catch (Exception ex1) {
                m_Log.error("EIKA.Flexia Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : " + ex.getMessage(), ex1);
                respuestaServicio += " - Error al intentar registrar y notificar un error en la ejecucion del proceso : " + ex1.getMessage();
                //throw ex1;
            }
        } finally {
            try {
                m_Log.info("Fin Proceso Segundo Plano... ");
            } catch (Exception ex) {
                ex.printStackTrace();
                m_Log.error("Error la ejecutar cierres de Conexiones ",ex);
            }
        }
        comprobAdecAutThread.stop();
    }
    
}
