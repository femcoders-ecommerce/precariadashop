package com.precariada.precariadashop.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.precariada.precariadashop.dtos.category.CategoryResponse;
import com.precariada.precariadashop.dtos.product.ProductRequest;
import com.precariada.precariadashop.dtos.product.ProductResponse;
import com.precariada.precariadashop.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ProductResponse> productResponses;

    @BeforeEach
    void setUp() {
        productResponses = new ArrayList<>();

        ProductResponse product1 = new ProductResponse(1L, "Product 1", 20.00, "https://imagen.jpg", true, new CategoryResponse(1L, "Textil", Collections.emptyList()));
        ProductResponse product2 = new ProductResponse(2L, "Product 2", 30.00, "https://imagen.jpg", false, new CategoryResponse(2L, "Print", Collections.emptyList()));

        productResponses.add(product1);
        productResponses.add(product2);
    }

    @Test
    void shouldGetAllProductsSuccessfully() throws Exception {
        given(productService.getAllProducts()).willReturn(productResponses);

        mockMvc.perform(get("/api/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$").isArray())
                .andExpect((ResultMatcher) jsonPath("$.length()").value(1))
                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1L))
                .andExpect((ResultMatcher) jsonPath("$[1].id").value(2L))
                .andExpect((ResultMatcher) jsonPath("$[0].name").value("Product 1"))
                .andExpect((ResultMatcher) jsonPath("$[1].name").value("Product 2"))
                .andExpect((ResultMatcher) jsonPath("$[0].price").value(20.00))
                .andExpect((ResultMatcher) jsonPath("$[1].price").value(30.00))
                .andExpect((ResultMatcher) jsonPath("$[0].imageUrl").value("https://imagen.jpg"))
                .andExpect((ResultMatcher) jsonPath("$[1].imageUrl").value("https://imagen.jpg"))
                .andExpect((ResultMatcher) jsonPath("$[0].featured").value(true))
                .andExpect((ResultMatcher) jsonPath("$[1].featured").value(false))
                .andExpect((ResultMatcher) jsonPath("$[0].category").value("Textil"))
                .andExpect((ResultMatcher) jsonPath("$[1].category").value("Print"));
    }

    @Test
    void shouldCreateProductSuccessfully() throws Exception {
        ProductRequest request = new ProductRequest("Product 3", 25.00, "https://imagen.jpg", true, "Print");
        ProductResponse savedResponse = new ProductResponse(3L, "Product 3", 25.00, "https://imagen.jpg", true, "Print");

        given(productService.addProduct(Mockito.any(ProductRequest.class))).willReturn(savedResponse);

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.name").value("Product 3"))
                .andExpect((ResultMatcher) jsonPath("$.price").value(25.00))
                .andExpect((ResultMatcher) jsonPath("$.imageUrl").value("https://imagen.jpg"))
                .andExpect((ResultMatcher) jsonPath("$.featured").value(true))
                .andExpect((ResultMatcher) jsonPath("$.category").value("Print"));
        }

        @Test
        void shouldReturnBadRequestWhenProductNameIsInvalid() throws Exception {
            ProductRequest invalidRequest = new ProductRequest("", 0.0, "", true, "");
            String json = objectMapper.writeValueAsString(invalidRequest);


            mockMvc.perform(post("/api/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }
}