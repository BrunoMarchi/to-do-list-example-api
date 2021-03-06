# Build
FROM maven:3.6.1-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -P docker

# Package
FROM openjdk:8-jre-slim
COPY --from=build /home/app/target/todo-list-1.0.0.jar /usr/local/lib/todo-list-1.0.0.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/usr/local/lib/todo-list-1.0.0.jar"]