# To Do List API
This is an example of a To Do List API build using Spring Boot, with Spring Actuator for Health Check and Metrics.
Database of choice was MariaDB, which was installed on the local machine. To change it modify the application.properties and pom.xml dependency accordingly.
It's also currently configured to run in Docker.
## Running
Create the database, the name used was **todo_list** which can be changed in application.properties:
> spring.datasource.url

Two profiles are provided:
> local
>
> docker

local is the default profile and docker is used in dockerfile
### Running in local machine
You can build using the defauld profile and your preferred method (e.g. Maven, IntelliJ, etc.)
Just set the database username and password in application-local.properties:
> spring.datasource.username=**your database user**
>
> spring.datasource.password=**your database password**
### Docker
For docker just set the database username and password in application-docker.properties:
> spring.datasource.username=**your database user**
>
> spring.datasource.password=**your database password**

To build a Docker image
> docker build -t docker-spring-to-do-api .

To run
> docker run --rm -it -p 9090:9090 docker-spring-to-do-api

## cURL Examples
List all entries:
> curl -X GET http://127.0.0.1:9090/todo

Get entry by id:
> curl -X GET http://127.0.0.1:9090/todo/{id}

Create entry:
> curl -X POST http://127.0.0.1:9090/todo -H "Content-Type: application/json" -d "{\"description\" : \"test\"}"

Update existing entry, if it does not exist create a new one:
> curl -X PUT http://127.0.0.1:9090/todo/{id} -H "Content-Type: application/json" -d "{\"status\" : \"pending\", \"description\": \"changed description\"}"

Delete entry by id:
> curl -X DELETE http://127.0.0.1:9090/todo/{id}

Health Check:
> curl -X GET http://127.0.0.1:9090/healthcheck

List Available Metrics:
> curl -X GET http://127.0.0.1:9090/metrics

Get Specific Metric:
> curl -X GET http://127.0.0.1:9090/metrics/{requiredMetricName}
>
>Example:
>
> curl -X GET http://127.0.0.1:9090/metrics/http.server.requests

## Improvements
- Add pagination to repository
- Add filtering by status to repository
- Deploy in Kubernetes
- Improve logs and add logging level
- Add Integration Tests
- Create a simple automated test, maybe using Postman?
- Use Flyway or Liquibase to handle database creation
