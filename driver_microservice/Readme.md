# 🚗 Driver Microservice

Este microservicio se encarga de la **lógica de negocio para registrar y gestionar la información de los conductores**.  
Forma parte del proyecto de práctica con microservicios en **Spring Boot** y **Spring Cloud**.

---

## 🎯 Objetivo
- Centralizar la gestión de datos de conductores.
- Proveer endpoints REST para operaciones CRUD.
- Servir como base para la integración con otros microservicios (reservas, autenticación).

---

## 🛠️ Tecnologías Utilizadas
- **Spring Boot** → Framework principal.
- **Spring Data JPA** → Persistencia y acceso a datos.
- **H2 Database** → Base de datos en memoria para pruebas.
- **Swagger** → Documentación interactiva de endpoints.
- **Gradle** → Construcción y ejecución del proyecto.

---

## ▶️ Cómo Ejecutar

Desde la carpeta del microservicio:

```bash
# (Opcional) aplicar formato al código
./gradlew spotlessApply

# Construir el proyecto
./gradlew build

# Arrancar el microservicio
./gradlew bootRun
