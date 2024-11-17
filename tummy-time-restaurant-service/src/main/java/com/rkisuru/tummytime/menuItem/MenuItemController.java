package com.rkisuru.tummytime.menuItem;

import com.rkisuru.tummytime.menu.Menu;
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
    public ResponseEntity<MenuItem> createMenuItem(@Valid @PathVariable Long menuId, @RequestBody MenuItemRequest request, String userId) {

        return ResponseEntity.ok(menuItemService.createMenuItem(menuId, request, userId));
    }

    @GetMapping("/{menuId}/items/{itemId}")
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable Long menuId, @PathVariable Long itemId) {

        return ResponseEntity.ok(menuItemService.findById(menuId, itemId));
    }

    @PutMapping("/{menuId}/items")
    public ResponseEntity<MenuItem> updateMenuItem(@RequestBody MenuitemEditRequest request, @PathVariable Long menuId, @RequestParam Long itemId, String userId) {

        return ResponseEntity.ok(menuItemService.updateMenuItem(request, menuId, itemId, userId));
    }

    @DeleteMapping("/{menuId}/items")
    public ResponseEntity<String> deleteItem(@PathVariable Long menuId, @RequestParam Long itemId) {

            menuItemService.deleteItem(itemId);
            return ResponseEntity.ok("Item deleted successfully");
    }

    @PostMapping(value = "/{menuId}/items", consumes = "multipart/form-data")
    public ResponseEntity<Menu> uploadImage(@PathVariable Long menuId, @RequestParam("file") MultipartFile file, @RequestParam Long itemId) throws IOException {

        menuItemService.uploadMenuItemImage(itemId, file);
        return ResponseEntity.ok().build();
    }



}
