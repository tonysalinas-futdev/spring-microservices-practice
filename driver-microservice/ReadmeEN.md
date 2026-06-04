# 🚗 Driver Microservice

This microservice is responsible for the **business logic of registering and managing driver information**.
It is part of the microservices practice project built with **Spring Boot** and **Spring Cloud**.

---

## Communication with Other Microservices
This microservice connects directly with the user microservice. When creating a new driver, it retrieves the user’s data and then assigns the **DRIVER** role to that user.

## 🎯 Purpose
- Centralize driver data management.
- Provide REST endpoints for CRUD operations.
- Serve as a foundation for integration with other microservices (reservations, authentication).

---

## 🛠️ Technologies Used
- **Feign Client** → REST communication with other microservices.
- **Spring Boot** → Main framework.
- **Spring Data JPA** → Persistence and data access.
- **H2 Database** → In-memory database for testing.
- **Swagger** → Interactive API documentation.
- **Gradle** → Project build and execution.

---

## ▶️ How to Run

From the microservice folder:

```bash
# (Optional) apply code formatting
./gradlew spotlessApply

# Build the project
./gradlew build

# Start the microservice
./gradlew bootRun

# Access the Swagger UI at:
http://localhost:8023/swagger-ui/index.html