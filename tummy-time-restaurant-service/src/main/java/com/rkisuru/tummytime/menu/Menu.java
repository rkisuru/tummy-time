package com.rkisuru.tummytime.menu;

import com.rkisuru.tummytime.menuItem.MenuItem;
import com.rkisuru.tummytime.type.MenuType;
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

    @Column(nullable = false)
    private String name;

    private String cover;
    private String description;

    @Enumerated(EnumType.STRING)
    private MenuType type;

    @OneToMany(mappedBy = "menu")
    private List<MenuItem> menuItems;
}