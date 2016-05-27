
// funcion que recibe el area que debe retornar y recibe la seccion donde 
// lo debe colocar

function cargarElemento (seccion,localizacion) {

    if (seccion == "footer") {
        $(localizacion).load("html/footer.html");
    }
    else if(seccion == "nav"){
        $(localizacion).load("html/nav.html");
    }
}
    


