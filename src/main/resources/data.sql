INSERT INTO instructores (nombre, email, especialidad)
VALUES ('Juan Pérez', 'juan.perez@empresa.com', 'Liderazgo');

INSERT INTO cursos (nombre, descripcion, fecha_inicio, fecha_fin, cupo_maximo, activo, instructor_id)
VALUES (
    'Curso de Liderazgo',
    'Desarrollo de habilidades de liderazgo para mandos medios.',
    '2025-01-10',
    '2025-01-15',
    20,
    true,
    1
);

INSERT INTO empleados (nombre, email, password, rol)
VALUES ('Empleado Prueba', 'empleado@empresa.com', 'dummy', 'EMPLEADO');
