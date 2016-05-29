
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
    $("#inputMensaje").focus();

    setTimeout(function  () {
        
        $("#info-chat a").click(function(event) {
            event.preventDefault();
            $("#chat").slideUp('400');
        });
    }, 400)

    console.log( top + " " + widthChat);
}

jQuery(document).ready(function($) {
        var backup;

        $("#chat").css('display', 'none');

        $('a[name="ticket"]').click(function(event) {
            event.preventDefault();
            if (!backup) { 
                backup = $("#contenido").detach();
            }
            $('<div class="container" id="contenido"></div>').appendTo('body');
            $("#contenido").load("html/ticket.html");
        });
        $('a[name="seguimiento"]').click(function(event) {
            event.preventDefault();
            if (backup) {
                $('#contenido').empty();
                backup.appendTo('body');
            };
        });

        str1 = '<tr><td><span class="glyphicon glyphicon-eye-open"></span></td>'

        td = '<td>'
        td2 = '</td>'
        str2 = '<input type="button" class="btn btn-info btn-block" value="Estado">'
        str3 = '<button type="button" name="chat-button" class="btn btn-default"><span class="glyphicon glyphicon-comment"></span></button>'

        for(var i = 0;i< Math.random()*5;i++){
            $(str1.concat(td, "Marca",td2,td,"Modelo",td2,td,"$",parseInt(Math.random() * 10000 +1),td2,td,str2,td2,td,str3,td2,"</tr>")).appendTo('table tbody');            
        }
        $("button[name=chat-button]").each(function(index, el) {
            $(el).click(function(event) {
                $("#chat").show();
                posicionarChat();
                $("#chat").slideDown(400);
                $(window).resize(function(event) {
                posicionarChat();
            });
        });
        });


// <tr>
//     <td>
//         <span class="glyphicon glyphicon-eye-open"></span>
//     </td>
//     <td>
//         Marca
//     </td>
//     <td>
//         Modelo
//     </td>
//     <td>
//         $1000
//     </td>
//     <td>
//         <input type="button" class="btn btn-info btn-block" value="Estado">
//     </td>
//     <td>
//         <button type="button" name="chat-button" class="btn btn-default"><span class="glyphicon glyphicon-comment"></span></button>
//     </td>
// </tr>




});7