package com.example.payment_service.Payment_Service.controller;


import com.example.payment_service.Payment_Service.model.PaymentEntity;
import com.example.payment_service.Payment_Service.model.PaymentRequest;
import com.example.payment_service.Payment_Service.model.PaymentStatus;
import com.example.payment_service.Payment_Service.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PaymentService service;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }
    }

    // Test 1: createOrder API
    @Test
    void testCreateOrder() throws Exception {

        Mockito.when(service.createOrder(100.0))
                .thenReturn("Order Created");

        mockMvc.perform(post("/payment/create-order")
                        .param("amount", "100.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order Created"));
    }

    //Test 2: savePayment API
    @Test
    void testSavePayment() throws Exception {

        PaymentRequest request = new PaymentRequest(
                "order123",
                "pay123",
                PaymentStatus.SUCCESS,
                100.0
        );

        PaymentEntity entity = new PaymentEntity();
        entity.setOrderId("order123");
        entity.setPaymentId("pay123");
        entity.setStatus(PaymentStatus.SUCCESS);
        entity.setAmount(100.0);

        Mockito.when(service.savePayment(Mockito.any()))
                .thenReturn(entity);

        mockMvc.perform(post("/payment/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value("order123"))
                .andExpect(jsonPath("$.paymentId").value("pay123"))
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.amount").value(100.0));
    }
}
