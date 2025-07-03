package com.precariada.precariadashop.dtos.product;

import com.precariada.precariadashop.models.Product;

public interface ProductMapper {
    public static Product dtoToEntity (ProductRequest dto){
        return new Product(dto.name(), dto.price(), dto.imageUrl(), dto.featured(), dto.category());
    }
    public static ProductResponse entityToDto (Product product){
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getImageUrl(), product.isFeatured(), product.getCategory());
    }
}
