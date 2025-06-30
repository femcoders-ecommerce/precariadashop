package com.precariada.precariadashop.repositories;

import com.precariada.precariadashop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
