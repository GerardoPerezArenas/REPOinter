<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<%@page import="es.altia.agora.business.escritorio.UsuarioValueObject" %>
<%@page import="es.altia.flexia.integracion.moduloexterno.melanbide_interop.i18n.MeLanbideInteropI18n" %>
<%@page import="es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.ComboNisae" %>
<%@page import="es.altia.flexia.integracion.moduloexterno.melanbide_interop.nisae.entities.InteropServiciosNisae" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
    <%
        int idiomaUsuario = 0;
        int codOrganizacion = 0;
        int idUsuario=0;
        int apl = 5;
        String css = "";
        UsuarioValueObject usuario = new UsuarioValueObject();
        try
        {
            if (session != null) 
            {
                if (usuario != null) 
                {
                    usuario = (UsuarioValueObject) session.getAttribute("usuario");
                    idiomaUsuario = usuario.getIdioma();
                    codOrganizacion  = usuario.getOrgCod();
                    idUsuario=usuario.getIdUsuario();
                    apl = usuario.getAppCod();
                    css = usuario.getCss();
                }
            }
        }
        catch(Exception ex)
        {
        
        }
        MeLanbideInteropI18n meLanbideInteropI18n = MeLanbideInteropI18n.getInstance();
        String numExpediente    = request.getParameter("numero");
        String mensajeInicial    = request.getParameter("mensajeInicial");
    %>
    
    <jsp:useBean id="descriptor" scope="request" class="es.altia.agora.interfaces.user.web.util.TraductorAplicacionBean"  type="es.altia.agora.interfaces.user.web.util.TraductorAplicacionBean" />
    <jsp:setProperty name="descriptor"  property="idi_cod" value="<%=idiomaUsuario%>" />
    <jsp:setProperty name="descriptor"  property="apl_cod" value="<%=apl%>" />
    
    <link rel="StyleSheet" media="screen" type="text/css" href="<%=request.getContextPath()%><%=css%>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/font-awesome-4.6.2/css/font-awesome.min.css'/>" media="all">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/font-awesome-4.6.2/less/animated.less'/>" media="all">
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap413/bootstrap.min.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilo.css"/>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/DataTables/datatables.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap413/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/extension/melanbide_interop/telematico/utilities/gestionCargaModExtensionFromXML.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/general.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/popup.js"></script>
    <script type="text/javascript">
        var APP_CONTEXT_PATH = '<%=request.getContextPath()%>';
    </script>
<div class="contenidoPantalla">
    <input type="hidden" id="mensajeInicial" name="mensajeInicial" value="<%=mensajeInicial%>">
    <div style="padding-left: 15px; text-align: center;" class="txttitblanco"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "legend.telematico.titulo.principal")%></div>
    <div  class="container align-self-center" style="width: 40%; margin-top:30px;">
        <div class="alert alert-danger alert-dismissible fade show" id="textoValidacionCampos" role="alert" style="display:none;"></div>
        <input type="hidden" id="mensajeBaseCamposOblgatorios" name="mensajeBaseCamposOblgatorios" value="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.campos.obligatorios")%>"/>
        <div class="form-group">
            <label for="ejercicio"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.ejercicio")%></label>
            <input type="text" maxlength="4" max="4" width="6em" class="form-control" id="ejercicio" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.ejercicio")%>"  placeholder="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.ejercicio.placeholder")%>" 
                   onkeypress="return numeroEntero(event);" onfocusout="comprobarVerTextoValidacionCamposmensajeInicial()">
        </div>
        <div class="form-group">
            <label for="listaProcedimiento"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.procedimiento")%></label>
            <select class="selectpicker form-control" data-actions-box="true" name="listaProcedimiento" id="listaProcedimiento" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.procedimiento")%>">
                <option value=""><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.select.option.default")%></option>
                <c:forEach items="${listaProcedimiento}" var="elementLista" varStatus="contador">
                    <option value="<c:out value="${elementLista.id}"/>" title="<c:out value="${elementLista.valor}"/>"><c:out value="${elementLista.id}"/></option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <div class="input-group-prepend">
                <div class="alert alert-primary">
                    <input type="checkbox" id="rangoExpedientes" name="rangoExpedientes" aria-label=""/>
                    &nbsp;&nbsp;<span><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.rango.expedientes")%></span>
                </div>
            </div> 
            <div class="input-group" id="divRangoExpedientes" name="divRangoExpedientes" style="display: none;">
                <label for="numeroExpedienteDesde"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente.desde")%></label>
                <input type="text" maxlength="6" max="6" width="6em" class="form-control" id="numeroExpedienteDesde" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente.desde")%>"  placeholder="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente.placeholder")%>" 
                       onkeypress="return numeroEntero(event);">
                <label for="numeroExpedienteHasta"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente.hasta")%></label>
                <input type="text" maxlength="6" max="6" width="6em" class="form-control" id="numeroExpedienteHasta" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente.hasta")%>"  placeholder="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente.placeholder")%>" 
                       onkeypress="return numeroEntero(event);">
            </div>
        </div>
        <div class="form-group">
            <div class="input-group-prepend">
                <div class="alert alert-primary">
                    &nbsp;&nbsp;<span><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente.title")%></span>
                </div>
            </div> 
            <label for="numeroExpediente"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente")%></label>
            <input type="text" maxlength="20" max="20" width="10em" class="form-control" id="numeroExpediente" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.expediente.placeholder")%>"  placeholder="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.expediente.placeholder")%>" 
                   >
        </div>
        <button type="button" id="lanzarProceso" onclick="lanzarProcesoComprobacionDatosPreview();" class="btn btn-primary"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.boton.lanzar.proceso")%></button>
    </div>
   <!--Modales-->
   <div class="modal fade" id="modalGestionOperacionCargaMEXML" tabindex="-1" role="dialog" aria-labelledby="modalMensajeLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="modalMensajeLabel"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.modal.titulo")%></h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
                <h5 id="textoBodyModal"></h5>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-primary" data-dismiss="modal"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.modal.boton.volver")%></button>
              <button type="button" id="modalAceptarLanzarProceso" onclick="lanzarProcesoComprobacionDatos();" class="btn btn-primary"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.modal.boton.ejecutar")%></button>
            </div>
          </div>
        </div>
    </div>
</div>

