--liquibase formatted sql

--changeset giz:005.1
CREATE TABLE IF NOT EXISTS pengumuman (
    id INT NOT NULL AUTO_INCREMENT,
    judul VARCHAR (50) NOT NULL,
    isi VARCHAR (255) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    PRIMARY KEY(id);
)
