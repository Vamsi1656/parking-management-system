package com.example.payment_service.Payment_Service.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    @NotBlank(message = "OrderId should not be blank")
    private String orderId;

//    @NotBlank(message = "PaymentId should not be blank")
    private String paymentId;

    @Pattern(regexp = "SUCCESS|FAILED|PENDING", message = "Invalid status")
    private PaymentStatus status;

    @DecimalMin(value = "1.0",message = "Amount should be grater than 0.")
    private double amount;
}
