package com.precariada.precariadashop.services;

import com.precariada.precariadashop.dtos.cart.CartDTO;
import com.precariada.precariadashop.dtos.cart.CartMapper;
import com.precariada.precariadashop.models.Cart;
import com.precariada.precariadashop.models.CartItem;
import com.precariada.precariadashop.models.Product;
import com.precariada.precariadashop.models.User;
import com.precariada.precariadashop.repositories.CartItemRepository;
import com.precariada.precariadashop.repositories.CartRepository;
import com.precariada.precariadashop.repositories.ProductRepository;
import com.precariada.precariadashop.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }


    public CartDTO getCartByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id " + id));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new NoSuchElementException("Cart not found"));
        return CartMapper.entityToDto(cart);
    }

    private Cart getOrCreateCar(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + userId));
        return cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(new Cart(user, new ArrayList<>(), 0.0)));
    }

    private void updateTotalPrice(Cart cart){
        double total = cart.getItems().stream()
                .mapToDouble(item -> item.getProductId().getPrice() * item.getQuantity()).sum();
        cart.setTotalPrice(total);
    }

    public CartDTO addProductToCart(Long userId, Long productId){
        Cart cart = getOrCreateCar(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + productId));

        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProductId(cart, product);

        if (existingItem.isPresent()){
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + 1);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem(cart, product, 1);
            cart.getItems().add(newItem);
        }

        updateTotalPrice(cart);
        cartRepository.save(cart);
        return CartMapper.entityToDto(cart);
    }

    public CartDTO removeProductFromCart(Long userId, Long productId){
        Cart cart = getOrCreateCar(userId);
        boolean removed = cart.getItems().removeIf(item -> item.getProductId().getId().equals(productId));

        if (!removed){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID " + productId + " in cart");
        }

        updateTotalPrice(cart);
        cartRepository.save(cart);
        return CartMapper.entityToDto(cart);
    }
}
