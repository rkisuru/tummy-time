package com.rkisuru.tummytime.mapper;

import com.rkisuru.tummytime.file.ReadFile;
import com.rkisuru.tummytime.menu.Menu;
import com.rkisuru.tummytime.menu.MenuRequest;
import com.rkisuru.tummytime.menu.MenuResponse;
import com.rkisuru.tummytime.menuItem.*;
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

    public PurchaseResponse toPurchaseResponse(MenuItem item) {

        return new PurchaseResponse(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getConsistOf()
        );
    }
}
