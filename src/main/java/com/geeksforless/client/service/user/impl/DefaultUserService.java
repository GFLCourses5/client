package com.geeksforless.client.service.user.impl;

import com.geeksforless.client.exception.UserNotFoundException;
import com.geeksforless.client.mapper.UserMapper;
import com.geeksforless.client.model.dto.UserDto;
import com.geeksforless.client.repository.UserRepository;
import com.geeksforless.client.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with email %s not found", email)
                ));
    }

    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id).map(UserMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(
                    String.format("User with ID %d not found", id)
                ));
    }
}
