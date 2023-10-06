package com.geeksforless.client.service.user;

import com.geeksforless.client.model.dto.UserDto;

public interface UserService {
    void delete(Long id);

    UserDto findByEmail(String email);

    UserDto findById(Long id);
}
