package com.qrmenu.MenuService.domain.controller;

import com.qrmenu.MenuService.domain.model.response.MenuResponse;
import com.qrmenu.MenuService.domain.service.MenuService;
import com.qrmenu.MenuService.domain.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/menus/restaurant")
public class CustomerMenuController {

    @Autowired
    private MenuServiceImpl menuService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<MenuResponse> getMenuByRestaurant(@PathVariable UUID restaurantId) {
        MenuResponse menuResponse = menuService.getMenuByRestaurantId(restaurantId);
        return ResponseEntity.ok(menuResponse);
    }

    @GetMapping("/test")
    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER')")
    public ResponseEntity<String> gettt(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok("Yes this is restaurant manager : "+userId);
    }
}
