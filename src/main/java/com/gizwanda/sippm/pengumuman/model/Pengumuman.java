package com.gizwanda.sippm.pengumuman.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Pengumuman {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    @NonNull
    private String judul;

    @Column
    @NonNull
    private String isi;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updatedAt;

    public Pengumuman(PengumumanDTO pengumumanDTO) {
        this.judul = pengumumanDTO.getJudul();
        this.isi = pengumumanDTO.getIsi();
    }
}
