package edu.miu.shopmartbackend.service.impl;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import edu.miu.shopmartbackend.controller.PaymentController;
import edu.miu.shopmartbackend.controller.PaymentData;
import edu.miu.shopmartbackend.enums.OrderStatus;
import edu.miu.shopmartbackend.model.Order;
import edu.miu.shopmartbackend.model.Product;
import edu.miu.shopmartbackend.model.ShoppingCart;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.model.dto.OrderDto;
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
    public OrderDto placeOrder(PaymentData paymentData) throws StripeException {
        Order order = new Order();
        User buyer = userRepo.findById(paymentData.getBuyer_id()).get();
        ShoppingCart shoppingCart = buyer.getShoppingCart();

        order.setOrderStatus(OrderStatus.ORDERED);
        order.setOrderDate(LocalDate.now());
        order.setShoppingCart(shoppingCart);
        order.setBuyer(buyer);

        double totalPrice = 0;
        List<Product> products = shoppingCart.getProducts();

        for (Product p : products) {
            totalPrice += p.getPrice();
        }
        System.out.println("===================order set====================");
        System.out.println(order);
        order.setTotalOrderPrice(totalPrice);
        orderRepo.save(order);
        System.out.println("===============order saved1 ================");

        PaymentIntent paymentIntent = paymentService.handlePayment(paymentData);
        //invoiceService.payToOrder(totalPrice);
        if("succeeded".equals(paymentIntent.getStatus())){

            order.setOrderStatus(OrderStatus.PAID);
            OrderDto orderDto = modelMapper.map(orderRepo.save(order), OrderDto.class);
            System.out.println("==================Order saved2================");
            System.out.println(order);
            return orderDto;
        }else {
            System.out.println("!!!!!!!!!!!!exception!!!!!!!!!!!!!!!");
            throw new IllegalStateException("Payment failed");
        }



    }

//    @Override
//    public OrderDto placeOrder(long buyer_id) throws StripeException {
//        Order order = new Order();
//        User buyer = userRepo.findById(buyer_id).get();
//        ShoppingCart shoppingCart = buyer.getShoppingCart();
//
//        order.setOrderStatus(OrderStatus.ORDERED);
//        order.setOrderDate(LocalDate.now());
//        order.setShoppingCart(shoppingCart);
//        order.setBuyer(buyer);
//
//        double totalPrice = 0;
//        List<Product> products = shoppingCart.getProducts();
//
//        for (Product p : products) {
//            totalPrice += p.getPrice();
//        }
//
//        // Handle payment
//        PaymentData paymentData = new PaymentData();
//        paymentData.setType("card");
//        paymentData.setCardNumber("4242424242424242"); // replace with actual card number
//        paymentData.setExp_month(12);
//        paymentData.setExp_year(2023);
//        paymentData.setCvc("123"); // replace with actual CVC code
//        paymentData.setAmount(totalPrice);
//        paymentData.setCurrency("usd");
//        paymentData.setBuyer_id(buyer_id);
//        PaymentIntent paymentIntent = paymentController.handlePayment(paymentData);
//
//        if (paymentIntent.getStatus().equals("succeeded")) {
//            order.setTotalOrderPrice(totalPrice);
//            invoiceService.payToOrder(totalPrice);
//            orderRepo.save(order);
//        } else {
//            throw new RuntimeException("Payment failed");
//        }
//
//        return modelMapper.map(order, OrderDto.class);
//    }


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

    //    @Override
//    public OrderDto deliverOrder(long orderId) {
//        Order order = modelMapper.map(orderRepo.findById(orderId).get(), Order.class);
//        if (order.getOrderStatus() == OrderStatus.SHIPPED) {
//            order.setOrderStatus(OrderStatus.DELIVERED);
//        }
//        User buyer = order.getBuyer();
//        int points = buyer.getPoints() + 10;
//        buyer.setPoints(points);
//        order.setBuyer(buyer);
//        return modelMapper.map(orderRepo.save(order),OrderDto .class);
//
//    }
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


