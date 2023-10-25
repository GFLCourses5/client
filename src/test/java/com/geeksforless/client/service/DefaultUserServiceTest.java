package com.geeksforless.client.service;


import com.geeksforless.client.exception.UserNotFoundException;
import com.geeksforless.client.model.dto.UserDto;
import com.geeksforless.client.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import com.geeksforless.client.repository.UserRepository;
import com.geeksforless.client.service.user.impl.DefaultUserService;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DefaultUserServiceTest {

    @Mock
    private UserRepository userRepository;

    private DefaultUserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new DefaultUserService(userRepository);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        userService.delete(userId);
        Mockito.verify(userRepository).deleteById(userId);
    }

    @Test
    public void testFindUserByEmail() {
        String email = "test@example.com";
        Optional<User> expectedUser = Optional.of(prepareUser());
        Mockito.when(userRepository.findByEmail(email)).thenReturn(expectedUser);
        UserDto actualUser = userService.findByEmail(email);
        assertEquals(email, actualUser.getEmail());

    }
    @Test
    public void testFindUserById() {
        Long id = 1L;
        Optional<User> expectedUser = Optional.of(prepareUser());
        Mockito.when(userRepository.findById(id)).thenReturn(expectedUser);
        UserDto actualUser = userService.findById(id);
        assertEquals(id, actualUser.getId());

    }

    @Test
    public void testFindUserByEmailUserNotFound() {
        String email = "nonexistent@example.com";
        Optional<User> expectedUser = Optional.empty();
        Mockito.when(userRepository.findByEmail(email)).thenReturn(expectedUser);
        assertThrows(UserNotFoundException.class, () -> userService.findByEmail(email));
    }

     private User prepareUser(){
        return new User(1L, "fsf","test@example.com", "name");
     }
}