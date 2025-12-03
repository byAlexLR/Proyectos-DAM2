-- Crea la base de datos empresa y la tabla empleados
DROP DATABASE IF EXISTS empresa_db;
CREATE DATABASE IF NOT EXISTS empresa_db;
USE empresa_db;

-- Tabla de Empleados
CREATE TABLE IF NOT EXISTS empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    puesto VARCHAR(50) NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    fecha_contratacion DATE
);

-- Insertar Datos a Empleados
INSERT INTO empleados (nombre, puesto, salario, fecha_contratacion) VALUES
('Ana García', 'Desarrollador Senior', 3500.00, '2020-01-15'),
('Carlos López', 'Desarrollador Junior', 1800.00, '2022-03-10'),
('María Rodriguez', 'Gerente de Proyectos', 4500.00, '2019-07-20'),
('Juan Pérez', 'Desarrollador Senior', 3600.00, '2020-05-12'),
('Lucía Mendez', 'Diseñador UI/UX', 2200.00, '2021-11-01'),
('Sofía Ruiz', 'Desarrollador Junior', 1900.00, '2022-08-15'),
('Pedro Sánchez', 'Analista de Datos', 2800.00, '2021-02-25'),
('Elena Torres', 'Gerente de Proyectos', 4700.00, '2018-12-10');