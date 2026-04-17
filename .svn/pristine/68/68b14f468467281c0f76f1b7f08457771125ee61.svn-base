/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.xml.tratarrespuesta;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.MELANBIDE_INTEROP;
import java.io.StringReader;
import java.util.ResourceBundle;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;

/**
 *
 * @author INGDGC
 */
public class TratarRespuestasXMLWS {
    
    
    //Logger
    private static Logger log = Logger.getLogger(MELANBIDE_INTEROP.class);   
    ResourceBundle m_Conf = ResourceBundle.getBundle("common");
    
    public Respuestas parsearSalidaXMLWStoClase(String xmlString){ 
        //, String nombreCompletoClase
        Respuestas claseBaseRespuestas = new Respuestas();
        try{
            JAXBContext jaxbContext;
            jaxbContext = JAXBContext.newInstance(Respuestas.class);
            //Class.forName(nombreCompletoClase
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlString);
            claseBaseRespuestas = (Respuestas) unmarshaller.unmarshal(reader);
        }catch(Exception ex){
            log.error("parsearSalidaXMLWStoClase - Error al parsear el XML. " 
                     + xmlString + " / Clase Respuestas, WS Diputacion", ex);  //+ " / " + nombreCompletoClase
            claseBaseRespuestas.getRespuesta().getEstado().setCodigo("-1");
            claseBaseRespuestas.getRespuesta().getEstado().setCodigo("Error al parsear el XML de la salida del WS a la clase Base Respuestas.");
        }
        return claseBaseRespuestas;
    }
    
    public String obtenerStringRespuestaFromXMLWSDiputacion(String xmlRespuesta){
        StringBuilder textoPlanoRespuestaWS = new StringBuilder();
        if(xmlRespuesta!=null && xmlRespuesta!=""){
            Respuestas respuestaWS = parsearSalidaXMLWStoClase(xmlRespuesta);
            if (respuestaWS != null && respuestaWS.getRespuesta() != null) {
                textoPlanoRespuestaWS.append("Estado : ");
                textoPlanoRespuestaWS.append(respuestaWS.getRespuesta().getEstado().getCodigo());
                textoPlanoRespuestaWS.append(" - ");
                textoPlanoRespuestaWS.append(respuestaWS.getRespuesta().getEstado().getDescripcion());
                if (respuestaWS.getRespuesta().getTransmisiones() != null
                    && respuestaWS.getRespuesta().getTransmisiones().getTransmision() != null) {
                    textoPlanoRespuestaWS.append(" Respuesta : ");
                    textoPlanoRespuestaWS.append(respuestaWS.getRespuesta().getTransmisiones().getTransmision().getResultado());
                    textoPlanoRespuestaWS.append(" - ");
                    textoPlanoRespuestaWS.append(respuestaWS.getRespuesta().getTransmisiones().getTransmision().getDescripcion());
                }
            }
            
        }else{
            textoPlanoRespuestaWS.append("Respuesta del WS recibida a vacía o null");
        }
        return textoPlanoRespuestaWS.toString();
    }
}
