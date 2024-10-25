FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/authservice-1.0-SNAPSHOT.jar authservice.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/app/authservice.jar"]