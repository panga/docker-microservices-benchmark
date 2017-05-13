FROM golang:1.8.1

EXPOSE 3000

WORKDIR /go/src/app
COPY app.go .

RUN go-wrapper download
RUN go-wrapper install

CMD ["app"]