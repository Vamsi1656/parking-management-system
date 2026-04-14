package com.example.location_service.Location_Service.dto;

public class APIResponseDto {

    private LocationResponseDTO locationResponseDTO;
    private CityResponseDTO cityResponseDTO;

    public APIResponseDto(LocationResponseDTO locationResponseDTO, CityResponseDTO cityResponseDTO) {
        this.locationResponseDTO = locationResponseDTO;
        this.cityResponseDTO = cityResponseDTO;
    }

    public APIResponseDto() {
        super();
    }

    
    public LocationResponseDTO getLocationResponseDTO() {
        return locationResponseDTO;
    }

    public void setLocationResponseDTO(LocationResponseDTO locationResponseDTO) {
        this.locationResponseDTO = locationResponseDTO;
    }

    public CityResponseDTO getCityResponseDTO() {
        return cityResponseDTO;
    }

    public void setCityResponseDTO(CityResponseDTO cityResponseDTO) {
        this.cityResponseDTO = cityResponseDTO;
    }
}