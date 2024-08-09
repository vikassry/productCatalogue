package com.example.productcatalogue.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {
    @Value("${razorpay.key}")
    private String apiKey;

    @Value("${razorpay.secret}")
    private String apiSecret;

    @Bean
    public RazorpayClient getRazorpayClient() throws RazorpayException {
        return  new RazorpayClient(apiKey, apiSecret);
    }
}
