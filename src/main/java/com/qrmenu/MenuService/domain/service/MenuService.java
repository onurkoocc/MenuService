package com.qrmenu.MenuService.domain.service;

import com.qrmenu.MenuService.domain.model.request.MenuRequest;
import com.qrmenu.MenuService.domain.model.response.MenuResponse;

import java.util.UUID;

public interface MenuService {
    MenuResponse createMenu(MenuRequest menuRequest);
    MenuResponse getMenuById(Long menuId);
    MenuResponse getMenuByRestaurantId(UUID restaurantId);
}
