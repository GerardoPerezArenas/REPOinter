/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.services;

import es.altia.agora.business.registro.RegistroValueObject;
import es.altia.agora.technical.ConstantesDatos;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores.ErrorLan6ExcepcionBeanNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores.GestionarErroresDokusiNISAE;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.dao.MEInteropCargaTelemXMLServiceDAO;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.dto.MEInteropCargaTelemXMLExpediente;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.dto.MEInteropCargaTelemXMLParameters;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;
import es.altia.flexia.integracion.moduloexterno.plugin.ModuloIntegracionExterno;
import es.altia.flexia.integracion.moduloexterno.plugin.ModuloIntegracionExternoFactoria;
import es.altia.util.conexion.AdaptadorSQLBD;
import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6ErrorBean;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author INGDGC
 */
public class MEInteropCargaTelemXMLService implements Runnable{
    
    Logger m_Log = Logger.getLogger(this.getClass());
    private final SimpleDateFormat formatFechaLog = new SimpleDateFormat("yyyyMMddHHmmssSSS");


    public MEInteropCargaTelemXMLService() {
        m_Log = Logger.getLogger(this.getClass());
    }

    private Thread comprobAdecAutThread = null;
    AdaptadorSQLBD adaptador = null;
    Integer codOrganizacion = null;
    MEInteropCargaTelemXMLParameters mEInteropCargaTelemXMLParameters;
    List<MEInteropCargaTelemXMLExpediente> listExpedientes;
    private final MEInteropCargaTelemXMLServiceDAO mEInteropCargaTelemXMLServiceDAO = new MEInteropCargaTelemXMLServiceDAO();
    
    public void start(Integer _codOrganizacion,MEInteropCargaTelemXMLParameters _mEInteropCargaTelemXMLParameters, List<MEInteropCargaTelemXMLExpediente> _listExpedientes,AdaptadorSQLBD adapt) {
        comprobAdecAutThread = new Thread(this, "GestionServiciosCargaTeleDatosMEFromXML");
        adaptador = adapt;
        codOrganizacion=_codOrganizacion;
        mEInteropCargaTelemXMLParameters=(_mEInteropCargaTelemXMLParameters!=null ? _mEInteropCargaTelemXMLParameters : new MEInteropCargaTelemXMLParameters());
        listExpedientes=(_listExpedientes!=null ? _listExpedientes : new ArrayList<MEInteropCargaTelemXMLExpediente>());
        comprobAdecAutThread.start();
    }

    @Override
    public void run() {
        m_Log.info("Inicio Proceso Segundo Plano... " + formatFechaLog.format(new Date()));
        int i = 0;
        String resultadoProceso = "OK";
        String mensajeErrorProceso = "";
        String respuestaServicio = "";
        
        try {

            String tipo_doc = "";
            String cifUsuarioLogueado = "";
            String nombreUsuarioLogueado = "";
            
            if (listExpedientes != null && listExpedientes.size() > 0) {
                for (MEInteropCargaTelemXMLExpediente listExpediente : listExpedientes) {
                    m_Log.info("-- Expediente Tratado : " + listExpediente.getExpedienteNumero()+ " - Id registro: " + listExpediente.getRegistroEjercicio()+ "/" + listExpediente.getRegistroNumero());
                    try {
                        RegistroValueObject documento = new RegistroValueObject();
                        documento.setNombreDoc(listExpediente.getDocumentoRegistroNombre());
                        documento.setDoc(listExpediente.getDocumentoRegistroContenido());
                        String nombreModulo = leerAtributoFicheroXML(documento,ConstantesDatos.TAG_XML_FLX_EXTENSION,ConstantesDatos.TAG_XML_CODIGOMODULO,ConstantesDatos.ATRIBUTO_XML_COD);
                        if (nombreModulo.length() > 0) {
                            ModuloIntegracionExternoFactoria factoria = ModuloIntegracionExternoFactoria.getInstance();
                            ModuloIntegracionExterno modulo = factoria.getImplClass(codOrganizacion, nombreModulo);
                            if (modulo != null) {
                                String value = new String(documento.getDoc(), "ISO-8859-1");
                                try {
                                    modulo.cargarExpedienteExtension(codOrganizacion, listExpediente.getExpedienteNumero(), value);
                                } catch (Exception e) {
                                    m_Log.error(" Error al ejecutar la operacion cargarExpedienteExtension del módulo " + modulo.getNombreModulo() + ": " + e.getMessage());
                                    e.printStackTrace();
                                    respuestaServicio=" Error al ejecutar la operacion cargarExpedienteExtension del módulo " + modulo.getNombreModulo() + ": " + e.getMessage();
                                }
                            } else {
                                respuestaServicio="Error al cargar los datos de extension. Datos no recueperados desde XML.";
                            }
                        }
                    } catch (Exception ex) {
                        m_Log.error(" Error de tipo Lan6Excepcion : " + ex.getMessage(),ex);
                        try {
                            String mensajeExcepcion = (ex.getMessage() != null ? ex.getMessage() : "-Mensaje de error no recibido en la peticion-");
                            m_Log.info("Registrar el error en BD : " + mensajeExcepcion);
                            String causaExcepcion = (ex.getCause() != null ? (ex.getCause().getMessage() != null ? ex.getCause().getMessage() + " - " + ex.getCause().toString() : ex.getCause().toString()) : "");
                            String trazaError = (ex.getStackTrace()!=null? Arrays.toString(ex.getStackTrace()):"");  

                            Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                                    trazaError, "FLEXIA",
                                    "TELEMATICO_01", "Error carga datos ME telematicamente desde XML", 1);
                            ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, ex);
                            int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, listExpediente.getExpedienteNumero(), "", listExpediente.getRegistroEjercicio()+ "/" +listExpediente.getRegistroNumero(), "MEInteropCargaTelemXMLService");
                            m_Log.info("Error Registrado en BD correctamente. " + idError);
                        } catch (Exception ex1) {
                            m_Log.error("Proceso Segundo plano carga XML. Flexia Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : " + ex.getMessage(), ex1);
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
                String trazaError = (ex.getStackTrace()!=null? Arrays.toString(ex.getStackTrace()):"");  

                Lan6ErrorBean errorBean = new Lan6ErrorBean(causaExcepcion, mensajeExcepcion,
                        trazaError, ConstantesMeLanbideInterop.ERROR_NISAE_SISTEMA_ORIGEN_FLEXIA,
                        "TELEMATICO_01", "Error carga datos ME telematicamente desde XML", 1);
                ErrorLan6ExcepcionBeanNISAE errorLan6Bean = new ErrorLan6ExcepcionBeanNISAE(errorBean, ex);
                int idError = GestionarErroresDokusiNISAE.grabarError(errorLan6Bean, mEInteropCargaTelemXMLParameters.getNumeroExpediente() + "-" + mEInteropCargaTelemXMLParameters.getNumeroExpedienteDesde() + "-" + mEInteropCargaTelemXMLParameters.getNumeroExpedienteHasta(), "-", mEInteropCargaTelemXMLParameters.getEjercicio() + "-" + mEInteropCargaTelemXMLParameters.getProcedimiento(), "cargarDatosMEFromXMlEntradaRegistroTelematica");
                m_Log.info("Error Registrado en BD correctamente. " + idError);
                respuestaServicio += " - Mas detalles en el Gestor Errores : ID:" + idError;
            } catch (Exception ex1) {
                m_Log.error("Carga XML Telematico - Proceso Segundo Plano . Flexia Error al registrar errores mediante servicios de Adaptadores de platea. Error que se intenta Registrar : " + ex.getMessage(), ex1);
                respuestaServicio += " - Error al intentar registrar y notificar un error en la ejecucion del proceso : " + ex1.getMessage();
            }
        } finally {
            m_Log.info("Fin Proceso Segundo Plano... " + formatFechaLog.format(new Date()));
        }
        comprobAdecAutThread.stop();
    }
    
    /**
     * Método auxiliar que lee el valor de un tag de un fichero xml
     *
     * @param documento Objeto que contiene el documento
     * @param padre Tag padre
     * @param hijo Tag hijo
     * @param atributo Atributo a leer
     * @return Valor del atributo
     */
    private String leerAtributoFicheroXML(RegistroValueObject documento, String padre, String hijo, String nomAtributo) {
        m_Log.info("leerAtributoFicheroXML(): Inicio lectura tags fichero " + documento.getNombreDoc() + " - nomAtributo : " + nomAtributo);
        String atributo = "";
        try {
            byte[] ficheroBytes = documento.getDoc();
            String fichero = new String(ficheroBytes, ConstantesDatos.CODIFICACION_ISO_8859_1);
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new StringReader(fichero));
            Element nodoRaiz = doc.getRootElement();
            if (nodoRaiz != null) {
                Element nodoPadre = nodoRaiz.getChild(padre);
                if (nodoPadre != null) {
                    Element nodoHijo = nodoPadre.getChild(hijo);
                    if (nodoHijo != null) {
                        atributo = nodoHijo.getAttributeValue(nomAtributo);
                    }
                }
            }
        } catch (IOException io) {
            atributo = null;
            m_Log.error("leerAtributoFicheroXML(): Error al procesar el fichero XML. IOExecption: " + io.getMessage());
        } catch (JDOMException jdo) {
            atributo = null;
            m_Log.error("leerAtributoFicheroXML(): Error al procesar el fichero XML. JDOM Exception: " + jdo.getMessage());
        } catch (Exception e) {
            atributo = null;
            m_Log.error("leerAtributoFicheroXML(): Error al procesar el fichero XML. Exception: " + e.getMessage());
        }
        m_Log.debug("leerAtributoFicheroXML(): Fin lectura tags fichero " + documento.getNombreDoc() + " - Atributo : " + atributo);
        return atributo;
    }
}
