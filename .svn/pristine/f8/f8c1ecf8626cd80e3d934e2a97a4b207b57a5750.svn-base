/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.cliente.ejemplo;

//import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.getdatosidentidad.pro.X53JsGetDatosIdentidadWSDelegate;
//import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.getdatosidentidad.pro.X53JsGetDatosIdentidadWSPortBindingQSService;


//import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.cliente.GetDatosIdentidadWS.X53JsGetDatosIdentidadWSPortBindingQSService;
//import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.cliente.GetDatosIdentidadWS.X53JsGetDatosIdentidadWSPortBindingQSService_Impl;

//import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.cliente.GetDatosIdentidadWS.X53JsGetDatosIdentidadWSDelegate_Stub;


//import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.cliente.GetDatosIdentidadWS.X53JsGetDatosIdentidadWSDelegate;
//import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.cliente.GetDatosIdentidadWS.X53JsGetDatosIdentidadWSDelegate;
//import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservice.cliente.GetDatosIdentidadWS.X53JsGetDatosIdentidadWSPortBindingQSService_Impl;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import org.apache.log4j.Logger;


//import requirentews.X53JsGetDatosIdentidadWSDelegate;
//import requirentews.X53JsGetDatosIdentidadWSPortBindingQSService;
//import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservices.clientes.getDatosIdentidad.impl.jaxws.X53JsGetDatosIdentidadWSDelegate;
//import es.altia.flexia.integracion.moduloexterno.melanbide_interop.webservices.clientes.getDatosIdentidad.impl.jaxws.X53JsGetDatosIdentidadWSPortBindingQSService;

/**
 *
 * @author davidg
 */
public class InvokeServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(InvokeServlet.class);   
    /**
	 * Constructor of the object.
	 */
	public InvokeServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
        
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            String resultado = "";

            try {
                 resultado=getDatosIdentidad();

                   response.setContentType("text/html");
                   PrintWriter out = response.getWriter();

                out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
                out.println("<HTML>");
                out.println("  <HEAD><TITLE>Resultado</TITLE></HEAD>");
                out.println("  <BODY>");
                out.println("  <P>Salida</P>");
                out.println("<P>" + resultado + "</P>");
                out.println("<br /><br /><hr />");
                out.println("  <a href=\"../index.jsp\">Volver</a>");
                out.println("  </BODY>");
                out.println("</HTML>");

                out.flush();
                out.close();


            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
             }

                      }

                /**
                 * Initialization of the servlet. <br>
                 *
                 * @throws ServletException if an error occure
                 */
                public void init() throws ServletException {
                        // Put your code here
                }


                /**Llamada test servicio
                 * @throws IOException 
                 * @throws Exception **/

                private String getDatosIdentidad() throws IOException ,Exception{

                String organoSolicitante    = "Eusko Jaurlaritza - Ejie Desarrollo";	 
                String unidadTramitadora    = "Departamento de Educación";
                String codigoProcedimiento  = "SVDI_000233";
                String nombreProcedimiento  = "Solicitud y matriculación del alumnado";
                String finalidad            = "Solicitud y matriculación del alumnado";
                String consentimiento = "Si";
                String nifTramitador = "12345678Z";
                String nombreTramitador = "JUAN JOSE PEREZ ARTEAGA";
                String idExpediente         = "R02K0000000000000001";
                String tipoTramite          = "CONSULTA";
                String tipoDocumento        = "DNI";
                String numDocumento         = "10000322Z";
                String nombreTitular        = "MANUELA";
                String apellido1Titular     = "BLANCO";
                String apellido2Titular     = "VIDAL";
                String numSoporte          = "";
                String Sexo                = "M";
                String FechaNacimiento	     = "20020905";
                String ProvinciaNacimiento  = "PONTEVEDRA";
                String PaisNacimiento 	    = "ESPAÑA";
                String ProvinciaResidencia  = "LEON";
                String PaisResidencia       = "ESPAÑA";

            String result = "no";	
            String wsdlUrl = "http://svc.extra.integracion.jakina.ejiedes.net/ctxapp/x53jsGetDatosIdentidadWS?WSDL"; 
            String nameSpaceUri = "http://requirenteWS/";
            String localPart ="";
            QName qName = new QName(nameSpaceUri,localPart );

            //X53JsGetDatosIdentidadWSPortBindingQSService_Impl service = new X53JsGetDatosIdentidadWSPortBindingQSService_Impl();
            
            //X53JsGetDatosIdentidadWSPortBindingQSService service = new X53JsGetDatosIdentidadWSPortBindingQSService();
                        
            //X53JsGetDatosIdentidadWSPortBindingQSService service = null;
                try {
                        //service = new X53JsGetDatosIdentidadWSPortBindingQSService_Impl();
                        //service = new X53JsGetDatosIdentidadWSPortBindingQSService();
                        
                        //X53JsGetDatosIdentidadWSDelegate port = service.getX53jsGetDatosIdentidadWSDelegate(wsdlUrl,qName.toString());
              /*
                    X53JsGetDatosIdentidadWSDelegate port = service.getX53JsGetDatosIdentidadWSPortBindingQSPort();
                        result = port.getDatosIdentidadWS(organoSolicitante,
                                           unidadTramitadora, 
                                           codigoProcedimiento, 
                                           nombreProcedimiento,
                                           finalidad,
                                           consentimiento,
                                           nifTramitador,
                                           nombreTramitador, 
                                           idExpediente, 
                                           tipoTramite, 
                                           tipoDocumento, 
                                           numDocumento, 
                                           nombreTitular,
                                           apellido1Titular,     
                                           apellido2Titular,
                                           numSoporte,
                                           Sexo,
                                           FechaNacimiento,
                                           ProvinciaNacimiento,
                                           PaisNacimiento,
                                           ProvinciaResidencia,
                                           PaisResidencia);
                    */


                }/*catch(ServiceException e) {
                   e.printStackTrace();
                } catch(RemoteException e) {
                    e.printStackTrace();
                }*/
                catch(Exception e){
                    e.printStackTrace();
                    log.error(e.getMessage() + "  -  " + e.getStackTrace(), e);
                }

            return result;
        }
    
}
