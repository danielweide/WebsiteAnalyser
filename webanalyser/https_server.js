var https = require('https');
var fs = require('fs');

var https_options = {

  key: fs.readFileSync("./ssl_files/domain.key"),

  cert: fs.readFileSync("./ssl_files/domain.crt"),

  ca: [

          fs.readFileSync('./ssl_files/domain.crt'),

          fs.readFileSync('./ssl_files/domain.crt')

       ]
};

https.createServer(https_options, function (req, res) {

 res.writeHead(200);

 res.end("Welcome to Node.js HTTPS Servern");

}).listen(4200)
