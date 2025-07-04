package com.precariada.precariadashop.dtos.users;

public record UserResponse(
        Long id,
        String username,
        String email,
        String password
) {
}
