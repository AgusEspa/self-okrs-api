package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.UserAlreadyExistsException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.exceptions.UserNotAuthorizedException;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import me.projects.SelfOKRs.dtos.RegistrationForm;
import me.projects.SelfOKRs.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserEntityService {

    private final UserEntityRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public UserEntityService(UserEntityRepository userRepository, AuthenticationFacade authenticationFacade) {
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
    }

//    public List<UserEntity> all() {
//        return userRepository.findAll();
//    }

    // Refactor, not secure
//    public UserEntity one(Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new UserEntityNotFoundException(id));
//    }

    public UserEntity newUser(RegistrationForm newUser) {

        if (userRepository.findByEmailAddress(newUser.getEmailAddress()).isEmpty()) {
            return userRepository.save(newUser.toUser());
        } else {
            throw new UserAlreadyExistsException(newUser.getEmailAddress());
        }
    }

    // Refactor, not secure and not updating securityContext
    public UserEntity editUser(RegistrationForm editedUser) {
        String username = authenticationFacade.getAuthentication().getName();
        UserEntity fetchedUser = userRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        if (userRepository.findByEmailAddress(editedUser.getEmailAddress()).isEmpty()) {
            fetchedUser.setUsername(editedUser.getUsername());
            fetchedUser.setEmailAddress(editedUser.getEmailAddress());
            fetchedUser.setPassword(passwordEncoder.encode(editedUser.getPassword()));
            return  userRepository.save(fetchedUser);
        } else {
            throw new UserAlreadyExistsException(editedUser.getEmailAddress());
        }
    }

    public void deleteOne(Map<String, String> credentials) {
        String username = authenticationFacade.getAuthentication().getName();

        UserEntity fetchedUser = userRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        if (passwordEncoder.matches(credentials.get("password"), fetchedUser.getPassword()) && username.equals(credentials.get("emailAddress"))) {
            userRepository.deleteById(fetchedUser.getId());
        } else throw new UserNotAuthorizedException(username);
    }
}
