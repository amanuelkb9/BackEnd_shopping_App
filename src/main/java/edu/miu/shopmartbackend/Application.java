package edu.miu.shopmartbackend;

import edu.miu.shopmartbackend.model.Role;
import edu.miu.shopmartbackend.model.dto.UsernamePassDto;
import edu.miu.shopmartbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
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

    @Bean
    CommandLineRunner runner(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ADMIN"));
            userService.saveRole(new Role(null, "BUYER"));
            userService.saveRole(new Role(null, "SELLER"));

            userService.addUser(new UsernamePassDto("admin", "pass123"));
            userService.addUser(new UsernamePassDto("buyer", "pass123"));
            userService.addUser(new UsernamePassDto("seller", "pass123"));

            userService.addRoleToUser("admin","ADMIN");
            userService.addRoleToUser("buyer", "BUYER");
            userService.addRoleToUser("seller", "SELLER");
        };
    }

}
