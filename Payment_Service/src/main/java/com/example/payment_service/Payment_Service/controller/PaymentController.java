package com.example.payment_service.Payment_Service.controller;

import com.example.payment_service.Payment_Service.model.PaymentRequest;
import com.example.payment_service.Payment_Service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestParam double amount) throws Exception{
        return ResponseEntity.ok(service.createOrder(amount));
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePayment(@RequestBody PaymentRequest request){
        return ResponseEntity.ok(service.savePayment(request));
    }
}
