# 🚀 Proyecto de Práctica con Microservicios en Spring Cloud

Este repositorio tiene como propósito **aprender y practicar** el desarrollo de aplicaciones basadas en **microservicios** utilizando **Spring Boot** y **Spring Cloud**.  
No está orientado a producción, sino a la **comprensión de conceptos clave** y al uso de herramientas fundamentales para construir sistemas distribuidos.

---

## 🎯 Objetivos del Proyecto
- Comprender la arquitectura de microservicios y sus beneficios.
- Explorar las capacidades de **Spring Cloud**: configuración centralizada, descubrimiento de servicios, balanceo de carga, etc.
- Practicar la comunicación entre servicios y la gestión de datos.
- Implementar un flujo básico de autenticación y gestión de usuarios.

---

## 🧩 Microservicios y Componentes

### 1. **Microservicio de Conductores**
- Gestión de información de conductores.
- Operaciones CRUD (crear, leer, actualizar, eliminar).
- Ejemplo: registrar disponibilidad de un conductor.

### 2. **Microservicio de Reservas**
- Administración de reservas de viajes.
- Relación con conductores y usuarios.
- Ejemplo: crear una reserva asignando un conductor disponible.

### 3. **Microservicio de Autenticación y Usuarios**
- Registro y autenticación de usuarios.
- Manejo de roles y permisos básicos.
- Ejemplo: login y generación de tokens JWT.

### 4. **Servicio de Registro (Eureka)**
- Registro y gestión de instancias de microservicios.

### 5. **Módulo de Seguridad**
- Lógica para parsear tokens JWT.
- Mapeo de authorities y definición de rutas públicas.

### 6. **Config Server**
- Centralización de archivos de configuración de cada microservicio.

### 7. **API Gateway**
- Punto de entrada único para las peticiones a la aplicación.

### 8. **Admin Server**
- Monitoreo de métricas de salud de los microservicios.

---

## 🛠️ Tecnologías Utilizadas
- **Spring Boot** → base para cada microservicio.
- **Spring Cloud** → configuración, descubrimiento y comunicación entre servicios.
- **JUnit 5** → pruebas unitarias e integración.
