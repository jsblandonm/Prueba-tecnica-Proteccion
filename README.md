Task Collab - API de Gestión de Tareas Colaborativas
Task Collab  API RESTful para la gestión de tareas colaborativas, construida con Java y Spring Boot. Proporciona un backend seguro y bien estructurado que permite a los usuarios gestionar tareas, asignarlas a otros miembros y realizar un seguimiento de su progreso.
Este proyecto fue desarrollado como una demostración técnica para evaluar las capacidades en el desarrollo de APIs seguras, estructuradas y mantenibles.
✨ Características Principales
Autenticación y Autorización Segura: Implementación de JSON Web Tokens (JWT) para proteger los endpoints.
Gestión de Roles: Sistema de roles simple (ADMIN, USER) para controlar los permisos de acceso.
Operaciones CRUD Completas: Endpoints para Crear, Leer, Actualizar y Eliminar tareas y usuarios.
Lógica de Negocio Clara: Separación de responsabilidades entre controladores, servicios y repositorios.
Base de Datos en Memoria: Configurado con H2 para un arranque y pruebas rápidas.
🛠️ Tecnologías Utilizadas
Backend
Java 17 
Spring Boot 3
Spring Security: Para la gestión de autenticación y JWT.
Spring Data JPA: Para la persistencia de datos.
Hibernate: Como implementación de JPA.
H2 Database: Base de datos en memoria para el entorno de desarrollo.
Lombok: Para reducir el código boilerplate.
Maven: Como gestor de dependencias y construcción del proyecto.
🚀 Cómo Empezar
Sigue estos pasos para levantar el proyecto en tu entorno local.
Prerrequisitos
JDK 17 o superior.
Apache Maven 3.8 o superior.
Un cliente de API como Postman o Insomnia para probar los endpoints.
(Opcional) Un IDE como IntelliJ IDEA o VS Code.
Clonación del Repositorio
Generated bash
git clone https://[github.com/jsblandonm]/Prueba-tecnica-Proteccion.git

La API estará disponible en http://localhost:4444.
Base de Datos H2
La aplicación utiliza una base de datos en memoria H2. Puedes acceder a su consola web mientras la aplicación está corriendo:
URL de la consola: http://localhost:4444/h2-console
JDBC URL: jdbc:h2:mem:db_tareascolaborativo
Username: root
Password: 1234
📖 Guía de la API
A continuación se muestra un resumen de los endpoints disponibles. Para acceder a los endpoints protegidos, primero debes registrarte o iniciar sesión para obtener un token JWT, y luego incluirlo en el encabezado de tus peticiones como Authorization: Bearer <tu-token>.
🔑 Autenticación
Registrar un Nuevo Usuario
Endpoint: POST /auth/register
Body:
Generated json
{
  "username": "nuevo_usuario",
  "password": "una_contraseña_segura"
}
Use code with caution.
Json
Respuesta: Un token JWT.
Iniciar Sesión
Endpoint: POST /auth/login
Body:
Generated json
{
  "username": "usuario_existente",
  "password": "su_contraseña"
}
Use code with caution.
Json
Respuesta: Un nuevo token JWT.
👤 Usuarios (Protegido)
Obtener Todos los Usuarios
Endpoint: GET /api/users
Respuesta: Una lista de todos los usuarios registrados.
Obtener un Usuario por ID
Endpoint: GET /api/users/{id}
Respuesta: Los detalles de un usuario específico.
✅ Tareas (Protegido)
Obtener Tareas del Usuario
Endpoint: GET /api/tasks
Descripción: Los administradores obtienen todas las tareas. Los usuarios normales solo obtienen las tareas que se les han asignado.
Respuesta: Una lista de tareas.
Crear una Nueva Tarea
Endpoint: POST /api/tasks
Body:
Generated json
{
  "title": "Primera tarea para la demo",
  "description": "tarea de ejemplo",
  "status": "PENDING",
  "createdAt": "2025-08-05T14:30:00",
  "assignedToId": 2
}
Use code with caution.
Json
Respuesta: La tarea recién creada.
Actualizar una Tarea
Endpoint: PUT /api/tasks/{id}
Body: Similar al de creación.
Respuesta: La tarea actualizada.
Eliminar una Tarea
Endpoint: DELETE /api/tasks/{id}
Respuesta: 200 OK si la eliminación fue exitosa.
