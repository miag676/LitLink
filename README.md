# Litlink

This project was created as a school project for the class PRPO.

## How to run

To run the project you require maven and Java. It is recommended that you have Java 17.

The backend is compiled in the /backend directory using:

### `mvn clean package`

After that you can build the images with the Dockerfiles for each microservice manually and run them locally on different ports.
The application inside the container will listen on port 8080.

The application expects that it will run in Kubernetes cluster so if you want to run it locally you will have to change the config.yaml files found at microservice/src/main/resource/config.yaml.

``` yaml
 kumuluzee:
  name: litlink
  version: 1.0.0
  env:
    name: dev
  datasources:
  - jndi-name: jdbc/LitLinkDS
    connection-url: jdbc:postgresql://postgres:5432/litlink #change this line to: connection-url: jdbc:postgresql://localhost:5432/litlink
    username: postgres
    password: postgres
    max-pool-size: 20
  openapi-mp:
    enabled: true
    servlet:
      mapping: /v1/users/openapi
```

The application also expects that the database will have a user named postgres with the password postgres that is named litlink so run:

### `docker run -d -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=litlink -p 5432:5432 postgres:13`
