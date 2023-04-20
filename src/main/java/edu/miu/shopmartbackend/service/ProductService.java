package edu.miu.shopmartbackend.service;

import edu.miu.shopmartbackend.model.Product;
import edu.miu.shopmartbackend.model.dto.ProductDto;

import java.util.List;

public interface ProductService {
     void saveProduct(Product product, long seller_id);

    void deleteProduct(long id);

    void updateProduct(ProductDto productDto, long id);
}

