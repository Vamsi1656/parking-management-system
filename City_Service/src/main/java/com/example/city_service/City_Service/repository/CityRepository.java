package com.example.city_service.City_Service.repository;

import com.example.city_service.City_Service.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity,Long> {
    List<CityEntity> findByIsDeletedFalse();

    boolean existsByNameAndStateId(String name, Long stateId);
}
