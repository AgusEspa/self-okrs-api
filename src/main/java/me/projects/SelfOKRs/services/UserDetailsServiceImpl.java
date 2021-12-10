package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());

    }
}
