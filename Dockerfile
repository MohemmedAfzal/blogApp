# define base docker image
FROM openjdk:11
LABEL maintainer="blogApp.net"
ADD target/blogApp-0.0.1-SNAPSHOT.jar blogApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "blogApp-0.0.1-SNAPSHOT.jar"]
