package edu.miu.shopmartbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {

    @JsonProperty("product_id")
    private long productId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_price")
    private double price;

    @JsonProperty("product_description")
    private String description;

    private String imageUrl;

//    @JsonProperty("is_purchased")
//    private boolean isPurchased;
}
