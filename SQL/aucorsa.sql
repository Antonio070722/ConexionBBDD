CREATE DATABASE AUCORSA;
USE AUCORSA;

CREATE TABLE CONDUCTORES(
    numeroConductor INT PRIMARY KEY,
    nombre VARCHAR(30),
    apellidos VARCHAR(30)
);

SELECT * FROM CONDUCTORES;

CREATE TABLE LUGARES(
    IdLugar INT PRIMARY KEY,
    site VARCHAR(50),
    cp VARCHAR(10),
    ciudad VARCHAR(30)
);
SELECT * FROM LUGARES;

SELECT IdLugar, cp, ciudad, site
FROM LUGARES;

--ALTER TABLE para renombrar las columnas mal nombradas
ALTER TABLE LUGARES CHANGE site site_temp VARCHAR(50);
ALTER TABLE LUGARES CHANGE cp ciudad VARCHAR(10);
ALTER TABLE LUGARES CHANGE ciudad site VARCHAR(30);
ALTER TABLE LUGARES CHANGE site_temp cp VARCHAR(50);

CREATE TABLE BUSES(
    Registro INT PRIMARY KEY,
    Licencia VARCHAR(20),
    Tipo VARCHAR(20)
);

ALTER TABLE BUSES
    MODIFY Registro VARCHAR(10);
    
    SELECT * FROM BUSES;
    
--ALTER TABLE para renombrar las columnas mal nombradas
-- 1. Renombrar 'Licencia' a un nombre temporal (ej: 'col_temp')
ALTER TABLE BUSES CHANGE Licencia col_temp VARCHAR(20);
-- 2. Renombrar 'Tipo' a 'Licencia'
ALTER TABLE BUSES CHANGE Tipo Licencia VARCHAR(20);
-- 3. Renombrar el temporal ('col_temp') a 'Tipo'
ALTER TABLE BUSES CHANGE col_temp Tipo VARCHAR(20);

CREATE TABLE BDP(
    Registro VARCHAR(10),
    numConductor INT,
    IdLugar INT,
    day_of_week VARCHAR(15),
    PRIMARY KEY (Registro, numConductor, IdLugar),
    FOREIGN KEY (Registro) REFERENCES BUSES(Registro),
    FOREIGN KEY (numConductor) REFERENCES CONDUCTORES(numeroConductor),
    FOREIGN KEY (IdLugar) REFERENCES LUGARES(IdLugar)
);

--Inserar datos de los autobuses
INSERT INTO BUSES VALUES
('B001', 'Urbano', 'LIC001'),
('B002', 'Interurbano', 'LIC002'),
('B003', 'Tursmo', 'LIC003'),
('B004', 'Escolar', 'LIC004'),
('B005', 'Urbano', 'LIC005'),
('B006', 'Turismo', 'LIC006');

--Insertar datos de los conductores
INSERT INTO CONDUCTORES VALUES
(101, 'Carlos', 'García'),
(102, 'Lucía', 'Perez'),
(103, 'Manuel', 'Martín'),
(104, 'Laura', 'López'),
(105, 'Javier', 'Sánchez'),
(106, 'Marta', 'Fernández');

--Insertas lugares
INSERT INTO LUGARES VALUES
(1, '14001', 'Córdoba', 'Centro'),
(2, '28013', 'Madrid', 'Sol'),
(3, '41001', 'Sevilla', 'Triana'),
(4, '08001', 'Barcelona', 'Gótico'),
(5, '46001', 'Valencia', 'Carmen'),
(6, '29001', 'Málaga', 'Soho');

--Insertar datos en la relación BDP
INSERT INTO BDP VALUES
('B001', 101, 1, 'Lunes'),
('B002', 102, 2, 'Martes'),
('B003', 103, 3, 'Miércoles'),
('B004', 104, 4, 'Jueves'),
('B005', 105, 5, 'Viernes'),
('B006', 106, 6, 'Lunes');

SELECT * FROM CONDUCTORES;

--Consultas
--1 Mostrar qué conductor conduce qué bus a qué lugar y en qué día
SELECT
    C.nombre AS Conductor,
    C.apellidos AS Apellidos,
    B.Licencia AS Bus,
    L.site AS Lugar,
    BDP.day_of_week AS Dia
FROM
    BDP
        JOIN
    CONDUCTORES C ON BDP.numConductor = C.numeroConductor
        JOIN
    BUSES B ON BDP.Registro = B.Registro
        JOIN
    LUGARES L ON BDP.IdLugar = L.IdLugar;
