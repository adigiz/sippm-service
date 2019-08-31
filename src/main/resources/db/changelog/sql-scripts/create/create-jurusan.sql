--liquibase formatted sql

--changeset giz:003.1
CREATE TABLE IF NOT EXISTS jurusan (
 id INT NOT NULL AUTO_INCREMENT,
 nama VARCHAR(100) NOT NULL,

 PRIMARY KEY(id),
 CONSTRAINT user_unique_nama UNIQUE (nama)
);
