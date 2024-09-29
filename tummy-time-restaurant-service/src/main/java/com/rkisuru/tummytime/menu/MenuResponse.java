package com.rkisuru.tummytime.menu;

import com.rkisuru.tummytime.menuItem.MenuItem;
import com.rkisuru.tummytime.type.MenuType;
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
