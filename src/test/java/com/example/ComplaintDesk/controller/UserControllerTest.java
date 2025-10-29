package com.example.ComplaintDesk.controller;

import com.example.ComplaintDesk.model.User;
import com.example.ComplaintDesk.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Test User");
        user1.setEmail("test@example.com");
        user1.setRole("user");

        List<User> users = Arrays.asList(user1);
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/users/"))
                .andExpect(status().isOk());
    }
}
