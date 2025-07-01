package com.precariada.precariadashop.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @OneToMany (mappedBy = "cart_items")
    private List<CartItem> items = new ArrayList<>();

    @Column (name = "total_price")
    private Double totalPrice;

    public Cart() {
    }

    public Cart(Long id, List<CartItem> items, User user, Double totalPrice) {
        this.id = id;
        this.items = items;
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public void clearCart(){
        items.clear();
    }

    public Long getUserId() {
        return id;
    }

    public void setUserId(Long id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}



