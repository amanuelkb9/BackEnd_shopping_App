package edu.miu.shopmartbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "* product name is required")
    private String productName;

@DecimalMin(value = "0.1")
private double price;
    private String description;
    private boolean isPurchased;

    private String imageUrl;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User seller;

}
