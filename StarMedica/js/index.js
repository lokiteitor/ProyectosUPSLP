jQuery(document).ready(function($) {

    $(".especialidades input").click(function(event) {
        event.preventDefault();
        $(this).next('.show').load('html/directorio.html .directorio');
        $(this).hide();
        setTimeout(function () {
            $(".especialidades a").click(function(event) {
                event.preventDefault();
                $("<div></div>").insertAfter(this)
                $(this).next().load('html/directorio.html .infodoctor');
            }); 
        }, 200);
    });

    $(".info input").click(function(event) {
        event.preventDefault();
        $("<div></div>").insertAfter(this)
        $(this).next().load('html/directorio.html .infoServicio');
        setTimeout(function  () {
            $('body').css({
                'backgroundColor':'black',
                'filter': 'opacity(.6)',
                '-webkit-filter': 'opacity(.6)'
            });

            $('.infoServicio').css({
                'top': $(window).scrollTop()+100,
                'left': $(window).width() /2 - ($('.infoServicio').width()/2)
            });
            $('.infoServicio img').click(function(event) {
                $('.infoServicio').parent().css('display', 'none');
                $('body').css({
                    'backgroundColor': '#F0F0F0',
                    'filter': 'none',
                    '-webkit-filter': 'none'
                });
            });

        }, 200);        
    });

});