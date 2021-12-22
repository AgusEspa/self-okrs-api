package me.projects.SelfOKRs;

import me.projects.SelfOKRs.controllers.UserController;
import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.services.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GoalControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldReturnAllUsersForUnauthenticatedUsers() throws Exception {
        when(userService.all())
                .thenReturn(List.of(new User("Testing", "test@mail.com", "testing_pass1")));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("Testing"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("test@mail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("testing_pass1"));
    }

//    @Test
//    public void shouldAllowCreationForUnauthenticatedUsers() throws Exception {
//        this.mockMvc
//                .perform(
//                        post("/api/users")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content("{\"username\": \"Testing\", \"email\":\"test@mail.com\", \"password\":\"testing_pass1\"}")
//                )
//                .andExpect(status().isCreated())
//                .andExpect(header().exists("Location"))
//                .andExpect(header().string("Location", Matchers.containsString("Testing")));
//
//        verify(userService).newUser(any(User.class));
//    }
}
