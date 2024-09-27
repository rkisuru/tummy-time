package com.rkisuru.restaurant.service.menu;

import com.rkisuru.restaurant.service.menuItem.MenuItem;
import com.rkisuru.restaurant.service.type.MenuType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuResponse {

    private Long id;
    private String menuName;
    private byte[] cover;
    private String description;
    private MenuType type;
    private List<MenuItem> menuItems;
}
