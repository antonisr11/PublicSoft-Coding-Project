# Use a base image with OpenJDK and Maven
FROM maven:3.9.4 AS builder

# Set the working directory
WORKDIR /builderApp

# Copy the Maven project files
COPY spring-app/pom.xml .
COPY spring-app/src ./src

# Package the Spring Boot application
RUN mvn clean package -DskipTests

# Use a base image with OpenJDK to run the application
FROM openjdk:23-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the packaged JAR file from the builder stage
COPY --from=builder /builderApp/target/*.jar /app/app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
