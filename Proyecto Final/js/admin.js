jQuery(document).ready(function($) {
    // eligir una cantidad aleatoria de reportes
    var cantidadReportes = parseInt(Math.random() * 5) +1;
    var cols;
    var consulta;
    var reporte;
    var backup;

    for(var i = 0;i<=cantidadReportes;i++){
        if (i % 2 == 0) {
            // si ya no cabe en alguna columna crearla
            $("#reportes").append('<div class="row"><div class="col-lg-3"></div></div>');

        };
        // puede que exitan varias columnas por lo que debemos averiguar
        // donde colocarla
        cols = parseInt(i/2);
        consulta = $("#reportes .row");
        reporte = $('<div></div>');
        $(reporte).load("html/reporte.html #minireport");
        $(reporte).insertAfter($(consulta[cols]).find('.col-lg-3'));
    }

    setTimeout(function (){
        $('.minireport button[name="go"]').each(function(index, el) {
            console.log(el);
            $(el).click(function(event) {
                event.preventDefault();
                backup = $("#reportes").detach();
                console.log("click");
                showReport();
            });
        });
    },400);
    $('a[name="reporte"]').click(function(event) {
        event.preventDefault();
        if (backup) {
            $("#showreport").remove();
            $(backup).appendTo('body');
        };
    });
    $('a[name=asignar]').click(function(event) {
        event.preventDefault();
        backup = $("#reportes").detach();
        $('<div id="showreport"></div>').appendTo('body');

        $('#showreport').load("html/reporte.html #asignaciones" );

        var nombre;
        var tareas;
        setTimeout(function () {
            for(var i = 1;i<= Math.random() * 5 +1;i++ ){
                th = "<tr><th>Tecnico "
                $("#asignaciones tbody").append(th.concat(i,"</th>","<th>",parseInt(Math.random() * 20 +1),"</th>","</tr>"))
                nombre = "Tecnico" + i;
                console.log(i);
            }
        },200)
    });

});

function showReport () {
    $('<div id="showreport"></div>').appendTo('body');
    setTimeout(function  () {
        $("#showreport").load("html/reporte.html #detalle");    
    },200);
}
