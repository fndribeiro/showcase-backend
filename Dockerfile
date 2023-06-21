# Use the base image with Java 20 and Maven pre-installed
FROM maven:3.8.4-openjdk-20

# Set the working directory inside the container
WORKDIR /app

# Copy the source code and pom.xml file to the container
COPY src /app/src
COPY pom.xml /app

# Build the application using Maven
RUN mvn clean package

# Expose the port that the application will run on
EXPOSE 8080

# Specify the command to run the application
CMD ["java", "-jar", "target/showcase-backend.jar"]
