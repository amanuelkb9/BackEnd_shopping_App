package edu.miu.shopmartbackend.service.impl;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import edu.miu.shopmartbackend.enums.OrderStatus;
import edu.miu.shopmartbackend.model.*;
import edu.miu.shopmartbackend.model.dto.OrderDto;
import edu.miu.shopmartbackend.model.dto.PaymentDto;
import edu.miu.shopmartbackend.repo.OrderRepo;
import edu.miu.shopmartbackend.repo.UserRepo;
import edu.miu.shopmartbackend.service.InvoiceService;
import edu.miu.shopmartbackend.service.OrderService;
import edu.miu.shopmartbackend.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    PaymentService paymentService;
    @Override
    public OrderDto placeOrder(PaymentDto paymentDto) throws StripeException {
        // Find buyer by ID and check if it exists
        Optional<User> buyerOpt = userRepo.findById(paymentDto.getBuyer_id());
        if (!buyerOpt.isPresent()) {
            throw new IllegalStateException("Buyer not found");
        }
        User buyer = buyerOpt.get();
        ShoppingCart shoppingCart = buyer.getShoppingCart();
        // Calculate total price
        double totalPrice = shoppingCart.getProducts().stream().mapToDouble(Product::getPrice).sum();

        // Create order object
        Order order = new Order();
        order.setOrderStatus(OrderStatus.ORDERED);
        order.setOrderDate(LocalDate.now());

        order.setBuyer(buyer);
        order.setTotalOrderPrice(totalPrice);
        // Save the order
        orderRepo.save(order);

        paymentDto.setAmount(totalPrice);
        paymentDto.setOrder_Id(order.getId());
        paymentDto.setName(buyer.getFirstname() + " " + buyer.getLastname());
        // Handle payment
        PaymentIntent paymentIntent = paymentService.handlePayment(paymentDto);
        // Check payment status
        System.out.println("1111111111111111111111111111111");
        System.out.println(paymentIntent.getStatus());
        System.out.println("1111111111111111111111111111111");
        if ("succeeded".equals(paymentIntent.getStatus())) {
            for(Product prod: shoppingCart.getProducts()){
                prod.setPurchased(true);
            }
            order.setShoppingCart(shoppingCart);
            order.setOrderStatus(OrderStatus.PAID);

            Order savedOrder = orderRepo.save(order);
            Invoice invoice = invoiceService.payToOrder(paymentDto);


            return modelMapper.map(savedOrder, OrderDto.class);
        } else {
            throw new IllegalStateException("Payment failed");
        }
    }




    @Override
    public OrderDto shipOrder(long orderId) {

        Order orders = modelMapper.map(orderRepo.findById(orderId).get(), Order.class);
        ShoppingCart shoppingCart = orders.getShoppingCart();

        List<Product> products = shoppingCart.getProducts();
        if(products != null)
        products.forEach(p -> p.setPurchased(true));
//
        shoppingCart.setProducts(products);
        orders.setShoppingCart(shoppingCart);
//        Order order = modelMapper.map(findOrderById(orderId), Order.class);
        if (orders.getOrderStatus() == OrderStatus.ORDERED) {
            orders.setOrderStatus(OrderStatus.SHIPPED);
        }
        return modelMapper.map(orderRepo.save(orders), OrderDto.class);
    }

    @Override
    public OrderDto editOrder(long orderId, double discount) {
        OrderDto orderDto = modelMapper.map(orderRepo.findById(orderId).get(), OrderDto.class);
        orderDto.setTotalOrderPrice(orderDto.getTotalOrderPrice() - discount);
        return orderDto;
    }


    @Override
    public OrderDto cancelOrder(long orderId) {
        Order orders = modelMapper.map(orderRepo.findById(orderId).get(), Order.class);

        ShoppingCart shoppingCart = orders.getShoppingCart();

        List<Product> products = shoppingCart.getProducts();
        if(products != null)
            products.forEach(p -> p.setPurchased(false));

        shoppingCart.setProducts(products);
        orders.setShoppingCart(shoppingCart);

        if (orders.getOrderStatus() == OrderStatus.ORDERED) {
            orders.setOrderStatus(OrderStatus.CANCELLED);
        }
        return modelMapper.map(orderRepo.save(orders), OrderDto.class);
    }



}


