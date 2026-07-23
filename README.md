# Hotel Booking API 

API REST para la gestión de reservas de hoteles desarrollada con Spring Boot. El proyecto implementa autenticación mediante JWT, control de acceso por roles y operaciones CRUD para hoteles, habitaciones, clientes y reservas, siguiendo buenas prácticas de desarrollo.

## Características

* Autenticación y autorización con JWT.
* Control de acceso basado en roles.
* Gestión de hoteles.
* Gestión de tipos de habitación.
* Gestión de habitaciones.
* Gestión de clientes.
* Gestión de reservas.
* Validación de datos.
* Manejo global de excepciones.
* Persistencia de datos con MySQL.

## Tecnologías

* Java 21
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA (Hibernate)
* MySQL
* Maven
* Lombok
* Bean Validation

## Estructura del proyecto

```text
src
├── config
├── domain
│   ├── auth
│   ├── customer
│   ├── hotel
│   ├── reservation
│   ├── room
│   ├── roomtype
│   └── user
├── security
├── shared
└── resources
```

## Instalación

### Requisitos

* Java 21 o superior
* Maven 3.9 o superior
* MySQL 8 o superior

### Configuración

Configura las siguientes variables de entorno o agrégalas en tu archivo `application.properties`:

```properties
DB_URL=
DB_USERNAME=
DB_PASSWORD=

JWT_SECRET=
JWT_EXPIRATION=
```

### Ejecutar el proyecto

Con Maven:

```bash
mvn spring-boot:run
```

O generar el archivo JAR:

```bash
mvn clean package
```

## Ejemplo de respuesta de error

```json
{
  "status": 404,
  "code": "RESOURCE_NOT_FOUND",
  "message": "El recurso solicitado no fue encontrado.",
  "timestamp": "2026-07-15T14:30:15"
}
```

## Mejoras futuras

* Consolidar el proceso actual y ampliar la cobertura de casos de uso.
* Desarrollar pruebas unitarias y de integración.
* Incorporar el seguimiento de pagos asociados a las reservas.
* Definir y gestionar las fechas de reservas.
* Implementar el módulo de autoreservas.


