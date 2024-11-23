FROM rust:1.82.0 AS build

WORKDIR /app

COPY . .

RUN cargo build --release

EXPOSE 3000

CMD ["/app/target/release/rust_app"]