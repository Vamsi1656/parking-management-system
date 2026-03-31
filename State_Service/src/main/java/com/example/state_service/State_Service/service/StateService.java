package com.example.state_service.State_Service.service;

import com.example.state_service.State_Service.dto.StateRequestDTO;
import com.example.state_service.State_Service.dto.StateResponseDTO;
import com.example.state_service.State_Service.entity.StateEntity;
import com.example.state_service.State_Service.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public StateResponseDTO createState(StateRequestDTO dto) {

        // ✅ DTO → Entity
        StateEntity entity = new StateEntity();
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setCountry(dto.getCountry());


        entity.setIsActive(true);
        entity.setIsDeleted(false);


        StateEntity saved = stateRepository.save(entity);


        return new StateResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getCode(),
                saved.getCountry(),
                saved.getIsActive(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );
    }



    public List<StateResponseDTO> getAllStates() {

        List<StateEntity> list = stateRepository.findAll();

        return list.stream()
                .map(state -> new StateResponseDTO(
                        state.getId(),
                        state.getName(),
                        state.getCode(),
                        state.getCountry(),
                        state.getIsActive(),
                        state.getCreatedAt(),
                        state.getUpdatedAt()
                ))
                .toList();
    }
    public StateResponseDTO getStateById(Long stateId) {

        StateEntity state = stateRepository.findById(stateId)
                .orElseThrow(() -> new RuntimeException("State not found"));

        return new StateResponseDTO(
                state.getId(),
                state.getName(),
                state.getCode(),
                state.getCountry(),
                state.getIsActive(),
                state.getCreatedAt(),
                state.getUpdatedAt()
        );
    }
    public StateResponseDTO updateState(Long id, StateRequestDTO dto) {

        StateEntity state = stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("State not found"));

        // Check duplicate code (important)
        if (stateRepository.existsByCode(dto.getCode())
                && !state.getCode().equals(dto.getCode())) {
            throw new RuntimeException("State code already exists");
        }

        // Update fields
        state.setName(dto.getName());
        state.setCode(dto.getCode());
        state.setCountry(dto.getCountry());

        StateEntity updated = stateRepository.save(state);

        return new StateResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getCode(),
                updated.getCountry(),
                updated.getIsActive(),
                updated.getCreatedAt(),
                updated.getUpdatedAt()
        );
    }
    public void deleteState(Long id) {

        StateEntity state = stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("State not found"));

        // Already deleted check
        if (state.getIsDeleted()) {
            throw new RuntimeException("State already deleted");
        }

        // Soft delete
        state.setIsDeleted(true);

        stateRepository.save(state);
    }
}