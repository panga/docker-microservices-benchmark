const fastify = require('fastify')();
const fastifyExpress = require('@fastify/express');
const routes = require('./routes');

fastify.register(fastifyExpress)
  .after(() => {
    fastify.use(routes);
    fastify.express.disable('etag');
  })

fastify.listen({ host: '0.0.0.0', port: 3000 }, (err, address) => console.log(err || `Listening on ${address}`));

function shutdownHandler() {
  fastify.close((err) => {
      if (err) {
          console.error(err);
          process.exit(1);
      }
  
      process.exit(0);
  });
}

process.on('SIGTERM', shutdownHandler);
process.on('SIGINT', shutdownHandler);
