package com.example.payment_service.Payment_Service.repository;

import com.example.payment_service.Payment_Service.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
}
