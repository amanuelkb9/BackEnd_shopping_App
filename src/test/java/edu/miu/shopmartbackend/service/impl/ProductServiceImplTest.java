package edu.miu.shopmartbackend.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.shopmartbackend.model.Address;
import edu.miu.shopmartbackend.model.Product;
import edu.miu.shopmartbackend.model.ShoppingCart;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.model.dto.ProductDto;
import edu.miu.shopmartbackend.repo.OrderRepo;
import edu.miu.shopmartbackend.repo.ProductRepo;
import edu.miu.shopmartbackend.repo.UserRepo;
import edu.miu.shopmartbackend.util.ListMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private ListMapper<Product, ProductDto> listMapper;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private OrderRepo orderRepo;

    @MockBean
    private ProductRepo productRepo;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link ProductServiceImpl#saveProduct(Product, long)}
     */
    @Test
    void testSaveProduct() {
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
        when(productRepo.save((Product) any())).thenReturn(product);

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
        Optional<User> ofResult = Optional.of(user1);
        when(userRepo.findById((Long) any())).thenReturn(ofResult);

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

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setId(1L);
        shoppingCart2.setProducts(new ArrayList<>());

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
        user2.setShoppingCart(shoppingCart2);
        user2.setUsername("janedoe");

        Product product1 = new Product();
        product1.setDescription("The characteristics of someone or something");
        product1.setId(1L);
        product1.setImageUrl("https://example.org/example");
        product1.setPrice(10.0d);
        product1.setProductName("Product Name");
        product1.setPurchased(true);
        product1.setSeller(user2);
        productServiceImpl.saveProduct(product1, 1L);
        verify(productRepo).save((Product) any());
        verify(userRepo).findById((Long) any());
        assertEquals(user2, product1.getSeller());
    }

    /**
     * Method under test: {@link ProductServiceImpl#saveProduct(Product, long)}
     */
    @Test
    void testSaveProduct2() {
        when(productRepo.save((Product) any())).thenThrow(new RuntimeException());

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

        Product product = new Product();
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImageUrl("https://example.org/example");
        product.setPrice(10.0d);
        product.setProductName("Product Name");
        product.setPurchased(true);
        product.setSeller(user1);
        assertThrows(RuntimeException.class, () -> productServiceImpl.saveProduct(product, 1L));
        verify(productRepo).save((Product) any());
        verify(userRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct() {
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
        when(modelMapper.map((Object) any(), (Class<Product>) any())).thenReturn(product);

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

        Product product1 = new Product();
        product1.setDescription("The characteristics of someone or something");
        product1.setId(1L);
        product1.setImageUrl("https://example.org/example");
        product1.setPrice(10.0d);
        product1.setProductName("Product Name");
        product1.setPurchased(true);
        product1.setSeller(user1);
        Optional<Product> ofResult = Optional.of(product1);
        when(productRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> productServiceImpl.deleteProduct(1L));
        verify(modelMapper).map((Object) any(), (Class<Product>) any());
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct2() {
        when(modelMapper.map((Object) any(), (Class<Product>) any())).thenThrow(new RuntimeException());

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
        assertThrows(RuntimeException.class, () -> productServiceImpl.deleteProduct(1L));
        verify(modelMapper).map((Object) any(), (Class<Product>) any());
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct3() {
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
        Product product = mock(Product.class);
        when(product.isPurchased()).thenReturn(false);
        doNothing().when(product).setDescription((String) any());
        doNothing().when(product).setId((Long) any());
        doNothing().when(product).setImageUrl((String) any());
        doNothing().when(product).setPrice(anyDouble());
        doNothing().when(product).setProductName((String) any());
        doNothing().when(product).setPurchased(anyBoolean());
        doNothing().when(product).setSeller((User) any());
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImageUrl("https://example.org/example");
        product.setPrice(10.0d);
        product.setProductName("Product Name");
        product.setPurchased(true);
        product.setSeller(user);
        when(modelMapper.map((Object) any(), (Class<Product>) any())).thenReturn(product);

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

        Product product1 = new Product();
        product1.setDescription("The characteristics of someone or something");
        product1.setId(1L);
        product1.setImageUrl("https://example.org/example");
        product1.setPrice(10.0d);
        product1.setProductName("Product Name");
        product1.setPurchased(true);
        product1.setSeller(user1);
        Optional<Product> ofResult = Optional.of(product1);
        doNothing().when(productRepo).deleteById((Long) any());
        when(productRepo.findById((Long) any())).thenReturn(ofResult);
        productServiceImpl.deleteProduct(1L);
        verify(modelMapper).map((Object) any(), (Class<Product>) any());
        verify(product).isPurchased();
        verify(product).setDescription((String) any());
        verify(product).setId((Long) any());
        verify(product).setImageUrl((String) any());
        verify(product).setPrice(anyDouble());
        verify(product).setProductName((String) any());
        verify(product).setPurchased(anyBoolean());
        verify(product).setSeller((User) any());
        verify(productRepo).findById((Long) any());
        verify(productRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateProduct(ProductDto, long)}
     */
    @Test
    void testUpdateProduct() {
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
        when(modelMapper.map((Object) any(), (Class<Product>) any())).thenReturn(product);

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

        Product product1 = new Product();
        product1.setDescription("The characteristics of someone or something");
        product1.setId(1L);
        product1.setImageUrl("https://example.org/example");
        product1.setPrice(10.0d);
        product1.setProductName("Product Name");
        product1.setPurchased(true);
        product1.setSeller(user1);
        Optional<Product> ofResult = Optional.of(product1);

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

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setId(1L);
        shoppingCart2.setProducts(new ArrayList<>());

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
        user2.setShoppingCart(shoppingCart2);
        user2.setUsername("janedoe");

        Product product2 = new Product();
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setImageUrl("https://example.org/example");
        product2.setPrice(10.0d);
        product2.setProductName("Product Name");
        product2.setPurchased(true);
        product2.setSeller(user2);
        when(productRepo.save((Product) any())).thenReturn(product2);
        when(productRepo.findById((Long) any())).thenReturn(ofResult);
        productServiceImpl.updateProduct(new ProductDto(1L, "Product Name", 10.0d,
                "The characteristics of someone or something", "https://example.org/example"), 1L);
        verify(modelMapper).map((Object) any(), (Class<Product>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateProduct(ProductDto, long)}
     */
    @Test
    void testUpdateProduct2() {
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
        when(modelMapper.map((Object) any(), (Class<Product>) any())).thenReturn(product);

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

        Product product1 = new Product();
        product1.setDescription("The characteristics of someone or something");
        product1.setId(1L);
        product1.setImageUrl("https://example.org/example");
        product1.setPrice(10.0d);
        product1.setProductName("Product Name");
        product1.setPurchased(true);
        product1.setSeller(user1);
        Optional<Product> ofResult = Optional.of(product1);
        when(productRepo.save((Product) any())).thenThrow(new RuntimeException());
        when(productRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> productServiceImpl.updateProduct(new ProductDto(1L, "Product Name",
                10.0d, "The characteristics of someone or something", "https://example.org/example"), 1L));
        verify(modelMapper).map((Object) any(), (Class<Product>) any());
        verify(productRepo).save((Product) any());
        verify(productRepo).findById((Long) any());
    }
}

