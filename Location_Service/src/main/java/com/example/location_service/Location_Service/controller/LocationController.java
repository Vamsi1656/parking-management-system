package com.example.location_service.Location_Service.controller;
import com.example.location_service.Location_Service.dto.APIResponseDto;
import com.example.location_service.Location_Service.dto.LocationRequestDTO;
import com.example.location_service.Location_Service.dto.LocationResponseDTO;
import com.example.location_service.Location_Service.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService service;

    //CREATE → ADMIN only
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public LocationResponseDTO create(@RequestBody @Valid LocationRequestDTO dto) {
        return service.create(dto);
    }

    // GET BY ID → ADMIN + USER
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public APIResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    //GET ALL → ADMIN + USER
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public List<LocationResponseDTO> getAll() {
        return service.getAll();
    }

    //UPDATE → ADMIN only
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public LocationResponseDTO update(
            @PathVariable Long id,
            @RequestBody @Valid LocationRequestDTO dto) {

        return service.update(id, dto);
    }

    // 🔐 DELETE → ADMIN only
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Location deleted successfully";
    }
}