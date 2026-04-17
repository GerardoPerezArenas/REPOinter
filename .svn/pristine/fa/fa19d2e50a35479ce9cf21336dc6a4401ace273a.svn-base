/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.util;

import org.apache.log4j.Logger;

/**
 *
 * @author INGDGC
 */
public class MeLanbideInteropGeneralUtils {
    
    private static final Logger log = Logger.getLogger(MeLanbideInteropGeneralUtils.class.getName());
    
    public static String getCodProcedimientoFromNumExpediente(String numExpediente){
        String returnValue = "";
        log.debug("getCodProcedimientoFromNumExpediente - Begin()" + numExpediente);
        if(numExpediente!=null && numExpediente!=""){
            try {
                String[] arreglo = numExpediente.split(ConstantesMeLanbideInterop.BARRA_SEPARADORA);
                log.debug("Tama�o arreglo obtenido del Num. Expediente :" + arreglo.length);
                returnValue=arreglo[1];
            } catch (Exception e) {
                log.error("Error al obtener el codgo de procedimiento desde el numero de expediente, retornamos null",e);
                returnValue=null;
            }
        }
        log.debug("getCodProcedimientoFromNumExpediente - End()" + returnValue);
        return returnValue;
    }
    
    public static String parsearTipoDocFlexiaToTipoDocEJIE_WS(String tipoDocFlexia){
        String tipoDocWSEJIE ="";
        if(tipoDocFlexia!=null){
            if("1".equalsIgnoreCase(tipoDocFlexia))
                tipoDocWSEJIE="DNI";
            else if("2".equalsIgnoreCase(tipoDocFlexia))
                tipoDocWSEJIE="Pasaporte";
            else if("3".equalsIgnoreCase(tipoDocFlexia))
                tipoDocWSEJIE="NIE";
            else if("4".equalsIgnoreCase(tipoDocFlexia))
                tipoDocWSEJIE="CIF";
        }
        return tipoDocWSEJIE;
    }
    
    public static String parsearTipoDocFlexiaToTipoDocEIKA_WS(String tipoDocFlexia){
        String tipoDocWSEJIE ="";
        if(tipoDocFlexia!=null){
            if("1".equalsIgnoreCase(tipoDocFlexia)
                    || "3".equalsIgnoreCase(tipoDocFlexia)
                    || "4".equalsIgnoreCase(tipoDocFlexia))
                tipoDocWSEJIE="ES1";
            else 
                tipoDocWSEJIE="PAS";
        }
        return tipoDocWSEJIE;
    }

    public static int getEjercicioFromNumExpediente(String numExpediente){
        int returnValue = 0;
        log.info("getCodProcedimientoFromNumExpediente - Begin()" + numExpediente);
        if(numExpediente!=null && numExpediente!=""){
            try {
                String[] arreglo = numExpediente.split(ConstantesMeLanbideInterop.BARRA_SEPARADORA);
                log.debug("Tamano arreglo obtenido del Num. Expediente :" + arreglo.length);
                returnValue= Integer.parseInt(arreglo[0]);
            } catch (Exception e) {
                log.error("Error al obtener el ejercicio desde el numero de expediente, retornamos 0",e);
            }
        }
        log.info("getCodProcedimientoFromNumExpediente - End()" + returnValue);
        return returnValue;
    }
    
}
