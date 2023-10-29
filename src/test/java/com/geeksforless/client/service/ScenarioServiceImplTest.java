package com.geeksforless.client.service;

import com.geeksforless.client.client.WorkerClient;
import com.geeksforless.client.model.dto.scenario.*;
import com.geeksforless.client.model.entity.CustomUserDetails;
import com.geeksforless.client.service.scenario.impl.ScenarioServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScenarioServiceImplTest {

    @Mock
    private WorkerClient workerClient;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ScenarioServiceImpl scenarioService;

    @BeforeEach
    void setUp() {
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setId(1L);
        userDetails.setEmail("user@example.com");
        userDetails.setPassword("password");
        lenient().when(authentication.getPrincipal()).thenReturn(userDetails);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(workerClient);
    }

    @Test
    void testGetScenarioResults() {
        Long userId = 1L;
        List<ScenarioResultDTO> expectedResults = Collections.singletonList(new ScenarioResultDTO());
        when(workerClient.getScenariosByUserId(userId)).thenReturn(expectedResults);
        List<ScenarioResultDTO> actualResults = scenarioService.getScenarioResults(authentication);
        assertEquals(expectedResults, actualResults);
    }

    @Test
    void testGetScenarioById() {
        Integer scenarioId = 1;
        ScenarioResultDTO expectedResult = new ScenarioResultDTO();
        when(workerClient.getScenarioById(scenarioId)).thenReturn(expectedResult);
        ScenarioResultDTO actualResult = scenarioService.getScenarioById(scenarioId);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testSendScenario_ValidJson_ShouldPostScenarios() {
        String validJson = "[{\"name\":\"Test scenario\",\"site\":\"example.com\"," +
                "\"steps\":[{\"action\":\"Click\",\"value\":\"Button\"}]}]";
        ScenarioFormDTO formData = new ScenarioFormDTO(validJson, true);
        doNothing().when(workerClient).postScenarios(any(ScenarioRequestDTO.class));

        scenarioService.sendScenario(formData, authentication);
        ScenarioRequestDTO expectedRequest = ScenarioRequestDTO.builder()
                .userId(1L)
                .scenarios(Collections.singletonList(new Scenario("Test scenario", "example.com",
                        Collections.singletonList(new Step("Click", "Button")))))
                .proxyRequired(true)
                .build();
        verify(workerClient).postScenarios(expectedRequest);
    }

    @Test
    void testGetScenarioResults_ExceptionThrown_ShouldHandleException() {
        Long userId = 1L;
        when(workerClient.getScenariosByUserId(userId)).thenThrow(new RuntimeException("Error retrieving scenarios"));

        assertThrows(RuntimeException.class, () -> {
            scenarioService.getScenarioResults(authentication);
        });
    }

    @Test
    void testGetScenarioById_ExceptionThrown_ShouldHandleException() {
        Integer scenarioId = 1;
        when(workerClient.getScenarioById(scenarioId)).thenThrow(new RuntimeException("Error retrieving scenario"));

        assertThrows(RuntimeException.class, () -> {
            scenarioService.getScenarioById(scenarioId);
        });
    }

    @Test
    void testSendScenario_EmptyScenariosList_ShouldPostEmptyList() {
        ScenarioFormDTO formData = new ScenarioFormDTO("[]", false);
        ScenarioRequestDTO expectedRequest = ScenarioRequestDTO.builder()
                .userId(1L)
                .scenarios(Collections.emptyList())
                .proxyRequired(false)
                .build();
        scenarioService.sendScenario(formData, authentication);
        verify(workerClient).postScenarios(expectedRequest);
        verifyNoMoreInteractions(workerClient);
    }
}