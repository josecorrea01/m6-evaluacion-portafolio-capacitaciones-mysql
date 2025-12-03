# M6 - Evaluación de Portafolio: Sistema de Capacitaciones (Spring Boot + MySQL)

## 1. Datos del estudiante y docentes

- **Estudiante:** _[Tu nombre completo]_
- **Bootcamp:** Desarrollo Full Stack Java
- **Módulo:** M6 - Aplicaciones Web con Spring Boot
- **Docente principal:** _[Nombre docente]_  
- **Ayudante / Co-docente:** _[Nombre ayudante, si aplica]_

## 2. Descripción general del proyecto

Aplicación web interna para la gestión de **cursos de capacitación** de una empresa.  
Permite:

- Panel **ADMIN** para gestionar cursos e instructores.
- Panel **EMPLEADO** para ver cursos disponibles e inscribirse.
- **Autenticación y autorización** con Spring Security (roles `ADMIN` y `EMPLEADO`).
- Exposición de **servicios REST** para interoperabilidad con otros sistemas.
- Uso de **MySQL** como base de datos relacional.
- Gestión del ciclo de vida del proyecto mediante un tablero en **GitHub Projects / Trello / Jira** (evidencias en `docs/gestor_proyecto`).

## 3. Tecnologías principales

- **Backend:** Java 17, Spring Boot 3.3.4
- **Web MVC:** Spring Web, Spring MVC, Thymeleaf
- **Seguridad:** Spring Security (form login + HTTP Basic para REST)
- **Persistencia:** Spring Data JPA, MySQL 8.x
- **Vista:** HTML5, Bootstrap 5, CSS personalizado
- **Build:** Maven

## 4. Configuración de la base de datos MySQL

1. Ingresar a **MySQL Workbench** o consola de MySQL.
2. Ejecutar los siguientes comandos para crear la base de datos y el usuario para la aplicación:

```sql
CREATE DATABASE m6_capacitaciones
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE USER 'capacitaciones_user'@'localhost'
  IDENTIFIED BY 'Capacitaciones123';

GRANT ALL PRIVILEGES
  ON m6_capacitaciones.*
  TO 'capacitaciones_user'@'localhost';

FLUSH PRIVILEGES;
```

3. Verifica que puedes conectarte con ese usuario desde MySQL Workbench.

## 5. Configuración de la aplicación (Spring Boot)

El archivo `src/main/resources/application.properties` ya viene configurado así:

```properties
spring.application.name=M6Capacitaciones
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/m6_capacitaciones?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Santiago
spring.datasource.username=capacitaciones_user
spring.datasource.password=Capacitaciones123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.thymeleaf.cache=false
```

> **Nota:** No necesitas tu usuario personal de MySQL; la aplicación usará el usuario `capacitaciones_user`.

## 6. Usuarios y roles (Spring Security)

La configuración de seguridad (`SecurityConfig`) define **dos usuarios en memoria**:

- Admin:
  - Usuario: `admin@empresa.com`
  - Password: `admin123`
  - Rol: `ROLE_ADMIN`
- Empleado:
  - Usuario: `empleado@empresa.com`
  - Password: `empleado123`
  - Rol: `ROLE_EMPLEADO`

Reglas principales:

- Rutas `/admin/**` → solo `ADMIN`
- Rutas `/empleado/**` → solo `EMPLEADO`
- Rutas `/api/**` → cualquier usuario autenticado (se recomienda usar HTTP Basic en Postman)

## 7. Estructura de paquetes y capas

- `com.skillnest.m6.capacitaciones`
  - `M6CapacitacionesApplication` → clase main
  - `controller`
    - `HomeController` → vista principal `/`
    - `LoginController` → pantalla de login `/login`
    - `AdminCursoController` → panel admin `/admin/cursos`
    - `EmpleadoCursoController` → panel empleado `/empleado/cursos`
  - `controller.api`
    - `CursoRestController` → `GET /api/cursos`
    - `InscripcionRestController` → `POST /api/inscripciones`
    - `CursoDto`, `InscripcionRequest` → DTOs
  - `model`
    - `Curso`, `Empleado`, `Instructor`, `Inscripcion` → entidades JPA
  - `repository`
    - `CursoRepository`, `EmpleadoRepository`, `InstructorRepository`, `InscripcionRepository`
  - `security`
    - `SecurityConfig` → configuración de Spring Security

## 8. Vistas Thymeleaf

- `templates/index.html` → Home luego del login (cards para ADMIN / EMPLEADO)
- `templates/login.html` → login personalizado
- `templates/admin/cursos/lista.html` → tabla de cursos para administrador
- `templates/admin/cursos/form.html` → formulario para crear/editar curso
- `templates/empleado/cursos/lista.html` → cards de cursos para empleado

CSS principal:

- `src/main/resources/static/css/styles.css`

## 9. Datos iniciales de ejemplo

El archivo `src/main/resources/data.sql` inserta datos de prueba:

- Un instructor demo (`Juan Pérez`)
- Un curso demo (`Curso de Liderazgo`)
- Un empleado de pruebas (`empleado@empresa.com`)

Esto permite probar rápidamente:

- Listado de cursos en el panel admin
- Listado de cursos en el panel empleado
- Inscripciones desde la vista del empleado
- Consumo del endpoint `GET /api/cursos`

## 10. Pasos para ejecutar y probar

1. Crear base de datos y usuario MySQL (ver sección 4).
2. Importar el proyecto en **IntelliJ IDEA** como proyecto Maven.
3. Esperar a que se descarguen las dependencias.
4. Ejecutar la clase `M6CapacitacionesApplication`.
5. Verificar en la consola que Spring Boot inicia sin errores.
6. Abrir el navegador y entrar a: `http://localhost:8080/`
7. Iniciar sesión como:
   - Admin → `admin@empresa.com` / `admin123`
   - Empleado → `empleado@empresa.com` / `empleado123`

### 10.1. Evidencias sugeridas (capturas)

Guarda tus capturas en `docs/capturas/` con nombres como:

- `01_consola_spring_boot_ok.png`
- `02_login_spring_security.png`
- `03_admin_lista_cursos.png`
- `04_admin_form_nuevo_curso.png`
- `05_admin_curso_creado.png`
- `06_empleado_lista_cursos.png`
- `07_empleado_inscripcion_exitosa.png`
- `08_mysql_inscripciones.png`
- `09_error_403_acceso_no_autorizado.png`
- `10_mysql_tablas_principales.png`
- `11_postman_get_cursos.png`
- `12_postman_post_inscripciones.png`

Luego puedes enlazarlas en este mismo README si la plataforma lo permite.

## 11. Servicios REST

- `GET /api/cursos`
  - Devuelve JSON con lista de cursos disponibles.
  - Protegido por Spring Security (HTTP Basic).
- `POST /api/inscripciones`
  - Crea una inscripción a partir de un JSON con `cursoId` y `empleadoId`.
  - Protegido por Spring Security (HTTP Basic).

Ejemplo de cuerpo para `POST /api/inscripciones`:

```json
{
  "cursoId": 1,
  "empleadoId": 1
}
```

## 12. Reflexión final (para completar)

> Aquí puedes escribir tu reflexión personal sobre:
>
> - Qué aprendiste con este proyecto.
> - Dificultades técnicas que enfrentaste (MySQL, seguridad, templates, etc.).
> - Cómo este proyecto se relaciona con tu futuro perfil profesional.
> - Qué mejorarías en una versión futura (por ejemplo: autenticación basada en BD, JWT, front más robusto, etc.).
