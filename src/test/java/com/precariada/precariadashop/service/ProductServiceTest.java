package com.precariada.precariadashop.service;

import com.precariada.precariadashop.dtos.product.ProductRequest;
import com.precariada.precariadashop.dtos.product.ProductResponse;
import com.precariada.precariadashop.models.Category;
import com.precariada.precariadashop.models.Product;
import com.precariada.precariadashop.repositories.CategoryRepository;
import com.precariada.precariadashop.repositories.ProductRepository;
import com.precariada.precariadashop.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    ProductService productService;

    Category category;

    @BeforeEach
    void setUp() {
        category = new Category("category");
    }

    @Test
    void shouldReturnListOfProductResponses() {
        Product product1 = new Product("Product 1", 20.00, "https://imagen.jpg", true, category);
        Product product2 = new Product("Product 2", 30.00, "https://imagen.jpg", false, category);

        given(productRepository.findAll()).willReturn(List.of(product1, product2));

        List<ProductResponse> result = productService.getAllProducts();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).name()).isEqualTo("Product 1");
        assertThat(result.get(1).name()).isEqualTo("Product 2");
        assertThat(result.get(0).price()).isEqualTo(20.00);
        assertThat(result.get(1).price()).isEqualTo(30.00);
        assertThat(result.get(0).imageUrl()).isEqualTo("https://imagen.jpg");
        assertThat(result.get(1).imageUrl()).isEqualTo("https://imagen.jpg");
        assertThat(result.get(0).featured()).isEqualTo(true);
        assertThat(result.get(1).featured()).isEqualTo(false);
        assertThat(result.get(0).category()).isEqualTo("category");
        assertThat(result.get(1).category()).isEqualTo("category");

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void shouldAddProductWithExistingCategorySuccessfully() {
        ProductRequest request = new ProductRequest("Product 3", 30.00, "https://imagen.jpg", false, "category");

        Product savedProduct = new Product(request.name(), request.price(), request.imageUrl(), request.featured(), category);
        savedProduct.setId(1L);

        given(categoryRepository.findByName("category")).willReturn(Optional.of(category));
        given(productRepository.save(any(Product.class))).willReturn(savedProduct);

        ProductResponse response = productService.addProduct(request);

        assertThat(response.name()).isEqualTo("Product 3");
        assertThat(response.category()).isEqualTo("category");

        verify(categoryRepository, times(1)).findByName("category");
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void shouldThrowExceptionWhenCategoryDoesNotExist() {
        ProductRequest request = new ProductRequest("Product 4", 50.00, "https://imagen.jpg", true, "Textil");

        given(categoryRepository.findByName(anyString())).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> productService.addProduct(request));

        assertThat(thrown)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("CategoryName not found");

        verify(productRepository, never()).save(any());
    }
}