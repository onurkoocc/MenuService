package com.qrmenu.MenuService.domain.controller;

import com.qrmenu.MenuService.domain.model.request.MenuRequest;
import com.qrmenu.MenuService.domain.model.response.MenuResponse;
import com.qrmenu.MenuService.domain.service.impl.MenuServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuServiceImpl menuService;

    @PostMapping
    @PreAuthorize("hasRole('RESTAURANT_MANAGER')")
    public ResponseEntity<MenuResponse> createMenu(@Valid @RequestBody MenuRequest menuRequest) {
        MenuResponse menuResponse = menuService.createMenu(menuRequest);
        return ResponseEntity.ok(menuResponse);
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<MenuResponse> getMenuById(@PathVariable Long menuId) {
        MenuResponse menuResponse = menuService.getMenuById(menuId);
        return ResponseEntity.ok(menuResponse);
    }

    // Other endpoints...
}
