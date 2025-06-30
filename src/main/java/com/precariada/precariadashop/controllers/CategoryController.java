package com.precariada.precariadashop.controllers;

import com.precariada.precariadashop.dtos.category.CategoryRequest;
import com.precariada.precariadashop.dtos.category.CategoryResponse;
import com.precariada.precariadashop.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategory(){
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody @Valid CategoryRequest newCategory){
        CategoryResponse createdCategory = categoryService.addCategory(newCategory);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }
}
