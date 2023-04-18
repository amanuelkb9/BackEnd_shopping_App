package edu.miu.shopmartbackend.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.shopmartbackend.model.Address;
import edu.miu.shopmartbackend.model.Order;
import edu.miu.shopmartbackend.model.Product;
import edu.miu.shopmartbackend.model.Role;
import edu.miu.shopmartbackend.model.ShoppingCart;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.repo.ProductRepo;
import edu.miu.shopmartbackend.repo.ShoppingCartRepo;
import edu.miu.shopmartbackend.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ShoppingCartServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ShoppingCartServiceImplTest {
    @MockBean
    private ProductRepo productRepo;

    @MockBean
    private ShoppingCartRepo shoppingCartRepo;

    @Autowired
    private ShoppingCartServiceImpl shoppingCartServiceImpl;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link ShoppingCartServiceImpl#createShoppingCart(long)}
     */
    @Test
    void testCreateShoppingCart() {
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
        user.setFirstname("Jane");
        user.setFollowing(true);
        user.setId(1L);
        user.setLastname("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");
        when(userRepo.getUserById(anyLong())).thenReturn(user);
        assertNull(shoppingCartServiceImpl.createShoppingCart(1L));
        verify(userRepo).getUserById(anyLong());
    }

    /**
     * Method under test: {@link ShoppingCartServiceImpl#getShoppingCartById(long)}
     */
    @Test
    void testGetShoppingCartById() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());
        Optional<ShoppingCart> ofResult = Optional.of(shoppingCart);
        when(shoppingCartRepo.findById((Long) any())).thenReturn(ofResult);
        assertSame(shoppingCart, shoppingCartServiceImpl.getShoppingCartById(1L));
        verify(shoppingCartRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ShoppingCartServiceImpl#getShoppingCartById(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetShoppingCartById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at edu.miu.shopmartbackend.service.impl.ShoppingCartServiceImpl.getShoppingCartById(ShoppingCartServiceImpl.java:49)
        //   See https://diff.blue/R013 to resolve this issue.

        when(shoppingCartRepo.findById((Long) any())).thenReturn(Optional.empty());
        shoppingCartServiceImpl.getShoppingCartById(1L);
    }

    /**
     * Method under test: {@link ShoppingCartServiceImpl#addProductToShoppingCart(long, long)}
     */
    @Test
    void testAddProductToShoppingCart() {
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
        user.setFirstname("Jane");
        user.setFollowing(true);
        user.setId(1L);
        user.setLastname("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setPrice(10.0d);
        product.setProductName("Product Name");
        product.setPurchased(true);
        product.setSeller(user);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.findById((Long) any())).thenReturn(ofResult);

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());
        when(shoppingCartRepo.save((ShoppingCart) any())).thenReturn(shoppingCart1);

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
        user1.setFirstname("Jane");
        user1.setFollowing(true);
        user1.setId(1L);
        user1.setLastname("Doe");
        user1.setOrders(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setProducts(new ArrayList<>());
        user1.setRoles(new ArrayList<>());
        user1.setShippingAddress(address3);
        user1.setShoppingCart(shoppingCart2);
        user1.setUsername("janedoe");
        Optional<User> ofResult1 = Optional.of(user1);

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

        ShoppingCart shoppingCart3 = new ShoppingCart();
        shoppingCart3.setId(1L);
        shoppingCart3.setProducts(new ArrayList<>());

        User user2 = new User();
        user2.setAproved(true);
        user2.setBillingAddress(address4);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstname("Jane");
        user2.setFollowing(true);
        user2.setId(1L);
        user2.setLastname("Doe");
        user2.setOrders(new ArrayList<>());
        user2.setPassword("iloveyou");
        user2.setProducts(new ArrayList<>());
        user2.setRoles(new ArrayList<>());
        user2.setShippingAddress(address5);
        user2.setShoppingCart(shoppingCart3);
        user2.setUsername("janedoe");
        when(userRepo.save((User) any())).thenReturn(user2);
        when(userRepo.findById((Long) any())).thenReturn(ofResult1);
        assertSame(shoppingCart1, shoppingCartServiceImpl.addProductToShoppingCart(1L, 1L));
        verify(productRepo).findById((Long) any());
        verify(shoppingCartRepo).save((ShoppingCart) any());
        verify(userRepo).save((User) any());
        verify(userRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ShoppingCartServiceImpl#addProductToShoppingCart(long, long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProductToShoppingCart2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at edu.miu.shopmartbackend.service.impl.ShoppingCartServiceImpl.addProductToShoppingCart(ShoppingCartServiceImpl.java:57)
        //   See https://diff.blue/R013 to resolve this issue.

        when(productRepo.findById((Long) any())).thenReturn(Optional.empty());

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());
        when(shoppingCartRepo.save((ShoppingCart) any())).thenReturn(shoppingCart);

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

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        User user = new User();
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstname("Jane");
        user.setFollowing(true);
        user.setId(1L);
        user.setLastname("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart1);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);

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
        user1.setFirstname("Jane");
        user1.setFollowing(true);
        user1.setId(1L);
        user1.setLastname("Doe");
        user1.setOrders(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setProducts(new ArrayList<>());
        user1.setRoles(new ArrayList<>());
        user1.setShippingAddress(address3);
        user1.setShoppingCart(shoppingCart2);
        user1.setUsername("janedoe");
        when(userRepo.save((User) any())).thenReturn(user1);
        when(userRepo.findById((Long) any())).thenReturn(ofResult);
        shoppingCartServiceImpl.addProductToShoppingCart(1L, 1L);
    }

    /**
     * Method under test: {@link ShoppingCartServiceImpl#deleteShoppingCart(long)}
     */
    @Test
    void testDeleteShoppingCart() {
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
        user.setFirstname("Jane");
        user.setFollowing(true);
        user.setId(1L);
        user.setLastname("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);

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
        user1.setFirstname("Jane");
        user1.setFollowing(true);
        user1.setId(1L);
        user1.setLastname("Doe");
        user1.setOrders(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setProducts(new ArrayList<>());
        user1.setRoles(new ArrayList<>());
        user1.setShippingAddress(address3);
        user1.setShoppingCart(shoppingCart1);
        user1.setUsername("janedoe");
        when(userRepo.save((User) any())).thenReturn(user1);
        when(userRepo.findById((Long) any())).thenReturn(ofResult);
        shoppingCartServiceImpl.deleteShoppingCart(1L);
        verify(userRepo).save((User) any());
        verify(userRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ShoppingCartServiceImpl#deleteProductByIdFromCart(long, long)}
     */
    @Test
    void testDeleteProductByIdFromCart() {
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
        user.setFirstname("Jane");
        user.setFollowing(true);
        user.setId(1L);
        user.setLastname("Doe");
        ArrayList<Order> orderList = new ArrayList<>();
        user.setOrders(orderList);
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setPrice(10.0d);
        product.setProductName("Product Name");
        product.setPurchased(true);
        product.setSeller(user);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.findById((Long) any())).thenReturn(ofResult);

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());
        when(shoppingCartRepo.save((ShoppingCart) any())).thenReturn(shoppingCart1);

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
        user1.setFirstname("Jane");
        user1.setFollowing(true);
        user1.setId(1L);
        user1.setLastname("Doe");
        user1.setOrders(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setProducts(new ArrayList<>());
        user1.setRoles(new ArrayList<>());
        user1.setShippingAddress(address3);
        user1.setShoppingCart(shoppingCart2);
        user1.setUsername("janedoe");
        Optional<User> ofResult1 = Optional.of(user1);

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

        ShoppingCart shoppingCart3 = new ShoppingCart();
        shoppingCart3.setId(1L);
        shoppingCart3.setProducts(new ArrayList<>());

        User user2 = new User();
        user2.setAproved(true);
        user2.setBillingAddress(address4);
        user2.setEmail("jane.doe@example.org");
        user2.setFirstname("Jane");
        user2.setFollowing(true);
        user2.setId(1L);
        user2.setLastname("Doe");
        user2.setOrders(new ArrayList<>());
        user2.setPassword("iloveyou");
        user2.setProducts(new ArrayList<>());
        user2.setRoles(new ArrayList<>());
        user2.setShippingAddress(address5);
        user2.setShoppingCart(shoppingCart3);
        user2.setUsername("janedoe");
        when(userRepo.save((User) any())).thenReturn(user2);
        when(userRepo.findById((Long) any())).thenReturn(ofResult1);
        ShoppingCart actualDeleteProductByIdFromCartResult = shoppingCartServiceImpl.deleteProductByIdFromCart(1L, 1L);
        assertSame(shoppingCart2, actualDeleteProductByIdFromCartResult);
        List<Product> products = actualDeleteProductByIdFromCartResult.getProducts();
        assertEquals(orderList, products);
        assertTrue(products.isEmpty());
        verify(productRepo).findById((Long) any());
        verify(shoppingCartRepo).save((ShoppingCart) any());
        verify(userRepo).save((User) any());
        verify(userRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ShoppingCartServiceImpl#deleteProductByIdFromCart(long, long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteProductByIdFromCart2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at edu.miu.shopmartbackend.service.impl.ShoppingCartServiceImpl.deleteProductByIdFromCart(ShoppingCartServiceImpl.java:76)
        //   See https://diff.blue/R013 to resolve this issue.

        when(productRepo.findById((Long) any())).thenReturn(Optional.empty());

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());
        when(shoppingCartRepo.save((ShoppingCart) any())).thenReturn(shoppingCart);

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

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        User user = new User();
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstname("Jane");
        user.setFollowing(true);
        user.setId(1L);
        user.setLastname("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart1);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);

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
        user1.setFirstname("Jane");
        user1.setFollowing(true);
        user1.setId(1L);
        user1.setLastname("Doe");
        user1.setOrders(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setProducts(new ArrayList<>());
        user1.setRoles(new ArrayList<>());
        user1.setShippingAddress(address3);
        user1.setShoppingCart(shoppingCart2);
        user1.setUsername("janedoe");
        when(userRepo.save((User) any())).thenReturn(user1);
        when(userRepo.findById((Long) any())).thenReturn(ofResult);
        shoppingCartServiceImpl.deleteProductByIdFromCart(1L, 1L);
    }

    /**
     * Method under test: {@link ShoppingCartServiceImpl#clearShoppingCart(long)}
     */
    @Test
    void testClearShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());
        when(shoppingCartRepo.save((ShoppingCart) any())).thenReturn(shoppingCart);

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

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        User user = new User();
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstname("Jane");
        user.setFollowing(true);
        user.setId(1L);
        user.setLastname("Doe");
        ArrayList<Order> orderList = new ArrayList<>();
        user.setOrders(orderList);
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart1);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findById((Long) any())).thenReturn(ofResult);
        ShoppingCart actualClearShoppingCartResult = shoppingCartServiceImpl.clearShoppingCart(1L);
        assertSame(shoppingCart1, actualClearShoppingCartResult);
        assertEquals(orderList, actualClearShoppingCartResult.getProducts());
        verify(shoppingCartRepo).save((ShoppingCart) any());
        verify(userRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ShoppingCartServiceImpl#clearShoppingCart(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testClearShoppingCart2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at edu.miu.shopmartbackend.service.impl.ShoppingCartServiceImpl.clearShoppingCart(ShoppingCartServiceImpl.java:91)
        //   See https://diff.blue/R013 to resolve this issue.

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());
        when(shoppingCartRepo.save((ShoppingCart) any())).thenReturn(shoppingCart);
        when(userRepo.findById((Long) any())).thenReturn(Optional.empty());

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

        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        shoppingCart1.setProducts(new ArrayList<>());

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setId(1L);
        shoppingCart2.setProducts(new ArrayList<>());
        User user = mock(User.class);
        when(user.getShoppingCart()).thenReturn(shoppingCart2);
        doNothing().when(user).setAproved(anyBoolean());
        doNothing().when(user).setBillingAddress((Address) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFirstname((String) any());
        doNothing().when(user).setFollowing(anyBoolean());
        doNothing().when(user).setId((Long) any());
        doNothing().when(user).setLastname((String) any());
        doNothing().when(user).setOrders((List<Order>) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setProducts((List<Product>) any());
        doNothing().when(user).setRoles((List<Role>) any());
        doNothing().when(user).setShippingAddress((Address) any());
        doNothing().when(user).setShoppingCart((ShoppingCart) any());
        doNothing().when(user).setUsername((String) any());
        user.setAproved(true);
        user.setBillingAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstname("Jane");
        user.setFollowing(true);
        user.setId(1L);
        user.setLastname("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart1);
        user.setUsername("janedoe");
        shoppingCartServiceImpl.clearShoppingCart(1L);
    }

    /**
     * Method under test: {@link ShoppingCartServiceImpl#viewCartDetail(long)}
     */
    @Test
    void testViewCartDetail() {
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
        user.setFirstname("Jane");
        user.setFollowing(true);
        user.setId(1L);
        user.setLastname("Doe");
        user.setOrders(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setProducts(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setShippingAddress(address1);
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");
        when(userRepo.getUserById(anyLong())).thenReturn(user);
        assertSame(shoppingCart, shoppingCartServiceImpl.viewCartDetail(1L));
        verify(userRepo).getUserById(anyLong());
    }

    /**
     * Method under test: {@link ShoppingCartServiceImpl#editQuantity(long, String, Integer)}
     */
    @Test
    void testEditQuantity() {
        assertNull(shoppingCartServiceImpl.editQuantity(1L, "Product Name", 10));
    }
}

