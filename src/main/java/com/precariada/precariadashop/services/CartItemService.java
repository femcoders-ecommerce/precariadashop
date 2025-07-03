package com.precariada.precariadashop.services;

import com.precariada.precariadashop.models.Cart;
import com.precariada.precariadashop.models.CartItem;
import com.precariada.precariadashop.models.Product;
import com.precariada.precariadashop.repositories.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService {
  /*  private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

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
}
