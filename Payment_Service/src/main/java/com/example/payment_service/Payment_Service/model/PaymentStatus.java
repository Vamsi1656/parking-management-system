package com.example.payment_service.Payment_Service.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentStatus {
    SUCCESS,
    FAILED,
    PENDING;

    @JsonCreator
    public static PaymentStatus from(String value) {
        return PaymentStatus.valueOf(value.toUpperCase());
    }
}