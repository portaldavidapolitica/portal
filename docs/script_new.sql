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
id_acao int not null auto_increment,
nome varchar(100),
primary key(id_acao)
);

create table partido_acao(
id_partido_acao int not null auto_increment,
id_partido int,
id_acao int,
id_usuario_inclusao int,
id_usuario_alteracao int,
txt_valor_antigo varchar(50),
txt_valor_novo varchar(50),
dat_acao datetime,
primary key(id_partido_acao),
foreign key(id_partido) references partido(id),
foreign key(id_usuario_inclusao) references usuario(id),
foreign key(id_usuario_alteracao) references usuario(id),
foreign key(id_acao) references acao(id_acao)
);

create table politico_acao(
id_politico_acao int not null auto_increment,
id_politico int,
id_acao int,
id_usuario_inclusao int,
id_usuario_alteracao int,
txt_valor_antigo varchar(50),
txt_valor_novo varchar(50),
dat_acao datetime,
primary key(id_politico_acao),
foreign key(id_politico) references politico(id),
foreign key(id_usuario_inclusao) references usuario(id),
foreign key(id_usuario_alteracao) references usuario(id),
foreign key(id_acao) references acao(id_acao)
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

create table status_partido(
id_status_partido int not null auto_increment,
nome varchar(50),
descricao varchar(200),
primary key(id_status_partido)
);

insert into status_partido(nome,descricao) values('Aprovado','Partido aprovado pelo moderador'); 
insert into status_partido(nome,descricao) values('Reprovado','Partido reprovado pelo moderador'); 
insert into status_partido(nome,descricao) values('Aguardando aprovacao','Partido aguardando aprovacao pelo moderador'); 

alter table partido
add id_status_partido int;

alter table partido
add foreign key (id_status_partido) references status_partido(id_status_partido); 

create table status_politico(
id_status_politico int not null auto_increment,
nome varchar(50),
descricao varchar(200),
primary key(id_status_politico)
);

insert into status_politico(nome,descricao) values('Aprovado','Politico aprovado pelo moderador'); 
insert into status_politico(nome,descricao) values('Reprovado','Politico reprovado pelo moderador'); 
insert into status_politico(nome,descricao) values('Aguardando aprovacao','Politico aguardando aprovacao pelo moderador'); 

alter table politico
add id_status_politico int;

alter table politico
add foreign key (id_status_politico) references status_politico(id_status_politico)

