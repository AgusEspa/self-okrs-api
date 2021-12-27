package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private UserService userService;

    // Test GET request for all
    @Test
    @DisplayName("Should return all users")
    public void shouldReturnAllUsers() throws Exception {
        when(userService.all())
                .thenReturn(List.of(
                        new User("test1", "test1@mail.com", "testing_pass1"),
                        new User("test2", "test2@mail.com", "testing_pass2")
                ));

        this.mockMvc
                .perform(get("/api/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"username\":\"test1\",\"emailAddress\":\"test1@mail.com\",\"password\":\"testing_pass1\"},{\"username\":\"test2\",\"emailAddress\":\"test2@mail.com\",\"password\":\"testing_pass2\"}]")
                );
    }

    // Test GET request for one
    @Test
    public void givenIdShouldReturnUserWithId() throws Exception {
        User testUserWithId = new User("test1", "test1@mail.com", "testing_pass1");
        testUserWithId.setId(5L);
        when(userService.one(testUserWithId.getId()))
                .thenReturn(testUserWithId);

        this.mockMvc
                .perform(get("/api/users/5"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":5,\"username\":\"test1\",\"emailAddress\":\"test1@mail.com\",\"password\":\"testing_pass1\"}")
                );
    }

    // Test POST request
    @Test
    public void shouldAllowCreationForUnauthenticatedUsers() throws Exception {
        this.mockMvc
                .perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"Testing\",\"email\":\"test@mail.com\",\"password\":\"testing_pass1\"}")
                )
                .andExpect(status().isCreated());

        verify(userService).newUser(any(User.class));
    }

    // Test PUT request

    // Test DELETE request
}
