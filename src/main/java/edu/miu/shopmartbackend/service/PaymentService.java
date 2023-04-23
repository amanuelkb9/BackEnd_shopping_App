package edu.miu.shopmartbackend.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import edu.miu.shopmartbackend.model.CardPayment;
import edu.miu.shopmartbackend.model.dto.PaymentDto;

public interface PaymentService {
    Customer createCustomer(PaymentDto PaymentDto) throws StripeException;
    PaymentIntent handlePayment(PaymentDto paymentDto) throws StripeException;


    PaymentIntent sellerPayment(CardPayment cardPayment) throws StripeException;
}
