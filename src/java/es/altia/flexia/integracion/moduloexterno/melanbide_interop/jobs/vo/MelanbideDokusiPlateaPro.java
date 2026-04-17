/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo;

import java.util.Date;

/**
 *
 * @author INGDGC
 */
public class MelanbideDokusiPlateaPro {
    
    private int id;
    private String codigoProcedimientoFlexia;
    private String codigoProcedimientoPlatea;
    private Date fecha_creacion;

    public MelanbideDokusiPlateaPro() {
    }

    public MelanbideDokusiPlateaPro(int id, String codigoProcedimientoFlexia, String codigoProcedimientoPlatea, Date fecha_creacion) {
        this.id = id;
        this.codigoProcedimientoFlexia = codigoProcedimientoFlexia;
        this.codigoProcedimientoPlatea = codigoProcedimientoPlatea;
        this.fecha_creacion = fecha_creacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoProcedimientoFlexia() {
        return codigoProcedimientoFlexia;
    }

    public void setCodigoProcedimientoFlexia(String codigoProcedimientoFlexia) {
        this.codigoProcedimientoFlexia = codigoProcedimientoFlexia;
    }

    public String getCodigoProcedimientoPlatea() {
        return codigoProcedimientoPlatea;
    }

    public void setCodigoProcedimientoPlatea(String codigoProcedimientoPlatea) {
        this.codigoProcedimientoPlatea = codigoProcedimientoPlatea;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    @Override
    public String toString() {
        return "MelanbideDokusiPlateaPro{" + "id=" + id + ", codigoProcedimientoFlexia=" + codigoProcedimientoFlexia + ", codigoProcedimientoPlatea=" + codigoProcedimientoPlatea + ", fecha_creacion=" + fecha_creacion + '}';
    }
    
}
