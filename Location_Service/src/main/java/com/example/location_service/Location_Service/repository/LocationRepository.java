package com.example.location_service.Location_Service.repository;

import com.example.location_service.Location_Service.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    List<LocationEntity> findByIsDeletedFalse();

    boolean existsByNameAndCityId(String name, Long cityId);
}
