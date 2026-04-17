/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.entities.InteropLlamadasServiciosNisae;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores.ErrorLan6ExcepcionBeanNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores.GestionarErroresDokusiNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConfigurationParameter;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.MeLanbideInteropGeneralUtils;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.tercero.TerceroVO;
import es.altia.flexia.registro.digitalizacion.lanbide.util.GestionAdaptadoresLan6Config;
import es.altia.util.conexion.AdaptadorSQLBD;
import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6ErrorBean;
import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6Excepcion;
import java.util.List;
import net.lanbide.interoperability.otddff.beans.LanNISAEEntrada;
import net.lanbide.interoperability.otddff.beans.LanNISAESalida;
import net.lanbide.interoperability.otddff.servicios.LanConsultaObligacionesTributariasServicio;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author INGDGC
 */
public class GestionServiciosNISAEEjecucionHHFF implements Runnable{
    
    Logger m_Log = Logger.getLogger(this.getClass());

    public GestionServiciosNISAEEjecucionHHFF() {
        m_Log = Logger.getLogger(this.getClass());
    }

    private Thread comprobAdecAutThread = null;
    AdaptadorSQLBD adaptador = null;
    Integer codOrganizacion = null;
    String idProcoFlexia_LAN6;
    InteropLlamadasServiciosNisae interopLlamadasServiciosNisae;
    List<ExpedienteNisaeVO> listExpedientes;
    
    public void start(Integer _codOrganizacion,InteropLlamadasServiciosNisae _interopLlamadasServiciosNisae,String _idProcoFlexia_LAN6,AdaptadorSQLBD adapt) {
        comprobAdecAutThread = new Thread(this, "GestionServiciosNISAEEjecucionHHFF");
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
//            if(interopLlamadasServiciosNisae!=null && interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos()!=null && !interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos().isEmpty()
//                    && "1".equalsIgnoreCase(interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos())){
//                listExpedientes = servicioNISAERegexla.getExpedientesProcesarNISAEFiltroExpedientesEspecificos(codOrganizacion, interopLlamadasServiciosNisae, adaptador);
//            }else{
//                listExpedientes = servicioNISAERegexla.getExpedientesProcesarNISAE(codOrganizacion, interopLlamadasServiciosNisae, adaptador);
//            }
            
            if (listExpedientes != null && listExpedientes.size() > 0) {
                ExpedienteNisaeVO expedienteNisaeVO = new ExpedienteNisaeVO();
                for (ExpedienteNisaeVO listExpediente : listExpedientes) {
                    expedienteNisaeVO = listExpediente;
                    m_Log.info("-- Expediente Tratado : " + expedienteNisaeVO.getNumeroExpediente() + " - Peticion : " + expedienteNisaeVO.getIdPeticionPreviaBBDDLog() + " - " + expedienteNisaeVO.getCodigoEstadoSecundarioPeticion());
                    interopLlamadasServiciosNisae.setNumeroExpediente(expedienteNisaeVO.getNumeroExpediente());
                    try {
                        TerceroVO _tercero = expedienteNisaeVO.getTitularExpediente();
                        if (_tercero != null) {
                            tipo_doc = MeLanbideInteropGeneralUtils.parsearTipoDocFlexiaToTipoDocEJIE_WS(_tercero.getTipoDoc());
                            m_Log.info("estasCorrienteHaciendaForalHHFFBatch - TipoDocumento Tecero Calculado " + tipo_doc);
                            
                            // Comprobamos que solo llamemos para CAPV y que no haya oposicion al llmar al servicio
                            if((
                                    "01".equalsIgnoreCase(_tercero.getCodigoProvinciaDom())
                                    || "20".equalsIgnoreCase(_tercero.getCodigoProvinciaDom())
                                    || "48".equalsIgnoreCase(_tercero.getCodigoProvinciaDom())
                                ) && !servicioNISAERegexla.traducirSeOponeInteroperabilidad(expedienteNisaeVO.getOposicionObligacionesTributariasHF())
                               ){
                                
                                LanConsultaObligacionesTributariasServicio   servicios = new LanConsultaObligacionesTributariasServicio(idProcoFlexia_LAN6);
                                LanNISAEEntrada lan6ObligTribEntrada = new LanNISAEEntrada();

                                // Si hay una peticion previa pendiente solo pasamos los datos de la Peticion
                                if (expedienteNisaeVO.getCodigoEstadoSecundarioPeticion() != null && !expedienteNisaeVO.getCodigoEstadoSecundarioPeticion().isEmpty()) {
                                    m_Log.info("-- Existe una peticion previa : " + expedienteNisaeVO.getCodigoEstadoSecundarioPeticion());
                                    int idRegistroLogPadre=expedienteNisaeVO.getIdPeticionPreviaBBDDLog();
                                    interopLlamadasServiciosNisae.setIdPeticionPadre(idRegistroLogPadre);
                                    
                                    lan6ObligTribEntrada.setIdPeticion(String.valueOf(idRegistroLogPadre)); //Pasamos el ID de la peticion principal
                                    lan6ObligTribEntrada.setCodigoEstadoSecundario(expedienteNisaeVO.getCodigoEstadoSecundarioPeticion());
                                    
                                    int idRegistroLogHijo = servicioNISAERegexla.crearRegistroBBDDLogLlamadaHHFF(codOrganizacion, interopLlamadasServiciosNisae, idProcoFlexia_LAN6, lan6ObligTribEntrada, expedienteNisaeVO.getObservaciones(), expedienteNisaeVO.getNumeroExpediente(), adaptador);
                                    if (interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos() != null && !interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos().isEmpty() && interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos().equalsIgnoreCase("1")) {
                                        servicioNISAERegexla.actualizarEstadoExpedienteInListaFiltroExptsEspecificos(codOrganizacion, interopLlamadasServiciosNisae.getNumeroExpediente(), 1, "ID_PADRE=" + idRegistroLogPadre +"-ID_HIJO="+idRegistroLogHijo, adaptador);
                                    }
                                    //Asiganmos al objeto expedientes por si falla que se muestre el id en el mail de errores
                                    expedienteNisaeVO.setIdPeticionPreviaBBDDLog(idRegistroLogHijo);
                                    m_Log.info("ID Log Llamada Nueva Peticion Hija : " + idRegistroLogHijo);
                                    m_Log.info("ID Log BBDD Llamada Peticion Padre: " + idRegistroLogPadre);

                                    LanNISAESalida  lan6ObligTribSalida = servicios.consultaObligacionesTributarias(lan6ObligTribEntrada);
                                    
                                    if(lan6ObligTribSalida!=null){
                                        // Tratar repsueta del Servicio
                                        respuestaServicio = "Codigo estado: " + lan6ObligTribSalida.getCodigoEstado()
                                                + " Resultado: " + lan6ObligTribSalida.getResultado()
                                                + " Descripcion Resultado: " + lan6ObligTribSalida.getDescripcionResultado()
                                                + " Estado Secundario : " + lan6ObligTribSalida.getCodigoEstadoSecundario()
                                                + " Documento: " + lan6ObligTribSalida.getDocTitular()
                                                + " Peticion : " + lan6ObligTribSalida.getIdPeticion()
                                                + " Literal Estado: " + lan6ObligTribSalida.getLiteralEstado()
                                                + " Motivos no Cumple: " + lan6ObligTribSalida.getMotivosNoCumple()
                                                + " Literal Estado: " + lan6ObligTribSalida.getLiteralEstado()
                                                + " Motivo Erro: " + lan6ObligTribSalida.getMotivosError()
                                                + " Tiempo Estimado Respuesta : " + lan6ObligTribSalida.getTiempoEstimadoRespuesta();

                                        // Registramos el en BD el Id peticion, campo suplementario respuesta y actualizamos el a tabla de log
                                        servicioNISAERegexla.actualizarCamposSuplementariosNISAE_HHFF(codOrganizacion, expedienteNisaeVO.getNumeroExpediente(), lan6ObligTribSalida, adaptador);
                                        //Actalizamos la hija creada
                                        servicioNISAERegexla.actualizarRegistroBBDDLogLlamadaHHFF(expedienteNisaeVO.getIdPeticionPreviaBBDDLog(), lan6ObligTribSalida, expedienteNisaeVO.getObservaciones(), adaptador);
                                        // Si el estado de la consulta hija creda es != 0002 Actualizamos la padre para que la proxima se cree como nueva peticion.
                                        if (!"0002".equalsIgnoreCase(lan6ObligTribSalida.getCodigoEstado())) {
                                            servicioNISAERegexla.actualizarSoloEstadoYdescripcionRegistroBBDDLogLlamada(idRegistroLogPadre, lan6ObligTribSalida.getCodigoEstado(), lan6ObligTribSalida.getLiteralEstado(), adaptador);
                                        }
                                    }else{
                                        m_Log.error("OBjeto de respuesta servicio recibida a Null no se puede procesar..." + expedienteNisaeVO.getDocumentoInteresadoPeticion()+ " " + expedienteNisaeVO.getNumeroExpediente());
                                        servicioNISAERegexla.actualizarRegistroBBDDLogLlamadaHHFF(expedienteNisaeVO.getIdPeticionPreviaBBDDLog(), new net.lanbide.interoperability.otddff.beans.LanNISAESalida(),"Respuesta del servicio recibida a NUll no se pudo procesar la peticion. ", adaptador);
                                    } 

                                    
                                    
                                    expedienteNisaeVO = new ExpedienteNisaeVO();
                                } else {
                                    m_Log.info("-- Nueva Peticion");

                                    //Registramos la llamada en la tabla log para obtener nuestro secuncial
                                    //adaptador.inicioTransaccion(con);
                                    interopLlamadasServiciosNisae.setIdPeticionPadre(0);
                                    int idRegistroLog = servicioNISAERegexla.crearRegistroBBDDLogLlamadaHHFF(codOrganizacion, interopLlamadasServiciosNisae, idProcoFlexia_LAN6, lan6ObligTribEntrada, expedienteNisaeVO.getObservaciones(), expedienteNisaeVO.getNumeroExpediente(),adaptador);
                                    //Asiganmos al objeto expedientes por si falla que se muestre el id en el mail de errores
                                    expedienteNisaeVO.setIdPeticionPreviaBBDDLog(idRegistroLog);
                                    m_Log.info("ID Log Llamada " + idRegistroLog);
                                    //Hay que paser un secuencial sino falla, es nuestro ID en BD log
                                    lan6ObligTribEntrada.setIdPeticion(String.valueOf(idRegistroLog));

                                    lan6ObligTribEntrada.setIdExpediente(expedienteNisaeVO.getNumeroExpediente());
                                    lan6ObligTribEntrada.setTerritorio(_tercero.getCodigoProvinciaDom());

                                    //En caso de Ser desde Interfaz 
                                    /*
                            UsuarioValueObject usuarioValueObject = getUsarioLogueadoEnSession(request);
                            if (usuarioValueObject != null) {
                                cifUsuarioLogueado = MeLanbideInteropManager.getInstance().getUsuarioNIF(usuarioValueObject.getIdUsuario(), adapt);
                                nombreUsuarioLogueado = usuarioValueObject.getNombreUsu();
                            } else {
                                m_Log.error("Usuario no recogido desde session al llmar al WS");
                            }
                                     */
                                    // Caso de Batch
                                    cifUsuarioLogueado = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.RESPONSABLE_SERVICIO_NIF + interopLlamadasServiciosNisae.getProcedimientoHHFF(), ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                                    nombreUsuarioLogueado = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.RESPONSABLE_SERVICIO_NOMBRE + interopLlamadasServiciosNisae.getProcedimientoHHFF(), ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                                    m_Log.info("Responsable Servicio " + nombreUsuarioLogueado + "-" + nombreUsuarioLogueado);

                                    lan6ObligTribEntrada.setNombreCompletoFuncionario(nombreUsuarioLogueado);
                                    lan6ObligTribEntrada.setNifFuncionario(cifUsuarioLogueado);

                                    lan6ObligTribEntrada.setConsentimiento(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.CONSENTIMIENTO + interopLlamadasServiciosNisae.getProcedimientoHHFF(), ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                                    lan6ObligTribEntrada.setTipoDocTitular(tipo_doc);
                                    lan6ObligTribEntrada.setDocTitular(_tercero.getDoc());
                                    lan6ObligTribEntrada.setNombreTitular(_tercero.getNombre());
                                    lan6ObligTribEntrada.setApellido1((_tercero.getApellido1()!=null && !_tercero.getApellido1().isEmpty()
                                            ? _tercero.getApellido1() // SI es DNI/NIE y viene sin apellidos puede ser Autonomo (Registra solo nombre completo) o simplemente que no tiene segundo apellido
                                            : ("1".equalsIgnoreCase(_tercero.getTipoDoc()) || "3".equalsIgnoreCase(_tercero.getTipoDoc())?"N/A":"")
                                            )
                                    );
                                    lan6ObligTribEntrada.setApellido2((_tercero.getApellido2()!=null && !_tercero.getApellido2().isEmpty()
                                            ? _tercero.getApellido2() // SI es DNI/NIE y viene sin apellidos puede ser Autonomo (Registra solo nombre completo) o simplemente que no tiene segundo apellido
                                            : ("1".equalsIgnoreCase(_tercero.getTipoDoc()) || "3".equalsIgnoreCase(_tercero.getTipoDoc())?"N/A":"")
                                            )
                                    );
                                    
                                    // Actulizo el log con los datos enviados
                                    servicioNISAERegexla.actualizarTextoJsonDatosEnviadosDocTTHHPeticionRegistroBBDDLogLlamada(idRegistroLog, lan6ObligTribEntrada, adaptador);
                                    
                                    if (interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos() != null && !interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos().isEmpty() && interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos().equalsIgnoreCase("1")) {
                                        servicioNISAERegexla.actualizarEstadoExpedienteInListaFiltroExptsEspecificos(codOrganizacion, interopLlamadasServiciosNisae.getNumeroExpediente(), 1, "ID=" + idRegistroLog, adaptador);
                                    }

                                    net.lanbide.interoperability.otddff.beans.LanNISAESalida lan6ObligTribSalida = servicios.consultaObligacionesTributarias(lan6ObligTribEntrada);

                                    // Tratar repsueta del Servicio
                                    respuestaServicio = "Codigo estado: " + lan6ObligTribSalida.getCodigoEstado()
                                            + " Resultado: " + lan6ObligTribSalida.getResultado()
                                            + " Descripcion Resultado: " + lan6ObligTribSalida.getDescripcionResultado()
                                            + " Estado Secundario : " + lan6ObligTribSalida.getCodigoEstadoSecundario()
                                            + " Documento: " + lan6ObligTribSalida.getDocTitular()
                                            + " Peticion : " + lan6ObligTribSalida.getIdPeticion()
                                            + " Literal Estado: " + lan6ObligTribSalida.getLiteralEstado()
                                            + " Motivos no Cumple: " + lan6ObligTribSalida.getMotivosNoCumple()
                                            + " Literal Estado: " + lan6ObligTribSalida.getLiteralEstado()
                                            + " Motivo Erro: " + lan6ObligTribSalida.getMotivosError()
                                            + " Tiempo Estimado Respuesta : " + lan6ObligTribSalida.getTiempoEstimadoRespuesta();
                                    // Registramos el en BD el Id peticion, campo suplementario respuesta y actualizamos el a tabla de log
                                    servicioNISAERegexla.actualizarCamposSuplementariosNISAE_HHFF(codOrganizacion, expedienteNisaeVO.getNumeroExpediente(), lan6ObligTribSalida, adaptador);
                                    servicioNISAERegexla.actualizarRegistroBBDDLogLlamadaHHFF(idRegistroLog, lan6ObligTribSalida, expedienteNisaeVO.getObservaciones(), adaptador);

                                    expedienteNisaeVO = new ExpedienteNisaeVO();
                                }
                            }else{
                                // NO invocamos el servicio para ese expediente
                                int idRegistroLog = servicioNISAERegexla.crearRegistroBBDDLogLlamadaHHFF(codOrganizacion, interopLlamadasServiciosNisae, idProcoFlexia_LAN6, new net.lanbide.interoperability.otddff.beans.LanNISAEEntrada()
                                        , "Servicio no invocado:  titular con domicilio fuera de la CAPV " + _tercero.getDoc() + " "+ _tercero.getCodigoProvinciaDom()
                                        + " - o hay oposicion a la llamada de los servicios de interoperabilidad."
                                        , expedienteNisaeVO.getNumeroExpediente()
                                        ,adaptador);
                                //Asiganmos al objeto expedientes por si falla que se muestre el id en el mail de errores
                                expedienteNisaeVO.setIdPeticionPreviaBBDDLog(idRegistroLog);
                                m_Log.info("ID Log Llamada " + idRegistroLog);
                                if (interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos() != null && !interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos().isEmpty() && interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos().equalsIgnoreCase("1")) {
                                    servicioNISAERegexla.actualizarEstadoExpedienteInListaFiltroExptsEspecificos(codOrganizacion, interopLlamadasServiciosNisae.getNumeroExpediente(), 1, "ID=" + idRegistroLog, adaptador);
                                }
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
                            String trazaError = ex.getTrazaExcepcion();  // ExceptionUtils.getStackTrace(ex);

                            Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                                    trazaError, ConstantesMeLanbideInterop.ERROR_NISAE_SISTEMA_ORIGEN_FLEXIA,
                                    ConstantesMeLanbideInterop.ERROR_NISAE_CODIGO_ERROR_HHFF, ConstantesMeLanbideInterop.ERROR_NISAE_MENSAJE_HHFF, ex.gettipologia());
                            ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, ex);
                            int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, expedienteNisaeVO.getNumeroExpediente(), idProcoFlexia_LAN6, expedienteNisaeVO.getIdPeticionPreviaBBDDLog() + " - " +expedienteNisaeVO.getCodigoEstadoSecundarioPeticion(), "estasCorrienteHaciendaForalHHFFBatch");
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
                        ConstantesMeLanbideInterop.ERROR_NISAE_CODIGO_ERROR_HHFF, ConstantesMeLanbideInterop.ERROR_NISAE_MENSAJE_HHFF, 1);
                ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, ex);
                int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, interopLlamadasServiciosNisae.getNumeroExpedienteDesde()+ "-" + interopLlamadasServiciosNisae.getNumeroExpedienteHasta(), idProcoFlexia_LAN6, interopLlamadasServiciosNisae.getEjercicioHHFF() + "-" + interopLlamadasServiciosNisae.getProcedimientoHHFF(), "estasCorrienteHaciendaForalHHFFBatch");
                m_Log.info("Error Registrado en BD correctamente. " + idError);
                respuestaServicio += " - Mas detalles en el Gestor Errores : ID:" + idError;
            } catch (Exception ex1) {
                m_Log.error("Dokusi.Flexia Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : " + ex.getMessage(), ex1);
                respuestaServicio += " - Error al intentar registrar y notificar un error en la ejecucion del proceso : " + ex1.getMessage();
                //throw ex1;
            }
        } finally {
            try {
                m_Log.info("Fin Proceso Segundo Plano... ");
                //adaptador.finTransaccion(con);
                //if (adaptador != null) {
                //    adaptador.devolverConexion(con);
                //}
            } catch (Exception ex) {
                ex.printStackTrace();
                m_Log.error("Error la ejecutar cierres de Conexiones ",ex);
            }
        }
        comprobAdecAutThread.stop();
    }
    
}
