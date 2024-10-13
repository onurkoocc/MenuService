package com.qrmenu.MenuService.domain.mapper;


import com.qrmenu.MenuService.domain.model.entity.*;
import com.qrmenu.MenuService.domain.model.request.CategoryRequest;
import com.qrmenu.MenuService.domain.model.request.MenuRequest;
import com.qrmenu.MenuService.domain.model.request.ProductRequest;
import com.qrmenu.MenuService.domain.model.response.CategoryResponse;
import com.qrmenu.MenuService.domain.model.response.MenuResponse;
import com.qrmenu.MenuService.domain.model.response.ProductResponse;

import java.util.ArrayList;
import java.util.List;

public class MenuMapper {

    public static Menu menuRequestToMenu(MenuRequest menuRequest) {
        if (menuRequest == null) {
            return null;
        }

        Menu menu = new Menu();
        menu.setRestaurantId(menuRequest.getRestaurantId());
        // Categories will be set in DAO layer
        return menu;
    }

    public static MenuResponse menuToMenuResponse(Menu menu) {
        if (menu == null) {
            return null;
        }

        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setId(menu.getId());
        menuResponse.setRestaurantId(menu.getRestaurantId());

        if (menu.getCategories() != null) {
            List<CategoryResponse> categoryResponses = new ArrayList<>();
            for (Category category : menu.getCategories()) {
                categoryResponses.add(categoryToCategoryResponse(category));
            }
            menuResponse.setCategories(categoryResponses);
        }
        return menuResponse;
    }

    public static Category categoryRequestToCategory(CategoryRequest categoryRequest) {
        if (categoryRequest == null) {
            return null;
        }

        Category category = new Category();
        category.setName(categoryRequest.getName());
        // parentCategory and menu will be set in DAO layer
        return category;
    }

    public static CategoryResponse categoryToCategoryResponse(Category category) {
        if (category == null) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setParentCategoryId(
                category.getParentCategory() != null ? category.getParentCategory().getId() : null
        );

        if (category.getSubCategories() != null) {
            List<CategoryResponse> subCategoryResponses = new ArrayList<>();
            for (Category subCategory : category.getSubCategories()) {
                subCategoryResponses.add(categoryToCategoryResponse(subCategory));
            }
            categoryResponse.setSubCategories(subCategoryResponses);
        }

        if (category.getProducts() != null) {
            List<ProductResponse> productResponses = new ArrayList<>();
            for (Product product : category.getProducts()) {
                productResponses.add(productToProductResponse(product));
            }
            categoryResponse.setProducts(productResponses);
        }

        return categoryResponse;
    }

    public static Product productRequestToProduct(ProductRequest productRequest) {
        if (productRequest == null) {
            return null;
        }

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setTitle(productRequest.getTitle());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setPhotoUrl(productRequest.getPhotoUrl());
        // Category will be set in DAO layer
        return product;
    }

    public static ProductResponse productToProductResponse(Product product) {
        if (product == null) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setTitle(product.getTitle());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setPhotoUrl(product.getPhotoUrl());
        return productResponse;
    }
}
