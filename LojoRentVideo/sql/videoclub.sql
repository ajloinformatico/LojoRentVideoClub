drop database if exists videoclub;
create database videoclub;
use videoclub;

#Usuer
drop table if exists users;
create table users(
	nick varchar(30) primary key,
	password varchar(20), #Controlar que minimo sea de 8 caracteres
    name varchar(60),
    surname varchar(60),
    mail varchar(60),
    saldo double,
    cliente_premium boolean
);

#Films
drop table if exists films;
create table films(
	cod_film int primary key,
    name_film varchar(60),
    price double,
    estreno boolean,
    n_copias int,
    genero enum('misterio', 'accion', 'intriga', 'romantica','terror','conduccion','fantasticas')
);

#Rent User-Films
drop table if exists rent;
create table rent(
	# cod_rent int auto_increment primary key,
	nick_fk varchar(30),
    cod_film_fk int,
    fecha varchar(20),
	
    #primary key
	# primary key(nick_fk, fecha),
    primary key(nick_fk, cod_film_fk),
    
    #Foreign keys
    constraint nick_fk foreign key (nick_fk) references users (nick) on delete cascade on update cascade,
    constraint cod_film_fk foreign key (cod_film_fk) references films (cod_film) on delete cascade on update cascade 
);