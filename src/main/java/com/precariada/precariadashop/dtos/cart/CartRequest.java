package com.precariada.precariadashop.dtos.cart;

import com.precariada.precariadashop.models.CartItem;
import com.precariada.precariadashop.models.User;

import java.util.List;

public record CartRequest(
    Long userId,

    List<CartItem> items,

    Double totalPrice

) {
}
