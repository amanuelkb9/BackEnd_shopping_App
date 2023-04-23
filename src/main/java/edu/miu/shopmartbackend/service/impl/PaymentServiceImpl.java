package edu.miu.shopmartbackend.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentConfirmParams;
import edu.miu.shopmartbackend.model.Payment;
import edu.miu.shopmartbackend.model.dto.PaymentDto;
import edu.miu.shopmartbackend.repo.PaymentRepository;
import edu.miu.shopmartbackend.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;





@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Value("${stripe.apiKey}")
    private String secretKey;



    @Override
    public Customer createCustomer(PaymentDto paymentDto) throws StripeException {
        Stripe.apiKey = secretKey;

        // Create a new Customer object with the customer's name and email
        CustomerCreateParams customerParams = new CustomerCreateParams.Builder()
                .setName(paymentDto.getName())
                .setEmail(paymentDto.getEmail())
                .setPaymentMethod(paymentDto.getPaymentMethodId())
                .build();
        Customer customer = Customer.create(customerParams);
        customer.setSources(new PaymentSourceCollection());
        return customer;
    }




    public PaymentIntent handlePayment(PaymentDto paymentDto) throws StripeException {
        Stripe.apiKey = secretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", (long) (paymentDto.getAmount() * 100));
        params.put("currency", paymentDto.getCurrency());
        params.put("CustomerName", paymentDto.getName());
        params.put("customerId", paymentDto.getBuyer_id());
        params.put("order", paymentDto.getOrder_Id());
        params.put("description", "Order Payment");

        // Set up payment method
        Map<String, Object> paymentMethodParams = new HashMap<>();
        switch (paymentDto.getType()) {
            case "card":
                paymentMethodParams.put("type", "card");
                paymentMethodParams.put("card[number]", paymentDto.getCardNumber());
                paymentMethodParams.put("card[exp_month]", paymentDto.getExp_month());
                paymentMethodParams.put("card[exp_year]", paymentDto.getExp_year());
                paymentMethodParams.put("card[cvc]", paymentDto.getCvc());
                params.put("payment_method", PaymentMethod.create(paymentMethodParams).getId());
                break;
//            case "sepa_debit":
//                paymentMethodParams.put("type", "sepa_debit");
//                paymentMethodParams.put("sepa_debit[iban]", paymentDto.getIban());
//                params.put("payment_method_types[]", "sepa_debit");
//                params.put("payment_method_data[sepa_debit]", paymentMethodParams);
//                break;
            default:
                throw new IllegalArgumentException("Invalid payment method type");
        }

        // Create payment intent
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        System.out.println("PaymentIntent created: " + paymentIntent);

        // Confirm payment intent
        PaymentIntentConfirmParams confirmParams = new PaymentIntentConfirmParams.Builder()
                .setPaymentMethod(paymentIntent.getPaymentMethod())
                .build();
        try {
            paymentIntent.confirm(confirmParams);
            paymentIntent.setStatus("succeeded");
            System.out.println("PaymentIntent confirmed: " + paymentIntent);
            paymentRepository.save(modelMapper.map(paymentDto, Payment.class));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return paymentIntent;
    }

    @Override
    public Customer getCustomer(String customerId) throws StripeException {
        Stripe.apiKey = secretKey;
        Customer customer = Customer.retrieve(customerId);
        return customer;
    }
}
