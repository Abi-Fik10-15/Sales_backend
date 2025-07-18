# Stage 1: Build the application
# Use a base image that includes both Maven and OpenJDK 17
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file first. This step is cached by Docker, so dependencies
# are only re-downloaded if pom.xml changes.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of your application's source code
COPY src ./src

# Package the application into a .jar file. We skip running tests during the build.
RUN mvn clean package -DskipTests

# ---

# Stage 2: Create the final, lightweight runtime image
# Use a slim JRE image which is much smaller than the full JDK/Maven image
FROM openjdk:17-slim

WORKDIR /app

# Copy only the built .jar file from the 'build' stage into the final image
COPY --from=build /app/target/*.jar app.jar

# Expose the port that the application will run on.
# This should match the server.port in your application.properties.
EXPOSE 8084

# The command to run the application when the container starts
ENTRYPOINT ["java","-jar","app.jar"]
