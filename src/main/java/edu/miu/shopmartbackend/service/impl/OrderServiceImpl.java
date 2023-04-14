package edu.miu.shopmartbackend.service.impl;

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


    @Override
    public OrderDto placeOrder(long buyer_id) {
        Order order = new Order();
        User buyer = userRepo.findById(buyer_id).get();
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

        invoiceService.payToOrder(totalPrice);
        order.setTotalOrderPrice(totalPrice);

        return modelMapper.map(orderRepo.save(order), OrderDto.class);

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


