package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.exceptions.UserNotFoundException;
import me.projects.SelfOKRs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> all() {
        return userRepository.findAll();
    }

    public User one(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User newUser(User newUser) {
        return userRepository.save(newUser);
    }

    public void deleteOne(Long id) {
        userRepository.deleteById(id);
    }
}
