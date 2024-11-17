package com.rkisuru.tummytime.menuItem;

import com.rkisuru.tummytime.file.ImageUploadService;
import com.rkisuru.tummytime.mapper.Mapper;
import com.rkisuru.tummytime.menu.Menu;
import com.rkisuru.tummytime.menu.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository repository;
    private final MenuRepository menuRepository;
    private final Mapper mapper;
    private final ImageUploadService imageUploadService;

    public List<MenuItem> findAllByMenuId(Long menuId) {

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));

        return menu.getMenuItems();
    }

    public MenuItem createMenuItem(Long menuId, MenuItemRequest request, String userId) {

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));

        if (!Objects.equals(menu.getUserId(), userId)) {
            throw new RuntimeException("You are not allowed to add items to this menu");
        }

        MenuItem menuItem = mapper.toMenuItem(request);
        menuItem.setMenu(menu);
        return repository.save(menuItem);
    }


    public MenuItem findById(Long menuId, Long itemId) {

        return repository.findMenuItemByMenuId(menuId, itemId);
    }

    public MenuItem updateMenuItem(MenuitemEditRequest request, Long menuId, Long itemId, String userId) {

        MenuItem item = repository.findMenuItemByMenuId(menuId, itemId);

        if (item == null) {
            throw new EntityNotFoundException("Item not found");
        }

        if (!Objects.equals(item.getMenu().getUserId(), userId)) {
            throw new RuntimeException("You are not allowed to update items in this menu");
        }

        if (!request.name().isBlank()) {
            item.setName(request.name());
        }
        if (!request.consistOf().isEmpty()){
            item.setConsistOf(request.consistOf());
        }
        if (request.price() != null) {
            item.setPrice(request.price());
        }
        return repository.save(item);
    }

    public void deleteItem(Long itemId) {
        repository.deleteById(itemId);
    }

    public void uploadMenuItemImage(Long menuItemId, MultipartFile file) throws IOException {
        MenuItem menuItem = repository.findById(menuItemId)
                .orElseThrow(() -> new EntityNotFoundException("MenuItem not found"));
        var menuItemCover = imageUploadService.uploadImage(file);
        menuItem.setImage(menuItemCover);
        repository.save(menuItem);
    }

    public Long triggerAvailable(Long itemId, String userId) {

        MenuItem item = repository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        if(!Objects.equals(item.getMenu().getUserId(), userId)) {
            throw new RuntimeException("You are not allowed to update items in this menu");
        }

        if (!item.isAvailable()) {
            item.setAvailable(true);
            repository.save(item);
        }
        else {
            item.setAvailable(false);
            repository.save(item);
        }
        return item.getId();
    }
}
