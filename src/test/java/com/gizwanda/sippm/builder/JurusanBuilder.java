package com.gizwanda.sippm.builder;

import com.github.javafaker.Faker;
import com.gizwanda.sippm.jurusan.JurusanRepository;
import com.gizwanda.sippm.jurusan.model.Jurusan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JurusanBuilder {
    @Autowired
    JurusanRepository jurusanRepository;

    Faker faker = new Faker();
    String nama = faker.name().fullName();

    public Jurusan build() {
        return new Jurusan(nama);
    }

    public Jurusan create() {
        return jurusanRepository.save(build());
    }

    public JurusanBuilder withNama(String nama) {
        this.nama = nama;
        return this;
    }

    public void cleanUp() {
        jurusanRepository.deleteAll();
    }
}
