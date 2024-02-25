FROM ubuntu:22.04
LABEL authors="JOSEJ"
RUN apt-get update && apt-get install -y openjdk-17-jdk
expose 8080
VOLUME /tmp
ADD target/memoriesbackend-0.0.1-SNAPSHOT.jar memoriesbackend.jar
RUN sh -c 'touch /memoriesbackend.jar'
ENTRYPOINT ["java","-jar","/memoriesbackend.jar"]