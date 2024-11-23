FROM amazoncorretto:23

EXPOSE 3000

WORKDIR /app

ADD target/app.jar .

CMD ["java", "-XX:+UseZGC", "-Xmx1G", "-jar", "app.jar"]