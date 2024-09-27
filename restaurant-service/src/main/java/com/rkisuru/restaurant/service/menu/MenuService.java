package com.rkisuru.restaurant.service.menu;

import com.rkisuru.restaurant.service.file.ImageUploadService;
import com.rkisuru.restaurant.service.mapper.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final Mapper mapper;
    private final ImageUploadService imageUploadService;

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
            menuRepository.save(menu);
        }
        if (!request.description().isBlank()) {
            menu.setDescription(request.description());
            menuRepository.save(menu);
        }
        menu.setType(request.type());
        return menuRepository.save(menu);
    }

    public String deleteMenu(Long menuId) {

        menuRepository.deleteById(menuId);
        return "Menu deleted Successfully";
    }

    public void uploadMenuCover(Long menuId, MultipartFile file) throws IOException {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));
            var menuCover = imageUploadService.uploadImage(file);
            menu.setCover(menuCover);
            menuRepository.save(menu);
    }
}
