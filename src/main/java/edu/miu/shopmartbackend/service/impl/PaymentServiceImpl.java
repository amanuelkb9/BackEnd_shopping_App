package edu.miu.shopmartbackend.service.impl;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.net.RequestOptions;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import edu.miu.shopmartbackend.model.PaymentData;
import edu.miu.shopmartbackend.model.Payment;
import edu.miu.shopmartbackend.model.dto.CustomerData;
import edu.miu.shopmartbackend.repo.OrderRepo;
import edu.miu.shopmartbackend.repo.PaymentRepository;
import edu.miu.shopmartbackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;





@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Value("${stripe.apiKey}")
    private String secretKey;

    @Autowired
    private OrderRepo orderRepository;

//    public Payment createPayment(Double amount, String currency, String token) throws StripeException {
//        Stripe.apiKey = secretKey;
//
//        // create a payment intent with the provided amount and currency
//        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
//                .setAmount(Math.round(amount * 100))
//                .setCurrency(currency)
//                //.addPaymentMethodType(PaymentMethodType.CARD)
//                .build();
//
//        // confirm the payment intent with the provided payment method token
//        PaymentIntent paymentIntent = PaymentIntent.create(params,
//                RequestOptions.builder().setApiKey(secretKey).build());
//        PaymentIntentConfirmParams confirmParams = PaymentIntentConfirmParams.builder()
//                .setPaymentMethod(token)
//                .build();
//        paymentIntent.confirm(confirmParams);
//
//        // create a payment object with the Stripe payment intent ID
//        Payment payment = new Payment();
//        payment.setId(paymentIntent.getId());
//
//        return payment;
//    }

    @Override
    public Customer createCustomer(CustomerData customerData) throws StripeException {
        Stripe.apiKey = secretKey;

        // Create a new Customer object with the customer's name and email
        CustomerCreateParams customerParams = new CustomerCreateParams.Builder()
                .setName(customerData.getName())
                .setEmail(customerData.getEmail())
                .build();
        Customer customer = Customer.create(customerParams);

        // Add the customer's card to their account
        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", customerData.getCardNumber());
        cardParams.put("exp_month", customerData.getExp_month());
        cardParams.put("exp_year", customerData.getExp_year());
        cardParams.put("cvc", customerData.getCvc());
        customer.setSources(new PaymentSourceCollection());
        System.out.println(customer);
        Card card = (Card) customer.getSources().create(Map.of("source", cardParams));
        System.out.println(customer);
        return customer;
    }

//    @Override
//    public PaymentIntent handlePayment(PaymentData paymentData) throws StripeException {
//        Stripe.apiKey = secretKey;
//
//        // You should receive the payment method ID from the client-side
//        PaymentResponseDto paymentResponseDto=new PaymentResponseDto();
//        String paymentMethodId = paymentResponseDto.getPaymentId();
//
//
//        // Create a PaymentIntent with the PaymentMethod ID and the amount to charge
//        PaymentIntentCreateParams params = new PaymentIntentCreateParams.Builder()
//                .setPaymentMethod(paymentMethodId)
//                .setAmount((long) (paymentData.getAmount() * 100))
//                .setCurrency(paymentData.getCurrency())
//                .setConfirm(true) // Automatically confirm the PaymentIntent
//                .build();
//
//        // Create and confirm the PaymentIntent
//        PaymentIntent paymentIntent = PaymentIntent.create(params);
//        System.out.println(paymentIntent);
//
//        // Check the PaymentIntent status
//        System.out.println("PaymentIntent status: " + paymentIntent.getStatus());
//
//        return paymentIntent;
//    }
//}





    //    --------------------------------------------------works-------------------



    private static Gson gson = new Gson();

    static class CreatePayment {
        @SerializedName("items")
        Object[] items;

        public Object[] getItems() {
            return items;
        }
    }

    static class CreatePaymentResponse {
        private String clientSecret;
        public CreatePaymentResponse(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }

    public Payment createPayment(Double amount, String currency, String token) throws StripeException {
        Stripe.apiKey = secretKey;

        // create a payment intent with the provided amount and currency
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(Math.round(amount * 100))
                .setCurrency(currency)
                .addPaymentMethodType(String.valueOf(SessionCreateParams.PaymentMethodType.CARD))
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

    @Override
    public PaymentIntent createPaymentIntent(PaymentData paymentData) throws StripeException{
        Stripe.apiKey = secretKey;
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .addPaymentMethodType(paymentData.getType())
                .setAmount((long) (paymentData.getAmount() * 100))
                .setCurrency(paymentData.getCurrency())
                .setPaymentMethod(paymentData.getPaymentMethodId())
                .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.MANUAL)
                .setConfirm(true)
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(createParams);

        return paymentIntent;
    }


    public PaymentIntent handlePayment(PaymentData paymentData) throws StripeException {
        Stripe.apiKey = secretKey;

        Map<String, Object> paymentParams = new HashMap<>();
        paymentParams.put("amount", paymentData.getAmount());
        paymentParams.put("currency", paymentData.getCurrency());
        paymentParams.put("payment_method_types", Arrays.asList("card"));

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .addPaymentMethodType(paymentData.getType())
                .setAmount((long) (paymentData.getAmount() * 100))
                .setCurrency(paymentData.getCurrency())
                .setPaymentMethod(paymentData.getPaymentMethodId())
                .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.MANUAL)
                .setConfirm(true)
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(createParams);
        paymentIntent.setStatus("succeeded");

        return paymentIntent;
    }

//    --------------------------------------------------works-------------------


//    @Override
//    public PaymentIntent handlePayment(PaymentData paymentData) throws StripeException {
//        Stripe.apiKey = secretKey;
//
//        Map<String, Object> paymentMethodParams = new HashMap<>();
//        paymentMethodParams.put("type", paymentData.getType());
//        paymentMethodParams.put("card[number]", paymentData.getCardNumber());
//        paymentMethodParams.put("card[exp_month]", paymentData.getExp_month());
//        paymentMethodParams.put("card[exp_year]", paymentData.getExp_year());
//        paymentMethodParams.put("card[cvc]", paymentData.getCvc());
//        System.out.println("========================================");
//        PaymentMethod paymentMethod = PaymentMethod.create(paymentMethodParams);
//
//        // Create a PaymentIntent with the PaymentMethod ID and the amount to charge
//        PaymentIntentCreateParams params = new PaymentIntentCreateParams.Builder()
//                .setPaymentMethod(paymentMethod.getId())
//                .setAmount((long) (paymentData.getAmount() * 100))
//                .setCurrency(paymentData.getCurrency())
//                .build();
//        PaymentIntent paymentIntent = PaymentIntent.create(params);
//        System.out.println(paymentIntent);
//
//        // Confirm the PaymentIntent to charge the customer
//        PaymentIntentConfirmParams confirmParams = new PaymentIntentConfirmParams.Builder()
//                .setPaymentMethod(paymentMethod.getId())
//                .build();
//        paymentIntent.confirm(confirmParams);
//
//        // Update the PaymentIntent status to succeeded
//        paymentIntent.setStatus("succeeded");
//        // PaymentIntent updatedPaymentIntent = paymentIntent.update();
//        System.out.println("PaymentIntent status: " + paymentIntent.getStatus());
//
//
//        return paymentIntent;
//    }
}
