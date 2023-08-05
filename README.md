# Docker Microservices Benchmark

Copyright © 2023 - Leonardo Zanivan

## Info

### Application Endpoints

- GET /data       = Return a sample JSON data.
- GET /concat     = Return a random string (10000 characters).
- GET /fibonacci  = Return the result of fibonacci(30) with recursion.

### Web Framework (keep-alive & multi-thread enabled)

- go 1.20 + gin 1.9
- java 20 + vert.x 4.4
- node 18 + express 4.18
- java 20 + spring boot 3.1 + undertow 2.3
- python 3.11 + flask 2.3 + tornado 6.3

### Docker Host Environment

- MacBook Pro 2023 (M2 10 cores, 16GB RAM)
- Docker for Mac 4.21.1 (4 cores, 6GB memory)

## Package Results

### Application Size

1. vertx: 7.8 MB (app.jar -> fat jar)
2. spring: 19.0 MB (app.jar -> fat jar)
3. node: 3.6 MB (app.js + node_modules)
4. python: 14.1 MB (app.py + pip)
5. go: 10.7 MB (app)

### Docker Image Size

1. vertx: 312 MB (base image: amazoncorretto:20-alpine)
2. spring: 324 MB (base image: amazoncorretto:20-alpine)
3. node: 186 MB (base image: node:20-alpine)
4. python: 77 MB (base image: python:3.11-alpine)
5. go: 10.7 MB (base image: golang:1.20-alpine)

## Benchmark Results (best of 3 executions)

### JSON serialization

``hey -c 100 -n 100000 http://localhost:300x/data``

| Metric  | Go | Vert.x | Node | Spring | Python |
|---|---|---|---|---|---|
| CPU usage | 110% | **78%** | 117% | 177% | TOO SLOW |
| Memory usage | **12 MB** | 1022 MB | 90 MB | 1522 MB | TOO SLOW |
| Requests/s | 38356 | **39241** | 16575 | 34909 | TOO SLOW |  
| Latency avg | 2.6ms | **2.5ms** | 6ms | 2.9ms | TOO SLOW | 
| Latency p99 | 5.9ms | **5.5ms** | 15.7ms | 7.3ms | TOO SLOW | 
| Latency max | 46.7ms | **41.9ms** | 538.7ms | 41.8ms | TOO SLOW | 
| Total time | 26.071s | **25.483s** | 60.330s | 28.645s | TOO SLOW | 

### Random string generation 

``hey -c 100 -n 10000 http://localhost:300x/concat``

| Metric  | Go | Vert.x | Node | Spring | Python |
|---|---|---|---|---|---|
| CPU usage | 354% | **108%** | 115% | 384% | TOO SLOW | 
| Memory usage | **15 MB** | 1270 MB | 78 MB | 1370 MB | TOO SLOW | 
| Requests/s | 792 | **4480** | 3600 | 957 | TOO SLOW | 
| Latency avg | 125.2ms | **21.7ms** | 27ms | 102.6ms | TOO SLOW | 
| Latency p99 | 167.4ms | **43.8ms** | 52.3ms | 191ms | TOO SLOW | 
| Latency max | 209.4ms | **68.3ms** | 1339ms | 307.6ms | TOO SLOW | 
| Total time | 12.619s | **2.232s** | 27.849s | 10.446s | TOO SLOW | 

### Fibonacci calculation

``hey -c 100 -n 10000 http://localhost:300x/fibonacci``

| Metric  | Go | Vert.x | Node | Spring | Python |
|---|---|---|---|---|---|
| CPU usage | 383% | 388% | 390% | **368%** | TOO SLOW | 
| Memory usage | **12 MB** | 238 MB | 73 MB | 506 MB | TOO SLOW | 
| Requests/s | 1134 | **1372** | 606 | 1364 | TOO SLOW | 
| Latency avg | 85.8ms | 72.2ms | 164.2ms | **71ms** | TOO SLOW | 
| Latency p99 | 199.5ms | **99.9ms** | 174ms | 159.7ms | TOO SLOW | 
| Latency max | 384.4ms | **162.7ms** | 223.5ms | 241.2ms | TOO SLOW | 
| Total time | 8.814s | **7.288s** | 16.499s | 7.3312s | TOO SLOW | 
