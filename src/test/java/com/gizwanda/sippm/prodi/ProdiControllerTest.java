package com.gizwanda.sippm.prodi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gizwanda.sippm.builder.JurusanBuilder;
import com.gizwanda.sippm.builder.ProdiBuilder;
import com.gizwanda.sippm.jurusan.model.Jurusan;
import com.gizwanda.sippm.prodi.model.Prodi;
import com.gizwanda.sippm.prodi.model.ProdiDTO;
import org.junit.After;
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
public class ProdiControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProdiBuilder prodiBuilder;

    @MockBean
    ProdiService prodiService;

    @Autowired
    JurusanBuilder jurusanBuilder;

    @After
    public void cleanUp() {
        prodiBuilder.cleanUp();
        jurusanBuilder.cleanUp();
    }

    @Test
    public void createProdi_expectReturnStatusCode201WhenCreated() throws Exception {
        Jurusan jurusan = jurusanBuilder.create();
        ProdiDTO prodiDTO = prodiBuilder.buildDTO(prodiBuilder.build());
        prodiDTO.setJurusan(jurusan);
        ObjectMapper objectMapper = new ObjectMapper();

        given(prodiService.create(prodiDTO)).willReturn(new ResponseEntity<>(prodiDTO, HttpStatus.CREATED));
        this.mockMvc.perform(post("/api/v1/prodis")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(prodiDTO)))
                    .andExpect(status().isCreated());

        verify(prodiService, times(1)).create(prodiDTO);
    }

    @Test
    public void fetchAll_expectReturnAllProdi() throws Exception {
        Prodi prodi = prodiBuilder.create();
        List<Prodi> prodiList = Collections.singletonList(prodi);

        given(prodiService.fetchAll()).willReturn(prodiList);
        this.mockMvc.perform(get("/api/v1/prodis")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andReturn();

        verify(prodiService, times(1)).fetchAll();
    }

    @Test
    public void fetchProdiById_expectReturnProdiWhenExists() throws Exception {
        Prodi prodi = prodiBuilder.create();
        int prodiId = prodi.getId();

        given(prodiService.fetchById(prodiId)).willReturn(prodi);
        this.mockMvc.perform(get("/api/v1/prodis/" + prodiId)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nama", is(prodi.getNama())))
                    .andReturn();

        verify(prodiService, times(1)).fetchById(prodiId);
    }
}
