package com.rkisuru.restaurant.service.menuItem;

import java.math.BigDecimal;

public record MenuitemEditRequest(

        String name,
        String description,
        BigDecimal price,
        String category
) {
}
