package com.geeksforless.client.service.scenario.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geeksforless.client.model.dto.scenario.*;
import com.geeksforless.client.model.entity.CustomUserDetails;
import com.geeksforless.client.service.scenario.ScenarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ScenarioServiceImpl implements ScenarioService {

    @Override
    public void sendScenario(ScenarioFormDTO request) {
        List<Scenario> scenarios = mapScenarioRequestDTOJsonString(request.getScenariosList());
        ScenarioRequestDTO scenarioRequestDTO = ScenarioRequestDTO
                .builder()
                .scenarioList(scenarios)
                .userId(getCurrentUserId())
                .proxyRequired(request.getProxyRequired())
                .build();

    }

    @Override
    public List<ScenarioResultDTO> getScenarioResults(Long userId) {
        return null;
    }

    @Override
    public ScenarioResultDTO getScenarioById(Integer id) {
        return null;
    }

    private List<Scenario> mapScenarioRequestDTOJsonString(String scenarioJSON) {
        try {
            ParsedScenarioDTO parsedScenarioDTO = parseJson(scenarioJSON);
            List<Scenario> scenarios = parsedScenarioDTO.getScenarios();
            System.out.println(scenarios);
            return scenarios;
        } catch (IOException e) {
            handleIOException(e);
            return Collections.emptyList();
        }
    }

    private ParsedScenarioDTO parseJson(String scenarioJSON) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(scenarioJSON, ParsedScenarioDTO.class);
    }

    private List<Scenario> convertToScenarios(ParsedScenarioDTO parsedScenarioDTO) {
        List<Scenario> scenarios = new ArrayList<>();
        for (Scenario scenarioDTO : parsedScenarioDTO.getScenarios()) {
            Scenario scenario = createScenario(scenarioDTO);
            scenarios.add(scenario);
        }
        return scenarios;
    }

    private Scenario createScenario(Scenario scenarioDTO) {
        Scenario scenario = new Scenario();
        scenario.setName(scenarioDTO.getName());
        scenario.setSite(scenarioDTO.getSite());
        List<Step> steps = new ArrayList<>();
        for (Step stepDTO : scenarioDTO.getSteps()) {
            Step step = createStep(stepDTO);
            steps.add(step);
        }
        scenario.setSteps(steps);
        return scenario;
    }

    private Step createStep(Step stepDTO) {
        Step step = new Step();
        step.setAction(stepDTO.getAction());
        step.setValue(stepDTO.getValue());
        return step;
    }

    private void handleIOException(IOException e) {
        e.printStackTrace();
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}
