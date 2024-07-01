# define base docker image
FROM openjdk:11
LABEL maintainer="blogApp.net"
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
