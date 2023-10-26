package com.geeksforless.client.service.scenario.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geeksforless.client.client.WorkerClient;
import com.geeksforless.client.model.dto.scenario.Scenario;
import com.geeksforless.client.model.dto.scenario.ScenarioFormDTO;
import com.geeksforless.client.model.dto.scenario.ScenarioRequestDTO;
import com.geeksforless.client.model.dto.scenario.ScenarioResultDTO;
import com.geeksforless.client.model.entity.CustomUserDetails;
import com.geeksforless.client.service.scenario.ScenarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ScenarioServiceImpl implements ScenarioService {

    private final WorkerClient client;

    @Override
    public void sendScenario(ScenarioFormDTO formData, Authentication authentication) {
        ScenarioRequestDTO request = prepareRequest(formData, authentication);
        client.postScenarios(request);
    }

    private ScenarioRequestDTO prepareRequest(ScenarioFormDTO formData, Authentication authentication) {
        List<Scenario> scenarios = toScenarioList(formData.getScenariosList());
        ScenarioRequestDTO request = ScenarioRequestDTO
                .builder()
                .scenarios(scenarios)
                .userId(getCurrentUserId(authentication))
                .proxyRequired(formData.getProxyRequired())
                .build();
        return request;
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

    private Long getCurrentUserId(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }

    private List<Scenario> parseJson(String scenarioJSON) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Scenario.class);
        return objectMapper.readValue(scenarioJSON, type);
    }

    @Override
    public List<ScenarioResultDTO> getScenarioResults(Authentication authentication) {
        long userId = getCurrentUserId(authentication);
        List<ScenarioResultDTO> scenarios = client.getScenariosByUserId(userId);
        return scenarios;
    }

    @Override
    public ScenarioResultDTO getScenarioById(Integer id) {
        ScenarioResultDTO scenario = client.getScenarioById(id);
        return scenario;
    }
}
