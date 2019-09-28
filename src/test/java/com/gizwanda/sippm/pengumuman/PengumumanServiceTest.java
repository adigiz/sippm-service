package com.gizwanda.sippm.pengumuman;

import com.gizwanda.sippm.builder.PengumumanBuilder;
import com.gizwanda.sippm.common.exception.ResourceNotFoundException;
import com.gizwanda.sippm.pengumuman.model.Pengumuman;
import com.gizwanda.sippm.pengumuman.model.PengumumanDTO;
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

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PengumumanServiceTest {
    @Autowired
    PengumumanService pengumumanService;

    @Autowired
    PengumumanBuilder pengumumanBuilder;

    @After
    public void cleanUp() {
        pengumumanBuilder.cleanUp();
    }

    @Test
    public void fetchAll_expectReturnEmptyListWhenNoPengumumanFound() {
        List<Pengumuman> emptyList = Collections.emptyList();
        List<Pengumuman> actualList = pengumumanService.fetchAll();

        assertEquals(emptyList, actualList);
    }

    @Test
    public void fetchAll_expectReturnPengumumanListWhenFound() {
        Pengumuman pengumuman = pengumumanBuilder.create();
        List<Pengumuman> pengumumanList = Collections.singletonList(pengumuman);
        List<Pengumuman> actualPengumumanList = pengumumanService.fetchAll();

        assertEquals(pengumumanList, actualPengumumanList);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void fetchById_expectThrowsResourceNotFoundException() {
        pengumumanService.fetchById(9999);
    }

    @Test
    public void fetchById_expectReturnPengumumanWhenFound() {
        Pengumuman pengumuman = pengumumanBuilder.create();
        int pengumumanId = pengumuman.getId();
        Pengumuman actualPengumuman = pengumumanService.fetchById(pengumumanId);

        assertEquals(pengumuman, actualPengumuman);
    }

    @Test
    public void create_expectReturnStatusCreatedWhenCreateSuccessful() {
        PengumumanDTO pengumumanDTO = pengumumanBuilder.buildDTO(pengumumanBuilder.build());
        ResponseEntity responseEntity = pengumumanService.create(pengumumanDTO);
        PengumumanDTO actualPengumuman = new PengumumanDTO(pengumumanService.fetchAll().get(0));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(actualPengumuman, responseEntity.getBody());
    }
}
