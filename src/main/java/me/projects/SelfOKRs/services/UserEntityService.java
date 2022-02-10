package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.dtos.UpdateForm;
import me.projects.SelfOKRs.dtos.UserData;
import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.UserAlreadyExistsException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.exceptions.UserNotAuthorizedException;
import me.projects.SelfOKRs.exceptions.WrongPasswordException;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import me.projects.SelfOKRs.dtos.RegistrationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    Logger logger = LoggerFactory.getLogger(UserEntityService.class);

    @Autowired
    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

//    public List<UserEntity> all() {
//        return userRepository.findAll();
//    }

    // Refactor, not secure
//    public UserEntity one(Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new UserEntityNotFoundException(id));
//    }

    public UserData fetchUserData() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity fetchedUser = userEntityRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));
        return new UserData(fetchedUser.getUsername(), fetchedUser.getEmailAddress());
    }

    public UserEntity newUser(RegistrationForm newUser) {
        if (userEntityRepository.findByEmailAddress(newUser.getEmailAddress()).isEmpty()) {
            return userEntityRepository.save(newUser.toUser());
        } else {
            throw new UserAlreadyExistsException(newUser.getEmailAddress());
        }
    }

    public UserEntity updateUser(UpdateForm editedUser) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity fetchedUser = userEntityRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        if (!(passwordEncoder.matches(editedUser.getOldPassword(), fetchedUser.getPassword()))) {
            throw new WrongPasswordException();
        }

        if (userEntityRepository.findByEmailAddress(editedUser.getEmailAddress()).isEmpty() || editedUser.getEmailAddress().equals(username)) {
            fetchedUser.setUsername(editedUser.getUsername());
            fetchedUser.setEmailAddress(editedUser.getEmailAddress());
            fetchedUser.setPassword(passwordEncoder.encode(editedUser.getPassword()));
            return  userEntityRepository.save(fetchedUser);
        } else {
            throw new UserAlreadyExistsException(editedUser.getEmailAddress());
        }
    }

    public void removeUser(Map<String, String> credentials) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity fetchedUser = userEntityRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        if (passwordEncoder.matches(credentials.get("password"), fetchedUser.getPassword()) && username.equals(credentials.get("emailAddress"))) {
            userEntityRepository.deleteById(fetchedUser.getId());
        } else throw new UserNotAuthorizedException(username);
    }
}
