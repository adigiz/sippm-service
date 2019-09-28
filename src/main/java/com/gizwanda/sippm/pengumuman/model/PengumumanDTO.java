package com.gizwanda.sippm.pengumuman.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "createdAt", "updatedAt"})
public class PengumumanDTO {
    private int id;
    private String judul;
    private String isi;
    private Date createdAt;
    private Date updatedAt;

    public PengumumanDTO(Pengumuman pengumuman){
        this.id = pengumuman.getId();
        this.judul = pengumuman.getJudul();
        this.isi = pengumuman.getIsi();
        this.createdAt = pengumuman.getCreatedAt();
        this.updatedAt = pengumuman.getUpdatedAt();
    }
}
