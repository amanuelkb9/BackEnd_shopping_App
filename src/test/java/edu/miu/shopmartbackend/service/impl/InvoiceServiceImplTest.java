package edu.miu.shopmartbackend.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.shopmartbackend.model.Address;
import edu.miu.shopmartbackend.model.Invoice;
import edu.miu.shopmartbackend.model.ShoppingCart;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.model.dto.PaymentDto;
import edu.miu.shopmartbackend.repo.UserRepo;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InvoiceServiceImpl.class})
@ExtendWith(SpringExtension.class)
class InvoiceServiceImplTest {
    @Autowired
    private InvoiceServiceImpl invoiceServiceImpl;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link InvoiceServiceImpl#payToOrder(PaymentDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPayToOrder() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.InvoiceServiceImpl.payToOrder(InvoiceServiceImpl.java:22)
        //   See https://diff.blue/R013 to resolve this issue.

        invoiceServiceImpl.payToOrder(new PaymentDto());
    }

    /**
     * Method under test: {@link InvoiceServiceImpl#payToOrder(PaymentDto)}
     */
    @Test
    void testPayToOrder2() {
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
        when(userRepo.getUserById(anyLong())).thenReturn(user);

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(10.0d);
        Invoice actualPayToOrderResult = invoiceServiceImpl.payToOrder(paymentDto);
        assertNull(actualPayToOrderResult.getCardNumber());
        assertSame(user, actualPayToOrderResult.getUser());
        assertEquals(10.0d, actualPayToOrderResult.getTotalPrice());
        assertEquals(0L, actualPayToOrderResult.getId());
        verify(userRepo).getUserById(anyLong());
    }
}

