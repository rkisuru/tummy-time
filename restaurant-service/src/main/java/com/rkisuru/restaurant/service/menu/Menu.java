package com.rkisuru.restaurant.service.menu;

import com.rkisuru.restaurant.service.menuItem.MenuItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cover;
    private String description;

    @OneToMany(mappedBy = "menu")
    private List<MenuItem> menuItems;
}