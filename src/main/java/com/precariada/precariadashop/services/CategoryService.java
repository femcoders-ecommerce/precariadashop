package com.precariada.precariadashop.services;

import com.precariada.precariadashop.dtos.category.CategoryMapper;
import com.precariada.precariadashop.dtos.category.CategoryRequest;
import com.precariada.precariadashop.dtos.category.CategoryResponse;
import com.precariada.precariadashop.models.Category;
import com.precariada.precariadashop.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

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

    public CategoryResponse addCategory(CategoryRequest categoryRequest){
        Category newCategory = CategoryMapper.dtoToEntity(categoryRequest);
        Category savedCategory = categoryRepository.save(newCategory);
        return CategoryMapper.entityToDto(savedCategory);
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest updateCategory){
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category with ID" + id + "was not found"));
        existing.setName(updateCategory.name());

        Category saved = categoryRepository.save(existing);
        return CategoryMapper.entityToDto(saved);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
