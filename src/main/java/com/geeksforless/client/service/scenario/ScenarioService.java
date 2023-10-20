package com.geeksforless.client.service.scenario;

import com.geeksforless.client.model.dto.scenario.ScenarioFormDTO;
import com.geeksforless.client.model.dto.scenario.ScenarioResultDTO;

import java.util.List;

public interface ScenarioService {

    void sendScenario(ScenarioFormDTO request);

    List<ScenarioResultDTO> getScenarioResults(Long userId);

    ScenarioResultDTO getScenarioById(Integer id);

}
