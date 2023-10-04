package com.geeksforless.client.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProxyNetworkConfig {
    private String hostname;
    private Integer port;
}