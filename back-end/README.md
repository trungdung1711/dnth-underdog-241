# Online Fashion Shopping - Docker Instructions

This document explains how to build a Docker image for the Spring Boot application and run it as a container.

---

## **Prerequisites**
- Install **Docker** on your machine.
- Ensure the application is packaged into a JAR file (e.g., `app.jar`) located in the `target/` directory.  
  You can create the JAR by running:
  ```bash
  .\mvnw clean package
  
## **Building**
- Run the command to build the image:
  ```bash
  docker build -t online-fashion-shopping:latest .
- Run the container
  ```bash
  docker run -d -p 8080:8080 --name online-fashion-shopping-container online-fashion-shopping:latest
- Stop the container
  ```bash
  docker stop online-fashion-shopping-container
- Remove the container
  ```bash
  docker rm online-fashion-shopping-container
- Remove the image
  ```bash
  docker rmi online-fashion-shopping:latest   