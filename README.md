🧩 Task Collab - API de Gestión de Tareas Colaborativas
Task Collab es una API RESTful desarrollada en Java con Spring Boot, diseñada para gestionar tareas de forma colaborativa. Ofrece un backend seguro y bien estructurado que permite a los usuarios crear, asignar y dar seguimiento a tareas.
Este proyecto fue creado como una demostración técnica para evaluar capacidades en el desarrollo de APIs seguras, mantenibles y bien estructuradas.

✨ Características Principales
🔐 Autenticación y Autorización Segura
Uso de JSON Web Tokens (JWT) para proteger los endpoints.

👥 Gestión de Roles
Sistema de roles (ADMIN, USER) para controlar permisos de acceso.

🔄 Operaciones CRUD Completas
Endpoints para crear, leer, actualizar y eliminar tareas y usuarios.

🧠 Lógica de Negocio Clara
Separación de responsabilidades entre controladores, servicios y repositorios.

🗃️ Base de Datos en Memoria
Configurada con H2 para facilitar pruebas y arranque rápido.

🛠️ Tecnologías Utilizadas
Java 17

Spring Boot 3

Spring Security – Autenticación y JWT.

Spring Data JPA – Persistencia de datos.

Hibernate – Implementación de JPA.

H2 Database – Base de datos en memoria.

Lombok – Reducción de boilerplate.

Maven – Gestión de dependencias y construcción.

🚀 Cómo Empezar
🔧 Prerrequisitos
JDK 17 o superior

Apache Maven 3.8 o superior

Un cliente API como Postman o Insomnia

(Opcional) Un IDE como IntelliJ IDEA o VS Code

📥 Clonar el Repositorio

git clone https://github.com/jsblandonm/Prueba-tecnica-Proteccion.git

▶️ Levantar la Aplicación
La API estará disponible en:
http://localhost:4444


La API estará disponible en:
http://localhost:4444

🗃️ Consola de Base de Datos H2
Disponible mientras la aplicación está corriendo:

URL: http://localhost:4444/h2-console

JDBC URL: jdbc:h2:mem:db_tareascolaborativo

Usuario: root

Contraseña: 1234

📖 Guía de la API
⚠️ Para acceder a los endpoints protegidos, primero debes registrarte o iniciar sesión para obtener un token JWT.
Luego inclúyelo en tus peticiones como:
Authorization: Bearer <token>

🔑 Autenticación
Registrar un Nuevo Usuario
Endpoint: POST /auth/register

Body:

{
  "username": "nuevo_usuario",
  "password": "una_contraseña_segura"
}

Respuesta: Token JWT

Iniciar Sesión
Endpoint: POST /auth/login

Body:

{
  "username": "usuario_existente",
  "password": "su_contraseña"
}

Respuesta: Token JWT

👤 Usuarios (Protegido)
Obtener Todos los Usuarios
Endpoint: GET /api/users

Respuesta: Lista de usuarios registrados

Obtener Usuario por ID
Endpoint: GET /api/users/{id}

Respuesta: Detalles del usuario

✅ Tareas (Protegido)
Obtener Tareas del Usuario
Endpoint: GET /api/tasks

Descripción:

ADMIN: ve todas las tareas

USER: ve solo sus tareas asignadas

Respuesta: Lista de tareas

Crear una Nueva Tarea
Endpoint: POST /api/tasks

Body:

{
  "title": "Primera tarea para la demo",
  "description": "tarea de ejemplo",
  "status": "PENDING",
  "createdAt": "2025-08-05T14:30:00",
  "assignedToId": 2
}


Respuesta: Tarea creada

Actualizar una Tarea
Endpoint: PUT /api/tasks/{id}

Body: Igual al de creación

Respuesta: Tarea actualizada

Eliminar una Tarea
Endpoint: DELETE /api/tasks/{id}

Respuesta: 200 OK si la eliminación fue exitosa
