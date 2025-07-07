package com.precariada.precariadashop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.precariada.precariadashop.dtos.cart.CartDTO;
import com.precariada.precariadashop.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

    @SpringBootTest
    @AutoConfigureMockMvc
    @ActiveProfiles("tests")
    public class CartControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private CartService cartService;

        @Autowired
        private ObjectMapper objectMapper;

        private CartDTO sampleCart;

        @BeforeEach
        void setUp() {
            sampleCart = new CartDTO(1L, 2L, Collections.emptyList(), 0.0);
        }

        @Test
        void shouldGetCartByUserId() throws Exception {
            given(cartService.getCartByUserId(2L)).willReturn(sampleCart);

            mockMvc.perform(get("/api/cart/{userId}", 2L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(sampleCart.getId()))
                    .andExpect(jsonPath("$.userId").value(sampleCart.getUserId()))
                    .andExpect(jsonPath("$.items").isArray())
                    .andExpect(jsonPath("$.totalPrice").value(sampleCart.getTotalPrice()));
        }

        @Test
        void shouldAddProductToCart() throws Exception {
            given(cartService.addProductToCart(2L, 5L)).willReturn(sampleCart);

            mockMvc.perform(post("/api/cart/{userId}/add/{productId}", 2L, 5L))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(sampleCart.getId()))
                    .andExpect(jsonPath("$.userId").value(sampleCart.getUserId()));
        }

        @Test
        void shouldRemoveProductFromCart() throws Exception {
            given(cartService.removeProductFromCart(2L, 5L)).willReturn(sampleCart);

            mockMvc.perform(delete("/api/cart/{userId}/remove/{productId}", 2L, 5L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(sampleCart.getId()))
                    .andExpect(jsonPath("$.userId").value(sampleCart.getUserId()));
        }
    }