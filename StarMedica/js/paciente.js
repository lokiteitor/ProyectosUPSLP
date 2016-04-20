jQuery(document).ready(function($) {

    $("#individualMenu li[name=encuesta]").hide();
    $("#individualMenu li[name=imprimir]").hide();


    // revisar que se de click en reservar cita para mostrar form
    $("li[name=cita] a").click(function(event) {
        // mostrar formulario
        console.log('click cita')
        event.preventDefault();
        $("#emergente").load("html/citas.html");
        $("#detallesCita").css('display', 'none');

        $("li[name=cita").hide(100);


        showForm();
    });

    $("li[name=ingresar] a").click(function(event) {
        event.preventDefault();
        $("#detallesCita").css('display', 'none');
        $("#streaming").load("html/consultorio.html");

        setTimeout(function  () {
            $("#workarea #individualMenu").hide();
            $("#workarea input[type=button").click(function(event) {
                event.preventDefault();
                $("#individualMenu li[name=encuesta]").fadeIn(300);
                $("#individualMenu li[name=imprimir]").fadeIn(300);
                $("#individualMenu li[name=ingresar]").hide(400);
                $("#streaming").html("");
            });
        }, 100);

    });

    $("li[name=encuesta] a").click(function(event) {
        event.preventDefault();
        $("#detallesCita").css('display', 'none');
        $("#emergente").html("");
        $("#emergente").load("html/encuesta.html");
        $("#emergente").css("display",'block');
        showForm();

    });
});


function sendForm () {
    // revisar cuando envia el formulario
    console.log('Volver');
    // volver a la normalidad
    $("#detallesCita").css('display', 'block');
    $("#emergente").fadeOut('400');
    $(window).scrollTop(0);
    $("#clientWorkSpace").css({
        'filter':'none',
        '-webkit-filter': 'none'
    });

}

function showForm () {
    setTimeout(function () {
        $("#clientWorkSpace").css({
            'filter':'blur(10px)',
            '-webkit-filter': 'blur(10px)'
        });
        var hgt = $(".baseFloat").height() + 50;
        $("#clientWorkSpace").height(hgt);
        $(".baseFloat").css({
            'top': $(window).scrollTop()+200,
            'left': $(window).width() /2 - ($('.baseFloat').width()/2),
            'z-index':100
            });

    }, 200);

}