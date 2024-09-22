package com.rkisuru.restaurant.service.menuItem;

import java.math.BigDecimal;

public record MenuItemRequest(

        String name,
        String description,
        BigDecimal price,
        String category
) {
}
