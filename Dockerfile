FROM openjdk:8
EXPOSE 8080
ADD target/blog-service.jar blog-service.jar
ENTRYPOINT ["java" "-jar","/blog-service.jar"]