<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%@page import="es.altia.agora.business.escritorio.UsuarioValueObject" %>
<%@page import="es.altia.flexia.integracion.moduloexterno.melanbide_interop.i18n.MeLanbideInteropI18n" %>
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
    
    <!--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/DataTables/datatables.min.css"/>-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap413/bootstrap.min.css" media="all"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilo.css"/>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/DataTables/datatables.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap413/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/extension/melanbide_interop/gestionIntegracionNISAE.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/general.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/popup.js"></script>
    <script type="text/javascript">
        var APP_CONTEXT_PATH = '<%=request.getContextPath()%>';
    </script>
<body>
    <div style="padding-left: 15px; text-align: center;" class="txttitblanco"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "legend.titulo.principal")%></div>
    <input type="hidden" id="mensajeInicial" name="mensajeInicial" value="<%=mensajeInicial%>">
   <!--Modales-->
   <div class="modal fade" id="modalGestionMensajeOperacion" tabindex="-1" role="dialog" aria-labelledby="modalMensajeLabel" aria-hidden="true">
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
            </div>
          </div>
        </div>
      </div>
</body>

