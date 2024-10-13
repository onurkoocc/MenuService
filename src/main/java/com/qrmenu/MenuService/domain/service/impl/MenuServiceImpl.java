package com.qrmenu.MenuService.domain.service.impl;

import com.qrmenu.MenuService.domain.dao.MenuDao;
import com.qrmenu.MenuService.domain.model.request.MenuRequest;
import com.qrmenu.MenuService.domain.model.response.MenuResponse;
import com.qrmenu.MenuService.domain.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private MenuDao menuDao;

    public MenuResponse createMenu(MenuRequest menuRequest) {
        return menuDao.createMenu(menuRequest);
    }

    public MenuResponse getMenuById(Long menuId) {
        return menuDao.getMenuById(menuId);
    }

    public MenuResponse getMenuByRestaurantId(UUID restaurantId) {
        return menuDao.getMenuByRestaurantId(restaurantId);
    }
}
