jQuery(document).ready(function($) {
    var html;
    var ticket;
    var filial;
    // codigos de filial
    var codigos = ['AB123-23','DF123-43','TF234-54','RF142-54'];
    // Llenamos los tickets
    for (var i = 0; i < 10; i++) {
        ticket = parseInt(Math.random() * 100);
        filial = codigos[parseInt(Math.random()*4)];

        html = '<option value=' + filial +"'>"+filial+"-----"+ticket+"</option>"

        console.log(html);

        $(html).appendTo('form[name=listaTickets] select[name=tickets]'); 
    };

    // De principio ocultamos el panel detalles
    $('#detallesTicket').css('display', 'none');

    // obtenemos el valor de la opcion seleccionada
    var select;
    $("form[name=listaTickets] input[type=button]").click(function(event) {
        select = $("form[name=listaTickets] option:selected").text()
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
    });


// al presionar enviar redirigir a asignar.html
    $('input[name=asignar]').click(function(event) {
        window.location="asignar.html"
    });



});