package com.geeksforless.client.model.dto.scenario;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ScenarioRequestDTO {
    @NotNull
    private Long userId;
    @NotEmpty
    private List<Scenario> scenarios;
    private boolean proxyRequired;
}
