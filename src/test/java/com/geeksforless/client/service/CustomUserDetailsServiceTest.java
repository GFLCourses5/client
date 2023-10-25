package com.geeksforless.client.service;

import com.geeksforless.client.exception.UserNotFoundException;
import com.geeksforless.client.model.entity.User;
import com.geeksforless.client.repository.UserRepository;
import com.geeksforless.client.service.user.impl.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomUserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;

    private CustomUserDetailsService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new CustomUserDetailsService(userRepository);
    }


    @Test
    public void testLoadUserByUsername() {
        String email = "test@example.com";
        Optional<User> expectedUser = Optional.of(prepareUser());
        Mockito.when(userRepository.findByEmail(email)).thenReturn(expectedUser);
        UserDetails actualUser = userService.loadUserByUsername(email);
        assertEquals(email, actualUser.getUsername());
    }


    @Test
    public void testLoadUserByEmail() {
        String email = "nonexistent@example.com";
        Optional<User> expectedUser = Optional.empty();
        Mockito.when(userRepository.findByEmail(email)).thenReturn(expectedUser);
        assertThrows(UserNotFoundException.class, () -> userService.loadUserByUsername(email));
    }

    private User prepareUser(){
        return new User(1L, "fsf","test@example.com", "name");
    }
}
