package com.precariada.precariadashop.repositories;

import com.precariada.precariadashop.models.Cart;
import com.precariada.precariadashop.models.CartItem;
import com.precariada.precariadashop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProductId(Cart cart, Product product);
}