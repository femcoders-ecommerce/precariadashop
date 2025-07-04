package com.precariada.precariadashop.dtos.product;

import com.precariada.precariadashop.models.Category;

public record ProductResponse(
        Long id,
        String name,
        Double price,
        String imageUrl,
        boolean featured,
        String category
) {
}
