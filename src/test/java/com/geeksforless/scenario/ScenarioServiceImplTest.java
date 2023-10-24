package com.geeksforless.scenario;

import com.geeksforless.client.client.WorkerClient;
import com.geeksforless.client.model.dto.scenario.*;
import com.geeksforless.client.model.entity.CustomUserDetails;
import com.geeksforless.client.service.scenario.impl.ScenarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScenarioServiceImplTest {

    @Mock
    private WorkerClient workerClient;

    @InjectMocks
    private ScenarioServiceImpl scenarioService;

    @BeforeEach
    void setUp() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setId(1L);
        userDetails.setEmail("user@example.com");
        userDetails.setPassword("password");
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    void testSendScenario() {
        ScenarioFormDTO formData = new ScenarioFormDTO("[]", false);
        ScenarioRequestDTO expectedRequest = ScenarioRequestDTO.builder()
                .userId(1L)
                .scenarioList(Collections.emptyList())
                .proxyRequired(false)
                .build();
        scenarioService.sendScenario(formData);
        verify(workerClient).postScenarios(expectedRequest);
        verifyNoMoreInteractions(workerClient);
    }

    @Test
    void testGetScenarioResults() {
        Long userId = 1L;
        List<ScenarioResultDTO> expectedResults = Collections.singletonList(new ScenarioResultDTO());
        when(workerClient.getScenariosByUserId(userId)).thenReturn(expectedResults);
        List<ScenarioResultDTO> actualResults = scenarioService.getScenarioResults(userId);
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

        scenarioService.sendScenario(formData);

        ScenarioRequestDTO expectedRequest = ScenarioRequestDTO.builder()
                .userId(1L)
                .scenarioList(Collections.singletonList(new Scenario("Test scenario", "example.com",
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
            scenarioService.getScenarioResults(userId);
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
                .scenarioList(Collections.emptyList())
                .proxyRequired(false)
                .build();
        scenarioService.sendScenario(formData);
        verify(workerClient).postScenarios(expectedRequest);
        verifyNoMoreInteractions(workerClient);
    }
}