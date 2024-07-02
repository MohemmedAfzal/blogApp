# define base docker image
FROM openjdk:17
LABEL maintainer="blogApp.net"
ADD JarRepo/blogApp-0.0.1-SNAPSHOT.jar blogApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "blogApp-0.0.1-SNAPSHOT.jar"]
