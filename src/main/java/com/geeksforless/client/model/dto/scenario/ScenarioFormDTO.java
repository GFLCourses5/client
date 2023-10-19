package com.geeksforless.client.model.dto.scenario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenarioFormDTO {

    private String scenariosList;
    private Boolean proxyRequired;
}
