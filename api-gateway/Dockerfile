FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/api-gateway-1.0-SNAPSHOT.jar api-gateway.jar
ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]
ENV SPRING_PROFILES_ACTIVE=docker
