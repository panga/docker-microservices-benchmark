const express = require('express');
const http = require('http');
const { StaticPool } = require("node-worker-threads-pool");

const poolSize = require('os').cpus().length;
const pool = new StaticPool({
  size: poolSize,
  task: "./worker.js"
});

function sampleData() {
  var data = [];

  data.push({
    'id': 'prod3568',
    'name': 'Egg Whisk',
    'price': 3.99,
    'weight': 150
  });

  data.push({
    'id': 'prod7340',
    'name': 'Tea Cosy',
    'price': 5.99,
    'weight': 100
  });

  data.push({
    'id': 'prod8643',
    'name': 'Spatula',
    'price': 1,
    'weight': 80
  });

  return data;
}

function randomString(len) {
  const alphabet = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
  var result = '';

  for (var i = 0; i < len; i++) {
    var rand = Math.floor(Math.random() * (alphabet.length));
    result += alphabet[rand];
  }

  return result;
};

const app = express();

app.set('etag', false);
app.set('x-powered-by', false);

app.get('/data', (req, res) => {
  res.send({
    data: sampleData()
  });
});

app.get('/concat', (req, res) => {
  res.send({
    concat: randomString(10000)
  });
});

app.get('/fibonacci', (req, res) => {
  pool.exec({n: 30}).then(result => {
    res.send({
      fibonacci: result.fibonacci
    });
  });
});

const server = http.createServer({
  keepAlive: true,
  keepAliveTimeout: 60000
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
