FROM openjdk:8-jre

EXPOSE 3000

WORKDIR /app

ADD target/app.jar .

CMD ["java", "-XX:+UseG1GC", "-XX:+UseStringDeduplication", "-jar", "app.jar"]