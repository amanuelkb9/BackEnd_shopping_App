package edu.miu.shopmartbackend.service;

import edu.miu.shopmartbackend.model.Payment;
import edu.miu.shopmartbackend.model.dto.OrderDto;
import edu.miu.shopmartbackend.model.dto.PaymentDto;

public interface EmailSenderService {
    public void sendEmail(String to, String subject, String message);
    public void sendPaymentConfirmationEmail(String recipientEmail, PaymentDto paymentDto);
    public void sendPaymentFailureEmail(String recipientEmail, PaymentDto paymentDto);
    public void sendOrderConfirmationEmail(String recipientEmail, PaymentDto paymentDto);
}
