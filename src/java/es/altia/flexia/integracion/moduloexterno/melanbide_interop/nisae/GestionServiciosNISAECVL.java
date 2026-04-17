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
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.DatosEspecificos;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Persona;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.RequestRestServiceCVL;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.ResponseRestServiceCVL;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Tramitador;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.tercero.TerceroVO;
import es.altia.flexia.integracion.moduloexterno.plugin.ModuloIntegracionExternoCampoSupFactoria;
import es.altia.flexia.integracion.moduloexterno.plugin.camposuplementario.IModuloIntegracionExternoCamposFlexia;
import es.altia.flexia.integracion.moduloexterno.plugin.persistence.vo.CampoSuplementarioModuloIntegracionVO;
import es.altia.flexia.integracion.moduloexterno.plugin.persistence.vo.SalidaIntegracionVO;
import es.altia.util.conexion.AdaptadorSQLBD;
import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6ErrorBean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
/**
 *
 * @author INGDGC
 */
public class GestionServiciosNISAECVL implements Runnable {

    Logger m_Log = Logger.getLogger(this.getClass());

    public GestionServiciosNISAECVL() {
        m_Log = Logger.getLogger(this.getClass());
    }

    private Thread comprobAdecAutThread = null;
    AdaptadorSQLBD adaptador = null;
    Integer codOrganizacion = null;
    String idProcoFlexia_LAN6;
    InteropLlamadasServiciosNisae interopLlamadasServiciosNisae;
    List<ExpedienteNisaeVO> listExpedientes;
    private List<Persona> personas = null;
    private boolean llamadaWithoutThread = false;
    
    public void start(Integer _codOrganizacion, InteropLlamadasServiciosNisae _interopLlamadasServiciosNisae, String _idProcoFlexia_LAN6, AdaptadorSQLBD adapt) {
        comprobAdecAutThread = new Thread(this, "GestionServiciosNISAECVL");
        adaptador = adapt;
        codOrganizacion = _codOrganizacion;
        interopLlamadasServiciosNisae = (_interopLlamadasServiciosNisae != null ? _interopLlamadasServiciosNisae : new InteropLlamadasServiciosNisae());
        idProcoFlexia_LAN6 = _idProcoFlexia_LAN6;
        llamadaWithoutThread = false;
        comprobAdecAutThread.start();
    }

    public void startWithoutThread(Integer _codOrganizacion, InteropLlamadasServiciosNisae _interopLlamadasServiciosNisae, String _idProcoFlexia_LAN6, AdaptadorSQLBD adapt) {
        comprobAdecAutThread = new Thread(this, "GestionServiciosNISAECVL");
        adaptador = adapt;
        codOrganizacion = _codOrganizacion;
        interopLlamadasServiciosNisae = (_interopLlamadasServiciosNisae != null ? _interopLlamadasServiciosNisae : new InteropLlamadasServiciosNisae());
        idProcoFlexia_LAN6 = _idProcoFlexia_LAN6;
    }
    
    public void startWithoutThread(Integer _codOrganizacion, InteropLlamadasServiciosNisae _interopLlamadasServiciosNisae, 
            String _idProcoFlexia_LAN6, final List<Persona> personas, AdaptadorSQLBD adapt) {
        comprobAdecAutThread = new Thread(this, "GestionServiciosNISAECVL: startWithoutThread");
        adaptador = adapt;
        codOrganizacion = _codOrganizacion;
        interopLlamadasServiciosNisae = (_interopLlamadasServiciosNisae != null ? _interopLlamadasServiciosNisae : new InteropLlamadasServiciosNisae());
        idProcoFlexia_LAN6 = _idProcoFlexia_LAN6;
        this.personas = personas;
        llamadaWithoutThread = true;
    }

    @Override
    public void run() {
        String respuestaServicio = "";
        try {
            String cifUsuarioLogueado = "";
            String nombreUsuarioLogueado = "";
            GestionServiciosNISAE gestionServiciosNISAE = new GestionServiciosNISAE();
            // Expedientes a Tratar
            listExpedientes = gestionServiciosNISAE.getExpedientesProcesarNISAE(codOrganizacion, interopLlamadasServiciosNisae, adaptador);
            if (listExpedientes != null && listExpedientes.size() > 0) {
                //Iteracion de todos los expedientes recuperados
                cifUsuarioLogueado = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.RESPONSABLE_SERVICIO_NIF + interopLlamadasServiciosNisae.getProcedimientoHHFF() + ConstantesMeLanbideInterop.BARRA_BAJA + interopLlamadasServiciosNisae.getFkWSSolicitado(), ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                nombreUsuarioLogueado = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.RESPONSABLE_SERVICIO_NOMBRE + interopLlamadasServiciosNisae.getProcedimientoHHFF() + ConstantesMeLanbideInterop.BARRA_BAJA + interopLlamadasServiciosNisae.getFkWSSolicitado(), ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                for (ExpedienteNisaeVO vo : listExpedientes) {
                    m_Log.info("Expediente : " + vo.getNumeroExpediente());
                    String codigoProcFlexia = MeLanbideInteropGeneralUtils.getCodProcedimientoFromNumExpediente(vo.getNumeroExpediente());
                    //Tipo de documento del demandadnte que se esta consultado
                    /*if (vo.getTitularExpediente() != null) {
                        m_Log.info("Tipo de documento previo al cáculo: " + vo.getTitularExpediente().getTipoDoc());
                        tipo_doc = ("1".equalsIgnoreCase(vo.getTitularExpediente().getTipoDoc()) ? "D" : "3".equalsIgnoreCase(vo.getTitularExpediente().getTipoDoc()) ? "E" : "2".equalsIgnoreCase(vo.getTitularExpediente().getTipoDoc()) ? "P" :"");
                        m_Log.info("consultaVidaLaboralCVLBatch - TipoDocumento Tercero Calculado " + tipo_doc);
                    }*/

                    //Parametrizamos la llamadas
                    String idPeticion = codigoProcFlexia + new Date();

                    RequestRestServiceCVL requestRestServiceCVL = new RequestRestServiceCVL();
                    es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Tramitador tramitador = new es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Tramitador();
                    Persona persona = new Persona();
                    DatosEspecificos datosEspecificos = new DatosEspecificos();

                    tramitador.setCodProcedimiento(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.PREFIJO_CODIGO_PROCEDIMIENTO + "CVL" + ConstantesMeLanbideInterop.BARRA_BAJA + codigoProcFlexia, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                    tramitador.setConsentimientoFirmado(ConfigurationParameter.getParameter("CONSENTIMIENTO_FIRMADO_CVL_" + codigoProcFlexia, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                    tramitador.setFinalidadProcedimiento(ConfigurationParameter.getParameter("FINALIDAD_PROCEDIMIENTO_CVL_" + codigoProcFlexia, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));
                    tramitador.setNifUsuarioTramitador(cifUsuarioLogueado);
                    tramitador.setUsuarioTramitador(nombreUsuarioLogueado);
                    tramitador.setNombreProcedimiento(ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_PROCEDIMIENTO + codigoProcFlexia, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES));

                     // Gestion Peeticiones por persona

                    if(!llamadaWithoutThread){ // Llamadas online desde pestana del expediente son llamadaWithoutThread, !llamadaWithoutThread para llamadas en segundo plano.
                        // Por defecto lo qu ehace en LAK Llamando desde procesos NISAE.
                        personas = new ArrayList<Persona>();
                        IModuloIntegracionExternoCamposFlexia el = ModuloIntegracionExternoCampoSupFactoria.getInstance().getImplClass();
                        SalidaIntegracionVO salida = new SalidaIntegracionVO();
                        CampoSuplementarioModuloIntegracionVO campoSuplementario = new CampoSuplementarioModuloIntegracionVO();
                        String valorDNI = null;
                        String tipo_doc = "";

                        salida = el.getCampoSuplementarioExpediente(String.valueOf(codOrganizacion), String.valueOf(interopLlamadasServiciosNisae.getEjercicioHHFF()), vo.getNumeroExpediente(),
                                codigoProcFlexia,
                                "DNIPERCON", 2);
                        if (salida.getStatus() == 0) {
                            campoSuplementario = salida.getCampoSuplementario();
                            if (campoSuplementario != null && campoSuplementario.getValorTexto() != null) {
                                valorDNI = campoSuplementario.getValorTexto();
                            }
                        }


                        if (valorDNI != null && !valorDNI.isEmpty() && !valorDNI.equalsIgnoreCase("null")) {
                            tipo_doc = (validar(valorDNI) ? "DNI" : "NIE");
                        }

                        persona.setNumDocumento(valorDNI);
                        persona.setTipoDocumento(tipo_doc);
                        personas.add(persona);
                    }

                    datosEspecificos.setFechaDesde(interopLlamadasServiciosNisae.getFechaDesdeCVL());
                    datosEspecificos.setFechaHasta(interopLlamadasServiciosNisae.getFechaHastaCVL());

                    requestRestServiceCVL.setDatosEspecificos(datosEspecificos);
//                    requestRestServiceCVL.setPersona(persona);
                    requestRestServiceCVL.setTramitador(tramitador);

                    if(personas != null && personas.size() >0){
                        for (final Persona p : personas) {
                            requestRestServiceCVL.setPersona(p);
                            Gson gson = new Gson();
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            gson = gsonBuilder.create();
                            String JsonString = gson.toJson(requestRestServiceCVL);
                            m_Log.info("Texto LLamada : " + JsonString);

                            // Registramos la llamada en la tabla log para obtener nuestro secuncial
                            int idRegistroLog = gestionServiciosNISAE.crearRegistroBBDDLogLlamadaCVL(codOrganizacion, interopLlamadasServiciosNisae, idProcoFlexia_LAN6, requestRestServiceCVL, null, idPeticion, vo.getNumeroExpediente(), adaptador);

                            // Se añade información a la tabla INTEROP de VL Información de control
                            Date today = new Date();
                            String unidadTramitadora = gestionServiciosNISAE.rescatarUnidadTramitadora(vo.getNumeroExpediente(), adaptador);
                            m_Log.info("unidadTramitadora : " + unidadTramitadora);
                            gestionServiciosNISAE.crearVLInformacionControl(today, idRegistroLog, cifUsuarioLogueado, nombreUsuarioLogueado, unidadTramitadora, adaptador);

                            //Actualizamos estado de la tabla filtros exp. especificos
                            if (interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos() != null && !interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos().isEmpty() && interopLlamadasServiciosNisae.getEjecutarFiltroExpedientesEspecificos().equalsIgnoreCase("1")) {
                                gestionServiciosNISAE.actualizarEstadoExpedienteInListaFiltroExptsEspecificos(codOrganizacion, vo.getNumeroExpediente(), 1, "ID=" + idRegistroLog, adaptador);
                            }
                            //Asigamos al objeto expedientes por si falla que se muestre el id en el mail de errores
                            vo.setIdPeticionPreviaBBDDLog(idRegistroLog);
                            interopLlamadasServiciosNisae.setIdPeticionPadre(0);
                            interopLlamadasServiciosNisae.setId(idRegistroLog);
                            m_Log.info("ID Log Llamada " + idRegistroLog);

                            //Invocamos la creacion del cliente
                            String nombreModulo = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_MODULO, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                            String urlString = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                                    + ConstantesMeLanbideInterop.URL_SERVICIO_CVL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                            m_Log.error("URL: " + urlString);

                            String nameSpaceUri = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                                    + ConstantesMeLanbideInterop.QNAME_NAMESPACEURI_CVL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                            m_Log.error("NameSpaceUri: " + nameSpaceUri);

                            String localPart = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                                    + ConstantesMeLanbideInterop.QNAME_LOCALPART_CVL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                            m_Log.error("LocalPart: " + localPart);

                            String urlRestServiceStr = ConfigurationParameter.getParameter(codOrganizacion + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                                    + ConstantesMeLanbideInterop.MODULO_INTEGRACION + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                                    + nombreModulo + ConstantesMeLanbideInterop.BARRA_SEPARADORA
                                    + ConstantesMeLanbideInterop.URL_REST_SERVICE_CVL, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
                            m_Log.info("Url del Rest Service : " + urlRestServiceStr);

                            URL urlRestService = new URL(urlRestServiceStr);
                            HttpURLConnection conRS = (HttpURLConnection) urlRestService.openConnection();
                            conRS.setRequestMethod("POST");
                            // Formato de la peticion
                            conRS.setRequestProperty("Content-Type", "application/json");
                            // Set the ?Accept? request header to ?application/json? to read the response in the desired format
                            conRS.setRequestProperty("Accept", "application/json");
                            // Ensure the Connection Will Be Used to Send Content : //To send request content, let's enable the URLConnection object's doOutput property to true. Otherwise, we'll not be able to write content to the connection output stream:
                            conRS.setDoOutput(true);
                            conRS.setDoInput(true);

                            try {
                                OutputStream os = conRS.getOutputStream();
                                byte[] input = JsonString.getBytes("utf-8");
                                os.write(input, 0, input.length);
                            } catch (Exception ex) {
                                m_Log.error("Error preparando la llamada al WS .. " + ex.getMessage(), ex);
                                respuestaServicio = "-7 Error al preparar la llamada al WS... " + ex.getMessage();
                            }
                            m_Log.info("Hemos escrito la llamada .. Procedemos  recoger la respuesta ..");
                            ResponseRestServiceCVL responseRestServiceCVL = null;
                            try {
                                m_Log.info(" Respuesta : " + conRS.getResponseCode() + " - " + conRS.getResponseMessage());
                                BufferedReader br = new BufferedReader(new InputStreamReader(conRS.getInputStream(), "utf-8"));
                                StringBuilder responseJSON = new StringBuilder();
                                String responseLine = null;
                                while ((responseLine = br.readLine()) != null) {
                                    responseJSON.append(responseLine.trim());
                                }
                                m_Log.info("Despues de leer la respuesta... " + responseJSON);
                                responseRestServiceCVL = gson.fromJson(responseJSON.toString(), ResponseRestServiceCVL.class);

                                m_Log.info("Escribir Resultado en BBDD - campos suplementarios");
                                //Escribir los campos suplementarios
                                gestionServiciosNISAE.actualizarCamposSuplementariosNISAE_CVL(codOrganizacion, vo.getNumeroExpediente(), requestRestServiceCVL, responseRestServiceCVL, adaptador);

                            } catch (Exception ex) {
                                m_Log.error("Error preparando la respuesta al WS .. " + ex.getMessage(), ex);
                                respuestaServicio += "-8 Error al procesar la respuesta del WS... " + ex.getMessage();
                                if (responseRestServiceCVL == null) {
                                    responseRestServiceCVL = new ResponseRestServiceCVL();
                                }
                                responseRestServiceCVL.setCodRespuesta("ERROR");
                                responseRestServiceCVL.setDescRespuesta(ex.getMessage());
                                responseRestServiceCVL.setCodigoEstado("ERROR");
                                responseRestServiceCVL.setTextoEstado(ex.getMessage());
                            } finally {
                                m_Log.info("Escribir llamada en el m_Log INTEROP_SERVICIOS_NISAE_LOG");
                                //Escribir la fila en INTEROP_SERVICIOS_NISAE_LOG
                                gestionServiciosNISAE.actualizarRegistroBBDDLogLlamada(idRegistroLog, responseRestServiceCVL, adaptador);

                                // Se añade información a la tabla INTEROP de VL
                                if (!responseRestServiceCVL.getCodRespuesta().equals("ERROR")) {
                                    gestionServiciosNISAE.crearInteropVL(responseRestServiceCVL, requestRestServiceCVL, idRegistroLog, p.getNumDocumento(), p.getTipoDocumento(), vo.getNumeroExpediente(), adaptador);
                                }
                            }
                        }
                    }else{
                        m_Log.error("No hay personas para ejecutar la llamadas");
                    }
                }
            } else {
                m_Log.info("Lista de expedientes para tramitar recibida vacia.. ");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            m_Log.error("Error la ejecutando proceso en segundo plano " + ex.getMessage(), ex);
            String mensajeExcepcion = "";
            respuestaServicio = "Error al invocar el proceso en segundo plano : " + ex.getMessage();
            try {
                m_Log.info("Vamos a registrar el error en BD : " + ex.getMessage());
                String causaExcepcion = (ex.getCause() != null ? (ex.getCause().getMessage() != null ? ex.getCause().getMessage() + " - " + ex.getCause().toString() : ex.getCause().toString()) : "");
                mensajeExcepcion = ex.getMessage();
                String trazaError = ExceptionUtils.getStackTrace(ex);  // ExceptionUtils.getStackTrace(ex);

                Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                        trazaError, ConstantesMeLanbideInterop.ERROR_NISAE_SISTEMA_ORIGEN_FLEXIA,
                        ConstantesMeLanbideInterop.ERROR_NISAE_CODIGO_ERROR_CONSULTA_CVL, ConstantesMeLanbideInterop.ERROR_NISAE_MENSAJE_CONSULTA_CVL, 1);
                ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, ex);
                int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, interopLlamadasServiciosNisae.getNumeroExpedienteDesde() + "-" + interopLlamadasServiciosNisae.getNumeroExpedienteHasta(), idProcoFlexia_LAN6, interopLlamadasServiciosNisae.getEjercicioHHFF() + "-" + interopLlamadasServiciosNisae.getProcedimientoHHFF(), "consultaVidaLaboralCVLBatch");
                m_Log.info("Error Registrado en BD correctamente. " + idError);
                respuestaServicio += " - Mas detalles en el Gestor Errores : ID:" + idError;
            } catch (Exception ex1) {
                m_Log.error("consultaVidaLaboralCVLBatch. Flexia Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : " + ex.getMessage(), ex1);
                respuestaServicio += " - Error al intentar registrar y notificar un error en la ejecucion del proceso : " + ex1.getMessage();
            }
        } finally {
            try {
                m_Log.info("Fin Proceso Segundo Plano... ");
                comprobAdecAutThread.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
                m_Log.error("Error la finalizar/parar la ejecucion del Proceso " + ex.getMessage(), ex);
            }
        }
    }

    public boolean validar(String tipo_doc) {
        String letraMayuscula = "";

        if (tipo_doc.length() != 9 || Character.isLetter(tipo_doc.charAt(8)) == false) {
            return false;
        }

        letraMayuscula = (tipo_doc.substring(8)).toUpperCase();

        if (soloNumeros(tipo_doc) == true && letraDNI(tipo_doc).equals(letraMayuscula)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean soloNumeros(String tipo_doc) {

        int i, j = 0;
        String numero = "";
        String miDNI = "";
        String[] unoNueve = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (i = 0; i < tipo_doc.length() - 1; i++) {
            numero = tipo_doc.substring(i, i + 1);

            for (j = 0; j < unoNueve.length; j++) {
                if (numero.equals(unoNueve[j])) {
                    miDNI += unoNueve[j];
                }
            }
        }

        if (miDNI.length() != 8) {
            return false;
        } else {
            return true;
        }
    }

    private String letraDNI(String tipo_doc) {

        int miDNI = Integer.parseInt(tipo_doc.substring(0, 8));
        int resto = 0;
        String miLetra = "";
        String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        resto = miDNI % 23;

        miLetra = asignacionLetra[resto];

        return miLetra;
    }
}
