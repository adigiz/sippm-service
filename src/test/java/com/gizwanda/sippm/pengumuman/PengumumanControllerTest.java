package com.gizwanda.sippm.pengumuman;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gizwanda.sippm.builder.PengumumanBuilder;
import com.gizwanda.sippm.pengumuman.model.Pengumuman;
import com.gizwanda.sippm.pengumuman.model.PengumumanDTO;
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
public class PengumumanControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PengumumanBuilder pengumumanBuilder;

    @MockBean
    PengumumanService pengumumanService;

    @After
    public void cleanUp() {
        pengumumanBuilder.cleanUp();
    }

    @Test
    public void createPengumuman_expectReturnStatusCode201WhenCreated() throws Exception {
        PengumumanDTO pengumumanDTO = pengumumanBuilder.buildDTO(pengumumanBuilder.build());
        ObjectMapper objectMapper = new ObjectMapper();

        given(pengumumanService.create(pengumumanDTO)).willReturn(new ResponseEntity<>(pengumumanDTO, HttpStatus.CREATED));
        this.mockMvc.perform(post("/api/v1/pengumumans")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(pengumumanDTO)))
                    .andExpect(status().isCreated());

        verify(pengumumanService, times(1)).create(pengumumanDTO);
    }

    @Test
    public void fetchAll_expectReturnAllPengumumans() throws Exception {
        Pengumuman pengumuman = pengumumanBuilder.create();

        given(pengumumanService.fetchAll()).willReturn(Collections.singletonList(pengumuman));
        this.mockMvc.perform(get("/api/v1/pengumumans")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andReturn();

        verify(pengumumanService, times(1)).fetchAll();
    }

    @Test
    public void fetchPengumumanById_expectReturnPengumumanWhenExists() throws Exception {
        Pengumuman pengumuman = pengumumanBuilder.create();
        int pengumumanId = pengumuman.getId();

        given(pengumumanService.fetchById(pengumumanId)).willReturn(pengumuman);
        this.mockMvc.perform(get("/api/v1/pengumumans/" + pengumumanId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.judul", is(pengumuman.getJudul())))
                .andReturn();

        verify(pengumumanService, times(1)).fetchById(pengumumanId);
    }
}
