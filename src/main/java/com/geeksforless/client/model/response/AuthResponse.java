package com.geeksforless.client.model.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String tokenType;
}
