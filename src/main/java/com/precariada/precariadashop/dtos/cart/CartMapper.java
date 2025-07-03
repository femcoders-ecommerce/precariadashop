package com.precariada.precariadashop.dtos.cart;

import com.precariada.precariadashop.dtos.cartItem.CartItemDTO;
import com.precariada.precariadashop.models.Cart;

import java.util.List;

public class CartMapper {
    public CartDTO entityToDto(Cart cart) {
        List<CartItemDTO> items = cart.getItems().stream()
                .map(item -> new CartItemDTO(
                        item.getProductId().getId(),
                        item.getProductId().getName(),
                        item.getProductId().getPrice(),
                        item.getQuantity()
                )).toList();

        return new CartDTO(
                cart.getId(),
                cart.getUserId(),
                items,
                cart.getTotalPrice()
        );

    }
}
