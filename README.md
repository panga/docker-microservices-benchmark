# Docker Microservices Benchmark

Copyright © 2024 - Leonardo Zanivan

## Info

### Application Endpoints

- GET /data       = Return a sample JSON data.
- GET /concat     = Return a random string (10000 characters).
- GET /fibonacci  = Return the result of fibonacci(30) with recursion.

### Web Frameworks

- go 1.23 + echo 4.12
- go 1.23 + fiber 2.52
- java 23 + vert.x 4.5
- java 23 + spring boot 3.4
- java 23 + helidon 4.1
- node 22 + express 4.18
- node 22 + fastify 4.21
- bun 1.1.36 + express 4.18
- dotnet core 9.0
- python 3.13 + flask 3.1

#### TODO
- rust
- swift
- add redis test case

### Performance Tweaks

All applications use optimized implementations for each language & framework.
Connection keep-alive is enabled and multi-thread is used when possible.

Memory GC configuration:
- Go: `GOGC=1000`
- Java: `-XX:+UseZGC -Xmx1G`
- Node: `--max-semi-space-size=64`

### Docker Host Environment

- MacBook Pro 2023 (M2 10 cores, 16GB RAM)
- Docker for Mac 4.35.1 (4 cores, 6GB memory)

## Package Results

### Application Size

1. dotnet: 115 KB (app.dll)
2. node express: 3.6 MB (app.js + node_modules)
3. java helidon: 4.5 MB (app.jar + libs)
4. go echo: 8.1 MB (app)
5. java vertx: 8.3 MB (app.jar -> fat jar)
6. python: 17 MB (app + pip)
7. java spring: 20.0 MB (app.jar -> fat jar)

## Benchmark Results (best of 3 executions)

### JSON serialization

``hey -c 100 -n 1000000 http://localhost:30xx/data``

| Metric        | Requests/s | CPU usage | Memory usage | Latency avg | Latency p99 | Latency max | Total time |
|---------------|------------|-----------|--------------|-------------|-------------|-------------|------------|

### Random string generation

``hey -c 100 -n 10000 http://localhost:30xx/concat``

| Metric        | Requests/s | CPU usage | Memory usage | Latency avg | Latency p99 | Latency max | Total time |
|---------------|------------|-----------|--------------|-------------|-------------|-------------|------------|

### Fibonacci calculation

``hey -c 100 -n 10000 http://localhost:30xx/fibonacci``

| Metric        | Requests/s | CPU usage | Memory usage | Latency avg | Latency p99 | Latency max | Total time |
|---------------|------------|-----------|--------------|-------------|-------------|-------------|------------|
