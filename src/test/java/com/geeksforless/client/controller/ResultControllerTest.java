package com.geeksforless.client.controller;

import com.geeksforless.client.configuration.security.TestUserDetailsConfig;
import com.geeksforless.client.model.dto.scenario.ScenarioResultDTO;
import com.geeksforless.client.model.dto.scenario.StepResultDTO;
import com.geeksforless.client.service.scenario.ScenarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ResultController.class)
@Import({TestUserDetailsConfig.class})
public class ResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScenarioService scenarioService;

    @Test
    @WithUserDetails(value="testuser", userDetailsServiceBeanName="userDetailsService")
    void testShowResults() throws Exception {
        List<ScenarioResultDTO> scenarioResultDTOList = new ArrayList<>();

        when(scenarioService.getScenarioResults(any(Authentication.class))).thenReturn(scenarioResultDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/scenarios"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("result-page"))
                .andExpect(MockMvcResultMatchers.model().attribute("scenarioResults", scenarioResultDTOList));
    }
    @Test
    @WithUserDetails(value="testuser", userDetailsServiceBeanName="userDetailsService")
    void testShowScenarioDetails() throws Exception {
        int scenarioId = 1;

        ScenarioResultDTO scenarioResultDTO = new ScenarioResultDTO();

        List<StepResultDTO> stepsResults = new ArrayList<>();

        scenarioResultDTO.setStepsResults(stepsResults);

        when(scenarioService.getScenarioById(scenarioId)).thenReturn(scenarioResultDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/scenarios/{id}", scenarioId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("stepResult-page"))
                .andExpect(MockMvcResultMatchers.model().attribute("stepsResults", stepsResults));
    }
}
