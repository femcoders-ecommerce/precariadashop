package com.precariada.precariadashop.controllers;

import com.precariada.precariadashop.dtos.cart.CartRequest;
import com.precariada.precariadashop.dtos.cart.CartResponse;
import com.precariada.precariadashop.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService){this.cartService = cartService;}

    @PostMapping ("/{userId}/add/{productId}")
    public ResponseEntity<CartResponse> addProductToCart(@RequestBody CartRequest cartRequest) {
        return new ResponseEntity<>(cartService.addProductToCart(cartRequest), HttpStatus.CREATED);
    }
    @DeleteMapping ("/{userId}/remove/{productId}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable Long id){
        cartService.deleteProductFromCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
