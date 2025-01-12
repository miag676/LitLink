# Litlink

This project was created as a school project for the class PRPO.

## How to run

To run the project you require maven and Java. It is recommended that you have Java 17.

The backend is compiled in the /backend directory using:

### `mvn clean package`

After that you can build the images with the Dockerfiles for each microservice manually and run them locally on different ports.
The application inside the container will listen on port 8080.

The application expects that a database with a user postgres and password postgres named litlink will run on port 5432 so run:

### `docker run -d -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=litlink -p 5432:5432 postgres:13`

