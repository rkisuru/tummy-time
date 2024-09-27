package com.rkisuru.restaurant.service.menuItem;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record MenuItemRequest(

        @NotEmpty(message = "Name cannot be empty")
        @NotNull(message = "Name cannot be null")
        @Size(min = 2, max = 50, message = "Name must at least 2 characters long")
        String name,

        List<String> consistOf,

        @NotEmpty(message = "Price cannot be empty")
        @NotNull(message = "Price cannot be null")
        BigDecimal price
) {
}
