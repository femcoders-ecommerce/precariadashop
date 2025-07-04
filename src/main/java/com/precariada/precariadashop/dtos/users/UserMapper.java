package com.precariada.precariadashop.dtos.users;

import com.precariada.precariadashop.models.User;

public interface UserMapper {
    public static User dtoToEntity(UserRequest dto) {
        return new User(dto.username(), dto.email(), dto.password());
    }

    public static UserResponse entityToDto(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }
}
