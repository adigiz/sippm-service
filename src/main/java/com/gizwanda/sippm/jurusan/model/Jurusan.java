package com.gizwanda.sippm.jurusan.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Jurusan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NonNull
    private String nama;
}
