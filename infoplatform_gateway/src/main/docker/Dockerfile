FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD gateway.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
