package com.rkisuru.tummytime.menu;

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
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus() {

        return ResponseEntity.ok(menuService.findAll());
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long menuId) {

        return ResponseEntity.ok(menuService.findById(menuId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MenuResponse>> getMenusByUserId(@PathVariable String userId) {

        return ResponseEntity.ok(menuService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Menu> createMenu(@Valid @RequestBody MenuRequest request, String userId) {

        return ResponseEntity.ok(menuService.createMenu(request, userId));
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<Menu> updateMenu(@RequestBody MenuEditRequest request, @PathVariable Long menuId, String userId) {

        return ResponseEntity.ok(menuService.updateMenu(request, menuId, userId));
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long menuId) {
            menuService.deleteMenu(menuId);
            return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{menuId}/cover", consumes = "multipart/form-data")
    public ResponseEntity<Menu> uploadCover(@PathVariable Long menuId, @RequestParam("file") MultipartFile file, String userId) throws IOException {

        menuService.uploadMenuCover(menuId, file, userId);
        return ResponseEntity.ok().build();
    }

}
