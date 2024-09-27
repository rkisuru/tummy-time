package com.rkisuru.restaurant.service.menuItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemResponse {

    private Long id;
    private String name;
    private List<String> consistOf;
    private BigDecimal price;
    private byte[] image;
}
