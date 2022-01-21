package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.dtos.RegistrationForm;
import me.projects.SelfOKRs.security.TokenService;
import me.projects.SelfOKRs.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

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

    // Only with ROLE_ADMIN
//    @GetMapping
//    ResponseEntity<?> getAllUsers() {
//        return ResponseEntity.ok(userService.all());
//    }

    // Only with ROLE_ADMIN
//    @GetMapping("/{id}")
//    ResponseEntity<?> getOneUser(@PathVariable Long id) {
//        return ResponseEntity.ok(userService.one(id));
//    }

    @PostMapping("/signup")
    ResponseEntity<?> createUser(@Valid @RequestBody RegistrationForm newUser) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.newUser(newUser));
    }

    @PutMapping
    ResponseEntity<?> updateUser(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.editUser(user));
    }

    // Refactor, not secure
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteOne(id);
    }

    @GetMapping("/token/refresh")
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        tokenService.refreshToken(request, response);
    }
}
