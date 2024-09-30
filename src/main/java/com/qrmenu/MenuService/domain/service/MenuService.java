package com.qrmenu.MenuService.domain.service;

import com.qrmenu.MenuService.domain.model.entity.Menu;
import com.qrmenu.MenuService.domain.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Menu getMenuByRestaurantId(Long restaurantId){
        return menuRepository.findByRestaurantId(restaurantId).orElse(null);
    }
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu updateMenu(Long id, Menu menuDetails) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        // Update menu fields
        return menuRepository.save(menu);
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }
}
