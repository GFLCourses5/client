package com.geeksforless.client.service.proxy;

import com.geeksforless.client.model.dto.ProxyConfigHolder;

import java.util.List;

/**
 * Interface for retrieving proxy configurations from a proxy service.
 *
 * This interface defines a contract for retrieving a list of proxy configurations
 * from a proxy service. Classes implementing this interface should provide
 * an implementation to fetch proxy configurations and return them as a list
 * of {@link ProxyConfigHolder} objects.
 *
 * @see ProxyConfigHolder
 */
public interface ProxyClientService {
    /**
     * Retrieves a list of proxy configurations from the proxy service.
     *
     * @return A list of {@link ProxyConfigHolder} objects representing proxy configurations.
     */
    List<ProxyConfigHolder> getProxyList();
}
