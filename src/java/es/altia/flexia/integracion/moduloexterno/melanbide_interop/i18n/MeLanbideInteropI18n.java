/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.i18n;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.util.ConstantesMeLanbideInterop;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 *
 * @author davidg
 */
public class MeLanbideInteropI18n {
    
    
    private static HashMap<Integer,ResourceBundle> arrayBundleMensajes = new HashMap<Integer, ResourceBundle>();
    private static ResourceBundle bundlePropiedades = ResourceBundle.getBundle(ConstantesMeLanbideInterop.FICHERO_PROPIEDADES);
    private static MeLanbideInteropI18n instance = null;
    
    public static String LATIN_DATE_FORMAT = "dd/MM/yyyy";
    public static String DESC_DATE_FORMAT_SLASH = "yyyy/MM/dd";
    
    
    
     /**
     * Constructor privado
     * Recupera los ficheros de propiedades del mÃ³dulo y los carga para que solo se lean una vez.
     */
    private MeLanbideInteropI18n()
    {
        //Recuperamos del bundle de propiedades la propiedad que indica los archivos de mensajes de que dispone el moddulo.
        String archivos = bundlePropiedades.getString("FICHEROS_MENSAJES");
        String ubicacion = bundlePropiedades.getString("UBICACION_FICHEROS_MENSAJES");
        if(archivos != null && !archivos.equalsIgnoreCase(""))
        {
            String[]arrayArchivos = archivos.split(";");
            for(int x=0; x<arrayArchivos.length; x++)
            {
                try
                {
                    ResourceBundle bundle = ResourceBundle.getBundle(ubicacion + "." + arrayArchivos[x]);
                    if(bundle != null)
                    {
                        char cod = arrayArchivos[x].charAt(arrayArchivos[x].length()-1);
                        //Le restamos 48 por que es el valor de 0 en ascii por que el char es el codigo ascii del valor.
                        Integer codIdioma = Integer.valueOf(Integer.valueOf(cod) - 48);
                        arrayBundleMensajes.put(codIdioma, bundle);
                    }
                }
                catch(Exception ex)
                {
                    
                }
            }
        }
    }
    
     /**
     * Devuelve el valor de la clave de los ficheros de properties de mensajes que estÃ¡n cargados por defecto.
     * Si no existe devuelve un string vacÃ­o.
     * @param codIdioma
     * @param key
     * @return String
     */
    public String getMensaje(Integer codIdioma, String key)
    {
        String mensaje ="";
        if(arrayBundleMensajes != null)
        {
            try
            {
                ResourceBundle bundle = arrayBundleMensajes.get(codIdioma);
                mensaje = bundle.getString(key);
            }
            catch(Exception ex)
            {
                
            }
        }
        return mensaje;
    }   
    
    public static MeLanbideInteropI18n getInstance()
    {
        if(instance == null)
        {
            synchronized(MeLanbideInteropI18n.class)
            {
                if(instance == null)
                {
                    instance = new MeLanbideInteropI18n();
                }
            }
        }
        return instance;
    }

    
    
}
