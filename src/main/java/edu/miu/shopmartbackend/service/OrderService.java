package edu.miu.shopmartbackend.service;

import com.stripe.exception.StripeException;
import edu.miu.shopmartbackend.model.dto.PaymentDto;
import edu.miu.shopmartbackend.model.dto.OrderDto;

public interface OrderService {


    OrderDto cancelOrder(long orderId);

    OrderDto shipOrder(long orderId);

//    OrderDto deliverOrder(long orderId);

    OrderDto editOrder(long orderId, double discount);
    OrderDto placeOrder (PaymentDto paymentDto) throws StripeException;


}
