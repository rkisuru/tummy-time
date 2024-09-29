package com.rkisuru.tummytime.menuItem;

import java.math.BigDecimal;
import java.util.List;

public record PurchaseResponse (

         Long id,
         String name,
         BigDecimal price,
         List<String> consistOf
) {}
