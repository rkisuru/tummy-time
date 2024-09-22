package com.rkisuru.restaurant.service.mapper;

import com.rkisuru.restaurant.service.menu.Menu;
import com.rkisuru.restaurant.service.menu.MenuRequest;
import com.rkisuru.restaurant.service.menuItem.MenuItem;
import com.rkisuru.restaurant.service.menuItem.MenuItemRequest;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public Menu toMenu(MenuRequest request) {

        return Menu.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    public MenuItem toMenuItem(MenuItemRequest request) {

        return MenuItem.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .category(request.category())
                .build();
    }
}
