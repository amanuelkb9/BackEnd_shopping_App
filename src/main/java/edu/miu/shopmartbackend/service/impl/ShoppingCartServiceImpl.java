package edu.miu.shopmartbackend.service.impl;

import edu.miu.shopmartbackend.model.Product;
import edu.miu.shopmartbackend.model.ShoppingCart;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.model.dto.UserDto;
import edu.miu.shopmartbackend.repo.ProductRepo;
import edu.miu.shopmartbackend.repo.ShoppingCartRepo;
import edu.miu.shopmartbackend.repo.UserRepo;
import edu.miu.shopmartbackend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ShoppingCartRepo shoppingCartRepo;

    @Autowired
    ProductRepo productRepo;

    @Override
    public ShoppingCart createShoppingCart(long user_id) {
        ShoppingCart shoppingCart;
        User buyer = userRepo.getUserById(user_id);
        if (buyer.getShoppingCart() == null) {
            shoppingCart = new ShoppingCart();
        } else {
            return null;
        }
        buyer.setShoppingCart(shoppingCart);
//        shoppingCart.setBuyer(buyer);
        List<Product> products = new ArrayList<>();
        shoppingCart.setProducts(products);
        return shoppingCartRepo.save(shoppingCart);

    }


    @Override
    public ShoppingCart getShoppingCartById(long cart_id) {
        return shoppingCartRepo.findById(cart_id).get();
    }

    @Override
    public ShoppingCart addProductToShoppingCart(long buyer_id, long product_id) {
        User buyer = userRepo.findById(buyer_id).get();
        ShoppingCart shoppingCart = buyer.getShoppingCart();

        Product product = productRepo.findById(product_id).get();
        List<Product> products = shoppingCart.getProducts();
        products.add(product);
        shoppingCart.setProducts(products);
        buyer.setShoppingCart(shoppingCart);
        userRepo.save(buyer);
        return shoppingCartRepo.save(shoppingCart);
    }

    @Override
    public void deleteShoppingCart(long buyer_id) {
        User buyer = userRepo.findById(buyer_id).get();
        ShoppingCart shoppingCart = buyer.getShoppingCart();
        shoppingCart.setProducts(null);
        userRepo.save(buyer);
    }

    @Override
    public ShoppingCart deleteProductByIdFromCart(long buyer_id, long product_id) {
        Product product = productRepo.findById(product_id).get();
        User buyer = userRepo.findById(buyer_id).get();
        ShoppingCart shoppingCart = buyer.getShoppingCart();

        List<Product> products = shoppingCart.getProducts();
        products.remove(product);
        shoppingCart.setProducts(products);
        shoppingCartRepo.save(shoppingCart);
        buyer.setShoppingCart(shoppingCart);
        userRepo.save(buyer);
        return shoppingCart;
    }

    @Override
    public ShoppingCart clearShoppingCart(long user_id) {
        User user = userRepo.findById(user_id).get();
        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCart.setProducts(new ArrayList<>());
        shoppingCartRepo.save(shoppingCart);
        user.setShoppingCart(shoppingCart);

        return shoppingCart;
    }

    @Override
    public ShoppingCart viewCartDetail(long userId) {
        User user = userRepo.getUserById(userId);
        ShoppingCart shoppingCart = user.getShoppingCart();

        return shoppingCart;
    }

    @Override
    public ShoppingCart editQuantity(long userId, String productName, Integer number) {
//        ShoppingCart shoppingCart = userRepo.getUserById(userId).getShoppingCart();
//        shoppingCart.getProducts().stream().map(product -> product.getProductName())
//                .filter(product -> product.equals(productName)).collect(System)


        return null;
    }


}
