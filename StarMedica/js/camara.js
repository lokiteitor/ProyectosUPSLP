        var websocket;
        var video = document.querySelector('video');
        // definimos getUserMedia en forma comun para todos los navegadores
        navigator.getUserMedia = ( navigator.getUserMedia ||
                           navigator.webkitGetUserMedia ||
                           navigator.mozGetUserMedia ||
                           navigator.msGetUserMedia);

        window.URL = window.URL || window.webkitURL;

        window.requestAnimFrame = (function(callback){
            return window.requestAnimationFrame ||
            window.webkitRequestAnimationFrame ||
            window.mozRequestAnimationFrame ||
            window.oRequestAnimationFrame ||
            window.msRequestAnimationFrame ||
            function(callback){
                window.setTimeout(callback, 1000 / 100);
            };
        })();
        // esperamos para recibir correctamente los scripts de socket.io
        // y empezar a transmitir
        setTimeout(function () {
            websocket = io.connect("https://www.150205.upslpweb.ga:1337");
            navigator.getUserMedia({video:true,audio:true},function(localMediaStream) {
            // aqui lo dibujo
            video.src = window.URL.createObjectURL(localMediaStream);
            },function callback () {
                console.log('Error')
            });
            websocket.on('setFrame',proc);
            init();


        }, 400);
        // obtenemos los frames dibujados en el canvas
        function dFrame(ctx,video,canvas)
        {
            // probar a enviar webp y recibir en un video en vez de img
            ctx.drawImage(video,0,0);
            var dataURL = canvas.toDataURL('image/jpeg',0.2);
            websocket.emit('newFrame',dataURL);
            requestAnimFrame(function(){
                setTimeout(function(){dFrame(ctx,video,canvas);},200)
            });
        }
        // empezar la transmission
        function init()
        {
            var canvas = document.querySelector('#Emisor');
            var video = document.querySelector('video');
            var ctx = canvas.getContext('2d');
            dFrame(ctx,video,canvas);
        }

        function proc(img)
        {
            // dibujar los datos recibidos del servidor
            document.querySelector('#frame').src = img;
        };



jQuery(document).ready(function($) {
    
});
