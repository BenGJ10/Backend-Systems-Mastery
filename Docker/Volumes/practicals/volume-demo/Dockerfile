# Describe from which base image to build
FROM eclipse-temurin:21

# Set the working directory inside the container
WORKDIR /app

# Copy everything from the target folder on the host to app.jar in the container
COPY target/*.jar app.jar

# 8080 port will be used by the application to listen for requests
EXPOSE 8080

# Volume declaration to persist data

VOLUME [ "/app/feedback" ]

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

    