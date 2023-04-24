package edu.miu.shopmartbackend.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import edu.miu.shopmartbackend.enums.OrderStatus;
import edu.miu.shopmartbackend.model.Address;
import edu.miu.shopmartbackend.model.Order;
import edu.miu.shopmartbackend.model.ShoppingCart;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.model.dto.OrderDto;
import edu.miu.shopmartbackend.model.dto.PaymentDto;
import edu.miu.shopmartbackend.repo.OrderRepo;
import edu.miu.shopmartbackend.repo.UserRepo;
import edu.miu.shopmartbackend.service.InvoiceService;
import edu.miu.shopmartbackend.service.PaymentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {
    @MockBean
    private InvoiceService invoiceService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private OrderRepo orderRepo;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link OrderServiceImpl#placeOrder(PaymentDto)}
     */
    @Test
    void testPlaceOrder() throws StripeException {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreet("Street");
        address.setZipcode("21654");

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setState("MD");
        address1.setStreet("Street");
        address1.setZipcode("21654");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());

        User user = new User();
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        Order order = new Order();
        order.setBuyer(user);
        order.setId(1L);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStatus(OrderStatus.SHIPPED);
        order.setShoppingCart(shoppingCart1);
        order.setTotalOrderPrice(10.0d);
        when(orderRepo.save((Order) any())).thenReturn(order);
        when(paymentService.handlePayment((PaymentDto) any())).thenReturn(new PaymentIntent());

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setState("MD");
        address2.setStreet("Street");
        address2.setZipcode("21654");

        Address address3 = new Address();
        address3.setCity("Oxford");
        address3.setState("MD");
        address3.setStreet("Street");
        address3.setZipcode("21654");

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setId(1L);
        shoppingCart2.setProducts(new ArrayList<>());

        User user1 = new User();
        user1.setAproved(true);
        user1.setBillingAddress(address2);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1L);
        user1.setLastName("Doe");
        user1.setOrders(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setProducts(new ArrayList<>());
        user1.setRoles(new ArrayList<>());
        user1.setShippingAddress(address3);
        user1.setShoppingCart(shoppingCart2);
        user1.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user1);
        when(userRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> orderServiceImpl.placeOrder(new PaymentDto()));
        verify(orderRepo).save((Order) any());
        verify(paymentService).handlePayment((PaymentDto) any());
        verify(userRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#placeOrder(PaymentDto)}
     */
    @Test
    void testPlaceOrder2() throws StripeException {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreet("Street");
        address.setZipcode("21654");

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setState("MD");
        address1.setStreet("Street");
        address1.setZipcode("21654");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());

        User user = new User();
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        Order order = new Order();
        order.setBuyer(user);
        order.setId(1L);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStatus(OrderStatus.SHIPPED);
        order.setShoppingCart(shoppingCart1);
        order.setTotalOrderPrice(10.0d);
        when(orderRepo.save((Order) any())).thenReturn(order);
        when(paymentService.handlePayment((PaymentDto) any())).thenThrow(new IllegalStateException());

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setState("MD");
        address2.setStreet("Street");
        address2.setZipcode("21654");

        Address address3 = new Address();
        address3.setCity("Oxford");
        address3.setState("MD");
        address3.setStreet("Street");
        address3.setZipcode("21654");

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setId(1L);
        shoppingCart2.setProducts(new ArrayList<>());

        User user1 = new User();
        user1.setAproved(true);
        user1.setBillingAddress(address2);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1L);
        user1.setLastName("Doe");
        user1.setOrders(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setProducts(new ArrayList<>());
        user1.setRoles(new ArrayList<>());
        user1.setShippingAddress(address3);
        user1.setShoppingCart(shoppingCart2);
        user1.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user1);
        when(userRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> orderServiceImpl.placeOrder(new PaymentDto()));
        verify(orderRepo).save((Order) any());
        verify(paymentService).handlePayment((PaymentDto) any());
        verify(userRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#placeOrder(PaymentDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPlaceOrder3() throws StripeException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.OrderServiceImpl.placeOrder(OrderServiceImpl.java:68)
        //   See https://diff.blue/R013 to resolve this issue.

        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreet("Street");
        address.setZipcode("21654");

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setState("MD");
        address1.setStreet("Street");
        address1.setZipcode("21654");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());

        User user = new User();
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        Order order = new Order();
        order.setBuyer(user);
        order.setId(1L);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStatus(OrderStatus.SHIPPED);
        order.setShoppingCart(shoppingCart1);
        order.setTotalOrderPrice(10.0d);
        when(orderRepo.save((Order) any())).thenReturn(order);
        when(paymentService.handlePayment((PaymentDto) any())).thenReturn(null);

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setState("MD");
        address2.setStreet("Street");
        address2.setZipcode("21654");

        Address address3 = new Address();
        address3.setCity("Oxford");
        address3.setState("MD");
        address3.setStreet("Street");
        address3.setZipcode("21654");

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setId(1L);
        shoppingCart2.setProducts(new ArrayList<>());

        User user1 = new User();
        user1.setAproved(true);
        user1.setBillingAddress(address2);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1L);
        user1.setLastName("Doe");
        user1.setOrders(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setProducts(new ArrayList<>());
        user1.setRoles(new ArrayList<>());
        user1.setShippingAddress(address3);
        user1.setShoppingCart(shoppingCart2);
        user1.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user1);
        when(userRepo.findById((Long) any())).thenReturn(ofResult);
        orderServiceImpl.placeOrder(new PaymentDto());
    }

    /**
     * Method under test: {@link OrderServiceImpl#shipOrder(long)}
     */
    @Test
    void testShipOrder() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreet("Street");
        address.setZipcode("21654");

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setState("MD");
        address1.setStreet("Street");
        address1.setZipcode("21654");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());

        User user = new User();
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        Order order = new Order();
        order.setBuyer(user);
        order.setId(1L);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStatus(OrderStatus.SHIPPED);
        order.setShoppingCart(shoppingCart1);
        order.setTotalOrderPrice(10.0d);
        when(modelMapper.map((Object) any(), (Class<Order>) any())).thenReturn(order);

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setState("MD");
        address2.setStreet("Street");
        address2.setZipcode("21654");

        Address address3 = new Address();
        address3.setCity("Oxford");
        address3.setState("MD");
        address3.setStreet("Street");
        address3.setZipcode("21654");

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setId(1L);
        shoppingCart2.setProducts(new ArrayList<>());

        User user1 = new User();
        user1.setAproved(true);
        user1.setBillingAddress(address2);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1L);
        user1.setLastName("Doe");
        user1.setOrders(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setProducts(new ArrayList<>());
        user1.setRoles(new ArrayList<>());
        user1.setShippingAddress(address3);
        user1.setShoppingCart(shoppingCart2);
        user1.setUsername("janedoe");

        ShoppingCart shoppingCart3 = new ShoppingCart();
        shoppingCart3.setId(1L);
        shoppingCart3.setProducts(new ArrayList<>());

        Order order1 = new Order();
        order1.setBuyer(user1);
        order1.setId(1L);
        order1.setOrderDate(LocalDate.ofEpochDay(1L));
        order1.setOrderStatus(OrderStatus.SHIPPED);
        order1.setShoppingCart(shoppingCart3);
        order1.setTotalOrderPrice(10.0d);
        Optional<Order> ofResult = Optional.of(order1);
        when(orderRepo.save((Order) any())).thenThrow(new IllegalStateException());
        when(orderRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> orderServiceImpl.shipOrder(1L));
        verify(modelMapper).map((Object) any(), (Class<Order>) any());
        verify(orderRepo).save((Order) any());
        verify(orderRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#shipOrder(long)}
     */
    @Test
    void testShipOrder2() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreet("Street");
        address.setZipcode("21654");

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setState("MD");
        address1.setStreet("Street");
        address1.setZipcode("21654");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());

        User user = new User();
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setId(1L);
        shoppingCart2.setProducts(new ArrayList<>());
        Order order = mock(Order.class);
        when(order.getOrderStatus()).thenThrow(new IllegalStateException());
        when(order.getShoppingCart()).thenReturn(shoppingCart2);
        doNothing().when(order).setBuyer((User) any());
        doNothing().when(order).setId((Long) any());
        doNothing().when(order).setOrderDate((LocalDate) any());
        doNothing().when(order).setOrderStatus((OrderStatus) any());
        doNothing().when(order).setShoppingCart((ShoppingCart) any());
        doNothing().when(order).setTotalOrderPrice(anyDouble());
        order.setBuyer(user);
        order.setId(1L);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStatus(OrderStatus.SHIPPED);
        order.setShoppingCart(shoppingCart1);
        order.setTotalOrderPrice(10.0d);
        when(modelMapper.map((Object) any(), (Class<Order>) any())).thenReturn(order);

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setState("MD");
        address2.setStreet("Street");
        address2.setZipcode("21654");

        Address address3 = new Address();
        address3.setCity("Oxford");
        address3.setState("MD");
        address3.setStreet("Street");
        address3.setZipcode("21654");

        ShoppingCart shoppingCart3 = new ShoppingCart();
        shoppingCart3.setId(1L);
        shoppingCart3.setProducts(new ArrayList<>());

        User user1 = new User();
        user1.setAproved(true);
        user1.setBillingAddress(address2);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1L);
        user1.setLastName("Doe");
        user1.setOrders(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setProducts(new ArrayList<>());
        user1.setRoles(new ArrayList<>());
        user1.setShippingAddress(address3);
        user1.setShoppingCart(shoppingCart3);
        user1.setUsername("janedoe");

        ShoppingCart shoppingCart4 = new ShoppingCart();
        shoppingCart4.setId(1L);
        shoppingCart4.setProducts(new ArrayList<>());

        Order order1 = new Order();
        order1.setBuyer(user1);
        order1.setId(1L);
        order1.setOrderDate(LocalDate.ofEpochDay(1L));
        order1.setOrderStatus(OrderStatus.SHIPPED);
        order1.setShoppingCart(shoppingCart4);
        order1.setTotalOrderPrice(10.0d);
        Optional<Order> ofResult = Optional.of(order1);

        Address address4 = new Address();
        address4.setCity("Oxford");
        address4.setState("MD");
        address4.setStreet("Street");
        address4.setZipcode("21654");

        Address address5 = new Address();
        address5.setCity("Oxford");
        address5.setState("MD");
        address5.setStreet("Street");
        address5.setZipcode("21654");

        ShoppingCart shoppingCart5 = new ShoppingCart();
        shoppingCart5.setId(1L);
        shoppingCart5.setProducts(new ArrayList<>());

        User user2 = new User();
        user2.setAproved(true);
        user2.setBillingAddress(address4);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1L);
        user2.setLastName("Doe");
        user2.setOrders(new ArrayList<>());
        user2.setPassword("iloveyou");
        user2.setProducts(new ArrayList<>());
        user2.setRoles(new ArrayList<>());
        user2.setShippingAddress(address5);
        user2.setShoppingCart(shoppingCart5);
        user2.setUsername("janedoe");

        ShoppingCart shoppingCart6 = new ShoppingCart();
        shoppingCart6.setId(1L);
        shoppingCart6.setProducts(new ArrayList<>());

        Order order2 = new Order();
        order2.setBuyer(user2);
        order2.setId(1L);
        order2.setOrderDate(LocalDate.ofEpochDay(1L));
        order2.setOrderStatus(OrderStatus.SHIPPED);
        order2.setShoppingCart(shoppingCart6);
        order2.setTotalOrderPrice(10.0d);
        when(orderRepo.save((Order) any())).thenReturn(order2);
        when(orderRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> orderServiceImpl.shipOrder(1L));
        verify(modelMapper).map((Object) any(), (Class<Order>) any());
        verify(order).getOrderStatus();
        verify(order).getShoppingCart();
        verify(order).setBuyer((User) any());
        verify(order).setId((Long) any());
        verify(order).setOrderDate((LocalDate) any());
        verify(order).setOrderStatus((OrderStatus) any());
        verify(order, atLeast(1)).setShoppingCart((ShoppingCart) any());
        verify(order).setTotalOrderPrice(anyDouble());
        verify(orderRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#editOrder(long, double)}
     */
    @Test
    void testEditOrder() {
        OrderDto orderDto = new OrderDto();
        when(modelMapper.map((Object) any(), (Class<OrderDto>) any())).thenReturn(orderDto);

        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreet("Street");
        address.setZipcode("21654");

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setState("MD");
        address1.setStreet("Street");
        address1.setZipcode("21654");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());

        User user = new User();
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        Order order = new Order();
        order.setBuyer(user);
        order.setId(1L);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStatus(OrderStatus.SHIPPED);
        order.setShoppingCart(shoppingCart1);
        order.setTotalOrderPrice(10.0d);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepo.findById((Long) any())).thenReturn(ofResult);
        OrderDto actualEditOrderResult = orderServiceImpl.editOrder(1L, 10.0d);
        assertSame(orderDto, actualEditOrderResult);
        assertEquals(-10.0d, actualEditOrderResult.getTotalOrderPrice());
        verify(modelMapper).map((Object) any(), (Class<OrderDto>) any());
        verify(orderRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#editOrder(long, double)}
     */
    @Test
    void testEditOrder2() {
        when(modelMapper.map((Object) any(), (Class<OrderDto>) any())).thenThrow(new IllegalStateException());

        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreet("Street");
        address.setZipcode("21654");

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setState("MD");
        address1.setStreet("Street");
        address1.setZipcode("21654");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());

        User user = new User();
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        Order order = new Order();
        order.setBuyer(user);
        order.setId(1L);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStatus(OrderStatus.SHIPPED);
        order.setShoppingCart(shoppingCart1);
        order.setTotalOrderPrice(10.0d);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> orderServiceImpl.editOrder(1L, 10.0d));
        verify(modelMapper).map((Object) any(), (Class<OrderDto>) any());
        verify(orderRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#editOrder(long, double)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testEditOrder3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.OrderServiceImpl.editOrder(OrderServiceImpl.java:188)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<OrderDto>) any())).thenReturn(null);

        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreet("Street");
        address.setZipcode("21654");

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setState("MD");
        address1.setStreet("Street");
        address1.setZipcode("21654");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());

        User user = new User();
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        Order order = new Order();
        order.setBuyer(user);
        order.setId(1L);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStatus(OrderStatus.SHIPPED);
        order.setShoppingCart(shoppingCart1);
        order.setTotalOrderPrice(10.0d);
        Optional<Order> ofResult = Optional.of(order);
        when(orderRepo.findById((Long) any())).thenReturn(ofResult);
        orderServiceImpl.editOrder(1L, 10.0d);
    }

    /**
     * Method under test: {@link OrderServiceImpl#editOrder(long, double)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testEditOrder4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at edu.miu.shopmartbackend.service.impl.OrderServiceImpl.editOrder(OrderServiceImpl.java:187)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<OrderDto>) any())).thenReturn(new OrderDto());
        when(orderRepo.findById((Long) any())).thenReturn(Optional.empty());
        orderServiceImpl.editOrder(1L, 10.0d);
    }

    /**
     * Method under test: {@link OrderServiceImpl#cancelOrder(long)}
     */
    @Test
    void testCancelOrder() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreet("Street");
        address.setZipcode("21654");

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setState("MD");
        address1.setStreet("Street");
        address1.setZipcode("21654");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());

        User user = new User();
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        Order order = new Order();
        order.setBuyer(user);
        order.setId(1L);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStatus(OrderStatus.SHIPPED);
        order.setShoppingCart(shoppingCart1);
        order.setTotalOrderPrice(10.0d);
        when(modelMapper.map((Object) any(), (Class<Order>) any())).thenReturn(order);

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setState("MD");
        address2.setStreet("Street");
        address2.setZipcode("21654");

        Address address3 = new Address();
        address3.setCity("Oxford");
        address3.setState("MD");
        address3.setStreet("Street");
        address3.setZipcode("21654");

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setId(1L);
        shoppingCart2.setProducts(new ArrayList<>());

        User user1 = new User();
        user1.setAproved(true);
        user1.setBillingAddress(address2);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1L);
        user1.setLastName("Doe");
        user1.setOrders(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setProducts(new ArrayList<>());
        user1.setRoles(new ArrayList<>());
        user1.setShippingAddress(address3);
        user1.setShoppingCart(shoppingCart2);
        user1.setUsername("janedoe");

        ShoppingCart shoppingCart3 = new ShoppingCart();
        shoppingCart3.setId(1L);
        shoppingCart3.setProducts(new ArrayList<>());

        Order order1 = new Order();
        order1.setBuyer(user1);
        order1.setId(1L);
        order1.setOrderDate(LocalDate.ofEpochDay(1L));
        order1.setOrderStatus(OrderStatus.SHIPPED);
        order1.setShoppingCart(shoppingCart3);
        order1.setTotalOrderPrice(10.0d);
        Optional<Order> ofResult = Optional.of(order1);
        when(orderRepo.save((Order) any())).thenThrow(new IllegalStateException());
        when(orderRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> orderServiceImpl.cancelOrder(1L));
        verify(modelMapper).map((Object) any(), (Class<Order>) any());
        verify(orderRepo).save((Order) any());
        verify(orderRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link OrderServiceImpl#cancelOrder(long)}
     */
    @Test
    void testCancelOrder2() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreet("Street");
        address.setZipcode("21654");

        Address address1 = new Address();
        address1.setCity("Oxford");
        address1.setState("MD");
        address1.setStreet("Street");
        address1.setZipcode("21654");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());

        User user = new User();
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1L);
        user.setLastName("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setId(1L);
        shoppingCart2.setProducts(new ArrayList<>());
        Order order = mock(Order.class);
        when(order.getOrderStatus()).thenThrow(new IllegalStateException());
        when(order.getShoppingCart()).thenReturn(shoppingCart2);
        doNothing().when(order).setBuyer((User) any());
        doNothing().when(order).setId((Long) any());
        doNothing().when(order).setOrderDate((LocalDate) any());
        doNothing().when(order).setOrderStatus((OrderStatus) any());
        doNothing().when(order).setShoppingCart((ShoppingCart) any());
        doNothing().when(order).setTotalOrderPrice(anyDouble());
        order.setBuyer(user);
        order.setId(1L);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStatus(OrderStatus.SHIPPED);
        order.setShoppingCart(shoppingCart1);
        order.setTotalOrderPrice(10.0d);
        when(modelMapper.map((Object) any(), (Class<Order>) any())).thenReturn(order);

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setState("MD");
        address2.setStreet("Street");
        address2.setZipcode("21654");

        Address address3 = new Address();
        address3.setCity("Oxford");
        address3.setState("MD");
        address3.setStreet("Street");
        address3.setZipcode("21654");

        ShoppingCart shoppingCart3 = new ShoppingCart();
        shoppingCart3.setId(1L);
        shoppingCart3.setProducts(new ArrayList<>());

        User user1 = new User();
        user1.setAproved(true);
        user1.setBillingAddress(address2);
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1L);
        user1.setLastName("Doe");
        user1.setOrders(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setProducts(new ArrayList<>());
        user1.setRoles(new ArrayList<>());
        user1.setShippingAddress(address3);
        user1.setShoppingCart(shoppingCart3);
        user1.setUsername("janedoe");

        ShoppingCart shoppingCart4 = new ShoppingCart();
        shoppingCart4.setId(1L);
        shoppingCart4.setProducts(new ArrayList<>());

        Order order1 = new Order();
        order1.setBuyer(user1);
        order1.setId(1L);
        order1.setOrderDate(LocalDate.ofEpochDay(1L));
        order1.setOrderStatus(OrderStatus.SHIPPED);
        order1.setShoppingCart(shoppingCart4);
        order1.setTotalOrderPrice(10.0d);
        Optional<Order> ofResult = Optional.of(order1);

        Address address4 = new Address();
        address4.setCity("Oxford");
        address4.setState("MD");
        address4.setStreet("Street");
        address4.setZipcode("21654");

        Address address5 = new Address();
        address5.setCity("Oxford");
        address5.setState("MD");
        address5.setStreet("Street");
        address5.setZipcode("21654");

        ShoppingCart shoppingCart5 = new ShoppingCart();
        shoppingCart5.setId(1L);
        shoppingCart5.setProducts(new ArrayList<>());

        User user2 = new User();
        user2.setAproved(true);
        user2.setBillingAddress(address4);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1L);
        user2.setLastName("Doe");
        user2.setOrders(new ArrayList<>());
        user2.setPassword("iloveyou");
        user2.setProducts(new ArrayList<>());
        user2.setRoles(new ArrayList<>());
        user2.setShippingAddress(address5);
        user2.setShoppingCart(shoppingCart5);
        user2.setUsername("janedoe");

        ShoppingCart shoppingCart6 = new ShoppingCart();
        shoppingCart6.setId(1L);
        shoppingCart6.setProducts(new ArrayList<>());

        Order order2 = new Order();
        order2.setBuyer(user2);
        order2.setId(1L);
        order2.setOrderDate(LocalDate.ofEpochDay(1L));
        order2.setOrderStatus(OrderStatus.SHIPPED);
        order2.setShoppingCart(shoppingCart6);
        order2.setTotalOrderPrice(10.0d);
        when(orderRepo.save((Order) any())).thenReturn(order2);
        when(orderRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> orderServiceImpl.cancelOrder(1L));
        verify(modelMapper).map((Object) any(), (Class<Order>) any());
        verify(order).getOrderStatus();
        verify(order).getShoppingCart();
        verify(order).setBuyer((User) any());
        verify(order).setId((Long) any());
        verify(order).setOrderDate((LocalDate) any());
        verify(order).setOrderStatus((OrderStatus) any());
        verify(order, atLeast(1)).setShoppingCart((ShoppingCart) any());
        verify(order).setTotalOrderPrice(anyDouble());
        verify(orderRepo).findById((Long) any());
    }
}

