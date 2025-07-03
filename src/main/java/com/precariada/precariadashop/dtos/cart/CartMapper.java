package com.precariada.precariadashop.dtos.cart;

import com.precariada.precariadashop.dtos.cartItem.CartItemDTO;
import com.precariada.precariadashop.models.Cart;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
                cart.getUser(),
                items,
                cart.getTotalPrice()
        );

    }
}
