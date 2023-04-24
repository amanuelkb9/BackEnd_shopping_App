package edu.miu.shopmartbackend.service.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import edu.miu.shopmartbackend.model.dto.PaymentDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailSenderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmailSenderServiceImplTest {
    @Autowired
    private EmailSenderServiceImpl emailSenderServiceImpl;

    @MockBean
    private JavaMailSender javaMailSender;

    /**
     * Method under test: {@link EmailSenderServiceImpl#sendEmail(String, String, String)}
     */
    @Test
    void testSendEmail() throws MailException {
        doNothing().when(javaMailSender).send((SimpleMailMessage) any());
        emailSenderServiceImpl.sendEmail("alice.liddell@example.org", "Hello from the Dreaming Spires",
                "Not all who wander are lost");
        verify(javaMailSender).send((SimpleMailMessage) any());
    }

    /**
     * Method under test: {@link EmailSenderServiceImpl#sendPaymentConfirmationEmail(String, PaymentDto)}
     */
    @Test
    void testSendPaymentConfirmationEmail() throws MailException {
        doNothing().when(javaMailSender).send((SimpleMailMessage) any());
        emailSenderServiceImpl.sendPaymentConfirmationEmail("jane.doe@example.org", new PaymentDto());
        verify(javaMailSender).send((SimpleMailMessage) any());
    }

    /**
     * Method under test: {@link EmailSenderServiceImpl#sendPaymentConfirmationEmail(String, PaymentDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSendPaymentConfirmationEmail2() throws MailException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.EmailSenderServiceImpl.sendPaymentConfirmationEmail(EmailSenderServiceImpl.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(javaMailSender).send((SimpleMailMessage) any());
        emailSenderServiceImpl.sendPaymentConfirmationEmail("jane.doe@example.org", null);
    }

    /**
     * Method under test: {@link EmailSenderServiceImpl#sendPaymentFailureEmail(String, PaymentDto)}
     */
    @Test
    void testSendPaymentFailureEmail() throws MailException {
        doNothing().when(javaMailSender).send((SimpleMailMessage) any());
        emailSenderServiceImpl.sendPaymentFailureEmail("jane.doe@example.org", new PaymentDto());
        verify(javaMailSender).send((SimpleMailMessage) any());
    }

    /**
     * Method under test: {@link EmailSenderServiceImpl#sendPaymentFailureEmail(String, PaymentDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSendPaymentFailureEmail2() throws MailException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.EmailSenderServiceImpl.sendPaymentFailureEmail(EmailSenderServiceImpl.java:53)
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(javaMailSender).send((SimpleMailMessage) any());
        emailSenderServiceImpl.sendPaymentFailureEmail("jane.doe@example.org", null);
    }

    /**
     * Method under test: {@link EmailSenderServiceImpl#sendOrderConfirmationEmail(String, PaymentDto)}
     */
    @Test
    void testSendOrderConfirmationEmail() throws MailException {
        doNothing().when(javaMailSender).send((SimpleMailMessage) any());
        emailSenderServiceImpl.sendOrderConfirmationEmail("jane.doe@example.org", new PaymentDto());
        verify(javaMailSender).send((SimpleMailMessage) any());
    }

    /**
     * Method under test: {@link EmailSenderServiceImpl#sendOrderConfirmationEmail(String, PaymentDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSendOrderConfirmationEmail2() throws MailException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.EmailSenderServiceImpl.sendOrderConfirmationEmail(EmailSenderServiceImpl.java:66)
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(javaMailSender).send((SimpleMailMessage) any());
        emailSenderServiceImpl.sendOrderConfirmationEmail("jane.doe@example.org", null);
    }
}

