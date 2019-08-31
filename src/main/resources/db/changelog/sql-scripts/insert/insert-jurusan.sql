--liquibase formatted sql

--changeset giz:003.2
INSERT INTO jurusan (id, nama_jurusan) VALUES
(1, 'Jurusan Matematika dan Teknologi Informasi'),
(2, 'Jurusan Sains, Teknologi Pangan dan Kemaritiman'),
(3, 'Jurusan Teknologi Industri dan Proses'),
(4, 'Jurusan Teknologi Sipil dan Perencanaan'),
(5, 'Jurusan Ilmu Kebumian dan Lingkungan');
