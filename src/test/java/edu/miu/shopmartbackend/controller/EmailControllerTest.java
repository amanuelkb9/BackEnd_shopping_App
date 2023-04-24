package edu.miu.shopmartbackend.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.shopmartbackend.model.dto.EmailDto;
import edu.miu.shopmartbackend.service.EmailSenderService;
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

@ContextConfiguration(classes = {EmailController.class})
@ExtendWith(SpringExtension.class)
class EmailControllerTest {
    @Autowired
    private EmailController emailController;

    @MockBean
    private EmailSenderService emailSenderService;

    /**
     * Method under test: {@link EmailController#sendEmail(EmailDto)}
     */
    @Test
    void testSendEmail() throws Exception {
        doNothing().when(emailSenderService).sendEmail((String) any(), (String) any(), (String) any());

        EmailDto emailDto = new EmailDto();
        emailDto.setMessage("Not all who wander are lost");
        emailDto.setSubject("Hello from the Dreaming Spires");
        emailDto.setTo("alice.liddell@example.org");
        String content = (new ObjectMapper()).writeValueAsString(emailDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sendEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(emailController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Email Sent successfully"));
    }

    /**
     * Method under test: {@link EmailController#sendPaymentConfirmationEmail(EmailDto)}
     */
    @Test
    void testSendPaymentConfirmationEmail() throws Exception {
        doNothing().when(emailSenderService).sendEmail((String) any(), (String) any(), (String) any());

        EmailDto emailDto = new EmailDto();
        emailDto.setMessage("Not all who wander are lost");
        emailDto.setSubject("Hello from the Dreaming Spires");
        emailDto.setTo("alice.liddell@example.org");
        String content = (new ObjectMapper()).writeValueAsString(emailDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/paymentEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(emailController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Payment Email Sent successfully"));
    }
}

