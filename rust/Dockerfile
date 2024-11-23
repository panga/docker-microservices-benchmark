FROM rust:1.70 AS build

WORKDIR /app

COPY . .

RUN cargo build --release

FROM debian:buster-slim

WORKDIR /app

COPY --from=build /app/target/release/rust_app .

EXPOSE 3000

CMD ["./rust_app"]
