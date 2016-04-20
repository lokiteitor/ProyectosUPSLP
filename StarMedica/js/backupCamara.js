
jQuery(document).ready(function($) {


function hasGetUserMedia() {
  // Note: Revisar si el navegador soporta la api de video de html
  return !!(navigator.getUserMedia || navigator.webkitGetUserMedia ||
            navigator.mozGetUserMedia || navigator.msGetUserMedia);
}

if (hasGetUserMedia()) {
    // si esta disponible el API ver mi video en esta parte
    // aqui se normaliza la declaracion de getUserMedia
    navigator.getUserMedia = ( navigator.getUserMedia ||
                       navigator.webkitGetUserMedia ||
                       navigator.mozGetUserMedia ||
                       navigator.msGetUserMedia);

    // obtengo el tag video
    var video = document.querySelector('video');
    // objeto canvas
    var canvas = document.querySelector('#Emisor');
    var context = canvas.getContext('2d');
    context.width = 320;
    context.height = 280;

    navigator.getUserMedia({video:true,audio:true},function(localMediaStream) {
    // aqui lo dibujo
    video.src = window.URL.createObjectURL(localMediaStream);
    },function callback () {
        console.log('Error')
    });

    // conecto al websocket
    var socket = io.connect('http://www.150205.upslpweb.ga:1337');
    // inicia el ciclo
    setInterval(function() { sendFrame(video, context); }, 1000/100);

    // aqui se recibe la señal
    socket.on('setFrame',received);
    init();
} 
else {
  alert('Su navegador no es compatible con las funciones de esta pagina');
}

// emito el video al websocket
function emit(message) {
    socket.emit('newFrame', message);
}

// envio los datos al websocket
function sendFrame(video, context) {
    context.drawImage(video, 0, 0, context.width, context.height);
    emit(canvas.toDataURL('image/webp'));
}

function received(img)
{
    console.log('recibiendo');

    // dibujo el video de la otra camara
    document.querySelector('#frame').src = img;
};

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
function dFrame(ctx,video,canvas)
{
    ctx.drawImage(video,0,0);
    var dataURL = canvas.toDataURL('image/webp');
    socket.emit('newFrame',dataURL);
    requestAnimFrame(function(){
        setTimeout(function(){dFrame(ctx,video,canvas);},200)
    });
}


function init()
{
    var canvas = document.querySelector('#Emisor');
    var video = document.querySelector('video');
    var ctx = canvas.getContext('2d');
    dFrame(ctx,video,canvas);
}

});





