/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.jobs.vo;

import java.sql.Clob;
import java.text.SimpleDateFormat;

/**
 *
 * @author INGDGC
 */
public class InteropJobsAKSSGAProceso {
    
    private final SimpleDateFormat formatFechaddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
   
    private long id; // ID NOT NULL NUMBER(12) 
    private long fechaCarga; // FECHACARGA NOT NULL NUMBER(8)  *formato yyyymmdd
    private Clob xmlSga; // XMLSGA     NOT NULL CLOB

    public InteropJobsAKSSGAProceso(long id, long fechaCarga, Clob xmlSga) {
        this.id = id;
        this.fechaCarga = fechaCarga;
        this.xmlSga = xmlSga;
    }

    public InteropJobsAKSSGAProceso() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(long fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Clob getXmlSga() {
        return xmlSga;
    }

    public void setXmlSga(Clob xmlSga) {
        this.xmlSga = xmlSga;
    }

    @Override
    public String toString() {
        return "InteropJobsAKSSGAProceso{" + "formatFechaddMMyyyy=" + formatFechaddMMyyyy + ", id=" + id + ", fechaCarga=" + fechaCarga + ", xmlSga=" + xmlSga + '}';
    }
    
}
