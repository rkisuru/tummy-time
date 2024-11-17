package com.rkisuru.tummytime.menu;

public record MenuEditRequest(

        String name,
        String description,
        String type
) {
}
