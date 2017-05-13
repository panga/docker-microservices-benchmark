const express = require('express')

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

function fibonacci(n) {
  if (n < 2) return n;
  return fibonacci(n - 2) + fibonacci(n - 1);
};

function customHeaders(req, res, next) {
  res.setHeader('Connection', 'Close');
  next()
}

function start(port, callback) {
  const app = express();

  var numThreads = require('os').cpus().length;
  var pool = require('threads_a_gogo').createPool(numThreads).all.eval(fibonacci);

  app.use(customHeaders);
  app.set('etag', false);
  app.set('x-powered-by', false);

  app.get('/data', function(req, res) {
    res.send({
      data: sampleData()
    });
  });

  app.get('/concat', function(req, res) {
    res.send({
      concat: randomString(10000)
    });
  });

  app.get('/fibonacci', function(req, res) {
    pool.any.eval('fibonacci(30)', (err, result) => {
      res.send({
        fibonacci: result
      });
    });
  });

  app.listen(port, callback)
}

module.exports = {
  start: start
};