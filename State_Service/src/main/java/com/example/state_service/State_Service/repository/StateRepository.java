package com.example.state_service.State_Service.repository;

import com.example.state_service.State_Service.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<StateEntity, Long> {
    boolean existsByCode(String code);
    boolean existsByIdAndIsDeletedFalse(Long id);
}
