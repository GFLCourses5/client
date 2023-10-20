package com.geeksforless.client.model.dto.scenario;

import lombok.Data;

@Data
public class StepResultDTO {
    private Step step;
    private boolean isPassed;
}
