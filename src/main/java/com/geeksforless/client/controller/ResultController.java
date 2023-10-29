package com.geeksforless.client.controller;


import com.geeksforless.client.model.dto.scenario.ScenarioResultDTO;
import com.geeksforless.client.model.dto.scenario.StepResultDTO;
import com.geeksforless.client.service.scenario.ScenarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
public class ResultController {

    private ScenarioService scenarioService;

    @GetMapping("/scenarios")
    public String showResults(Model model, Authentication authentication) {
        List<ScenarioResultDTO> scenarioResultDTO = scenarioService.getScenarioResults(authentication);
        model.addAttribute("scenarioResults", scenarioResultDTO);
        return "result-page";
    }

    @GetMapping("/scenarios/{id}")
    public String showScenarioDetails(@PathVariable("id") Integer scenarioId, Model model) {
        ScenarioResultDTO scenarioResultDTO = scenarioService.getScenarioById(scenarioId);
        List<StepResultDTO> stepsResults = scenarioResultDTO.getStepsResults();
        model.addAttribute("stepsResults", stepsResults);
        model.addAttribute("scenarioName", scenarioResultDTO.getName());
        return "stepResult-page";
    }
}