package com.precariada.precariadashop.services;

import com.precariada.precariadashop.dtos.product.ProductMapper;
import com.precariada.precariadashop.dtos.product.ProductRequest;
import com.precariada.precariadashop.dtos.product.ProductResponse;
import com.precariada.precariadashop.models.Product;
import com.precariada.precariadashop.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> ProductMapper.entityToDto(product)).toList();
    }

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product newProduct = ProductMapper.dtoToEntity(productRequest);
        Product savedProduct = productRepository.save(newProduct);
        return ProductMapper.entityToDto(savedProduct);
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Product not found with id: " + id));
        existingProduct.setName(productRequest.name());
        existingProduct.setPrice(productRequest.price());
        existingProduct.setImageUrl(productRequest.imageUrl());
        existingProduct.setFeatured(productRequest.featured());
        Product updatedProduct = productRepository.save(existingProduct);
        return ProductMapper.entityToDto(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
