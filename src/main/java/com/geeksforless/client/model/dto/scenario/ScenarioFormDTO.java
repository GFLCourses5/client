package com.geeksforless.client.model.dto.scenario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScenarioFormDTO {

    private String scenariosList;
    private Boolean proxyRequired;
}
