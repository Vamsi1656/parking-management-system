package com.example.user_service.User_Service.controller;

import com.example.user_service.User_Service.dto.LoginDTO;
import com.example.user_service.User_Service.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/loginUser")
    public String login(@Valid@RequestBody LoginDTO logindto){
        return service.login(logindto);
    }
}
