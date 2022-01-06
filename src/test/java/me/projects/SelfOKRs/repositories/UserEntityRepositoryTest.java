package me.projects.SelfOKRs.repositories;

import me.projects.SelfOKRs.entities.UserEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserEntityRepositoryTest {

    @Autowired
    UserEntityRepository userRepository;
    UserEntity user1;
    UserEntity user2;

    @BeforeAll
    public void setUp() {
        user1 = new UserEntity("test1","test1@t.com","fs9fds9fds09jf!0");
        user2 = new UserEntity("test2","test2@t.com","fs9fds9fds09jf!0");
        userRepository.save(user1);
        userRepository.save(user2);
    }
    @AfterAll
    public void tearDown() {
        userRepository.deleteAll();
    }

    // Test Create operation
    @Test
    public void shouldSaveUser() {
        UserEntity user = new UserEntity("newTest", "newTest@mail.com", "fasfd8sf8a8sdf");
        UserEntity savedUser = userRepository.save(user);
        assertThat(savedUser).usingRecursiveComparison().ignoringFields("id").isEqualTo(user);
    }

    // Test Read operation for All
    @Test
    public void shouldReturnListOfAllUsers() {
        List<UserEntity> userList = userRepository.findAll();
        assertEquals(user1.getId(), userList.get(0).getId());
        assertEquals(user2.getId(), userList.get(1).getId());
    }

    // Test Read operation for One
    @Test
    public void givenIdShouldReturnSameUserEmailWithThatId() {
        Optional<UserEntity> fetchedUser1 = userRepository.findById(user1.getId());
        assertEquals(user1.getEmailAddress(), fetchedUser1.get().getEmailAddress());
        Optional<UserEntity> fetchedUser2 = userRepository.findById(user2.getId());
        assertEquals(user2.getEmailAddress(), fetchedUser2.get().getEmailAddress());
    }

    // Test Update operation
    @Test
    public void givenIdShouldUpdateUser () {
        Optional<UserEntity> fetchedUser = userRepository.findById(user1.getId());
        UserEntity updatedUser = fetchedUser.get();
        updatedUser.setEmailAddress("newtestingmail@mail.com");
        userRepository.save(updatedUser);
        fetchedUser = userRepository.findById(user1.getId());
        assertEquals(updatedUser.getEmailAddress(), fetchedUser.get().getEmailAddress());
    }


    // Test Delete operation
    @Test
    public void givenIdShouldDeleteUser() {
        List<UserEntity> fullUserList = userRepository.findAll();
        userRepository.deleteById(user2.getId());
        Optional<UserEntity> fetchDeletedUser = userRepository.findById(user2.getId());
        assertEquals(true, fetchDeletedUser.isEmpty());
        List<UserEntity> updatedUserList = userRepository.findAll();
        assertEquals((fullUserList.size() - 1), updatedUserList.size());
    }
}
