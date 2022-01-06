package me.projects.SelfOKRs.services;

import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.repositories.UserEntityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserEntityServiceTest {

    @Mock
    UserEntityRepository userRepository;

    @InjectMocks
    UserEntityService userService;

    // Test GET request for all

    // Test GET request for one

    // Test POST request
    @Test
    public void shouldCreateNewUser() {
        UserEntity newUser = new UserEntity("test1", "test1@mail.com", "testing_pass1");

        when(userRepository.save(newUser)).thenReturn(newUser);

        UserEntity created = userService.newUser(newUser);

        assertEquals(created.getUsername(), newUser.getUsername());
    }

    // Test PUT request

    // Test DELETE request
}
