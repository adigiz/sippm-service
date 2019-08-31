--liquibase formatted sql

--changeset giz:004.1
CREATE TABLE IF NOT EXISTS prodi (
    id INT NOT NULL AUTO_INCREMENT,
    nama VARCHAR(100) NOT NULL,
    jurusan_id INT NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT prodi_unique_nama UNIQUE (nama),
    CONSTRAINT fk_prodi_jurusan FOREIGN KEY(jurusan_id) REFERENCES jurusan(id)
);
