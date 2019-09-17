package com.gizwanda.sippm.builder;

import com.github.javafaker.Faker;
import com.gizwanda.sippm.pengumuman.PengumumanRepository;
import com.gizwanda.sippm.pengumuman.model.Pengumuman;
import com.gizwanda.sippm.pengumuman.model.PengumumanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PengumumanBuilder {
    @Autowired
    PengumumanRepository pengumumanRepository;

    Faker faker = new Faker();
    String judul = faker.name().title();
    String isi = faker.lorem().paragraph();

    public Pengumuman build(){
        return new Pengumuman(judul, isi);
    }

    public PengumumanDTO buildDTO(Pengumuman pengumuman) {
        return new PengumumanDTO(pengumuman);
    }

    public Pengumuman create() {
        return pengumumanRepository.save(build());
    }

    public void cleanUp() {
        pengumumanRepository.deleteAll();
    }
}
