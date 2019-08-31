--liquibase formatted sql

--changeset giz:004.2
INSERT INTO prodi (id, nama, jurusan_id) VALUES
(1, 'Matematika', 1),
(2, 'Sistem Informasi', 1),
(3, 'Informatika', 1),
(4, 'Fisika', 2),
(5, 'Teknik Perkapalan', 2),
(6, 'Teknik Kelautan', 2),
(7, 'Teknik Mesin', 3),
(8, 'Teknik Elektro', 3),
(9, 'Teknik Kimia', 3),
(10, 'Teknik Industri', 3),
(11, 'Teknik Sipil', 4),
(12, 'Perencanaan Wilayah dan Kota', 4),
(13, 'Teknik Material dan Metalurgi', 5),
(14, 'Teknik Lingkungan', 5);

