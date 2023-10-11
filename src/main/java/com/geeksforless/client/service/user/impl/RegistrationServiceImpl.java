package com.geeksforless.client.service.user.impl;

import com.geeksforless.client.mapper.UserMapper;
import com.geeksforless.client.model.dto.UserDto;
import com.geeksforless.client.model.entity.User;
import com.geeksforless.client.model.request.SaveUserRequest;
import com.geeksforless.client.repository.UserRepository;
import com.geeksforless.client.service.user.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserDto createUser(SaveUserRequest request) {
        String email = request.getEmail();
        if (userRepository.findByEmail(email).isPresent()) {
            return null;
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

}
