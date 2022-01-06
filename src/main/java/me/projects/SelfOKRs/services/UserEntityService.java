package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.UserEntityNotFoundException;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService {

    private final UserEntityRepository userRepository;

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

    public UserEntity newUser(UserEntity newUser) {
        return userRepository.save(newUser);
    }

    public UserEntity editUser(Long id, UserEntity editedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(editedUser.getUsername());
                    user.setPassword(editedUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserEntityNotFoundException(id));
    }

    public void deleteOne(Long id) {
        userRepository.deleteById(id);
    }

}
