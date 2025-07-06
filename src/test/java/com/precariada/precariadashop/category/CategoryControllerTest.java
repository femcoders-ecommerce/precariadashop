package com.precariada.precariadashop.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.precariada.precariadashop.dtos.category.CategoryRequest;
import com.precariada.precariadashop.dtos.category.CategoryResponse;
import com.precariada.precariadashop.dtos.product.ProductResponse;
import com.precariada.precariadashop.services.CategoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<CategoryResponse> categoryResponses;

    @BeforeEach
    void setUp(){
        ProductResponse product1 = new ProductResponse(1L, "Lamina 'Titis'", 29.99, "https://imagen.jpg", true, "Print");
        ProductResponse product2 = new ProductResponse(2L, "Camiseta 'Envejecer con las amigas'", 49.99, "https://imagen.jpg", false, "Textil");

        List<ProductResponse> products2 = List.of(product2);
        List<ProductResponse> products1 = List.of(product1);

        categoryResponses = new ArrayList<>();

        CategoryResponse category1 = new CategoryResponse(1L, "Textil", products1);
        CategoryResponse category2 = new CategoryResponse(2L, "Print", products2);

        categoryResponses.add(category1);
        categoryResponses.add(category2);
    }

    @Test
    void shouldGetAllCategoriesSuccessfully() throws Exception {
        given(categoryService.getAllCategories()).willReturn(categoryResponses);

        mockMvc.perform(get("/api/categories").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Textil"))
                .andExpect(jsonPath("$[0].products", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].products[0].name").value("Lamina 'Titis'"))
                .andExpect(jsonPath("$[1].name").value("Print"))
                .andExpect(jsonPath("$[1].products", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[1].products[0].name").value("Camiseta 'Envejecer con las amigas'"));
    }

    @Test
    void shouldCreateCategorySuccessfully() throws Exception {
        CategoryRequest request = new CategoryRequest("Category 3");
        CategoryResponse savedResponse = new CategoryResponse(3L,"Category 3", List.of());

        given(categoryService.addCategory(Mockito.any(CategoryRequest.class))).willReturn(savedResponse);

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Category 3"))
                .andExpect(jsonPath("$.products").isEmpty());
    }

    @Test
    void shouldReturnBadRequestWhenCategoryNameIsInvalid() throws Exception {
        CategoryRequest invalidRequest = new CategoryRequest("");
        String json = objectMapper.writeValueAsString(invalidRequest);

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
