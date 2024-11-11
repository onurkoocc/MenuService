package com.qrmenu.MenuService.domain.service;

import com.qrmenu.MenuService.domain.model.request.CategoryRequest;
import com.qrmenu.MenuService.domain.model.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);

    public CategoryResponse getCategoryById(Long id);
    CategoryResponse updateCategory(CategoryRequest categoryRequest);
    void deleteCategory(Long categoryId);
}
