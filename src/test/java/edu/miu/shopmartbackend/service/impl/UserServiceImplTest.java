package edu.miu.shopmartbackend.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.shopmartbackend.model.Address;
import edu.miu.shopmartbackend.model.Order;
import edu.miu.shopmartbackend.model.Product;
import edu.miu.shopmartbackend.model.Role;
import edu.miu.shopmartbackend.model.ShoppingCart;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.model.dto.UserDto;
import edu.miu.shopmartbackend.model.dto.UsernamePassDto;
import edu.miu.shopmartbackend.repo.RoleRepo;
import edu.miu.shopmartbackend.repo.UserRepo;
import edu.miu.shopmartbackend.util.ListMapper;

import java.io.IOException;
import java.io.PipedOutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.catalina.connector.Response;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.DelegatingServletOutputStream;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private ListMapper<User, UserDto> listMapper;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepo roleRepo;

    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
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
        when(userRepo.findByUsername((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = userServiceImpl.loadUserByUsername("janedoe");
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
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
        Address shippingAddress = new Address();
        Address billingAddress = new Address();
        ShoppingCart shoppingCart1 = new ShoppingCart();
        ArrayList<Role> roles = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();

        User user = new User(1L, "Jane", "Doe", "janedoe", "iloveyou", "jane.doe@example.org", true, true,
                shippingAddress, billingAddress, shoppingCart1, roles, orders, new ArrayList<>());
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
        when(userRepo.findByUsername((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = userServiceImpl.loadUserByUsername("janedoe");
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepo).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Cannot pass null or empty values to constructor
        //       at edu.miu.shopmartbackend.service.impl.UserServiceImpl.loadUserByUsername(UserServiceImpl.java:66)
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
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("");
        when(user.getRoles()).thenReturn(new ArrayList<>());
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
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findByUsername((String) any())).thenReturn(ofResult);
        userServiceImpl.loadUserByUsername("janedoe");
    }

    /**
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername4() throws UsernameNotFoundException {
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

        Role role = new Role();
        role.setId(1L);
        role.setRole("Role");

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getRoles()).thenReturn(roleList);
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
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findByUsername((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = userServiceImpl.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepo).findByUsername((String) any());
        verify(user).getPassword();
        verify(user).getUsername();
        verify(user).getRoles();
        verify(user).setAproved(anyBoolean());
        verify(user).setBillingAddress((Address) any());
        verify(user).setEmail((String) any());
        verify(user).setFirstname((String) any());
        verify(user).setFollowing(anyBoolean());
        verify(user).setId((Long) any());
        verify(user).setLastname((String) any());
        verify(user).setOrders((List<Order>) any());
        verify(user).setPassword((String) any());
        verify(user).setProducts((List<Product>) any());
        verify(user).setRoles((List<Role>) any());
        verify(user).setShippingAddress((Address) any());
        verify(user).setShoppingCart((ShoppingCart) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername5() throws UsernameNotFoundException {
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

        Role role = new Role();
        role.setId(1L);
        role.setRole("Role");

        Role role1 = new Role();
        role1.setId(2L);
        role1.setRole("Role");

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role1);
        roleList.add(role);
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getRoles()).thenReturn(roleList);
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
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findByUsername((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = userServiceImpl.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepo).findByUsername((String) any());
        verify(user).getPassword();
        verify(user).getUsername();
        verify(user).getRoles();
        verify(user).setAproved(anyBoolean());
        verify(user).setBillingAddress((Address) any());
        verify(user).setEmail((String) any());
        verify(user).setFirstname((String) any());
        verify(user).setFollowing(anyBoolean());
        verify(user).setId((Long) any());
        verify(user).setLastname((String) any());
        verify(user).setOrders((List<Order>) any());
        verify(user).setPassword((String) any());
        verify(user).setProducts((List<Product>) any());
        verify(user).setRoles((List<Role>) any());
        verify(user).setShippingAddress((Address) any());
        verify(user).setShoppingCart((ShoppingCart) any());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoadUserByUsername6() throws UsernameNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at edu.miu.shopmartbackend.service.impl.UserServiceImpl.loadUserByUsername(UserServiceImpl.java:60)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepo.findByUsername((String) any())).thenReturn(Optional.empty());

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
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getUsername()).thenReturn("janedoe");
        when(user.getRoles()).thenReturn(new ArrayList<>());
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
        user.setShoppingCart(shoppingCart);
        user.setUsername("janedoe");
        userServiceImpl.loadUserByUsername("janedoe");
    }

    /**
     * Method under test: {@link UserServiceImpl#addUser(UsernamePassDto)}
     */
    @Test
    void testAddUser() {
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
        when(modelMapper.map((Object) any(), (Class<User>) any())).thenReturn(user);

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
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        UsernamePassDto usernamePassDto = new UsernamePassDto("janedoe", "iloveyou");

        userServiceImpl.addUser(usernamePassDto);
        verify(modelMapper).map((Object) any(), (Class<User>) any());
        verify(userRepo).save((User) any());
        verify(passwordEncoder).encode((CharSequence) any());
        assertEquals("secret", usernamePassDto.getPassword());
    }

    /**
     * Method under test: {@link UserServiceImpl#addUser(UsernamePassDto)}
     */
    @Test
    void testAddUser2() {
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
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<User>) any())).thenReturn(user);

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
        when(passwordEncoder.encode((CharSequence) any())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> userServiceImpl.addUser(new UsernamePassDto("janedoe", "iloveyou")));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(long)}
     */
    @Test
    void testDeleteUser() {
        doNothing().when(userRepo).deleteById((Long) any());
        userServiceImpl.deleteUser(1L);
        verify(userRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(long)}
     */
    @Test
    void testDeleteUser2() {
        doThrow(new RuntimeException()).when(userRepo).deleteById((Long) any());
        assertThrows(RuntimeException.class, () -> userServiceImpl.deleteUser(1L));
        verify(userRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#saveRole(Role)}
     */
    @Test
    void testSaveRole() {
        Role role = new Role();
        role.setId(1L);
        role.setRole("Role");
        when(roleRepo.save((Role) any())).thenReturn(role);

        Role role1 = new Role();
        role1.setId(1L);
        role1.setRole("Role");
        assertSame(role, userServiceImpl.saveRole(role1));
        verify(roleRepo).save((Role) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#saveRole(Role)}
     */
    @Test
    void testSaveRole2() {
        when(roleRepo.save((Role) any())).thenThrow(new RuntimeException());

        Role role = new Role();
        role.setId(1L);
        role.setRole("Role");
        assertThrows(RuntimeException.class, () -> userServiceImpl.saveRole(role));
        verify(roleRepo).save((Role) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#addRoleToUser(String, String)}
     */
    @Test
    void testAddRoleToUser() {
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
        when(userRepo.findByUsername((String) any())).thenReturn(ofResult);

        Role role = new Role();
        role.setId(1L);
        role.setRole("Role");
        when(roleRepo.findByRole((String) any())).thenReturn(role);
        userServiceImpl.addRoleToUser("janedoe", "Role");
        verify(userRepo).findByUsername((String) any());
        verify(roleRepo).findByRole((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#addRoleToUser(String, String)}
     */
    @Test
    void testAddRoleToUser2() {
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
        when(userRepo.findByUsername((String) any())).thenReturn(ofResult);
        when(roleRepo.findByRole((String) any())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> userServiceImpl.addRoleToUser("janedoe", "Role"));
        verify(userRepo).findByUsername((String) any());
        verify(roleRepo).findByRole((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#addRoleToUser(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddRoleToUser3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at edu.miu.shopmartbackend.service.impl.UserServiceImpl.addRoleToUser(UserServiceImpl.java:89)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepo.findByUsername((String) any())).thenReturn(Optional.empty());

        Role role = new Role();
        role.setId(1L);
        role.setRole("Role");
        when(roleRepo.findByRole((String) any())).thenReturn(role);
        userServiceImpl.addRoleToUser("janedoe", "Role");
    }

    /**
     * Method under test: {@link UserServiceImpl#approveSeller(long)}
     */
    @Test
    void testApproveSeller() {
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
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<User>) any())).thenReturn(user);
        when(userRepo.getUserById(anyLong())).thenThrow(new UsernameNotFoundException("Msg"));
        when(userRepo.save((User) any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class, () -> userServiceImpl.approveSeller(1L));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(userRepo).getUserById(anyLong());
    }

    /**
     * Method under test: {@link UserServiceImpl#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        assertThrows(RuntimeException.class, () -> userServiceImpl.refreshToken(request, new Response()));
    }

    /**
     * Method under test: {@link UserServiceImpl#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRefreshToken2() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.UserServiceImpl.refreshToken(UserServiceImpl.java:105)
        //   See https://diff.blue/R013 to resolve this issue.

        userServiceImpl.refreshToken(null, new Response());
    }

    /**
     * Method under test: {@link UserServiceImpl#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken3() throws IOException {
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("https://example.org/example");
        assertThrows(RuntimeException.class,
                () -> userServiceImpl.refreshToken(defaultMultipartHttpServletRequest, new Response()));
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRefreshToken4() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.apache.catalina.connector.Response.isCommitted(Response.java:619)
        //       at org.apache.catalina.connector.Response.setHeader(Response.java:1436)
        //       at edu.miu.shopmartbackend.service.impl.UserServiceImpl.refreshToken(UserServiceImpl.java:135)
        //   See https://diff.blue/R013 to resolve this issue.

        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        userServiceImpl.refreshToken(defaultMultipartHttpServletRequest, new Response());
    }

    /**
     * Method under test: {@link UserServiceImpl#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRefreshToken5() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.apache.catalina.connector.Response.isCommitted(Response.java:619)
        //       at org.apache.catalina.connector.Response.setHeader(Response.java:1436)
        //       at javax.servlet.http.HttpServletResponseWrapper.setHeader(HttpServletResponseWrapper.java:165)
        //       at edu.miu.shopmartbackend.service.impl.UserServiceImpl.refreshToken(UserServiceImpl.java:135)
        //   See https://diff.blue/R013 to resolve this issue.

        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        userServiceImpl.refreshToken(defaultMultipartHttpServletRequest, new HttpServletResponseWrapper(new Response()));
    }

    /**
     * Method under test: {@link UserServiceImpl#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken6() throws IOException {
        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        userServiceImpl.refreshToken(defaultMultipartHttpServletRequest, mockHttpServletResponse);
        verify(defaultMultipartHttpServletRequest).getHeader((String) any());
        assertEquals(403, mockHttpServletResponse.getStatus());
        assertNull(mockHttpServletResponse.getRedirectedUrl());
        assertEquals(2, mockHttpServletResponse.getHeaderNames().size());
        assertEquals("application/json", mockHttpServletResponse.getContentType());
    }

    /**
     * Method under test: {@link UserServiceImpl#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRefreshToken7() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.io.IOException: Pipe not connected
        //       at java.io.PipedOutputStream.write(PipedOutputStream.java:120)
        //       at java.io.OutputStream.write(OutputStream.java:157)
        //       at com.fasterxml.jackson.core.json.UTF8JsonGenerator._flushBuffer(UTF8JsonGenerator.java:2171)
        //       at com.fasterxml.jackson.core.json.UTF8JsonGenerator.close(UTF8JsonGenerator.java:1214)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4573)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValue(ObjectMapper.java:3780)
        //       at edu.miu.shopmartbackend.service.impl.UserServiceImpl.refreshToken(UserServiceImpl.java:141)
        //   See https://diff.blue/R013 to resolve this issue.

        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        Response response = mock(Response.class);
        when(response.getOutputStream()).thenReturn(new DelegatingServletOutputStream(new PipedOutputStream()));
        doNothing().when(response).setContentType((String) any());
        doNothing().when(response).setHeader((String) any(), (String) any());
        doNothing().when(response).setStatus(anyInt());
        userServiceImpl.refreshToken(defaultMultipartHttpServletRequest, response);
    }

    /**
     * Method under test: {@link UserServiceImpl#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRefreshToken8() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: argument "out" is null
        //       at com.fasterxml.jackson.databind.ObjectMapper._assertNotNull(ObjectMapper.java:4829)
        //       at com.fasterxml.jackson.databind.ObjectMapper.createGenerator(ObjectMapper.java:1172)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValue(ObjectMapper.java:3780)
        //       at edu.miu.shopmartbackend.service.impl.UserServiceImpl.refreshToken(UserServiceImpl.java:141)
        //   See https://diff.blue/R013 to resolve this issue.

        DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = mock(
                DefaultMultipartHttpServletRequest.class);
        when(defaultMultipartHttpServletRequest.getHeader((String) any())).thenReturn("Bearer ");
        Response response = mock(Response.class);
        when(response.getOutputStream()).thenReturn(null);
        doNothing().when(response).setContentType((String) any());
        doNothing().when(response).setHeader((String) any(), (String) any());
        doNothing().when(response).setStatus(anyInt());
        userServiceImpl.refreshToken(defaultMultipartHttpServletRequest, response);
    }

    /**
     * Method under test: {@link UserServiceImpl#registerUser(UserDto)}
     */
    @Test
    void testRegisterUser() {
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
        when(modelMapper.map((Object) any(), (Class<User>) any())).thenReturn(user);

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
        userServiceImpl.registerUser(new UserDto());
        verify(modelMapper).map((Object) any(), (Class<User>) any());
        verify(userRepo).save((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#registerUser(UserDto)}
     */
    @Test
    void testRegisterUser2() {
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
        when(modelMapper.map((Object) any(), (Class<User>) any())).thenReturn(user);
        when(userRepo.save((User) any())).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> userServiceImpl.registerUser(new UserDto()));
        verify(modelMapper).map((Object) any(), (Class<User>) any());
        verify(userRepo).save((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#approveBuyer(long)}
     */
    @Test
    void testApproveBuyer() {
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
        when(userRepo.getUserById(anyLong())).thenReturn(user);
        assertSame(userDto, userServiceImpl.approveBuyer(1L));
        verify(modelMapper).map((Object) any(), (Class<UserDto>) any());
        verify(userRepo).getUserById(anyLong());
        verify(userRepo).save((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#approveBuyer(long)}
     */
    @Test
    void testApproveBuyer2() {
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<UserDto>) any())).thenReturn(new UserDto());

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
        when(userRepo.save((User) any())).thenThrow(new RuntimeException());
        when(userRepo.getUserById(anyLong())).thenReturn(user);
        assertThrows(RuntimeException.class, () -> userServiceImpl.approveBuyer(1L));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(userRepo).getUserById(anyLong());
        verify(userRepo).save((User) any());
    }
}

