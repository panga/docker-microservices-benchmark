# Docker Microservices Benchmark

Copyright © 2023 - Leonardo Zanivan

## Info

### Application Endpoints

- GET /data       = Return a sample JSON data.
- GET /concat     = Return a random string (10000 characters).
- GET /fibonacci  = Return the result of fibonacci(30) with recursion.

### Web Frameworks

- go 1.20 + echo 4.11
- java 20 + vert.x 4.4
- node 18 + express 4.18
- java 20 + spring boot 3.1

### Performance Tweaks

All applications use optimized implementations for each language & framework.
Connection keep-alive is enabled and multi-thread is used when possible.

Runtime performance configuration:
- Golang: `GOGC=1000`
- Java: `-XX:+UseZGC`

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

1. go: 7.1 MB (base image: golang:1.20-alpine)
2. node: 186 MB (base image: node:20-alpine)
3. vertx: 312 MB (base image: amazoncorretto:20-alpine)
4. spring: 324 MB (base image: amazoncorretto:20-alpine)
5. node + graalvm: 1210 MB (base image: graalvm/nodejs-community:23-jvm20)

## Benchmark Results (best of 3 executions)

### JSON serialization

``hey -c 100 -n 1000000 http://localhost:300x/data``

| Metric        | Go         | Vert.x      | Node     | Spring  | GraalVM JS |
|---|---|---|---|---|---|
| CPU usage     | 109%       | **72%**     | 117%     | 177%    | 109%       |
| Memory usage  | **46 MB**  | 865 MB      | 90 MB    | 1522 MB | 764 MB     |
| Requests/s    | 39524      | **40586**   | 16575    | 35278   | 15518      |  
| Latency avg   | **2.5ms**  | **2.5ms**   | 6ms      | 2.8ms   | 6.4ms      | 
| Latency p99   | **4.9ms**  | 5.1ms       | 15.7ms   | 7.3ms   | 16.5ms     | 
| Latency max   | **27.4ms** | 44.3ms      | 538.7ms  | 43.1ms  | 55.2ms     | 
| Total time    | 25.300s    | **24.638s** | 60.330s  | 28.345s | 64.439s    | 

### Random string generation 

``hey -c 100 -n 10000 http://localhost:300x/concat``

| Metric        | Go        | Vert.x      | Node     | Spring  | GraalVM JS |
|---|---|---|---|---|---|
| CPU usage     | 359%      | **108%**    | 115%     | 390%    | 110%       | 
| Memory usage  | **44 MB** | 600 MB      | 78 MB    | 602 MB  | 780 MB     | 
| Requests/s    | 766       | **4121**    | 3585     | 1018    | 2327       | 
| Latency avg   | 129.5ms   | **23.6ms**  | 23.7ms   | 96.2ms  | 42.2ms     | 
| Latency p99   | 169.5ms   | **47.1ms**  | 52.5ms   | 255.4ms | 82.8ms     | 
| Latency max   | 206.7ms   | **60.2ms**  | 1300ms   | 375.9ms | 107ms      | 
| Total time    | 13.042s   | **2.426s**  | 2.788s   | 9.815s  | 4.296s     | 

### Fibonacci calculation

``hey -c 100 -n 10000 http://localhost:300x/fibonacci``

| Metric        | Go        | Vert.x      | Node     | Spring   | GraalVM JS |
|---|---|---|---|---|---|
| CPU usage     | 383%      | 388%        | 390%     | 368%     | **340%**   | 
| Memory usage  | **45 MB** | 238 MB      | 73 MB    | 506 MB   | 960 MB     | 
| Requests/s    | 1134      | **1372**    | 606      | 1364     | 408        | 
| Latency avg   | 85.8ms    | 72.2ms      | 164.2ms  | **71ms** | 243.5ms    | 
| Latency p99   | 199.5ms   | **99.9ms**  | 174ms    | 159.7ms  | 264.8ms    | 
| Latency max   | 384.4ms   | **162.7ms** | 223.5ms  | 241.2ms  | 305.7ms    | 
| Total time    | 8.814s    | **7.288s**  | 16.499s  | 7.3312s  | 24.470s    | 
