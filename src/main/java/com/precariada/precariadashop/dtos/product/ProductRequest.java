package com.precariada.precariadashop.dtos.product;

import jakarta.validation.constraints.*;

public record ProductRequest(
        @NotBlank (message= "Name is required")
        @Size(max=50, message= "Name must be less than 50 characters")
        String name,

        @NotNull (message = "Price is required")
        @Min(value = 0, message= "Price must be greater than 0")
        Double price,

        @Pattern(regexp = "^(https?://.*\\.(png|jpg|jpeg|gif|svg))$", message = "Invalid content type")
        String imageUrl,

        boolean featured

        /* private Category category*/
) {
}



