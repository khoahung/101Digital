FROM openjdk:8-jdk-alpine
EXPOSE 8081
COPY project/target/project-0.0.1-SNAPSHOT.jar project-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","project-0.0.1-SNAPSHOT.jar"]
