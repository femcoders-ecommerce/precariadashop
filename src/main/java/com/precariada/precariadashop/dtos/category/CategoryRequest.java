package com.precariada.precariadashop.dtos.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CategoryRequest (
        @NotBlank(message = "Name is required")
        @Size(max = 30, message = "Name must be less than 30 characters")
        String name
){
}
