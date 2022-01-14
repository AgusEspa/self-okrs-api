package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.security.RegistrationForm;
import me.projects.SelfOKRs.security.TokenService;
import me.projects.SelfOKRs.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserEntityController {

    private final UserEntityService userService;
    private final TokenService tokenService;

    @Autowired
    public UserEntityController(UserEntityService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping
    List<UserEntity> getAllUsers() {
        return userService.all();
    }

    @GetMapping("/{id}")
    UserEntity getOneUser(@PathVariable Long id) {
        return userService.one(id);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    UserEntity createUser(@RequestBody RegistrationForm newUser) {
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

    @GetMapping("/token/refresh")
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        tokenService.refreshToken(request, response);
    }
}
