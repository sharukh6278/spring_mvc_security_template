DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;


CREATE TABLE employee (empId VARCHAR(10) NOT NULL,empName VARCHAR(100) NOT NULL);

create table users (username varchar(50) not null primary key,password varchar(120) not null,enabled NUMBER(1) DEFAULT 0 NOT NULL);

create table authorities (username varchar(50) not null,authority varchar(50) not null,foreign key (username) references users (username));