# define base docker image
FROM openjdk:11
LABEL maintainer="blogApp.net"
ADD target/blogApp-0.0.1-SNAPSHOT.jar blogapp-docker.jar
ENTRYPOINT ["java", "-jar", "blogapp-docker.jar"]
