package com.gizwanda.sippm.jurusan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gizwanda.sippm.builder.JurusanBuilder;
import com.gizwanda.sippm.common.exception.AlreadyExistException;
import com.gizwanda.sippm.jurusan.model.Jurusan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class JurusanControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    JurusanBuilder jurusanBuilder;

    @MockBean
    JurusanService jurusanService;

    @Test
    public void fetchAllJurusan_expectReturnAllJurusan() throws Exception {
        Jurusan jurusan = jurusanBuilder.create();
        List<Jurusan> jurusanList = Collections.singletonList(jurusan);

        given(jurusanService.fetchAll()).willReturn(jurusanList);
        this.mockMvc.perform(get("/api/v1/jurusans")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andReturn();

        verify(jurusanService, times(1)).fetchAll();
    }

    @Test
    public void fetchBJurusanById_expectReturnJurusanWhenExists() throws Exception {
        Jurusan jurusan = jurusanBuilder.create();
        int expectedId = jurusan.getId();

        given(jurusanService.fetchById(expectedId)).willReturn(jurusan);
        this.mockMvc.perform(get("/api/v1/jurusans/" + expectedId)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.namaJurusan", is(jurusan.getNamaJurusan())))
                    .andReturn();

        verify(jurusanService, times(1)).fetchById(expectedId);
    }

    @Test
    public void createJurusan_expectReturnStatusCode201WhenCreated() throws Exception {
        Jurusan jurusan = jurusanBuilder.build();
        ObjectMapper objectMapper = new ObjectMapper();

        given(jurusanService.create(jurusan)).willReturn(new ResponseEntity<>(jurusan, HttpStatus.CREATED));
        this.mockMvc.perform(post("/api/v1/jurusans")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(jurusan)))
                    .andExpect(status().isCreated());

        verify(jurusanService, times(1)).create(jurusan);
    }
}
