package com.precariada.precariadashop.controllers;

import com.precariada.precariadashop.dtos.cart.CartDTO;
import com.precariada.precariadashop.services.CartItemService;
import com.precariada.precariadashop.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/cart-item")
public class CartItemController {
    private final CartItemService cartItemService;
    private final CartService cartService;

    public CartItemController(CartItemService cartItemService, CartService cartService) {
        this.cartItemService = cartItemService;
        this.cartService = cartService;
    }

    @PostMapping("/add/{userId}/{productId}")
    public ResponseEntity<CartDTO> addProductToCartItem(@PathVariable Long userId, @PathVariable Long productId) {
        var cart = cartService.getOrCreateCart(userId);
        cartItemService.addProductToCartItem(cart, productId);
        cartService.updateTotalPrice(cart);
        cartService.saveCart(cart);
        return new ResponseEntity<>(cartService.getCartByUserId(userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{userId}/{productId}")
    public ResponseEntity<CartDTO> removeProductFromCartItem(@PathVariable Long userId, @PathVariable Long productId) {
        var cart = cartService.getOrCreateCart(userId);
        cartItemService.removeProductFromCartItem(cart, productId);
        cartService.updateTotalPrice(cart);
        cartService.saveCart(cart);
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }
}
