package me.projects.SelfOKRs;

import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.repositories.UserRepository;
import me.projects.SelfOKRs.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    private UserService userService;
    private User user1;
    private User user2;
    private List<User> users;

    @BeforeEach
    public void setUp() {
        users = new ArrayList<>();
        user1 = new User("test1","test1@t.com","fs9fds9fds09jf!0");
        user2 = new User("test2","test2@t.com","fs9fds9fds09jf!0");
        users.add(user1);
        users.add(user2);
    }

    @AfterEach
    public void tearDown() {
        user1 = null;
        user2 = null;
        users = null;
    }

    @Test
    public void givenProductToAddShouldReturnAddedProduct() {
        when(userRepository.save(any())).thenReturn(user1);
        userService.newUser(user1);
        verify(userRepository,times(1)).save(any());
    }

    @Test
    public void givenGetAllUsersShouldReturnListOfAllUsers(){
        userRepository.save(user1);
        when(userRepository.findAll()).thenReturn(users);
        List<User> users1 = userService.all();
        assertEquals(users1, users);
        verify(userRepository,times(1)).save(user1);
        verify(userRepository,times(1)).findAll();
    }

    @Test
    public void givenIdThenShouldReturnUserOfThatId() {
        when(userRepository.findById(user1.getId())).thenReturn(Optional.ofNullable(user1));
        assertThat(userService.one(user1.getId())).isEqualTo(user1);
    }

//    @Test
//    public void givenIdTODeleteThenShouldDeleteTheUser(){
//        when(userService.deleteOne(user1.getId())).thenReturn(user1);
//        verify(userRepository,times(1)).findAll();
//    }

}
