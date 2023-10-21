package com.geeksforless.client.controller;


import com.geeksforless.client.model.dto.scenario.ScenarioResultDTO;
import com.geeksforless.client.service.scenario.ScenarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ResultController {

    private ScenarioService scenarioService;

    @GetMapping("/results")
    public String showResults(Model model) {

        ScenarioResultDTO scenarioResultDTO = scenarioService.getScenarioById(1);
        model.addAttribute("scenarioResult", scenarioResultDTO);
        return "result-page";
    }
}
