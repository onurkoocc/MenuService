package com.qrmenu.MenuService.domain.dao.impl;

import com.qrmenu.MenuService.config.exception.ResourceNotFoundException;
import com.qrmenu.MenuService.domain.dao.MenuDao;
import com.qrmenu.MenuService.domain.mapper.MenuMapper;
import com.qrmenu.MenuService.domain.model.entity.*;
import com.qrmenu.MenuService.domain.model.request.CategoryRequest;
import com.qrmenu.MenuService.domain.model.request.MenuRequest;
import com.qrmenu.MenuService.domain.model.request.ProductRequest;
import com.qrmenu.MenuService.domain.model.response.MenuResponse;
import com.qrmenu.MenuService.domain.repository.MenuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MenuDaoImpl implements MenuDao {

    @Autowired
    private MenuRepository menuRepository;

    public MenuResponse createMenu(MenuRequest menuRequest) {
        // Map DTO to Entity
        Menu menu = MenuMapper.menuRequestToMenu(menuRequest);

        // Manually set categories
        if (menuRequest.getCategories() != null) {
            List<Category> categories = new ArrayList<>();
            for (CategoryRequest categoryRequest : menuRequest.getCategories()) {
                Category category = mapCategoryRequestToCategory(categoryRequest, null, menu);
                categories.add(category);
            }
            menu.setCategories(categories);
        }

        // Save the menu
        Menu savedMenu = menuRepository.save(menu);

        // Map Entity to DTO
        return MenuMapper.menuToMenuResponse(savedMenu);
    }

    public MenuResponse getMenuById(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu not found with id: " + menuId));

        return MenuMapper.menuToMenuResponse(menu);
    }

    public MenuResponse getMenuByRestaurantId(UUID restaurantId) {
        Menu menu = menuRepository.findByRestaurantId(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu not found with restaurant id: " + restaurantId));

        return MenuMapper.menuToMenuResponse(menu);
    }

    // Helper method to map CategoryRequest to Category
    private Category mapCategoryRequestToCategory(CategoryRequest categoryRequest, Category parentCategory, Menu menu) {
        Category category = MenuMapper.categoryRequestToCategory(categoryRequest);
        category.setMenu(menu);
        category.setParentCategory(parentCategory);

        // Map products
        if (categoryRequest.getProducts() != null) {
            List<Product> products = new ArrayList<>();
            for (ProductRequest productRequest : categoryRequest.getProducts()) {
                Product product = MenuMapper.productRequestToProduct(productRequest);
                product.setCategory(category);
                products.add(product);
            }
            category.setProducts(products);
        }

        // Map subcategories
        if (categoryRequest.getSubCategories() != null) {
            List<Category> subCategories = new ArrayList<>();
            for (CategoryRequest subCategoryRequest : categoryRequest.getSubCategories()) {
                Category subCategory = mapCategoryRequestToCategory(subCategoryRequest, category, menu);
                subCategories.add(subCategory);
            }
            category.setSubCategories(subCategories);
        }

        return category;
    }
}
