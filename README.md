# Docker Microservices Benchmark

Copyright © 2023 - Leonardo Zanivan

## Info

### Application Endpoints

- GET /data       = Return a sample JSON data.
- GET /concat     = Return a random string (10000 characters).
- GET /fibonacci  = Return the result of fibonacci(30) with recursion.

### Web Frameworks

- go 1.20 + echo 4.11
- go 1.21 + fiber 2.48
- java 20 + vert.x 4.4
- java 20 + spring boot 3.1
- java 21 + helidon 4.0
- node 20 + express 4.18

### Performance Tweaks

All applications use optimized implementations for each language & framework.
Connection keep-alive is enabled and multi-thread is used when possible.

Memory GC configuration:
- Go: `GOGC=1000`
- Java: `-XX:+UseZGC -Xmx1G`
- Node: `--max-semi-space-size=64`

### Docker Host Environment

- MacBook Pro 2023 (M2 10 cores, 16GB RAM)
- Docker for Mac 4.21.1 (4 cores, 6GB memory)

## Package Results

### Application Size

1. node: 3.6 MB (app.js + node_modules)
2. go: 7.1 MB (app)
3. vertx: 7.8 MB (app.jar -> fat jar)
4. spring: 19.0 MB (app.jar -> fat jar)

### Docker Image Size

1. go + echo: 7.1 MB (base image: golang:1.20-alpine)
2. go + fiber: 9 MB (base image: golang:1.21-alpine)
3. helidon: 148 MB (base image: amazoncorretto:21-alpine)
4. node + express: 186 MB (base image: node:20-alpine)
5. node + bun: 256 MB (base image: oven/bun:0.7)
6. vertx: 312 MB (base image: amazoncorretto:20-alpine)
7. spring: 324 MB (base image: amazoncorretto:20-alpine)
8. node + graaljs: 1210 MB (base image: graalvm/nodejs-community:23-jvm20)
9. node + uws: 1290 MB (base image: node:18)

## Benchmark Results (best of 3 executions)

### JSON serialization

``hey -c 100 -n 1000000 http://localhost:30xx/data``

| Metric        | Go         | Vert.x  | Spring  | Node     | Graal JS | Bun JS  | Node + UWS | Go Fiber    | Helidon |
|---|---|---|---|---|---|---|---|---|---|
| CPU usage     | 109%       | **72%** | 178%    | 118%     | 137%     | 108%    | 72%        | 113%        | 149%    |
| Memory usage  | 46 MB      | 865 MB  | 1080 MB | 184 MB   | 661 MB   | 151 MB  | 52 MB      | **45 MB**   | 179 MB  |
| Requests/s    | 39524      | 40586   | 35440   | 16575    | 15915    | 26371   | 40838      | **46524**   | 43876   |
| Latency avg   | 2.5ms      | 2.5ms   | 2.8ms   | 6ms      | 6.2ms    | 3.8ms   | 2.4ms      | **2.1ms**   | 2.3ms   |
| Latency p99   | 4.9ms      | 5.1ms   | 7.3ms   | 15.7ms   | 16.5ms   | 9.6ms   | 5.1ms      | **4ms**     | 4.4ms   |
| Latency max   | **27.4ms** | 44.3ms  | 38.9ms  | 538.7ms  | 55.2ms   | 33ms    | 24.5ms     | **23.6ms**  | 36.6ms  |
| Total time    | 25.300s    | 24.638s | 28.216s | 60.330s  | 62.831s  | 37.919s | 24.486s    | **21.494s** | 22.791s |

### Random string generation

``hey -c 100 -n 10000 http://localhost:30xx/concat``

| Metric        | Go      | Vert.x   | Spring  | Node     | Graal JS | Bun JS | Node + UWS | Go Fiber   | Helidon |
|---|---|---|---|---|---|---|---|---|---|
| CPU usage     | 359%    | **108%** | 390%    | 115%     | 110%     | 115%   | 108%       | 300%       | 400%    |
| Memory usage  | 44 MB   | 600 MB   | 602 MB  | 175 MB   | 780 MB   | 180 MB | 180 MB     | **44 MB**  | 126 MB  |
| Requests/s    | 766     | 4121     | 1018    | 3930     | 2327     | 6096   | 5636       | **21808**  | 566     |
| Latency avg   | 129.5ms | 23.6ms   | 96.2ms  | 23.8ms   | 42.2ms   | 16ms   | 17.2ms     | **4.5ms**  | 174.6ms |
| Latency p99   | 169.5ms | 47.1ms   | 255.4ms | 49.4ms   | 82.8ms   | 30.8ms | 34.8ms     | **12.6ms** | 203.4ms |
| Latency max   | 206.7ms | 60.2ms   | 375.9ms | 747.3ms  | 107ms    | 55.4ms | 51.2ms     | **37.1ms** | 481.4ms |
| Total time    | 13.042s | 2.426s   | 9.815s  | 2.544s   | 4.296s   | 1.64s  | 1.774s     | **0.483s** | 17.664s |

### Fibonacci calculation

``hey -c 100 -n 10000 http://localhost:30xx/fibonacci``

| Metric        | Go      | Vert.x  | Spring  | Node    | Graal JS | Bun JS  | Node + UWS | Go Fiber | Helidon |
|---|---|---|---|---|---|---|---|---|---|
| CPU usage     | 383%    | 388%    | 368%    | 390%    | **340%** | 385%    | 355%       | 400%     | 400%   |
| Memory usage  | 45 MB   | 238 MB  | 506 MB  | 73 MB   | 960 MB   | 135 MB  | 85 MB      | **5 MB** | 108 MB |
| Requests/s    | 1134    | 1372    | 1364    | 606     | 408      | 1030    | 498        | 1174     | **1569** |
| Latency avg   | 85.8ms  | 72.2ms  | 71ms    | 164.2ms | 243.5ms  | 96.5ms  | 201.7ms    | 83.5ms   | **63.3ms** |
| Latency p99   | 199.5ms | 99.9ms  | 159.7ms | 174ms   | 264.8ms  | 102.8ms | 236.7ms    | 134.9ms  | **67.7ms** |
| Latency max   | 384.4ms | 162.7ms | 241.2ms | 223.5ms | 305.7ms  | 151.9ms | 260.1ms    | 227ms    | **136ms**  |
| Total time    | 8.814s  | 7.288s  | 7.3312s | 16.499s | 24.470s  | 9.699s  | 20.275s    | 8.517s   | **6.369s** |
