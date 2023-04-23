package edu.miu.shopmartbackend.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.*;
import edu.miu.shopmartbackend.model.dto.PaymentDto;
import edu.miu.shopmartbackend.service.OrderService;
import edu.miu.shopmartbackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${stripe.apiKey}")
    private String secretKey;

    @Autowired
    private OrderService orderService;


    @PostMapping("")
    public String handlePayment(@RequestBody PaymentDto paymentDto) throws StripeException {
        return paymentService.handlePayment(paymentDto).getStatus();
    }


    @PostMapping("/createCustomer")
    public Customer createCustomer(@RequestBody PaymentDto paymentDto) throws StripeException {
        return paymentService.createCustomer(paymentDto);

    }


    @GetMapping("/getCustomer/{customerId}")
    public Customer getCustomer(@PathVariable String customerId) throws StripeException{

        return paymentService.getCustomer(customerId);
    }


}