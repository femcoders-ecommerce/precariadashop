package com.precariada.precariadashop.dtos.product;

import com.precariada.precariadashop.models.Category;
import com.precariada.precariadashop.models.Product;

public interface ProductMapper {
    static Product dtoToEntity (ProductRequest dto, Category category){
        return new Product(dto.name(), dto.price(), dto.imageUrl(), dto.featured(), category);
    }
    static ProductResponse entityToDto (Product product){
        String category = (product.getCategory() != null) ? product.getCategory().getName() : null;
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getImageUrl(), product.isFeatured(), category);
    }
}