package com.example.state_service.State_Service.controller;

import com.example.state_service.State_Service.dto.StateRequestDTO;
import com.example.state_service.State_Service.dto.StateResponseDTO;
import com.example.state_service.State_Service.service.StateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/states")
public class StateController {

    @Autowired
    private StateService service;

    // ✅ CREATE STATE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/state")
    public ResponseEntity<StateResponseDTO> createState(
            @RequestBody @Valid StateRequestDTO stateRequestDTO) {

        StateResponseDTO response = service.createState(stateRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<StateResponseDTO>> getAllStates() {

        List<StateResponseDTO> states = service.getAllStates();
        return new ResponseEntity<>(states, HttpStatus.OK);
    }
    @GetMapping("/{stateId}")
    public ResponseEntity<StateResponseDTO> getStateById(@PathVariable Long stateId) {

        StateResponseDTO state = service.getStateById(stateId);
        return new ResponseEntity<>(state, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<StateResponseDTO> updateState(
            @PathVariable Long id,
            @RequestBody StateRequestDTO dto) {

        StateResponseDTO updated = service.updateState(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteState(@PathVariable Long id) {

        service.deleteState(id);

        return new ResponseEntity<>("State deleted successfully", HttpStatus.OK);
    }
}