package com.example.state_service.State_Service.controller;

import com.example.state_service.State_Service.config.JwtUtil;
import com.example.state_service.State_Service.dto.AuthRequest;
import com.example.state_service.State_Service.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        //Hardcoded users (for now)

        if ("admin".equals(request.getUsername()) &&
                "admin123".equals(request.getPassword())) {

            String token = jwtUtil.generateToken(request.getUsername(), "ROLE_ADMIN");
            return ResponseEntity.ok(new AuthResponse(token));
        }

        if ("user".equals(request.getUsername()) &&
                "user123".equals(request.getPassword())) {

            String token = jwtUtil.generateToken(request.getUsername(), "ROLE_USER");
            return ResponseEntity.ok(new AuthResponse(token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password");
    }
}