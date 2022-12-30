FROM openjdk:18
ADD target/library docker.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/library docker.jar"]