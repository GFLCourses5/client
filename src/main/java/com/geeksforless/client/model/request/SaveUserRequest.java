package com.geeksforless.client.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SaveUserRequest {
    @NotBlank(message = "Name must not be empty.")
    private String name;
    @NotBlank(message = "Password must not be empty.")
    private String password;
    @NotBlank(message = "Email must not be empty.")
    @Email(message = "Email must be a valid email address.")
    private String email;
}
