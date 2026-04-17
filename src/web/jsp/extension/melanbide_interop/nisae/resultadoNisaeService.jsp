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
%>

<jsp:useBean id="descriptor" scope="request" class="es.altia.agora.interfaces.user.web.util.TraductorAplicacionBean"  type="es.altia.agora.interfaces.user.web.util.TraductorAplicacionBean" />
<jsp:setProperty name="descriptor"  property="idi_cod" value="<%=idiomaUsuario%>" />
<jsp:setProperty name="descriptor"  property="apl_cod" value="<%=apl%>" />
<script type="text/javascript">var APP_CONTEXT_PATH = '<%=request.getContextPath()%>';</script>

<link rel="StyleSheet" media="screen" type="text/css" href="<%=request.getContextPath()%><%=css%>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/font-awesome-4.6.2/css/font-awesome.min.css'/>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/font-awesome-4.6.2/less/animated.less'/>" media="all">

<!--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/DataTables/datatables.min.css"/>-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap413/bootstrap.min.css" media="all"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilo.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/scripts/DataTables/datatables.min.css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/DataTables/datatables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/bootstrap413/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/extension/melanbide_interop/gestionResultadosIntegracionNISAE.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/general.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/popup.js"></script>
<div class="contenidoPantalla">
    <jsp:include page="/jsp/hidepage.jsp" flush="true">
        <jsp:param name='cargaDatos' value='<%=descriptor.getDescripcion("msjCargDatos")%>'/>
    </jsp:include>
    <div style="padding-left: 15px; text-align: center;" class="txttitblanco"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.titulo.principal")%></div>
    <div  class="container align-self-center" style="width: 40%; margin-top:30px;">
            <!--EJERCICIO-->
            <div class="form-group">
                <label for="ejercicio"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.ejercicio")%></label>
                <input type="text" maxlength="4" max="4" width="6em" class="form-control" id="ejercicio" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.ejercicio")%>"  placeholder="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.ejercicio.placeholder")%>" 
                       onkeypress="return numeroEntero(event);">
            </div>
            <!--PROCEDIMIENTO-->
            <div class="form-group">
                <label for="listaProcedimiento"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.procedimiento")%></label>
                <select class="selectpicker form-control" data-actions-box="true" name="listaProcedimiento" id="listaProcedimiento" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.procedimiento")%>">
                    <option value=""><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.select.option.default")%></option>
                    <c:forEach items="${listaProcedimiento}" var="elementLista" varStatus="contador">
                        <option value="<c:out value="${elementLista.id}"/>" title="<c:out value="${elementLista.valor}"/>"><c:out value="${elementLista.id}"/></option>
                    </c:forEach>
                </select>
            </div>
            <!--SERVICIOS-->
            <div class="form-group">
                            <label for="listaWebServices"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"label.servicio.web")%></label>
                            <select class="selectpicker form-control" data-actions-box="true" name="listaWebServices" id="listaWebServices" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario,"label.servicio.web")%>" onChange="onChangeCVL()">
                                <option value=""><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.select.option.default")%></option>
                                <c:forEach items="${listaWebServices}" var="elementLista" varStatus="contador">
                                    <option value="<c:out value="${elementLista.id}"/>" title="<c:out value="${elementLista.valor}"/>"><c:out value="${elementLista.valor}"/></option>
                                </c:forEach>
                            </select>
                        </div>
            <!--ESTADOEXPEDIENTE-->
            <div class="form-group">
                <label style="width: 140px;" for="estadoExpediente"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.estadoexpediente")%></label>
                <input type="text" size="45" class="form-control" id="estadoExpediente" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.estadoexpediente")%>"  placeholder="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.estadoexpediente.placeholder")%>" >
            </div>
            <!--NUMEROEXPEDIENTEDESDE / NUMEROEXPEDIENTEHASTA-->
            <div class="form-group">
                <label for="numeroExpedienteDesde"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente.desde")%></label>
                <input type="text" maxlength="6" max="6" width="6em" class="form-control" id="numeroExpedienteDesde" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente.desde")%>"  placeholder="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente.placeholder")%>" 
                       onkeypress="return numeroEntero(event);">
                <label for="numeroExpedienteHasta"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente.hasta")%></label>
                <input type="text" maxlength="6" max="6" width="6em" class="form-control" id="numeroExpedienteHasta" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente.hasta")%>"  placeholder="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "label.numeroexpediente.placeholder")%>" 
                       onkeypress="return numeroEntero(event);">
            </div>
            <!--FECHAHORAENVIOPETICION-->
            <div class="form-group">
                <label style="width: 140px;" for="fechahoraenviopeticion"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.fechaenviopeticion")%></label>
                <input type="text" maxlength="10" size="45" class="form-control" id="fechahoraenviopeticion" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.fechaenviopeticion")%>"  placeholder="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.fechaenviopeticion.placeholder")%>" >
            </div>
            <!--ESTADO-->
            <div class="form-group">
                <label style="width: 140px;" for="estado"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.estado")%></label>
                <input type="text" maxlength="10" size="45" class="form-control" id="estado" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.estado")%>"  placeholder="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.estado.placeholder")%>" >
            </div>
            <!--RESULTADO-->
            <div class="form-group">
                <label style="width: 140px;" for="resultado"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.resultado")%></label>
                <input type="text" size="45" class="form-control" id="resultado" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.resultado")%>"  placeholder="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.resultado.placeholder")%>" >
            </div>
            <div class="form-group">
                <label for="documentoInteresado"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.documento")%></label>
                <input type="text" maxlength="15" max="15" width="6em" class="form-control" id="documentoInteresado" title="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.documento")%>"  placeholder="<%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.label.documento.placeholder")%>" 
                       >
            </div>
            <!--BOTON FILTRAR-->
            <button type="button" id="filtrar" class="btn btn-primary" onclick="lanzarProcesoFiltroTablaLog();"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.button.filtrar")%></button>
            <!--BOTON Exportar-->
            <button type="button" id="exportar" class="btn btn-primary" onclick="lanzarProcesoExportarTablaLog();"><%=meLanbideInteropI18n.getMensaje(idiomaUsuario, "resultado.button.exportar")%></button>            
    </div>         
    <div>
        <!--TABLA -->
        <div id="tablaLog" style="margin-top: 30px; margin-bottom: 100px; margin-left: 30px; margin-right: 30px; height: 250px;">
            <table class="xTabla compact tablaDatos" id="tableLog">
                <thead>
                    <tr role="row">
                        <th field="id" datatype="String" class="selectedUp" rowspan="1" colspna="1" >ID</th>
                        <th field="codOrganizacion" datatype="String"  class="selectedUp" rowspan="1" colspna="1" >Cod Organización</th>
                        <th field="ejercicioHHFF" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Ejercicio</th>
                        <th field="procedimientoHHFF" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Procedimiento</th>
                        <th field="estadoExpediente" datatype="String"  class="selectedUp" rowspan="1" colspna="1" >Estado Expediente</th>
                        <th field="numeroExpedienteDesde" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Numero Expediente Desde</th>
                        <th field="numeroExpedienteHasta" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Numero Expediente Hasta</th>
                        <th field="textoJsonDatosEnviados" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Datos Enviados</th>
                        <th field="numeroExpediente" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Numero Expediente</th>
                        <th field="fechaAhoraEnvioPeticion" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Fecha Envio</th>
                        <th field="codigoEstadoSecundario" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Codigo Estado Secundario</th>
                        <th field="estado" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Estado</th>
                        <th field="descripcionEstado" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Descripción Estado</th>
                        <th field="resultado" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Resultado</th>
                        <th field="textoJsonDatosRecibidos" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Datos Recibidos</th>
                        <th field="documentoInteresado" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Documento Interesado</th>
                        <th field="tiempoEstimadoRespuesta" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Tiempo Estimado Respuesta</th>
                        <th field="territorioHistorico" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Territorio Historico</th>
                        <th field="observaciones" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Observaciones</th>
                        <th field="idPeticionPadre" datatype="String" class="selectedUp" rowspan="1" colspna="1" >Id Petición Padre</th>
                        <th field="fkwsSolicitado" datatype="String" class="selectedUp" rowspan="1" colspna="1" >FKWS Solicitado</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lstInteropLlamadas}" var="elementLista" varStatus="contador">
                        <tr value="<c:out value="${elementLista.id}"/>">
                            <td align="left" valign="middle"><c:out value="${elementLista.id}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.codOrganizacion}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.ejercicioHHFF}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.procedimientoHHFF}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.estadoExpediente}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.numeroExpedienteDesde}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.numeroExpedienteHasta}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.textoJsonDatosEnviados}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.numeroExpediente}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.fechaHoraEnvioPeticion}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.codigoEstadoSecundario}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.estado}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.descripcionEstado}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.resultado}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.textoJsonDatosRecibidos}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.documentoInteresado}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.tiempoEstimadoRespuesta}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.territorioHistorico}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.observaciones}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.idPeticionPadre}" /></td>
                            <td align="left" valign="middle"><c:out value="${elementLista.fkWSSolicitado}" /></td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- Campos Ocultos con texto por idioma -->
        <input type="hidden" name="texto-buscar" id="texto-buscar" value="<%=descriptor.getDescripcion("buscar")%>">
        <input type="hidden" name="texto-siguiente" id="texto-siguiente" value="<%=descriptor.getDescripcion("siguiente")%>">
        <input type="hidden" name="texto-mosFilasPag" id="texto-mosFilasPag" value="<%=descriptor.getDescripcion("mosFilasPag")%>">
        <input type="hidden" name="texto-msgNoResultBusq" id="texto-msgNoResultBusq" value="<%=descriptor.getDescripcion("msgNoResultBusq")%>">
        <input type="hidden" name="texto-mosPagDePags" id="texto-mosPagDePags" value="<%=descriptor.getDescripcion("mosPagDePags")%>">
        <input type="hidden" name="texto-noRegDisp" id="texto-noRegDisp" value="<%=descriptor.getDescripcion("noRegDisp")%>">
        <input type="hidden" name="texto-filtrDeTotal" id="texto-filtrDeTotal" value="<%=descriptor.getDescripcion("filtrDeTotal")%>">
    </div>
</div>

