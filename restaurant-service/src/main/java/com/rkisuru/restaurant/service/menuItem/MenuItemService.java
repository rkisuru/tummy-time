package com.rkisuru.restaurant.service.menuItem;

import com.rkisuru.restaurant.service.mapper.Mapper;
import com.rkisuru.restaurant.service.menu.Menu;
import com.rkisuru.restaurant.service.menu.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository repository;
    private final MenuRepository menuRepository;
    private final Mapper mapper;

    public List<MenuItem> findAllByMenuId(Long menuId) {

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));

        return menu.getMenuItems();
    }

    public MenuItem createMenuItem(Long menuId, MenuItemRequest request) {

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));

        MenuItem menuItem = mapper.toMenuItem(request);
        menuItem.setMenu(menu);
        return repository.save(menuItem);
    }


    public MenuItem findById(Long menuId, Long itemId) {

        return repository.findMenuItemByMenuId(menuId, itemId);
    }

    public MenuItem updateMenuItem(MenuitemEditRequest request, Long menuId, Long itemId) {

        MenuItem item = repository.findMenuItemByMenuId(menuId, itemId);
        if (item != null) {
            if (!request.name().isBlank()){
                item.setName(request.name());
            }
            if (!request.description().isBlank()){
                item.setDescription(request.description());
            }
            if (!request.category().isBlank()) {
                item.setCategory(request.category());
            }
            if (request.price() != null) {
                item.setPrice(request.price());
            }
            return repository.save(item);
        } else {
            throw new EntityNotFoundException("Item not found");
        }
    }

    public String deleteItem(Long itemId) {

        repository.deleteById(itemId);
        return "Item deleted Successfully";
    }
}
