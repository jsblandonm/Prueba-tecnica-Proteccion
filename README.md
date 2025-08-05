# 🧩 Task Collab - API de Gestión de Tareas Colaborativas

![Java](https://img.shields.io/badge/java-17-orange.svg)
![Spring Boot](https://img.shields.io/badge/spring%20boot-3.0-brightgreen.svg)
![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

Task Collab es una API RESTful desarrollada en Java con Spring Boot, diseñada para gestionar tareas de forma colaborativa. Ofrece un backend seguro y bien estructurado que permite a los usuarios crear, asignar y dar seguimiento a tareas.

Este proyecto fue creado como una demostración técnica para evaluar capacidades en el desarrollo de APIs seguras, mantenibles y bien estructuradas.

## 📋 Tabla de Contenidos

- [Características Principales](#-características-principales)
- [Tecnologías Utilizadas](#️-tecnologías-utilizadas)
- [Estructura del Proyecto](#️-estructura-del-proyecto)
- [Instalación](#-instalación)
- [Configuración](#️-configuración)
- [Uso de la API](#-uso-de-la-api)
- [Ejemplos de Peticiones](#-ejemplos-de-peticiones)
- [Testing](#-testing)
- [Contribuir](#-contribuir)
- [Licencia](#-licencia)

## ✨ Características Principales

### 🔐 Autenticación y Autorización Segura
- Uso de JSON Web Tokens (JWT) para proteger los endpoints
- Passwords encriptados con BCrypt

### 👥 Gestión de Roles
- Sistema de roles (ADMIN, USER) para controlar permisos de acceso
- Autorización granular por endpoint

### 🔄 Operaciones CRUD Completas
- Endpoints para crear, leer, actualizar y eliminar tareas y usuarios
- Validaciones de entrada y manejo de errores

### 🧠 Lógica de Negocio Clara
- Separación de responsabilidades entre controladores, servicios y repositorios
- Arquitectura en capas bien definida

### 🗃️ Base de Datos en Memoria
- Configurada con H2 para facilitar pruebas y arranque rápido
- Datos de prueba precargados

## 🛠️ Tecnologías Utilizadas

- **Java 17** - Lenguaje de programación
- **Spring Boot 3** - Framework principal
- **Spring Security** - Autenticación y JWT
- **Spring Data JPA** - Persistencia de datos
- **Hibernate** - Implementación de JPA
- **H2 Database** - Base de datos en memoria
- **Lombok** - Reducción de boilerplate
- **Maven** - Gestión de dependencias y construcción
- **Swagger/OpenAPI** - Documentación de API

## 🏗️ Estructura del Proyecto

```
src/
├── main/java/com/taskcollab/
│   ├── config/          # Configuraciones (Security, JWT, etc.)
│   ├── controller/      # Controladores REST
│   ├── service/         # Lógica de negocio
│   ├── repository/      # Acceso a datos
│   ├── model/           # Entidades JPA
│   ├── dto/             # Data Transfer Objects
├── main/resources/
│   ├── application.yml  # Configuración de la aplicación
│   └── data.sql         # Datos de prueba
└── test/                # Pruebas unitarias e integración
```

## 🚀 Instalación

### 🔧 Prerrequisitos

- **JDK 17** o superior
- **Apache Maven 3.8** o superior
- Un cliente API como **Postman** o **Insomnia**
- (Opcional) Un IDE como **IntelliJ IDEA** o **VS Code**

### 📥 Clonar el Repositorio

```bash
git clone https://github.com/jsblandonm/Prueba-tecnica-Proteccion.git
cd Prueba-tecnica-Proteccion
```

### 📦 Instalar Dependencias

```bash
mvn clean install
```

### ▶️ Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

La aplicación se ejecutará en: `http://localhost:4444`

## ⚙️ Configuración

### 🌐 URLs Importantes

- **API Base URL**: `http://localhost:4444`
- **Documentación Swagger**: `http://localhost:4444/swagger-ui.html`
- **Consola H2**: `http://localhost:4444/h2-console`

### 🗃️ Base de Datos H2

La consola está disponible mientras la aplicación está corriendo:

- **URL**: `http://localhost:4444/h2-console`
- **JDBC URL**: `jdbc:h2:mem:db_tareascolaborativo`
- **Usuario**: `root`
- **Contraseña**: `1234`

### 👤 Usuarios Predeterminados

La aplicación incluye usuarios de prueba:

| Usuario | Contraseña | Rol |
|---------|------------|-----|
| admin   | admin123   | ADMIN |
| user1   | user123    | USER |

## 📖 Uso de la API

### 🔐 Autenticación

⚠️ **Importante**: Para acceder a los endpoints protegidos, primero debes registrarte o iniciar sesión para obtener un token JWT.

Incluye el token en tus peticiones como:
```
Authorization: Bearer <tu_token_jwt>
```

## 📝 Ejemplos de Peticiones

### 🔑 Autenticación

#### Registrar un Nuevo Usuario
```http
POST /auth/register
Content-Type: application/json

{
  "username": "nuevo_usuario",
  "password": "una_contraseña_segura"
}
```

**Respuesta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "nuevo_usuario",
  "roles": ["USER"]
}
```

#### Iniciar Sesión
```http
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

**Respuesta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "admin",
  "roles": ["ADMIN"]
}
```

### 👤 Gestión de Usuarios

#### Obtener Todos los Usuarios
```http
GET /api/users
Authorization: Bearer <token>
```

**Respuesta:**
```json
[
  {
    "id": 1,
    "username": "admin",
    "roles": ["ADMIN"],
    "createdAt": "2025-08-05T10:00:00"
  }
]
```

#### Obtener Usuario por ID
```http
GET /api/users/{id}
Authorization: Bearer <token>
```

### ✅ Gestión de Tareas

#### Obtener Tareas del Usuario
```http
GET /api/tasks
Authorization: Bearer <token>
```

**Comportamiento:**
- **ADMIN**: Ve todas las tareas del sistema
- **USER**: Ve solo sus tareas asignadas

**Respuesta:**
```json
[
  {
    "id": 1,
    "title": "Primera tarea para la demo",
    "description": "tarea de ejemplo",
    "status": "PENDING",
    "createdAt": "2025-08-05T14:30:00",
    "assignedTo": {
      "id": 2,
      "username": "user1"
    },
    "createdBy": {
      "id": 1,
      "username": "admin"
    }
  }
]
```

#### Crear una Nueva Tarea
```http
POST /api/tasks
Authorization: Bearer <token>
Content-Type: application/json

{
  "title": "Primera tarea para la demo",
  "description": "tarea de ejemplo",
  "status": "PENDING",
  "assignedToId": 2
}
```

**Estados Válidos:** `PENDING`, `IN_PROGRESS`, `COMPLETED`

#### Actualizar una Tarea
```http
PUT /api/tasks/{id}
Authorization: Bearer <token>
Content-Type: application/json

{
  "title": "Tarea actualizada",
  "description": "descripción actualizada",
  "status": "IN_PROGRESS",
  "assignedToId": 2
}
```

#### Eliminar una Tarea
```http
DELETE /api/tasks/{id}
Authorization: Bearer <token>
```

**Respuesta:** `200 OK` si la eliminación fue exitosa

**Error de Base de Datos:**
- Verificar que H2 esté correctamente configurado
- Revisar logs en consola

**Token JWT Expirado:**
- Volver a hacer login para obtener un nuevo token
- Los tokens expiran en 24 horas por defecto


### Estándares de Código
- Seguir las convenciones de Java
- Escribir pruebas para nuevas funcionalidades
- Documentar métodos públicos


## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

## 📞 Contacto

**Desarrollador**: Johan Sebastián Blandón Montoya  
**GitHub**: [@jsblandonm](https://github.com/jsblandonm)  
**Proyecto**: [Task Collab](https://github.com/jsblandonm/Prueba-tecnica-Proteccion)

---

⭐ **¡Dale una estrella si este proyecto te fue útil!** ⭐
