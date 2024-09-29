package com.rkisuru.tummytime.menu;

import com.rkisuru.tummytime.type.MenuType;

public record MenuEditRequest(

        String name,
        String description,
        MenuType type
) {
}
