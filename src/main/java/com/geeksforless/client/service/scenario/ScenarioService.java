package com.geeksforless.client.service.scenario;

import com.geeksforless.client.model.dto.scenario.ScenarioFormDTO;
import com.geeksforless.client.model.dto.scenario.ScenarioResultDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ScenarioService {

    void sendScenario(ScenarioFormDTO request, Authentication authentication);

    List<ScenarioResultDTO> getScenarioResults(Authentication authentication);

    ScenarioResultDTO getScenarioById(Integer id);

}
