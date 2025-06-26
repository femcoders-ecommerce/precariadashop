package com.precariada.precariadashop.dtos.product;

public record ProductResponse(
        Long id,
        String name,
        Double price,
        String imageUrl,
        boolean featured
        /* private Category category*/
) {
}
