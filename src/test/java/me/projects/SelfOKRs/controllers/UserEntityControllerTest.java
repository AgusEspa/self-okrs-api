package me.projects.SelfOKRs.controllers;

import me.projects.SelfOKRs.entities.UserEntity;
import me.projects.SelfOKRs.dtos.RegistrationForm;
import me.projects.SelfOKRs.services.UserEntityService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserEntityControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private UserEntityService userService;

    // Test GET request unauthenticated calls
    @Test
    public void ShouldBlockUnauthenticatedCalls() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().is(403));
    }

    // Test GET request for all
    // ROLE_ADMIN not yet implemented
    @Disabled
    @Test
    @WithMockUser
    public void shouldReturnAllUsers() throws Exception {
        when(userService.all())
                .thenReturn(List.of(
                        new UserEntity("test1", "test1@mail.com", "testing_pass1"),
                        new UserEntity("test2", "test2@mail.com", "testing_pass2")
                ));

        this.mockMvc
                .perform(get("/api/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"username\":\"test1\",\"emailAddress\":\"test1@mail.com\",\"password\":\"testing_pass1\"},{\"username\":\"test2\",\"emailAddress\":\"test2@mail.com\",\"password\":\"testing_pass2\"}]"))
        ;

        verify(userService, atLeastOnce()).all();
    }

    // Test GET request for one
    // ROLE_ADMIN not yet implemented
    @Disabled
    @Test
    @WithMockUser
    public void givenIdShouldReturnUser() throws Exception {
        UserEntity testUserWithId = new UserEntity("test1", "test1@mail.com", "testing_pass1");
        testUserWithId.setId(5L);
        when(userService.one(testUserWithId.getId())).thenReturn(testUserWithId);

        this.mockMvc
                .perform(get("/api/users/5"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":5,\"username\":\"test1\",\"emailAddress\":\"test1@mail.com\",\"password\":\"testing_pass1\"}"))
        ;

        verify(userService, atLeastOnce()).one(5L);
    }

    // Test POST request
    @Test
    public void shouldCreateNewUser() throws Exception {
        RegistrationForm testUser = new RegistrationForm("test1", "test1@mail.com", "testing_pass1");
        UserEntity user = testUser.toUser();
        when(userService.newUser(testUser)).thenReturn(user);

        this.mockMvc
                .perform(post("/api/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"test1\",\"emailAddress\": \"test1@mail.com\",\"password\": \"testing_pass1\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.emailAddress").value(testUser.getEmailAddress()))
        ;

        verify(userService, times(1)).newUser(any());

    }

    // Test PUT request
    @Test
    @WithMockUser
    public void shouldUpdateExistingUser() throws Exception {
        UserEntity testUser = new UserEntity("test1", "newtest@mail.com", "testing_pass1");
        when(userService.editUser(1L, testUser)).thenReturn(testUser);

        this.mockMvc
                .perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"test1\",\"emailAddress\": \"newtest@mail.com\",\"password\": \"testing_pass1\"}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.emailAddress").value(testUser.getEmailAddress()))
        ;

        verify(userService, times(1)).editUser(1L, testUser);
    }

    // Test PUT request exception
//    @Test
//    public void shouldReturnExceptionForNonExistentUser() throws Exception {
//        User testUser = new User("test1", "newtest@mail.com", "testing_pass1");
//        when(userService.editUser(1L, testUser)).thenReturn(new UserNotFoundException(testUser.getId()));
//
//        mockMvc.perform(put("/api/users/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"username\":\"test1\",\"emailAddress\":\"newtest@mail.com\",\"password\":\"testing_pass1\"}")
//                )
//                .andExpect(status().isBadRequest());
//
//        verify(userService, times(1)).checkAnswer(anyInt());
//    }

    // Test DELETE request
    @Test
    @WithMockUser
    public void shouldDeleteUser() throws Exception {

        this.mockMvc
                .perform(delete("/api/users/1"))
                .andExpect(status().isOk())
        ;

        verify(userService, times(1)).deleteOne(1L);
    }
}
