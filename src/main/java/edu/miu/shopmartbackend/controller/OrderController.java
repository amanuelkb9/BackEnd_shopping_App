package edu.miu.shopmartbackend.controller;

import edu.miu.shopmartbackend.aspect.annotation.EmailSender;
import edu.miu.shopmartbackend.model.dto.OrderDto;
import edu.miu.shopmartbackend.service.OrderService;
import edu.miu.shopmartbackend.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    SearchService searchService;

    @ResponseStatus(HttpStatus.OK)
    @EmailSender
    @PatchMapping("/{buyer_id}/create")
    OrderDto createOrder(@PathVariable("buyer_id") long buyer_id){
        return orderService.placeOrder(buyer_id);
    }

    @ResponseStatus(HttpStatus.OK)
    @EmailSender
    @PatchMapping("/{orderId}/ship")
    OrderDto shipOrder(@PathVariable("orderId") long orderId){
        return orderService.shipOrder(orderId);
    }

//    @ResponseStatus(HttpStatus.OK)
//    @PatchMapping("/{orderId}/deliver")
//    OrderDto deliverOrder(@PathVariable("orderId") long orderId){
//        return orderService.deliverOrder(orderId);
//    }




    @ResponseStatus(HttpStatus.OK)
    @EmailSender
    @PatchMapping("/{orderId}/edit")
    public OrderDto editOrder(Long orderId, double discount) {
        return orderService.editOrder(orderId, discount);
    }


    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{orderId}")
    OrderDto cancelOrder(@PathVariable long orderId){
        return orderService.cancelOrder(orderId);
    }



}
