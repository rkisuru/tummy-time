package com.rkisuru.restaurant.service.menu;

import com.rkisuru.restaurant.service.mapper.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final Mapper mapper;

    public List<Menu> findAll() {

        return menuRepository.findAll();
    }

    public Menu findById(Long menuId) {

        return menuRepository.findById(menuId)
                .orElseThrow(()-> new EntityNotFoundException("Menu not found"));
    }

    public Menu createMenu(MenuRequest request) {

        Menu menu = mapper.toMenu(request);
        return menuRepository.save(menu);
    }

    public Menu updateMenu(MenuEditRequest request, Long menuId) {

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));

        if (!request.name().isBlank()) {
            menu.setName(request.name());
            return menuRepository.save(menu);
        }
        if (!request.description().isBlank()) {
            menu.setDescription(request.description());
            return menuRepository.save(menu);
        }
        return menuRepository.save(menu);
    }

    public String deleteMenu(Long menuId) {

        menuRepository.deleteById(menuId);
        return "Menu deleted Successfully";
    }
}
