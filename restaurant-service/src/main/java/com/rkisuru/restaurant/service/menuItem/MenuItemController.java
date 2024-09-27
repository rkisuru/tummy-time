package com.rkisuru.restaurant.service.menuItem;

import com.rkisuru.restaurant.service.menu.Menu;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
@CrossOrigin
public class MenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping("/{menuId}/items")
    public ResponseEntity<List<MenuItem>> getAllByMenuId(@PathVariable Long menuId) {

        return ResponseEntity.ok(menuItemService.findAllByMenuId(menuId));
    }

    @PostMapping("/{menuId}/items")
    public ResponseEntity<MenuItem> createMenuItem(@Valid @PathVariable Long menuId, @RequestBody MenuItemRequest request) {

        return ResponseEntity.ok(menuItemService.createMenuItem(menuId, request));
    }

    @GetMapping("/{menuId}/items/{itemId}")
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable Long menuId, @PathVariable Long itemId) {

        return ResponseEntity.ok(menuItemService.findById(menuId, itemId));
    }

    @PutMapping("/{menuId}/items/{itemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@RequestBody MenuitemEditRequest request, @PathVariable Long menuId, @PathVariable Long itemId) {

        return ResponseEntity.ok(menuItemService.updateMenuItem(request, menuId, itemId));
    }

    @DeleteMapping("/{menuId}/items/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable Long itemId) {

        return ResponseEntity.ok(menuItemService.deleteItem(itemId));
    }

    @PostMapping(value = "/{menuId}/items/{itemId}/cover", consumes = "multipart/form-data")
    public ResponseEntity<Menu> uploadImage(@PathVariable Long itemId, @RequestParam("file") MultipartFile file) throws IOException {

        menuItemService.uploadMenuItemImage(itemId, file);
        return ResponseEntity.ok().build();
    }

}
