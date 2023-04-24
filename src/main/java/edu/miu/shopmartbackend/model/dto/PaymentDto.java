package edu.miu.shopmartbackend.model.dto;

import edu.miu.shopmartbackend.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private long buyer_id;
    private long order_Id;
    private String name;
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

}
