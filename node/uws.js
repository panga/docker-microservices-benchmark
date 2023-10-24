const uWS = require('uWebSockets.js');
const expressify = require('uwebsockets-express');
const routes = require('./routes');

const uwsApp = uWS.App();
const app = expressify.default(uwsApp);

app.set('etag', false);
app.set('x-powered-by', false);
app.use(routes);

app.listen(3000, () => {
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
