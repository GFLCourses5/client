package com.geeksforless.client.service.proxy.impl;

import com.geeksforless.client.client.FeignProxyClient;
import com.geeksforless.client.mapper.ProxyResponseMapper;
import com.geeksforless.client.model.dto.ProxyConfigHolder;
import com.geeksforless.client.model.request.Mode;
import com.geeksforless.client.model.request.ProxyParameters;
import com.geeksforless.client.model.response.ProxyResponse;
import com.geeksforless.client.model.response.WebshareResponse;
import com.geeksforless.client.service.proxy.ProxyClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that implements the ProxyClientService interface using Feign.
 *
 * This class provides an implementation of the ProxyClientService interface
 * by making HTTP requests to the Webshare Proxy API using the Feign client.
 *
 * @see ProxyClientService
 * @see FeignProxyClient
 */
@Service
@RequiredArgsConstructor
public class FeignProxyClientService implements ProxyClientService {
    private final FeignProxyClient client;
    /**
     * Retrieves a list of proxy configurations from the Webshare Proxy API.
     *
     * @return A list of {@link ProxyConfigHolder} objects representing the proxy configurations.
     */
    @Override
    public List<ProxyConfigHolder> getProxyList() {
        WebshareResponse response = client.getProxyList(getProxyParameters());
        return ProxyResponseMapper.toCollectionDto(response.getResults());
    }

    private  ProxyParameters getProxyParameters() {
        return ProxyParameters.builder()
                .mode(Mode.DIRECT.getName())
                .valid(true)
                .build();
    }
}
