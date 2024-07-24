--create table medicos(
                       -- id bigint not null primary key auto_increment,
                       -- nombre varchar(100) not null ,
                       -- email varchar(100) not null unique ,
                       -- documento varchar(8) not null unique,
                       -- especialidad varchar(100) not null ,
                       -- calle varchar(100) not null ,
                       -- distrito varchar(100) not null ,
                       -- complemento varchar(100) not null ,
                       -- numero varchar(20) not null ,
                       -- ciudad varchar(100) not null
--);
create table medicos(
                        id BIGSERIAL not null primary key,
                        nombre varchar(100) not null ,
                        email varchar(100) not null unique ,
                        documento varchar(8) not null unique,
                        especialidad varchar(100) not null ,
                        calle varchar(100) not null ,
                        distrito varchar(100) not null ,
                        complemento varchar(100) not null ,
                        numero varchar(20) not null ,
                        ciudad varchar(100) not null
);