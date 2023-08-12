const HyperExpress = require('hyper-express');
const webserver = new HyperExpress.Server();
const routes = require('./routes_hyper');

webserver.use(routes);;
webserver.listen(3000).then(() => console.log('Listening on port 3000'));

function shutdownHandler() {
  webserver.close();
  setTimeout(() => process.exit(0), 100);
}

process.on('SIGTERM', shutdownHandler);
process.on('SIGINT', shutdownHandler);
