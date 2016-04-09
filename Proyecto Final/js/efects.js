jQuery(document).ready(function($) {
    
    // Salvar contenido anterior
    var backup;
    // TODO : cuando el usuario ingreso ya un valor no sobrescribirlo


    $("input[type=text]").focus(function(event) {
        backup = $(this).val();

        $(this).val('');
        $(this).css('color', 'black');

    });
    $("input[type=password]").focus(function(event) {
        backup = $(this).val();

        $(this).val('');
        $(this).css('color', 'black');

    });
  
});