## Spring Boot - Upload Image BE

## System Requirements

- Java openjdk : Version 17.0.2
- Spring Boot : version 3.4.1
- Database : MariaDB
- Maven : Apache Maven 3.9.3
- Editor : Intellij IDEA 2023.1.1 Community Edition

## Dependencies

- Spring Web
- Spring Data JPA
- Spring Boot DevTools
- Mysql Driver
- Lombok

## Run Project

1. clone project Spring Boot - Upload Image BE
```
git clone https://github.com/rardan97/upload-image-be.git
```
2. add database name "db_upload_image" in MariaDB

3. open project with intellij IDEA then edit config database in application.properties
```
path : upload-image-be/src/main/resources/application.properties
```

```
spring.application.name=upload-image-be
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/db_upload_image
spring.datasource.username=root
spring.datasource.password=
spring.jpa.database=mysql

##### config directory uploads image
upload.dir=./uploads
spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
spring.servlet.multipart.file-size-threshold=2MB
```

4. open terminal input command
```
mvn clean install 
```
5. if success next input command
```
mvn spring-boot:run
```

## Frontend Project
The FrontEnd of this application can be accessed at the following link:
```
https://github.com/rardan97/upload-image-fe
```