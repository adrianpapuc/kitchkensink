Migrated Spring Boot Application

This project is a migrated version of the Kitchensink quickstart, now utilizing Spring Boot with Java 21 and MongoDB for persistence. This application serves as a demonstration of using modern Java technologies in web-enabled database applications.

## Prerequisites

### For Running the App

- **Java 21**: Ensure you have Java 21 installed. You can check your Java version by running:
  ```bash
  java -version
  ```
- **MongoDB**: Ensure you have a MongoDB instance running. You can either install it locally or in docker.
  - Locally: Follow the steps from https://www.mongodb.com/docs/manual/administration/install-community/
  - In-docker: 
    - Install docker & docker-compose 
    - run docker compose command 
    ```bash
      docker-compose -f docker-compose-mongodb.yml up -d
    ```
- Run the spring boot application from Intellij or terminal:
    ```bash
    mvn spring-boot:run
  ```
