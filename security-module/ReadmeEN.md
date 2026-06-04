# Security Module

This service implements the core logic for **authentication and authorization**, regardless of whether the application uses a **reactive** or **traditional** approach.

It includes:
- The filter responsible for processing **JWT** tokens.
- The definition of **public routes**.
- The logic required to map **authorities**.

The module must be present in every microservice and requires the mandatory use of an **RSA key** encoded in **Base64** to ensure proper functionality.

---

## Packages

### [core](ca://s?q=Details_of_core_package)
Provides the security configuration for both reactive and standard applications.  
Includes the necessary *converters* to map **authorities** in each case, ensuring consistent integration across all microservices.

### [shared](ca://s?q=Details_of_shared_package)
Contains the supporting classes required by the **core** package, enabling code reuse and maintainability.
