FROM golang:1.23 AS builder

WORKDIR /go/src/app

ENV CGO_ENABLED=0

ADD go.mod .
ADD app.go .

RUN go get .
RUN go build

FROM scratch

EXPOSE 3000

COPY --from=builder /go/src/app/main /go/bin/main

CMD ["/go/bin/main"]