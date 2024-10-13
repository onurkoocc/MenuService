package com.qrmenu.MenuService.domain.controller;

import com.qrmenu.MenuService.domain.model.request.CategoryRequest;
import com.qrmenu.MenuService.domain.model.response.CategoryResponse;
import com.qrmenu.MenuService.domain.service.CategoryService;
import com.qrmenu.MenuService.domain.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menus/categories")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasAnyRole('RESTAURANT_MANAGER')")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
        CategoryResponse createdCategory = categoryService.createCategory(request);
        return ResponseEntity.ok(createdCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryResponse);
    }
}
