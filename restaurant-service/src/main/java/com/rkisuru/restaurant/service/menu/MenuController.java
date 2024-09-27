package com.rkisuru.restaurant.service.menu;

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

    @PostMapping
    public ResponseEntity<Menu> createMenu(@Valid @RequestBody MenuRequest request) {

        return ResponseEntity.ok(menuService.createMenu(request));
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<Menu> updateMenu(@RequestBody MenuEditRequest request, @PathVariable Long menuId) {

        return ResponseEntity.ok(menuService.updateMenu(request, menuId));
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<String> deleteMenu(@PathVariable Long menuId) {

        return ResponseEntity.ok(menuService.deleteMenu(menuId));
    }

    @PostMapping(value = "/{menuId}/cover", consumes = "multipart/form-data")
    public ResponseEntity<Menu> uploadCover(@PathVariable Long menuId, @RequestParam("file") MultipartFile file) throws IOException {

        menuService.uploadMenuCover(menuId, file);
        return ResponseEntity.ok().build();
    }

}
