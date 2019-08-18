package com.gizwanda.sippm.user;

import com.gizwanda.sippm.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByNip(String nip);
}
