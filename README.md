# Resource Management Project
Sample Spring Boot code to demonstrate the use of Spring Events and other critical integrations

## Setting Up the Code
### System Requirements
1. Java 17 or above
2. Docker Desktop

### Running the Code

#### MySQL
1. Run the MySQL Container in docker. 
2. You can optionally use any existing instance if required. Make sure to update `application.yml` accordingly
```shell
docker-composse up
```

#### Application
```shell
 ./mvnw clean spring-boot:run
```
or by going to `ResourceManagementApplication` class and clicking on run button

#### Checkstyle and Tests
```shell
./mvnw clean checkstyle:checkstyle test
```