/**
 * LangaiDem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.LangaiDem_pkg;

import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.acceso.AccesoValueObject;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.demanda.FormacionesComplementariasValueObject;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.demanda.FormacionesOcupacionalesValueObject;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.demanda.ServiciosValueObject;
import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.langaideman.es.altia.langai.business.demanda.SituAdminValueObject;

public interface LangaiDem extends java.rmi.Remote {
    public ServiciosValueObject consultaServicios(AccesoValueObject accesoValueObject, ServiciosValueObject serviciosValueObject, java.lang.String idioma) throws java.rmi.RemoteException;
    public ServiciosValueObject modificaServicios(AccesoValueObject accesoValueObject, ServiciosValueObject serviciosValueObject, java.lang.String idioma) throws java.rmi.RemoteException;
    public ServiciosValueObject altaServicio(AccesoValueObject accesoValueObject, ServiciosValueObject serviciosValueObject, java.lang.String idioma) throws java.rmi.RemoteException;
    public ServiciosValueObject bajaServicio(AccesoValueObject accesoValueObject, ServiciosValueObject serviciosValueObject, java.lang.String idioma) throws java.rmi.RemoteException;
    public FormacionesOcupacionalesValueObject consultaForOcup(AccesoValueObject accesoValueObject, FormacionesOcupacionalesValueObject formacionesOcupacionalesValueObject, java.lang.String idioma) throws java.rmi.RemoteException;
    public FormacionesOcupacionalesValueObject modificaForOcup(AccesoValueObject accesoValueObject, FormacionesOcupacionalesValueObject formacionesOcupacionalesValueObject, java.lang.String idioma) throws java.rmi.RemoteException;
    public FormacionesComplementariasValueObject consultaForCompl(AccesoValueObject accesoValueObject, FormacionesComplementariasValueObject formacionesComplementariasValueObject, java.lang.String idioma) throws java.rmi.RemoteException;
    public FormacionesComplementariasValueObject modificaForCompl(AccesoValueObject accesoValueObject, FormacionesComplementariasValueObject formacionesComplementariasValueObject, java.lang.String idioma) throws java.rmi.RemoteException;
    public SituAdminValueObject renovarDem(AccesoValueObject accesoValueObject, java.lang.String tipo_doc, java.lang.String num_doc, java.lang.String f_real_renov, java.lang.String medio, java.lang.String idioma) throws java.rmi.RemoteException;
    public java.lang.String renovarDemNet(java.lang.String tipo_doc, java.lang.String num_doc, java.lang.String pass_dem, java.lang.String f_real_renov, java.lang.String idioma) throws java.rmi.RemoteException;
    public java.lang.String renovarDemCentriphone(java.lang.String gen_per_corr, java.lang.String idioma) throws java.rmi.RemoteException;
    public java.lang.String cambioSitAdmAltaNet(java.lang.String tipo_doc, java.lang.String num_doc, java.lang.String pass_dem, java.lang.String sit_lab, java.lang.String idioma) throws java.rmi.RemoteException;
    public int existeDemanda(java.lang.String cod_centro_usu, java.lang.String cod_ubic_usu, java.lang.String num_doc, java.lang.String tipo_doc) throws java.rmi.RemoteException;
    public java.lang.String[] consultaDemanda(java.lang.String cod_centro_usu, java.lang.String cod_ubic_usu, java.lang.String num_doc, java.lang.String tipo_doc, java.lang.String idioma) throws java.rmi.RemoteException;
}
