package com.gizwanda.sippm.user;

import com.gizwanda.sippm.builder.UserBuilder;
import com.gizwanda.sippm.user.model.User;
import org.junit.After;
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
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    public void fetchUsers_expectReturnAllUsers() throws Exception {
        User user = new User(1, "18055845", "Adityo", "IVA", "Fungsional", "SI", "Sempaja", "085246795314", new Date(new Date().getTime()), new Date(new Date().getTime()));
        List<User> userList = Arrays.asList(user);

        given(userService.fetchAll()).willReturn(userList);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].nama", is(user.getNama())));
    }

    @Test
    public void fetchUserById_expectReturnUserWhenExists() throws Exception {
        User user = new User(1, "18055845", "Adityo", "IVA", "Fungsional", "SI", "Sempaja", "085246795314", new Date(new Date().getTime()), new Date(new Date().getTime()));

        given(userService.fetchById(user.getId())).willReturn(user);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/users/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nip", is(user.getNip())))
                .andExpect(jsonPath("$.nama", is(user.getNama())));
    }
}
