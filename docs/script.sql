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


create table acao(
id int not null auto_increment,
nome varchar(100),
primary key(id)
);

create table partido_acao(
id int not null auto_increment,
acao_id int,
usuarioInclusao_id int,
usuarioAlteracao_id int,
txt_valor_antigo varchar(50),
txt_valor_novo varchar(50),
dat_acao datetime,
primary key(id),
foreign key(usuarioInclusao_id) references usuario(id),
foreign key(usuarioAlteracao_id) references usuario(id),
foreign key(acao_id) references acao(id)
);

create table politico_acao(
id int not null auto_increment,
acao_id int,
usuarioInclusao_id int,
usuarioAlteracao_id int,
txt_valor_antigo varchar(50),
txt_valor_novo varchar(50),
dat_acao datetime,
primary key(id),
foreign key(usuarioInclusao_id) references usuario(id),
foreign key(usuarioAlteracao_id) references usuario(id),
foreign key(acao_id) references acao(id)
);

create table partido(
id int not null auto_increment,
nome varchar(50),
sigla varchar(10),
descricao varchar(200),
acao_id int,
primary key(id),
foreign key(acao_id) references partido_acao
);

create table politico(
id int not null auto_increment,
nome varchar(100),
acao_id int,
primary key(id),
foreign key(partido_id) references partido(id),
foreign key(acao_id) references politico_acao     
);

create table status_publicacao(
id int not null auto_increment,
nome varchar(50),
descricao varchar(200),
primary key(id)
);


insert into status_publicacao(nome,descricao) values('Aprovado','Publicacao aprovada pelo moderador'); 
insert into status_publicacao(nome,descricao) values('Reprovado','Publicacao reprovada pelo moderador'); 
insert into status_publicacao(nome,descricao) values('Aguardando aprovacao','Publicacao aguardando aprovacao pelo moderador'); 
insert into acao(nome) values('Cadastrar Partido');
insert into acao(nome) values('Editar Partido');
insert into acao(nome) values('Excluir Partido');
insert into acao(nome) values('Cadastrar Politico');
insert into acao(nome) values('Editar Politico');
insert into acao(nome) values('Excluir Politico');


create table publicacao(
id int not null auto_increment,
titulo varchar(100),
texto varchar(500),
politico_id int,
statusPublicacao_id int,
usuario_id int,
primary key(id),
foreign key(politico_id) references politico(id),
foreign key(statusPublicacao_id) references status_publicacao(id),
foreign key(usuario_id) references usuario(id)
);

