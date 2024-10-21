create table foto (
id_foto serial primary key,
dados VARBINARY(255),
tipo varchar(100),
nome varchar(100),
id_usuario bigint,
foreign key (id_usuario) references usuario(id_usuario)
);