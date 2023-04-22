package edu.miu.shopmartbackend.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.net.RequestOptions;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentIntentUpdateParams;
import edu.miu.shopmartbackend.model.Payment;
import edu.miu.shopmartbackend.model.dto.CustomerData;
import edu.miu.shopmartbackend.model.dto.PaymentRequestDto;
import edu.miu.shopmartbackend.model.dto.PaymentResponseDto;
import edu.miu.shopmartbackend.service.OrderService;
import edu.miu.shopmartbackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${stripe.apiKey}")
    private String secretKey;

    @Autowired
    private OrderService orderService;

//    @PostMapping("")
//    public ResponseEntity<?> handlePayment(@RequestBody Map<String, Object> payload) {
//        try {
//            Double amount = Double.parseDouble(payload.get("amount").toString());
//            String currency = payload.get("currency").toString();
//            String token = payload.get("token").toString();
//
//            Payment payment = paymentService.createPayment(amount, currency, token);
//
//            return ResponseEntity.ok(Map.of("paymentId", payment.getId()));
//        } catch (StripeException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

//    @PostMapping()
//    public ResponseEntity<?> handlePayment(@RequestBody Map<String, Object> payload) {
//        try {
//            if (!payload.containsKey("amount") || !payload.containsKey("currency") || !payload.containsKey("token")) {
//                return ResponseEntity.badRequest().build();
//            }
//
//            Optional<Double> amountOpt = Optional.ofNullable((Double) payload.get("amount"));
//            Optional<String> currencyOpt = Optional.ofNullable((String) payload.get("currency"));
//            Optional<String> tokenOpt = Optional.ofNullable((String) payload.get("token"));
//
//            if (amountOpt.isEmpty() || currencyOpt.isEmpty() || tokenOpt.isEmpty()) {
//                return ResponseEntity.badRequest().build();
//            }
//
//            Double amount = amountOpt.get();
//            String currency = currencyOpt.get();
//            String token = tokenOpt.get();
//
//            Payment payment = paymentService.createPayment(amount, currency, token);
//
//            return ResponseEntity.ok(Map.of("paymentId", payment.getId()));
//        } catch (StripeException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error_message", e.getMessage()));
//        }
//    }
//}


//    @PostMapping("/charge")
//    public String chargePerson(@RequestBody ChargeRequest chargeRequest) throws StripeException {
//        // Set the Stripe API key
//        Stripe.apiKey = "sk_test_51MyLHKEXvdN6BAd1OBlAu42MOQM2XOIAu4F0UD1T4GxadihZP39lPYmdwNv8ONLdKIL6tGCbvSbJHtmwwc55GvUr00ZtTRNULt";
//
//        // Create the charge parameters
//        Map<String, Object> chargeParams = new HashMap<>();
//        chargeParams.put("amount", chargeRequest.getAmount()); // charge amount in cents
//        chargeParams.put("currency", chargeRequest.getCurrency()); // charge currency
//        chargeParams.put("source", chargeRequest.getSource()); // Stripe token representing the payment method
//        chargeParams.put("description", chargeRequest.getDescription()); // charge description
//
//        // Charge the person
//        Charge charge = Charge.create(chargeParams);
//
//        // Return the charge ID to the client
//        return "Charge successful: " + charge.getId();
//    }
//
//    // Define the request payload for the charge API
//    public static class ChargeRequest {
//        private int amount;
//        private String currency;
//        private String source;
//        private String description;
//
//        public int getAmount() {
//            return amount;
//        }
//
//        public void setAmount(int amount) {
//            this.amount = amount;
//        }
//
//        public String getCurrency() {
//            return currency;
//        }
//
//        public void setCurrency(String currency) {
//            this.currency = currency;
//        }
//
//        public String getSource() {
//            return source;
//        }
//
//        public void setSource(String source) {
//            this.source = source;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//    }

//last
//    @PostMapping
//    public ResponseEntity<PaymentResponseDto> handlePayment(@RequestBody PaymentRequestDto paymentRequestDto) {
//        try {
//            Double amount = paymentRequestDto.getAmount();
//            String currency = paymentRequestDto.getCurrency();
//            String token = paymentRequestDto.getToken();
//
//            Payment payment = paymentService.createPayment(amount, currency, token);
//
//            PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
//            paymentResponseDto.setPaymentId(payment.getId());
//
//            return ResponseEntity.ok(paymentResponseDto);
//        } catch (StripeException e) {
//            System.out.println(e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }


    // handles payment... works

//    @PostMapping("")
//    public void handlePayment(@RequestBody PaymentData paymentData) throws StripeException{ //@PathVariable Double amount, @PathVariable String currency) throws StripeException {
//        // Set your Stripe API key
//        Stripe.apiKey = secretKey;
//
//        Map<String, Object> paymentMethodParams = new HashMap<>();
//        paymentMethodParams.put("type", paymentData.getType());
//        paymentMethodParams.put("card[number]", paymentData.getCardNumber());
//        paymentMethodParams.put("card[exp_month]", paymentData.getExp_month());
//        paymentMethodParams.put("card[exp_year]", paymentData.getExp_year());
//        paymentMethodParams.put("card[cvc]", paymentData.getCvc());
//
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
////display confirmation page and proceed to charge
//        // Confirm the PaymentIntent to charge the customer
//        PaymentIntentConfirmParams confirmParams = new PaymentIntentConfirmParams.Builder()
//                .setPaymentMethod(paymentMethod.getId())
//                .build();
//        paymentIntent.confirm(confirmParams);
//        System.out.println("=======================================");
//        System.out.println(paymentIntent);
//        //call the createOrder method
//        //orderService.placeOrder(paymentData.getBuyer_id());
//        System.out.println(confirmParams.getReceiptEmail());
//    }





    @PostMapping("")
    public String handlePayment(@RequestBody PaymentData paymentData) throws StripeException {
//        // Set your Stripe API key
//        Stripe.apiKey = secretKey;
//
//        Map<String, Object> paymentMethodParams = new HashMap<>();
//        paymentMethodParams.put("type", paymentData.getType());
//        paymentMethodParams.put("card[number]", paymentData.getCardNumber());
//        paymentMethodParams.put("card[exp_month]", paymentData.getExp_month());
//        paymentMethodParams.put("card[exp_year]", paymentData.getExp_year());
//        paymentMethodParams.put("card[cvc]", paymentData.getCvc());
//
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
//       // PaymentIntent updatedPaymentIntent = paymentIntent.update();
//        System.out.println("PaymentIntent status: " + paymentIntent.getStatus());
//
//        // Call the createOrder method
//        // orderService.placeOrder(paymentData.getBuyer_id());
//        System.out.println(confirmParams.getReceiptEmail());
//        return paymentIntent;
        return paymentService.handlePayment(paymentData);
    }


    @PostMapping("/createCustomer")
    public Customer createCustomer(@RequestBody CustomerData customerData) throws StripeException {
        return paymentService.createCustomer(customerData);
//        Stripe.apiKey = secretKey;
//
//        // Create a new Customer object with the customer's name and email
//        CustomerCreateParams customerParams = new CustomerCreateParams.Builder()
//                .setName(name)
//                .setEmail(email)
//                .build();
//        Customer customer = Customer.create(customerParams);
//
//        // Add the customer's card to their account
//        Map<String, Object> cardParams = new HashMap<>();
//        cardParams.put("number", cardNumber);
//        cardParams.put("exp_month", expMonth);
//        cardParams.put("exp_year", expYear);
//        cardParams.put("cvc", cvc);
//        customer.setSources(new PaymentSourceCollection());
//        System.out.println(customer);
//        Card card = (Card) customer.getSources().create(Map.of("source", cardParams));
//        System.out.println(customer);
//        return customer;
    }











//adds customers...works

//    @PostMapping("/createCustomer")
//    public PaymentData createCustomer(@RequestBody PaymentData customerData) throws StripeException {
//        Stripe.apiKey = secretKey;
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", customerData.getName());
//        params.put("email", customerData.getEmail());
//        Customer customer =Customer.create(params);
//        customerData.setCustomerId(customer.getId());
//        return customerData;
//
//    }

    @PostMapping("/addCard/{customerId}")
    public ResponseEntity<?> addCard(@PathVariable String customerId){

        Stripe.apiKey = secretKey;
        try {

            Customer customer = Customer.retrieve(customerId);//"cus_Nk8lZp5YlJbFy0");
            Map<String, Object> cardParam = new HashMap<>();
            cardParam.put("number", "4242424242424242");
            cardParam.put("exp_month", "12");
            cardParam.put("exp_year", "2030");
            cardParam.put("cvc", "776");
            Map<String, Object> tokenParam = new HashMap<>();
            tokenParam.put("card", cardParam);

            Token token = Token.create(cardParam);
            Map<String, Object> source = new HashMap<>();
            source.put("source", token.getId());

            customer.getSources().create(source);
            System.out.println(customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);

        } catch(StripeException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("Invalid card", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/charge")
    public ResponseEntity<?> chargeCard(@RequestParam("amount") int amount, @RequestParam("currency") String currency, @RequestParam("stripeToken") String stripeToken) {
        try {
            Stripe.apiKey = secretKey;
            Map<String, Object> params = new HashMap<>();
            params.put("amount", amount);
            params.put("currency", currency);
            params.put("source", stripeToken);
            Charge charge = Charge.create(params);

            return new ResponseEntity<Charge>(charge, HttpStatus.OK);
        } catch (StripeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("exception arose" + e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getCustomer/{customerId}")
    public Customer getCustomer(@PathVariable String customerId) throws StripeException{
        Stripe.apiKey = secretKey;
        Customer customer = Customer.retrieve(customerId);
        if(customer == null){
            return null;
        }
        return customer;
    }

   // @PostMapping("/charge")
//    public void charge() throws StripeException{
//        Stripe.apiKey = "sk_test_51MyLHKEXvdN6BAd1OBlAu42MOQM2XOIAu4F0UD1T4GxadihZP39lPYmdwNv8ONLdKIL6tGCbvSbJHtmwwc55GvUr00ZtTRNULt";
//
//        Map<String, Object> chargeParams = new HashMap<>();
//        chargeParams.put("amount", 2000);
//        chargeParams.put("currency", "usd");
//        chargeParams.put("description", "My First Test Charge (created for API docs at https://www.stripe.com/docs/api)");
//        chargeParams.put("source", "tok_visa");
//// ^ obtained with Stripe.js
//
////        RequestOptions options = RequestOptions
////                .builder()
////                .setIdempotencyKey("K71lzA34ThaI2moo")
////                .build();
//        Charge charge = Charge.create(chargeParams);
//    }

//    @PostMapping()
//    public ResponseEntity<?> handlePayment(@RequestBody PaymentRequestDto paymentRequestDto) {
//        try {
//            if (!paymentRequestDto.getAmount().equals("amount") ||
//                    !paymentRequestDto.getCurrency().equals("currency") || !paymentRequestDto.getToken().equals("token")) {
//                return ResponseEntity.badRequest().build();
//            }
//
//            Optional<Double> amountOpt = Optional.ofNullable((Double) paymentRequestDto.getAmount());
//            Optional<String> currencyOpt = Optional.ofNullable((String) paymentRequestDto.getCurrency());
//            Optional<String> tokenOpt = Optional.ofNullable((String) paymentRequestDto.getToken());
//
//            if (amountOpt.isEmpty() || currencyOpt.isEmpty() || tokenOpt.isEmpty()) {
//                return ResponseEntity.badRequest().build();
//            }
//
//            Double amount = amountOpt.get();
//            String currency = currencyOpt.get();
//            String token = tokenOpt.get();
//
//            Payment payment = paymentService.createPayment(amount, currency, token);
//
//            return ResponseEntity.ok(Map.of("paymentId", payment.getId()));
//        } catch (StripeException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error_message", e.getMessage()));
//        }
//    }
}