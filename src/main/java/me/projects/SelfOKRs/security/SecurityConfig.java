package me.projects.SelfOKRs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    UserDetailsService authentication() {
        UserDetails agus = User.builder()
                .username("agus")
                .password("p")
                .roles("ADMIn")
                .build();

        return new InMemoryUserDetailsManager(agus);
    }
}
