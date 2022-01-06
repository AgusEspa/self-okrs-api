package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import me.projects.SelfOKRs.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserEntityRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final UserEntity fetchedUser = userRepository.findByEmailAddress(username);

        if (fetchedUser == null) {
            throw new UsernameNotFoundException(username);
        }

        final SecurityUser securityUser = new SecurityUser(fetchedUser);

        UserDetails user = User.withUsername(securityUser.getUsername())
                .password(securityUser.getPassword())
                .authorities("USER")
                .build();

        return user;
    }
}
