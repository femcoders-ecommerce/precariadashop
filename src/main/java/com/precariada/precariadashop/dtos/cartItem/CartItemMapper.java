package com.precariada.precariadashop.dtos.cartItem;

import com.precariada.precariadashop.models.CartItem;
import com.precariada.precariadashop.models.Product;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
    public static CartItemDTO entityToDto(CartItem cartItem) {
        Product product = cartItem.getProductId();
        return new CartItemDTO(product.getId(), product.getName(), product.getPrice(), cartItem.getQuantity());
    }
}