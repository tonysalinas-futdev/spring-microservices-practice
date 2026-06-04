# Security Module

Este servicio implementa la lógica central de **autenticación y autorización**, independientemente de si la aplicación utiliza un enfoque **reactivo** o **tradicional**.

Incluye:
- El filtro encargado de procesar tokens **JWT**.
- La definición de **rutas públicas**.
- La lógica necesaria para mapear las **authorities**.

El módulo debe estar presente en cada microservicio y requiere obligatoriamente el uso de una clave **RSA** codificada en **Base64** para garantizar su correcto funcionamiento.

---

## Paquetes

### [core](ca://s?q=Detalles_del_paquete_core)
Contiene la configuración de seguridad para aplicaciones reactivas y estándar.  
Incluye los *converters* necesarios para mapear las **authorities** en cada caso, asegurando una integración consistente en todos los microservicios.

### [shared](ca://s?q=Detalles_del_paquete_shared)
Proporciona las clases de soporte requeridas por el paquete **core**, facilitando la reutilización y el mantenimiento del código.
