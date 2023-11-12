# Docker Microservices Benchmark

Copyright © 2023 - Leonardo Zanivan

## Info

### Application Endpoints

- GET /data       = Return a sample JSON data.
- GET /concat     = Return a random string (10000 characters).
- GET /fibonacci  = Return the result of fibonacci(30) with recursion.

### Web Frameworks

- go 1.21 + echo 4.11
- go 1.21 + fiber 2.48
- java 21 + vert.x 4.4
- java 21 + spring boot 3.2
- java 21 + helidon 4.0
- node 20 + express 4.18
- dotnet core 7.0

### Performance Tweaks

All applications use optimized implementations for each language & framework.
Connection keep-alive is enabled and multi-thread is used when possible.

Memory GC configuration:
- Go: `GOGC=1000`
- Java: `-XX:+UseZGC -Xmx1G`
- Node: `--max-semi-space-size=64`

### Docker Host Environment

- MacBook Pro 2023 (M2 10 cores, 16GB RAM)
- Docker for Mac 4.25.0 (4 cores, 6GB memory)

## Package Results

### Application Size

1. dotnet: 256 KB (app.dll)
2. node: 3.6 MB (app.js + node_modules)
3. go: 7.2 MB (app)
4. vertx: 7.8 MB (app.jar -> fat jar)
5. spring: 20.0 MB (app.jar -> fat jar)

### Docker Image Size

1. go + echo: 7.2 MB (base image: golang:1.21-alpine)
2. go + fiber: 10.3 MB (base image: golang:1.21-alpine)
3. helidon: 148 MB (base image: amazoncorretto:21-alpine)
4. node + express: 186 MB (base image: node:20-alpine)
5. dotnet: 216 MB (base image: mcr.microsoft.com/dotnet/sdk:7.0)
6. vertx: 321 MB (base image: amazoncorretto:21-alpine)
7. spring: 334 MB (base image: amazoncorretto:21-alpine)
8. node + bunjs: 449 MB (base image: oven/bun:1.0)
9. node + graaljs: 1210 MB (base image: graalvm/nodejs-community:23-jvm20)
10. node + uws: 1350 MB (base image: node:20)

## Benchmark Results (best of 3 executions)

### JSON serialization

``hey -c 100 -n 1000000 http://localhost:30xx/data``

| Metric        | Requests/s | CPU usage | Memory usage | Latency avg | Latency p99 | Latency max | Total time |
|---------------|------------|-----------|--------------|-------------|-------------|-------------|------------|
| Go Fiber      | 46524      | 113%      | 45 MB        | 2.1ms       | 4ms         | 23.6ms      | 21.494s    |
| Helidon       | 43876      | 149%      | 179 MB       | 2.3ms       | 4.4ms       | 36.6ms      | 22.791s    |
| Go            | 41787      | 145%      | 46 MB        | 2.4ms       | 4.6ms       | 38ms        | 24.300s    |
| Vert.x        | 40586      | 80%       | 865 MB       | 2.5ms       | 5.1ms       | 44.3ms      | 24.638s    |
| Dotnet        | 37482      | 207%      | 41 MB        | 2.7ms       | 5.6ms       | 35.8ms      | 26.679s    |
| Spring        | 35440      | 178%      | 1080 MB      | 2.8ms       | 7.3ms       | 38.9ms      | 28.216s    |
| Node + UWS    | 23157      | 102%      | 177 MB       | 4.3ms       | 10.8ms      | 47.2ms      | 43.183s    |
| Bun JS        | 22627      | 111%      | 120 MB       | 4.4ms       | 11ms        | 75ms        | 44.193s    |
| Node          | 16575      | 118%      | 184 MB       | 6ms         | 15.7ms      | 538.7ms     | 60.330s    |
| Graal JS      | 15915      | 137%      | 661 MB       | 6.2ms       | 16.5ms      | 55.2ms      | 62.831s    |

### Random string generation

``hey -c 100 -n 10000 http://localhost:30xx/concat``

| Metric        | Requests/s | CPU usage | Memory usage | Latency avg | Latency p99 | Latency max | Total time |
|---------------|------------|-----------|--------------|-------------|-------------|-------------|------------|
| Go Fiber      | 21808      | 300%      | 44 MB        | 4.5ms       | 12.6ms      | 37.1ms      | 0.483s     |
| Go            | 20689      | 308%      | 44 MB        | 4.8ms       | 13.1ms      | 46.6ms      | 0.533s     |
| Dotnet        | 19311      | 130%      | 43 MB        | 5.1ms       | 17.6ms      | 43.4ms      | 0.567s     |
| Bun JS        | 6897       | 110%      | 85 MB        | 14.1ms      | 29.1ms      | 55.9ms      | 1.449s     |
| Node + UWS    | 4748       | 107%      | 175 MB       | 20.7ms      | 41.1ms      | 72.4ms      | 2.106s     |
| Vert.x        | 4121       | 105%      | 600 MB       | 23.6ms      | 47.1ms      | 60.2ms      | 2.426s     |
| Node          | 3930       | 115%      | 175 MB       | 23.8ms      | 49.4ms      | 747.3ms     | 2.544s     |
| Graal JS      | 2327       | 110%      | 780 MB       | 42.2ms      | 82.8ms      | 107ms       | 4.296s     |
| Spring        | 1018       | 390%      | 602 MB       | 96.2ms      | 255.4ms     | 375.9ms     | 9.815s     |
| Helidon       | 566        | 400%      | 126 MB       | 174.6ms     | 203.4ms     | 481.4ms     | 17.664s    |

### Fibonacci calculation

``hey -c 100 -n 10000 http://localhost:30xx/fibonacci``

| Metric        | Requests/s | CPU usage | Memory usage | Latency avg | Latency p99 | Latency max | Total time |
|---------------|------------|-----------|--------------|-------------|-------------|-------------|------------|
| Helidon       | 1569       | 400%      | 108 MB       | 63.3ms      | 67.7ms      | 136ms       | 6.369s     |
| Vert.x        | 1372       | 388%      | 238 MB       | 72.2ms      | 99.9ms      | 162.7ms     | 7.288s     |
| Spring        | 1364       | 368%      | 506 MB       | 71ms        | 159.7ms     | 241.2ms     | 7.331s     |
| Dotnet        | 1209       | 400%      | 33 MB        | 82.2ms      | 129.8ms     | 159ms       | 8.265s     |
| Go            | 1176       | 400%      | 43 MB        | 83.1ms      | 138.2ms     | 333.9ms     | 8.502s     |
| Go Fiber      | 1174       | 400%      | 5 MB         | 83.5ms      | 134.9ms     | 227ms       | 8.517s     |
| Bun JS        | 1061       | 400%      | 123 MB       | 93.7ms      | 98.9ms      | 147.1ms     | 9.42s      |
| Node + UWS    | 625        | 400%      | 100 MB       | 159.1ms     | 171.7ms     | 189.3ms     | 15.995s    |
| Node          | 606        | 390%      | 73 MB        | 164.2ms     | 174ms       | 223.5ms     | 16.499s    |
| Graal JS      | 408        | 340%      | 960 MB       | 243.5ms     | 264.8ms     | 305.7ms     | 24.470s    |
