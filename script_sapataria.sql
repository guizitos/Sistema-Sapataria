CREATE DATABASE IF NOT EXISTS sapataria;

USE sapataria;

CREATE TABLE IF NOT EXISTS Calcados (
    codigo_calcado INT PRIMARY KEY,
    nome VARCHAR(100),
    marca VARCHAR(50),
    valor DOUBLE,
    cor VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS Categorias (
    codigo_categoria INT PRIMARY KEY,
    nome_categoria VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Estoque (
    codigo_estoque INT PRIMARY KEY,
    codigo_calcado INT,
    quantidade INT,
    FOREIGN KEY (codigo_calcado) REFERENCES Calcados(codigo_calcado)
);

CREATE TABLE IF NOT EXISTS Vendedores (
    codigo_vendedor INT PRIMARY KEY,
    nome_vendedor VARCHAR(100),
    email_vendedor VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Vendas (
    codigo_venda INT PRIMARY KEY,
    data_venda DATE,
    codigo_calcado INT,
    codigo_vendedor INT,
    quantidade_vendida INT,
    valor_total DOUBLE,
    FOREIGN KEY (codigo_calcado) REFERENCES Calcados(codigo_calcado),
    FOREIGN KEY (codigo_vendedor) REFERENCES Vendedores(codigo_vendedor)
);

-- Relacionamento entre Calcados e Categorias 
CREATE TABLE IF NOT EXISTS Calcados_Categorias (
    codigo_calcado INT,
    codigo_categoria INT,
    PRIMARY KEY (codigo_calcado, codigo_categoria),
    FOREIGN KEY (codigo_calcado) REFERENCES Calcados(codigo_calcado),
    FOREIGN KEY (codigo_categoria) REFERENCES Categorias(codigo_categoria)
);

INSERT INTO Categorias (codigo_categoria, nome_categoria)
VALUES (1, 'Esportivo'), 
       (2, 'Casual'),
       (3, 'Social');

INSERT INTO Calcados (codigo_calcado, nome, marca, valor, cor)
VALUES (1, 'Tênis Running', 'Nike', 299.99, 'Preto'),
       (2, 'Sapatênis Casual', 'Adidas', 249.99, 'Branco'),
       (3, 'Sapato Social', 'Ferracini', 399.99, 'Marrom');

INSERT INTO Estoque (codigo_estoque, codigo_calcado, quantidade)
VALUES (1, 1, 50),
       (2, 2, 30),
       (3, 3, 20);

INSERT INTO Calcados_Categorias (codigo_calcado, codigo_categoria)
VALUES (1, 1), 
       (2, 2), 
       (3, 3); 



