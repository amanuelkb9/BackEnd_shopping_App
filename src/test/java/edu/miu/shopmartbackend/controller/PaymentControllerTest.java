package edu.miu.shopmartbackend.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.model.PaymentIntent;
import edu.miu.shopmartbackend.model.CardPayment;
import edu.miu.shopmartbackend.model.dto.PaymentDto;
import edu.miu.shopmartbackend.service.OrderService;
import edu.miu.shopmartbackend.service.PaymentService;
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

@ContextConfiguration(classes = {PaymentController.class})
@ExtendWith(SpringExtension.class)
class PaymentControllerTest {
    @MockBean
    private OrderService orderService;

    @Autowired
    private PaymentController paymentController;

    @MockBean
    private PaymentService paymentService;

    /**
     * Method under test: {@link PaymentController#handlePayment(PaymentDto)}
     */
    @Test
    void testHandlePayment() throws Exception {
        when(paymentService.handlePayment((PaymentDto) any())).thenReturn(new PaymentIntent());

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PaymentController#sellerPayment(CardPayment)}
     */
    @Test
    void testSellerPayment() throws Exception {
        when(paymentService.sellerPayment((CardPayment) any())).thenReturn(new PaymentIntent());

        CardPayment cardPayment = new CardPayment();
        cardPayment.setCardNumber("42");
        cardPayment.setCvc(1);
        cardPayment.setExpMonth(1);
        cardPayment.setExpYear(1);
        String content = (new ObjectMapper()).writeValueAsString(cardPayment);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/payments/sellerPayment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PaymentController#createCustomer(PaymentDto)}
     */
    @Test
    void testCreateCustomer() throws Exception {
        when(paymentService.createCustomer((PaymentDto) any())).thenReturn(null);

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/payments/createCustomer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

