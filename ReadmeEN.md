# 🚀 Microservices Practice Project with Spring Cloud

This repository is intended to **learn and practice** the development of applications based on **microservices** using **Spring Boot** and **Spring Cloud**.  
It is not production-oriented, but rather focused on **understanding key concepts** and using fundamental tools to build distributed systems.

---

## 🎯 Project Objectives
- Understand microservices architecture and its benefits.
- Explore the capabilities of **Spring Cloud**: centralized configuration, service discovery, load balancing, etc.
- Practice communication between services and data management.
- Implement a basic authentication and user management flow.

---

## 🧩 Microservices and Components

### 1. **Drivers Microservice**
- Manages driver information.
- CRUD operations (create, read, update, delete).
- Example: register driver availability.

### 2. **Reservations Microservice**
- Manages trip reservations.
- Links drivers and users.
- Example: create a reservation by assigning an available driver.

### 3. **Authentication and Users Microservice**
- User registration and authentication.
- Basic role and permission management.
- Example: login and JWT token generation.

### 4. **Service Registry (Eureka)**
- Registers and manages microservice instances.

### 5. **Security Module**
- Logic to parse JWT tokens.
- Maps authorities and defines public routes.

### 6. **Config Server**
- Centralizes configuration files for each microservice.

### 7. **API Gateway**
- Single entry point for application requests.

### 8. **Admin Server**
- Monitors health metrics of microservices.

---

## 🛠️ Technologies Used
- **Spring Boot** → foundation for each microservice.
- **Spring Cloud** → configuration, service discovery, and communication between services.
- **JUnit 5** → unit and integration testing.
