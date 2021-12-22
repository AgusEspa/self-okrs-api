package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.all();
    }

    @GetMapping("/{id}")
    User getOneUser(@PathVariable Long id) {
        return userService.one(id);
    }

    @PostMapping
    User createUser(@RequestBody User newUser) {
        return userService.newUser(newUser);
    }

    @PutMapping("/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.editUser(id, user);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteOne(id);
    }
}
