package edu.miu.shopmartbackend.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.shopmartbackend.model.Address;
import edu.miu.shopmartbackend.service.AddressService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AddressController.class})
@ExtendWith(SpringExtension.class)
class AddressControllerTest {
    @Autowired
    private AddressController addressController;

    @MockBean
    private AddressService addressService;

    /**
     * Method under test: {@link AddressController#addBuyerBillingAddress(long, Address)}
     */
    @Test
    void testAddBuyerBillingAddress() throws Exception {
        doNothing().when(addressService).addBuyerBillingAddress(anyLong(), (Address) any());

        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreet("Street");
        address.setZipcode("21654");
        String content = (new ObjectMapper()).writeValueAsString(address);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/address/{buyer_id}/billing", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AddressController#addBuyerShippingAddress(long, Address)}
     */
    @Test
    void testAddBuyerShippingAddress() throws Exception {
        doNothing().when(addressService).addBuyerShippingAddress(anyLong(), (Address) any());

        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreet("Street");
        address.setZipcode("21654");
        String content = (new ObjectMapper()).writeValueAsString(address);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/address/{buyer_id}/shipping", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AddressController#getBuyerBillingAddress(long)}
     */
    @Test
    void testGetBuyerBillingAddress() throws Exception {
        when(addressService.getBuyerBillingAddress(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/address/{buyer_id}/billing",
                1L);
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AddressController#getBuyerBillingAddress(long)}
     */
    @Test
    void testGetBuyerBillingAddress2() throws Exception {
        when(addressService.getBuyerBillingAddress(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/address/{buyer_id}/billing", 1L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AddressController#getBuyerShippingAddress(long)}
     */
    @Test
    void testGetBuyerShippingAddress() throws Exception {
        when(addressService.getBuyerShippingAddress(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/address/{buyer_id}/shipping",
                1L);
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AddressController#getBuyerShippingAddress(long)}
     */
    @Test
    void testGetBuyerShippingAddress2() throws Exception {
        when(addressService.getBuyerShippingAddress(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/address/{buyer_id}/shipping", 1L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(addressController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

