--liquibase formatted sql

--changeset giz:002.1
CREATE TABLE IF NOT EXISTS profile (
    id INT NOT NULL AUTO_INCREMENT,
    nip VARCHAR(10),
    nama VARCHAR(35),
    pangkat VARCHAR(9),
    jabatan VARCHAR(50),
    lab VARCHAR(50),
    alamat VARCHAR(191),
    no_telp VARCHAR(14),
    created_at DATETIME,
    updated_at DATETIME,
    PRIMARY KEY(id),
    CONSTRAINT user_unique_nip UNIQUE (nip)
)
