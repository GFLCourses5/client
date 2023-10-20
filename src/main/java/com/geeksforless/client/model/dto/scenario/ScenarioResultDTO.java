package com.geeksforless.client.model.dto.scenario;

public class ScenarioResultDTO {
    private Integer id;
    private String name;
    private String result;

    public ScenarioResultDTO() {
    }

    public ScenarioResultDTO(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
