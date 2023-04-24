package edu.miu.shopmartbackend.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.stripe.exception.StripeException;
import edu.miu.shopmartbackend.model.CardPayment;
import edu.miu.shopmartbackend.model.dto.PaymentDto;
import edu.miu.shopmartbackend.repo.PaymentRepository;
import edu.miu.shopmartbackend.repo.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PaymentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PaymentServiceImplTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link PaymentServiceImpl#createCustomer(PaymentDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateCustomer() throws StripeException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        paymentServiceImpl.createCustomer(new PaymentDto());
    }

    /**
     * Method under test: {@link PaymentServiceImpl#createCustomer(PaymentDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateCustomer2() throws StripeException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setName(" ");
        paymentServiceImpl.createCustomer(paymentDto);
    }

    /**
     * Method under test: {@link PaymentServiceImpl#createCustomer(PaymentDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateCustomer3() throws StripeException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.PaymentServiceImpl.createCustomer(PaymentServiceImpl.java:47)
        //   See https://diff.blue/R013 to resolve this issue.

        paymentServiceImpl.createCustomer(null);
    }

    /**
     * Method under test: {@link PaymentServiceImpl#createCustomer(PaymentDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateCustomer4() throws StripeException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        PaymentDto paymentDto = mock(PaymentDto.class);
        when(paymentDto.getEmail()).thenReturn("jane.doe@example.org");
        when(paymentDto.getName()).thenReturn("Name");
        when(paymentDto.getPaymentMethodId()).thenReturn("42");
        paymentServiceImpl.createCustomer(paymentDto);
    }

    /**
     * Method under test: {@link PaymentServiceImpl#handlePayment(PaymentDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandlePayment() throws StripeException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.PaymentServiceImpl.handlePayment(PaymentServiceImpl.java:63)
        //   See https://diff.blue/R013 to resolve this issue.

        paymentServiceImpl.handlePayment(new PaymentDto());
    }

    /**
     * Method under test: {@link PaymentServiceImpl#handlePayment(PaymentDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandlePayment2() throws StripeException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(10.0d);
        paymentServiceImpl.handlePayment(paymentDto);
    }

    /**
     * Method under test: {@link PaymentServiceImpl#handlePayment(PaymentDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandlePayment3() throws StripeException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        paymentServiceImpl.handlePayment(
                new PaymentDto(1L, 1L, "amount", "jane.doe@example.org", 10.0d, "GBP", "amount", "42", "42", 1, 1, "amount"));
    }

    /**
     * Method under test: {@link PaymentServiceImpl#handlePayment(PaymentDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandlePayment4() throws StripeException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        PaymentDto paymentDto = mock(PaymentDto.class);
        when(paymentDto.getAmount()).thenReturn(10.0d);
        when(paymentDto.getCurrency()).thenReturn("GBP");
        when(paymentDto.getEmail()).thenReturn("jane.doe@example.org");
        when(paymentDto.getName()).thenReturn("Name");
        when(paymentDto.getPaymentMethodId()).thenReturn("42");
        paymentServiceImpl.handlePayment(paymentDto);
    }

    /**
     * Method under test: {@link PaymentServiceImpl#sellerPayment(CardPayment)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSellerPayment() throws StripeException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        paymentServiceImpl.sellerPayment(new CardPayment());
    }

    /**
     * Method under test: {@link PaymentServiceImpl#sellerPayment(CardPayment)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSellerPayment2() throws StripeException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at edu.miu.shopmartbackend.service.impl.PaymentServiceImpl.sellerPayment(PaymentServiceImpl.java:132)
        //   See https://diff.blue/R013 to resolve this issue.

        paymentServiceImpl.sellerPayment(null);
    }

    /**
     * Method under test: {@link PaymentServiceImpl#sellerPayment(CardPayment)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSellerPayment3() throws StripeException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        CardPayment cardPayment = mock(CardPayment.class);
        when(cardPayment.getCvc()).thenReturn(1);
        when(cardPayment.getExpMonth()).thenReturn(1);
        when(cardPayment.getExpYear()).thenReturn(1);
        when(cardPayment.getCardNumber()).thenReturn("42");
        paymentServiceImpl.sellerPayment(cardPayment);
    }
}

