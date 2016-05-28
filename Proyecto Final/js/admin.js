jQuery(document).ready(function($) {
    // eligir una cantidad aleatoria de reportes
    var cantidadReportes = parseInt(Math.random() * 5) +1;
    var cols;
    var consulta;


    for(var i = 0;i<=cantidadReportes;i++){
        if (i % 2 == 0) {
            // si ya no cabe en alguna columna crearla
            $("#reportes").append('<div class="row"><div class="col-lg-3"></div></div>');

        };
        // puede que exitan varias columnas por lo que debemos averiguar
        // donde colocarla
        cols = parseInt(i/2);
        consulta = $("#reportes .row");
        $('<div class=" det col-lg-3"><form class="minireport"><img src="img/user-icon.png" class="img-responsive img-circle"><h4 class="text-center">Folio</h4><strong name="nombre">Nombre Cliente</strong><br><strong name="marca">Marca-Modelo</strong><p name="descripcion">Descripcion</p><button type="button" class="btn btn-info">Detalles</button></form></div>').appendTo(consulta[cols]);
    }

});