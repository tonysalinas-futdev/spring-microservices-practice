# 🚗 Driver Microservice

Este microservicio se encarga de la **lógica de negocio para registrar y gestionar la información de los viajes**.  
Forma parte del proyecto de práctica con microservicios en **Spring Boot** y **Spring Cloud**.

---

## Comunicación con otros microservicios
Este microservicio se conecta directamente con el de drivers para poder asignar uno a un viaje, conocer su estado de disponibilidad y cambiarlo.

## 🎯 Objetivo
- Centralizar la lógica para la gestión de viajes.
- Proveer endpoints REST para operaciones CRUD.
- Servir como base para la integración con otros microservicios (drivers, autenticación).

---

## 🛠️ Tecnologías Utilizadas
- **Feign Client**->Comunicación REST con otros microservicios
- **Spring Boot** → Framework principal.
- **Spring Data JPA** → Persistencia y acceso a datos.
- **H2 Database** → Base de datos en memoria.
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


# Ruta para ver la documentación de los endpoints
http://localhost:8084/swagger-ui/index.html