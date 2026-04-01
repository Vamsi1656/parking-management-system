package com.example.location_service.Location_Service.service;

import com.example.location_service.Location_Service.dto.APIResponseDto;
import com.example.location_service.Location_Service.dto.CityResponseDTO;
import com.example.location_service.Location_Service.dto.LocationRequestDTO;
import com.example.location_service.Location_Service.dto.LocationResponseDTO;
import com.example.location_service.Location_Service.entity.LocationEntity;
import com.example.location_service.Location_Service.repository.LocationRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository repository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RestTemplate restTemplate;

    //CREATE
    public LocationResponseDTO create(LocationRequestDTO dto) {

        if (repository.existsByNameAndCityId(dto.getName(), dto.getCityId())) {
            throw new RuntimeException("Location already exists in this city");
        }

        LocationEntity entity = new LocationEntity();
        entity.setName(dto.getName());
        entity.setCityId(dto.getCityId());

        LocationEntity saved = repository.save(entity);

        return mapToDTO(saved);
    }

    // GET BY ID (Location + City)
    public APIResponseDto getById(Long id) {

        LocationEntity locationEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        //Get token
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Authorization header missing");
        }

        // Add token to headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        //CALL CITY SERVICE (CORRECT TYPE)
        ResponseEntity<APIResponseDto> responseEntity =
                restTemplate.exchange(
                        "http://localhost:1908/api/cities/" + locationEntity.getCityId(),
                        HttpMethod.GET,
                        entity,
                        APIResponseDto.class
                );

        //NULL SAFETY CHECK
        if (responseEntity.getBody() == null) {
            throw new RuntimeException("City service response is null");
        }

        CityResponseDTO cityResponseDTO =
                responseEntity.getBody().getCityResponseDTO();

        // Build Location response
        LocationResponseDTO locationResponseDTO = new LocationResponseDTO(
                locationEntity.getId(),
                locationEntity.getName(),
                locationEntity.getCityId(),
                locationEntity.getIsActive(),
                locationEntity.getCreatedAt(),
                locationEntity.getUpdatedAt()
        );

        // Final API response
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setLocationResponseDTO(locationResponseDTO);
        apiResponseDto.setCityResponseDTO(cityResponseDTO);

        return apiResponseDto;
    }

    // GET ALL
    public List<LocationResponseDTO> getAll() {
        return repository.findByIsDeletedFalse()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    //UPDATE
    public LocationResponseDTO update(Long id, LocationRequestDTO dto) {

        LocationEntity location = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        boolean exists = repository.existsByNameAndCityId(dto.getName(), dto.getCityId());

        if (exists &&
                !(location.getName().equals(dto.getName()) &&
                        location.getCityId().equals(dto.getCityId()))) {

            throw new RuntimeException("Location already exists in this city");
        }

        location.setName(dto.getName());
        location.setCityId(dto.getCityId());

        LocationEntity updated = repository.save(location);

        return mapToDTO(updated);
    }

    // DELETE (SOFT)
    public void delete(Long id) {
        LocationEntity location = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        location.setIsDeleted(true);
        repository.save(location);
    }

    // MAPPER
    private LocationResponseDTO mapToDTO(LocationEntity entity) {
        return new LocationResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getCityId(),
                entity.getIsActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}