package com.rkisuru.tummytime.menu;

import com.rkisuru.tummytime.file.ImageUploadService;
import com.rkisuru.tummytime.mapper.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

    public List<MenuResponse> findByUserId(String userId) {

        return menuRepository.findByUserId(userId)
                .stream()
                .map(mapper::toMenuResponse)
                .toList();
    }

    public Menu createMenu(MenuRequest request, String userId) {

        Menu menu = mapper.toMenu(request);
        menu.setUserId(userId);
        return menuRepository.save(menu);
    }

    public Menu updateMenu(MenuEditRequest request, Long menuId, String userId) {

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));

        if(!Objects.equals(menu.getUserId(), userId)) {
            throw new RuntimeException("You are not authorized to update this menu");
        }

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

    public void deleteMenu(Long menuId) {
        menuRepository.deleteById(menuId);
    }

    public void uploadMenuCover(Long menuId, MultipartFile file, String userId) throws IOException {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found"));
        if (!Objects.equals(menu.getUserId(), userId)) {
            throw new RuntimeException("You are not authorized to update this menu");
        }
            var menuCover = imageUploadService.uploadImage(file);
            menu.setCover(menuCover);
            menuRepository.save(menu);
    }
}
