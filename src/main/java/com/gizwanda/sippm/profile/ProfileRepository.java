package com.gizwanda.sippm.profile;

import com.gizwanda.sippm.profile.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    boolean existsByNip(String nip);
}
