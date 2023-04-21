package edu.miu.shopmartbackend.model;

import edu.miu.shopmartbackend.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(style = "yyyy-mm-dd")
//    @NotNull
    @Column(name="order_date")
    private LocalDate orderDate;
    @Enumerated(EnumType.STRING)
    @Column(name="order_status")
    private OrderStatus orderStatus;
    @Column(name = "total_order_price")
    private double totalOrderPrice;
//
    @ManyToOne
    @JoinColumn(name ="buyer_id")
    private User buyer;


    @OneToOne
    @JoinColumn(name ="shopping_cart_id")
   private ShoppingCart shoppingCart;
}
