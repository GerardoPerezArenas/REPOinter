/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    asignarManejadoresEventos();
    if($("#mensajeInicial").val()!==null && $("#mensajeInicial").val()!== undefined && $("#mensajeInicial").val()!==""
            && $("#mensajeInicial").val()!=="null"){
        mostrarModalGestionMensajeOperacion($("#mensajeInicial").val());
    }
    $('#divListaFiltroExpedientesExpecificosBotonera').hide();
    $('#ejercicio').prop("disabled", "true");
    $('#listaProcedimiento').prop("disabled", "true");
    $('#rangoExpedientes').prop("disabled", "true");
   

});
var parametrosLLamada ={
        tarea:'preparar'
        ,modulo:'MELANBIDE_INTEROP'
        ,operacion:null
        ,tipo:0
        ,control:new Date().getTime()
        ,ejercicio:null
        ,procedimiento:null
        ,numeroExpediente:null
        ,numeroExpedienteDesde:null
        ,numeroExpedienteHasta:null
        ,jsonParametrosLLamada:null
    };


function asignarManejadoresEventos(){
    
//    $('#modalGestionOperacionCargaMEXML').on('hidden.bs.modal', function (e) {
//        ("#textoBodyModal").prop("text","");
//    });
    $('#listaWebServices').on('change', function (e) {
        if ($("#listaWebServices").val() === "2") {
            $('#soloPeticionesEstEnProceso').removeAttr("checked");
            $('#soloPeticionesEstEnProceso').prop("disabled", "true");
        } else {
            $('#soloPeticionesEstEnProceso').removeAttr("disabled");
        }         
    });
    
    //Mostar Ocultar Options para AERTE
    $("#listaProcedimiento").on('change', function(e) {
       if($("#listaProcedimiento").val() == "AERTE"){
           $("#listaWebServices option[value=1]").hide();
           $("#listaWebServices option[value=2]").hide();
           $("#listaWebServices option[value=3]").show();
       }else{
           $("#listaWebServices option[value=3]").hide();
           $("#listaWebServices option[value=1]").show();
           $("#listaWebServices option[value=2]").show();
       } 
    });
    
    $('#rangoExpedientes').on('click', function (e) {
        if ($("#rangoExpedientes:checked").length > 0) {
            $('#divRangoExpedientes').slideDown();
            $("#numeroExpedienteDesde").removeAttr("disabled");
            $("#numeroExpedienteHasta").removeAttr("disabled");
        } else {
            $('#divListaFiltroExpedientesExpecificosBotonera').hide();
            $("#numeroExpedienteDesde").val("")
            $("#numeroExpedienteHasta").val("");
            $("#numeroExpedienteDesde").prop("disabled", "true");
            $("#numeroExpedienteHasta").prop("disabled", "true");
        }         
    });
    /*
    $('#ejecutarFiltroExpedientesEspecificosCargarLista').on('click', function (e) {
        if ($("#ejecutarFiltroExpedientesEspecificosCargarLista:checked").length > 0) {
            $('#divListaFiltroExpedientesExpecificos').slideDown();
        } else {
            $('#ejecutarFiltroExpedientesEsupecificosCargarLista').removeAttr("checked");
            $('#divListaFiltroExpedientesExpecificos').hide();
        }         
    });
    */
    
}

function upperCasetext(idElemento){
    if($("#"+idElemento).val()!== null && $("#"+idElemento).val()!== ""){
        var texto="";
        texto=$("#"+idElemento).val();
        $("#"+idElemento).val(texto.toUpperCase());
    }
}

//esta funcion solo deja ingresar numeros enteros
function numeroEntero(e) {
    var caracter;
    caracter = e.keyCode;
    status = caracter;
    if (caracter > 47 && caracter < 58) {
        return true;
    }
    return false;
}

function soloTexto(e) {
    var caracter;
    caracter = e.keyCode;
    status = caracter;
    if ((caracter > 64 && caracter < 91) //Letras
        ||(caracter > 7 && caracter < 10) // Bksp,Tab
        ||(caracter > 36 && caracter < 41) //Arrows
        ||(caracter === 46) //delete
        ) {
        return true;
    }
    return false;
}

function lanzarProcesoComprobacionDatosPreview(){
    if(validarParemetrosObligatorios()){
        var mensaje= "Seguro de ejecutar estar operacion? recuerde que se sobre escribiran los datos existentes con los definido en el XML de la Entrada de Registro Telematica asociada como creacion al Expediente (EXR_TOP=0 Tabla E_EXR).";
        mostrarModalGestionMensajeOperacion(mensaje,true);
        //getExpedientesProcesarNISAENumeroTotalProcesar();
    }
}
function lanzarProcesoComprobacionDatos(){
    $("#modalGestionOperacionCargaMEXML").modal('hide');
    var dataParameter = $.extend({},parametrosLLamada);
    dataParameter.control=new Date().getTime();
    dataParameter.ejercicio=($("#ejercicio").val()!== undefined && $("#ejercicio").val()!== "" ? $("#ejercicio").val() : null);
    dataParameter.procedimiento=($("#listaProcedimiento").val()!== undefined && $("#listaProcedimiento").val()!== "" ? $("#listaProcedimiento").val() : null);
    dataParameter.numeroExpediente=($("#numeroExpediente").val()!== undefined && $("#numeroExpediente").val()!== "" ? $("#numeroExpediente").val() : null);
    dataParameter.numeroExpedienteDesde=($("#numeroExpedienteDesde").val()!== undefined && $("#numeroExpedienteDesde").val()!== "" ? $("#numeroExpedienteDesde").val() : null);
    dataParameter.numeroExpedienteHasta=($("#numeroExpedienteHasta").val()!== undefined && $("#numeroExpedienteHasta").val()!== "" ? $("#numeroExpedienteHasta").val() : null);
    dataParameter.operacion='cargarDatosMEFromXMlEntradaRegistroTelematica';
    dataParameter.jsonParametrosLLamada=JSON.stringify(dataParameter);
    var urlBaseLlamada=APP_CONTEXT_PATH+"/PeticionModuloIntegracion.do";
    $.ajax({
        type: 'POST',
        url: urlBaseLlamada,
        data: dataParameter,
        success: function(respuesta){             
                      if(respuesta!==null){
                         //mostrarModalGestionMensajeOperacion(respuesta);
                         jsp_alerta_Grande("A",respuesta);
                      }
          },
       //dataType: dataType,
          error:function(jqXHR,textStatus,errorThrown ){
              var mensajeError='Error lanzar el proceso  ' + jqXHR + ' - ' +textStatus + '  ' + errorThrown ;
              mostrarModalGestionMensajeOperacion(mensajeError);
          },
        async:true
    });
}

function validarParemetrosObligatorios(){
    $("#textoValidacionCampos").text("");
    if($("#rangoExpedientes:checked").length > 0){
        if ($("#ejercicio").val() === "" || $("#listaProcedimiento").val() === ""
                || $("#numeroExpedienteDesde").val() === "" || $("#numeroExpedienteHasta").val() === "") {
            var textoMensaje = $("#mensajeBaseCamposOblgatorios").val();
            if ($("#ejercicio").val() === "")
                textoMensaje = textoMensaje + " " + $("#ejercicio").prop("title") + ".";
            if ($("#listaProcedimiento").val() === "")
                textoMensaje = textoMensaje + " " + $("#listaProcedimiento").prop("title") + ".";
            if ($("#numeroExpedienteDesde").val() === "")
                textoMensaje = textoMensaje + " " + $("#numeroExpedienteDesde").prop("title") + ".";
            if ($("#numeroExpedienteHasta").val() === "")
                textoMensaje = textoMensaje + " " + $("#numeroExpedienteHasta").prop("title") + ".";
            $("#textoValidacionCampos").text(textoMensaje);
            $('#textoValidacionCampos').slideDown();
            return false;
        } else if ($("#numeroExpedienteDesde").val() !== "" && $("#numeroExpedienteHasta").val() !== "") {
            if (parseInt($("#numeroExpedienteHasta").val()) < parseInt($("#numeroExpedienteDesde").val())) {
                $("#textoValidacionCampos").text("Rango expedientes incorrecto. Expediente Hasta menor que Expediente desde.");
                $('#textoValidacionCampos').slideDown();
                return false;
            }
        }        
    }else{
        var textoMensaje = $("#mensajeBaseCamposOblgatorios").val();
        if ($("#numeroExpediente").val() === ""){
            textoMensaje = textoMensaje + " " + $("#ejercicio").prop("title") + ".";
            $("#textoValidacionCampos").text(textoMensaje);
            $('#textoValidacionCampos').slideDown();
            return false
        }
    }
    $('#textoValidacionCampos').hide();
    return true;
}

function comprobarVerTextoValidacionCampos(){
    if(($("#rangoExpedientes:checked").length > 0 && $("#ejercicio").val()!=="" && $("#procedimiento").val()!==""
            && $("#numeroExpedienteDesde").val() !== "" && $("#numeroExpedienteHasta").val() !== "")
            ||($("#numeroExpediente").val() !== "")
            ){
        $('#textoValidacionCampos').text(""); 
        $('#textoValidacionCampos').hide();
    }
}

function mostrarModalGestionMensajeOperacion(textoMostrar,mostrarEjecutar,textoHTMLMostrar){
    
    if($("#textoBodyModal")!==null && $("#textoBodyModal")!==undefined){
        // Limpiamos mensajes Previos
        $("#textoBodyModal").empty();
        $("#textoBodyModal").text(textoMostrar);
        if(textoHTMLMostrar!=null && textoHTMLMostrar!=undefined){
             if( $("#textoBodyModal").text()!="")
                $("#textoBodyModal").append(textoHTMLMostrar)
            else
                $("#textoBodyModal").prop("innerHTML",textoHTMLMostrar);
        }
    }
    if($("#modalAceptarLanzarProceso")!==null && $("#modalAceptarLanzarProceso")!==undefined){
        if (mostrarEjecutar != null && mostrarEjecutar != undefined && mostrarEjecutar) {
            $("#modalAceptarLanzarProceso").removeProp("disabled");
        } else {
            $("#modalAceptarLanzarProceso").prop("disabled", "true");
        }
    }
    
    $("#modalGestionOperacionCargaMEXML").modal('show');
}

function mostrarListaExpedientesEspecificos(){
    var dataParameter = $.extend({}, parametrosLLamada);
    dataParameter.control = new Date().getTime();
    dataParameter.operacion = 'getListaFiltroExpedientesEspecificos';
    var urlBaseLlamada = APP_CONTEXT_PATH + "/PeticionModuloIntegracion.do";
    $.ajax({
        type: 'POST',
        url: urlBaseLlamada,
        data: dataParameter,
        success: function (respuesta) {
            var htmlTex = "";
            if (respuesta !== null && "" !== respuesta && respuesta.length > 0) {
                var htmlTex = "<ul>";
                for (var i = 0; i<respuesta.length; i++) {
                    htmlTex += "<li>" + respuesta[i].numeroExpediente + "</li>";
                }
                htmlTex += "</ul>";

            } else {
                htmlTex = "No se han registrado expedientes en la tabla INTEROP_SERVICIOS_NISAE_EXP_FI para el filtro Expedientes especifios.";
            }
            mostrarModalGestionMensajeOperacion("", false, htmlTex);
        },
        //dataType: dataType,
        error: function (jqXHR, textStatus, errorThrown) {
            var mensajeError = 'Error lanzar el proceso de comprobacion ' + jqXHR + ' - ' + textStatus + '  ' + errorThrown;
            mostrarModalGestionMensajeOperacion(mensajeError);
        },
        async: false
    });
}

function cargarListaExpedientesEspecificos(){
    var dataParameter = $.extend({}, parametrosLLamada);
    dataParameter.control = new Date().getTime();
    dataParameter.operacion = 'cargarListaFiltroExpedientesEspecificos';
    dataParameter.jsonListaExpedientes=$("#listaFiltroExpedientesExpecificos").val();
    var urlBaseLlamada = APP_CONTEXT_PATH + "/PeticionModuloIntegracion.do";
    $.ajax({
        type: 'POST',
        url: urlBaseLlamada,
        data: dataParameter,
        success: function (respuesta) {
            if (respuesta !== null) {
                mostrarModalGestionMensajeOperacion(respuesta, false);
            }
        },
        //dataType: dataType,
        error: function (jqXHR, textStatus, errorThrown) {
            var mensajeError = 'Error lanzar el proceso de comprobacion ' + jqXHR + ' - ' + textStatus + '  ' + errorThrown;
            mostrarModalGestionMensajeOperacion(mensajeError);
        },
        async: false
    });
}
/*
function comprobarEstadoClaseBlodyControlScroll(){
    if($('.modal.in').length > 0 && $('body').prop("class").indexOf("modal-open")<0 )
    {
        $('body').addClass('modal-open');
    }
}
*/

/*function getExpedientesProcesarNISAENumeroTotalProcesar(){
    $("#modalGestionOperacionCargaMEXML").modal('hide');
    var dataParameter = $.extend({}, parametrosLLamada);
    dataParameter.control = new Date().getTime();
    dataParameter.ejercicioHHFF = $("#ejercicio").val();
    dataParameter.procedimientoHHFF = $("#listaProcedimiento").val();
    dataParameter.estadoexpediente = $('[name="filtro"]:checked').data("estadoexpediente");
    dataParameter.numeroExpedienteDesde = $("#numeroExpedienteDesde").val();
    dataParameter.numeroExpedienteHasta = $("#numeroExpedienteHasta").val();
    dataParameter.soloPeticionesEstEnProceso = ($("#soloPeticionesEstEnProceso:checked").length > 0 ? "1" : "0");
    dataParameter.fkWSSolicitado = $("#listaWebServices").val(); // Tabla INTEROP_SERVICIOS_NISAE cod 1:HHFF, 2:TGSS, 3:CP (Padron)
    dataParameter.ejecutarFiltroExpedientesEspecificos = ($("#ejecutarFiltroExpedientesEspecificos:checked").length > 0 ? "1" : "0");
    dataParameter.operacion = 'getExpedientesProcesarNISAENumeroTotalProcesar';

    var urlBaseLlamada = APP_CONTEXT_PATH + "/PeticionModuloIntegracion.do";
    $.ajax({
        type: 'POST',
        url: urlBaseLlamada,
        data: dataParameter,
        success: function (respuesta) {
            if (respuesta !== null && respuesta.length==2) {
                var mensajeString = respuesta[0];
                var mensajeHtml = '<br/><span class="badge badge-warning" id="expedientesProcesar">'+respuesta[1]+'</span>';
                mostrarModalGestionMensajeOperacion(mensajeString,true,mensajeHtml);
            }else{
                if (respuesta !== null){
                    mostrarModalGestionMensajeOperacion(respuesta,false);
                }else{
                    mostrarModalGestionMensajeOperacion("No se ha poodido recuperar el numero de Expedienes a procesar, operacion cancelada, consulte con el Adminstrador para ,mas detalles.",false);
                }
            }
        },
        //dataType: dataType,
        error: function (jqXHR, textStatus, errorThrown) {
            var mensajeError = 'Error lanzar el proceso de comprobacion ' + jqXHR + ' - ' + textStatus + '  ' + errorThrown;
            mostrarModalGestionMensajeOperacion(mensajeError,false);
        },
        async: true
    });
}
*/