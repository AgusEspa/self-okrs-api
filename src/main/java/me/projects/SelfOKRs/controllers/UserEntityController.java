package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.dtos.UserData;
import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.dtos.RegistrationForm;
import me.projects.SelfOKRs.security.TokenService;
import me.projects.SelfOKRs.services.UserEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserEntityController {

    private final UserEntityService userService;
    private final TokenService tokenService;

    Logger logger = LoggerFactory.getLogger(UserEntityController.class);

    @Autowired
    public UserEntityController(UserEntityService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    // Only with ROLE_ADMIN
//    @GetMapping
//    ResponseEntity<List> getAllUsers() {
//        return ResponseEntity.ok(userService.all());
//    }

    // Only with ROLE_ADMIN
//    @GetMapping("/{id}")
//    ResponseEntity<UserEntity> getOneUser(@PathVariable Long id) {
//        return ResponseEntity.ok(userService.one(id));
//    }

    @GetMapping("/authenticated")
    ResponseEntity<UserData> getUserData() {
        return ResponseEntity.ok(userService.fetchUserData());
    }

    @PostMapping("/signup")
    ResponseEntity<UserEntity> createUser(@Valid @RequestBody RegistrationForm newUser) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.newUser(newUser));
    }

    @PutMapping
    ResponseEntity<UserEntity> updateUser(@Valid @RequestBody RegistrationForm editedUser) {
        return ResponseEntity.ok(userService.editUser(editedUser));
    }

    @DeleteMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    void deleteUser(@RequestParam Map<String,String> credentials) {
        userService.deleteOne(credentials);
    }

    @GetMapping("/token/refresh")
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        tokenService.refreshToken(request, response);
    }
}
