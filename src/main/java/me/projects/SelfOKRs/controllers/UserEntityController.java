package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.dtos.requests.EditUserForm;
import me.projects.SelfOKRs.dtos.responses.UserCredentialsResponse;
import me.projects.SelfOKRs.dtos.responses.UserResponse;
import me.projects.SelfOKRs.dtos.requests.RegistrationForm;
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

    private final UserEntityService userEntityService;
    private final TokenService tokenService;

    Logger logger = LoggerFactory.getLogger(UserEntityController.class);

    @Autowired
    public UserEntityController(UserEntityService userEntityService, TokenService tokenService) {
        this.userEntityService = userEntityService;
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
    ResponseEntity<UserCredentialsResponse> getUserData() {
        return ResponseEntity.ok(userEntityService.fetchUserData());
    }

    @PostMapping(path = "/signup")
    ResponseEntity<UserResponse> createUser(@Valid @RequestBody RegistrationForm newUser) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userEntityService.newUser(newUser));
    }

    @PutMapping
    ResponseEntity<UserResponse> editUser(@Valid @RequestBody EditUserForm editedUser) {
        return ResponseEntity.ok(userEntityService.updateUser(editedUser));
    }

    @DeleteMapping
    void deleteUser(@Valid @RequestBody EditUserForm editedUser) {
        userEntityService.removeUser(editedUser);
    }

    @GetMapping("/token/refresh")
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        tokenService.refreshToken(request, response);
    }
}
