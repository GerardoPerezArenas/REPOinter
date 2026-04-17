/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores;

import com.lanbide.lan6.errores.bean.ErrorBean;
import com.lanbide.lan6.registro.error.RegistroErrores;
import org.apache.log4j.Logger;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConfigurationParameter;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;

/**
 *
 * @author davidg
 */
public class GestionarErroresDokusiNISAE {
    
    public static Logger _log = Logger.getLogger(GestionarErroresDokusiNISAE.class);

    public static int  grabarError(ErrorLan6ExcepcionBeanNISAE e, String numeroExpediente, String idProcFlexiaLan6, String idPeticionPrevia, String claseMetodoError) throws Exception {
        ErrorBean errorBean = new ErrorBean();
        int respuestaGrabarErrorInt=0;
        try {
            
            // Mensaje explicativo del error (Recogido de constantes)
            if (e.getMessages() != null && e.getMessages().size() > 0) {
                errorBean.setMensajeError(e.getMessages().get(0) + "");
            } else {
                errorBean.setMensajeError("");
            }
	
            // Mensaje lanzado por el error
            errorBean.setMensajeExcepError(e.getMensajeExcepcion()!=null? e.getMensajeExcepcion().replaceAll("'", "") : "MensajeExcepcion Recibido a Null");
            // Causa lanzada por el error
            errorBean.setCausa(e.getCausaExcepcion()!=null?e.getCausaExcepcion().replaceAll("'", ""):"CausaExcepcion Recibida a Null");
            // Traza lanzada por el error: limitar a 4000 caracteres
            String traza = e.getTrazaExcepcion()!=null?e.getTrazaExcepcion():"TrazaExcepcion Recibida a Null";
            errorBean.setTraza(limitarString(traza, 4000).replaceAll("'", ""));
            // Procedimiento donde se ha producido el error
            errorBean.setIdProcedimiento(idProcFlexiaLan6);
            // Evento que produce el error
            String evento = "";
            errorBean.setEvento(evento);
            // Codigo del error
            if (e.getCodes() != null && e.getCodes().size() > 0) {
                errorBean.setIdError(e.getCodes().get(0) + "");
            } else {
                errorBean.setIdError("");
            }
            // Identificador del expediente en Flexia
            errorBean.setIdFlexia(numeroExpediente);
            // Oid de solicitud
            errorBean.setIdClave(idPeticionPrevia);
            // Sistema que ha producido el error
            errorBean.setSistemaOrigen(e.getSistema()!=null?e.getSistema():"Sistema Origen recibido a Null");
            // Clase y metodo que graba el error
            errorBean.setSituacion(claseMetodoError);
            // Nombre del log donde se ha registrado
            String ficheroLog = ConfigurationParameter.getParameter(ConstantesMeLanbideInterop.NOMBRE_KEY_FICHEROLOG_LOG4J, ConstantesMeLanbideInterop.FICHERO_PROPIEDADES_LOG4J);
            errorBean.setErrorLog(ficheroLog);
            // Grabar error
            String respuestaGrabarError = RegistroErrores.registroError(errorBean);
            respuestaGrabarErrorInt= (respuestaGrabarError!=null && !respuestaGrabarError.isEmpty()) ? Integer.valueOf(respuestaGrabarError) : 0;
            _log.info("respuestaGrabarError RegistroErrores.registroError() " + respuestaGrabarError);
        } catch (Exception ex) {
            _log.error("GestionarErroresDokusiNISAE - Error al registrar en BD Errores presentados en ejecuciones jobs/batch ", ex);
            throw ex;
        }
        return respuestaGrabarErrorInt;
    }

    private static String limitarString(String trazaExcepcion, int limite) {
        String cadenaLimitada = "";
        if(trazaExcepcion!=null && !trazaExcepcion.isEmpty()){
            if(trazaExcepcion.length()>limite)
                cadenaLimitada = trazaExcepcion.substring(0, limite);
            else
                cadenaLimitada=trazaExcepcion;
        }
        return cadenaLimitada;
    }
    
    
    
}
