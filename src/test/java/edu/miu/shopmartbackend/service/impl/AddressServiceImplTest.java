package edu.miu.shopmartbackend.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.shopmartbackend.model.Address;
import edu.miu.shopmartbackend.model.ShoppingCart;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AddressServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AddressServiceImplTest {
    @Autowired
    private AddressServiceImpl addressServiceImpl;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link AddressServiceImpl#addBuyerBillingAddress(long, Address)}
     */
    @Test
    void testAddBuyerBillingAddress() {
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

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

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
        user1.setShoppingCart(shoppingCart1);
        user1.setUsername("janedoe");
        when(userRepo.save((User) any())).thenReturn(user1);
        when(userRepo.getUserById(anyLong())).thenReturn(user);

        Address address4 = new Address();
        address4.setCity("Oxford");
        address4.setState("MD");
        address4.setStreet("Street");
        address4.setZipcode("21654");
        addressServiceImpl.addBuyerBillingAddress(1L, address4);
        verify(userRepo).getUserById(anyLong());
        verify(userRepo).save((User) any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#addBuyerShippingAddress(long, Address)}
     */
    @Test
    void testAddBuyerShippingAddress() {
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

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

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
        user1.setShoppingCart(shoppingCart1);
        user1.setUsername("janedoe");
        when(userRepo.save((User) any())).thenReturn(user1);
        when(userRepo.getUserById(anyLong())).thenReturn(user);

        Address address4 = new Address();
        address4.setCity("Oxford");
        address4.setState("MD");
        address4.setStreet("Street");
        address4.setZipcode("21654");
        addressServiceImpl.addBuyerShippingAddress(1L, address4);
        verify(userRepo).getUserById(anyLong());
        verify(userRepo).save((User) any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#getBuyerBillingAddress(long)}
     */
    @Test
    void testGetBuyerBillingAddress() {
        ArrayList<Address> addressList = new ArrayList<>();
        when(userRepo.getBuyerBillingAddress(anyLong())).thenReturn(addressList);
        List<Address> actualBuyerBillingAddress = addressServiceImpl.getBuyerBillingAddress(1L);
        assertSame(addressList, actualBuyerBillingAddress);
        assertTrue(actualBuyerBillingAddress.isEmpty());
        verify(userRepo).getBuyerBillingAddress(anyLong());
    }

    /**
     * Method under test: {@link AddressServiceImpl#getBuyerShippingAddress(long)}
     */
    @Test
    void testGetBuyerShippingAddress() {
        ArrayList<Address> addressList = new ArrayList<>();
        when(userRepo.getBuyerShippingAddress(anyLong())).thenReturn(addressList);
        List<Address> actualBuyerShippingAddress = addressServiceImpl.getBuyerShippingAddress(1L);
        assertSame(addressList, actualBuyerShippingAddress);
        assertTrue(actualBuyerShippingAddress.isEmpty());
        verify(userRepo).getBuyerShippingAddress(anyLong());
    }
}

