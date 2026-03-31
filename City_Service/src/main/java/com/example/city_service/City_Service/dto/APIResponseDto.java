package com.example.city_service.City_Service.dto;

public class APIResponseDto {
    private CityResponseDTO cityResponseDTO;
    private StateResponseDTO stateResponseDTO;

    public APIResponseDto(CityResponseDTO cityResponseDTO, StateResponseDTO stateResponseDTO) {
        this.cityResponseDTO = cityResponseDTO;
        this.stateResponseDTO = stateResponseDTO;
    }

    public APIResponseDto() {
        super();
    }

    public CityResponseDTO getCityResponseDTO() {
        return cityResponseDTO;
    }

    public void setCityResponseDTO(CityResponseDTO cityResponseDTO) {
        this.cityResponseDTO = cityResponseDTO;
    }

    public StateResponseDTO getStateResponseDTO() {
        return stateResponseDTO;
    }

    public void setStateResponseDTO(StateResponseDTO stateResponseDTO) {
        this.stateResponseDTO = stateResponseDTO;
    }
}
