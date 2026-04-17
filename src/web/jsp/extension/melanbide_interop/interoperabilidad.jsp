<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>


<%@page import="es.altia.flexia.integracion.moduloexterno.melanbide_interop.i18n.MeLanbideInteropI18n"%>
<%@page import="es.altia.flexia.integracion.moduloexterno.melanbide_interop.vo.tercero.TerceroVO"%>
<%@page import="es.altia.agora.business.escritorio.UsuarioValueObject" %>
<%@page import="es.altia.common.service.config.Config"%>
<%@page import="es.altia.common.service.config.ConfigServiceHelper"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>

<%
    int idiomaUsuario = 1;
    int apl = 5;
    String css = "";
    if(request.getParameter("idioma") != null)
    {
        try
        {
            idiomaUsuario = Integer.parseInt(request.getParameter("idioma"));
        }
        catch(Exception ex)
        {}
    }
    UsuarioValueObject usuario = new UsuarioValueObject();
    try
    {
        if (session != null) 
        {
            if (usuario != null) 
            {
                usuario = (UsuarioValueObject) session.getAttribute("usuario");
                idiomaUsuario = usuario.getIdioma();
            apl = usuario.getAppCod();
            css = usuario.getCss();
            }
        }
    }
    catch(Exception ex)
    {
        
    }

    // Para coger los datos del tercero y pasarlos al WS
    TerceroVO _tercero = new TerceroVO();
    List<TerceroVO> _tercerosxExpediente = (List<TerceroVO>)request.getAttribute("listaTerceros");	
    
    //Clase para internacionalizar los mensajes de la aplicación.
    MeLanbideInteropI18n meLanbideInteropI18n = MeLanbideInteropI18n.getInstance();   

    Config m_Config = ConfigServiceHelper.getConfig("common");
    String statusBar = m_Config.getString("JSP.StatusBar");
    String nombreModulo     = request.getParameter("nombreModulo");
    String codOrganizacion  = request.getParameter("codOrganizacionModulo");
    String numExpediente    = request.getParameter("numero");
    
    String userAgent = request.getHeader("user-agent");
    String margenIzqPestanaEpecialidadesRecursos = "";

    if(userAgent.indexOf("MSIE")!=-1) {
        // Internet Explorer
        margenIzqPestanaEpecialidadesRecursos = "";
    }else{
        // Firefox u otro navegador
        margenIzqPestanaEpecialidadesRecursos = "margin-left:10px;";
    }
    
    // recogemos atributos para ocultar WS NO Autorizados para e procedimiento
    /*
    String hidenbtnDatosIdentidad=(String)request.getAttribute("hidenbtnDatosIdentidad");
    String hidenbtnEpigrafesIae=(String)request.getAttribute("hidenbtnDatosIdentidad");
    String hidenbtnDomicilioFiscal=(String)request.getAttribute("hidenbtnDatosIdentidad");
    String hidenbtnCorrientePagoTGSS=(String)request.getAttribute("hidenbtnDatosIdentidad");
    String hidenbtnObligacionesTribuDipu=(String)request.getAttribute("hidenbtnDatosIdentidad");
    String hidenbtnLangaiDemanda=(String)request.getAttribute("hidenbtnDatosIdentidad");
*/  

%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/extension/melanbide_interop/melanbide_interop.css"/>
<jsp:useBean id="descriptor" scope="request" class="es.altia.agora.interfaces.user.web.util.TraductorAplicacionBean"  type="es.altia.agora.interfaces.user.web.util.TraductorAplicacionBean" />
<jsp:setProperty name="descriptor"  property="idi_cod" value="<%=idiomaUsuario%>" />
<jsp:setProperty name="descriptor"  property="apl_cod" value="<%=apl%>" />
<link rel="StyleSheet" media="screen" type="text/css" href="<%=request.getContextPath()%><%=css%>">
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/extension/melanbide_interop/melanbide_interop.css"/>




<script type="text/javascript">   
    var tp41_interop;
    var codTercero;
    
    function configurarPestanas(){        
        mostrarPestanaEpecialidadesRecursos();        
    }
    
    function ocultarPestanaEpecialidadesRecursos(){
        tp41_interop.hideTabPage(1);
        //tp41_interop.hideTabPage(2);
    }
    
    function mostrarPestanaEpecialidadesRecursos(){
        tp41_interop.showTabPage(1);
        //tp41_interop.showTabPage(2);
    }
    
    function recogerListaTerceros(){
        var listaTercerosExp;
        var nooChlidren = 0;
        var uno = document.forms[0];
        if(navigator.appName.indexOf("Internet Explorer")!=-1){
            // En IE el XML viene en responseText y no en la propiedad responseXML
            nooChlidren = uno.children.length;
        }else{
            // En el resto de navegadores el XML se recupera de la propiedad responseXML
            nooChlidren = uno.childElementCount;
        }
        for(i=0; i<nooChlidren; i++)
        {
            if(uno.children[i].name=="listaCodTercero"){
                //alert('lo encontramos : teceros del expediente:'+ uno.children[i].value);
                listaTercerosExp = uno.children[i].value;     
                break;
            }
        }
        return listaTercerosExp;
    }
    
    function llamarServicioDatosIdentidad(){
        var listaTercerosExp = recogerListaTerceros();    
        if(tabTerceros.selectedIndex != -1){
            var ajax = getXMLHttpRequest();
            var nodos = null;
            var CONTEXT_PATH = '<%=request.getContextPath()%>';
            var url = CONTEXT_PATH + "/PeticionModuloIntegracion.do";
            var parametros = "";

            parametros = "tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=GetVerificacionDatosIdentificacion&tipo=0&numero=<%=numExpediente%>&listaTercerosExp="+listaTercerosExp
                           + "&codTer="+ listaTerceros[tabTerceros.selectedIndex][0]
                           + "&documento="+ listaTerceros[tabTerceros.selectedIndex][1];
            try{
                ajax.open("POST",url,false);
                ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=ISO-8859-1");
                ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");     
                //var formData = new FormData(document.getElementById('formContrato'));
                ajax.send(parametros);
                if (ajax.readyState==4 && ajax.status==200){
                    var xmlDoc = null;
                    if(navigator.appName.indexOf("Internet Explorer")!=-1){
                        // En IE el XML viene en responseText y no en la propiedad responseXML
                        var text = ajax.responseText;
                        xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
                        xmlDoc.async="false";
                        xmlDoc.loadXML(text);
                    }else{
                        // En el resto de navegadores el XML se recupera de la propiedad responseXML
                        xmlDoc = ajax.responseXML;
                    }//if(navigator.appName.indexOf("Internet Explorer")!=-1)
                }//if (ajax.readyState==4 && ajax.status==200)
                nodos = xmlDoc.getElementsByTagName("RESPUESTA");
                if(nodos.length>0){
                    var elemento = nodos[0];
                    var hijos = elemento.childNodes;
                    var codigoOperacion = null;
                    var textoRespuestaWS = null;
                    //var lista = new Array();
                    //var fila = new Array();
                    //var nodoFila;
                    //var hijosFila;
                    for(j=0;hijos!=null && j<hijos.length;j++){
                        if(hijos[j].nodeName=="CODIGO_OPERACION"){                            
                            codigoOperacion = hijos[j].childNodes[0].nodeValue;
                            //alert(codigoOperacion);
                            //lista[j] = codigoOperacion;
                        }//if(hijos[j].nodeName=="CODIGO_OPERACION")        
                        else if(hijos[j].nodeName=="RESULTADO"){                            
                            textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                            //alert(textoRespuestaWS);
                        }
                        /*
                        else if(hijos[j].nodeName=="FILA"){
                            nodoFila = hijos[j];
                            hijosFila = nodoFila.childNodes;
                            for(var cont = 0; cont < hijosFila.length; cont++){
                                    alert('pasa por el for: ' + con);
                            }
                            lista[j] = fila;
                            fila = new Array();
                        }  
                        */
                    }//for(j=0;hijos!=null && j<hijos.length;j++)
                    if(codigoOperacion=="0"){
                        jsp_alerta("A",' ' + textoRespuestaWS);
                        //window.returnValue =  lista;
                        //cerrarVentana();
                    }else if(codigoOperacion=="1" || "4" < codigoOperacion ){
                        jsp_alerta("A",textoRespuestaWS); /*'<=//meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorBD")%>'*/
                    }else if(codigoOperacion=="2"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                    }else if(codigoOperacion=="3"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.pasoParametros")%>');
                    }else if(codigoOperacion=="4"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.expSinTercero")%>');
                    }else{
                            jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                    }//if(
                }else{
                    jsp_alerta('A',"Error procesando la solicitud. No se ha podido establecer conexion/obtener respuesta del WebService.");
                }
            }
            catch(err){
                jsp_alerta('A',"Error procesando la solicitud : " + err.message);
            }//try-catch
        }else{
            jsp_alerta('A', '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "msg.msjNoSelecFila")%>');
        }
    }
    
    function llamarServicioEpigrafesIAE(){
        var listaTercerosExp = recogerListaTerceros();    
        if(tabTerceros.selectedIndex != -1){
            var ajax = getXMLHttpRequest();
            var nodos = null;
            var CONTEXT_PATH = '<%=request.getContextPath()%>';
            var url = CONTEXT_PATH + "/PeticionModuloIntegracion.do";
            var parametros = "";

            parametros = "tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=GetConsultaEpigrafesIae&tipo=0&numero=<%=numExpediente%>&listaTercerosExp="+listaTercerosExp
                           + "&codTer="+ listaTerceros[tabTerceros.selectedIndex][0]
                           + "&documento="+ listaTerceros[tabTerceros.selectedIndex][1];
            try{
                ajax.open("POST",url,false);
                ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=ISO-8859-1");
                ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");     
                //var formData = new FormData(document.getElementById('formContrato'));
                ajax.send(parametros);
                if (ajax.readyState==4 && ajax.status==200){
                    var xmlDoc = null;
                    if(navigator.appName.indexOf("Internet Explorer")!=-1){
                        // En IE el XML viene en responseText y no en la propiedad responseXML
                        var text = ajax.responseText;
                        xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
                        xmlDoc.async="false";
                        xmlDoc.loadXML(text);
                    }else{
                        // En el resto de navegadores el XML se recupera de la propiedad responseXML
                        xmlDoc = ajax.responseXML;
                    }//if(navigator.appName.indexOf("Internet Explorer")!=-1)
                }//if (ajax.readyState==4 && ajax.status==200)
                nodos = xmlDoc.getElementsByTagName("RESPUESTA");
                if(nodos.length>0){
                    var elemento = nodos[0];
                    var hijos = elemento.childNodes;
                    var codigoOperacion = null;
                    var textoRespuestaWS = null;
                    for(j=0;hijos!=null && j<hijos.length;j++){
                        if(hijos[j].nodeName=="CODIGO_OPERACION"){                            
                            codigoOperacion = hijos[j].childNodes[0].nodeValue;
                        }//if(hijos[j].nodeName=="CODIGO_OPERACION")
                        else if(hijos[j].nodeName=="RESULTADO"){                            
                            textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                        }
                    }//for(j=0;hijos!=null && j<hijos.length;j++)
                    if(codigoOperacion=="0"){
                        jsp_alerta("A",textoRespuestaWS);
                    }else if(codigoOperacion=="1" || "4"<codigoOperacion){
                        jsp_alerta("A",textoRespuestaWS);
                    }else if(codigoOperacion=="2"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                    }else if(codigoOperacion=="3"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.pasoParametros")%>');
                    }else if(codigoOperacion=="4"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.expSinTercero")%>');
                    }else{
                            jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                    }//if(
                }else{
                    jsp_alerta('A',"Error procesando la solicitud. No se ha podido establecer conexion/obtener respuesta del WebService.");
                }
            }
            catch(Err){
                jsp_alerta('A',"Error procesando la solicitud : " + Err.message);
            }//try-catch
        }else{
            jsp_alerta('A', '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "msg.msjNoSelecFila")%>');
        }
    }
    
    function llamarServicioDomicilioFiscal(){
        var listaTercerosExp = recogerListaTerceros();    
        if(tabTerceros.selectedIndex != -1){
            var ajax = getXMLHttpRequest();
            var nodos = null;
            var CONTEXT_PATH = '<%=request.getContextPath()%>';
            var url = CONTEXT_PATH + "/PeticionModuloIntegracion.do";
            var parametros = "";

            parametros = "tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=GetConsultaDomicilioFiscal&tipo=0&numero=<%=numExpediente%>&listaTercerosExp="+listaTercerosExp
                           + "&codTer="+ listaTerceros[tabTerceros.selectedIndex][0]
                           + "&documento="+ listaTerceros[tabTerceros.selectedIndex][1];
            try{
                ajax.open("POST",url,false);
                ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=ISO-8859-1");
                ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");     
                //var formData = new FormData(document.getElementById('formContrato'));
                ajax.send(parametros);
                if (ajax.readyState==4 && ajax.status==200){
                    var xmlDoc = null;
                    if(navigator.appName.indexOf("Internet Explorer")!=-1){
                        // En IE el XML viene en responseText y no en la propiedad responseXML
                        var text = ajax.responseText;
                        xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
                        xmlDoc.async="false";
                        xmlDoc.loadXML(text);
                    }else{
                        // En el resto de navegadores el XML se recupera de la propiedad responseXML
                        xmlDoc = ajax.responseXML;
                    }//if(navigator.appName.indexOf("Internet Explorer")!=-1)
                }//if (ajax.readyState==4 && ajax.status==200)
                nodos = xmlDoc.getElementsByTagName("RESPUESTA");
                if(nodos.length>0){
                    var elemento = nodos[0];
                    var hijos = elemento.childNodes;
                    var codigoOperacion = null;
                    var textoRespuestaWS = null;
                    for(j=0;hijos!=null && j<hijos.length;j++){
                        if(hijos[j].nodeName=="CODIGO_OPERACION"){                            
                            codigoOperacion = hijos[j].childNodes[0].nodeValue;
                        }//if(hijos[j].nodeName=="CODIGO_OPERACION") 
                        else if(hijos[j].nodeName=="RESULTADO"){                            
                            textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                        }
                    }//for(j=0;hijos!=null && j<hijos.length;j++)
                    if(codigoOperacion=="0"){
                        jsp_alerta("A",textoRespuestaWS);
                    }else if(codigoOperacion=="1" || "4"<codigoOperacion){
                        jsp_alerta("A",textoRespuestaWS);
                    }else if(codigoOperacion=="2"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                    }else if(codigoOperacion=="3"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.pasoParametros")%>');
                    }else if(codigoOperacion=="4"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.expSinTercero")%>');
                    }else{
                            jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                    }//if(
                }else{
                    jsp_alerta('A',"Error procesando la solicitud. No se ha podido establecer conexion/obtener respuesta del WebService.");
                }
            }
            catch(Err){
                jsp_alerta('A',"Error procesando la solicitud : " + Err.message);
            }//try-catch
        }else{
            jsp_alerta('A', '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "msg.msjNoSelecFila")%>');
        }
    }
    function isDocumentoVacio(documento){
        if(documento!==undefined && documento!==null && documento!=="null" && documento!=="")
            return false;
        return true;
    }
    
    function llamarServicioLagaiDemanda(){
        var listaTercerosExp = recogerListaTerceros(); 
        var docvacio = isDocumentoVacio(listaTerceros[tabTerceros.selectedIndex][1]);
        if(tabTerceros.selectedIndex != -1 && !docvacio ){
            var ajax = getXMLHttpRequest();
            var nodos = null;
            var CONTEXT_PATH = '<%=request.getContextPath()%>';
            var url = CONTEXT_PATH + "/PeticionModuloIntegracion.do";
            var parametros = "";

            parametros = "tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=GetConsultaLangaiDemanda&tipo=0&numero=<%=numExpediente%>&listaTercerosExp="+listaTercerosExp
                           + "&codTer="+ listaTerceros[tabTerceros.selectedIndex][0]
                           + "&documento="+ listaTerceros[tabTerceros.selectedIndex][1]
                           + "&versionTer="+ listaTerceros[tabTerceros.selectedIndex][4]
                   ;
            try{
                ajax.open("POST",url,false);
                ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=ISO-8859-1");
                ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");     
                //var formData = new FormData(document.getElementById('formContrato'));
                ajax.send(parametros);
                if (ajax.readyState==4 && ajax.status==200){
                    var xmlDoc = null;
                    if(navigator.appName.indexOf("Internet Explorer")!=-1){
                        // En IE el XML viene en responseText y no en la propiedad responseXML
                        var text = ajax.responseText;
                        xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
                        xmlDoc.async="false";
                        xmlDoc.loadXML(text);
                    }else{
                        // En el resto de navegadores el XML se recupera de la propiedad responseXML
                        xmlDoc = ajax.responseXML;
                    }//if(navigator.appName.indexOf("Internet Explorer")!=-1)
                }//if (ajax.readyState==4 && ajax.status==200)
                nodos = xmlDoc.getElementsByTagName("RESPUESTA");
                var elemento = nodos[0];
                var hijos = elemento.childNodes;
                var codigoOperacion = null;
                //var textoRespuestaWS = null;
                var lista = new Array();
                //var fila = new Array();
                //var nodoFila;
                //var hijosFila;
                for(j=0;hijos!=null && j<hijos.length;j++){
                    if(hijos[j].nodeName=="CODIGO_OPERACION"){                            
                        codigoOperacion = hijos[j].childNodes[0].nodeValue;
                        lista[j] = codigoOperacion;
                    }//if(hijos[j].nodeName=="CODIGO_OPERACION")  
                    /*
                    else if(hijos[j].nodeName=="RESULTADO"){                            
                        textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                        alert(textoRespuestaWS);
                    }//if(hijos[j].nodeName=="CODIGO_OPERACION")       
                    */
                    /*
                    else if(hijos[j].nodeName=="FILA"){
                        nodoFila = hijos[j];
                        hijosFila = nodoFila.childNodes;
                        for(var cont = 0; cont < hijosFila.length; cont++){
                                alert('pasa por el for: ' + con);
                        }
                        lista[j] = fila;
                        fila = new Array();
                    } 
                    */
                }//for(j=0;hijos!=null && j<hijos.length;j++)
                /*
                    * 0 - No existe demandante o se ha producido un error
                    * 1 - Existe demandante y está en alta (SituAdm A)
                    * 2 - Existe demandante y está en suspensión (SituAdm S)
                    * 9 - Existe demandante y el estado es otro (SituAdm 'else')
                    */
                if(codigoOperacion=="0"){
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"msg.respuesta.langaidemaWS.0")%>');
                }else if(codigoOperacion=="1"){
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"msg.respuesta.langaidemaWS.1")%>');
                }else if(codigoOperacion=="2"){
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"msg.respuesta.langaidemaWS.2")%>');
                }else if(codigoOperacion=="9"){
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"msg.respuesta.langaidemaWS.9")%>');
                }else if(codigoOperacion=="-1"){
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.pasoParametros")%>');    
                }else if(codigoOperacion=="-2"){
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                }else if(codigoOperacion=="-3"){
                    jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorConectionWS")%>');    
                }else{
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                }//if(
            }
            catch(err){
                 jsp_alerta('A',"Error procesando la solicitud : " + err.message);
            }//try-catch
        }else{
            if(docvacio){
                jsp_alerta('A', '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "msg.msjDocumentoInterNull")%>');
            }else{
                jsp_alerta('A', '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "msg.msjNoSelecFila")%>');
            }
        }
    }
    
    function llamarServicioCorrientePagoHHFF(){
        var listaTercerosExp = recogerListaTerceros();    
        if(tabTerceros.selectedIndex != -1){
            var ajax = getXMLHttpRequest();
            var nodos = null;
            var CONTEXT_PATH = '<%=request.getContextPath()%>';
            var url = CONTEXT_PATH + "/PeticionModuloIntegracion.do";
            var parametros = "";

            parametros = "tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=GetConsultaCorrientePagoHHFF&tipo=0&numero=<%=numExpediente%>&listaTercerosExp="+listaTercerosExp
                           + "&codTer="+ listaTerceros[tabTerceros.selectedIndex][0]
                           + "&documento="+ listaTerceros[tabTerceros.selectedIndex][1];
            try{
                ajax.open("POST",url,false);
                ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=ISO-8859-1");
                ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");     
                ajax.send(parametros);
                if (ajax.readyState==4 && ajax.status==200){
                    var xmlDoc = null;
                    if(navigator.appName.indexOf("Internet Explorer")!=-1){
                        var text = ajax.responseText;
                        xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
                        xmlDoc.async="false";
                        xmlDoc.loadXML(text);
                    }else{
                        xmlDoc = ajax.responseXML;
                    }
                }
                nodos = xmlDoc.getElementsByTagName("RESPUESTA");
                if(nodos.length>0){
                    var elemento = nodos[0];
                    var hijos = elemento.childNodes;
                    var codigoOperacion = null;
                    var textoRespuestaWS = null;
                    for(j=0;hijos!=null && j<hijos.length;j++){
                        if(hijos[j].nodeName=="CODIGO_OPERACION"){                            
                            codigoOperacion = hijos[j].childNodes[0].nodeValue;
                        }
                        else if(hijos[j].nodeName=="RESULTADO"){                            
                            textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                        }
                    }
                    if(codigoOperacion=="0"){
                        jsp_alerta("A",textoRespuestaWS);
                    }else if(codigoOperacion=="1" || "4"<codigoOperacion){
                        jsp_alerta("A",textoRespuestaWS);
                    }else if(codigoOperacion=="2"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                    }else if(codigoOperacion=="3"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.pasoParametros")%>');
                    }else if(codigoOperacion=="4"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.expSinTercero")%>');
                    }else{
                            jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                    }
                }else{
                    jsp_alerta('A',"Error procesando la solicitud. No se ha podido establecer conexion/obtener respuesta del WebService.");
                }
            }
            catch(Err){
                 jsp_alerta('A',"Error procesando la solicitud : " + Err.message);
            }//try-catch
        }else{
            jsp_alerta('A', '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "msg.msjNoSelecFila")%>');
        }//try-catch
        }else{
            jsp_alerta('A', '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "msg.msjNoSelecFila")%>');
        }//try-catch
        }else{
            jsp_alerta('A', '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "msg.msjNoSelecFila")%>');
        }//try-catch
        }else{
            jsp_alerta('A', '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "msg.msjNoSelecFila")%>');
        }
        }else{
            jsp_alerta('A', '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "msg.msjNoSelecFila")%>');
        }
    }

    function llamarServicioCorrientePagoTgss(){
        var listaTercerosExp = recogerListaTerceros();    
        if(tabTerceros.selectedIndex != -1){
            var ajax = getXMLHttpRequest();
            var nodos = null;
            var CONTEXT_PATH = '<%=request.getContextPath()%>';
            var url = CONTEXT_PATH + "/PeticionModuloIntegracion.do";
            var parametros = "";

            parametros = "tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=GetConsultaCorrientePagoTGSS&tipo=0&numero=<%=numExpediente%>&listaTercerosExp="+listaTercerosExp
                           + "&codTer="+ listaTerceros[tabTerceros.selectedIndex][0]
                           + "&documento="+ listaTerceros[tabTerceros.selectedIndex][1];
            try{
                ajax.open("POST",url,false);
                ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=ISO-8859-1");
                ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");     
                //var formData = new FormData(document.getElementById('formContrato'));
                ajax.send(parametros);
                if (ajax.readyState==4 && ajax.status==200){
                    var xmlDoc = null;
                    if(navigator.appName.indexOf("Internet Explorer")!=-1){
                        // En IE el XML viene en responseText y no en la propiedad responseXML
                        var text = ajax.responseText;
                        xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
                        xmlDoc.async="false";
                        xmlDoc.loadXML(text);
                    }else{
                        // En el resto de navegadores el XML se recupera de la propiedad responseXML
                        xmlDoc = ajax.responseXML;
                    }//if(navigator.appName.indexOf("Internet Explorer")!=-1)
                }//if (ajax.readyState==4 && ajax.status==200)
                nodos = xmlDoc.getElementsByTagName("RESPUESTA");
                if(nodos.length>0){
                    var elemento = nodos[0];
                    var hijos = elemento.childNodes;
                    var codigoOperacion = null;
                    var textoRespuestaWS = null;
                    var lista = new Array();
                    var fila = new Array();
                    var nodoFila;
                    var hijosFila;
                    for(j=0;hijos!=null && j<hijos.length;j++){
                        if(hijos[j].nodeName=="CODIGO_OPERACION"){                            
                            codigoOperacion = hijos[j].childNodes[0].nodeValue;
                        }//if(hijos[j].nodeName=="CODIGO_OPERACION")
                        else if(hijos[j].nodeName=="RESULTADO"){                            
                            textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                        }
                    }//for(j=0;hijos!=null && j<hijos.length;j++)
                    if(codigoOperacion=="0"){
                        jsp_alerta("A",textoRespuestaWS);
                    }else if(codigoOperacion=="1" || "4"<codigoOperacion){
                        jsp_alerta("A",textoRespuestaWS);
                    }else if(codigoOperacion=="2"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                    }else if(codigoOperacion=="3"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.pasoParametros")%>');
                    }else if(codigoOperacion=="4"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.expSinTercero")%>');
                    }else{
                            jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                    }//if(
                }else{
                    jsp_alerta('A',"Error procesando la solicitud. No se ha podido establecer conexion/obtener respuesta del WebService.");
                }
            }
            catch(Err){
                 jsp_alerta('A',"Error procesando la solicitud : " + err.message);
            }//try-catch
        }else{
            jsp_alerta('A', '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "msg.msjNoSelecFila")%>');
        }
    }
    
    function llamarServicioObligacionesTribuDipu(){
        var listaTercerosExp = recogerListaTerceros();    
        if(tabTerceros.selectedIndex != -1){
            var ajax = getXMLHttpRequest();
            var nodos = null;
            var CONTEXT_PATH = '<%=request.getContextPath()%>';
            var url = CONTEXT_PATH + "/PeticionModuloIntegracion.do";
            var parametros = "";

            parametros = "tarea=preparar&modulo=MELANBIDE_INTEROP&operacion=GetConsultaObligacionesTribuDipu&tipo=0&numero=<%=numExpediente%>&listaTercerosExp="+listaTercerosExp
                           + "&codTer="+ listaTerceros[tabTerceros.selectedIndex][0]
                           + "&documento="+ listaTerceros[tabTerceros.selectedIndex][1];
            try{
                ajax.open("POST",url,false);
                ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=ISO-8859-1");
                ajax.setRequestHeader("Accept", "text/xml, application/xml, text/plain");     
                //var formData = new FormData(document.getElementById('formContrato'));
                ajax.send(parametros);
                if (ajax.readyState==4 && ajax.status==200){
                    var xmlDoc = null;
                    if(navigator.appName.indexOf("Internet Explorer")!=-1){
                        // En IE el XML viene en responseText y no en la propiedad responseXML
                        var text = ajax.responseText;
                        xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
                        xmlDoc.async="false";
                        xmlDoc.loadXML(text);
                    }else{
                        // En el resto de navegadores el XML se recupera de la propiedad responseXML
                        xmlDoc = ajax.responseXML;
                    }//if(navigator.appName.indexOf("Internet Explorer")!=-1)
                }//if (ajax.readyState==4 && ajax.status==200)
                nodos = xmlDoc.getElementsByTagName("RESPUESTA");
                if(nodos.length>0){
                    var elemento = nodos[0];
                    var hijos = elemento.childNodes;
                    var codigoOperacion = null;
                    var textoRespuestaWS = null;
                    for(j=0;hijos!=null && j<hijos.length;j++){
                        if(hijos[j].nodeName=="CODIGO_OPERACION"){                            
                            codigoOperacion = hijos[j].childNodes[0].nodeValue;
                        }//if(hijos[j].nodeName=="CODIGO_OPERACION")
                        else if(hijos[j].nodeName=="RESULTADO"){                            
                            textoRespuestaWS = hijos[j].childNodes[0].nodeValue;
                        }   
                    }//for(j=0;hijos!=null && j<hijos.length;j++)
                    if(codigoOperacion=="0"){
                        jsp_alerta("A",textoRespuestaWS);
                    }else if(codigoOperacion=="1" ||"4"<codigoOperacion){
                        jsp_alerta("A",textoRespuestaWS);
                    }else if(codigoOperacion=="2"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                    }else if(codigoOperacion=="3"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.pasoParametros")%>');
                    }else if(codigoOperacion=="4"){
                        jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.expSinTercero")%>');
                    }else{
                            jsp_alerta("A",'<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"error.errorGen")%>');
                    }//if(
                }else{
                    jsp_alerta('A',"Error procesando la solicitud. No se ha podido obtener respuesta o establecer conexion con el WebService.");
                }
            }
            catch(err){
                jsp_alerta('A',"Error procesando la solicitud : " + err.message);
            }//try-catch
        }else{
            jsp_alerta('A', '<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "msg.msjNoSelecFila")%>');
        }
    }

    function cerrarVentana(){
        if(navigator.appName=="Microsoft Internet Explorer") { 
              window.parent.window.opener=null; 
              window.parent.window.close(); 
        }else if(navigator.appName=="Netscape") { 
              top.window.opener = top; 
              top.window.open('','_parent',''); 
              top.window.close(); 
        }else{
             window.close(); 
        } 
    }
</script>

<body>
    <div class="tab-page" id="tabPageinterop" style="height:520px; width: 100%;">
        <h2 class="tab" id="pestanainterop"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"label.interoperabilidad.tituloPestana")%></h2>
        <script type="text/javascript">tp1.addTabPage( document.getElementById( "tabPageinterop" ) );</script>
        <div style="clear: both;">
            <label class="legendAzul" style="text-align: center;"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.interoperabilidad.tituloTablaTerceros")%></label>
                <div id="divGeneral"  style="overflow-y: auto; overflow-x: hidden; height: 160px;">     <!--onscroll="deshabilitarRadios();"-->
                    <div id="listaTerceros" style="padding: 5px; width:870px; height: 150px; text-align: center; overflow-x: auto; overflow-y: auto;margin:0px;margin-top:0px;" align="center"></div>
                </div>
        </div>
        <div style="clear: both;">
            <div class="contenidoPantalla">
                <div style="width: 100%; padding: 10px; text-align: left;">
                    <div  class="sub3titulo" style="clear: both; text-align: center; font-size: 14px;">
                        <span>
                            <%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"label.interoperabilidad.tituloBotones")%>
                        </span>
                    </div>
                    <br><br>
                    <div class="botonera" style="text-align: center">
                        <logic:equal name="hidenbtnDatosIdentidad" value="1" scope="request">
                            <input type="button" id="btnDatosIdentidad" name="btnDatosIdentidad" class="interopBotonMuylargoBoton" value="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"btn.datosIdentidad")%>" onclick="llamarServicioDatosIdentidad()">
                        <br><br>
                        </logic:equal>
                        <logic:equal name="hidenbtnEpigrafesIae" value="1" scope="request">
                            <input type="button" id="btnEpigrafesIae" name="btnEpigrafesIae" class="interopBotonMuylargoBoton" value="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"btn.epigrafesIAE")%>" onclick="llamarServicioEpigrafesIAE()" >
                            <br><br>
                         </logic:equal>
                        <logic:equal name="hidenbtnDomicilioFiscal" value="1" scope="request">
                            <input type="button" id="btnDomicilioFiscal" name="btnDomicilioFiscal" class="interopBotonMuylargoBoton" value="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"btn.domicilioFiscal")%>" onclick="llamarServicioDomicilioFiscal()" >
                            <br><br>
                        </logic:equal>
                        <logic:equal name="hidenbtnCorrientePagoTGSS" value="1" scope="request">
                            <input type="button" id="btnCorrientePagoTGSS" name="btnCorrientePagoTGSS" class="interopBotonMuylargoBoton" value="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"btn.btnCorrientePagoTGSS")%>" onclick="llamarServicioCorrientePagoTgss()" >
                            <br><br>
                        </logic:equal>    
                        <logic:equal name="hidenbtnHHFF" value="1" scope="request">
                            <input type="button" id="btnCorrientePagoHHFF" name="btnCorrientePagoHHFF" class="interopBotonMuylargoBoton" value="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"btn.btnCorrientePagoHHFF")%>" onclick="llamarServicioCorrientePagoHHFF()" >
                            <br><br>
                        </logic:equal>
                        <logic:equal name="hidenbtnObligacionesTribuDipu" value="1" scope="request">
                            <input type="button" id="btnObligacionesTribuDipu" name="btnObligacionesTribuDipu" class="interopBotonMuylargoBoton" value="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"btn.btnObligacionesTribuDipu")%>" onclick="llamarServicioObligacionesTribuDipu()" >
                            <br><br>
                        </logic:equal>    
                        <logic:equal name="hidenbtnLangaiDemanda" value="1" scope="request">
                            <input type="button" id="btnLangaiDemanda" name="btnLangaiDemanda" class="interopBotonMuylargoBoton" value="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"btn.langaiDemanda")%>" onclick="llamarServicioLagaiDemanda()" >
                        </logic:equal>    
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
    <script type="text/javascript">
        //Tabla Terceros
        var tabTerceros;
        var listaTerceros = new Array();
        var listaTercerosTabla = new Array();
        

        tabTerceros = new Tabla(document.getElementById('listaTerceros'), 820);
        tabTerceros.addColumna('100','left',"<%= meLanbideInteropI18n.getMensaje(idiomaUsuario,"interoperabilidad.tablaTerceros.col1")%>");
        tabTerceros.addColumna('400','left',"<%= meLanbideInteropI18n.getMensaje(idiomaUsuario,"interoperabilidad.tablaTerceros.col2")%>");
        tabTerceros.addColumna('300','left',"<%= meLanbideInteropI18n.getMensaje(idiomaUsuario,"interoperabilidad.tablaTerceros.col3")%>");

        tabTerceros.displayCabecera=true;
        tabTerceros.height = 100;

        <%  		
            TerceroVO objectVO = null;
            List<TerceroVO> List = (List<TerceroVO>)request.getAttribute("listaTerceros");													
            if (List!= null && List.size() >0){
                for (int indice=0;indice<List.size();indice++)
                {
                    objectVO = List.get(indice);
                    //SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                    //String fechaAdqui = formatoFecha.format(objectVO.getFechaAdq());

        %>
            
            var nombreCompleto = '<%=objectVO.getNombreCompleto()%>';
            nombreCompleto = nombreCompleto.replace('null','');
            var documentoInte = '<%=objectVO.getDoc()%>';
            documentoInte = documentoInte.replace('null','');
            //alert(nombreCompleto);
            listaTercerosTabla[<%=indice%>] = [documentoInte,nombreCompleto,'<%=objectVO.getRol()%>'];
            listaTerceros[<%=indice%>] = ['<%=objectVO.getCodTer()%>',documentoInte,nombreCompleto,'<%=objectVO.getRol()%>','<%=objectVO.getVersionTercero()%>'];

        <%
                }// for
            }// if
        %>

        tabTerceros.lineas=listaTercerosTabla;
        tabTerceros.displayTabla();


        if(navigator.appName.indexOf("Internet Explorer")!=-1){
            try{
                var div = document.getElementById('listaTerceros');
                div.children[0].children[0].children[0].children[0].children[1].style.width = '100%';
                div.children[0].children[0].children[1].style.width = '100%';
            }
            catch(err){

            }
        }
    </script>


    <script type="text/javascript">   
        //configurarPestanas();
    </script>