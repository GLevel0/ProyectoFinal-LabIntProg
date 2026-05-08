CREATE DATABASE uasd;

USE uasd;

CREATE TABLE escuelas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100)
);

CREATE TABLE carreras (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(150),
    id_escuela INT,
    FOREIGN KEY (id_escuela) REFERENCES escuelas(id)
);

INSERT INTO escuelas(nombre) VALUES
('Ingeniería'),
('Negocios'),
('Artes'),
('Salud');

INSERT INTO carreras(nombre, id_escuela) VALUES
('Ingeniería en Sistemas', 1),
('Ingeniería Civil', 1),
('Ingeniería Industrial', 1),

('Administración de Empresas', 2),
('Contabilidad', 2),
('Mercadeo', 2),

('Diseño Gráfico', 3),
('Música', 3),

('Medicina', 4),
('Odontología', 4);
