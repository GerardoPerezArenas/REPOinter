/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.telematico.dto;

import java.util.Date;

/**
 *
 * @author INGDGC
 */
public class MEInteropCargaTelemXMLExpediente {
    
    private String expedienteNumero;
    private int registroDepartamento;
    private int registroUor;
    private String registroTipo;
    private Integer registroEjercicio;
    private Integer registroNumero;
    private int registroExrTop;
    private String documentoRegistroNombre;
    private Date documentoRegistroFecha;
    private String documentoRegistroMimeType;
    private String documentoRegistroOid;
    private byte[] documentoRegistroContenido;

    public String getExpedienteNumero() {
        return expedienteNumero;
    }

    public void setExpedienteNumero(String expedienteNumero) {
        this.expedienteNumero = expedienteNumero;
    }

    public int getRegistroDepartamento() {
        return registroDepartamento;
    }

    public void setRegistroDepartamento(int registroDepartamento) {
        this.registroDepartamento = registroDepartamento;
    }

    public int getRegistroUor() {
        return registroUor;
    }

    public void setRegistroUor(int registroUor) {
        this.registroUor = registroUor;
    }

    public String getRegistroTipo() {
        return registroTipo;
    }

    public void setRegistroTipo(String registroTipo) {
        this.registroTipo = registroTipo;
    }

    public Integer getRegistroEjercicio() {
        return registroEjercicio;
    }

    public void setRegistroEjercicio(Integer registroEjercicio) {
        this.registroEjercicio = registroEjercicio;
    }

    public Integer getRegistroNumero() {
        return registroNumero;
    }

    public void setRegistroNumero(Integer registroNumero) {
        this.registroNumero = registroNumero;
    }

    public int getRegistroExrTop() {
        return registroExrTop;
    }

    public void setRegistroExrTop(int registroExrTop) {
        this.registroExrTop = registroExrTop;
    }

    public String getDocumentoRegistroNombre() {
        return documentoRegistroNombre;
    }

    public void setDocumentoRegistroNombre(String documentoRegistroNombre) {
        this.documentoRegistroNombre = documentoRegistroNombre;
    }

    public Date getDocumentoRegistroFecha() {
        return documentoRegistroFecha;
    }

    public void setDocumentoRegistroFecha(Date documentoRegistroFecha) {
        this.documentoRegistroFecha = documentoRegistroFecha;
    }

    public String getDocumentoRegistroMimeType() {
        return documentoRegistroMimeType;
    }

    public void setDocumentoRegistroMimeType(String documentoRegistroMimeType) {
        this.documentoRegistroMimeType = documentoRegistroMimeType;
    }

    public String getDocumentoRegistroOid() {
        return documentoRegistroOid;
    }

    public void setDocumentoRegistroOid(String documentoRegistroOid) {
        this.documentoRegistroOid = documentoRegistroOid;
    }

    public byte[] getDocumentoRegistroContenido() {
        return documentoRegistroContenido;
    }

    public void setDocumentoRegistroContenido(byte[] documentoRegistroContenido) {
        this.documentoRegistroContenido = documentoRegistroContenido;
    }

    @Override
    public String toString() {
        return "MEInteropCargaTelemXMLExpediente{" + "expedienteNumero=" + expedienteNumero + ", registroDepartamento=" + registroDepartamento + ", registroUor=" + registroUor + ", registroTipo=" + registroTipo + ", registroEjercicio=" + registroEjercicio + ", registroNumero=" + registroNumero + ", registroExrTop=" + registroExrTop + ", documentoRegistroNombre=" + documentoRegistroNombre + ", documentoRegistroFecha=" + documentoRegistroFecha + ", documentoRegistroMimeType=" + documentoRegistroMimeType + ", documentoRegistroOid=" + documentoRegistroOid + ", documentoRegistroContenido=" + documentoRegistroContenido + '}';
    }
    
}
