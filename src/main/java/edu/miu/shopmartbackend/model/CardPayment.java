package edu.miu.shopmartbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardPayment {

    private String cardNumber;
    private Integer expMonth;
    private Integer expYear;
    private Integer cvc;

}
