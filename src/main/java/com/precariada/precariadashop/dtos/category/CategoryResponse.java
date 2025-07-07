package com.precariada.precariadashop.dtos.category;

import com.precariada.precariadashop.dtos.product.ProductResponse;
import java.util.List;

public record CategoryResponse(
        Long id,
        String name,
        List<ProductResponse> products
) {
}