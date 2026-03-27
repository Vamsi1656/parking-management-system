package com.example.payment_service.Payment_Service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String orderId;
    private String paymentId;
    private String status;
    private double amount;
}
