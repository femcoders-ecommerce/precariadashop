package com.precariada.precariadashop.services;

import com.precariada.precariadashop.dtos.category.CategoryMapper;
import com.precariada.precariadashop.dtos.category.CategoryResponse;
import com.precariada.precariadashop.models.Category;
import com.precariada.precariadashop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> CategoryMapper.entityToDto(category)).toList();
    }
}
