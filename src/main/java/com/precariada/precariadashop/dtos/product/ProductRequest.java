package com.precariada.precariadashop.dtos.product;

import com.precariada.precariadashop.models.Category;
import jakarta.validation.constraints.*;

public record ProductRequest(
        @NotBlank (message= "Name is required")
        @Size(max=50, message= "Name must be less than 50 characters")
        String name,

        @NotNull (message = "Price is required")
        @Min(value = 0, message= "Price must be greater than 0")
        Double price,

        @Pattern(message = "Invalid content type", regexp = "^(https?://.*\\.(png|jpg|jpeg|gif|svg))$")
        String imageUrl,

        boolean featured,

        @NotBlank (message = "Category is required")
        Category category
) {
}



