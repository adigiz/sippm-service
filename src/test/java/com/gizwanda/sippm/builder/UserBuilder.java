package com.gizwanda.sippm.builder;

import com.github.javafaker.Faker;
import com.gizwanda.sippm.user.UserRepository;
import com.gizwanda.sippm.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class UserBuilder {

    @Autowired
    UserRepository userRepository;

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

    public User build(){
        return new User(id,nip, nama, pangkat, jabatan, lab, alamat, noTelp, createdAt, updatedAt);
    }

    public User create(){
        return userRepository.save(build());
    }

    public void cleanUp(){
        userRepository.deleteAll();
    }
}
