[![CircleCI](https://circleci.com/gh/akmal2409/recipe-app.svg?style=svg)](https://circleci.com/gh/akmal2409/recipe-app)
[![codecov](https://codecov.io/gh/akmal2409/recipe-app/branch/master/graph/badge.svg)](https://codecov.io/gh/akmal2409/recipe-app)
# recipe-app-mongodb
Recipe App (Spring MVC used)

Read more about MVC (Model View Controller)
https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller

# Future Plans and Improvements
  * Deploy in Docker

# Clone repository
#### SSH
    git@github.com:akmal2409/recipe-app-mongodb.git
#### HTTPS
    https://github.com/akmal2409/recipe-app-mongodb.git

# Building project
    mvn clean package

# Tests
#### Unit Tests (You can run with surefire plugin)
    mvn failsafe:integration-test
#### Integration Tests (You can run with failsafe plugin)
    mvn surefire:tests

# Running the project
    mvnw spring-boot:run

# Technologies used so far
* Spring Framework 5
* Spring Boot
* Hibernate
* Project Lombok
* Reactive MongoDB
* WebFlux
* Reactive Programming

# CI/CD
* Circle CI

## Prerequisites
* Java 11 or newer
* Maven
    

