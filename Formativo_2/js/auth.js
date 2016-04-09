// validar si es usuario o administrador
function Login (event) {
     if ($("input[name=usuario]").val() != 'Administrador' || 
        $("input[name=password]").val() != 'admin123'){

        if ($("input[name=usuario]").val() == 'Usuario' && 
           $("input[name=password]").val() == 'usuario123'){

            window.location="usuario.html"
        }
        else{
            alert("Usuario/contraseña invalido");
   
        }
        console.log($("input[name=correo]").val());
        console.log($("input[name=password]").val());
     }
     else{ 
        window.location="administrador.html"
     }
}

jQuery(document).ready(function() {
    // prevenir el submit y validar datos
    $("form[name=login]").submit(function(event) {
        event.preventDefault();
        Login();
    });    
});
