    
function validarLogin (event) {
     if ($("input[name=correo]").val() == 'admin@electro.com' && 
        $("input[name=password]").val() == 'admin123'){
        console.log("ok")
        window.location="administrador.html"
     }
     else if($("input[name=correo]").val() == "user@electro.com" &&
	$("input[name=password").val() == "user123"){

	window.location="cliente.html";

     }
     else if($("input[name=correo]").val() == "tecnico@electro.com" &&
	$("input[name=password]").val() == "tecnico123"){

		window.location = "tecnico.html";
	}
     else{ 
	alert("Usuario/contraseña incorrectos");
     }
}


jQuery(document).ready(function() {
    $("form[name=login]").submit(function(event) {
        event.preventDefault();
        validarLogin();
    });
	$("form[name=registro]").submit(function(event){

		event.preventDefault();
		alert("Registro realizado con exito");
		setTimeout(function(){window.location="cliente.html"},500);
	});
});


