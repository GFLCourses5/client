package com.geeksforless.client.model.dto.scenario;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ScenarioRequestDTO {

    private Long userId;
    private List<Scenario> scenarioList;
    private boolean proxyRequired;

}
