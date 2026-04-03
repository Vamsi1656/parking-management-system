package com.example.payment_service.Payment_Service.service;

import com.example.payment_service.Payment_Service.model.PaymentEntity;
import com.example.payment_service.Payment_Service.model.PaymentRequest;
import com.example.payment_service.Payment_Service.model.PaymentStatus;
import com.example.payment_service.Payment_Service.repository.PaymentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {


    @Mock
    private PaymentRepository repo;
    @InjectMocks
    private PaymentService service;
    AutoCloseable autoCloseable;
    PaymentEntity payment;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        payment = new PaymentEntity();
        payment.setOrderId("order_SXOa5hHNJPWWqg");
        payment.setPaymentId("pay_123");
        payment.setAmount(400.0);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }


    @Test
    void savePayment_success() {

        PaymentRequest request = new PaymentRequest();
        request.setOrderId("order_123");
        request.setPaymentId("pay_123");
        request.setStatus(PaymentStatus.SUCCESS);
        request.setAmount(500.0);

        when(repo.existsByPaymentId("pay_123")).thenReturn(false);
        when(repo.existsByOrderId("order_123")).thenReturn(false);

        PaymentEntity savedEntity = new PaymentEntity();
        savedEntity.setOrderId("order_123");

        when(repo.save(any(PaymentEntity.class))).thenReturn(savedEntity);

        PaymentEntity result = service.savePayment(request);

        assertThat(result).isNotNull();
        assertThat(result.getOrderId()).isEqualTo("order_123");

        verify(repo).save(any(PaymentEntity.class));
    }

    // Test duplicate paymentId
    @Test
    void savePayment_duplicatePaymentId() {

        PaymentRequest request = new PaymentRequest();
        request.setPaymentId("pay_123");

        when(repo.existsByPaymentId("pay_123")).thenReturn(true);

        assertThatThrownBy(() -> service.savePayment(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Payment already");

        verify(repo, never()).save(any());
    }

    // Test duplicate orderId
    @Test
    void savePayment_duplicateOrderId() {

        PaymentRequest request = new PaymentRequest();
        request.setPaymentId("pay_123");
        request.setOrderId("order_123");

        when(repo.existsByPaymentId("pay_123")).thenReturn(false);
        when(repo.existsByOrderId("order_123")).thenReturn(true);

        assertThatThrownBy(() -> service.savePayment(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Order already");

        verify(repo, never()).save(any());
    }
}