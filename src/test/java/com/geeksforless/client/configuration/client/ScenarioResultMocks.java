package com.geeksforless.client.configuration.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geeksforless.client.model.dto.scenario.ScenarioRequestDTO;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;


public class ScenarioResultMocks {

    private static final String SCENARIO_RESULT_LIST_JSON = "payload/get-scenarios-result-response.json";
    private static final String SCENARIO_RESULT_BY_ID_JSON = "payload/get-scenario-result-by-id-response.json";

    public static void setupMockScenariosListResponse(WireMockServer mockService, Long userId) throws IOException {
        String url = String.format("/scenarios/user/%d", userId);
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo(url))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        ScenarioResultMocks.class.getClassLoader().getResourceAsStream(SCENARIO_RESULT_LIST_JSON),
                                        defaultCharset()))));
    }

    public static void setupMockScenarioResponseById(WireMockServer mockService, Integer resultId) throws IOException {
        String url = String.format("/scenarios/%d", resultId);
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo(url))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        ScenarioResultMocks.class.getClassLoader().getResourceAsStream(SCENARIO_RESULT_BY_ID_JSON),
                                        defaultCharset()))));
    }

    public static void setupMockSendScenarioRequest(WireMockServer mockService, ScenarioRequestDTO expectedRequest) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedRequestBody = objectMapper.writeValueAsString(expectedRequest);
        mockService.stubFor(WireMock.post(WireMock.urlEqualTo("/scenarios"))
                .withRequestBody(WireMock.equalToJson(expectedRequestBody))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())));
    }
}
