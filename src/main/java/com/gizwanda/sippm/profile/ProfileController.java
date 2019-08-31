package com.gizwanda.sippm.profile;

import com.gizwanda.sippm.profile.model.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profiles")
    public List<Profile> fetchProfiles() {
        return profileService.fetchAll();
    }

    @GetMapping("/profiles/{id}")
    public Profile fetchProfileById(@PathVariable(value = "id") int profileId) {
        return profileService.fetchById(profileId);
    }

    @PostMapping("/profiles")
    public ResponseEntity<Profile> createProfile(@Valid @RequestBody Profile profile) {
        return profileService.create(profile);
    }
}
