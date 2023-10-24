FROM golang:1.21-alpine AS builder

WORKDIR /go/src/app

ADD go.mod .
ADD app.go .

RUN go get .
RUN go build

FROM scratch

EXPOSE 3000

COPY --from=builder /go/src/app/main /go/bin/main

CMD ["/go/bin/main"]