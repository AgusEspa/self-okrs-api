package me.projects.SelfOKRs;

import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    User user1;
    User user2;

    @BeforeAll
    public void setUp() {
        user1 = new User("test1","test1@t.com","fs9fds9fds09jf!0");
        user2 = new User("test2","test2@t.com","fs9fds9fds09jf!0");
        userRepository.save(user1);
        userRepository.save(user2);
    }
    @AfterAll
    public void tearDown() {
        userRepository.deleteById(user1.getId());
        userRepository.deleteById(user2.getId());
    }

    @Test
    public void shouldReturnListOfAllUsers() {
        List<User> userList = userRepository.findAll();
        assertEquals(user1.getId(), userList.get(userList.size()-2).getId());
        assertEquals(user2.getId(), userList.get(userList.size()-1).getId());
    }

    @Test
    public void givenIdShouldReturnSameUserEmailWithThatId() {
        Optional<User> fetchedUser1 = userRepository.findById(user1.getId());
        assertEquals(user1.getEmailAddress(), fetchedUser1.get().getEmailAddress());
        Optional<User> fetchedUser2 = userRepository.findById(user2.getId());
        assertEquals(user2.getEmailAddress(), fetchedUser2.get().getEmailAddress());
    }

}
