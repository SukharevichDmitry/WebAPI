FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/bookservice-1.0-SNAPSHOT.jar bookservice.jar
ENTRYPOINT ["java", "-jar", "/bookservice.jar"]