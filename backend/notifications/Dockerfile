# Base Maven image with JDK
FROM openjdk:17-jdk

# Set working directory
WORKDIR /app

# Copy the module's code into the container
COPY ./target/notifications-1.0.0.jar notifications-1.0.0.jar

# Expose necessary ports (e.g., 8080)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "notifications-1.0.0.jar"]
