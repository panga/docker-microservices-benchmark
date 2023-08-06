const express = require('express');
const http = require('http');
const routes = require('./routes');

const app = express();

app.set('etag', false);
app.set('x-powered-by', false);
app.use(routes);

const server = http.createServer({
  keepAlive: true,
  keepAliveTimeout: 60000,
  //highWaterMark: 131072 //128kb (default is 16kb)
}, app);

server.listen(3000, () => {
  console.log('Listening on port 3000');
});

function shutdownHandler() {
  server.close((err) => {
      if (err) {
          console.error(err);
          process.exit(1);
      }
  
      process.exit(0);
  });
}

process.on('SIGTERM', shutdownHandler);
process.on('SIGINT', shutdownHandler);
