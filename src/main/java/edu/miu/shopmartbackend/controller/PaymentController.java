package edu.miu.shopmartbackend.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import edu.miu.shopmartbackend.model.Payment;
import edu.miu.shopmartbackend.model.dto.PaymentRequestDto;
import edu.miu.shopmartbackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

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

    @PostMapping()
    public ResponseEntity<?> handlePayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        try {
            if (!paymentRequestDto.getAmount().equals("amount") ||
                    !paymentRequestDto.getCurrency().equals("currency") || !paymentRequestDto.getToken().equals("token")) {
                return ResponseEntity.badRequest().build();
            }

            Optional<Double> amountOpt = Optional.ofNullable((Double) paymentRequestDto.getAmount());
            Optional<String> currencyOpt = Optional.ofNullable((String) paymentRequestDto.getCurrency());
            Optional<String> tokenOpt = Optional.ofNullable((String) paymentRequestDto.getToken());

            if (amountOpt.isEmpty() || currencyOpt.isEmpty() || tokenOpt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Double amount = amountOpt.get();
            String currency = currencyOpt.get();
            String token = tokenOpt.get();

            Payment payment = paymentService.createPayment(amount, currency, token);

            return ResponseEntity.ok(Map.of("paymentId", payment.getId()));
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error_message", e.getMessage()));
        }
    }
}