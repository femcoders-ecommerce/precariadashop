package com.precariada.precariadashop.dtos.cart;

import com.precariada.precariadashop.dtos.cartItem.CartItemDTO;

import java.util.List;

public class CartDTO {
    private Long id;
    private Long user;
    private List<CartItemDTO> items;
    private Double totalPrice;

    public CartDTO(Long id, Long user, List<CartItemDTO> items, Double totalPrice) {
        this.id = id;
        this.user = user;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return user;
    }

    public void setUserId(Long userId) {
        this.user = userId;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
