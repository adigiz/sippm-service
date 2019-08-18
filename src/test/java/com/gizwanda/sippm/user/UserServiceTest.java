package com.gizwanda.sippm.user;

import com.gizwanda.sippm.builder.UserBuilder;
import com.gizwanda.sippm.common.exception.AlreadyExistException;
import com.gizwanda.sippm.common.exception.ResourceNotFoundException;
import com.gizwanda.sippm.user.model.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.invoke.empty.Empty;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserBuilder userBuilder;

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanUp() {
        userBuilder.cleanUp();
    }

    @Test
    public void create_expectUserCreated(){
        User createdUser = userBuilder.build();

        userService.create(createdUser);

        User actualUser = userRepository.findAll().get(0);
        assertEquals(createdUser, actualUser);
    }

    @Test(expected = AlreadyExistException.class)
    public void create_expectThrowsAlreadyExistsException_whenUserAlreadyExists() {
        User createdUser = userBuilder.create();
        userService.create(createdUser);
    }

    @Test
    public void fetchAll_expectEmpty() {
        List<User> userList = userService.fetchAll();

        assertThat(userList, is(empty()));
    }

    @Test
    public void fetchAll_expectReturnAllUser() {
        User createdUser = userBuilder.create();
        List<User> expectedUsers = Collections.singletonList(createdUser);

        List<User> actualUsers = userService.fetchAll();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void fetchById_expectReturnUser_whenIdFound() {
        User createdUser = userBuilder.create();
        int userId = createdUser.getId();

        User actualUser = userService.fetchById(userId);

        assertEquals(createdUser, actualUser);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void fetchById_expectThrowsResourceNotFoundException_whenUserNotFound() {
        User createdUser = userBuilder.build();
        int userId = createdUser.getId();

        userService.fetchById(userId);
    }
}
