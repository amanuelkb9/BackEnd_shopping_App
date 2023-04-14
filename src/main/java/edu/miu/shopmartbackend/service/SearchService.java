package edu.miu.shopmartbackend.service;

import edu.miu.shopmartbackend.model.dto.OrderDto;
import edu.miu.shopmartbackend.model.dto.ProductDto;
import edu.miu.shopmartbackend.model.dto.UserDto;

import java.util.List;

public interface SearchService {

    List<UserDto> getAllUsers();

    List<UserDto> getAllSellers();

    List<UserDto> getAllBuyers();

    UserDto getUserByUsername(String username);

    List<ProductDto> getAllProducts();

    UserDto getUserById(long id);

    ProductDto getProductById(long id);

    OrderDto findOrderById(long order_id);
}
