package com.example.location_service.Location_Service.controller;



import com.example.location_service.Location_Service.dto.APIResponseDto;
import com.example.location_service.Location_Service.dto.LocationRequestDTO;
import com.example.location_service.Location_Service.dto.LocationResponseDTO;
import com.example.location_service.Location_Service.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService service;

    @PostMapping
    public LocationResponseDTO create(@RequestBody @Valid LocationRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public APIResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<LocationResponseDTO> getAll() {
        return service.getAll();
    }
    @PutMapping("/{id}")
    public LocationResponseDTO update(
            @PathVariable Long id,
            @RequestBody @Valid LocationRequestDTO dto) {

        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Location deleted successfully";
    }
}
