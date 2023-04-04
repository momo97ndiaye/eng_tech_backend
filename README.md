# Microcredit backend component

This application provides the API endpoints for the microcredit application.

# Getting started

## Install the tools

1. [Java 17](https://adoptium.net/temurin/releases)
2. [IntelliJ](https://www.jetbrains.com/idea/download)
3. [Git](https://git-scm.com/downloads)

## Clone the project repository

1. Git repository [link](https://gitlab.com/eng-technologie-micro-credit/backend-app).
2. Documentation on adding SSH Key to GitLab is
   available [here](https://docs.gitlab.com/ee/user/ssh.html)
   .

```
   git clone git@gitlab.com:eng-technologie-micro-credit/backend-app.git
   git checkout main
```

## Set up IntelliJ IDEA for development

### Import the project

1. Click `File | Open...`
2. Select `backend-app` folder and click `OK`

### Configure the SDK

1. Go to `File | Project Structure... | Platform Settings | SDKs`
2. Click the `+ Add New SDK` button.
3. Select the installed `Java 17` SDK from your computer
4. Go to `File | Project Structure... | Project Settings | Project`
5. Select `Java 17` from the `Project SDK` field
6. Select `17 - Sealed types, alwyas-strict floating-point semantics` from the `Language level`
   field

## Test the application
Add to your env variable :
export SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/mydb"
export SPRING_DATASOURCE_USERNAME="myusername"
export SPRING_DATASOURCE_PASSWORD="mypassword"

Run the following command from the project root folder to start the application :
```
   ./gradlew.bat bootRun
```

Using a tool like POSTMAN, send a request to `http://localhost:8080/sample/public/ping`. You should get a response with
a `200 OK status` and the text *Response from a public endpoint*.

# Reference Documentation

* [Gradle](https://docs.gradle.org)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
* [Spring Boot Gradle Plugin](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/)
* [Spring Security OAuth 2.0 Resource Server](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html)