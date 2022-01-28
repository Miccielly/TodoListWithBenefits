FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/TodoListWithBenefits08.jar app.jar
CMD ["java", "-jar", "app.jar"]