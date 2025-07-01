package com.precariada.precariadashop.dtos.users;

public record UserResponse(
        String username,
        String email,
        String password
) {
}
