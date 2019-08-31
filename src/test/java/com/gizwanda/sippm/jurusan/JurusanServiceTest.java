package com.gizwanda.sippm.jurusan;

import com.gizwanda.sippm.builder.JurusanBuilder;
import com.gizwanda.sippm.common.exception.AlreadyExistException;
import com.gizwanda.sippm.common.exception.ResourceNotFoundException;
import com.gizwanda.sippm.jurusan.model.Jurusan;
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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JurusanServiceTest {
    @Autowired
    JurusanService jurusanService;

    @Autowired
    JurusanRepository jurusanRepository;

    @Autowired
    JurusanBuilder jurusanBuilder;

    @After
    public void cleanUp() {
        jurusanBuilder.cleanUp();
    }

    @Test
    public void fetchAll_expectReturnEmptyListWhenNoJurusanFound() {
        List<Jurusan> jurusanList = jurusanService.fetchAll();

        assertThat(jurusanList, is(empty()));
    }

    @Test
    public void fetchAll_expectReturnAllJurusan() {
        Jurusan jurusan = jurusanBuilder.create();
        List<Jurusan> expectedJurusanList = Collections.singletonList(jurusan);
        List<Jurusan> actualJurusanList = jurusanService.fetchAll();

        assertEquals(expectedJurusanList, actualJurusanList);
    }

    @Test(expected = AlreadyExistException.class)
    public void create_expectThrowsAlreadyExistsExceptionWhenNamaJurusanExists() {
        jurusanBuilder.withNama("Jurusan Matematika dan Teknologi Informasi").create();
        Jurusan newJurusan = jurusanBuilder.withNama("Jurusan Matematika dan Teknologi Informasi").build();

        jurusanService.create(newJurusan);
    }

    @Test
    public void create_expectReturnResponseEntityWithCreatedJurusanAndStatusCode201WhenCreated() {
        Jurusan jurusan = jurusanBuilder.build();
        ResponseEntity result = jurusanService.create(jurusan);
        Jurusan actualJurusan = jurusanRepository.findAll().get(0);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(jurusan, result.getBody());
        assertEquals(jurusan, actualJurusan);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void fetchById_expectReturnResourceNotFoundException() {
        Jurusan jurusan = jurusanBuilder.build();
        int jurusanId = jurusan.getId();

        jurusanService.fetchById(jurusanId);
    }

    @Test
    public void fetchById_expectReturnJurusanWithRespectiveIdWhenFound() {
        Jurusan jurusan = jurusanBuilder.create();
        int jurusanId = jurusan.getId();

        Jurusan searchedJurusan = jurusanService.fetchById(jurusanId);

        assertEquals(jurusan, searchedJurusan);
    }
}
