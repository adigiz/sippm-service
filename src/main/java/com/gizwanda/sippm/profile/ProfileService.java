package com.gizwanda.sippm.profile;

import com.gizwanda.sippm.common.exception.AlreadyExistException;
import com.gizwanda.sippm.common.exception.ResourceNotFoundException;
import com.gizwanda.sippm.profile.model.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    ResponseEntity<Profile> create(Profile profile) {
        if (profileRepository.existsByNip(profile.getNip())) {
            throw new AlreadyExistException("Profile with the current NIP already exists");
        }
        Profile savedProfile = profileRepository.save(profile);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    List<Profile> fetchAll() {
        return profileRepository.findAll();
    }

    Profile fetchById(int userId) {
        return profileRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: "+ userId));
    }
}
