package com.gizwanda.sippm.prodi.model;

import com.gizwanda.sippm.jurusan.model.Jurusan;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class ProdiDTO {
    int id;

    @NonNull
    private String nama;

    @NonNull
    private Jurusan jurusan;

    public ProdiDTO(Prodi prodi) {
        this.id = prodi.getId();
        this.nama = prodi.getNama();
        this.jurusan = prodi.getJurusan();
    }
}
