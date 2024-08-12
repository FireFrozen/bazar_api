FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/bazar_api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_bazar_api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_bazar_api.jar"]