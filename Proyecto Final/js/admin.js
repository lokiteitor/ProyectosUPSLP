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
    $('a[name="reporte"]').click(function() {
        if (backup) {
            $("#showreport").remove();
            $(backup).appendTo('body');
        };
    });

});

function showReport () {
    $('<div id="showreport"></div>').appendTo('body');
    setTimeout(function  () {
        $("#showreport").load("html/reporte.html #detalle");    
    },200);
    

}