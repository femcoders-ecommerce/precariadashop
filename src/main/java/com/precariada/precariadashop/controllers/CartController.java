package com.precariada.precariadashop.controllers;

import com.precariada.precariadashop.dtos.cart.CartDTO;
import com.precariada.precariadashop.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService){this.cartService = cartService;}

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long userId){
        CartDTO cartDTO = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartDTO);
    }

    @PostMapping("/{userId}/add/{productId}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable Long userId, @PathVariable Long productId){
        CartDTO updatedCart = cartService.addProductToCart(userId, productId);
        return new ResponseEntity<>(updatedCart, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<CartDTO> removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId){
        CartDTO updatedCart = cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok(updatedCart);
    }
}
