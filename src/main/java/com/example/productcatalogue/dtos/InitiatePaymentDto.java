package com.example.productcatalogue.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentDto {
    private String orderId;
    private String name;
    private String phoneNumber;
    private Long amount;
}
