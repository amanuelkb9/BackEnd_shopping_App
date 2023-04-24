package edu.miu.shopmartbackend;

import edu.miu.shopmartbackend.model.Product;
import edu.miu.shopmartbackend.model.Role;
import edu.miu.shopmartbackend.model.dto.ReportDto;
import edu.miu.shopmartbackend.model.dto.RoleDto;
import edu.miu.shopmartbackend.model.dto.UserDto;
import edu.miu.shopmartbackend.model.dto.UsernamePassDto;
import edu.miu.shopmartbackend.service.ProductService;
import edu.miu.shopmartbackend.service.ReportService;
import edu.miu.shopmartbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan("edu.miu.shopmartbackend.*")
public class Application implements CommandLineRunner{

    private final ReportService reportService;

    public Application(ReportService reportService) {
        this.reportService = reportService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto(1L,"John", "john.doe@example.com",new ArrayList<>()));
        users.add(new UserDto(2L,"Jane", "jane.doe@example.com",new ArrayList<>()));

        List<RoleDto> roles = new ArrayList<>();
        roles.add(new RoleDto(1L,"ADMIN"));
        roles.add(new RoleDto(2L,"BUYER"));
        roles.add(new  RoleDto(3L,"SELLER"));

        ReportDto reportDto1 = new ReportDto(1L,"Sales Report", LocalDate.of(2022, 4, 22), "Monthly sales report");
        ReportDto reportDto2 = new ReportDto(2L,"Expense Report", LocalDate.of(2022, 3, 15), "Quarterly expense report");
        ReportDto reportDto3 = new ReportDto(3L,"Profit Report", LocalDate.of(2022, 2, 10), "Yearly profit report");

        ReportDto reportDto5 = new ReportDto(1L,"Sales Report", LocalDate.of(2022, 4, 22), "Monthly sales report");

        reportService.createReport(reportDto1);
        reportService.createReport(reportDto2);
        reportService.createReport(reportDto3);

        System.out.println("Sample reports added successfully.");
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
            productService.saveProduct(new Product(null, "iPhone14", 1200, "This is iPhone14 - ProMax", false, "https://shopping-cart-product-images.s3.amazonaws.com/iphone14.jpg", null), 3);
            productService.saveProduct(new Product(null, "iMax", 500, "T his iMax with Bass 100% Long lasting battery ", false,"https://shopping-cart-product-images.s3.amazonaws.com/iphone14.jpg", null), 3);
            productService.saveProduct(new Product(null, "MacBook Pro", 2000, "This is MacBook Pro - 16 inch", false, "https://shopping-cart-product-images.s3.amazonaws.com/iphone14.jpg", null), 3);
            productService.saveProduct(new Product(null, "MacBook Air", 1500, "This is MacBook Air - 13 inch", false, "https://shopping-cart-product-images.s3.amazonaws.com/iphone14.jpg", null), 3);



        };
    }

}
