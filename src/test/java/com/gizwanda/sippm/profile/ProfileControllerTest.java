package com.gizwanda.sippm.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gizwanda.sippm.profile.model.Profile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ProfileService profileService;

    @Test
    public void fetchProfiles_expectReturnAllUsers() throws Exception {
        Profile profile = new Profile(1, "18055845", "Adityo", "IVA", "Fungsional", "SI", "Sempaja", "085246795314", new Date(new Date().getTime()), new Date(new Date().getTime()));
        List<Profile> profileList = Collections.singletonList(profile);

        given(profileService.fetchAll()).willReturn(profileList);
        this.mvc.perform(get("/api/v1/profiles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nama", is(profile.getNama())));

        verify(profileService, times(1)).fetchAll();
    }

    @Test
    public void fetchProfileById_expectReturnUserWhenExists() throws Exception {
        Profile profile = new Profile(1, "18055845", "Adityo", "IVA", "Fungsional", "SI", "Sempaja", "085246795314", new Date(new Date().getTime()), new Date(new Date().getTime()));

        given(profileService.fetchById(profile.getId())).willReturn(profile);

        this.mvc.perform(get("/api/v1/profiles/" + profile.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nip", is(profile.getNip())))
                .andExpect(jsonPath("$.nama", is(profile.getNama())));

        verify(profileService, times(1)).fetchById(profile.getId());
    }

    @Test
    public void createProfile_expectReturnStatusCode201WhenCreated() throws Exception{
        Profile profile = new Profile(1, "18055845", "Adityo", "IVA", "Fungsional", "SI", "Sempaja", "085246795314", new Date(new Date().getTime()), new Date(new Date().getTime()));
        ObjectMapper objectMapper = new ObjectMapper();

        given(profileService.create(profile)).willReturn(new ResponseEntity<>(profile, HttpStatus.CREATED));
        this.mvc.perform(post("/api/v1/profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(profile)))
                .andExpect(status().isCreated());

        verify(profileService, times(1)).create(profile);
    }
}
