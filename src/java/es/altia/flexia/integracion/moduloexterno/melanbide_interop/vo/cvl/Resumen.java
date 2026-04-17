/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl;

/**
 *
 * @author jaime.hermoso
 */
public class Resumen {
    
    private Totales totales;

    private PluriEmpleo pluriempleo;            

    /**
     * @return the totales
     */
    public Totales getTotales() {
        return totales;
    }

    /**
     * @param totales the totales to set
     */
    public void setTotales(Totales totales) {
        this.totales = totales;
    }

    /**
     * @return the pluriempleo
     */
    public PluriEmpleo getPluriempleo() {
        return pluriempleo;
    }

    /**
     * @param pluriempleo the pluriempleo to set
     */
    public void setPluriempleo(PluriEmpleo pluriempleo) {
        this.pluriempleo = pluriempleo;
    }

    @Override
    public String toString() {
        return "Resumen{" + "totales=" + totales + ", pluriempleo=" + pluriempleo + '}';
    }    
    
}
