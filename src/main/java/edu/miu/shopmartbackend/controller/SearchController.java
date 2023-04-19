package edu.miu.shopmartbackend.controller;

import edu.miu.shopmartbackend.model.dto.OrderDto;
import edu.miu.shopmartbackend.model.dto.ProductDto;
import edu.miu.shopmartbackend.model.dto.UserDto;

import edu.miu.shopmartbackend.service.SearchService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {
    private final SearchService searchService;


    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return searchService.getAllUsers();
    }

    @GetMapping("/sellers")
    public List<UserDto> getAllSellers() {
        return searchService.getAllSellers();
    }

    @GetMapping("/buyers")
    public List<UserDto> getAllBuyers() {
        return searchService.getAllBuyers();
    }

    @GetMapping("/name")
    public UserDto getUserByUsername(@RequestParam("username") String username) {
        return searchService.getUserByUsername(username);
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable long id) {
        return searchService.getUserById(id);
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts(){
        return searchService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ProductDto getProductById(@PathVariable("id") long id){
        return searchService.getProductById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{order_id}")
    OrderDto findOrderById(@PathVariable("order_id") long order_id){
        return searchService.findOrderById(order_id);
    }
}
