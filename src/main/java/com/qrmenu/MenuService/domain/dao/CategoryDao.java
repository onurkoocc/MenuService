package com.qrmenu.MenuService.domain.dao;

import com.qrmenu.MenuService.domain.model.request.CategoryRequest;
import com.qrmenu.MenuService.domain.model.response.CategoryResponse;

public interface CategoryDao {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse getCategoryById(Long categoryId);
    CategoryResponse updateCategory(CategoryRequest categoryRequest);
    void deleteCategory(Long categoryId);
}