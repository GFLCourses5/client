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

@FeignClient(name = "worker-api", url = "${worker.service.url}")
public interface WorkerClient {
    @PostMapping("/scenarios")
    @Headers("Content-Type: application/json")
    void postScenarios(@Valid ScenarioRequestDTO request);

    @GetMapping("/scenarios/user/{userId}")
    List<ScenarioResultDTO> getScenariosByUserId(@PathVariable("userId") Long userId);

    @GetMapping("/scenarios/{id}")
    ScenarioResultDTO getScenarioById(@PathVariable("id") Integer id);
}
