package com.example.productcatalogue.paymentGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayStrategy {

    @Autowired
    private RazorpayPaymentGateway razorpayPaymentGateway;

    public PaymentGateway getPaymentGateway() {
        return razorpayPaymentGateway;
    }
}
