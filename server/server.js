const http = require('http');
const app = require('./app');

const port = process.env.PORT || 3000;

//Default Port of the API
const server = http.createServer(app);

server.listen(port);
