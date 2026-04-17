/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.gestorerrores;

import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6ErrorBean;
import es.lanbide.lan6.adaptadoresPlatea.excepciones.Lan6Excepcion;

/**
 *
 * @author davidg
 */
public class ErrorLan6ExcepcionBeanNISAE extends Lan6Excepcion{
    
    private static final String CLASS_NAME = "ErrorLan6ExcepcionBean";

    public ErrorLan6ExcepcionBeanNISAE(String codigo, String mensaje) {
        super(codigo, mensaje);
    }
    
    public ErrorLan6ExcepcionBeanNISAE( Lan6ErrorBean bean, Throwable e) {
        super (ErrorLan6ExcepcionBeanNISAE.CLASS_NAME,null, bean, e);
    }
    
}
