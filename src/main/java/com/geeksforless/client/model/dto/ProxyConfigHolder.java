package com.geeksforless.client.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProxyConfigHolder {
    private ProxyNetworkConfig proxyNetworkConfig;
    private ProxyCredentials proxyCredentials;
}
