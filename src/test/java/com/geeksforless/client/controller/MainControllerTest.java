package com.geeksforless.client.controller;

import com.geeksforless.client.configuration.security.TestUserDetailsConfig;
import com.geeksforless.client.model.dto.scenario.ScenarioFormDTO;
import com.geeksforless.client.service.scenario.ScenarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(MainController.class)
@Import({TestUserDetailsConfig.class})
@AutoConfigureMockMvc(addFilters = false)
public class MainControllerTest {

    @MockBean
    private ScenarioService scenarioService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(value="testuser", userDetailsServiceBeanName="userDetailsService")
    public void testMainForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("main"));
    }

    @Test
    @WithUserDetails(value="testuser", userDetailsServiceBeanName="userDetailsService")
    public void testHandleFormSubmit() throws Exception {
        ScenarioFormDTO scenarioFormDTO = new ScenarioFormDTO();

        mockMvc.perform(MockMvcRequestBuilders.post("/main")
                        .flashAttr("scenarioFormDTO", scenarioFormDTO))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/scenarios"));
    }
}
