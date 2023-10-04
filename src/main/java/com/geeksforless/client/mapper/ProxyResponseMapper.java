package com.geeksforless.client.mapper;

import com.geeksforless.client.model.dto.ProxyConfigHolder;
import com.geeksforless.client.model.dto.ProxyCredentials;
import com.geeksforless.client.model.dto.ProxyNetworkConfig;
import com.geeksforless.client.model.response.ProxyResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper utility class for mapping ProxyResponse objects to ProxyConfigHolder DTOs.
 *
 * This utility class provides static methods to map ProxyResponse objects to
 * ProxyConfigHolder DTOs, allowing for easy conversion between different data types.
 *
 * @see ProxyConfigHolder
 * @see ProxyResponse
 */
@Component
public class ProxyResponseMapper {
    /**
     * Maps a ProxyResponse object to a ProxyConfigHolder DTO.
     *
     * @param response The ProxyResponse object to be mapped.
     * @return A ProxyConfigHolder DTO representing the mapped data.
     */
    public static ProxyConfigHolder toProxyDto(ProxyResponse response) {
        return ProxyConfigHolder.builder()
                .proxyNetworkConfig(getProxyNetworkConfig(response))
                .proxyCredentials(getProxyCredentials(response))
                .build();
    }

    private static ProxyNetworkConfig getProxyNetworkConfig(ProxyResponse response) {
        return ProxyNetworkConfig.builder()
                .port(response.getPort())
                .hostname(response.getProxy_address())
                .build();
    }

    private static ProxyCredentials getProxyCredentials(ProxyResponse response) {
        return ProxyCredentials.builder()
                .username(response.getUsername())
                .password(response.getPassword())
                .build();
    }

/**
 * Maps a list of ProxyResponse objects to a list of ProxyConfigHolder DTOs.
 *
 * @param responseList The list of ProxyResponse objects to be mapped.
 * @return The list of ProxyConfigHolder DTO representing the mapped data.
 */
    public static List<ProxyConfigHolder> toCollectionDto(List<ProxyResponse> responseList) {
        return responseList.stream()
                .map(ProxyResponseMapper::toProxyDto)
                .collect(Collectors.toList());
    }
}
