package com.rkisuru.restaurant.service.mapper;

import com.rkisuru.restaurant.service.file.ReadFile;
import com.rkisuru.restaurant.service.menu.Menu;
import com.rkisuru.restaurant.service.menu.MenuRequest;
import com.rkisuru.restaurant.service.menu.MenuResponse;
import com.rkisuru.restaurant.service.menuItem.MenuItem;
import com.rkisuru.restaurant.service.menuItem.MenuItemRequest;
import com.rkisuru.restaurant.service.menuItem.MenuItemResponse;
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
                .consistOf(request.consistOf())
                .price(request.price())
                .build();
    }

    public MenuResponse toMenuResponse(Menu menu) {

        return MenuResponse.builder()
                .id(menu.getId())
                .menuName(menu.getName())
                .description(menu.getDescription())
                .type(menu.getType())
                .cover(ReadFile.readFile(menu.getCover()))
                .menuItems(menu.getMenuItems())
                .build();
    }

    public MenuItemResponse toMenuItemResponse(MenuItem menuItem) {

        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .image(ReadFile.readFile(menuItem.getImage()))
                .consistOf(menuItem.getConsistOf())
                .price(menuItem.getPrice())
                .build();
    }
}
