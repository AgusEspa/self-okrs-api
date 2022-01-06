package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserEntityController {

    private final UserEntityService userService;

    @Autowired
    public UserEntityController(UserEntityService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<UserEntity> getAllUsers() {
        return userService.all();
    }

    @GetMapping("/{id}")
    UserEntity getOneUser(@PathVariable Long id) {
        return userService.one(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserEntity createUser(@RequestBody UserEntity newUser) {
        return userService.newUser(newUser);
    }

    @PutMapping("/{id}")
    UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        return userService.editUser(id, user);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteOne(id);
    }
}
