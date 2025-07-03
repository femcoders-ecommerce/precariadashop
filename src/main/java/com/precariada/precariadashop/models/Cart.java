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

    @OneToOne
    @JoinColumn (name = "user_id")
    private User userId;

    @OneToMany (mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @Column (name = "total_price")
    private Double totalPrice;

    public Cart() {
    }

    public Cart(User userId, List<CartItem> items, Double totalPrice) {
        this.userId = userId;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public void clearCart(){
        items.clear();
    }

    public Long getId() {
        return id;
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



