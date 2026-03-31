package com.example.city_service.City_Service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CityRequestDTO {

    @NotBlank(message = "City name is required")
    private String name;

    @NotNull(message = "State ID is required")
    private Long stateId;

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getStateId() { return stateId; }
    public void setStateId(Long stateId) { this.stateId = stateId; }
}
