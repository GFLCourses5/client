package com.geeksforless.client.client;

import com.geeksforless.client.configuration.ScenarioResultMocks;
import com.geeksforless.client.configuration.WireMockConfig;
import com.geeksforless.client.model.dto.scenario.*;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
public class WorkerClientTest {

    private static final long USER_ID = 1L;

    private static final int RESULT_ID = 1;
    private static final int EXPECTED_RESULT_COUNT = 20;
    private static final String EXPECTED_SCENARIO_NAME = "test scenario 1";
    private static final String EXPECTED_SCENARIO_SITE = "https://info.cern.ch";
    private static final String EXPECTED_SCENARIO_EXECUTION_DATE = "2023-10-19T13:04:07.796234Z";

    @Autowired
    private WireMockServer mockScenariosService;

    @Autowired
    private WorkerClient client;

    @Test
    public void whenGetScenarioResults_thenResultShouldBeReturned() throws IOException {
        ScenarioResultMocks.setupMockScenariosListResponse(mockScenariosService, USER_ID);
        List<ScenarioResultDTO> results = client.getScenariosByUserId(USER_ID);
        assertFalse(results.isEmpty());
    }

    @Test
    public void whenGetScenarioResults_thenReturnCorrectCount() throws IOException {
        ScenarioResultMocks.setupMockScenariosListResponse(mockScenariosService, USER_ID);
        List<ScenarioResultDTO> results = client.getScenariosByUserId(USER_ID);
        assertEquals(EXPECTED_RESULT_COUNT, results.size());
    }

    @Test
    public void whenGetScenarioResults_thenReturnCorrectType() throws IOException {
        ScenarioResultMocks.setupMockScenariosListResponse(mockScenariosService, USER_ID);
        List<ScenarioResultDTO> results = client.getScenariosByUserId(USER_ID);
        for (ScenarioResultDTO result : results) {
            assertEquals(ScenarioResultDTO.class, result.getClass());
        }
    }

    @Test
    public void whenGetScenarioById_thenReturnCorrectType() throws IOException {
        ScenarioResultMocks.setupMockScenarioResponseById(mockScenariosService, RESULT_ID);
        ScenarioResultDTO result = client.getScenarioById(RESULT_ID);
        assertEquals(ScenarioResultDTO.class, result.getClass());
    }

    @Test
    public void whenGetScenarioById_thenReturnCorrectResult() throws IOException {
        ScenarioResultMocks.setupMockScenarioResponseById(mockScenariosService, RESULT_ID);
        ScenarioResultDTO result = client.getScenarioById(RESULT_ID);
        assertEquals(prepareScenarioResults(), result);
    }

    private ScenarioResultDTO prepareScenarioResults() {
        ScenarioResultDTO result = new ScenarioResultDTO();
        result.setId(RESULT_ID);
        result.setName(EXPECTED_SCENARIO_NAME);
        result.setSite(EXPECTED_SCENARIO_SITE);
        result.setStepsResults(prepareStepResults());
        result.setExecutedAt(prepareDate(EXPECTED_SCENARIO_EXECUTION_DATE));
        return result;
    }

    private List<StepResultDTO> prepareStepResults(){
        return Arrays.asList(
                new StepResultDTO(new Step("clickCss", "body > ul > li:nth-child(1) > a"), true),
                new StepResultDTO(new Step("sleep", "5"), true),
                new StepResultDTO(new Step("clickXpath", "/html/body/p"), true)
        );
    }

    private OffsetDateTime prepareDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSX");
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, formatter);
        return offsetDateTime;
    }

    @Test
    public void whenPostScenarios_thenReturnCorrectResult() throws IOException {
        ScenarioResultMocks.setupMockSendScenarioRequest(mockScenariosService, prepareScenarioRequest());
        ScenarioRequestDTO request = prepareScenarioRequest();
        client.postScenarios(request);
        mockScenariosService.verify(postRequestedFor(urlEqualTo("/scenarios")));
    }

    private ScenarioRequestDTO prepareScenarioRequest() {
        ScenarioRequestDTO request = ScenarioRequestDTO.builder()
                .userId(null)
                .proxyRequired(false)
                .scenarioList(Arrays.asList(

                ))
                .build();
        return request;
    }
}
