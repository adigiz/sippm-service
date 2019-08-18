package com.gizwanda.sippm.user;

import com.gizwanda.sippm.user.model.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> fetchUsers() {
        return userService.fetchAll();
    }

    @GetMapping("/users/{id}")
    public User fetchUserById(@PathVariable(value = "id") int userId) {
        return userService.fetchById(userId);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userService.create(user);
    }
}
