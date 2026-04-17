/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo;

/**
 *
 * @author davidg
 */
public class URL_WebServiceVO {
    
    public URL_WebServiceVO(){
        }
  
    private String entornoDespliege;          //URLWS_ENTORNO
    private String codigoWebService;        //URLWS_CODWS
    private String nombreWebService;            //URLWS_NOMBREWS 
    private String urlWebService;         //URLWS_URLWS 
    
    public void setEntornoDespliege(String entornoDespliege) {
        this.entornoDespliege = entornoDespliege;
    }
    
    public String getEntornoDespliege() {
        return entornoDespliege;
    }
    
    public void setCodigoWebService(String codigoWebService) {
        this.codigoWebService = codigoWebService;
    }
    
    public String getCodigoWebService() {
        return codigoWebService;
    }
    
    public void setNombreWebService(String nombreWebService) {
        this.nombreWebService = nombreWebService;
    }
    
    public String getNombreWebService() {
        return nombreWebService;
    }
    
    public void setUrlWebService(String urlWebService) {
        this.urlWebService = urlWebService;
    }
    
    public String getUrlWebService() {
        return urlWebService;
    }
}
