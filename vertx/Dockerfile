FROM amazoncorretto:20-alpine

EXPOSE 3000

WORKDIR /app

ADD target/app.jar .

CMD ["java", "-XX:+UseZGC", "-XX:+UseStringDeduplication", "-jar", "app.jar"]