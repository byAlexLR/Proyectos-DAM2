CREATE DATABASE tienda_db;
USE tienda_db;

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    categoria VARCHAR(50),
    precio DECIMAL(10, 2),
    stock INT
);

INSERT INTO productos (nombre, categoria, precio, stock) VALUES 
('Laptop Gamer', 'Electrónica', 1200.50, 10),
('Ratón Óptico', 'Accesorios', 25.00, 50),
('Teclado Mecánico', 'Accesorios', 80.00, 30),
('Monitor 24"', 'Electrónica', 150.00, 15),
('Silla Ergonómica', 'Muebles', 200.00, 5);