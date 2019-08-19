package com.gizwanda.sippm.profile;

import com.gizwanda.sippm.profile.model.Profile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.mockito.BDDMockito.given;
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
        List<Profile> profileList = Arrays.asList(profile);

        given(profileService.fetchAll()).willReturn(profileList);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/profiles")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].nama", is(profile.getNama())));
    }

    @Test
    public void fetchProfileById_expectReturnUserWhenExists() throws Exception {
        Profile profile = new Profile(1, "18055845", "Adityo", "IVA", "Fungsional", "SI", "Sempaja", "085246795314", new Date(new Date().getTime()), new Date(new Date().getTime()));

        given(profileService.fetchById(profile.getId())).willReturn(profile);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/profiles/" + profile.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nip", is(profile.getNip())))
                .andExpect(jsonPath("$.nama", is(profile.getNama())));
    }
}
