CREATE DATABASE pvp_db;

USE pvp_db;

CREATE TABLE tipo_usuario(
codigo INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
descricao VARCHAR(255));

CREATE TABLE usuario(
codigo INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
login VARCHAR(30) NOT NULL UNIQUE,
senha VARCHAR(50) NOT NULL,
cpf INT NOT NULL UNIQUE,
data_incricao DATETIME NOT NULL,
data_ultimo_acesso DATETIME,
ind_ativo CHAR NOT NULL,
cod_tipo_usuario INT NOT NULL,
FOREIGN KEY (cod_tipo_usuario) REFERENCES tipo_usuario (codigo));

CREATE TABLE permissao(
codigo INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
role VARCHAR(50) NOT NULL);

CREATE TABLE permissao_usuario(
cod_tipo_usuario INT,
cod_permissao INT,
FOREIGN KEY (cod_tipo_usuario) REFERENCES tipo_usuario (codigo),
FOREIGN KEY (cod_permissao) REFERENCES permissao (codigo));

CREATE TABLE status(
codigo INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
descricao VARCHAR(255));

CREATE TABLE estado(
codigo INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
sigla VARCHAR(2) NOT NULL);

CREATE TABLE cidade(
codigo INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
cod_estado INT NOT NULL,
FOREIGN KEY (cod_estado) REFERENCES estado (codigo));

CREATE TABLE partido(
codigo INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
sigla VARCHAR(2) NOT NULL);

CREATE TABLE tipo_cargo(
codigo INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
descricao VARCHAR(255));

CREATE TABLE candidato(
codigo INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL);

CREATE TABLE filiacao(
codigo INT PRIMARY KEY AUTO_INCREMENT,
cod_candidato INT NOT NULL,
cod_partido INT NOT NULL,
cod_cidade INT NOT NULL,
ind_ativa CHAR NOT NULL,
FOREIGN KEY (cod_candidato) REFERENCES candidato(codigo),
FOREIGN KEY (cod_partido) REFERENCES partido(codigo),
FOREIGN KEY (cod_cidade) REFERENCES cidade(codigo));

CREATE TABLE postagem(
codigo INT PRIMARY KEY AUTO_INCREMENT,
titulo VARCHAR(255) NOT NULL,
descricao LONGTEXT NOT NULL,
fonte TEXT NOT NULL,
cod_candidato INT,
data_inclusao DATETIME NOT NULL,
cod_usuario INT,
cod_status INT,
FOREIGN KEY (cod_candidato) REFERENCES candidato(codigo),
FOREIGN KEY (cod_usuario) REFERENCES usuario(codigo),
FOREIGN KEY (cod_status) REFERENCES status(codigo));

CREATE TABLE tipo_alteracao(
codigo INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
descricao VARCHAR(255));

CREATE TABLE postagem_auditoria(
codigo INT PRIMARY KEY AUTO_INCREMENT,
cod_postagem INT,
cod_usuario INT,
cod_tipo_alteracao INT,
valor_antigo LONGTEXT,
valor_novo LONGTEXT);
