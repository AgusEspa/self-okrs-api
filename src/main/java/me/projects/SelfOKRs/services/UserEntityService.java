package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.exceptions.UserAlreadyExistsException;
import me.projects.SelfOKRs.exceptions.UserNotAuthorizedException;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import me.projects.SelfOKRs.dtos.RegistrationForm;
import me.projects.SelfOKRs.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public UserEntity editUser(UserEntity editedUser) {
        String username = authenticationFacade.getAuthentication().getName();
        Optional<UserEntity> fetchedUser = userRepository.findByEmailAddress(username);
        if (fetchedUser.isEmpty() || fetchedUser.get().getEmailAddress() != username) {
            throw new UserNotAuthorizedException(username);
        } else {
            UserEntity user = fetchedUser.get();
            if (editedUser.getUsername() != null) {
                user.setUsername(editedUser.getUsername());
                return  userRepository.save(user);
            }
            else if (editedUser.getEmailAddress() != null) {
                user.setEmailAddress(editedUser.getEmailAddress());
                return  userRepository.save(user);
            }
            else if (editedUser.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(editedUser.getPassword()));
                return  userRepository.save(user);
            } else return user;
        }
    }

    // Refactor, not secure
    public void deleteOne(Long id) {
        userRepository.deleteById(id);
    }

}
