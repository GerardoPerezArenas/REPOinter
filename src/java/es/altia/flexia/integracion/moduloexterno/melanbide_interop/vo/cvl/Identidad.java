/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl;

/**
 *
 * @author jaime.hermoso
 */
public class Identidad {
    
    private Integer numeroSituaciones;
    
    private Cabecera cabecera;
    
    private Resumen resumen;
    
    private ListaSituaciones listaSituaciones;

    /**
     * @return the numeroSituaciones
     */
    public Integer getNumeroSituaciones() {
        return numeroSituaciones;
    }

    /**
     * @param numeroSituaciones the numeroSituaciones to set
     */
    public void setNumeroSituaciones(Integer numeroSituaciones) {
        this.numeroSituaciones = numeroSituaciones;
    }

    /**
     * @return the cabecera
     */
    public Cabecera getCabecera() {
        return cabecera;
    }

    /**
     * @param cabecera the cabecera to set
     */
    public void setCabecera(Cabecera cabecera) {
        this.cabecera = cabecera;
    }

    /**
     * @return the resumen
     */
    public Resumen getResumen() {
        return resumen;
    }

    /**
     * @param resumen the resumen to set
     */
    public void setResumen(Resumen resumen) {
        this.resumen = resumen;
    }

    /**
     * @return the listaSituaciones
     */
    public ListaSituaciones getListaSituaciones() {
        return listaSituaciones;
    }

    /**
     * @param listaSituaciones the listaSituaciones to set
     */
    public void setListaSituaciones(ListaSituaciones listaSituaciones) {
        this.listaSituaciones = listaSituaciones;
    }
 
    @Override
    public String toString() {
        return "Identidad(" + "numeroSituaciones=" + numeroSituaciones + ", cabecera=" + cabecera + ", resumen=" + resumen + ", listaSituaciones=" + listaSituaciones + "}";
    }    
    
}
