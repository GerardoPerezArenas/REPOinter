   
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var parametrosLlamada = {
        tarea: 'preparar'
        , modulo: 'MELANBIDE_INTEROP'
        , operacion: null
        , tipo: 0
        , control: new Date().getTime()
        , ejercicioHHFF: null
        , procedimientoHHFF: null
        , estadoExpediente: null
        , numeroExpedienteDesde: null
        , numeroExpedienteHasta: null
        , fechaEnvioPeticion: null
        , estado: null
        , resultado: null
        , documentoInteresado: null
    };
 var urlBaseLlamada = APP_CONTEXT_PATH + "/PeticionModuloIntegracion.do";
 var tableLog;   
    
$(document).ready(function() {
    
    var datosParameter = $.extend({}, parametrosLlamada);
    datosParameter.operacion = "cargarPantallaLogServiciosNISAEFiltros";
    tableLog = $('#tableLog').DataTable({
        "serverSide": true,
        "ordering": false,
        "autoWidth": false,
        "scrollX": true,
        "scrollCollapse": true,
        "searching": false,
        "language": {
            "search": "",
            "paginate": {
                "previous": $("#texto-anterior").val(),
                "next": $("#texto-siguiente").val()
            },
            "lengthMenu": $("#texto-mosFilasPag").val(),
            "zeroRecords": $("#texto-msgNoResultBusq").val(),
            "info": $("#texto-mosPagDePags").val(),
            "infoEmpty": $("#texto-noRegDisp").val(),
            "infoFiltered": $("#texto-filtrDeTotal").val()
        },
        "ajax": {
            "url": urlBaseLlamada,
            "data": datosParameter,
            "method": "POST",
            "beforeSend": function () {
                pleaseWait('on');
            },
            "dataSrc": function (response) {
                var data = response.lstRegistros;
                if (data !== null) {
                    var all = [];
                    for (var i = 0; i < data.length; i++) {
                        var row = {
                            id: data[i].id,
                            codOrganizacion: data[i].codOrganizacion,
                            ejercicioHHFF: data[i].ejercicioHHFF,
                            procedimientoHHFF: data[i].procedimientoHHFF,
                            estadoExpediente: data[i].estadoExpediente,
                            numeroExpedienteDesde: data[i].numeroExpedienteDesde,
                            numeroExpedienteHasta: data[i].numeroExpedienteHasta,
                            textoJsonDatosEnviados: data[i].textoJsonDatosEnviados,
                            numeroExpediente: data[i].numeroExpediente,
                            fechaHoraEnvioPeticion: data[i].fechaHoraEnvioPeticion,
                            codigoEstadoSecundario: data[i].codigoEstadoSecundario,
                            estado: data[i].estado,
                            descripcionEstado: data[i].descripcionEstado,
                            resultado: data[i].resultado,
                            textoJsonDatosRecibidos: data[i].textoJsonDatosRecibidos,
                            documentoInteresado: data[i].documentoInteresado,
                            tiempoEstimadoRespuesta: data[i].tiempoEstimadoRespuesta,
                            territorioHistorico: data[i].territorioHistorico,
                            observaciones: data[i].observaciones,
                            idPeticionPadre: data[i].idPeticionPadre,
                            fkWSSolicitado: data[i].fkWSSolicitado
                        };
                        all.push(row);
                    }
                    pleaseWait('off');
                    return all;
                }
            }
        },
        "columns": [
            {"data": "id"},
            {"data": "codOrganizacion"},
            {"data": "ejercicioHHFF"},
            {"data": "procedimientoHHFF"},
            {"data": "estadoExpediente"},
            {"data": "numeroExpedienteDesde"},
            {"data": "numeroExpedienteHasta"},
            {"data": "textoJsonDatosEnviados"},
            {"data": "numeroExpediente"},
            {"data": "fechaHoraEnvioPeticion"},
            {"data": "codigoEstadoSecundario"},
            {"data": "estado"},
            {"data": "descripcionEstado"},
            {"data": "resultado"},
            {"data": "textoJsonDatosRecibidos"},
            {"data": "documentoInteresado"},
            {"data": "tiempoEstimadoRespuesta"},
            {"data": "territorioHistorico"},
            {"data": "observaciones"},
            {"data": "idPeticionPadre"},
            {"data": "fkWSSolicitado"}
        ]
    });
});

//Function filtrar
function lanzarProcesoFiltroTablaLog() {
    var datosParameter = $.extend({}, parametrosLlamada);
    datosParameter.control = new Date().getTime();
    datosParameter.ejercicioHHFF = $("#ejercicio").val();
    datosParameter.procedimientoHHFF = $("#listaProcedimiento").val();
    datosParameter.estadoExpediente = $("#estadoExpediente").val();
    datosParameter.numeroExpedienteDesde = $("#numeroExpedienteDesde").val();
    datosParameter.numeroExpedienteHasta = $("#numeroExpedienteHasta").val();
    datosParameter.fechaEnvioPeticion = $("#fechahoraenviopeticion").val();
    datosParameter.estado = $("#estado").val();
    datosParameter.resultado = $("#resultado").val();
    datosParameter.documentoInteresado = $("#documentoInteresado").val();
    if (datosParameter.fechaEnvioPeticion === "" || validarFecha(datosParameter.fechaEnvioPeticion)) {

        tableLog.destroy();
        datosParameter.operacion = "cargarPantallaLogServiciosNISAEFiltros";
        tableLog = $('#tableLog').DataTable({
            "serverSide": true,
            "ordering": false,
            "autoWidth": false,
            "scrollX": true,
            "scrollCollapse": true,
            "searching": false,
            "language": {
                "paginate": {
                    "previous": $("#texto-anterior").val(),
                    "next": $("#texto-siguiente").val()
                },
                "lengthMenu": $("#texto-mosFilasPag").val(),
                "zeroRecords": $("#texto-msgNoResultBusq").val(),
                "info": $("#texto-mosPagDePags").val(),
                "infoEmpty": $("#texto-noRegDisp").val(),
                "infoFiltered": $("#texto-filtrDeTotal").val()
            },
            "ajax": {
                "url": urlBaseLlamada,
                "data": datosParameter,
                "method": "POST",
                "beforeSend": function () {
                    pleaseWait('on');
                },
                "dataSrc": function (response) {
                    var data = response.lstRegistros;
                    if (data !== null) {
                        var all = [];
                        for (var i = 0; i < data.length; i++) {
                            var row = {
                                id: data[i].id,
                                codOrganizacion: data[i].codOrganizacion,
                                ejercicioHHFF: data[i].ejercicioHHFF,
                                procedimientoHHFF: data[i].procedimientoHHFF,
                                estadoExpediente: data[i].estadoExpediente,
                                numeroExpedienteDesde: data[i].numeroExpedienteDesde,
                                numeroExpedienteHasta: data[i].numeroExpedienteHasta,
                                textoJsonDatosEnviados: data[i].textoJsonDatosEnviados,
                                numeroExpediente: data[i].numeroExpediente,
                                fechaHoraEnvioPeticion: data[i].fechaHoraEnvioPeticion,
                                codigoEstadoSecundario: data[i].codigoEstadoSecundario,
                                estado: data[i].estado,
                                descripcionEstado: data[i].descripcionEstado,
                                resultado: data[i].resultado,
                                textoJsonDatosRecibidos: data[i].textoJsonDatosRecibidos,
                                documentoInteresado: data[i].documentoInteresado,
                                tiempoEstimadoRespuesta: data[i].tiempoEstimadoRespuesta,
                                territorioHistorico: data[i].territorioHistorico,
                                observaciones: data[i].observaciones,
                                idPeticionPadre: data[i].idPeticionPadre,
                                fkWSSolicitado: data[i].fkWSSolicitado
                            };
                            all.push(row);
                        }
                        pleaseWait('off');
                        return all;
                    }
                }
            },
            "columns": [
                {"data": "id"},
                {"data": "codOrganizacion"},
                {"data": "ejercicioHHFF"},
                {"data": "procedimientoHHFF"},
                {"data": "estadoExpediente"},
                {"data": "numeroExpedienteDesde"},
                {"data": "numeroExpedienteHasta"},
                {"data": "textoJsonDatosEnviados"},
                {"data": "numeroExpediente"},
                {"data": "fechaHoraEnvioPeticion"},
                {"data": "codigoEstadoSecundario"},
                {"data": "estado"},
                {"data": "descripcionEstado"},
                {"data": "resultado"},
                {"data": "textoJsonDatosRecibidos"},
                {"data": "documentoInteresado"},
                {"data": "tiempoEstimadoRespuesta"},
                {"data": "territorioHistorico"},
                {"data": "observaciones"},
                {"data": "idPeticionPadre"},
                {"data": "fkWSSolicitado"}
            ]
        });
    } else {
        alert("Fecha con formato no valido");
    }
}

//Function exportar
function lanzarProcesoExportarTablaLog() {
    pleaseWait('on');
    var datosParameter = $.extend({}, parametrosLlamada);
    datosParameter.operacion = "exportarLogServiciosNISAEFiltros";
    datosParameter.control = new Date().getTime();
    datosParameter.ejercicioHHFF = $("#ejercicio").val();
    datosParameter.procedimientoHHFF = $("#listaProcedimiento").val();
    datosParameter.estadoExpediente = $("#estadoExpediente").val();
    datosParameter.numeroExpedienteDesde = $("#numeroExpedienteDesde").val();
    datosParameter.numeroExpedienteHasta = $("#numeroExpedienteHasta").val();
    datosParameter.fechaEnvioPeticion = $("#fechahoraenviopeticion").val();
    datosParameter.estado = $("#estado").val();
    datosParameter.resultado = $("#resultado").val();
    datosParameter.documentoInteresado = $("#documentoInteresado").val();
    if (datosParameter.fechaEnvioPeticion === "" || validarFecha(datosParameter.fechaEnvioPeticion)) {
        window.location.href = urlBaseLlamada +  "?" + $.param(datosParameter);
    } else {
        alert("Formato de fecha no valido");
    }
    pleaseWait('off');
}

//Funcion validar fecha
function validarFecha(fecha) {
    return fecha.match(/^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/);
}

function numeroEntero(e) {
    var caracter;
    caracter = e.keyCode;
    status = caracter;
    if (caracter > 47 && caracter < 58) {
        return true;
    }
    return false;
}

