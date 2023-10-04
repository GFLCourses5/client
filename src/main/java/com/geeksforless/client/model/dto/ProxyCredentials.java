package com.geeksforless.client.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProxyCredentials {
    private String username;
    private String password;
}
