package com.example.payment_service.Payment_Service.service;

import com.example.payment_service.Payment_Service.model.PaymentEntity;
import com.example.payment_service.Payment_Service.model.PaymentRequest;
import com.example.payment_service.Payment_Service.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    @Autowired
    private PaymentRepository repo;

    //Create Order
    public String createOrder(double amount) throws Exception{

        RazorpayClient client = new RazorpayClient(key, secret);

        JSONObject options = new JSONObject();
        options.put("amount",amount*100);
        options.put("currency","INR");
        options.put("receipt","txn_123");

        Order order = client.orders.create(options);

        return order.toString();
    }

    //Save Payment After Success
    public PaymentEntity savePayment(PaymentRequest request){
        PaymentEntity pay = new PaymentEntity();

        pay.setOrderId(request.getOrderId());
        pay.setPaymentId(request.getPaymentId());
        pay.setStatus(request.getStatus());
        pay.setAmount(request.getAmount());

        return repo.save(pay);
    }
}
