# Base Maven image with JDK
FROM openjdk:17-jdk

# Set working directory
WORKDIR /app

# Copy the module's code into the container
COPY ./target/users-1.0.0.jar users-1.0.0.jar

# Build the module
#RUN mvn clean package

# Expose necessary ports (e.g., 8080)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "users-1.0.0.jar"]
