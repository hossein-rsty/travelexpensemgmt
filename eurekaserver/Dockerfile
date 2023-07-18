#base image with java runtime
FROM openjdk:22-slim
#get path to jar file passed as argument: ARG JAR_FILE=target/*.jar
#add jar file to container
COPY build/libs/*.jar app.jar
#execute
ENTRYPOINT ["java", "-jar", "/app.jar"]