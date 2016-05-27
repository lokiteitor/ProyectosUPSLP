
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
    console.log( top + " " + widthChat);

}

jQuery(document).ready(function($) {

        $("#chat").css('display', 'none');

        $("button[name=chat-button]").click(function(event) {
            $("#chat").show();
            posicionarChat();
            $("#chat").slideDown(400);
            $(window).resize(function(event) {
                posicionarChat();
            });
        });
});