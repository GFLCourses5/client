package com.geeksforless.client.service.user;

import com.geeksforless.client.model.dto.UserDto;
import com.geeksforless.client.model.request.SaveUserRequest;

public interface RegistrationService {
    UserDto createUser(SaveUserRequest request);
}
