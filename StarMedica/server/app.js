var express = require('express');
var http = require('http');
var app = express();
var server = http.createServer(app);

// creamos el servidor
server.listen(1337);
var io = require("socket.io").listen(server)

io.sockets.on("connection",function (data) {
    console.log('conectado');
    io.sockets.on("newFrame",function (data) {
        console.log(data);
        io.sockets.broadcast.emit("setFrame",data);    
    })
});

