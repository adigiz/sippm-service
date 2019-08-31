package com.gizwanda.sippm.profile;

import com.gizwanda.sippm.builder.ProfileBuilder;
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
    ProfileBuilder profileBuilder;

    @Autowired
    ProfileRepository profileRepository;

    @After
    public void cleanUp() {
        profileBuilder.cleanUp();
    }

    @Test
    public void create_expectUserCreated(){
        Profile createdProfile = profileBuilder.build();

        profileService.create(createdProfile);

        Profile actualProfile = profileRepository.findAll().get(0);
        assertEquals(createdProfile, actualProfile);
    }

    @Test(expected = AlreadyExistException.class)
    public void create_expectThrowsAlreadyExistsException_whenUserAlreadyExists() {
        Profile createdProfile = profileBuilder.create();
        profileService.create(createdProfile);
    }

    @Test
    public void fetchAll_expectEmpty() {
        List<Profile> profileList = profileService.fetchAll();

        assertThat(profileList, is(empty()));
    }

    @Test
    public void fetchAll_expectReturnAllUser() {
        Profile createdProfile = profileBuilder.create();
        List<Profile> expectedProfiles = Collections.singletonList(createdProfile);
        List<Profile> actualProfiles = profileService.fetchAll();

        assertEquals(expectedProfiles, actualProfiles);
    }

    @Test
    public void fetchById_expectReturnUser_whenIdFound() {
        Profile createdProfile = profileBuilder.create();
        int userId = createdProfile.getId();

        Profile actualProfile = profileService.fetchById(userId);

        assertEquals(createdProfile, actualProfile);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void fetchById_expectThrowsResourceNotFoundException_whenUserNotFound() {
        Profile createdProfile = profileBuilder.build();
        int userId = createdProfile.getId();

        profileService.fetchById(userId);
    }
}
