package com.example.location_service.Location_Service.service;
import com.example.location_service.Location_Service.dto.APIResponseDto;
import com.example.location_service.Location_Service.dto.CityResponseDTO;
import com.example.location_service.Location_Service.dto.LocationRequestDTO;
import com.example.location_service.Location_Service.dto.LocationResponseDTO;
import com.example.location_service.Location_Service.entity.LocationEntity;
import com.example.location_service.Location_Service.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    //CREATE
    public LocationResponseDTO create(LocationRequestDTO dto) {

        //Validate city exists
        if (repository.existsByNameAndCityId(dto.getName(), dto.getCityId())) {
            throw new RuntimeException("Location already exists in this city");
        }

        LocationEntity entity = new LocationEntity();
        entity.setName(dto.getName());
        entity.setCityId(dto.getCityId());

        LocationEntity saved = repository.save(entity);

        return mapToDTO(saved);
    }

    // GET BY ID (Location + City + State)
    public APIResponseDto getById(Long id) {

        LocationEntity locationEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        ResponseEntity<CityResponseDTO> responseEntity=restTemplate.getForEntity("http://localhost:1908/api/cities/" + locationEntity.getCityId(),
                CityResponseDTO.class);
        CityResponseDTO cityResponseDTO=responseEntity.getBody();
        LocationResponseDTO locationResponseDTO = new LocationResponseDTO(
                locationEntity.getId(),
                locationEntity.getName(),
                locationEntity.getCityId(),
                locationEntity.getIsActive(),
                locationEntity.getCreatedAt(),
                locationEntity.getUpdatedAt()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setLocationResponseDTO(locationResponseDTO);
        apiResponseDto.setCityResponseDTO(cityResponseDTO);

        return apiResponseDto;
    }


    //GET ALL
    public List<LocationResponseDTO> getAll() {
        return repository.findByIsDeletedFalse()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    // UPDATE LOCATION
    public LocationResponseDTO update(Long id, LocationRequestDTO dto) {

        // Check location exists
        LocationEntity location = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        // Check duplicate (same name + city but different ID)
        boolean exists = repository.existsByNameAndCityId(dto.getName(), dto.getCityId());

        if (exists &&
                !(location.getName().equals(dto.getName()) &&
                        location.getCityId().equals(dto.getCityId()))) {

            throw new RuntimeException("Location already exists in this city");
        }

        // Update fields
        location.setName(dto.getName());
        location.setCityId(dto.getCityId());

        //Save
        LocationEntity updated = repository.save(location);

        return mapToDTO(updated);
    }

    // DELETE (SOFT DELETE)
    public void delete(Long id) {
        LocationEntity location = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        location.setIsDeleted(true);
        repository.save(location);
    }

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
