package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository repository) {
        this.userRepository = repository;
    }

    @GetMapping
    List<User> all() {
        return userRepository.findAll();
    }
}
