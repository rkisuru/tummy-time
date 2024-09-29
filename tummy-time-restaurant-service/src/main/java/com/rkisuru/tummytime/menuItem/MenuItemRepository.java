package com.rkisuru.tummytime.menuItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @Query("SELECT menuItem FROM MenuItem menuItem WHERE menuItem.menu.id = :menuId AND menuItem.id = :itemId")
    MenuItem findMenuItemByMenuId(@Param("menuId") Long menuId, @Param("itemId") Long itemId);
}
