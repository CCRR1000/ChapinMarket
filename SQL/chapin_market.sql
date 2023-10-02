
CREATE DATABASE chapinMarket;

\c chapinmarket

CREATE SCHEMA Sucursales;
CREATE SCHEMA Stock;
CREATE SCHEMA GestionVenta;
CREATE SCHEMA Personal;



CREATE TABLE Sucursales.Sucursal (
    id VARCHAR(4) NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Personal.Empleado (
    usuario VARCHAR(10) NOT NULL,
    contrasena VARCHAR(20) NOT NULL,
    cui VARCHAR(20) UNIQUE NOT NULL ,
    rol VARCHAR(20) NOT NULL,
    sucursal VARCHAR(4),
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    salario DECIMAL(8,2) NOT NULL,
    numero_caja INT,
    PRIMARY KEY (usuario),
    FOREIGN KEY(sucursal) REFERENCES Sucursales.Sucursal(id)
);

CREATE TABLE Stock.Producto (
    codigo_barras VARCHAR(15) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    precio DECIMAL(8,2) NOT NULL,
    descripcion TEXT NOT NULL,
    PRIMARY KEY(codigo_barras)
);

CREATE TABLE Stock.Existencia (
    producto VARCHAR(15) NOT NULL,
    sucursal VARCHAR(4) NOT NULL,
    cantidad_bodega INT NOT NULL,
    cantidad_estante INT,
    numero_pasillo INT,
    PRIMARY KEY(producto,sucursal),
    FOREIGN KEY(producto) REFERENCES Stock.Producto(codigo_barras),
    FOREIGN KEY(sucursal) REFERENCES Sucursales.Sucursal(id)
);

CREATE TABLE GestionVenta.Cliente (
    nit VARCHAR(20) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    direccion VARCHAR(100),
    total_gastado DECIMAL(10,2),
    consumo_con_tarjeta DECIMAL(10,2),
    PRIMARY KEY (nit)
);

CREATE TABLE GestionVenta.Tarjeta (
    numero_tarjeta SERIAL NOT NULL,
    categoria VARCHAR(10) NOT NULL,
    cliente VARCHAR(20) NOT NULL,
    puntos INT NOT NULL,
    PRIMARY KEY (numero_tarjeta),
    FOREIGN KEY (cliente) REFERENCES GestionVenta.Cliente(nit)
);

CREATE TABLE GestionVenta.Factura (
    noDocumento SERIAL NOT NULL,
    cliente VARCHAR(20) NOT NULL,
    fecha DATE NOT NULL,
    total_consumo DECIMAL(8,2) NOT NULL,
    total_con_descuento DECIMAL(8,2) NOT NULL,
    total_a_pagar DECIMAL(8,2) NOT NULL,
    realizo_descuento BOOLEAN NOT NULL,
    cajero VARCHAR(10) NOT NULL,
    sucursal VARCHAR(4) NOT NULL,
    PRIMARY KEY (noDocumento),
    FOREIGN KEY (cliente) REFERENCES GestionVenta.Cliente(nit),
    FOREIGN KEY (cajero) REFERENCES Personal.Empleado(usuario),
    FOREIGN KEY (sucursal) REFERENCES Sucursales.Sucursal(id)
);

CREATE TABLE GestionVenta.Venta (
    id SERIAL NOT NULL,
    factura INT NOT NULL,
    producto VARCHAR(15) NOT NULL,
    monto DECIMAL(8,2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (factura) REFERENCES GestionVenta.Factura(noDocumento),
    FOREIGN KEY (producto) REFERENCES Stock.Producto(codigo_barras)
);



