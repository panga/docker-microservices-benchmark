const app = require('./app');

const port = 3000;
app.start(port, () => {
  console.log(`Listening on port ${port}`)
});