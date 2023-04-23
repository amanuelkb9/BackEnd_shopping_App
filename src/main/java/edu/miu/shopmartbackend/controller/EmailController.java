package edu.miu.shopmartbackend.controller;


import edu.miu.shopmartbackend.model.dto.EmailDto;
import edu.miu.shopmartbackend.service.EmailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/sendEmail")
public class EmailController {

    private final EmailSenderService emailSenderService;

    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    //Send simple email
    @PostMapping("/sendEmail")
    public ResponseEntity sendEmail(@RequestBody EmailDto emailMessage){
        this.emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(),emailMessage.getMessage());

        return ResponseEntity.ok("Email Sent successfully");

    }

    @PostMapping("/paymentEmail")
    public ResponseEntity sendPaymentConfirmationEmail(@RequestBody EmailDto emailMessage){
        this.emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(),emailMessage.getMessage());

        return ResponseEntity.ok("Payment Email Sent successfully");

    }


}
