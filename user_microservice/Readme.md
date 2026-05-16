# 👤 User Microservice

Este microservicio se encarga de la **gestión de usuarios y sus roles (Client, Admin, Driver, etc.)**.  
Forma parte del proyecto de práctica con microservicios en **Spring Boot** y **Spring Cloud**.

---

## 🎯 Objetivo
- Centralizar la gestión de datos de usuarios.
- Proveer endpoints REST para operaciones CRUD y autenticación.
- Manejar roles y permisos (Client, Admin, Driver).
- Servir como base para la integración con otros microservicios (reservas, autenticación).

---

## 🛠️ Tecnologías Utilizadas
- **Spring Boot** → Framework principal.
- **Spring Data JPA** → Persistencia y acceso a datos.
- **PostgreSQL** → Base de datos relacional para almacenamiento.
-  **Flyway** → Migraciones de base de datos versionadas y automáticas.
- **Swagger / OpenAPI** → Documentación interactiva de endpoints.
- **Gradle** → Construcción y ejecución del proyecto.

---

## ▶️ Cómo Ejecutar

Desde la carpeta del microservicio:

```bash
#Crear el .env usando el .env.example como guía

# (Opcional) aplicar formato al código
./gradlew spotlessApply

# Construir el proyecto
./gradlew build

# Arrancar el microservicio
./gradlew bootRun

## Ver documentación de swagger
http://localhost:8020/swagger-ui/index.html