package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.repositories.UserRepository;
import me.projects.SelfOKRs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    UserService userService;

    public UserController(UserRepository repository) {
        this.userRepository = repository;
    }

    @GetMapping
    List<User> getUsers() {
        return userService.all();
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id) {
        return userService.one(id);
    }

    @PostMapping
    User createUser(@RequestBody User newUser) {
        return userService.newUser(newUser);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteOne(id);
    }
}
