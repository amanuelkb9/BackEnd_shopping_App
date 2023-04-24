package edu.miu.shopmartbackend.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.shopmartbackend.model.dto.OrderDto;
import edu.miu.shopmartbackend.model.dto.PaymentDto;
import edu.miu.shopmartbackend.service.OrderService;
import edu.miu.shopmartbackend.service.SearchService;
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

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;

    @MockBean
    private SearchService searchService;

    /**
     * Method under test: {@link OrderController#shipOrder(long)}
     */
    @Test
    void testShipOrder() throws Exception {
        when(orderService.shipOrder(anyLong())).thenReturn(new OrderDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/api/v1/orders/{orderId}/ship", 1L);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"order_id\":0,\"order_date\":null,\"order_status\":null,\"total_price\":0.0,\"buyer\":null}"));
    }

    /**
     * Method under test: {@link OrderController#editOrder(Long, double)}
     */
    @Test
    void testEditOrder() throws Exception {
        when(orderService.editOrder(anyLong(), anyDouble())).thenReturn(new OrderDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/v1/orders/edit/{orderId}/{discount}", 1L, 10.0d);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"order_id\":0,\"order_date\":null,\"order_status\":null,\"total_price\":0.0,\"buyer\":null}"));
    }

    /**
     * Method under test: {@link OrderController#cancelOrder(long)}
     */
    @Test
    void testCancelOrder() throws Exception {
        when(orderService.cancelOrder(anyLong())).thenReturn(new OrderDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/orders/{orderId}", 1L);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"order_id\":0,\"order_date\":null,\"order_status\":null,\"total_price\":0.0,\"buyer\":null}"));
    }

    /**
     * Method under test: {@link OrderController#createOrder(PaymentDto)}
     */
    @Test
    void testCreateOrder() throws Exception {
        when(orderService.placeOrder((PaymentDto) any())).thenReturn(new OrderDto());

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(10.0d);
        paymentDto.setBuyer_id(1L);
        paymentDto.setCardNumber("42");
        paymentDto.setCurrency("GBP");
        paymentDto.setCvc("Cvc");
        paymentDto.setEmail("jane.doe@example.org");
        paymentDto.setExp_month(1);
        paymentDto.setExp_year(1);
        paymentDto.setName("Name");
        paymentDto.setOrder_Id(1L);
        paymentDto.setPaymentMethodId("42");
        paymentDto.setType("Type");
        String content = (new ObjectMapper()).writeValueAsString(paymentDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/api/v1/orders/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"order_id\":0,\"order_date\":null,\"order_status\":null,\"total_price\":0.0,\"buyer\":null}"));
    }
}

