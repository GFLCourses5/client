package com.geeksforless.client.client;

import com.geeksforless.client.model.request.ProxyParameters;
import com.geeksforless.client.model.response.WebshareResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Feign client interface for making HTTP requests to the Webshare Proxy API.
 *
 * This interface defines methods for interacting with the Webshare Proxy API.
 * It uses the Feign client library to simplify making HTTP requests to the
 * specified URL.
 *
 * @see FeignClient
 * @see WebshareResponse
 * @see ProxyParameters
 */
@FeignClient(
        value = "webshare",
        url = "https://proxy.webshare.io/api/v2"
)
public interface FeignProxyClient {
    /**
     * Retrieves a list of proxies from the Webshare Proxy API.
     *
     * This method sends a GET request to the "/proxy/list" endpoint of the
     * Webshare Proxy API to retrieve a list of available proxies.
     *
     * @param params The query parameters for the request.
     * @return A {@link WebshareResponse} containing the list of proxies.
     */
    @GetMapping("/proxy/list")
    WebshareResponse getProxyList(@SpringQueryMap ProxyParameters params);
}