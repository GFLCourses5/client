package com.geeksforless.client.service.scenario.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geeksforless.client.client.WorkerClient;
import com.geeksforless.client.model.dto.scenario.*;
import com.geeksforless.client.model.entity.CustomUserDetails;
import com.geeksforless.client.service.scenario.ScenarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ScenarioServiceImpl implements ScenarioService {

    private final WorkerClient client;

    @Override
    public void sendScenario(ScenarioFormDTO formData) {
        ScenarioRequestDTO request = prepareRequest(formData);
        client.postScenarios(request);
    }

    private ScenarioRequestDTO prepareRequest(ScenarioFormDTO formData) {
        List<Scenario> scenarios = toScenarioList(formData.getScenariosList());
        ScenarioRequestDTO request = ScenarioRequestDTO
                .builder()
                .scenarioList(scenarios)
                .userId(getCurrentUserId())
                .proxyRequired(formData.getProxyRequired())
                .build();
        return request;
    }

    @Override
    public List<ScenarioResultDTO> getScenarioResults(Long userId) {
        //TODO:Not implemented yet
        return null;
    }

    @Override
    public ScenarioResultDTO getScenarioById(Integer id) {
        //TODO:Not implemented yet
        return null;
    }

    private List<Scenario> toScenarioList(String scenarioJSON) {
        try {
            List<Scenario> scenarios = parseJson(scenarioJSON);
            return scenarios;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private List<Scenario> parseJson(String scenarioJSON) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Scenario.class);
        return objectMapper.readValue(scenarioJSON, type);
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}
