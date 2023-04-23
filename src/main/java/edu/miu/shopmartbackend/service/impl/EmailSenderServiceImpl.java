package edu.miu.shopmartbackend.service.impl;


import edu.miu.shopmartbackend.model.Payment;
import edu.miu.shopmartbackend.model.dto.PaymentDto;
import edu.miu.shopmartbackend.service.EmailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;

    public EmailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("projectm163@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        this.mailSender.send(simpleMailMessage);

    }
    @Override
    public void sendPaymentConfirmationEmail(String recipientEmail, PaymentDto paymentDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientEmail);
        simpleMailMessage.setSubject("Payment Confirmation");
        simpleMailMessage.setText("Dear " + paymentDto.getName() + ",\n\n"
                + "Your payment of $" + paymentDto.getAmount() + " has been received.\n\n"
                + "Thank you for your business.\n\n"
                + "Sincerely,\n"
                + "The Company");
        mailSender.send(simpleMailMessage);
    }


   }



































//
//
//import edu.miu.shopmartbackend.model.Order;
//import edu.miu.shopmartbackend.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//    @Service
//    public class EmailService {
//
//        @Autowired
//        private JavaMailSender mailSender;
//
//        public void sendEmail(String to, String subject, String content) {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(to);
//            message.setSubject(subject);
//            message.setText(content);
//            mailSender.send(message);
//        }
////        public void sendPaymentConfirmationEmail(String recipientEmail, Payment payment) {
////            SimpleMailMessage message = new SimpleMailMessage();
////            message.setTo(recipientEmail);
////            message.setSubject("Payment Confirmation");
////            message.setText("Dear " + payment.getRecipientName() + ",\n\n"
////                    + "Your payment of $" + payment.getAmount() + " has been received.\n\n"
////                    + "Thank you for your business.\n\n"
////                    + "Sincerely,\n"
////                    + "The Company");
////            mailSender.send(message);
////        }
//
//        public void sendOrderConfirmationEmail(String recipientEmail, Order order) {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(recipientEmail);
//            message.setSubject("Order Confirmation");
//            message.setText("Dear " + order.getBuyer().getId() + ",\n\n"
//                    + "Your order of " + order.getId() + " " + order.getTotalOrderPrice()
//                    + " has been received and is being processed.\n\n"
//                    + "Thank you for your business.\n\n"
//                    + "Sincerely,\n"
//                    + "The Company");
//            mailSender.send(message);
//        }
//
////        public void sendInvoiceEmail(String recipientEmail, InvoiceDetails invoiceDetails) {
////            SimpleMailMessage message = new SimpleMailMessage();
////            message.setTo(recipientEmail);
////            message.setSubject("Invoice");
////            message.setText("Dear " + invoiceDetails.getRecipientName() + ",\n\n"
////                    + "Please find attached your invoice for the following items:\n\n"
////                    + invoiceDetails.getProductDetails() + "\n\n"
////                    + "Total amount: $" + invoiceDetails.getTotalAmount() + "\n\n"
////                    + "Thank you for your business.\n\n"
////                    + "Sincerely,\n"
////                    + "The Company");
////            // Attach the invoice file
////            MimeMessageHelper helper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
////            try {
////                helper.setTo(recipientEmail);
////                helper.setSubject("Invoice");
////                helper.setText(message.getText(), true);
////                helper.addAttachment("invoice.pdf", new ClassPathResource("invoice.pdf"));
////                javaMailSender.send(helper.getMimeMessage());
////            } catch (MessagingException e) {
////                throw new MailException("Failed to send email", e);
////            }
////        }
//
////        public void sendProductDeliveryEmail(String recipientEmail, DeliveryDetails deliveryDetails) {
////            SimpleMailMessage message = new SimpleMailMessage();
////            message.setTo(recipientEmail);
////            message.setSubject("Product Delivery");
////            message.setText("Dear " + deliveryDetails.getRecipientName() + ",\n\n"
////                    + "Your order of " + deliveryDetails.getProductQuantity() + " " + deliveryDetails.getProductName()
////                    + " has been delivered.\n\n"
////                    + "Delivery tracking number: " + deliveryDetails.getTrackingNumber() + "\n\n"
////                    + "Thank you for your business.\n\n"
////                    + "Sincerely,\n"
////                    + "The Company");
////            javaMailSender.send(message);
////        }
// //Similarly, create methods for order confirmation, invoice, and product delivered emails.
//
//
//    }
//
//
////***************************************************************************************************************
////import edu.miu.shopmartbackend.Email.EmailTemplate;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.mail.SimpleMailMessage;
////import org.springframework.mail.javamail.JavaMailSender;
////import org.springframework.stereotype.Service;
//
////@Service
////    public class EmailService {
////
////    @Autowired
////    private JavaMailSender mailSender;
////
////    public void sendPaymentConfirmation(String to, String customerName, String orderNumber, String paymentDetails) {
////        String content = String.format(EmailTemplate.PAYMENT_CONFIRMATION, customerName, orderNumber, paymentDetails);
////        sendEmail(to, "Payment Confirmation", content);
////    }
////
////    public void sendOrderConfirmation(String to, String customerName, String orderNumber, String orderDetails) {
////        String content = String.format(EmailTemplate.ORDER_CONFIRMATION, customerName, orderNumber, orderDetails);
////        sendEmail(to, "Order Confirmation", content);
////    }
////
////    public void sendInvoice(String to, String customerName, String orderNumber, String invoiceDetails) {
////        String content = String.format(EmailTemplate.INVOICE, customerName, orderNumber, invoiceDetails);
////        sendEmail(to, "Invoice", content);
////    }
////
////    public void sendProductDelivered(String to, String customerName, String orderNumber, String deliveryDetails) {
////        String content = String.format(EmailTemplate.PRODUCT_DELIVERED, customerName, orderNumber, deliveryDetails);
////        sendEmail(to, "Product Delivered", content);
////    }
////
////    private void sendEmail(String to, String subject, String content) {
////        SimpleMailMessage message = new SimpleMailMessage();
////        message.setTo(to);
////        message.setSubject(subject);
////        message.setText(content);
////        mailSender.send(message);
////    }
////}
