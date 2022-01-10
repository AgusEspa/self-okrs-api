package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import me.projects.SelfOKRs.security.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService {

    private final UserEntityRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserEntityService(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> all() {
        return userRepository.findAll();
    }

    public UserEntity one(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserEntityNotFoundException(id));
    }

    public UserEntity newUser(RegistrationForm newUser) {
        return userRepository.save(newUser.toUser());
    }

    public UserEntity editUser(Long id, UserEntity editedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(editedUser.getUsername());
                    user.setEmailAddress(editedUser.getEmailAddress());
                    user.setPassword(passwordEncoder.encode(editedUser.getPassword()));
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserEntityNotFoundException(id));
    }

    public void deleteOne(Long id) {
        userRepository.deleteById(id);
    }

}
