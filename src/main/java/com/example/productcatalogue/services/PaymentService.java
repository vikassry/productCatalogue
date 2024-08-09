package com.example.productcatalogue.services;

import com.example.productcatalogue.paymentGateway.PaymentGatewayStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentGatewayStrategy paymentGatewayStrategy;

    public String paymentLink(Long amount, String name, String phoneNumber, String orderId){
        return paymentGatewayStrategy.getPaymentGateway().getPaymentLink(amount, name, phoneNumber, orderId);
    }
}
