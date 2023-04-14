package edu.miu.shopmartbackend.service.impl;

import edu.miu.shopmartbackend.aspect.annotation.EmailSender;
import edu.miu.shopmartbackend.service.InvoiceService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @EmailSender
    @Override
    public double payToOrder(double totalPrice) {
        return totalPrice;
    }
}
