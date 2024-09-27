package com.rkisuru.restaurant.service.menu;

import com.rkisuru.restaurant.service.type.MenuType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MenuRequest(

        @NotEmpty(message = "Name cannot be empty")
        @NotNull(message = "Name cannot be null")
        @Size(min = 2, max = 50, message = "Name must at least 2 characters long")
        String name,

        @NotEmpty(message = "Description cannot be empty")
        @NotNull(message = "Description cannot be null")
        String description,

        MenuType type
) {
}
