jQuery(document).ready(function($) {
    $("ul[class=submenu]").click(function() {
        $(this).children('ul').slideToggle();
        }, function() {
        $(this).children('ul').slideToggle()
    });

    if ($(window).width() <= 520) {
        console.log('ok')
        $("nav").html("");
        $("nav").load("html/menu.html");
        setTimeout(function () {
            $("#pull").click(function() {
                $("#menu").slideToggle();
            }, function() {
                $("#menu").slideToggle()
            });

        }, 200);
    };


});