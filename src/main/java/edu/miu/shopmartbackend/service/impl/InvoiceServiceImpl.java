package edu.miu.shopmartbackend.service.impl;

import edu.miu.shopmartbackend.aspect.annotation.EmailSender;
import edu.miu.shopmartbackend.model.Invoice;
import edu.miu.shopmartbackend.model.dto.PaymentDto;
import edu.miu.shopmartbackend.repo.UserRepo;
import edu.miu.shopmartbackend.service.InvoiceService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final UserRepo userRepo;

    public InvoiceServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @EmailSender
    @Override
    public Invoice payToOrder(PaymentDto paymentDto) {

        return new Invoice(paymentDto.getBuyer_id(), paymentDto.getCardNumber(), paymentDto.getAmount(),
                userRepo.getUserById(paymentDto.getBuyer_id()));
    }
}
