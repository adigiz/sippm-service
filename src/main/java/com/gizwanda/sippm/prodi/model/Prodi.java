package com.gizwanda.sippm.prodi.model;

import com.gizwanda.sippm.jurusan.model.Jurusan;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity()
@RequiredArgsConstructor
@NoArgsConstructor
public class Prodi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NonNull
    private String nama;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jurusan_id")
    private Jurusan jurusan;
}
