# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file to the container
COPY target/online-fashion-shopping-0.0.1-SNAPSHOT.jar /app/online-fashion-shopping-0.0.1-SNAPSHOT.jar

# Copy the public directory to the container
COPY storage /app/storage

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "online-fashion-shopping-0.0.1-SNAPSHOT.jar"]
