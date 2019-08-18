package com.gizwanda.sippm.user;

import com.gizwanda.sippm.common.exception.AlreadyExistException;
import com.gizwanda.sippm.common.exception.ResourceNotFoundException;
import com.gizwanda.sippm.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User create(User user) {
        if (userRepository.existsByNip(user.getNip())) {
            throw new AlreadyExistException("User with the current NIP already exists");
        }
        return userRepository.save(user);
    }

    List<User> fetchAll() {
        return userRepository.findAll();
    }

    User fetchById(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+ userId));
    }
}
