package com.geeksforless.client.model.response;

import lombok.Data;

import java.util.List;

/**
* Class represents response obtained from https://proxy.webshare.io/api/.
 */
@Data
public class WebshareResponse {
    private Integer count;
    private List<ProxyResponse> results;
}
