/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl;

import java.util.List;

/**
 *
 * @author jaime.hermoso
 */
public class ListaSituaciones {
    
    private List<Situacion> situacion;

    /**
     * @return the situacion
     */
    public List<Situacion> getSituacion() {
        return situacion;
    }

    /**
     * @param situacion the situacion to set
     */
    public void setSituacion(List<Situacion> situacion) {
        this.situacion = situacion;
    } 
    
    @Override
    public String toString() {
        return "ListaSituaciones{" + "situacion=" + situacion + '}';
    }    
    
}
