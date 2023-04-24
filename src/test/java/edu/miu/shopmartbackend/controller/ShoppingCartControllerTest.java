package edu.miu.shopmartbackend.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import edu.miu.shopmartbackend.model.ShoppingCart;
import edu.miu.shopmartbackend.service.ShoppingCartService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ShoppingCartController.class})
@ExtendWith(SpringExtension.class)
class ShoppingCartControllerTest {
    @Autowired
    private ShoppingCartController shoppingCartController;

    @MockBean
    private ShoppingCartService shoppingCartService;

    /**
     * Method under test: {@link ShoppingCartController#createShoppingCart(long)}
     */
    @Test
    void testCreateShoppingCart() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());
        when(shoppingCartService.createShoppingCart(anyLong())).thenReturn(shoppingCart);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/carts/{buyer_id}", 1L);
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"products\":[]}"));
    }

    /**
     * Method under test: {@link ShoppingCartController#getShoppingCartById(long)}
     */
    @Test
    void testGetShoppingCartById() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());
        when(shoppingCartService.getShoppingCartById(anyLong())).thenReturn(shoppingCart);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/carts/{cart_id}/cart", 1L);
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"products\":[]}"));
    }

    /**
     * Method under test: {@link ShoppingCartController#addProductToShoppingCart(long, long)}
     */
    @Test
    void testAddProductToShoppingCart() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());
        when(shoppingCartService.addProductToShoppingCart(anyLong(), anyLong())).thenReturn(shoppingCart);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/carts/{buyer_id}/{product_id}",
                1L, 1L);
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"products\":[]}"));
    }

    /**
     * Method under test: {@link ShoppingCartController#deleteShoppingCart(long)}
     */
    @Test
    void testDeleteShoppingCart() throws Exception {
        doNothing().when(shoppingCartService).deleteShoppingCart(anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/carts/{buyer_id}", 1L);
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ShoppingCartController#deleteShoppingCart(long)}
     */
    @Test
    void testDeleteShoppingCart2() throws Exception {
        doNothing().when(shoppingCartService).deleteShoppingCart(anyLong());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/carts/{buyer_id}", 1L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ShoppingCartController#deleteProductByIdFromCart(long, long)}
     */
    @Test
    void testDeleteProductByIdFromCart() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());
        when(shoppingCartService.deleteProductByIdFromCart(anyLong(), anyLong())).thenReturn(shoppingCart);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/carts/{buyer_id}/{product_id}", 1L, 1L);
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"products\":[]}"));
    }

    /**
     * Method under test: {@link ShoppingCartController#editQuantity(long, String, Integer)}
     */
    @Test
    void testEditQuantity() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());
        when(shoppingCartService.editQuantity(anyLong(), (String) any(), (Integer) any())).thenReturn(shoppingCart);
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/api/v1/carts/edit/{userId}", 1L);
        MockHttpServletRequestBuilder requestBuilder = putResult.param("number", String.valueOf(1))
                .param("productName", "foo");
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"products\":[]}"));
    }

    /**
     * Method under test: {@link ShoppingCartController#clearShopingCart(long)}
     */
    @Test
    void testClearShopingCart() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());
        when(shoppingCartService.clearShoppingCart(anyLong())).thenReturn(shoppingCart);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/api/v1/carts/{user_id}", 1L);
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"products\":[]}"));
    }

    /**
     * Method under test: {@link ShoppingCartController#viewShoppingCartDetails(long)}
     */
    @Test
    void testViewShoppingCartDetails() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setProducts(new ArrayList<>());
        when(shoppingCartService.viewCartDetail(anyLong())).thenReturn(shoppingCart);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/carts/{userId}/viewCartDetail",
                1L);
        MockMvcBuilders.standaloneSetup(shoppingCartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"products\":[]}"));
    }
}

