package com.rkisuru.restaurant.service.menu;

import com.rkisuru.restaurant.service.type.MenuType;

public record MenuEditRequest(

        String name,
        String description,
        MenuType type
) {
}
