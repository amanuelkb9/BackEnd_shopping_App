#start with base image containing Java runtime
FROM openjdk:11-jre
#Make port 7070 available to the world outside this container
EXPOSE 8080
ADD target/backend-shopping-app.jar backend-shopping-app.jar
#Run the jar file
ENTRYPOINT ["java","-jar","backend-shopping-app.jar"]
