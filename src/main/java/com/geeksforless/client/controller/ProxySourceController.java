package com.geeksforless.client.controller;

import com.geeksforless.client.model.dto.ProxyConfigHolder;
import com.geeksforless.client.service.proxy.ProxyClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/proxies")
public class ProxySourceController {
    private final ProxyClientService service;

    @GetMapping
    public ResponseEntity<List<ProxyConfigHolder>> getAllProxies() {
        List<ProxyConfigHolder> configHolderList = service.getProxyList();
        return ResponseEntity.ok().body(configHolderList);
    }
}
