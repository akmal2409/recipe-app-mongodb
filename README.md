[![CircleCI](https://circleci.com/gh/akmal2409/recipe-app.svg?style=svg)](https://circleci.com/gh/akmal2409/recipe-app)
[![codecov](https://codecov.io/gh/akmal2409/recipe-app/branch/master/graph/badge.svg)](https://codecov.io/gh/akmal2409/recipe-app)
# recipe-app
Recipe App (Spring MVC used)

Read more about MVC (Model View Controller)
https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller

# Future Plans and Improvements
* Move to MYSQL or Mongo

# Clone repository
#### SSH
    git@github.com:akmal2409/recipe-app.git
#### HTTPS
    https://github.com/akmal2409/recipe-app.git

# Building project
    mvn clean package

# Tests
#### Unit Tests (You can run with surefire plugin)
    mvn failsafe:integration-test
#### Integration Tests (You can run with failsafe plugin)
    mvn surefire:tests

# Running the project
    mvnw spring-boot:run
# Running the project in Docker
* Run command: 

    mvn clean package
* Pull Spring Boot image from Docker Hub
    
    docker run spring-boot-docker
* Extract from target folder under WEB part of the project recipe-app-SNAPSHOT-001.jar and place it in a folder together with Dockerfile
* Open the console in that folder
* Run command 

    docker build -t spring-boot-docker .
* Deploy your container and map port to 8080
    
    docker run -d -p 8080:8080 spring-boot-docker
  
# Technologies used so far
* Spring Framework 5
* Spring Boot
* Hibernate
* Project Lombok

# CI/CD
* Circle CI

## Prerequisites
* Java 11 or newer
* Maven
    

