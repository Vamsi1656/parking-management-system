package com.example.city_service.City_Service.controller;


import com.example.city_service.City_Service.dto.APIResponseDto;
import com.example.city_service.City_Service.dto.CityRequestDTO;
import com.example.city_service.City_Service.dto.CityResponseDTO;
import com.example.city_service.City_Service.service.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService service;

    @PostMapping
    public ResponseEntity<CityResponseDTO> createCity(
            @RequestBody @Valid CityRequestDTO dto) {

        return new ResponseEntity<>(service.createCity(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CityResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllCities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDto> getById(@PathVariable Long id) {
   APIResponseDto apiResponseDto=service.getCityById(id);
        return new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CityRequestDTO dto) {

        return ResponseEntity.ok(service.updateCity(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        service.deleteCity(id);
        return ResponseEntity.ok("City deleted successfully");
    }
}
