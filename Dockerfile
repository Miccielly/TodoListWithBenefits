FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/TodoListWithBenefits07.jar app.jar
CMD ["java", "-jar", "app.jar"]