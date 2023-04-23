package edu.miu.shopmartbackend.service;

import edu.miu.shopmartbackend.model.Invoice;
import edu.miu.shopmartbackend.model.dto.PaymentDto;

public interface InvoiceService {

   Invoice payToOrder(PaymentDto paymentDto);
}
