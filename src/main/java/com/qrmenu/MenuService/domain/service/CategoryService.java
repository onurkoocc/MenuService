package com.qrmenu.MenuService.domain.service;

import com.qrmenu.MenuService.domain.model.entity.Category;
import com.qrmenu.MenuService.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Create a new category or subcategory
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Get subcategories of a category
    public List<Category> getSubCategories(Long parentCategoryId) {
        return categoryRepository.findByParentCategoryId(parentCategoryId);
    }

    // Get top-level categories of a menu
    public List<Category> getTopLevelCategories(Long menuId) {
        return categoryRepository.findByMenu_IdAndParentCategoryIsNull(menuId);
    }

    // Additional methods for updating and deleting categories
    // ...
}
