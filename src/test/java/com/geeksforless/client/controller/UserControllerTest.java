package com.geeksforless.client.controller;

import com.geeksforless.client.model.dto.UserDto;
import com.geeksforless.client.model.request.SaveUserRequest;
import com.geeksforless.client.service.user.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @MockBean
    private RegistrationService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("loginPage"));
    }

    @Test
    void testLogout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?logout"));
    }

    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/registration"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration"));
    }

    @Test
    void testCreateUser_UserExists() throws Exception {
        SaveUserRequest saveUserRequest = new SaveUserRequest();
        when(service.createUser(saveUserRequest)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                        .flashAttr("user", saveUserRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("errorMessage", "User with email: " + saveUserRequest.getEmail() + " already exists"))
                .andExpect(MockMvcResultMatchers.view().name("registration"));
    }

    @Test
    void testCreateUser_Success() throws Exception {
        SaveUserRequest saveUserRequest = new SaveUserRequest();
        when(service.createUser(saveUserRequest)).thenReturn(new UserDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                        .flashAttr("user", saveUserRequest))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/login"));
    }
}
