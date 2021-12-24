package me.projects.SelfOKRs.repositories;

import me.projects.SelfOKRs.entities.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
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
        userRepository.deleteAll();
    }

    @Test
    public void shouldSaveUser() {
        User user = new User("newTest", "newTest@mail.com", "fasfd8sf8a8sdf");
        User savedUser = userRepository.save(user);
        assertThat(savedUser).usingRecursiveComparison().ignoringFields("id").isEqualTo(user);
    }

    @Test
    public void shouldReturnListOfAllUsers() {
        List<User> userList = userRepository.findAll();
        assertEquals(user1.getId(), userList.get(0).getId());
        assertEquals(user2.getId(), userList.get(1).getId());
    }

    @Test
    public void givenIdShouldReturnSameUserEmailWithThatId() {
        Optional<User> fetchedUser1 = userRepository.findById(user1.getId());
        assertEquals(user1.getEmailAddress(), fetchedUser1.get().getEmailAddress());
        Optional<User> fetchedUser2 = userRepository.findById(user2.getId());
        assertEquals(user2.getEmailAddress(), fetchedUser2.get().getEmailAddress());
    }

}
