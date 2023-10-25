package com.geeksforless.client.model.dto.scenario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenarioResultDTO {
    private Integer id;
    private String name;
    private String site;
    private List<StepResultDTO> stepsResults;
    private OffsetDateTime executedAt;
}
