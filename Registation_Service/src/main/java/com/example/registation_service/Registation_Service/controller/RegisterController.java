package com.example.registation_service.Registation_Service.controller;

import com.example.registation_service.Registation_Service.dto.RegisterDTO;
import com.example.registation_service.Registation_Service.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping("/saveUser")
    public String registerUser(@Valid @RequestBody RegisterDTO registerdto){
        return service.registerUser(registerdto);
    }
}
