package com.rkisuru.tummytime.menuItem;

import jakarta.validation.constraints.NotNull;

public record PurchaseRequest(

        @NotNull
        Long itemId
) {
}
