-- Esquema de referencia para la base de datos m6_capacitaciones
-- Este archivo es solo documental. La aplicación genera las tablas con JPA (ddl-auto=update)
-- Se incluye para evidenciar el diseño relacional.

CREATE TABLE instructores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    especialidad VARCHAR(255)
);

CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(500),
    fecha_inicio DATE,
    fecha_fin DATE,
    cupo_maximo INT,
    activo BIT,
    instructor_id BIGINT,
    CONSTRAINT fk_curso_instructor FOREIGN KEY (instructor_id)
        REFERENCES instructores(id)
);

CREATE TABLE empleados (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    rol VARCHAR(50)
);

CREATE TABLE inscripciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_inscripcion DATETIME,
    curso_id BIGINT,
    empleado_id BIGINT,
    CONSTRAINT fk_inscripcion_curso FOREIGN KEY (curso_id) REFERENCES cursos(id),
    CONSTRAINT fk_inscripcion_empleado FOREIGN KEY (empleado_id) REFERENCES empleados(id)
);
