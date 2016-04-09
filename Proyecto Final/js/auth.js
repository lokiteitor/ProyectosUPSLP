    
function validarLogin (event) {
     if ($("input[name=correo]").val() != 'admin@electro.com' || 
        $("input[name=password]").val() != 'admin123'){

        alert("Usuario/contraseña invalido");
        console.log($("input[name=correo]").val());
        console.log($("input[name=password]").val());

     }
     else{ 
        window.location="administrador.html"
     }
}


jQuery(document).ready(function() {
    $("form[name=login]").submit(function(event) {
        event.preventDefault();
        validarLogin();
    });    
});
