package com.precariada.precariadashop.services;

import com.precariada.precariadashop.dtos.users.UserMapper;
import com.precariada.precariadashop.dtos.users.UserRequest;
import com.precariada.precariadashop.dtos.users.UserResponse;
import com.precariada.precariadashop.models.User;
import com.precariada.precariadashop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserMapper.entityToDto(user)).toList();
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id " + id));
        return UserMapper.entityToDto(user);
    }

    public UserResponse addUser(UserRequest userRequest) {
        User newUser = UserMapper.dtoToEntity(userRequest);
        User savedUser = userRepository.save(newUser);
        return UserMapper.entityToDto(savedUser);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User updatedUser = userRepository.findById(id). orElseThrow(() -> new NoSuchElementException("User not found with id " + id));
        updatedUser.setUsername(userRequest.username());
        updatedUser.setEmail(userRequest.email());
        updatedUser.setPassword(userRequest.password());
        User newUser = userRepository.save(updatedUser);
        return UserMapper.entityToDto(newUser);
    }

    public void deleteUser(Long id) {
        getUserById(id);
        userRepository.deleteById(id);
    }
}
