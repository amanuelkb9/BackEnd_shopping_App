package edu.miu.shopmartbackend.service.impl;

import edu.miu.shopmartbackend.model.Address;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.repo.UserRepo;
import edu.miu.shopmartbackend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    UserRepo userRepo;

    @Override
    public void addBuyerBillingAddress(long buyer_id, Address address) {
        User buyer = userRepo.getUserById(buyer_id);
        buyer.setBillingAddress(address);
        userRepo.save(buyer);
    }

    @Override
    public void addBuyerShippingAddress(long buyer_id, Address address) {
        User buyer = userRepo.getUserById(buyer_id);
        buyer.setShippingAddress(address);
        userRepo.save(buyer);
    }

    @Override
    public List<Address> getBuyerBillingAddress(long id) {
        return userRepo.getBuyerBillingAddress(id);
    }

    @Override
    public List<Address> getBuyerShippingAddress(long id) {
        return userRepo.getBuyerShippingAddress(id);
    }
}
