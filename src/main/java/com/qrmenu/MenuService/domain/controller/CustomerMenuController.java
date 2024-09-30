package com.qrmenu.MenuService.domain.controller;

import com.qrmenu.MenuService.domain.model.entity.Menu;
import com.qrmenu.MenuService.domain.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/menus")
public class CustomerMenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Menu> getMenuByRestaurant(@PathVariable Long restaurantId) {
        Menu menu = menuService.getMenuByRestaurantId(restaurantId);
        return ResponseEntity.ok(menu);
    }
}
