package com.precariada.precariadashop.services;

import com.precariada.precariadashop.repositories.CartRepository;
import com.precariada.precariadashop.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartService (CartRepository cartRepository, UserRepository userRepository){
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
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
