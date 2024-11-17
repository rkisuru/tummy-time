package com.rkisuru.tummytime.menu;

import com.rkisuru.tummytime.menuItem.MenuItem;
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
    private String type;
    private List<MenuItem> menuItems;
}
