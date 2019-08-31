package com.gizwanda.sippm.builder;

import com.github.javafaker.Faker;
import com.gizwanda.sippm.profile.ProfileRepository;
import com.gizwanda.sippm.profile.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class ProfileBuilder {

    @Autowired
    ProfileRepository profileRepository;

    private Faker faker = new Faker();
    private int id = faker.number().randomDigit();
    private String nip = faker.code().toString();
    private String nama = faker.name().toString();
    private String pangkat = faker.code().toString();
    private String jabatan = faker.code().toString();
    private String lab = faker.university().toString();
    private String alamat = faker.address().toString();
    private String noTelp = faker.phoneNumber().toString();
    private Date createdAt = new Date(new java.util.Date().getTime());
    private Date updatedAt = new Date(new java.util.Date().getTime());

    public Profile build(){
        return new Profile(id,nip, nama, pangkat, jabatan, lab, alamat, noTelp, createdAt, updatedAt);
    }

    public Profile create(){
        return profileRepository.save(build());
    }

    public void cleanUp(){
        profileRepository.deleteAll();
    }
}
