package me.projects.SelfOKRs.security;

import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserEntityRepository userRepository;

    @Autowired
    public JpaUserDetailsService(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final UserEntity fetchedUser = userRepository.findByEmailAddress(username);

        if (fetchedUser == null) {
            throw new UsernameNotFoundException(username);
        }

        final SecurityUser securityUser = new SecurityUser(fetchedUser);

        return securityUser;
    }
}
