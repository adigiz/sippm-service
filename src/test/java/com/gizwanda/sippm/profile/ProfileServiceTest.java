package com.gizwanda.sippm.profile;

import com.gizwanda.sippm.builder.UserBuilder;
import com.gizwanda.sippm.common.exception.AlreadyExistException;
import com.gizwanda.sippm.common.exception.ResourceNotFoundException;
import com.gizwanda.sippm.profile.model.Profile;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProfileServiceTest {

    @Autowired
    ProfileService profileService;

    @Autowired
    UserBuilder userBuilder;

    @Autowired
    ProfileRepository profileRepository;

    @After
    public void cleanUp() {
        userBuilder.cleanUp();
    }

    @Test
    public void create_expectUserCreated(){
        Profile createdProfile = userBuilder.build();

        profileService.create(createdProfile);

        Profile actualProfile = profileRepository.findAll().get(0);
        assertEquals(createdProfile, actualProfile);
    }

    @Test(expected = AlreadyExistException.class)
    public void create_expectThrowsAlreadyExistsException_whenUserAlreadyExists() {
        Profile createdProfile = userBuilder.create();
        profileService.create(createdProfile);
    }

    @Test
    public void fetchAll_expectEmpty() {
        List<Profile> profileList = profileService.fetchAll();

        assertThat(profileList, is(empty()));
    }

    @Test
    public void fetchAll_expectReturnAllUser() {
        Profile createdProfile = userBuilder.create();
        List<Profile> expectedProfiles = Collections.singletonList(createdProfile);

        List<Profile> actualProfiles = profileService.fetchAll();

        assertEquals(expectedProfiles, actualProfiles);
    }

    @Test
    public void fetchById_expectReturnUser_whenIdFound() {
        Profile createdProfile = userBuilder.create();
        int userId = createdProfile.getId();

        Profile actualProfile = profileService.fetchById(userId);

        assertEquals(createdProfile, actualProfile);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void fetchById_expectThrowsResourceNotFoundException_whenUserNotFound() {
        Profile createdProfile = userBuilder.build();
        int userId = createdProfile.getId();

        profileService.fetchById(userId);
    }
}
