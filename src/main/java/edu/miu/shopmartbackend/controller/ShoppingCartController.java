package edu.miu.shopmartbackend.controller;

import edu.miu.shopmartbackend.model.ShoppingCart;
import edu.miu.shopmartbackend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;
    @PostMapping("/{buyer_id}")
    public ShoppingCart createShoppingCart(@PathVariable long buyer_id){
        return shoppingCartService.createShoppingCart(buyer_id);
    }

    @GetMapping("/{cart_id}/cart")
    public ShoppingCart getShoppingCartById(@PathVariable long cart_id){
        return shoppingCartService.getShoppingCartById(cart_id);
    }

    @PostMapping("/{buyer_id}/{product_id}")
    public ShoppingCart addProductToShoppingCart(@PathVariable long buyer_id, @PathVariable long product_id){
       return shoppingCartService.addProductToShoppingCart(buyer_id, product_id);
    }

    @DeleteMapping("/{buyer_id}")
    public void deleteShoppingCart(@PathVariable long buyer_id){
        shoppingCartService.deleteShoppingCart(buyer_id);
    }

    @DeleteMapping("/{buyer_id}/{product_id}")
    public ShoppingCart deleteProductByIdFromCart(@PathVariable long buyer_id, @PathVariable long product_id){
        return shoppingCartService.deleteProductByIdFromCart(buyer_id, product_id);
    }

    @PatchMapping("/{user_id}")
    public ShoppingCart clearShopingCart(@PathVariable long user_id){
        return shoppingCartService.clearShoppingCart(user_id);
    }

    @GetMapping("{userId}/viewCartDetail")
    public ShoppingCart viewShoppingCartDetails(@PathVariable long userId){
        return shoppingCartService.viewCartDetail(userId);
    }

@PutMapping("edit/{userId}")
    public ShoppingCart editQuantity(@PathVariable long userId, @RequestParam() String productName, @RequestParam Integer number){
        return shoppingCartService.editQuantity(userId, productName, number);
}






}
