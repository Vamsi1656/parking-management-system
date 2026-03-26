package com.example.user_service.User_Service.service.impl;

import com.example.user_service.User_Service.dto.LoginDTO;
import com.example.user_service.User_Service.entity.LoginEntity;
import com.example.user_service.User_Service.repository.LoginRepository;
import com.example.user_service.User_Service.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceimpl implements LoginService {

    @Autowired
    private LoginRepository repo;

    public String login(LoginDTO logindto){
        LoginEntity user = repo.findByEmail(logindto.getEmail());

        String dummyemail ="Kalaivananeswaran564@gmail.com";
        String dummypassword = "Bangalore@1010";

        if(!logindto.getEmail().equals(dummyemail)){
            throw new RuntimeException("Email Incorrect");
        }
        if(!logindto.getPassword().equals(dummypassword)){
            throw new RuntimeException("Password Incorrect");
        }



        return "Login Successfully";
    }
}
