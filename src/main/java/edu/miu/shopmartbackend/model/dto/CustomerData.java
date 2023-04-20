package edu.miu.shopmartbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Email;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerData {
    private String Data;
    private String name;

    @Email
    private String email;

    @CreditCardNumber
    private String cardNumber;
    private Integer exp_month;
    private Integer exp_year;
    private String cvc;
}
