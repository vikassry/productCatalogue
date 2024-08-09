package com.example.productcatalogue.paymentGateway;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import com.razorpay.RazorpayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RazorpayPaymentGateway implements PaymentGateway {
    @Autowired
    private RazorpayClient razorpayClient;

    @Override
    public String getPaymentLink(Long amount, String name, String phoneNumber, String orderId) {
        long time = new Date().getTime();
        JSONObject paymentLinkRequest = new JSONObject();

        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",true);
        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by", time);
        paymentLinkRequest.put("reference_id",String.valueOf(time));
        paymentLinkRequest.put("description","Payment desc");

        JSONObject customer = new JSONObject();
        customer.put("name",name);
        customer.put("contact",phoneNumber);
        customer.put("email","gaurav.kumar@example.com");
        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);

        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
        paymentLinkRequest.put("callback_method","get");

        try {
            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
            return payment.get("short_url");
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
    }
}
