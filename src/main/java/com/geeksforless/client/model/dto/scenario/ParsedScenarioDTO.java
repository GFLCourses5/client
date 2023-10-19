package com.geeksforless.client.model.dto.scenario;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ParsedScenarioDTO {
    @JsonProperty("scenarios")
    private List<Scenario> scenarios;
}
