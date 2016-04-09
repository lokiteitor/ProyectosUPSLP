jQuery(document).ready(function($) {
    





    var codigos = ['AB123-23','DF123-43','TF234-54','RF142-54'];

    var select = codigos[parseInt(Math.random() * 4)];

    console.log(select);

    // Llenamos los datos de detalles
    var f = new Date();
    var fecha = f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear();
    console.log(fecha)
    var hora = f.getHours() + ":" + f.getMinutes();

    $("var[name=fecha]").text(fecha)
    $("var[name=hora]").text(hora)
    $("code[name=filial]").text(select.split('-')[0])

    $('#detallesTicket').css('display', 'block');

    $('input[name=asignar]').click(function(event) {
        alert("Equipo Asignado");
    });

    // generar un identificador
    $("input[name=id]").val(getAlmancen());
    $("input[name=lugar]").val(getAlmancen());
    $("input[name=modelo]").val(getAlmancen());


});