package edu.miu.shopmartbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;

    private String username;

    private String password;

    @Email(message="{errors.invalid_email}")
    private String email;
    private boolean isAproved;



    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "ship_street")),
            @AttributeOverride(name = "city", column = @Column(name = "ship_city")),
            @AttributeOverride(name = "state", column = @Column(name = "ship_state")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "ship_zipcode"))
    })
    private Address shippingAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "bill_street")),
            @AttributeOverride(name = "city", column = @Column(name = "bill_city")),
            @AttributeOverride(name = "state", column = @Column(name = "bill_state")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "bill_zipcode"))
    })
    private Address billingAddress;

    @OneToOne()
    private ShoppingCart shoppingCart;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    List<Role> roles;

    @OneToMany
    private List<Order> orders;


    @OneToMany(mappedBy = "seller")
    private List<Product> products;




}