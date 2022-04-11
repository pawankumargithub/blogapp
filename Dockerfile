FROM openjdk:8
EXPOSE 8080
ADD target/blog-service-app.jar blog-service-app.jar
ENTRYPOINT ["java" "-jar","/blog-service-app.jar"]
CMD java Main