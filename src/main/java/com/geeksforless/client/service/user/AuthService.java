package com.geeksforless.client.service.user;

import com.geeksforless.client.model.dto.UserDto;
import com.geeksforless.client.model.request.AuthRequest;
import com.geeksforless.client.model.request.SaveUserRequest;
import com.geeksforless.client.model.response.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);

    UserDto registration(SaveUserRequest request);
}
