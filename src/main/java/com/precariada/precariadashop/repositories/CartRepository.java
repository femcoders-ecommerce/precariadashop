package com.precariada.precariadashop.repositories;

import com.precariada.precariadashop.models.Cart;
import com.precariada.precariadashop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
