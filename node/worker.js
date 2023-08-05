const { parentPort } = require("worker_threads");

parentPort.on("message", data => {
    parentPort.postMessage({fibonacci: fibonacci(data.n)});
});

function fibonacci(n) {
    if (n < 2) return n;
    return fibonacci(n - 2) + fibonacci(n - 1);
};