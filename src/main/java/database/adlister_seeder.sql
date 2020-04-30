drop database if exists adlister_db;

create database adlister_db;

use adlister_db;

create table users (
                          id int unsigned not null auto_increment primary key,
                          username varchar(50),
                          email varchar(50),
                          password varchar(30),
                          unique (username, email)
);

create table ads (
                       id int unsigned not null auto_increment primary key,
                       user_id int unsigned not null,
                       title varchar(50),
                       description varchar(255),
                       foreign key (user_id) references users (id)
);