package com.geeksforless.client.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProxyResponse {
    private String username;
    private String password;
    private String proxy_address;
    private int port;
    private boolean valid;
}
