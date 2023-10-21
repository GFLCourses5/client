package com.geeksforless.client.model.dto.scenario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StepResultDTO {
    private Step step;
    private boolean isPassed;
}
