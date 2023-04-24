package edu.miu.shopmartbackend.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.shopmartbackend.enums.OrderStatus;
import edu.miu.shopmartbackend.model.Address;
import edu.miu.shopmartbackend.model.Order;
import edu.miu.shopmartbackend.model.Product;
import edu.miu.shopmartbackend.model.ShoppingCart;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.model.dto.OrderDto;
import edu.miu.shopmartbackend.model.dto.ProductDto;
import edu.miu.shopmartbackend.model.dto.UserDto;
import edu.miu.shopmartbackend.repo.OrderRepo;
import edu.miu.shopmartbackend.repo.ProductRepo;
import edu.miu.shopmartbackend.repo.UserRepo;
import edu.miu.shopmartbackend.util.ListMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SearchServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SearchServiceImplTest {
    @MockBean
    private ListMapper<User, UserDto> listMapper;

    @MockBean
    private ListMapper<Product, ProductDto> listMapper1;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private OrderRepo orderRepo;

    @MockBean
    private ProductRepo productRepo;

    @Autowired
    private SearchServiceImpl searchServiceImpl;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link SearchServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers() {
        ArrayList<Object> objectList = new ArrayList<>();
        when((List<Object>) listMapper.mapList((List<User>) any(), (UserDto) any())).thenReturn(objectList);
        when(userRepo.findAll()).thenReturn(new ArrayList<>());
        List<UserDto> actualAllUsers = searchServiceImpl.getAllUsers();
        assertSame(objectList, actualAllUsers);
        assertTrue(actualAllUsers.isEmpty());
        verify(listMapper).mapList((List<User>) any(), (UserDto) any());
        verify(userRepo).findAll();
    }

    /**
     * Method under test: {@link SearchServiceImpl#getAllSellers()}
     */
    @Test
    void testGetAllSellers() {
        ArrayList<Object> objectList = new ArrayList<>();
        when((List<Object>) listMapper.mapList((List<User>) any(), (UserDto) any())).thenReturn(objectList);
        when(userRepo.findAllByRole((String) any())).thenReturn(new ArrayList<>());
        List<UserDto> actualAllSellers = searchServiceImpl.getAllSellers();
        assertSame(objectList, actualAllSellers);
        assertTrue(actualAllSellers.isEmpty());
        verify(listMapper).mapList((List<User>) any(), (UserDto) any());
        verify(userRepo).findAllByRole((String) any());
    }

    /**
     * Method under test: {@link SearchServiceImpl#getAllBuyers()}
     */
    @Test
    void testGetAllBuyers() {
        ArrayList<Object> objectList = new ArrayList<>();
        when((List<Object>) listMapper.mapList((List<User>) any(), (UserDto) any())).thenReturn(objectList);
        when(userRepo.findAllByRole((String) any())).thenReturn(new ArrayList<>());
        List<UserDto> actualAllBuyers = searchServiceImpl.getAllBuyers();
        assertSame(objectList, actualAllBuyers);
        assertTrue(actualAllBuyers.isEmpty());
        verify(listMapper).mapList((List<User>) any(), (UserDto) any());
        verify(userRepo).findAllByRole((String) any());
    }

    /**
     * Method under test: {@link SearchServiceImpl#getUserByUsername(String)}
     */
    @Test
    void testGetUserByUsername() {
        UserDto userDto = new UserDto();
        when(modelMapper.map((Object) any(), (Class<UserDto>) any())).thenReturn(userDto);

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
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findByUsername((String) any())).thenReturn(ofResult);
        assertSame(userDto, searchServiceImpl.getUserByUsername("janedoe"));
        verify(modelMapper).map((Object) any(), (Class<UserDto>) any());
        verify(userRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link SearchServiceImpl#getUserByUsername(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserByUsername2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at edu.miu.shopmartbackend.service.impl.SearchServiceImpl.getUserByUsername(SearchServiceImpl.java:54)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<UserDto>) any())).thenReturn(new UserDto());
        when(userRepo.findByUsername((String) any())).thenReturn(Optional.empty());
        searchServiceImpl.getUserByUsername("janedoe");
    }

    /**
     * Method under test: {@link SearchServiceImpl#getAllProducts()}
     */
    @Test
    void testGetAllProducts() {
        ArrayList<Object> objectList = new ArrayList<>();
        when((List<Object>) listMapper1.mapList((List<Product>) any(), (ProductDto) any())).thenReturn(objectList);
        when(productRepo.findAll()).thenReturn(new ArrayList<>());
        List<ProductDto> actualAllProducts = searchServiceImpl.getAllProducts();
        assertSame(objectList, actualAllProducts);
        assertTrue(actualAllProducts.isEmpty());
        verify(listMapper1).mapList((List<Product>) any(), (ProductDto) any());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link SearchServiceImpl#getUserById(long)}
     */
    @Test
    void testGetUserById() {
        UserDto userDto = new UserDto();
        when(modelMapper.map((Object) any(), (Class<UserDto>) any())).thenReturn(userDto);

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
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findById((Long) any())).thenReturn(ofResult);
        assertSame(userDto, searchServiceImpl.getUserById(1L));
        verify(modelMapper).map((Object) any(), (Class<UserDto>) any());
        verify(userRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link SearchServiceImpl#getUserById(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at edu.miu.shopmartbackend.service.impl.SearchServiceImpl.getUserById(SearchServiceImpl.java:64)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<UserDto>) any())).thenReturn(new UserDto());
        when(userRepo.findById((Long) any())).thenReturn(Optional.empty());
        searchServiceImpl.getUserById(1L);
    }

    /**
     * Method under test: {@link SearchServiceImpl#getProductById(long)}
     */
    @Test
    void testGetProductById() {
        ProductDto productDto = new ProductDto(1L, "Product Name", 10.0d, "The characteristics of someone or something",
                "https://example.org/example");

        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(productDto);

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

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImageUrl("https://example.org/example");
        product.setPrice(10.0d);
        product.setProductName("Product Name");
        product.setPurchased(true);
        product.setSeller(user);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.findById((Long) any())).thenReturn(ofResult);
        assertSame(productDto, searchServiceImpl.getProductById(1L));
        verify(modelMapper).map((Object) any(), (Class<ProductDto>) any());
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link SearchServiceImpl#getProductById(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetProductById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at edu.miu.shopmartbackend.service.impl.SearchServiceImpl.getProductById(SearchServiceImpl.java:70)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<ProductDto>) any())).thenReturn(new ProductDto(1L, "Product Name",
                10.0d, "The characteristics of someone or something", "https://example.org/example"));
        when(productRepo.findById((Long) any())).thenReturn(Optional.empty());
        searchServiceImpl.getProductById(1L);
    }

    /**
     * Method under test: {@link SearchServiceImpl#findOrderById(long)}
     */
    @Test
    void testFindOrderById() {
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
        assertSame(orderDto, searchServiceImpl.findOrderById(1L));
        verify(modelMapper).map((Object) any(), (Class<OrderDto>) any());
        verify(orderRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link SearchServiceImpl#findOrderById(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindOrderById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at edu.miu.shopmartbackend.service.impl.SearchServiceImpl.findOrderById(SearchServiceImpl.java:75)
        //   See https://diff.blue/R013 to resolve this issue.

        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<OrderDto>) any())).thenReturn(new OrderDto());
        when(orderRepo.findById((Long) any())).thenReturn(Optional.empty());
        searchServiceImpl.findOrderById(1L);
    }
}

