/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.altia.flexia.integracion.moduloexterno.melanbide_interop.ws.client.vidalaboralws.response;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.cvl.Identidad;

/**
 *
 * @author pablo.bugia
 */
public class Response {
    private String codRespuesta;
    private String descRespuesta;
    private String codigoEstado;
    private String textoEstado;
    private String xmlRespuesta;  
    private Identidad identidad;

    public Response(String codRespuesta, String descRespuesta, String codigoEstado, String textoEstado, String xmlRespuesta, Identidad identidad) {
        this.codRespuesta = codRespuesta;
        this.descRespuesta = descRespuesta;
        this.codigoEstado = codigoEstado;
        this.textoEstado = textoEstado;
        this.xmlRespuesta = xmlRespuesta;
        this.identidad = identidad;
    }

    public String getCodRespuesta() {
        return codRespuesta;
    }

    public void setCodRespuesta(String codRespuesta) {
        this.codRespuesta = codRespuesta;
    }

    public String getDescRespuesta() {
        return descRespuesta;
    }

    public void setDescRespuesta(String descRespuesta) {
        this.descRespuesta = descRespuesta;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getTextoEstado() {
        return textoEstado;
    }

    public void setTextoEstado(String textoEstado) {
        this.textoEstado = textoEstado;
    }

    public String getXmlRespuesta() {
        return xmlRespuesta;
    }

    public void setXmlRespuesta(String xmlRespuesta) {
        this.xmlRespuesta = xmlRespuesta;
    }

    public Identidad getIdentidad() {
        return identidad;
    }

    public void setIdentidad(Identidad identidad) {
        this.identidad = identidad;
    }

    @Override
    public String toString() {
        return "Response{" + "codRespuesta=" + codRespuesta + ", descRespuesta=" + descRespuesta + ", codigoEstado=" + codigoEstado + ", textoEstado=" + textoEstado + ", xmlRespuesta=" + xmlRespuesta + ", identidad=" + identidad + '}';
    }
}
