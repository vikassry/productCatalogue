package com.example.productcatalogue.paymentGateway;

public interface PaymentGateway {
    public String getPaymentLink(Long amount, String name, String phoneNumber, String orderId);
}
