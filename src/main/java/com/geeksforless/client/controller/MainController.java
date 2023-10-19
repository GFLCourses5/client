package com.geeksforless.client.controller;

import com.geeksforless.client.model.dto.scenario.ScenarioFormDTO;
import com.geeksforless.client.service.scenario.ScenarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MainController {
    private final ScenarioService scenarioService;

    @GetMapping("/main")
    public String mainForm() {
        return "main";
    }

    @PostMapping(value = "/main")
    public String handleFormSubmit(@ModelAttribute ScenarioFormDTO scenarioFormDTO) {
        scenarioService.sendScenario(scenarioFormDTO);
        return "redirect:/waiting-page";
    }

    @ModelAttribute
    private ScenarioFormDTO setupForm () {
        return new ScenarioFormDTO();
    }
}
