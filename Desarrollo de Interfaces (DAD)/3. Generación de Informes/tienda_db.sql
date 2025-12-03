-- Crea la base de datos tienda_db y las tablas productos y ventas
DROP DATABASE IF EXISTS tienda_db;
CREATE DATABASE IF NOT EXISTS tienda_db;
USE tienda_db;

-- Tabla de Productos
CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    precio DECIMAL(10, 2) NOT NULL,
    stock INT DEFAULT 0
);

-- Tabla de Ventas
CREATE TABLE IF NOT EXISTS ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT,
    cantidad INT NOT NULL,
    fecha_venta DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- Insertar Datos a Productos
INSERT INTO productos (nombre, categoria, precio, stock) VALUES
('Laptop Gamer X1', 'Electrónica', 1200.50, 15),
('Mouse Inalámbrico', 'Accesorios', 25.00, 100),
('Monitor 24 pulg', 'Electrónica', 180.00, 30),
('Teclado Mecánico', 'Accesorios', 85.00, 45),
('Silla Ergonómica', 'Muebles', 250.00, 10);

-- Insertar Datos a Ventas
INSERT INTO ventas (producto_id, cantidad, fecha_venta) VALUES
(1, 2, '2023-10-01'),
(1, 1, '2023-10-02'),
(2, 5, '2023-10-01'),
(2, 3, '2023-10-03'),
(3, 10, '2023-10-04'),
(5, 4, '2023-10-05');