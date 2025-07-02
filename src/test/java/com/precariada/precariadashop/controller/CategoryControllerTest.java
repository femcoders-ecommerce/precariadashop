package com.precariada.precariadashop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.precariada.precariadashop.controllers.CategoryController;
import com.precariada.precariadashop.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
    
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    
    @MockBean
    private CategoryService categoryService;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoryController(categoryService)).build();
        objectMapper = new ObjectMapper();
    }
    
    
}
