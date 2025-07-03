package com.precariada.precariadashop.dtos.cart;


import com.precariada.precariadashop.models.Cart;
import com.precariada.precariadashop.models.User;
import com.precariada.precariadashop.repositories.UserRepository;
import com.precariada.precariadashop.services.UserService;

public interface CartMapper {
    public static Cart dtoToEntity (CartRequest dto, User user){
        return new Cart(user, dto.items(), dto.totalPrice());
    }
    public static CartResponse entityToDto (Cart cart){
        return new CartResponse(cart.getId(), cart.getUserId(), cart.getItems(), cart.getTotalPrice());
    }

}
