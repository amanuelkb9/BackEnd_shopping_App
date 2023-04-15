package edu.miu.shopmartbackend.service.impl;

import edu.miu.shopmartbackend.model.Product;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.model.dto.OrderDto;
import edu.miu.shopmartbackend.model.dto.ProductDto;
import edu.miu.shopmartbackend.model.dto.UserDto;
import edu.miu.shopmartbackend.repo.OrderRepo;
import edu.miu.shopmartbackend.repo.ProductRepo;
import edu.miu.shopmartbackend.repo.UserRepo;
import edu.miu.shopmartbackend.service.SearchService;
import edu.miu.shopmartbackend.util.ListMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final UserRepo userRepo;
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    @Autowired
    ListMapper<User, UserDto> listMapperToDto;

    @Autowired
    ListMapper<Product, ProductDto> listMapperToDto2;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return (List<UserDto>)listMapperToDto.mapList(userRepo.findAll(), new UserDto());
    }

    @Override
    public List<UserDto> getAllSellers() {
        return (List<UserDto>) listMapperToDto.mapList(userRepo.findAllByRole("SELLER"), new UserDto());
    }


    @Override
    public List<UserDto> getAllBuyers() {
        return (List<UserDto>) listMapperToDto.mapList(userRepo.findAllByRole("BUYER"), new UserDto());
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return modelMapper.map(userRepo.findByUsername(username).get(), UserDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return (List<ProductDto>) listMapperToDto2.mapList(productRepo.findAll(), new ProductDto());
    }

    @Override
    public UserDto getUserById(long id) {
        return modelMapper.map(userRepo.findById(id).get(), UserDto.class);
    }


    @Override
    public ProductDto getProductById(long id) {
        return modelMapper.map(productRepo.findById(id).get(), ProductDto.class);
    }

    @Override
    public OrderDto findOrderById(long order_id) {
        return modelMapper.map(orderRepo.findById(order_id).get(), OrderDto.class);
    }
}
