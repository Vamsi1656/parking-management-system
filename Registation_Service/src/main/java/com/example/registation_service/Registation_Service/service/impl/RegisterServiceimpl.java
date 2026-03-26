package com.example.registation_service.Registation_Service.service.impl;

import com.example.registation_service.Registation_Service.dto.RegisterDTO;
import com.example.registation_service.Registation_Service.entity.RegisterEntity;
import com.example.registation_service.Registation_Service.repository.RegisterRepository;
import com.example.registation_service.Registation_Service.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterServiceimpl implements RegisterService {
    @Autowired
    private RegisterRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String registerUser(RegisterDTO registerdto){

        // Check email already exist or not
        Optional<RegisterEntity> existUser = repo.findByEmail(registerdto.getEmail());
        if(existUser.isPresent()){
            throw new RuntimeException("Email already exists");
        }

        // Encrypt Password
        String encodedpassword = passwordEncoder.encode(registerdto.getPassword());

        RegisterEntity users = new RegisterEntity(
                registerdto.getId(),
                registerdto.getUserName(),
                registerdto.getEmail(),
                encodedpassword,
                registerdto.getPhoneNumber(),
                registerdto.getState()
        );
        RegisterEntity saved = repo.save(users);
        return "Register successfully";


    }
}
