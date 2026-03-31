package com.example.location_service.Location_Service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class LocationRequestDTO {

    @NotBlank
    private String name;

    @NotNull
    private Long cityId;

    public LocationRequestDTO(String name, Long cityId) {
        this.name = name;
        this.cityId = cityId;
    }

    public LocationRequestDTO() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LocationRequestDTO that = (LocationRequestDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(cityId, that.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cityId);
    }

    @Override
    public String toString() {
        return "LocationRequestDTO{" +
                "name='" + name + '\'' +
                ", cityId=" + cityId +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
