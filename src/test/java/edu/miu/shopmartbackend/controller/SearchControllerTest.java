package edu.miu.shopmartbackend.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import edu.miu.shopmartbackend.model.dto.OrderDto;
import edu.miu.shopmartbackend.model.dto.ProductDto;
import edu.miu.shopmartbackend.model.dto.UserDto;
import edu.miu.shopmartbackend.service.SearchService;

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

@ContextConfiguration(classes = {SearchController.class})
@ExtendWith(SpringExtension.class)
class SearchControllerTest {
    @Autowired
    private SearchController searchController;

    @MockBean
    private SearchService searchService;

    /**
     * Method under test: {@link SearchController#getAllUsers()}
     */
    @Test
    void testGetAllUsers() throws Exception {
        when(searchService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/search/users");
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SearchController#getAllUsers()}
     */
    @Test
    void testGetAllUsers2() throws Exception {
        when(searchService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/search/users");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SearchController#getAllSellers()}
     */
    @Test
    void testGetAllSellers() throws Exception {
        when(searchService.getAllSellers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/search/sellers");
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SearchController#getAllSellers()}
     */
    @Test
    void testGetAllSellers2() throws Exception {
        when(searchService.getAllSellers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/search/sellers");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SearchController#getAllBuyers()}
     */
    @Test
    void testGetAllBuyers() throws Exception {
        when(searchService.getAllBuyers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/search/buyers");
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SearchController#getAllBuyers()}
     */
    @Test
    void testGetAllBuyers2() throws Exception {
        when(searchService.getAllBuyers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/search/buyers");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SearchController#getUserByUsername(String)}
     */
    @Test
    void testGetUserByUsername() throws Exception {
        when(searchService.getUserByUsername((String) any())).thenReturn(new UserDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/search/name")
                .param("username", "foo");
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"firstName\":null,\"lastName\":null,\"username\":null,\"password\":null,\"email\":null,\"aproved"
                                        + "\":false}"));
    }

    /**
     * Method under test: {@link SearchController#getUserById(long)}
     */
    @Test
    void testGetUserById() throws Exception {
        when(searchService.getUserById(anyLong())).thenReturn(new UserDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/search/user/{id}", 1L);
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"firstName\":null,\"lastName\":null,\"username\":null,\"password\":null,\"email\":null,\"aproved"
                                        + "\":false}"));
    }

    /**
     * Method under test: {@link SearchController#getAllProducts()}
     */
    @Test
    void testGetAllProducts() throws Exception {
        when(searchService.getAllProducts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/search/products");
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SearchController#getAllProducts()}
     */
    @Test
    void testGetAllProducts2() throws Exception {
        when(searchService.getAllProducts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/search/products");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SearchController#getProductById(long)}
     */
    @Test
    void testGetProductById() throws Exception {
        when(searchService.getProductById(anyLong())).thenReturn(new ProductDto(1L, "Product Name", 10.0d,
                "The characteristics of someone or something", "https://example.org/example"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/search/product/{id}", 1L);
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"imageUrl\":\"https://example.org/example\",\"product_id\":1,\"product_name\":\"Product Name\",\"product_price"
                                        + "\":10.0,\"product_description\":\"The characteristics of someone or something\"}"));
    }

    /**
     * Method under test: {@link SearchController#findOrderById(long)}
     */
    @Test
    void testFindOrderById() throws Exception {
        when(searchService.findOrderById(anyLong())).thenReturn(new OrderDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/search/{order_id}", 1L);
        MockMvcBuilders.standaloneSetup(searchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"order_id\":0,\"order_date\":null,\"order_status\":null,\"total_price\":0.0,\"buyer\":null}"));
    }
}

