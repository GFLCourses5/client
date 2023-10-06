package com.geeksforless.client.mapper;

import com.geeksforless.client.model.dto.UserDto;
import com.geeksforless.client.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    public static User toUser(UserDto userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
