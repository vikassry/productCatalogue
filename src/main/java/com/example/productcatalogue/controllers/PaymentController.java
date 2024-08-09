package com.example.productcatalogue.controllers;

import com.example.productcatalogue.dtos.InitiatePaymentDto;
import com.example.productcatalogue.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("")
    public String initiatePayment(@RequestBody InitiatePaymentDto request){
        return paymentService.paymentLink(request.getAmount(), request.getName(), request.getPhoneNumber(), request.getOrderId());
    }
}
