CREATE DATABASE pvp_db;

USE pvp_db;

CREATE TABLE usuario(
id INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
login VARCHAR(30) NOT NULL UNIQUE,
email VARCHAR(50) NOT NULL,
data_cadastro DATETIME NOT NULL,
twitter varchar(50),
hash_senha varchar(255),
ultimo_login DATETIME,
primary key(id)
);

CREATE TABLE permissao_usuario(
id INT NOT NULL AUTO_INCREMENT,
role varchar(50),
id_usuario int,
primary key(id),
foreign key(id_usuario) references usuario(id)
);

create table partido(
id int not null auto_increment,
nome varchar(50),
sigla varchar(10),
descricao varchar(200),
primary key(id)
);

create table politico(
id int not null auto_increment,
nome varchar(100),
id_partido int,
primary key(id),
foreign key(id_partido) references partido(id)
);

create table acao(
id int not null auto_increment,
nome varchar(100),
primary key(id)
);

create table partido_acao(
id int not null auto_increment,
id_acao int,
id_usuario_inclusao int,
id_usuario_alteracao int,
txt_valor_antigo varchar(50),
txt_valor_novo varchar(50),
dat_acao datetime,
primary key(id),
foreign key(id_usuario_inclusao) references usuario(id),
foreign key(id_usuario_alteracao) references usuario(id),
foreign key(id_acao) references acao(id)
);

create table politico_acao(
id int not null auto_increment,
id_acao int,
id_usuario_inclusao int,
id_usuario_alteracao int,
txt_valor_antigo varchar(50),
txt_valor_novo varchar(50),
dat_acao datetime,
primary key(id),
foreign key(id_usuario_inclusao) references usuario(id),
foreign key(id_usuario_alteracao) references usuario(id),
foreign key(id_acao) references acao(id)
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
id_politico int,
id_status_publicacao int,
id_usuario int,
primary key(id),
foreign key(id_politico) references politico(id),
foreign key(id_status_publicacao) references status_publicacao(id),
foreign key(id_usuario) references usuario(id)
);

