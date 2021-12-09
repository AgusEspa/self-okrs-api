package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.exceptions.UserNotFoundException;
import me.projects.SelfOKRs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService() {}

    public List<User> all() {
        return userRepository.findAll();
    }

    public User one(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User newUser(User newUser) {
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    public void deleteOne(Long id) {
        userRepository.deleteById(id);
    }
}
