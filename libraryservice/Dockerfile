FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/libraryservice-1.0-SNAPSHOT.jar libraryservice.jar
ENTRYPOINT ["java", "-jar", "/libraryservice.jar"]