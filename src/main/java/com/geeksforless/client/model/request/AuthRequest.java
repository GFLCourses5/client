package com.geeksforless.client.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @Email(message = "Email must be a valid email address.")
    private String email;
    @NotBlank(message = "Password must not be empty.")
    private String password;
}
