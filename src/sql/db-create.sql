drop database IF exists education;
create database education;
use education;

create table student(
        id int unique not null auto_increment,
        email varchar(30) unique not null,
        name varchar(30) not null,
        Surname varchar(30) not null
);
create table course(
   login varchar(30) not null,
   course varchar(30) unique  not null
);
create table admin(
                        login varchar(30) unique not null,
                        password varchar(30) not null
);
create table teachers(
                      login varchar(30) unique not null,
                      password varchar(30) not null
);




