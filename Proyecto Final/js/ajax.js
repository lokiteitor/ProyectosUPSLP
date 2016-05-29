
// funcion que recibe el area que debe retornar y recibe la seccion donde 
// lo debe colocar

function cargarElemento (seccion,localizacion) {

    if (seccion == "footer") {
        if (window.location.pathname == "/proyecto_final/login.html" ||
            window.location.pathname == "/proyecto_final/registro.html") {
            $(localizacion).load('html/footer.html',setTimeout(posicionarfoot(), 600));
        }
        else{
            $(localizacion).load("html/footer.html");
        }
    }
    else if(seccion == "nav"){
        if (window.location.pathname == "/proyecto_final/login.html") {
            $(localizacion).load("html/nav.html",function  () {
                setTimeout(function  () {
                    $('a[href="login.html').remove();   
                }, 600)  
            })
        }
        else if (window.location.pathname == "/proyecto_final/registro.html") {
            $(localizacion).load("html/nav.html",function  () {
                setTimeout(function  () {
                    $('a[href="registro.html').remove();   
                }, 600)  
            })            
        }
        else {
            $(localizacion).load("html/nav.html");

        }
    }
}
function posicionarfoot () {
     // obtener las dimensiones actuales de la ventana
    var altura = $(window).height();

    // obtener el ancho del elemento
    var heightf = $("#HereFooter").height();

    // obtener las posiciones adecuadas
    topfoot = altura - heightf;

    $("#HereFooter").css({
        "position":"absolute",
        "width":"100%",
        "top": topfoot

    });
    console.log(topfoot);
    console.log(altura);
    console.log(heightf);
}

