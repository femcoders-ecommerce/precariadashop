package com.precariada.precariadashop.dtos.category;

import com.precariada.precariadashop.dtos.product.ProductResponse;
import com.precariada.precariadashop.models.Category;
import java.util.List;
import java.util.stream.Collectors;

public interface CategoryMapper {
    static Category dtoToEntity (CategoryRequest dto){
        return new Category(dto.name());
    }
    static CategoryResponse entityToDto(Category category) {
        List<ProductResponse> productResponses = category.getProducts().stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getImageUrl(),
                        product.isFeatured(),
                        product.getCategory().getName()
                ))
                .collect(Collectors.toList());

        return new CategoryResponse(category.getId(), category.getName(), productResponses);
    }
}