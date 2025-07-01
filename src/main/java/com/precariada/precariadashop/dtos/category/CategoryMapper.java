package com.precariada.precariadashop.dtos.category;

import com.precariada.precariadashop.models.Category;

public interface CategoryMapper {
    public static Category dtoToEntity (CategoryRequest dto){
        return new Category(dto.name());
    }
    public static CategoryResponse entityToDto (Category category){
        return new CategoryResponse(category.getId(), category.getName(), category.getProducts());
    }
}
