package com.geeksforless.client.client;

import com.geeksforless.client.model.dto.scenario.ScenarioRequestDTO;
import com.geeksforless.client.model.dto.scenario.ScenarioResultDTO;
import feign.Headers;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "worker-api", url = "http://localhost:8080/api/v1")
public interface WorkerClient {
    @PostMapping("/scenarios")
    @Headers("Content-Type: application/json")
    void postScenarios(@Valid ScenarioRequestDTO request);

    @GetMapping("/scenarios/{userId}")
    List<ScenarioResultDTO> getScenarioResults(@PathVariable("userId") Long userId);
}
