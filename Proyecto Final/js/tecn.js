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
            $("#cards").append('<div class="row"><div class="col-lg-3 col-md-3"></div></div>');

        };
        // puede que exitan varias columnas por lo que debemos averiguar
        // donde colocarla
        cols = parseInt(i/2);
        consulta = $("#cards .row");
        reporte = $('<div></div>');
        //$('<div class=" det col-lg-3"><form class="minireport"><img src="img/user-icon.png" class="img-responsive img-circle"><h4 class="text-center">Folio</h4><strong name="nombre">Nombre Cliente</strong><br><strong name="marca">Marca-Modelo</strong><p name="descripcion">Descripcion</p><button name ="go" type="button" class="btn btn-info btn-block">Detalles</button></form></div>').appendTo(consulta[cols]);
        $(reporte).load("html/cards.html #card");
        $(reporte).insertAfter($(consulta[cols]).find('.col-lg-3'));
    }

    $("#chat").css('display', 'none');

    setTimeout(function (){
        $('#card button[name="go"]').each(function(index, el) {
            console.log(el);
            $(el).click(function(event) {
                event.preventDefault();
                backup = $("#cards").detach();
                console.log("click");
                showReport();
            });
        });
        enlazarChat();
    },400);

    $('a[name="task"]').click(function() {
        if (backup) {
            $("#showcard").remove();
            $(backup).appendTo('body');
        };
    });
    $('a[name="reporte"]').click(function() {
        backup = $("#cards").detach();
        showReport();
        setTimeout(function  () {
            $('<div class="form-group"><label for="folio">Seleccionar Ticket</label><select multiple required class="form-control"></select></div>').insertAfter('#reporte #head');    
            str = "<option>"
            for(var i=0;i<Math.random()*20;i++){

                $(str.concat("Folio: ",parseInt(Math.random() * 1024),"</option>")).appendTo('select');
            }

        },400);
        $('form[name="reporte"').submit(function(event) {
            alert("Reporte Creado Correctamente");
        });
        
    });
});

function showReport () {
    $("#chat").css('display', 'none');

    $('<div id="showcard"></div>').appendTo('body');
    setTimeout(function  () {
        $("#showcard").load("html/cards.html #reporte");    
    },200);
    setTimeout(function  () {
        $('form[name="reporte"]').submit(function() {
            alert("Reporte Modificado Correctamente")
        });
    }, 400);
    $("#chat").css('display', 'none');
    
}

function posicionarChat () {
     // obtener las dimensiones actuales de la ventana
     var alturaWindow = $(window).height();
     var anchoWindow = $(window).width();

    // obtener el ancho del elemento
    var heightChat = $("#info-chat").height() + $("#mensajes").height() + $("#entradaMsj").height();
    var widthChat = $("#chat").width();      

    // obtener las posiciones adecuadas
    var top = alturaWindow - heightChat;
    var widthChat = anchoWindow - widthChat;

    $("#chat").css({
        "top": top,
        "left": widthChat,
    });
    console.log( top + " " + widthChat);
    setTimeout(function  () {
        
        $("#info-chat a").click(function(event) {
            event.preventDefault();
            $("#chat").slideUp('400');
        });
    }, 400)
}

function enlazarChat () {
    $('#card button[name="chat-button"').each(function(index, el) {
        
        $(el).click(function(event) {
            console.log('chat');
            $("#chat").show();
            posicionarChat();
            $("#chat").slideDown(400);
            $(window).resize(function(event) {
                posicionarChat();
            });
        });
    });
}