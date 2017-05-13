# Docker Microservices Benchmark

Copyright © 2017 - Leonardo Zanivan

## Info

### Application Endpoints

- GET /data       = Return a sample JSON data.
- GET /concat     = Return a random string (10000 characters).
- GET /fibonacci  = Return the result of fibonacci(30) with recursion.

### Web Framework (multi-thread enabled)

- go 1.8.1 + gin 1.1.4
- java 1.8.0_121 + vert.x 3.4.1
- node 6.10 + express 4.15.2
- java 1.8.0_121 + spring boot 1.5.3 + undertow 1.5.3
- python 2.7.13 + flask 0.12.1 + tornado 4.5.1

### Docker Host Environment

- MacBook Pro Mid 2015 (Core i7 2.5GHz, 16GB RAM)
- Docker for Mac 17.03.1 (8 cores, 16GB memory)

## Package Results

### Application Size

1. node: 2.9 MB (app.js + node_modules)
2. vert.x: 6.0 MB (app.jar -> fat jar)
3. go: 9.1 MB (app)
4. python: 10.0 MB (app.py + pip)
5. spring: 14.0 MB (app.jar -> fat jar)

### Docker Image Size

1. vert.x: 317 MB (base image: openjdk:8-jre)
2. spring: 325 MB (base image: openjdk:8-jre)
3. node: 668 MB (base image: node:6.10)
4. python: 688 MB (base image: python:2.7.13)
5. go: 740 MB (base image: golang:1.8.1)

## Benchmark Results (best of 3 executions)

### Data 

``docker run --rm -it yokogawa/siege -c100 -b -r1000 http://localhost:3000/data``

| Metric  | Go | Vert.x | Node | Spring | Python |
|---|---|---|---|---|---|
| CPU usage | 152% | **121%** | 106% | 204% | 104% |
| Memory usage | **16.5 MB** | 285.5 MB | 77.3 MB | 447.3 MB | 21 MB |
| Elapsed time | 12.13 s | **11.00 s**  | 17.31 s | 14.30 s | 75.59 s |
| Response time | **0.01 s** | **0.01 s** | 0.02 s | 0.01 | 0.08 s |
| Transaction rate | 8244.02 t/s | **9090.91 t/s** | 5777.01 t/s | 6993.01 t/s | 1322.93 t/s |
| Throughput | 1.52 MB/s | **1.68 MB/s** | 1.06 MB/s | 1.29 MB/s | 0.27 MB/s |
| Longest transaction | 0.18 s | 0.15 s | **0.13 s** | 0.24 s | 0.33 |

### Concat 

``docker run --rm -it yokogawa/siege -c100 -b -r100 http://localhost:3000/concat``

| Metric  | Go | Vert.x | Node | Spring | Python |
|---|---|---|---|---|---|
| CPU usage | 581% | **120%** | 103% | 991% | 101% |
| Memory usage | **11.2 MB** | 263.5 MB | 64.4 MB | 402.5 MB | 20.1 MB |
| Elapsed time | 16.53 s | **3.59 s**  | 4.93 s | 19.31 s | 50.92 s |
| Response time | 0.16 s | **0.04 s** | 0.05 s | 0.19 s | 0.51 s |
| Transaction rate | 604.96 t/s | **2785.52 t/s** | 2028.40 t/s | 517.87 t/s | 196.39 t/s |
| Throughput | 5.78 MB/s | **26.60 MB/s** | 19.37 MB/s | 4.95 MB/s | 1.88 MB/s |
| Longest transaction | 0.41 s | **0.13 s** | 0.18 s | 0.61 | 0.59 |

### Fibonacci

``docker run --rm -it yokogawa/siege -c100 -b -r100 http://localhost:3000/fibonacci``

| Metric  | Go | Vert.x | Node | Spring | Python |
|---|---|---|---|---|---|
| CPU usage | 779% | **899%** | 990% | 854% | 104% |
| Memory usage | **13.3 MB** | 255.9 MB | 104.1 MB | 388.1 MB | 20.3 MB | 20 MB |
| Elapsed time | 15.56 s | **9.46 s**  | 27.86 s | 10.31s | TIMEOUT |
| Response time | 0.15 s | **0.09 s** | 0.26 s | 0.10 | TIMEOUT |
| Transaction rate | 642.67 t/s | **1057.08 t/s** | 358.94 t/s | 969.93 t/s | TIMEOUT |
| Throughput | 0.01 MB/s | **0.02 MB/s** | 0.01 MB/s | **0.02 MB/s** | TIMEOUT |
| Longest transaction | 0.82 s | **0.36 s** | 1.02 s | 0.39 s | TIMEOUT |

*Note: Fibonacci recursion test timeouts in python app because its the avg response time is 250ms*