package com.geeksforless.client.controller;


import com.geeksforless.client.model.dto.scenario.ScenarioResultDTO;
import com.geeksforless.client.service.scenario.ScenarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
public class ResultController {

    private ScenarioService scenarioService;

    @GetMapping("/scenarios")
    public String showResults(Model model) {
//        TODO: Fix getScenarioResults
        List<ScenarioResultDTO> scenarioResultDTO = Arrays.asList(
                scenarioService.getScenarioById(1),
                scenarioService.getScenarioById(1),
                scenarioService.getScenarioById(1));
        model.addAttribute("scenarioResults", scenarioResultDTO);
        return "result-page";
    }


}