package me.projects.SelfOKRs;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.projects.SelfOKRs.controllers.UserController;
import me.projects.SelfOKRs.entities.User;
import me.projects.SelfOKRs.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    @Mock
    private UserService userService;
    private User user;
    private List<User> users;

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        user = new User("test1","test1@t.com","fs9fds9fds09jf!0");
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    public void PostMappingOfUser() throws Exception {
        when(userService.newUser(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isCreated());
        verify(userService,times(1)).newUser(any());
    }

    @Test
    public void GetMappingOfAllUsers() throws Exception {
        when(userService.all()).thenReturn(users);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andDo(MockMvcResultHandlers.print());
        verify(userService).all();
        verify(userService,times(1)).all();
    }

    @Test
    public void GetMappingOfUserShouldReturnRespectiveUser() throws Exception {
        when(userService.one(user.getId())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void DeleteMappingUrlAndIdThenShouldReturnDeletedUser() throws Exception {
//        when(userService.deleteOne(user.getId())).thenReturn(user);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(user)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
