package edu.miu.shopmartbackend.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import edu.miu.shopmartbackend.model.PaymentData;
import edu.miu.shopmartbackend.model.Payment;
import edu.miu.shopmartbackend.model.dto.CustomerData;

public interface PaymentService {
    Customer createCustomer(CustomerData customerData) throws StripeException;
    PaymentIntent handlePayment(PaymentData paymentData) throws StripeException;
    Payment createPayment(Double amount, String currency, String token) throws StripeException;

    PaymentIntent createPaymentIntent(PaymentData paymentData) throws StripeException;
}
