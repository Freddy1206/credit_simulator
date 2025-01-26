# Use a base image for Java 8
FROM openjdk:8-jre-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR file
COPY app/application/target/credit-simulator-application-1.0.0-jar-with-dependencies.jar /app/credit_simulator.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/credit_simulator.jar"]
