package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    // Test GET request for all

    // Test GET request for one

    // Test POST request
    @Test
    public void shouldCreateNewUser() {
        User newUser = new User("test1", "test1@mail.com", "testing_pass1");

        when(userRepository.save(newUser)).thenReturn(newUser);

        User created = userService.newUser(newUser);

        assertEquals(created.getUsername(), newUser.getUsername());
    }

    // Test PUT request

    // Test DELETE request
}
