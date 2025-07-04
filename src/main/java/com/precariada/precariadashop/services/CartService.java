package com.precariada.precariadashop.services;

import com.precariada.precariadashop.dtos.cart.CartDTO;
import com.precariada.precariadashop.dtos.cart.CartMapper;
import com.precariada.precariadashop.models.Cart;
import com.precariada.precariadashop.models.User;
import com.precariada.precariadashop.repositories.CartItemRepository;
import com.precariada.precariadashop.repositories.CartRepository;
import com.precariada.precariadashop.repositories.ProductRepository;
import com.precariada.precariadashop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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


    /*public CartResponse addProductToCart (CartRequest cartRequest){
        User user = userRepository.findById(cartRequest.userId())
                .orElseThrow(()->new RuntimeException("User not found"));;
        Cart newCart = CartMapper.dtoToEntity(cartRequest, user);
        Cart saveCart = cartRepository.save(newCart);
        return CartMapper.entityToDto(saveCart);
    }

    public void deleteProductFromCart (Long id){cartRepository.deleteById(id);}*/
}
