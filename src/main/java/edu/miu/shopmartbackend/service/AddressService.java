package edu.miu.shopmartbackend.service;


import edu.miu.shopmartbackend.model.Address;
import java.util.List;

public interface AddressService {


    void addBuyerBillingAddress( long buyer_id, Address address);

    void addBuyerShippingAddress( long buyer_id, Address address);

    List<Address> getBuyerBillingAddress(long id);

    List<Address> getBuyerShippingAddress( long id);
}
