package com.geeksforless.client.repository;

import com.geeksforless.client.model.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void whenFindUserById_thenReturnUser() {
        Optional<User> optionalUser = repository.findById(1l);
        User actualUser = optionalUser.get();
        User expectedUser = prepareUser();
        assertEquals(expectedUser, actualUser);
    }

    @Test
    @Transactional
    public void whenSaveUser_thenReturnUser() {
        User user = prepareUser();
        User savedUser = repository.save(user);
        assertEquals(user, savedUser);
    }

    @Test
    @Transactional
    public void whenSaveAnotherUser_thenReturnCorrectCountAndId() {
        User user = prepareUser();
        user.setId(null);
        user.setName("Andrew");
        User savedUser = repository.save(user);
        long usersCount = repository.count();
        assertEquals(2l, savedUser.getId());
        assertEquals(2l, usersCount);
    }

    @Test
    @Transactional
    public void whenDeleteUser_thenReturnCorrectZeroCount() {
        repository.deleteById(1l);
        long usersCount = repository.count();
        assertEquals(0, usersCount);
    }

    private User prepareUser() {
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setEmail("test1@gmail.com");
        user.setPassword("password");
        return user;
    }
}
