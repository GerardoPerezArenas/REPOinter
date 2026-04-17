/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

/**
 *
 * @author INGDGC
 */
public class InteropServiciosNisaeJsonUtils {
    
    private GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls();
    private Gson gson = gsonBuilder.create();
    private final Logger LOG = Logger.getLogger(this.getClass());
    
    public String getJSONfromObject(Object object){
        String respuesta="";
        try {
            respuesta = gson.toJson(object);
        } catch (Exception e) {
            LOG.error("Error procesando objeto TO json", e);
        }
        return respuesta;
    }
}
