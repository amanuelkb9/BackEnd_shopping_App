package edu.miu.shopmartbackend;

import edu.miu.shopmartbackend.model.Product;
import edu.miu.shopmartbackend.model.Role;
import edu.miu.shopmartbackend.model.dto.UsernamePassDto;
import edu.miu.shopmartbackend.service.ProductService;
import edu.miu.shopmartbackend.service.UserService;
import edu.miu.shopmartbackend.util.ReportMapper;
import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//@ComponentScan("edu.miu.shopmartbackend.util")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public ReportMapper reportMapper() {
//        return Mappers.getMapper(ReportMapper.class);
//    }

    @Bean
    CommandLineRunner runner(UserService userService, ProductService productService) {
        return args -> {
            userService.saveRole(new Role(null, "ADMIN"));
            userService.saveRole(new Role(null, "BUYER"));
            userService.saveRole(new Role(null, "SELLER"));

            userService.addUser(new UsernamePassDto("admin", "pass123"));
            userService.addUser(new UsernamePassDto("buyer", "pass123"));
            userService.addUser(new UsernamePassDto("seller", "pass123"));

            userService.addRoleToUser("admin", "ADMIN");
            userService.addRoleToUser("buyer", "BUYER");
            userService.addRoleToUser("seller", "SELLER");

            // Seller adding his products
            productService.saveProduct(new Product(null, "iPhone14", 1200, "This is Lamborghini", false, "https://shopping-cart-product-images.s3.amazonaws.com/img/lambo.jpg", null), 3);
            productService.saveProduct(new Product(null, "iMax", 500, "This iMax with Bass 100% Long lasting battery ", false,"https://shopping-cart-product-images.s3.amazonaws.com/img/iMax.jpg", null), 3);
            productService.saveProduct(new Product(null, "MacBook-Pro 14", 2000, "This is MacBook Pro 14 - 17 inch", false, "https://shopping-cart-product-images.s3.amazonaws.com/img/macBookPro14.jpg", null), 3);
            productService.saveProduct(new Product(null, "MacBook Air", 1500, "This is iphone 14", false, "https://shopping-cart-product-images.s3.amazonaws.com/iphone14.jpg", null), 3);



        };
    }

}
