package edu.miu.shopmartbackend.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.net.RequestOptions;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import edu.miu.shopmartbackend.model.Payment;
import edu.miu.shopmartbackend.repo.PaymentRepository;
import edu.miu.shopmartbackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@Service
//public class PaymentServiceImpl implements PaymentService {
//    @Autowired
//    private PaymentRepository paymentRepository;
//
//    @Value("${stripe.apiKey}")
//    private String secretKey;
//
//    public Payment createPayment(Double amount, String currency, String token) throws StripeException {
//        Stripe.apiKey = secretKey;
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("amount", amount.intValue());
//        params.put("currency", currency);
//        params.put("payment_method_types", new String[] {"card"});
//        params.put("payment_method", token);
//        params.put("confirm", true);
//
//        PaymentIntent paymentIntent = PaymentIntent.create(params);
//
//        Payment payment = new Payment();
//       // payment.setStripeChargeId(paymentIntent.getId());
//        payment.setAmount(amount);
//        payment.setCurrency(currency);
//       // payment.setCreatedDate(new Date());
//
//        return paymentRepository.save(payment);
//    }
//}

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Value("${stripe.apiKey}")
    private String secretKey;

    public Payment createPayment(Double amount, String currency, String token) throws StripeException {
        Stripe.apiKey = secretKey;

        // create a payment intent with the provided amount and currency
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(Math.round(amount * 100))
                .setCurrency(currency)
                //.addPaymentMethodType(PaymentMethodType.CARD)
                .build();

        // confirm the payment intent with the provided payment method token
        PaymentIntent paymentIntent = PaymentIntent.create(params,
                RequestOptions.builder().setApiKey(secretKey).build());
        PaymentIntentConfirmParams confirmParams = PaymentIntentConfirmParams.builder()
                .setPaymentMethod(token)
                .build();
        paymentIntent.confirm(confirmParams);

        // create a payment object with the Stripe payment intent ID
        Payment payment = new Payment();
        payment.setId(paymentIntent.getId());

        return payment;
    }
}
