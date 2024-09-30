package com.qrmenu.MenuService.domain.controller;

import com.qrmenu.MenuService.domain.model.entity.Category;
import com.qrmenu.MenuService.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create a new category or subcategory
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    // Get subcategories of a category
    @GetMapping("/{id}/subcategories")
    public ResponseEntity<List<Category>> getSubCategories(@PathVariable Long id) {
        List<Category> subCategories = categoryService.getSubCategories(id);
        return ResponseEntity.ok(subCategories);
    }

    // Get top-level categories of a menu
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<Category>> getTopLevelCategories(@PathVariable Long menuId) {
        List<Category> categories = categoryService.getTopLevelCategories(menuId);
        return ResponseEntity.ok(categories);
    }

    // Additional endpoints for updating and deleting categories
    // ...
}
