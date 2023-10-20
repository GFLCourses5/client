package com.geeksforless.client.model.dto.scenario;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class ScenarioResultDTO {
    private Integer id;
    private String name;
    private String site;
    private List<StepResultDTO> stepsResults;
    private OffsetDateTime executedAt;
}
