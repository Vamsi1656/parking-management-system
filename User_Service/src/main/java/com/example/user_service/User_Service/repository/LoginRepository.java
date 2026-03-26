package com.example.user_service.User_Service.repository;

import com.example.user_service.User_Service.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity,Long> {
    LoginEntity findByEmail(String email);
}
