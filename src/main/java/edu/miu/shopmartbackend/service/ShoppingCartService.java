package edu.miu.shopmartbackend.service;
import edu.miu.shopmartbackend.model.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public interface ShoppingCartService {

    ShoppingCart createShoppingCart(long user_id);

    ShoppingCart getShoppingCartById(long cart_id);

    ShoppingCart addProductToShoppingCart(long buyer_id, long product_id);

    void deleteShoppingCart(long buyer_id);

    ShoppingCart deleteProductByIdFromCart(long buyer_id, long product_id);

    ShoppingCart clearShoppingCart(long user_id);


    ShoppingCart viewCartDetail(long userId);

    ShoppingCart editQuantity(long userId, String productName, Integer number);
}
