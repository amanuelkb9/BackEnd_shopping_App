package edu.miu.shopmartbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {
    @Id
    private long paymentId;
    private long buyer_id;
    private long order_Id;
    private String name;
    private Address address;

    @Email
    private String email;
    private Double amount;
    private String currency;
    private String type;
    private String paymentMethodId;
    @CreditCardNumber
    private String cardNumber;
    private Integer exp_month;
    private Integer exp_year;
    private String cvc;
    private String token;
}
