/* 
 * The MIT License
 *
 * Copyright 2017 David Delgado Hernandez 150205@upslp.edu.mx.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
/**
 * Author:  David Delgado Hernandez 150205@upslp.edu.mx
 * Created: 21/04/2017
 */

SET SESSION time_zone = 'America/Mexico_City';
SET GLOBAL time_zone = 'America/Mexico_City';

CREATE DATABASE callcenter;

USE callcenter;

CREATE TABLE USUARIO (
    CORREO VARCHAR(60) NOT NULL,
    NOMBRE VARCHAR(60) NOT NULL,
    APELLIDO VARCHAR(80) NOT NULL,
    FECHA_NACIMIENTO DATE NOT NULL,
    USERNAME VARCHAR(30) NOT NULL,
    PASSWORD VARCHAR(30) NOT NULL,
    NACIONALIDAD VARCHAR(30) NOT NULL,
    TURNO ENUM("MATUTINO","VESPERTINO","NOCTURNO") NOT NULL,
    ADMINISTRADOR BOOLEAN NOT NULL DEFAULT '0',
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (CORREO)
) ;

CREATE TABLE CLIENTE(
    CORREO VARCHAR(60) NOT NULL,
    NOMBRE VARCHAR(60) NOT NULL,
    APELLIDO VARCHAR(60) NOT NULL,
    DIRECCION VARCHAR(60) NOT NULL,
    EDAD DATE NOT NULL,
    TELEFONO VARCHAR(6) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (CORREO)    
);

CREATE TABLE INCIDENCIA (
    ID_INCIDENCIA INT NOT NULL AUTO_INCREMENT,
    TIPO ENUM ("QUEJA","SUGERENCIA") NOT NULL,
    IMPORTANCIA ENUM ("EN ESPERA","URGENTE") NOT NULL,
    DESCRIPCION TEXT NOT NULL,
    ID_USUARIO VARCHAR(60) NOT NULL,
    ID_CLIENTE VARCHAR(60) NOT NULL,
    FECHA DATE NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (ID_INCIDENCIA),    
    FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(CORREO),
    FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(CORREO)
);

CREATE TABLE MOVIMIENTO (
    ID_MOVIMIENTO INT NOT NULL AUTO_INCREMENT,
    ID_INCIDENCIA INT,
    ID_USUARIO VARCHAR(60) NOT NULL,
    TIPO ENUM("ALTA","BAJA","MODIFICACION") NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (ID_MOVIMIENTO),
    FOREIGN KEY (ID_INCIDENCIA) REFERENCES INCIDENCIA(ID_INCIDENCIA),
    FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO (CORREO)
);

INSERT INTO USUARIO (
    CORREO,
    NOMBRE,
    APELLIDO,
    FECHA_NACIMIENTO,
    USERNAME,
    PASSWORD,
    NACIONALIDAD,
    TURNO,
    ADMINISTRADOR,
    created_at,
    updated_at) VALUES (
        "david@example.com",
        "david",
        "delgado",
        "1996-11-12",
        "david",
        "pass",
        "mexico",
        "MATUTINO",
        "1",
        "1996-11-12",
        "1996-11-12"
    );