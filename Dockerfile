FROM openjdk:14
ADD target/bright-inventions-demo.jar bright-inventions-demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bright-inventions-demo.jar"]