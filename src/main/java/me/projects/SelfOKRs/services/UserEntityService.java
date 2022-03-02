package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.dtos.requests.EditUserForm;
import me.projects.SelfOKRs.dtos.responses.UserCredentialsResponse;
import me.projects.SelfOKRs.dtos.responses.UserResponse;
import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.UserAlreadyExistsException;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.exceptions.UserNotAuthorizedException;
import me.projects.SelfOKRs.exceptions.WrongPasswordException;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import me.projects.SelfOKRs.dtos.requests.RegistrationForm;
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

    public UserCredentialsResponse fetchUserData() {

        String username = getUsername();

        UserEntity fetchedUser = userEntityRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));
        return new UserCredentialsResponse(fetchedUser.getUsername(), fetchedUser.getEmailAddress());
    }

    public UserResponse newUser(RegistrationForm newUser) {
        if (userEntityRepository.findByEmailAddress(newUser.getEmailAddress()).isEmpty()) {
            UserEntity userEntity = newUser.toUser();
            userEntityRepository.save(userEntity);
            UserResponse userResponse = new UserResponse(userEntity.getId(), userEntity.getUsername(), userEntity.getEmailAddress());
            return userResponse;
        } else {
            throw new UserAlreadyExistsException(newUser.getEmailAddress());
        }
    }

    public UserResponse updateUser(EditUserForm editedUser) {

        String username = getUsername();

        UserEntity fetchedUser = userEntityRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        if (!(passwordEncoder.matches(editedUser.getOldPassword(), fetchedUser.getPassword()))) {
            throw new WrongPasswordException();
        }

        if (editedUser.getEmailAddress().equals(fetchedUser.getEmailAddress()) || userEntityRepository.findByEmailAddress(editedUser.getEmailAddress()).isEmpty()) {

            fetchedUser.setUsername(editedUser.getUsername());

            fetchedUser.setEmailAddress(editedUser.getEmailAddress());

            fetchedUser.setPassword(passwordEncoder.encode(editedUser.getNewPassword()));

            userEntityRepository.save(fetchedUser);
            UserResponse userResponse = new UserResponse(fetchedUser.getId(), fetchedUser.getUsername(), fetchedUser.getEmailAddress());
            return userResponse;
        } else {
            throw new UserAlreadyExistsException(editedUser.getEmailAddress());
        }
    }

    public void removeUser(EditUserForm editedUser) {

        String username = getUsername();

        UserEntity fetchedUser = userEntityRepository.findByEmailAddress(username)
                .orElseThrow(() -> new UserEntityNotFoundException(username));

        if (username.equals(editedUser.getEmailAddress())) {
            if (passwordEncoder.matches(editedUser.getOldPassword(), fetchedUser.getPassword())) {

            } else throw new WrongPasswordException();

        } else throw new UserNotAuthorizedException(username);

    }

    protected String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
