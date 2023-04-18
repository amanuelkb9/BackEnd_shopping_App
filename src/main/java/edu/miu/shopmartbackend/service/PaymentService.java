package edu.miu.shopmartbackend.service;

import com.stripe.exception.StripeException;
import edu.miu.shopmartbackend.model.Payment;

public interface PaymentService {
    Payment createPayment(Double amount, String currency, String token) throws StripeException;
}
