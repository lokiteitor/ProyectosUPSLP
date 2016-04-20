jQuery(document).ready(function($) {



    $("input[type=submit]").click(function(event) {
        event.preventDefault();
        var correo = $("input[name=correo]").val();
        var pass = $("input[name=password]").val();        

        if (checkUser(correo,pass) == 2) {
            window.location = "doctor.html";
        }
        else if(checkUser(correo,pass) == 1){
            window.location = "paciente.html";
        }
        else{
            alert("Usuario/Contraseña incorrecto");
        }
    });
});


function checkUser (user,pass) {
    // 2 doctor
    // 1 paciente
    // 0 nadie
    var rtrn;
    if (user == "user" && pass=="user123") {
        rtrn = 1;
    }
    else if (user == "admin" && pass=="admin123") {
        rtrn = 2;
    }
    else{
        rtrn = 0;
    }
    console.log(user + ' ' + pass);
    return rtrn;
}