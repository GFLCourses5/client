package com.geeksforless.client.model.dto.scenario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepResultDTO {
    private Step step;
    private Boolean isPassed;
}
