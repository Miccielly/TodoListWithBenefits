FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/TodoListWithBenefits06.jar app.jar
CMD ["java", "-jar", "app.jar"]