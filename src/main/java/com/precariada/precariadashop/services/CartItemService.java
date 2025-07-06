package com.precariada.precariadashop.services;

import com.precariada.precariadashop.models.Cart;
import com.precariada.precariadashop.models.CartItem;
import com.precariada.precariadashop.models.Product;
import com.precariada.precariadashop.repositories.CartItemRepository;
import com.precariada.precariadashop.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartItemService(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public CartItem addProductToCartItem(Cart cart, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProductId(cart, product);

        CartItem savedItem;
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + 1);
            savedItem = cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem(cart, product, 1);
            cart.getItems().add(newItem);
            savedItem = cartItemRepository.save(newItem);
        }
        return savedItem;

    }

    public void removeProductFromCartItem(Cart cart, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        CartItem itemToRemove = cartItemRepository.findByCartAndProductId(cart, product)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not in the cart"));

        cartItemRepository.delete(itemToRemove);
    }
}

/*
    public CartItem addOrIncrement(Cart cart, Product product) {
        Optional<CartItem> optionalItem = cart.getItems().stream()
                .filter(item -> item.getProductList().().equals(product.getId()))
                .findFirst();

        if (optionalItem.isPresent()) {
            CartItem existingItem = optionalItem.get();
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            return cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem (cart, product, 1);
            cart.getItems().add(newItem);
            return cartItemRepository.save(newItem);
        }
    }
   */
