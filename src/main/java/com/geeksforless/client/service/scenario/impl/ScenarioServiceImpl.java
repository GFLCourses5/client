package com.geeksforless.client.service.scenario.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geeksforless.client.model.dto.scenario.*;
import com.geeksforless.client.model.entity.CustomUserDetails;
import com.geeksforless.client.service.scenario.ScenarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class ScenarioServiceImpl implements ScenarioService {

    @Override
    public void sendScenario(ScenarioFormDTO request) {
        List<Scenario> scenarios = toScenarioList(request.getScenariosList());
        ScenarioRequestDTO scenarioRequestDTO = ScenarioRequestDTO
                .builder()
                .scenarioList(scenarios)
                .userId(getCurrentUserId())
                .proxyRequired(request.getProxyRequired())
                .build();
        //TODO:Not implemented yet
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
