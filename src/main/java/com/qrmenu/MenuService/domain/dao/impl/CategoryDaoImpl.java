package com.qrmenu.MenuService.domain.dao.impl;

import com.qrmenu.MenuService.config.exception.ResourceNotFoundException;
import com.qrmenu.MenuService.domain.dao.CategoryDao;
import com.qrmenu.MenuService.domain.mapper.MenuMapper;
import com.qrmenu.MenuService.domain.model.entity.*;
import com.qrmenu.MenuService.domain.model.request.CategoryRequest;
import com.qrmenu.MenuService.domain.model.request.ProductRequest;
import com.qrmenu.MenuService.domain.model.response.CategoryResponse;
import com.qrmenu.MenuService.domain.repository.CategoryRepository;
import com.qrmenu.MenuService.domain.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MenuRepository menuRepository;

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        // Map DTO to Entity
        Category category = MenuMapper.categoryRequestToCategory(categoryRequest);

        // Set Menu
        if (categoryRequest.getParentCategoryId() == null) {
            // Root category requires a menu ID
            if (categoryRequest.getMenuId() == null) {
                throw new IllegalArgumentException("Menu ID is required for root categories");
            }
            Menu menu = menuRepository.findById(categoryRequest.getMenuId())
                    .orElseThrow(() -> new ResourceNotFoundException("Menu not found with id: " + categoryRequest.getMenuId()));
            category.setMenu(menu);
        } else {
            // Subcategory
            Category parentCategory = categoryRepository.findById(categoryRequest.getParentCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent Category not found with id: " + categoryRequest.getParentCategoryId()));
            category.setParentCategory(parentCategory);
            category.setMenu(parentCategory.getMenu());
        }

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
                Category subCategory = mapCategoryRequestToCategory(subCategoryRequest, category, category.getMenu());
                subCategories.add(subCategory);
            }
            category.setSubCategories(subCategories);
        }

        // Save the category
        Category savedCategory = categoryRepository.save(category);

        // Map Entity to DTO
        return MenuMapper.categoryToCategoryResponse(savedCategory);
    }

    public CategoryResponse getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        return MenuMapper.categoryToCategoryResponse(category);
    }

    // Helper method to map subcategories
    private Category mapCategoryRequestToCategory(CategoryRequest categoryRequest, Category parentCategory, Menu menu) {
        Category category = MenuMapper.categoryRequestToCategory(categoryRequest);
        category.setParentCategory(parentCategory);
        category.setMenu(menu);

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
