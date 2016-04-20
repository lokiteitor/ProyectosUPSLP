jQuery(document).ready(function($) {
    $("input[value=Atender]").click(function(event) {
        event.preventDefault();
        $("#formulario").hide('300', function() {
        $("#historialMedico").load("html/citas.html");
        $("#historialMedico").hide();
        $("#historialMedico").fadeIn(500, function() {
            showForm();
        });
        });
    });



});


function showForm () {
    setTimeout(function () {
        var hgt = $(".baseFloat").height() + 50;
        $("#clientWorkSpace").height(hgt);
        $(".baseFloat").css({
            'position': 'relative',
            'top':'0',
            'left':'0',
            'margin-left':'15px'
        });
        // asignar aleatoriramente los datos
        var query;
        var qval;
        for (var i = 1; i <= 16; i++) {
            query = "input[name=question"+i+"]";
            qval = "input[value="+getRandomValue()+"]";
            $(query).filter(qval).attr({
                'checked':"checked",
                'readonly': 'readonly',
            });

        };  
        $(".baseFloat input[type=button]").val("Atender");
    }, 200);

}


function getRandomValue () {
    var val = parseInt(Math.random() * 2);
    var value;
    if (val == 1) {
        value = "si";
    }
    else{
        value = "no";
    }
    return value;
}
var flagRecet = false;


function sendForm () {
    $("#streaming").load("html/consultorio.html")
    $("#streaming").hide();
    $(window).scrollTop(0);
    $("#streaming").fadeIn(1000);

    $(".baseFloat input[type=button]").hide();


    setTimeout(function () {

        $("#workarea input").click(function(event) {
            event.preventDefault();
            $("#streaming").hide('400', function() {
                $("#streaming").html("");
            });
            setTimeout(function  () {
                $("<a href=doctor.html><input type=button value='Ir a menu' /></a>").appendTo('#streaming');
                $("#streaming").show('400');
            }, 600);
            
        });



        $('#individualMenu li').click(function(event) {
            event.preventDefault();
            if ($(this).attr('name') == 'receta' && flagRecet==false) {
                showReceta();
            };
            if ($(this).attr('name') == 'diagnostico' && flagRecet==false) {
                $(window).scrollTop($("#cuestionario").position()['top'])
            };

    });

    }, 200);
}


function showReceta () {
    // mostrar formualario para llenar la receta
    $("#menuAction").load('html/actionMenu.html #receta');
    $("#menuAction").hide();
    $("#menuAction ").slideDown(10);

    flagRecet = true;
    setTimeout(function  () {
        $("#menuAction #receta").css({
            'top': $(window).scrollTop()+100,
            'left': $(window).width() /2 - ($('#receta').width()/2)

            });


        $("#receta input[type=submit]").click(function(event) {
            event.preventDefault();
            $("#receta").hide('200', function() {
                alert("La receta ha sido Enviada");
            });;

        });
    }, 200);
}

