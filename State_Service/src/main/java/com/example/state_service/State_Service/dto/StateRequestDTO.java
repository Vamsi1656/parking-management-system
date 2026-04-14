package com.example.state_service.State_Service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class StateRequestDTO {
    @NotBlank(message = "State name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "State code is required")
    @Size(min = 2, max = 5, message = "Code must be between 2 and 5 characters")
    private String code;

    @NotBlank(message = "Country is required")
    private String country;

    public StateRequestDTO(String name, String code, String country) {
        this.name = name;
        this.code = code;
        this.country = country;
    }

    public StateRequestDTO() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StateRequestDTO that = (StateRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, country);
    }

    @Override
    public String toString() {
        return "StateRequestDTO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
