package com.rkisuru.tummytime.menuItem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rkisuru.tummytime.menu.Menu;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("JpaAttributeTypeInspection")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private List<String> consistOf;

    @Column(nullable = false)
    private BigDecimal price;

    private String image;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;
}
