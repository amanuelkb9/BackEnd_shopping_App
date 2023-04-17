package edu.miu.shopmartbackend.controller;
import edu.miu.shopmartbackend.model.Address;
import edu.miu.shopmartbackend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/{buyer_id}/billing")
   void addBuyerBillingAddress(@PathVariable long buyer_id, @RequestBody Address address) {

         addressService.addBuyerBillingAddress(buyer_id, address);
    }

    @PostMapping("/{buyer_id}/shipping")
    void addBuyerShippingAddress(@PathVariable long buyer_id, @RequestBody Address address) {

        addressService.addBuyerShippingAddress(buyer_id, address);
    }

    @GetMapping("/{buyer_id}/billing")
    List<Address> getBuyerBillingAddress(@PathVariable long buyer_id) {

        return addressService.getBuyerBillingAddress(buyer_id);
    }

    @GetMapping("/{buyer_id}/shipping")
    List<Address> getBuyerShippingAddress(@PathVariable long buyer_id) {

        return addressService.getBuyerShippingAddress(buyer_id);
    }
}
