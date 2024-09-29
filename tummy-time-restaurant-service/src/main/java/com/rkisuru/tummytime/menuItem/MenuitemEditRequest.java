package com.rkisuru.tummytime.menuItem;

import java.math.BigDecimal;
import java.util.List;

public record MenuitemEditRequest(

        String name,
        List<String> consistOf,
        BigDecimal price
) {
}
