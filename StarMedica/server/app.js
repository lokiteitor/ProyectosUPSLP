var express = require('express');
var https = require('https');
var app = express();
var fs = require('fs');
var server = https.createServer({
	key: fs.readFileSync('privkey.pem'),
	cert: fs.readFileSync('cert.pem')
},
app);

// creamos el servidor
server.listen(1337);
var io = require("socket.io").listen(server)

io.sockets.on("connection",function (socket) {
    socket.on("newFrame",function (data) {
        socket.broadcast.emit("setFrame",data);    
    })
});

