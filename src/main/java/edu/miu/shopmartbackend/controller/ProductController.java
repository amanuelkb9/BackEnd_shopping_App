package edu.miu.shopmartbackend.controller;

import edu.miu.shopmartbackend.model.Product;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.model.dto.ProductDto;
import edu.miu.shopmartbackend.service.OrderService;
import edu.miu.shopmartbackend.service.ProductService;
import edu.miu.shopmartbackend.service.SearchService;
import edu.miu.shopmartbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {


    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    SearchService searchService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/{seller_id}")
    public void addProduct(@RequestBody ProductDto productDto, @PathVariable long seller_id) {
        User seller = modelMapper.map(searchService.getUserById(seller_id), User.class);

        if (seller.isAproved()) {
            Product product = modelMapper.map(productDto, Product.class);
            Product newProduct = new Product();
            newProduct.setProductName(product.getProductName());
            newProduct.setDescription(product.getDescription());
            newProduct.setPrice(product.getPrice());
            productService.saveProduct(newProduct, seller_id);
        }


    }


    @PutMapping("/products/{id}")
    public void updateProduct(@RequestBody ProductDto productDto, @PathVariable("id") long id) {
        productService.updateProduct(productDto, id);

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
    }


}
