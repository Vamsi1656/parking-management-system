package com.example.payment_service.Payment_Service.repository;

import com.example.payment_service.Payment_Service.model.PaymentEntity;
import com.example.payment_service.Payment_Service.model.PaymentStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class PaymentRepositoryTest {

    //Given - When - Then

    @Autowired
    private PaymentRepository repo;
    PaymentEntity payment;

    @BeforeEach
    void setUp() {
        payment =new PaymentEntity("12345534sa", "soass55s", PaymentStatus.FAILED,500.0,"12ad");
        repo.save(payment);
    }

    @AfterEach
    void tearDown() {
        payment=null;
        repo.deleteAll();
    }

    //Success orderBy
    @Test
    void existsByOrderIdTest_True(){
        payment =new PaymentEntity();
        payment.setOrderId("order_SXOa5hHNJPWWqg");
        payment.setPaymentId("pay_123");
        payment.setAmount(400.0);
        repo.save(payment);
        Boolean result=repo.existsByOrderId("order_SXOa5hHNJPWWqg");
        assertThat(result);
    }
    //Failed orderBy
    @Test
    void existsByOrderIdTest_False(){

        Boolean result=repo.existsByOrderId("order_123");
        assertThat(result);
    }

    //PaymentId False
    @Test
    void existsByPaymentIdTest_False(){
        Boolean result=repo.existsByPaymentId("pay123");
        assertThat(result);
    }
    //PaymentId True
    @Test
    void existsByPaymentIdTest_True(){
        payment =new PaymentEntity();
        payment.setOrderId("order_SXOa5hHNJPWWqg");
        payment.setPaymentId("pay_123");
        payment.setAmount(400.0);
        repo.save(payment);
        Boolean result = repo.existsByPaymentId("pay_123");
        assertThat(result);
    }
}
