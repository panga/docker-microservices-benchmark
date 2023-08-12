const HyperExpress = require('hyper-express');
const { StaticPool } = require("node-worker-threads-pool");

const poolSize = require('os').cpus().length;
const pool = new StaticPool({
  size: poolSize,
  task: "./worker.js"
});

class Product {
  constructor(id, name, price, weight) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.weight = weight;
  }
}

function sampleData() {
  const data = [];

  data.push(new Product('prod3568', 'Egg Whisk', 3.99, 150));
  data.push(new Product('prod7340', 'Tea Cosy', 5.99, 100));
  data.push(new Product('prod8643', 'Spatula', 1, 80));

  return data;
}

function randomString(len) {
  const alphabet = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
  let result = '';

  for (let i = 0; i < len; i++) {
    const rand = Math.floor(Math.random() * (alphabet.length));
    result += alphabet[rand];
  }

  return result;
};

const router = new HyperExpress.Router();

router.get('/data', (req, res) => {
  res.json({
    data: sampleData()
  });
});

router.get('/concat', (req, res) => {
  res.json({
    concat: randomString(10000)
  });
});

router.get('/fibonacci', (req, res) => {
  pool.exec({n: 30}).then(result => {
    res.json({
      fibonacci: result.fibonacci
    });
  });
});

module.exports = router;