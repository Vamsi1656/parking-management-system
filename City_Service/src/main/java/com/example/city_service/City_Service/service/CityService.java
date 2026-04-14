package com.example.city_service.City_Service.service;
import com.example.city_service.City_Service.dto.APIResponseDto;
import com.example.city_service.City_Service.dto.CityRequestDTO;
import com.example.city_service.City_Service.dto.CityResponseDTO;
import com.example.city_service.City_Service.dto.StateResponseDTO;
import com.example.city_service.City_Service.entity.CityEntity;
import com.example.city_service.City_Service.repository.CityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RestTemplate restTemplate;

    public CityResponseDTO createCity(CityRequestDTO dto) {

        //Duplicate check (same city in same state)
        if (repository.existsByNameAndStateId(dto.getName(), dto.getStateId())) {
            throw new RuntimeException("City already exists in this state");
        }

        CityEntity entity = new CityEntity();
        entity.setName(dto.getName());
        entity.setStateId(dto.getStateId());

        CityEntity saved = repository.save(entity);

        return mapToDTO(saved);
    }

    public List<CityResponseDTO> getAllCities() {
        return repository.findByIsDeletedFalse()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    public APIResponseDto getCityById(Long cityId) {

        CityEntity city = repository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City not found"));

        // Get token from incoming request
        String token = request.getHeader("Authorization");

        //  Add token to headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        // Call State Service WITH token
        ResponseEntity<StateResponseDTO> responseEntity =
                restTemplate.exchange(
                        "http://localhost:1909/api/states/" + city.getStateId(),
                        HttpMethod.GET,
                        entity,
                        StateResponseDTO.class
                );

        StateResponseDTO stateResponseDTO = responseEntity.getBody();

        CityResponseDTO cityResponseDTO = new CityResponseDTO(
                city.getId(),
                city.getName(),
                city.getStateId(),
                city.getIsActive(),
                city.getCreatedAt(),
                city.getUpdatedAt()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setCityResponseDTO(cityResponseDTO);
        apiResponseDto.setStateResponseDTO(stateResponseDTO);

        return apiResponseDto;
    }
    public CityResponseDTO updateCity(Long id, CityRequestDTO dto) {

        CityEntity city = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found"));

        city.setName(dto.getName());
        city.setStateId(dto.getStateId());

        CityEntity updated = repository.save(city);

        return mapToDTO(updated);
    }

    public void deleteCity(Long id) {

        CityEntity city = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found"));

        city.setIsDeleted(true);
        repository.save(city);
    }

    private CityResponseDTO mapToDTO(CityEntity city) {
        return new CityResponseDTO(
                city.getId(),
                city.getName(),
                city.getStateId(),
                city.getIsActive(),
                city.getCreatedAt(),
                city.getUpdatedAt()
        );
    }
}
