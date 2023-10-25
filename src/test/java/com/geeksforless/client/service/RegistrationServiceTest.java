package com.geeksforless.client.service;


import com.geeksforless.client.mapper.UserMapper;
import com.geeksforless.client.model.dto.UserDto;
import com.geeksforless.client.model.entity.User;
import com.geeksforless.client.model.request.SaveUserRequest;
import com.geeksforless.client.service.user.impl.RegistrationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import com.geeksforless.client.repository.UserRepository;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    private RegistrationServiceImpl registrationService;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
        registrationService = new RegistrationServiceImpl(passwordEncoder, userRepository);
    }

    @Test
    public void testSaveUser(){
        User expectedUser = prepareUser();
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(expectedUser);
        SaveUserRequest request = prepareUserRequest();
        UserDto actualUser = registrationService.createUser(request);
        assertEquals(actualUser, UserMapper.toUserDto(expectedUser));
    }

    private User prepareUser(){
        return new User(1L, "fsf","test@example.com", "name");
    }

    private SaveUserRequest prepareUserRequest(){
        return new SaveUserRequest("name","fsf","test@example.com");
    }
}