# Use the official Maven image with JDK 17 as a parent image
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project to the container
COPY . .

# Build the project using Maven
RUN mvn clean package

# Use the official OpenJDK 17 image as the base image for running the application
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage to the runtime stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 7002


# Specify the default command to run the application
CMD ["java", "-jar", "app.jar"]
