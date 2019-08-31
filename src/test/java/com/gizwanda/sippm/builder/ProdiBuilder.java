package com.gizwanda.sippm.builder;

import com.github.javafaker.Faker;
import com.gizwanda.sippm.prodi.ProdiRepository;
import com.gizwanda.sippm.prodi.model.Prodi;
import com.gizwanda.sippm.prodi.model.ProdiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdiBuilder {
    @Autowired
    ProdiRepository prodiRepository;

    Faker faker = new Faker();
    String nama = faker.name().toString();

    public Prodi build() {
        return new Prodi(nama);
    }

    public ProdiDTO buildDTO(Prodi prodi) {
        return new ProdiDTO(prodi);
    }
    public Prodi create() {
        return prodiRepository.save(build());
    }

    public ProdiBuilder withNama(String nama) {
        this.nama = nama;
        return this;
    }

    public void cleanUp() {
        prodiRepository.deleteAll();
    }
}
