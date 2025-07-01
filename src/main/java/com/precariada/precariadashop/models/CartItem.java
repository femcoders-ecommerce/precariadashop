package com.precariada.precariadashop.models;


import jakarta.persistence.*;


@Entity
@Table(name = "cart-item")
public class CartItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Product product;
    private int quantity;


    public CartItem(Product product, int i) {
    }


    public CartItem(Long id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }
}