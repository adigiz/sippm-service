package com.gizwanda.sippm.prodi;

import com.gizwanda.sippm.builder.ProdiBuilder;
import com.gizwanda.sippm.common.exception.AlreadyExistException;
import com.gizwanda.sippm.common.exception.ResourceNotFoundException;
import com.gizwanda.sippm.prodi.model.Prodi;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProdiServiceTest {
    @Autowired
    ProdiService prodiService;

    @Autowired
    ProdiRepository prodiRepository;

    @Autowired
    ProdiBuilder prodiBuilder;

    @After
    public void cleanUp() {
        prodiBuilder.cleanUp();
    }

    @Test
    public void fetchAll_expectReturnEmptyListWhenNoProdiFound() {
        List<Prodi> prodiList = prodiService.fetchAll();

        assertThat(prodiList, is(empty()));
    }

    @Test
    public void fetchAll_expectReturnAllProdi() {
        Prodi prodi = prodiBuilder.create();
        List<Prodi> expectedProdis = Collections.singletonList(prodi);

        List<Prodi> actualProdis = prodiService.fetchAll();

        assertEquals(expectedProdis, actualProdis);
    }

    @Test(expected = AlreadyExistException.class)
    public void create_expectThrowsAlreadyExistsExceptionWhenProdiWithTheSameNameExists() {
        String nama = "Sistem Informasi";
        prodiBuilder.withNama(nama).create();
        Prodi newProdi = prodiBuilder.withNama(nama).build();

        prodiService.create(newProdi);
    }

    @Test
    public void create_expectReturnResponseEntityWithCreatedProdiAndStatusCode201WhenCreated() {
        Prodi prodi = prodiBuilder.build();

        ResponseEntity result = prodiService.create(prodi);
        Prodi actualProdi = prodiRepository.findAll().get(0);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(prodi, result.getBody());
        assertEquals(prodi, actualProdi);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void fetchById_expectThrowsResourceNotFoundExceptionWhenProdiWithExpectedIdNotFound() {
        Prodi prodi = prodiBuilder.build();
        int prodiId = prodi.getId();

        prodiService.fetchById(prodiId);
    }

    @Test
    public void fetchById_expectReturnProdiWithRespectedIdWhenFound() {
        Prodi prodi = prodiBuilder.create();
        int prodiId = prodi.getId();

        Prodi searchedProdi = prodiService.fetchById(prodiId);

        assertEquals(prodi, searchedProdi);
    }
}
